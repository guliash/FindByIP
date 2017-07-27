package com.guliash.findbyip;

import com.guliash.findbyip.core.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = NetworkModule.class)
public interface ApplicationComponent {

    OkHttpClient okHttpClient();

}
