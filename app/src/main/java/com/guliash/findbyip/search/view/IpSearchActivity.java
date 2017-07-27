package com.guliash.findbyip.search.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.guliash.findbyip.FindByIpApplication;
import com.guliash.findbyip.R;
import com.guliash.findbyip.search.di.DaggerSearchComponent;
import com.guliash.findbyip.search.ip.Location;
import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import io.reactivex.Observable;

public class IpSearchActivity extends AppCompatActivity implements IpSearchView {

    private static final int SNACK_ERROR_DURATION = 3000;

    private EditText ipEditText;
    private Button findButton;
    private TextView locationTextView;

    @Inject
    IpSearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DaggerSearchComponent.builder()
                .applicationComponent(FindByIpApplication.get(this).applicationComponent())
                .build()
                .inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipEditText = (EditText) findViewById(R.id.ip);
        findButton = (Button) findViewById(R.id.find);
        locationTextView = (TextView) findViewById(R.id.location);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.bind(this);
    }

    @Override
    protected void onPause() {
        presenter.unbind(this);

        super.onPause();
    }

    @NonNull
    @Override
    public String ip() {
        return ipEditText.getText().toString();
    }

    @NonNull
    @Override
    public Observable<Object> findByIpSelections() {
        return RxView.clicks(findButton);
    }

    @Override
    public void showError() {
        Snackbar.make(findButton, R.string.find_ip_error, SNACK_ERROR_DURATION).show();
    }

    @Override
    public void showLocation(Location location) {
        locationTextView.setText(String.format("%s,%s", location.latitude(), location.longitude()));
    }
}
