package com.guliash.findbyip.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.guliash.findbyip.FindByIpApplication;
import com.guliash.findbyip.R;
import com.guliash.findbyip.search.di.SearchComponent;
import com.guliash.findbyip.search.ip.IpSearchFragment;
import com.guliash.findbyip.search.ip.di.IpSearchComponent;
import com.guliash.findbyip.search.ip.di.IpSearchComponentBuilderProvider;

public class SearchActivity extends AppCompatActivity implements IpSearchComponentBuilderProvider {

    private SearchComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        component = FindByIpApplication.get(this).applicationComponent()
                .searchComponentBuilder()
                .build();
        component.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, IpSearchFragment.newInstance(), IpSearchFragment.TAG)
                    .commit();
        }
    }

    @Override
    public IpSearchComponent.Builder ipSearchComponentBuilder() {
        return component.ipSearchComponentBuilder();
    }
}
