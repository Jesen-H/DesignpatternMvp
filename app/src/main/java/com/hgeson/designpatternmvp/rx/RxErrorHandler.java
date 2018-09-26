package com.hgeson.designpatternmvp.rx;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.hgeson.designpatternmvp.exception.ApiException;
import com.hgeson.designpatternmvp.exception.BaseException;
import com.hgeson.designpatternmvp.exception.ErrorMessageFactory;
import com.orhanobut.logger.Logger;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;


public class RxErrorHandler {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;

    public RxErrorHandler(Context context) {
        this.mContext = context;
    }

    public BaseException handleError(Throwable e) {
        BaseException exception = new BaseException();
        if (e instanceof JsonParseException) {
            exception.setCode(BaseException.JSON_ERROR);
            exception.setDisplayMessage(ErrorMessageFactory.create(mContext, exception.getCode()));
        } else if (e instanceof HttpException) {
            exception.setCode(((HttpException) e).code());
            exception.setDisplayMessage(ErrorMessageFactory.create(mContext, exception.getCode()));
        } else if (e instanceof SocketTimeoutException) {
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
            exception.setDisplayMessage(ErrorMessageFactory.create(mContext, exception.getCode()));
        } else if (e instanceof SocketException) {

        } else if (e instanceof UnknownHostException) {
            exception.setCode(BaseException.UNKNOWN_HOST_ERROR);
            exception.setDisplayMessage(ErrorMessageFactory.create(mContext, exception.getCode()));
        } else if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            exception.setCode(apiException.getCode());
            exception.setDisplayMessage(apiException.getDisplayMessage());
            Logger.e(TAG, "捕获到Api错误：" + apiException.getCode() + "\tmessage：" + apiException.getMessage());
        } else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
            exception.setDisplayMessage(ErrorMessageFactory.create(mContext, exception.getCode()));
        }
        return exception;
    }

    public void showErrorMessage(BaseException e) {
        Toast.makeText(mContext, e.getDisplayMessage(), Toast.LENGTH_SHORT).show();
    }
}
