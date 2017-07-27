package com.guliash.findbyip;

import com.guliash.findbyip.core.ip.IpInfoModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {IpInfoModule.class})
public interface ApplicationComponent {
}
