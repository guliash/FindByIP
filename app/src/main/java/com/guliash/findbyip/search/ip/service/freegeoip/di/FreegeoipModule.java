package com.guliash.findbyip.search.ip.service.freegeoip.di;

import com.guliash.findbyip.search.ip.service.IpInfoService;
import com.guliash.findbyip.search.ip.service.freegeoip.FreegeoipApi;
import com.guliash.findbyip.search.ip.service.freegeoip.FreegeoipService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class FreegeoipModule {

    @Provides
    public static FreegeoipApi provideFreegeoipApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://freegeoip.net")
                .client(okHttpClient)
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
