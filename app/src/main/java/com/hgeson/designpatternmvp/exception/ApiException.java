package com.hgeson.designpatternmvp.exception;


/**
 * @Describe：Api错误
 * @Date：2018/9/26
 * @Author：hgeson
 */
public class ApiException extends BaseException {

    public static final int IMG_UPLOAD_ERROR = 20;

    private int code = -10;
    private String displayMessage = "";

    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
        this.code = code;
        this.displayMessage = displayMessage;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getDisplayMessage() {
        return displayMessage;
    }

    @Override
    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "code=" + code +
                ", displayMessage='" + displayMessage + '\'' +
                '}';
    }
}
