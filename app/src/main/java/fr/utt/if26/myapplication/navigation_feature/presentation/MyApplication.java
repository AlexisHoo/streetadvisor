package fr.utt.if26.myapplication.navigation_feature.presentation;

import android.app.Application;

public class MyApplication extends Application {
    public AppContainer appContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer();
    }
}

