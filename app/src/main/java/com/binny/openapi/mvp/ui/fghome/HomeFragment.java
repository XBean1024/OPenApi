package com.binny.openapi.mvp.ui.fghome;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.mvp.ui.base.BaseFragment;
import com.binny.openapi.mvp.ui.fghome.article.ArticleFragment;
import com.binny.openapi.mvp.ui.fghome.history.HistoryDayFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author  binny
 * date 5/9
 */
public class HomeFragment extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout mTab;
    private final String[] mTitles = {"每日一文"
            ,"历史上的今天","每日一文","每日一文","每日一文"
    };
    @Override
    protected void initView(View view) {
        mTab = view.findViewById(R.id.home_tab_layout);
        mViewPager = view.findViewById(R.id.home_view_pager);
        mTab.setupWithViewPager(mViewPager);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);

        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ArticleFragment());
        fragmentList.add(new HistoryDayFragment());
        fragmentList.add(new ArticleFragment());
        fragmentList.add(new ArticleFragment());
        fragmentList.add(new ArticleFragment());

        mViewPager.setAdapter(new HomeFragmentAdapter(getFragmentManager(), fragmentList,mTitles));
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mTab).init();
    }

    @Override
    protected void initRefreshView(final View containerView) {

    }

    @Override
    protected void getData() {

    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onLoadMore() {

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

}
