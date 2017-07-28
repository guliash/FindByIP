package com.guliash.findbyip.search.di;

import com.guliash.findbyip.search.SearchActivity;
import com.guliash.findbyip.search.ip.di.IpInfoModule;
import com.guliash.findbyip.search.ip.di.IpSearchComponentBuilderProvider;
import com.guliash.findbyip.search.ip.service.freegeoip.di.FreegeoipModule;
import com.guliash.findbyip.search.ip.service.geoip.di.GeoIpModule;

import dagger.Subcomponent;

@SearchScope
@Subcomponent(
        modules = {
                IpInfoModule.class,
                FreegeoipModule.class,
                GeoIpModule.class
        }
)
public interface SearchComponent extends IpSearchComponentBuilderProvider {

    @Subcomponent.Builder
    interface SearchComponentBuilder {
        SearchComponent build();
    }

    void inject(SearchActivity searchActivity);

}
