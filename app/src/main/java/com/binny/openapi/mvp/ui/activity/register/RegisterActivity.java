package com.binny.openapi.mvp.ui.activity.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.custom.KeyValueLayout;
import com.binny.openapi.mvp.bean.RegisterBean;
import com.binny.openapi.mvp.callback.DataCallback;
import com.binny.openapi.mvp.presenter.register.IRegisterPresenter;
import com.binny.openapi.mvp.presenter.register.RegisterPresenter;
import com.binny.openapi.util.UtilsLog;

public class RegisterActivity extends AppCompatActivity implements DataCallback<RegisterBean> {


    private IRegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegisterPresenter = new RegisterPresenter(this);
    }


    public void register(View view) {
        KeyValueLayout keyValueLayoutName = findViewById(R.id.register_phone);
        KeyValueLayout keyValueLayoutPwd = findViewById(R.id.register_password);
        mRegisterPresenter.getData(keyValueLayoutName.getValue(), keyValueLayoutPwd.getValue(), "许彬彬", "", "", "other2", null);
    }

    @Override
    public void onLoading() {
        UtilsLog.i( "onLoading = 正在加载。。。。。");
    }

    @Override
    public void onLoadDone() {
        UtilsLog.i( "onLoadDone = 加载完成。。。。。");
    }

    @Override
    public void onError(String result) {
        UtilsLog.i("onError = 加载失败。。。。。" + result);
    }


    @Override
    public void onSuccess(RegisterBean registerBean) {
        UtilsLog.i("updateView = " + registerBean.getMsg());
    }
}
