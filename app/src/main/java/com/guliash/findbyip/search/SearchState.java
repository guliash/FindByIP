package com.guliash.findbyip.search;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.guliash.findbyip.search.ip.model.IpInfo;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

public class SearchState implements Parcelable {

    private IpInfo ipInfo;

    private PublishSubject<IpInfo> ipInfoSubject = PublishSubject.create();

    public SearchState() {
    }

    protected SearchState(@NonNull Parcel in) {
        ipInfo = in.readParcelable(IpInfo.class.getClassLoader());
    }

    public static final Creator<SearchState> CREATOR = new Creator<SearchState>() {
        @Override
        public SearchState createFromParcel(Parcel in) {
            return new SearchState(in);
        }

        @Override
        public SearchState[] newArray(int size) {
            return new SearchState[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(ipInfo, flags);
    }
}
