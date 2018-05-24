package com.binny.openapi.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.binny.openapi.mvp.callback.OnPermissionCallback;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * 作者: binny
 * 时间: 5/24
 * 描述:
 */
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        handleIntent();
        initView();
    }

    protected abstract void handleIntent();

    protected abstract void initView();

    abstract int getLayoutId();


}
