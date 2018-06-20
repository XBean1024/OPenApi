package com.binny.openapi.mvp.view.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.binny.openapi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/20 14:24
 * @Description: 底部导航栏的容器类
 */
public abstract class NavigationContainerFragment extends BaseFragment {

    protected ViewPager mViewPager;
    protected TabLayout mTab;
    private View mTopView;

    protected List<BaseFragment> mFragments = new ArrayList<>();

    public NavigationContainerFragment() {
        super();

    }

    public  void tabTitles(){};
    
    /**
     * @return 通用布局文件
     */
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_navigation;
    }

    /**
     * @param view 实例化的ivew
     */
    @Override
    protected void initView(View view) {
        mTab = view.findViewById(R.id.home_tab_layout);
        mTopView = view.findViewById(R.id.top_view);
        mViewPager = view.findViewById(R.id.home_view_pager);
        mTab.setupWithViewPager(mViewPager);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        
    }

    /**
     * 初始化沉浸栏,设置适配器
     */
    @Override
    protected void afterInitView() {
        super.afterInitView();
        mImmersionBar.titleBar(mTopView).init();
        initFragments(mFragments);
        mViewPager.setAdapter(new CommonFragmentAdapter(getFragmentManager(), mFragments, initTitles()));
    }

    /**
     * @return 字符串数组
     */
    protected abstract String[] initTitles();

    /**
     * 每一个 tab 对应的 fragment
     * @param fragments 集合
     */
    protected abstract void initFragments(List<BaseFragment> fragments);



    @Override
    protected void getData() {

    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onLoadMore() {

    }
}
