package com.guliash.findbyip.search.ip;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.guliash.findbyip.R;
import com.guliash.findbyip.core.utils.Objects;
import com.guliash.findbyip.search.ip.di.IpSearchComponentBuilderProvider;
import com.guliash.findbyip.search.location.model.Location;
import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import io.reactivex.Observable;

public class IpSearchFragment extends Fragment implements IpSearchView {

    public static IpSearchFragment newInstance() {
        return new IpSearchFragment();
    }

    public static final String TAG = IpSearchFragment.class.getName();

    private static final int SNACK_ERROR_DURATION = 3000;

    private EditText ipEditText;
    private Button findButton;

    private ViewGroup locationLayout;
    private Button showOnMapButton;
    private TextView locationTextView;

    @Inject
    IpSearchPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Objects.requireInstance(getContext(), IpSearchComponentBuilderProvider.class)
                .ipSearchComponentBuilder()
                .build()
                .inject(this);

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_ip_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ipEditText = view.findViewById(R.id.ip);
        findButton = view.findViewById(R.id.find);

        locationLayout = view.findViewById(R.id.location_layout);
        showOnMapButton = view.findViewById(R.id.show_on_map);
        locationTextView = view.findViewById(R.id.location);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.bind(this);
    }

    @Override
    public void onPause() {
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

    @NonNull
    @Override
    public Observable<Object> showOnMapSelections() {
        return RxView.clicks(showOnMapButton);
    }

    @Override
    public void showError() {
        Snackbar.make(findButton, R.string.find_ip_error, SNACK_ERROR_DURATION).show();
    }

    @Override
    public void hideLocation() {
        locationLayout.setVisibility(View.GONE);
    }

    @Override
    public void showLocation(Location location) {
        locationLayout.setVisibility(View.VISIBLE);
        locationTextView.setText(String.format("%s,%s", location.latitude(), location.longitude()));
    }

}
