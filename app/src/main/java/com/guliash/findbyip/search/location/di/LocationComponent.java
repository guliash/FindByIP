package com.guliash.findbyip.search.location.di;

import com.guliash.findbyip.search.location.LocationFragment;

import dagger.Subcomponent;

@Subcomponent
public interface LocationComponent {

    @Subcomponent.Builder
    interface Builder {
        LocationComponent build();
    }

    void inject(LocationFragment locationFragment);

}
