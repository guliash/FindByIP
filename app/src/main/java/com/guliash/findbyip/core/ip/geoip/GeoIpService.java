package com.guliash.findbyip.core.ip.geoip;

import android.support.annotation.NonNull;

import com.guliash.findbyip.core.ip.IpInfo;
import com.guliash.findbyip.core.ip.IpInfoService;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import retrofit2.HttpException;
import retrofit2.Response;

@Singleton
public class GeoIpService implements IpInfoService {

    private final GeoIpApi geoIpApi;

    @Inject
    public GeoIpService(GeoIpApi geoIpApi) {
        this.geoIpApi = geoIpApi;
    }

    @NonNull
    @Override
    public Flowable<IpInfo> findByIp(@NonNull String ip) {
        return geoIpApi.findByIp().flatMap(it -> {
            final JSONObject jsonObject = new JSONObject(it.string());
            if (jsonObject.optString("type").equals("error")) {
                return Flowable.error(new HttpException(Response.error(400, it)));
            } else {
                final JSONObject location = jsonObject.optJSONObject("location");
                return Flowable.just(
                        IpInfo.create(
                                ip,
                                (float) location.getDouble("latitude"),
                                (float) location.getDouble("longitude")
                        )
                );
            }
        });
    }
}
