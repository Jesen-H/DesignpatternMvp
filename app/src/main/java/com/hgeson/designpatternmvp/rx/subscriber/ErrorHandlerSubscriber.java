package com.hgeson.designpatternmvp.rx.subscriber;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hgeson.designpatternmvp.base.BaseApplication;
import com.hgeson.designpatternmvp.exception.BaseException;
import com.hgeson.designpatternmvp.mvp.IBasicView;
import com.hgeson.designpatternmvp.rx.RxErrorHandler;
import com.orhanobut.logger.Logger;


import io.reactivex.disposables.Disposable;


/**
 * @Describe：封装Subscriber错误请求
 * @Date：2018/9/26
 * @Author：hgeson
 */
public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {

    private static final String TAG = "ErrorHandlerSubscriber";
    private RxErrorHandler mErrorHandler = null;

    private Context mContext;
    private IBasicView mIView;

    public ErrorHandlerSubscriber() {
        mContext = BaseApplication.getContext();
        mErrorHandler = new RxErrorHandler(mContext);
    }

    public ErrorHandlerSubscriber(IBasicView mIView) {
        this();
        this.mIView = mIView;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        Log.i("TAG","ErrorHandlerSubscriber onError：" + e.getMessage());
        BaseException baseException = mErrorHandler.handleError(e);
        if (baseException == null) {
            e.printStackTrace();
        } else {
            /*Toast错误提示*/
            mErrorHandler.showErrorMessage(baseException);

            if (baseException.getCode() == BaseException.ERROR_TOKEN) {
                toLogin();
            }
        }
        Logger.d("ErrorHandlerSubscriber", e.getMessage() + "BaseException：" + baseException.getDisplayMessage());
        if (mIView != null) {
            mIView.dismissLoading();
        }
    }

    @Override
    public void onNext(@NonNull T t) {
        if (mIView != null) {
            mIView.dismissLoading();
        }
    }

    @Override
    public void onComplete() {
        if (mIView != null) {
            mIView.dismissLoading();
        }
    }

    private void toLogin() {
//        Intent intent = new Intent(mContext, LoginActivity.class);
//        mContext.startActivity(intent);
    }
}
