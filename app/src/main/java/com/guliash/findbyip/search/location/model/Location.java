package com.guliash.findbyip.search.location.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {

    private static final float EPS = 1e-10f;

    private final float latitude;

    private final float longitude;

    private Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Location(Parcel in) {
        latitude = in.readFloat();
        longitude = in.readFloat();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public static Location create(float latitude, float longitude) {
        return new Location(latitude, longitude);
    }

    public float latitude() {
        return latitude;
    }

    public float longitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            if (obj == this) {
                return true;
            }
            final Location location = (Location) obj;
            return Math.abs(latitude - location.latitude) < EPS &&
                    Math.abs(longitude - location.longitude) < EPS;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("{%s %s}", latitude, longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeFloat(latitude);
        parcel.writeFloat(longitude);
    }
}
