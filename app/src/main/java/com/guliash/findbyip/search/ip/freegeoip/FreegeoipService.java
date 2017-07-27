package com.guliash.findbyip.search.ip.freegeoip;

import android.support.annotation.NonNull;

import com.guliash.findbyip.search.di.SearchScope;
import com.guliash.findbyip.search.ip.IpInfo;
import com.guliash.findbyip.search.ip.IpInfoService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@SearchScope
public class FreegeoipService implements IpInfoService {

    private final FreegeoipApi freegeoipApi;

    @Inject
    public FreegeoipService(@NonNull FreegeoipApi freegeoipApi) {
        this.freegeoipApi = freegeoipApi;
    }

    @NonNull
    @Override
    public Flowable<IpInfo> findByIp(@NonNull String ip) {
        return freegeoipApi.findByIp(ip)
                .map(it -> IpInfo.create(ip, it.latitude(), it.longitude()));
    }
}
