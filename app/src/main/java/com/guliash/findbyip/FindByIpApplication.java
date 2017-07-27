package com.guliash.findbyip;

import android.app.Application;
import android.content.Context;

public class FindByIpApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.applicationComponent = applicationComponentBuilder().build();
    }

    public static FindByIpApplication get(Context context) {
        return (FindByIpApplication) context.getApplicationContext();
    }

    public ApplicationComponent applicationComponent() {
        return applicationComponent;
    }

    protected DaggerApplicationComponent.Builder applicationComponentBuilder() {
        return DaggerApplicationComponent.builder();
    }
}
