package com.guliash.findbyip.search.view;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

public interface IpSearchView {

    @NonNull
    String ip();

    @NonNull
    Observable<Object> findByIpSelections();

}
