package com.guliash.findbyip.search.di;

import com.guliash.findbyip.ApplicationComponent;
import com.guliash.findbyip.search.SearchActivity;
import com.guliash.findbyip.search.ip.IpInfoModule;
import com.guliash.findbyip.search.ip.di.IpSearchComponentBuilderProvider;
import com.guliash.findbyip.search.ip.service.freegeoip.di.FreegeoipModule;
import com.guliash.findbyip.search.ip.service.geoip.di.GeoIpModule;

import dagger.Component;

@SearchScope
@Component(
        modules = {
                IpInfoModule.class,
                FreegeoipModule.class,
                GeoIpModule.class
        },
        dependencies = {
                ApplicationComponent.class
        }
)
public interface SearchComponent extends IpSearchComponentBuilderProvider {

    void inject(SearchActivity searchActivity);

}
