package com.guliash.findbyip.search.di;

import com.guliash.findbyip.ApplicationComponent;
import com.guliash.findbyip.search.ip.IpInfoModule;
import com.guliash.findbyip.search.ip.freegeoip.di.FreegeoipModule;
import com.guliash.findbyip.search.ip.geoip.di.GeoIpModule;
import com.guliash.findbyip.search.view.IpSearchActivity;

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
public interface SearchComponent {

    void inject(IpSearchActivity ipSearchActivity);

}
