package com.hgeson.designpatternmvp.model.contract;

import com.hgeson.designpatternmvp.base.BaseResponse;
import com.hgeson.designpatternmvp.model.bean.DataBean;

import io.reactivex.Observable;

/**
 * @Describe：
 * @Date：2018/9/26
 * @Author：hgeson
 */

public interface DataContract {
    interface view{
        void showData(BaseResponse<DataBean> bean);
    }

    interface presenter{
        void getData(String userid, String datetime);
    }

    interface model{
        Observable<BaseResponse<DataBean>> getDatas(String userid, String datetime);
    }
}
