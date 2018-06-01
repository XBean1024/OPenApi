package com.binny.openapi.mvp.ui.activity;

import android.os.Handler;

import com.binny.openapi.R;
import com.binny.openapi.mvp.ui.base.BaseActivity;


public class SplashActivity extends BaseActivity {


    @Override
    protected void handleIntent() {

    }

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intoActivityWithAnimotion(LockScreenActivity.class);

            }
        },3000);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
