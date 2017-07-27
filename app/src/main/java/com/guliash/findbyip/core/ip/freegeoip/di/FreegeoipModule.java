package com.guliash.findbyip.core.ip.freegeoip.di;

import com.guliash.findbyip.core.ip.IpInfoService;
import com.guliash.findbyip.core.ip.freegeoip.FreegeoipApi;
import com.guliash.findbyip.core.ip.freegeoip.FreegeoipService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FreegeoipModule {

    @Provides
    @Singleton
    public static FreegeoipApi provideFreegeoipApi() {
        return new Retrofit.Builder()
                .baseUrl("http://freegeoip.net")
                .build()
                .create(FreegeoipApi.class);
    }

    @Provides
    @Singleton
    @Freegeoip
    public static IpInfoService provideFreegeoipApiInfoService(FreegeoipApi freegeoipApi) {
        return new FreegeoipService(freegeoipApi);
    }

}
