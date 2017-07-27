package com.guliash.findbyip.core.ip.geoip.di;

import com.guliash.findbyip.core.ip.geoip.GeoIpApi;
import com.guliash.findbyip.core.ip.geoip.GeoIpService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class GeoIpModule {

    @Provides
    @Singleton
    public static GeoIpApi provideGeoIpApi() {
        return new Retrofit.Builder()
                .baseUrl("http://geoip.nekudo.com/api")
                .build()
                .create(GeoIpApi.class);
    }

    @Provides
    @Singleton
    @GeoIp
    public static GeoIpService geoIpService(GeoIpApi geoIpApi) {
        return new GeoIpService(geoIpApi);
    }

}
