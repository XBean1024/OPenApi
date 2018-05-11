package com.binny.openapi.mvp.ui.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.binny.openapi.R;
import com.binny.openapi.custom.KeyValueLayout;
import com.binny.openapi.mvp.bean.LoginBean;
import com.binny.openapi.mvp.presenter.login.LoginPresenter;
import com.binny.openapi.mvp.ui.activity.MainActivity;
import com.binny.openapi.mvp.ui.activity.ProtocolItemActivity;
import com.binny.openapi.mvp.ui.activity.register.RegisterActivity;
import com.binny.openapi.widget.SplashVideoView;

public class LoginActivity extends AppCompatActivity implements ILoginView,MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

//    private com.binny.openapi.widget.SplashVideoView mSpalshVideo;
    private com.binny.openapi.custom.KeyValueLayout mLoginPhone;
    private com.binny.openapi.custom.KeyValueLayout mLoginPassword;
    private android.widget.TextView mItemTv;
    private Activity mActivity = this;

    private LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginPresenter = new LoginPresenter(this);
        this.mItemTv = (TextView) findViewById(R.id.item);
        this.mLoginPassword = (KeyValueLayout) findViewById(R.id.login_password);
        this.mLoginPhone = (KeyValueLayout) findViewById(R.id.login_phone);
//        this.mSpalshVideo = (SplashVideoView) findViewById(R.id.login_video);

//        initVideoView();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //开始播放
//        mSpalshVideo.start();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //开始播放
//        mSpalshVideo.start();
    }

//    private void initVideoView() {
//        //设置屏幕常亮
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        mSpalshVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.mqr);
//        //设置相关的监听
//        mSpalshVideo.setOnPreparedListener(this);
//        mSpalshVideo.setOnCompletionListener(this);
//
//        String text = "登录即代表阅读并同意服务条款";
//        int len = text.length();
//        SpannableString spannableString = new SpannableString(text);
//        mItemTv.setMovementMethod(LinkMovementMethod.getInstance());//必须设置否则无效
//        spannableString.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(final View widget) {
//                startActivity(new Intent(mActivity, ProtocolItemActivity.class));
//            }
//
//            @Override
//            public void updateDrawState(final TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setColor(Color.parseColor("#0061ff"));
//                ds.setUnderlineText(false);    //去除超链接的下划线
//            }
//        }, len - 4, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        //改变选中文本的高亮颜色
////        mItemTv.setHighlightColor(Color.BLUE);
//        mItemTv.setText(spannableString);
//    }

    @Override
    public void onError(String result) {

    }

    @Override
    public void onLoading() {
        Log.i("[]", "onLoading" );
    }

    @Override
    public void onLoadDone() {
        Log.i("[]", "onLoadDone" );
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        int code = loginBean.getCode();
        if (code == 200) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else {
            Toast.makeText(mActivity, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View view) {
        mLoginPresenter.getData(mLoginPhone.getValue(),mLoginPassword.getValue());
    }

    public void toRegister(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}
