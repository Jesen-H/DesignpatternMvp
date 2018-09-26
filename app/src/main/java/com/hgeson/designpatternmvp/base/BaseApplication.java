package com.hgeson.designpatternmvp.base;

import android.content.Context;

import com.hgeson.designpatternmvp.R;
import com.hgeson.designpatternmvp.retrofit.RetrofitHelper;

/**
 * @Describe：
 * @Date：2018/9/26
 * @Author：hgeson
 */

public class BaseApplication extends MyApplication {
    private final String TAG = this.getClass().getSimpleName();
    //设置全局Context
    private static Context mContext;

    /*设置全局Retrofit*/
    private static RetrofitHelper retrofit;

    @Override
    protected void initMvp() {
        mContext = getApplicationContext();
        initRetrofit();
    }

    //获取全局Context
    public static Context getContext() {
        return mContext;
    }

    private void initRetrofit(){
        retrofit = new RetrofitHelper.Builder(this)
                .isTokenRequest(false)
                .setHost(getString(R.string.request_url))
                .build();
    }

    public static RetrofitHelper getRetrofit() {
        return retrofit;
    }
}
