package com.hgeson.designpatternmvp.retrofit;

import android.content.Context;

import com.hgeson.designpatternmvp.exception.BaseException;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Describe：网络状态拦截器
 * @Date：2018/9/26
 * @Author：hgeson
 */

public class NetWorkInterceptor implements Interceptor {

    private Context context;

    public NetWorkInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (NetWorkUtil.isNetworkAvailable(context)) {
            Logger.i("NetWork is Available");
            //有网络的情况下不拦截，同时也会将http转化为https。
            Request request = chain.request();
            return chain.proceed(request);
        } else {
            Logger.e("NetWork Error");
            //直接返回SOCKET_ERROR错误response，无网络错误
            Response response = new Response.Builder()
                    .code(BaseException.SOCKET_ERROR)
                    .build();
            return response;
        }
    }
}
