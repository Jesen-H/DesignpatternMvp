package com.hgeson.designpatternmvp.model;

import com.hgeson.designpatternmvp.base.BaseApplication;
import com.hgeson.designpatternmvp.base.BaseResponse;
import com.hgeson.designpatternmvp.model.bean.Api;
import com.hgeson.designpatternmvp.model.bean.DataBean;
import com.hgeson.designpatternmvp.model.contract.DataContract;
import com.hgeson.designpatternmvp.rx.RxSchedulerUtils;

import io.reactivex.Observable;

/**
 * @Describe：
 * @Date：2018/9/26
 * @Author：hgeson
 */

public class DataModelImpl implements DataContract.model{
    private Api api;

    public DataModelImpl() {
        this.api = BaseApplication.getRetrofit().createReq(Api.class);
    }

    @Override
    public Observable<BaseResponse<DataBean>> getDatas(String userid, String datetime) {
        return api.getChestSmartData(userid,datetime).compose(RxSchedulerUtils.<BaseResponse<DataBean>>normalSchedulersTransformer());
    }
}
