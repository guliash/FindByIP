package com.guliash.findbyip.search.ip.freegeoip;

import com.squareup.moshi.Json;

public class FreegeoipResponse {

    @Json(name = "latitude")
    private float latitude;

    @Json(name = "longitude")
    private float longitude;

    public float latitude() {
        return latitude;
    }

    public float longitude() {
        return longitude;
    }

}
