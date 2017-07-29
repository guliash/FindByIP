package com.guliash.findbyip.search;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.guliash.findbyip.search.ip.model.IpInfo;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

public class SearchState {

    private static final String IP_INFO = "searchState#ipInfo";

    private IpInfo ipInfo;

    private PublishSubject<IpInfo> ipInfoSubject = PublishSubject.create();

    public SearchState() {
    }

    @AnyThread
    public synchronized void setIpInfo(@NonNull IpInfo ipInfo) {
        this.ipInfo = ipInfo;

        ipInfoSubject.onNext(ipInfo);
    }

    @UiThread
    @NonNull
    public Observable<IpInfo> ipInfo() {
        return ipInfoSubject.startWith(cached()).observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    private Observable<IpInfo> cached() {
        return ipInfo == null ? Observable.empty() : Observable.just(ipInfo);
    }

    public void saveState(Bundle bundle) {
        bundle.putParcelable(IP_INFO, ipInfo);
    }

    public void restoreState(Bundle bundle) {
        ipInfo = bundle.getParcelable(IP_INFO);
    }
}
