package com.hgeson.designpatternmvp.mvp;

import java.util.ArrayList;

/**
 * View层接口
 * Created by Rayman on 2017/1/10.
 */
public interface IBasicView<T> {

    /**
     * 用于显示数据加载框
     */
    void showLoading();

    /**
     * 加载框dismiss
     */
    void dismissLoading();

    /**
     * View展示数据方法
     *
     * @param data
     */
    void showData(ArrayList<T> data);

    /**
     * 加载失败显示信息
     *
     * @param errorMsg
     */
    void showMessage(String errorMsg);
}
