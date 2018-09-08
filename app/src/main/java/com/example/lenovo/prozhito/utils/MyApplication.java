package com.example.lenovo.prozhito.utils;

import android.app.Application;
import android.graphics.Typeface;


public class MyApplication extends Application {
    public static MyApplication appInstance;
    public static Typeface normalTypeFace;
    @Override
    public void onCreate() {
        super.onCreate();
        appInstance=this;
        normalTypeFace=Typeface.createFromAsset(getAssets(),"ir_sans.ttf");

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
