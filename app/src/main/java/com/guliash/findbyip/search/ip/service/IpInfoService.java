package com.guliash.findbyip.search.ip.service;

import android.support.annotation.NonNull;

import com.guliash.findbyip.search.ip.model.IpInfo;

import io.reactivex.Flowable;

public interface IpInfoService {

    @NonNull
    Flowable<IpInfo> findByIp(@NonNull String ip);

}
