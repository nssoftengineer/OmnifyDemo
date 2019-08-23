package com.omnify.assignment.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("omnify.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}