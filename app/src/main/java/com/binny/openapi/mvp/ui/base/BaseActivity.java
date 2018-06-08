package com.binny.openapi.mvp.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.binny.openapi.R;
import com.binny.openapi.immersionbar.ImmersionBar;

/**
 * 作者: binny
 * 时间: 5/24
 * 描述:
 */
public abstract class BaseActivity extends FragmentActivity {
    private BaseActivity mActivity;
    private ImmersionBar mImmersionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();   //所有子类都将继承这些相同的属性
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }

}
