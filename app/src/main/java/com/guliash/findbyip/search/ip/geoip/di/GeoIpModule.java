package com.guliash.findbyip.search.ip.geoip.di;

import com.guliash.findbyip.search.ip.IpInfoService;
import com.guliash.findbyip.search.ip.geoip.GeoIpApi;
import com.guliash.findbyip.search.ip.geoip.GeoIpService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class GeoIpModule {

    @Provides
    public static GeoIpApi provideGeoIpApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://geoip.nekudo.com/")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GeoIpApi.class);
    }

    @Provides
    @GeoIp
    public static IpInfoService geoIpService(GeoIpApi geoIpApi) {
        return new GeoIpService(geoIpApi);
    }

}
