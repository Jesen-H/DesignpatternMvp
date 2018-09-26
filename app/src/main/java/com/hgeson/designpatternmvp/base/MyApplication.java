package com.hgeson.designpatternmvp.base;

import android.app.Application;

/**
 * @Describe：
 * @Date：2018/9/26
 * @Author：hgeson
 */

public abstract class MyApplication extends Application {
    private static MyApplication INSTANCE = null;

    public static MyApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initMvp();
    }

    protected abstract void initMvp();

}
