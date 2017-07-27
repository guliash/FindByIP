package com.guliash.findbyip.search.ip.geoip.di;

import com.guliash.findbyip.search.di.SearchScope;
import com.guliash.findbyip.search.ip.geoip.GeoIpApi;
import com.guliash.findbyip.search.ip.geoip.GeoIpService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class GeoIpModule {

    @Provides
    public static GeoIpApi provideGeoIpApi() {
        return new Retrofit.Builder()
                .baseUrl("http://geoip.nekudo.com/api")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GeoIpApi.class);
    }

    @Provides
    @GeoIp
    public static GeoIpService geoIpService(GeoIpApi geoIpApi) {
        return new GeoIpService(geoIpApi);
    }

}
