package com.binny.openapi.mvp.view.base;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.binny.openapi.mvp.view.base.BaseFragment;

import java.util.List;

/**
 * author  binny
 * date 5/22
 *
 * 嵌套 适配器
 */
public class CommonFragmentAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> mFragmentList;
    private String [] mTitles;
    public CommonFragmentAdapter(final FragmentManager fm, final List<BaseFragment> fragmentList, final String[] titles) {
        super(fm);
        mFragmentList = fragmentList;
        mTitles = titles;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(final int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }



    @Nullable
    @Override
    public CharSequence getPageTitle(final int position) {
        return mTitles[position];
    }
}
