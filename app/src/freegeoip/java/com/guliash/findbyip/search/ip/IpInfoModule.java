package com.guliash.findbyip.search.ip;

import com.guliash.findbyip.search.di.SearchScope;
import com.guliash.findbyip.search.ip.freegeoip.di.Freegeoip;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class IpInfoModule {

    @Provides
    @SearchScope
    public IpInfoService provideIpInfoService(@Freegeoip IpInfoService ipInfoService) {
        return ipInfoService;
    }

}
