package com.hgeson.designpatternmvp.base;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @Describe：
 * @Date：2018/9/26
 * @Author：hgeson
 */

public abstract class BaseObserver<T> implements Observer<BaseResponse<T>>{
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull BaseResponse<T> response) {
        if (response.getStatus()!=1) {
            result(new BaseResponse<T>(BaseResponse.Status.ERROR,"服务器繁忙，请稍后再试",null));
            return ;
        }

        if (response.getData() instanceof String && TextUtils.isEmpty((String) response.getData())) {
            result(new BaseResponse<T>(BaseResponse.Status.EMPTY,response.getMsg(),null));
            return;
        }

        if (JSON.toJSONString(response.getData()).equals("{}")) {
            result(new BaseResponse<T>(BaseResponse.Status.EMPTY,response.getMsg(),null));
            return;
        }
        result(new BaseResponse<T>(BaseResponse.Status.SUCCESS,response.getMsg(),response.getData()));
    }

    @Override
    public void onError(Throwable e) {
        result(new BaseResponse<T>(BaseResponse.Status.ERROR,"网络异常，请稍后再试",null));
    }

    @Override
    public void onComplete() {

    }

    public abstract void result(BaseResponse<T> response);
}
