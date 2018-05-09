package com.binny.openapi.mvp.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binny.openapi.util.JJLogger;

/**
 * author  binny
 * date 5/9
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    protected final String TAG;

    public BaseFragment() {

        this.TAG = this.getClass().getSimpleName();
        Log.i("[]", "BaseFragment = " + TAG);
    }

    /**
     * 执行该方法时，Fragment与Activity已经完成绑定
     * <p>
     * 该方法有一个Activity类型的参数，代表绑定的Activity，这时候你可以执行诸如mActivity = activity的操作。
     * <p>
     * 在API低于23的版本中不会执行该方法
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    /**
     * 在API低于23的版本中会执行该方法
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JJLogger.logInfo(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), null, false);
        initView(view);
        return view;
    }


    /**
     * 当 activity 创建完毕是调用
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    /**
     * @return 布局文件
     */
    protected abstract int getFragmentLayout();

    /**
     * 初始化view
     *
     * @param view 实例化的ivew
     */
    protected abstract void initView(View view);

    private boolean mIsAdded;


}
