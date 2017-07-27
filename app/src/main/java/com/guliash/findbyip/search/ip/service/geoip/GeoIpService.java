package com.guliash.findbyip.search.ip.service.geoip;

import android.support.annotation.NonNull;

import com.guliash.findbyip.search.ip.model.IpInfo;
import com.guliash.findbyip.search.ip.service.IpInfoService;
import com.guliash.findbyip.search.location.model.Location;

import org.json.JSONObject;

import io.reactivex.Flowable;
import retrofit2.HttpException;
import retrofit2.Response;

public class GeoIpService implements IpInfoService {

    private final GeoIpApi geoIpApi;

    public GeoIpService(GeoIpApi geoIpApi) {
        this.geoIpApi = geoIpApi;
    }

    @NonNull
    @Override
    public Flowable<IpInfo> findByIp(@NonNull String ip) {
        return geoIpApi.findByIp(ip).flatMap(it -> {
            final JSONObject jsonObject = new JSONObject(it.string());
            if (jsonObject.optString("type").equals("error")) {
                return Flowable.error(new HttpException(Response.error(400, it)));
            } else {
                final JSONObject location = jsonObject.optJSONObject("location");
                return Flowable.just(
                        IpInfo.create(
                                ip,
                                Location.create(
                                        (float) location.getDouble("latitude"),
                                        (float) location.getDouble("longitude")
                                )
                        )
                );
            }
        });
    }
}
