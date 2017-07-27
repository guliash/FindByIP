package com.guliash.findbyip.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.guliash.findbyip.FindByIpApplication;
import com.guliash.findbyip.R;
import com.guliash.findbyip.search.di.DaggerSearchComponent;
import com.guliash.findbyip.search.di.SearchComponent;
import com.guliash.findbyip.search.ip.IpSearchFragment;
import com.guliash.findbyip.search.ip.di.IpSearchComponent;
import com.guliash.findbyip.search.ip.di.IpSearchComponentBuilderProvider;

public class SearchActivity extends AppCompatActivity implements IpSearchComponentBuilderProvider {

    private SearchComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        component = DaggerSearchComponent.builder()
                .applicationComponent(FindByIpApplication.get(this).applicationComponent())
                .build();
        component.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, IpSearchFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public IpSearchComponent.Builder ipSearchComponentBuilder() {
        return component.ipSearchComponentBuilder();
    }
}
