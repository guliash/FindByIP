package com.guliash.findbyip.search.ip;

import com.guliash.findbyip.search.di.SearchScope;
import com.guliash.findbyip.search.ip.geoip.di.GeoIp;

import dagger.Module;
import dagger.Provides;

@Module
public class IpInfoModule {

    @Provides
    @SearchScope
    public static IpInfoService provideIpInfoService(@GeoIp IpInfoService ipInfoService) {
        return ipInfoService;
    }

}
