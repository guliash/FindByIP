package com.guliash.findbyip.search.ip.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.guliash.findbyip.search.location.model.Location;

@AutoValue
public abstract class IpInfo implements Parcelable {

    public abstract String ip();

    public abstract Location location();

    public static IpInfo create(@NonNull String ip, @NonNull Location location) {
        return new AutoValue_IpInfo(ip, location);
    }

}
