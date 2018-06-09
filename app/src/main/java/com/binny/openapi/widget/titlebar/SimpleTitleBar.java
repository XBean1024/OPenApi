package com.binny.openapi.widget.titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binny.openapi.R;

/**
 * Created by binny on 2018/6/10.
 * 简单的标题栏  返回按钮 标题 分享按钮
 */

public class SimpleTitleBar extends RelativeLayout {
    private TextView mLeftBtn;
    private TextView mTitle;
    private TextView mRightBtn;

    public SimpleTitleBar(Context context) {
        super(context);
    }

    public SimpleTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLeftBtn = findViewById(R.id.simple_left_btn);
        mTitle = findViewById(R.id.simple_title);
        mRightBtn = findViewById(R.id.simple_right_btn);
    }

    public SimpleTitleBar setTitle(String title) {
        mTitle.setText(title);
        return this;
    }
    public SimpleTitleBar setOnClickedListener(OnClickListener listener1,OnClickListener listener2){
        mLeftBtn.setOnClickListener(listener1);
        mRightBtn.setOnClickListener(listener2);
        return this;
    }
}
