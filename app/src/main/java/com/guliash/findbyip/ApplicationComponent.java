package com.guliash.findbyip;

import com.guliash.findbyip.core.network.NetworkModule;
import com.guliash.findbyip.core.rx.RxModule;
import com.guliash.findbyip.search.di.SearchComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, RxModule.class})
public interface ApplicationComponent {

    SearchComponent.Builder searchComponentBuilder();

}
