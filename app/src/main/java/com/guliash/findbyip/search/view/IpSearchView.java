package com.guliash.findbyip.search.view;

import android.support.annotation.NonNull;

import com.guliash.findbyip.search.ip.Location;

import io.reactivex.Observable;

public interface IpSearchView {

    @NonNull
    String ip();

    @NonNull
    Observable<Object> findByIpSelections();

    void showError();

    void showLocation(Location location);

}
