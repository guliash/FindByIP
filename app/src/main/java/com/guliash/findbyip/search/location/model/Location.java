package com.guliash.findbyip.search.location.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Location implements Parcelable {

    private static final float EPS = 1e-10f;

    public abstract float latitude();

    public abstract float longitude();

    public static Location create(float latitude, float longitude) {
        return new AutoValue_Location(latitude, longitude);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            if (obj == this) {
                return true;
            }
            final Location location = (Location) obj;
            return Math.abs(latitude() - location.latitude()) < EPS &&
                    Math.abs(longitude() - location.longitude()) < EPS;
        } else {
            return false;
        }
    }
}
