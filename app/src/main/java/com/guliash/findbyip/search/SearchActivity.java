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

    private SearchComponent component;

    private SearchState searchState = new SearchState();

    @Inject
    SearchCommunicationCenter searchCommunicationCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        searchState.restoreState(savedInstanceState);

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
        searchState.saveState(outState);
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
