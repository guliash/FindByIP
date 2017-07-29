package com.guliash.findbyip.search.ip.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.guliash.findbyip.search.location.model.Location;

public class IpInfo implements Parcelable {

    private final String ip;

    private final Location location;

    private IpInfo(@NonNull String ip, @NonNull Location location) {
        this.ip = ip;
        this.location = location;
    }

    protected IpInfo(Parcel in) {
        ip = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<IpInfo> CREATOR = new Creator<IpInfo>() {
        @Override
        public IpInfo createFromParcel(Parcel in) {
            return new IpInfo(in);
        }

        @Override
        public IpInfo[] newArray(int size) {
            return new IpInfo[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(ip);
        parcel.writeParcelable(location, flags);
    }
}
