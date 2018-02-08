package com.androidyuan.frame.base.activity;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by mac on 2018/2/8.
 */

public class BaseApplication extends Application {

    private static Context instance;

    public static final boolean isDebug = false;


    public static Context gainContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Fresco.initialize(this);
    }
}
