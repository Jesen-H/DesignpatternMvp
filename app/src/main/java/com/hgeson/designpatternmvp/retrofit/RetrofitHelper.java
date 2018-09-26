package com.hgeson.designpatternmvp.retrofit;

import android.content.Context;


import com.hgeson.designpatternmvp.R;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 封装Retrofit请求
 * Created by Rayman on 2017/10/14.
 */

public class RetrofitHelper {

    private static RetrofitHelper mInstance;
    private Context context;
    private Retrofit mRetrofit;
    private boolean isTokenRequest = false;
    private String HOST = "https://tool.meibbc.com/";

    /**
     * RetrofitHelper，默认都是带Token请求
     *
     * @param context
     */
    private RetrofitHelper(Context context) {
        this.context = context;
        initRetrofit();
    }

    private RetrofitHelper(RetrofitHelper helper) {
        this.context = helper.context;
        this.isTokenRequest = helper.isTokenRequest;
        this.HOST = helper.HOST;
        initRetrofit();
    }

    public static RetrofitHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitHelper(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 配置Retrofit信息
     */
    private void initRetrofit() {
        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(context.getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new NetWorkInterceptor(context))
                .addInterceptor(new LoggingInterceptor())
                .cache(cache)
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private SSLSocketFactory initSSL(){
        SSLSocketFactory sslSocketFactory=null;
        final TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
//        if(true){

        try {
            // Install the all-trusting trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//                sslContext.init(null, new TrustManager[]{}, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
             sslSocketFactory = sslContext.getSocketFactory();

        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sslSocketFactory;
    }

    public <T> T createReq(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }

    public static class Builder {

        private RetrofitHelper helper;
        private Context context;

        public Builder(Context context) {
            this.context = context;
            helper = new RetrofitHelper(context);
        }

        public Builder isTokenRequest(boolean isTokenRequest) {
            helper.isTokenRequest = isTokenRequest;
            return this;
        }

        public Builder setHost(String host) {
            helper.HOST = host;
            return this;
        }

        public RetrofitHelper build() {
            return new RetrofitHelper(helper);
        }
    }
}
