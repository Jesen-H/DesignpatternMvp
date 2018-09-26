package com.hgeson.designpatternmvp.presenter;

import com.hgeson.designpatternmvp.base.BaseObserver;
import com.hgeson.designpatternmvp.base.BaseResponse;
import com.hgeson.designpatternmvp.model.DataModelImpl;
import com.hgeson.designpatternmvp.model.bean.DataBean;
import com.hgeson.designpatternmvp.model.contract.DataContract;


/**
 * @Describe：
 * @Date：2018/9/26
 * @Author：hgeson
 */

public class DataPresenterImpl implements DataContract.presenter {
    private DataContract.view view;
    private DataContract.model model;

    public DataPresenterImpl(DataContract.view view) {
        this.view = view;
        model = new DataModelImpl();
    }

    @Override
    public void getData(String userid, String datetime) {
        model.getDatas(userid,datetime).subscribe(new BaseObserver<DataBean>() {
            @Override
            public void result(BaseResponse<DataBean> response) {
                view.showData(response);
            }
        });
    }
}
