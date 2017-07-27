package com.guliash.findbyip.search.ip;

import android.support.annotation.NonNull;

import io.reactivex.Flowable;

public interface IpInfoService {

    @NonNull
    Flowable<IpInfo> findByIp(@NonNull String ip);

}
