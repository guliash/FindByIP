package com.guliash.findbyip.search.ip.service.geoip;

import android.support.annotation.NonNull;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GeoIpApi {

    @GET("/api/{ip}")
    Flowable<ResponseBody> findByIp(@Path("ip") @NonNull String ip);

}
