package com.guliash.findbyip.search.location;

import android.support.annotation.NonNull;

import com.guliash.findbyip.core.mvp.BasePresenter;
import com.guliash.findbyip.search.SearchState;

import javax.inject.Inject;

public class LocationPresenter extends BasePresenter<LocationView> {

    private final SearchState searchState;

    @Inject
    public LocationPresenter(@NonNull SearchState searchState) {
        this.searchState = searchState;
    }

    @Override
    public void bind(LocationView view) {
        super.bind(view);

        unsubscribeOnUnbind(
                searchState.ipInfo().subscribe(it -> view.showLocation(it.location()))
        );
    }
}
