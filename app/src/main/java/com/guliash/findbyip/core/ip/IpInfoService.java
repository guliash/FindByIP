package com.guliash.findbyip.core.ip;

import android.support.annotation.NonNull;

import io.reactivex.Flowable;

public interface IpInfoService {

    @NonNull
    Flowable<IpInfo> findByIp(@NonNull String ip);

}
