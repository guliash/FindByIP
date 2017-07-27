package com.guliash.findbyip.search.ip.service.freegeoip;

import android.support.annotation.NonNull;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FreegeoipApi {

    @GET("/json/{ip}")
    Flowable<FreegeoipResponse> findByIp(@Path("ip") @NonNull String ip);

}
