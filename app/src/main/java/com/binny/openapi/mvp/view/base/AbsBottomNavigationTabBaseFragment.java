package com.binny.openapi.mvp.view.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.mvp.view.fgmine.ToolFragment;
import com.binny.openapi.util.UtilsLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/20 14:24
 * @Description: 底部导航栏 tab 的基础类
 * <p>
 * 该类 基于 viewpager {{@link #mViewPager}} + fragment {@link #mFragments}+ tablayout {@link #mTopView}实现不同页面的切换
 * <p>
 * 底部tab 的fragment 只需要 实现该类，提供 顶部tab、的标题 和  相应的 fragment 集合 集合 即可
 * <p>
 * <p>
 * 目前实现的 底部导航按钮有
 * {@link com.binny.openapi.mvp.view.fghome.HomeFragment}
 * {@link com.binny.openapi.mvp.view.fgpicture.PictureFragment}
 * {@link com.binny.openapi.mvp.view.fgmusic.MusicFragment}
 * {@link com.binny.openapi.mvp.view.fgvideo.VideoFragment}
 * {@link ToolFragment}
 */
public abstract class AbsBottomNavigationTabBaseFragment extends AbsBaseFragment {

    protected ViewPager mViewPager;
    protected TabLayout mTab;
    private View mTopView;

    protected List<AbsBaseFragment> mFragments = new ArrayList<>();


    /**
     * 该类的 布局文件
     * @return 通用布局文件
     */
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_navigation;
    }

    /**
     *
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
        mImmersionBar.titleBar(mTopView).execute();
        initFragments(mFragments);
        UtilsLog.i("天劫楼  " + mFragments.size());
        mViewPager.setAdapter(new CommonFragmentAdapter(getChildFragmentManager(), mFragments, initTitles()));
    }

    /**
     * @return 字符串数组
     */
    protected abstract String[] initTitles();

    /**
     * 每一个 tab 对应的 fragment
     *
     * @param fragments 集合
     */
    protected abstract void initFragments(List<AbsBaseFragment> fragments);


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
