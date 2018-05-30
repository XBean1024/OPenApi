package com.binny.openapi.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binny.openapi.R;

/**
 * 键值对形式的布局 用于 常用于注册界面
 * <p>
 * author  binny
 * date 5/6
 */
public class KeyValueLayout extends RelativeLayout {
    private TextView mKeyTv;
    private EditText mValueEt;

    public TextView getKeyTv() {
        return mKeyTv;
    }

    public String getValue() {
        return mValueEt.getText().toString();
    }

    public KeyValueLayout(Context context) {
        super(context);
        init(context, null);
    }

    public void setKeyTv(String name) {
        mKeyTv.setText(name);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KeyValueLayout);
        String key = array.getString(R.styleable.KeyValueLayout_key_name);
        String value_hint = array.getString(R.styleable.KeyValueLayout_value_hint);
        String value_text = array.getString(R.styleable.KeyValueLayout_value_text);
        int value_length = array.getInteger(R.styleable.KeyValueLayout_value_length,16);
        array.recycle();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_layout_key_value, this);
        mKeyTv = view.findViewById(R.id.key);
        mValueEt = view.findViewById(R.id.value);
        mKeyTv.setText(key);
        mValueEt.setFilters(new InputFilter[] { new InputFilter.LengthFilter(value_length) });
        if (!TextUtils.isEmpty(value_hint)) {
            mValueEt.setHint(value_hint);
        }
        if (!TextUtils.isEmpty(value_text)) {
            mValueEt.setText(value_text);
        }
        Log.i("key", "init" + key);

    }

    public KeyValueLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public KeyValueLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
}
