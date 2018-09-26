package com.hgeson.designpatternmvp.model.bean;

import com.hgeson.designpatternmvp.base.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @Describe：
 * @Date：2018/9/26
 * @Author：hgeson
 */

public interface Api {

    @POST("healthy-core/controller/UserBreastController/initUsedCondition.html")
    Observable<BaseResponse<DataBean>> getChestSmartData(@Query("userid") String userid,
                                                         @Query("datetime") String datetime);
}
