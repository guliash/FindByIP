package com.guliash.findbyip.search.ip;

import android.support.annotation.NonNull;

import com.guliash.findbyip.search.location.model.Location;

import io.reactivex.Observable;

public interface IpSearchView {

    @NonNull
    String ip();

    @NonNull
    Observable<Object> findByIpSelections();

    void showError();

    void showLocation(Location location);

}
