package com.binny.openapi.mvp.view.fghome;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.binny.openapi.R;
import com.binny.openapi.mvp.view.base.BaseFragment;
import com.binny.openapi.mvp.view.fghome.article.ArticleFragment;
import com.binny.openapi.mvp.view.fghome.juhe.history.JuheHistoryFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheAmusementFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheFashionFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheFinaceFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheGymFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheInternalFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheMilitrayFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheSCFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheSocialFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheTouTiaoFrgment;

import java.util.ArrayList;
import java.util.List;

/**
 * author  binny
 * date 5/9
 */
public class HomeFragment extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout mTab;
    private final String[] mTitles = {
            "头条",
            "每日一文",
            "历史上的今天",
            "社会",
            "国内",
            "娱乐",
            "体育",
            "军事",
            "科技",
            "财经",
            "时尚"
    };
    private View mTopView;

    @Override
    protected void initView(View view) {
        mTab = view.findViewById(R.id.home_tab_layout);
        mTopView = view.findViewById(R.id.top_view);
        mViewPager = view.findViewById(R.id.home_view_pager);
        mTab.setupWithViewPager(mViewPager);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);

        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new JuheTouTiaoFrgment());//头条
        fragmentList.add(new ArticleFragment());//每日一文
        fragmentList.add(new JuheHistoryFragment());//历史上的今天
        fragmentList.add(new JuheSocialFragment());//社会
        fragmentList.add(new JuheInternalFragment());//国内
        fragmentList.add(new JuheAmusementFragment());//娱乐
        fragmentList.add(new JuheGymFragment());//体育
        fragmentList.add(new JuheMilitrayFragment());//军事
        fragmentList.add(new JuheSCFragment());//科技
        fragmentList.add(new JuheFinaceFragment());//财经
        fragmentList.add(new JuheFashionFragment());//时尚


        mViewPager.setAdapter(new HomeFragmentAdapter(getFragmentManager(), fragmentList, mTitles));
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mTopView).init();

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
