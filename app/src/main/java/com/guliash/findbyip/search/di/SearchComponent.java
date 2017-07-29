package com.guliash.findbyip.search.di;

import com.guliash.findbyip.search.SearchActivity;
import com.guliash.findbyip.search.SearchState;
import com.guliash.findbyip.search.ip.di.IpInfoModule;
import com.guliash.findbyip.search.ip.di.IpSearchComponentBuilderProvider;
import com.guliash.findbyip.search.ip.service.freegeoip.di.FreegeoipModule;
import com.guliash.findbyip.search.ip.service.geoip.di.GeoIpModule;
import com.guliash.findbyip.search.location.di.LocationComponentBuilderProvider;

import dagger.BindsInstance;
import dagger.Subcomponent;

@SearchScope
@Subcomponent(
        modules = {
                IpInfoModule.class,
                FreegeoipModule.class,
                GeoIpModule.class
        }
)
public interface SearchComponent
        extends IpSearchComponentBuilderProvider, LocationComponentBuilderProvider {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder searchState(SearchState searchState);

        SearchComponent build();
    }

    void inject(SearchActivity searchActivity);

}
