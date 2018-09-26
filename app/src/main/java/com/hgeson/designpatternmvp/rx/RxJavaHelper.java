package com.hgeson.designpatternmvp.rx;

import android.content.Context;
import android.util.Log;

import com.hgeson.designpatternmvp.base.BaseResponse;
import com.hgeson.designpatternmvp.exception.ApiException;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * RxJava+Retrofit请求帮助类
 * Created by Rayman on 2017/5/9.
 */

public class RxJavaHelper<T> {

    private static final String TAG = "RxJavaHelper";
    private Context context;

    private RxJavaHelper(Context context) {
        Log.i(TAG, "RxJavaHelper init");
        this.context = context.getApplicationContext();
    }

    public static <T> ObservableTransformer<BaseResponse<T>, T> flatResponse() {
        Log.i(TAG, "flatResponse");
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                Log.i(TAG, "flatMap");
                return (ObservableSource<T>) upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(new Function<BaseResponse<T>, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(final BaseResponse<T> tBaseResponse) throws Exception {
                                if (tBaseResponse.isSuccess()) {
                                    Logger.i(tBaseResponse.toString());
                                    return createData(tBaseResponse.getData());
                                } else {
                                    Logger.e(TAG, "api error code：" + tBaseResponse.getStatus() + "\nerror msg：" + tBaseResponse.getMsg());
                                    int code = tBaseResponse.getStatus();
                                    String msg = tBaseResponse.getMsg();
                                    return Observable.error(new ApiException(code, msg));
                                }
                            }
                        });
            }
        };
    }

    private static <T> ObservableSource<?> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                e.onNext(data);
                e.onComplete();
            }
        });
    }
}
