package com.binny.openapi.mvp.ui.fragment.picture;

import android.animation.ValueAnimator;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.binny.openapi.R;
import com.binny.openapi.mvp.ui.fragment.BaseFragment;
import com.binny.openapi.mvp.ui.fragment.home.HomeFragmentAdapter;
import com.binny.openapi.mvp.ui.fragment.home.vpfragment.ArticleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author  binny
 * date 5/9
 */
public class PictureFragment extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout mTab;
    private final String[] mTitles = {"美文"
            ,"ONE","每日一文","每日一文","每日一文"
    };
    @Override
    protected void initView(View view) {
        mTab = view.findViewById(R.id.home_tab_layout);
        mViewPager = view.findViewById(R.id.home_view_pager);
        mTab.setupWithViewPager(mViewPager);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);

        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ArticleFragment());
        fragmentList.add(new ArticleFragment());
        fragmentList.add(new ArticleFragment());
        fragmentList.add(new ArticleFragment());
        fragmentList.add(new ArticleFragment());

        mViewPager.setAdapter(new HomeFragmentAdapter(getFragmentManager(), fragmentList,mTitles));
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }
}
