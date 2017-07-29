package com.guliash.findbyip.search.location;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.guliash.findbyip.core.utils.Objects;
import com.guliash.findbyip.search.location.di.LocationComponentBuilderProvider;
import com.guliash.findbyip.search.location.model.Location;

import javax.inject.Inject;

public class LocationFragment extends SupportMapFragment implements LocationView {

    private static final float ZOOM = 16f;

    @Inject
    LocationPresenter presenter;

    private GoogleMap map;

    public static LocationFragment newInstance() {
        return new LocationFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Objects.requireInstance(getContext(), LocationComponentBuilderProvider.class)
                .locationComponentBuilder()
                .build()
                .inject(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        getMapAsync(map -> {
            this.map = map;
            presenter.bind(this);
        });
    }

    @Override
    public void onPause() {
        presenter.unbind(this);
        super.onPause();
    }

    @Override
    public void showLocation(@NonNull Location location) {
        final LatLng latLng = new LatLng(location.latitude(), location.longitude());
        map.addMarker(new MarkerOptions().position(latLng));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM));
    }
}
