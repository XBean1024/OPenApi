package com.binny.openapi.mvp.view.activity;

import android.os.Handler;

import com.binny.openapi.R;
import com.binny.openapi.mvp.view.base.AbsBaseActivity;


public class SplashActivity extends AbsBaseActivity {


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
