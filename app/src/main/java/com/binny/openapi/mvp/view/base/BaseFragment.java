package com.binny.openapi.mvp.view.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binny.openapi.immersionbar.ImmersionBar;
import com.binny.openapi.util.UtilsLog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


/**
 * author  binny
 * date 5/9
 */
public abstract class BaseFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    protected Activity mActivity;
    protected final String TAG;
    protected RefreshLayout mRefreshLayout;
    private View mContainerView;
    protected boolean mIsFirstBindData = true;
    protected boolean mIsRefresh;
    protected boolean mIsLoadMore;

    protected ImmersionBar mImmersionBar;
    public BaseFragment() {
        this.TAG = this.getClass().getSimpleName();
    }


    /*
     * 防止频繁请求网络
     * */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        UtilsLog.i(TAG + "    setUserVisibleHint  = " + isVisibleToUser);
        if (mIsFirstBindData && mContainerView != null && isVisibleToUser) {
            mIsFirstBindData = false;
            getData();
        }
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mContainerView != null) {
            //防止重复创建视图
            return mContainerView;
        }

        /*
         * 创建视图
         * */
         /*
         * 如果第一次创建时，可见，则加载数据，绑定数据
         * */

        if (getUserVisibleHint()) {
            mIsFirstBindData = false;
            getData();
        }
        mContainerView = inflater.inflate(getFragmentLayout(), container, false);
        initRefreshView(mContainerView);
        initView(mContainerView);
        if (mRefreshLayout != null) {
            UtilsLog.i("设置下刷新。。。。。");
            mRefreshLayout.setOnRefreshListener(this);
            mRefreshLayout.setOnLoadMoreListener(this);
            mRefreshLayout.setEnableLoadMore(false);
        }

        initImmersionBar();
        return mContainerView;
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

    /**
     * 实例化下来刷新的框架
     *
     * @param containerView
     */
    protected abstract void initRefreshView(final View containerView);

    /**
     * 当孩子需要初始化数据，或者联网请求绑定数据，展示数据的 等等可以重写该方法
     */
    protected abstract void getData();

    @Override
    public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
        mIsRefresh = true;
        UtilsLog.i("下啦刷新。。。。");
        onRefresh();
    }

    protected abstract void onRefresh();

    @Override
    public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
        mIsLoadMore = true;
        UtilsLog.i("上啦刷新。。。。");
        onLoadMore();
    }

    protected abstract void onLoadMore();

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).fitsSystemWindows(false).init();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }
}
