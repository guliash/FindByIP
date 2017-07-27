package com.guliash.findbyip.core.ip;

import com.guliash.findbyip.core.ip.freegeoip.di.Freegeoip;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class IpInfoModule {

    @Provides
    @Singleton
    public IpInfoService provideIpInfoService(@Freegeoip IpInfoService ipInfoService) {
        return ipInfoService;
    }

}
