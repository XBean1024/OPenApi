package com.binny.openapi.mvp.view.base;

import android.view.View;
import android.widget.ListView;

import com.binny.openapi.R;
import com.binny.openapi.util.UtilsLog;
import com.smart.holder.CommonAdapter;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

/**
 * Created by binny on 2018/5/30.
 *
 * 各个导航栏目下得 tab 的 对应基础界面
 */

public abstract class AbsNavigationContentFragment extends AbsBaseFragment {

    protected CommonAdapter mCommonAdapter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_common_list;
    }

    @Override
    protected void initView(View view) {
        ListView l = view.findViewById(R.id.home_common_gv);
        mCommonAdapter = new CommonAdapter(getActivity(),initListBean(), initItem(),initViewHolderHelper());
        l.setAdapter(mCommonAdapter);
    }

    /**
     * @return 实例化item ,绑定数据的类
     */
    protected abstract IViewHolderHelper initViewHolderHelper();

    /**
     * @return 布局文件 id
     */
    protected abstract int initItem();

    /**
     * @return 初始化 数据集
     */
    protected abstract List initListBean();

    @Override
    protected void initRefreshView(View containerView) {
        mRefreshLayout = containerView.findViewById(R.id.common_refreshLayout);
    }


    @Override
    protected void onRefresh() {
        UtilsLog.i("下拉刷新数据");
    }


    /**
     * 加载更多
     */
    @Override
    protected void onLoadMore() {
        UtilsLog.i("上拉加载更多");
    }
}
