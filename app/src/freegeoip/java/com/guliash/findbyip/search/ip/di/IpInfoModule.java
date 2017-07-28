package com.guliash.findbyip.search.ip.di;

import com.guliash.findbyip.search.di.SearchScope;
import com.guliash.findbyip.search.ip.service.IpInfoService;
import com.guliash.findbyip.search.ip.service.freegeoip.di.Freegeoip;

import dagger.Module;
import dagger.Provides;

@Module
public class IpInfoModule {

    @Provides
    @SearchScope
    public static IpInfoService provideIpInfoService(@Freegeoip IpInfoService ipInfoService) {
        return ipInfoService;
    }

}
