package com.example.postscommentsapp;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class PostsApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
