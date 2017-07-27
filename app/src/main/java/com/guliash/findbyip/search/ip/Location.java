package com.guliash.findbyip.search.ip;

public class Location {

    private static final float EPS = 1e-10f;

    private final float latitude;

    private final float longitude;

    private Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

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
}
