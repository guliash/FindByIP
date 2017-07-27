package com.guliash.findbyip.search.ip;

import android.support.annotation.NonNull;

public class IpInfo {

    @NonNull
    private final String ip;

    private final float latitude;

    private final float longitude;

    private IpInfo(@NonNull String ip, float latitude, float longitude) {
        this.ip = ip;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static IpInfo create(@NonNull String ip, float latitude, float longitude) {
        return new IpInfo(ip, latitude, longitude);
    }

    @NonNull
    public String ip() {
        return ip;
    }

    public float latitude() {
        return latitude;
    }

    public float longitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("{%s %s %s}", ip, latitude, longitude);
    }
}
