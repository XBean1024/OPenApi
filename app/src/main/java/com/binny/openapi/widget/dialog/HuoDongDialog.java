package com.binny.openapi.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binny.openapi.R;
import com.bumptech.glide.Glide;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/25 10:19
 * @Description: 可配置的活动弹窗
 * <p>
 * -活动弹窗尺寸与位置规范：
 * 1、弹窗尺寸：固定宽度比例，比例为4：1，弹窗宽度占比为4 ，弹窗到屏幕边界的占比为1，高度根据宽度比例计算同比缩放；
 * 2、弹窗位置：居中手机屏幕中心；
 */
public class HuoDongDialog extends Dialog {
    private Activity mContext;

    private double mScaleWidth = 0.8f;
    private double mScaleHeight = 0.8f;
    private double mScaleWH;//宽高比

    private ImageView mImageView;
    private String mImgUrl;//背景
    private String mMessage;//弹窗显示信息
    private TextView mMessageTv;
    private TextView mHuoDongBtn;

    private int mMarginPoxBottom = -1;

    private String mBtnColorString;//按钮背景色

    public HuoDongDialog(@NonNull Context context) {
        this(context, R.style.app_article_deatil_dialog_style);
        mContext = (Activity) context;
    }

    public HuoDongDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_huo_dong_layout);
        mImageView = findViewById(R.id.huodong_img);
        mMessageTv = findViewById(R.id.huodong_message_tv);
        mHuoDongBtn = findViewById(R.id.huodong_btn);
        configDialog();

        if (TextUtils.isEmpty(mMessage)) {
            mMessageTv.setVisibility(View.GONE);
        } else {
            mMessageTv.setText(mMessage);
            if (mMarginPoxBottom != -1) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mMessageTv.getLayoutParams();
                lp.setMargins(0, 0, 0, mMarginPoxBottom);
                mMessageTv.setLayoutParams(lp);
//                mHuoDongBtn.setVisibility(View.GONE);
            }

        }
        if (!TextUtils.isEmpty(mBtnColorString)) {
            GradientDrawable drawable = (GradientDrawable) mHuoDongBtn.getBackground();
            drawable.setColor(Color.parseColor("#" + mBtnColorString));
            mHuoDongBtn.setBackground(drawable);
        }
        Glide.with(mContext)
                .load(mImgUrl)
                .asBitmap().into(mImageView);
        mHuoDongBtn.setOnClickListener(v -> HuoDongDialog.this.dismiss());
    }

    private void configDialog() {
        setCancelable(false);//不可取消
        WindowManager windowManager = mContext.getWindowManager();
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        Window window = this.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
        }
        WindowManager.LayoutParams params;
        if (window != null) {
            params = window.getAttributes();
            if (params != null) {
                params.width = (int) (screenWidth * mScaleWidth);
                params.height = (int) (screenWidth / mScaleWH);
                window.setAttributes(params);
            }
        }

    }

    /**
     * 设置弹窗的宽度缩放比
     *
     * @param scaleWidth 宽度缩放比 浮点值
     */
    public HuoDongDialog setScaleWidth(double scaleWidth) {
        mScaleWidth = scaleWidth;
        return this;
    }

    /**
     * 设置弹窗的宽高比
     *
     * @param scaleWidth 宽度缩放比 浮点值
     */
    public HuoDongDialog setScaleWH(double scaleWidth) {
        mScaleWH = scaleWidth;
        return this;
    }

    /**
     * 设置弹窗的 高度缩放比
     *
     * @param scaleHeight 高度缩放比  浮点值
     */
    public HuoDongDialog setScaleHeight(double scaleHeight) {
        mScaleHeight = scaleHeight;
        return this;
    }

    /**
     * 设置活动弹窗的背景图
     *
     * @param url URL
     */
    public HuoDongDialog setHuoDongImgUrl(String url) {
        mImgUrl = url;
        return this;
    }

    /**
     * 设置活动展示信息
     *
     * @param message 展示信息
     */
    public HuoDongDialog setHuoDongMessage(String message) {
        mMessage = message;
        return this;
    }

    /**
     * 设置 活动距离底部弹窗底部的距离
     *
     * @param marginPoxBottom 距离 像素值
     */
    public HuoDongDialog setMarginBottom(int marginPoxBottom) {
        mMarginPoxBottom = marginPoxBottom;
        return this;
    }

    /**
     * 设置点击按钮的背景色
     *
     * @param btnColorString 背景色
     */
    public HuoDongDialog setBtnBgColor(String btnColorString) {
        mBtnColorString = btnColorString;
        return this;
    }
}
