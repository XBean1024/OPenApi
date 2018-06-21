package com.binny.openapi.mvp.view.fghome.juhe.history;

import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.HistoryDayBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.presenter.juhe.HistoryTodayPresenter;
import com.binny.openapi.mvp.view.base.AbsNavigationContentListFragment;
import com.binny.openapi.util.UtilsLog;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binny on 2018/6/8.
 *
 */

public class HistoryViewHolderFragment extends AbsNavigationContentListFragment implements DataCallback<HistoryDayBean> {
    protected List<HistoryDayBean.ResultBean> mHistoryDayBeanList = new ArrayList<>();

    @Override
    public void onError(String result) {
        UtilsLog.e(result);
    }

    @Override
    public void onLoading() {
        UtilsLog.onLoading();
    }

    @Override
    public void onLoadDone() {
        UtilsLog.onLoadDone();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRefreshLayout.setEnableRefresh(false);
    }

    @Override
    protected IViewHolderHelper initViewHolderHelper() {
        return new HistoryTodayViewHolderHelper();
    }

    @Override
    protected int initItem() {
        return R.layout.item_fragment_home_history_lv;
    }

    @Override
    protected List initListBean() {
        return mHistoryDayBeanList;
    }

    @Override
    public void onSuccess(HistoryDayBean historyDayBean) {
        int size = historyDayBean.getResult().size();
        for (int i = 0; i < size; i++) {
            mHistoryDayBeanList.add(historyDayBean.getResult().get(i));
        }
        mCommonAdapter.notifyDataSetChanged();
    }

    @Override
    protected void getData() {
        new HistoryTodayPresenter().getDate(this);
    }

}
