package com.guliash.findbyip.core.ip.geoip;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

public interface GeoIpApi {

    Flowable<ResponseBody> findByIp();

}
