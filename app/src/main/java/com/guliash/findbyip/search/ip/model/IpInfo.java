package com.guliash.findbyip.search.ip.model;

import android.support.annotation.NonNull;

import com.guliash.findbyip.search.location.model.Location;

public class IpInfo {

    private final String ip;

    private final Location location;

    private IpInfo(@NonNull String ip, @NonNull Location location) {
        this.ip = ip;
        this.location = location;
    }

    public static IpInfo create(@NonNull String ip, @NonNull Location location) {
        return new IpInfo(ip, location);
    }

    @NonNull
    public String ip() {
        return ip;
    }

    @NonNull
    public Location location() {
        return location;
    }

    @Override
    public String toString() {
        return String.format("{%s %s}", ip, location);
    }
}
