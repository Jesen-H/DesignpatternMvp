package com.hgeson.designpatternmvp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hgeson.designpatternmvp.R;
import com.hgeson.designpatternmvp.base.BaseResponse;
import com.hgeson.designpatternmvp.model.bean.DataBean;
import com.hgeson.designpatternmvp.model.bean.UserBean;
import com.hgeson.designpatternmvp.model.contract.DataContract;
import com.hgeson.designpatternmvp.presenter.DataPresenterImpl;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestActivity extends Activity implements DataContract.view{
    private DataContract.presenter presenter;
    private String userid = "a9da8152e08743d49693197d18af8158";

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new DataPresenterImpl(this);
        tv = (TextView) findViewById(R.id.tv);

        MyCountDownTimer timer = new MyCountDownTimer(6000,1000);
        timer.start();
    }

    class MyCountDownTimer extends CountDownTimer{

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (millisUntilFinished / 1000 == 1){
                tv.setText("正在请求中...");
            }else {
                tv.setText(millisUntilFinished / 1000 + "");
            }
        }

        @Override
        public void onFinish() {
            presenter.getData(userid,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }
    }

    @Override
    public void showData(BaseResponse<DataBean> bean) {
        switch (bean.getStatus()){
            case BaseResponse.Status.SUCCESS:
                DataBean data = bean.getData();
                UserBean userBean = new UserBean(data.getUserName(),data.getUserIcon(),data.getBreastcup(),data.getUserSex());
                Toast.makeText(this, "请求成功！", Toast.LENGTH_SHORT).show();
                tv.setText(JSON.toJSONString(userBean));
                Logger.i(JSON.toJSONString(userBean));
                break;
            case BaseResponse.Status.EMPTY:
                tv.setText("请求失败！");
                Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                break;
            case BaseResponse.Status.ERROR:
                tv.setText("请求失败！");
                Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
