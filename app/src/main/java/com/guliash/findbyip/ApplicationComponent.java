package com.guliash.findbyip;

import com.guliash.findbyip.core.network.NetworkModule;
import com.guliash.findbyip.core.rx.ComputationScheduler;
import com.guliash.findbyip.core.rx.IoScheduler;
import com.guliash.findbyip.core.rx.MainScheduler;
import com.guliash.findbyip.core.rx.RxModule;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {NetworkModule.class, RxModule.class})
public interface ApplicationComponent {

    OkHttpClient okHttpClient();

    @MainScheduler
    Scheduler mainScheduler();

    @IoScheduler
    Scheduler ioScheduler();

    @ComputationScheduler
    Scheduler computationScheduler();

}
