package com.binny.openapi.mvp.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.binny.openapi.R;

/**
 * 作者: binny
 * 时间: 5/24
 * 描述:
 */
public abstract class BaseActivity extends FragmentActivity {
    private BaseActivity mActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mActivity = this;
        handleIntent();
        initView();
    }

    protected abstract void handleIntent();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected void intoActivity(Class<?> c){
        startActivity(new Intent(this,c));
        finish();
    }
    public void intoActivityWithAnimotion(Class<?> c){
        startActivity(new Intent(this,c));
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        finish();
    }
  public void intoActivityWithAnimotion(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }


}
