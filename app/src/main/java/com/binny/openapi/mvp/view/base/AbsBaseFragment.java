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
public abstract class AbsBaseFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    protected Activity mActivity;
    protected final String TAG;
    protected RefreshLayout mRefreshLayout;
    protected View mContainerView;
    protected boolean mIsFirstBindData = true;
    protected boolean mIsRefresh;
    protected boolean mIsLoadMore;

    protected ImmersionBar mImmersionBar;
    public AbsBaseFragment() {
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
            getData();//创建其他fragment 时  不加载数据，当 该fragment 可见时，加载数据
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


        mContainerView = inflater.inflate(getFragmentLayout(), container, false);
        initRefreshView(mContainerView);
        initView(mContainerView);
        if (mRefreshLayout != null) {
            UtilsLog.i("设置下刷新。。。。。");
            mRefreshLayout.setOnRefreshListener(this);
            mRefreshLayout.setOnLoadMoreListener(this);
            mRefreshLayout.setEnableLoadMore(false);
        }
        afterInitView();
        if (getUserVisibleHint()) {
            mIsFirstBindData = false;
            getData();//第一个 可见的 fragment 要加载数据
        }
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
     * 实例化下来刷新的框架,需要实例化 mRefreshLayout  {@link #mRefreshLayout}
     * 需要刷新操作 重写此方法
     * @param containerView 根视图
     */
    protected void initRefreshView(final View containerView) {

    }

    /**
     * 当孩子需要初始化数据，或者联网请求绑定数据，展示数据的 等等可以重写该方法
     *
     * 加载本地数据 或者网络数据
     */
    protected abstract void getData();

    @Override
    public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
        mIsRefresh = true;
        UtilsLog.i("下啦刷新。。。。");
        onRefresh();
    }

    /**
     * 下拉刷新操作 ，用子类来实现
     */
    protected abstract void onRefresh();

    @Override
    public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
        mIsLoadMore = true;
        UtilsLog.i("上啦刷新。。。。");
        onLoadMore();
    }

    /**
     * 加载更多的逻辑由子类来实现
     */
    protected abstract void onLoadMore();

    /**
     * 初始化沉浸式
     */
    protected void afterInitView() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).fitsSystemWindows(false).execute();

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
            mImmersionBar.execute();
    }
}
