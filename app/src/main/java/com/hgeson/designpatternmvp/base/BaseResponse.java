package com.hgeson.designpatternmvp.base;

/**
 * @Describe：
 * @Date：2018/9/26
 * @Author：hgeson
 */

public class BaseResponse<T> {
    public static final int SUCCESS_CODE = 1;

    private int status;
    private String msg = "";
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return getStatus() == SUCCESS_CODE;
    }

    public BaseResponse() {
    }

    public BaseResponse(int status,String message, T response) {
        this.status = status;
        this.msg = message;
        this.data = response;
    }


    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static class Status {
        public static final int SUCCESS = 1;
        public static final int ERROR = 2;
        public static final int EMPTY = 3;
    }
}
