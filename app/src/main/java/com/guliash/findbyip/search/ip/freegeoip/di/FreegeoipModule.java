package com.guliash.findbyip.search.ip.freegeoip.di;

import com.guliash.findbyip.search.di.SearchScope;
import com.guliash.findbyip.search.ip.IpInfoService;
import com.guliash.findbyip.search.ip.freegeoip.FreegeoipApi;
import com.guliash.findbyip.search.ip.freegeoip.FreegeoipService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class FreegeoipModule {

    @Provides
    public static FreegeoipApi provideFreegeoipApi() {
        return new Retrofit.Builder()
                .baseUrl("http://freegeoip.net")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(FreegeoipApi.class);
    }

    @Provides
    @Freegeoip
    public static IpInfoService provideFreegeoipApiInfoService(FreegeoipApi freegeoipApi) {
        return new FreegeoipService(freegeoipApi);
    }

}
