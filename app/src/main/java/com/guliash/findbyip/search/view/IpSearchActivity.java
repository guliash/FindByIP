package com.guliash.findbyip.search.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.guliash.findbyip.FindByIpApplication;
import com.guliash.findbyip.R;
import com.guliash.findbyip.search.di.DaggerSearchComponent;
import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import io.reactivex.Observable;

public class IpSearchActivity extends AppCompatActivity implements IpSearchView {

    private EditText ipEditText;
    private Button findButton;

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.bind(this);
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
}
