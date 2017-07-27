package com.guliash.findbyip.search.ip.di;

import com.guliash.findbyip.search.ip.IpSearchFragment;

import dagger.Subcomponent;

@Subcomponent
public interface IpSearchComponent {

    void inject(IpSearchFragment ipSearchFragment);

    @Subcomponent.Builder
    interface Builder {
        IpSearchComponent build();
    }

}
