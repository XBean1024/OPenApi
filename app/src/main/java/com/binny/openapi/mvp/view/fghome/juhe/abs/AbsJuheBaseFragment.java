package com.binny.openapi.mvp.view.fghome.juhe.abs;

import android.view.View;
import android.widget.GridView;

import com.binny.openapi.R;
import com.binny.openapi.mvp.view.base.BaseFragment;
import com.smart.holder.CommonAdapter;

/**
 * Created by binny on 2018/5/30.
 *
 * 历史上的今天
 */

public abstract class AbsJuheBaseFragment extends BaseFragment {

    protected CommonAdapter mCommonAdapter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_layout_home_common;
    }

    @Override
    protected void initView(View view) {
        GridView gridView = view.findViewById(R.id.home_common_gv);
        initAdapter();
        gridView.setAdapter(mCommonAdapter);

    }

    protected abstract void initAdapter();


    @Override
    protected void initRefreshView(View containerView) {
        mRefreshLayout = containerView.findViewById(R.id.article_refreshLayout);
    }


    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onLoadMore() {

    }


}