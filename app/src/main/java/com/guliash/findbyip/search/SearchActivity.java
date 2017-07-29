package com.guliash.findbyip.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.guliash.findbyip.FindByIpApplication;
import com.guliash.findbyip.R;
import com.guliash.findbyip.core.utils.Objects;
import com.guliash.findbyip.search.di.SearchComponent;
import com.guliash.findbyip.search.ip.IpSearchFragment;
import com.guliash.findbyip.search.ip.di.IpSearchComponent;
import com.guliash.findbyip.search.ip.di.IpSearchComponentBuilderProvider;
import com.guliash.findbyip.search.location.LocationFragment;
import com.guliash.findbyip.search.location.di.LocationComponent;
import com.guliash.findbyip.search.location.di.LocationComponentBuilderProvider;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity
        implements IpSearchComponentBuilderProvider, LocationComponentBuilderProvider {

    private static final String SEARCH_STATE = "searchState";

    private SearchComponent component;

    @Inject
    SearchState searchState;

    @Inject
    SearchCommunicationCenter searchCommunicationCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SearchState searchState;
        if (savedInstanceState == null) {
            searchState = new SearchState();
        } else {
            searchState = savedInstanceState.getParcelable(SEARCH_STATE);
        }

        component = FindByIpApplication.get(this).applicationComponent()
                .searchComponentBuilder()
                .searchState(Objects.requireNonNull(searchState))
                .build();
        component.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, IpSearchFragment.newInstance(), IpSearchFragment.TAG)
                    .commit();
        }

        searchCommunicationCenter.showOnMapSelections().subscribe(ø -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, LocationFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SEARCH_STATE, searchState);
    }

    @Override
    public IpSearchComponent.Builder ipSearchComponentBuilder() {
        return component.ipSearchComponentBuilder();
    }

    @Override
    public LocationComponent.Builder locationComponentBuilder() {
        return component.locationComponentBuilder();
    }
}
