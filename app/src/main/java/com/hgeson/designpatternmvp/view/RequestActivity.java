package com.hgeson.designpatternmvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.hgeson.designpatternmvp.R;
import com.hgeson.designpatternmvp.base.BaseResponse;
import com.hgeson.designpatternmvp.model.bean.DataBean;
import com.hgeson.designpatternmvp.model.contract.DataContract;
import com.hgeson.designpatternmvp.presenter.DataPresenterImpl;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestActivity extends Activity implements DataContract.view{
    private DataContract.presenter presenter;
    private String userid = "a9da8152e08743d49693197d18af8158";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String datetime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        presenter = new DataPresenterImpl(this);
        presenter.getData(userid,datetime);
    }

    @Override
    public void showData(BaseResponse<DataBean> bean) {
        switch (bean.getStatus()){
            case BaseResponse.Status.SUCCESS:
                Logger.i("username = " + bean.getData().getUserName());
                break;
            case BaseResponse.Status.EMPTY:
                Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                break;
            case BaseResponse.Status.ERROR:
                Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
