package com.binny.openapi.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.binny.openapi.MainActivity;
import com.binny.openapi.R;
import com.binny.openapi.custom.KeyValueLayout;
import com.binny.openapi.presenter.IRegisterPresenter;
import com.binny.openapi.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {


    private IRegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegisterPresenter = new RegisterPresenter(this);
    }

    @Override
    public void updateView(String msg, String code) {
        Log.i("", "updateView" + msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        if ("用户已注册！".equals(msg)) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void register(View view) {
        KeyValueLayout keyValueLayoutName = findViewById(R.id.register_phone);
        KeyValueLayout keyValueLayoutPwd = findViewById(R.id.register_password);
        mRegisterPresenter.getData(keyValueLayoutName.getValue(), keyValueLayoutPwd.getValue());
    }

    @Override
    public void onLoading() {

    }
}
