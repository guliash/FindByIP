package com.guliash.findbyip.search.ip.service.freegeoip;

import android.support.annotation.NonNull;

import com.guliash.findbyip.search.ip.model.IpInfo;
import com.guliash.findbyip.search.ip.service.IpInfoService;
import com.guliash.findbyip.search.location.model.Location;

import io.reactivex.Flowable;

public class FreegeoipService implements IpInfoService {

    private final FreegeoipApi freegeoipApi;

    public FreegeoipService(@NonNull FreegeoipApi freegeoipApi) {
        this.freegeoipApi = freegeoipApi;
    }

    @NonNull
    @Override
    public Flowable<IpInfo> findByIp(@NonNull String ip) {
        return freegeoipApi.findByIp(ip)
                .map(it -> IpInfo.create(ip, Location.create(it.latitude(), it.longitude())));
    }
}
