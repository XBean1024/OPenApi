package com.binny.openapi.mvp.ui.fghome.history;

import android.view.View;
import android.widget.GridView;

import com.binny.openapi.R;
import com.binny.openapi.mvp.bean.HistoryDayBean;
import com.binny.openapi.mvp.callback.DataCallback;
import com.binny.openapi.mvp.presenter.mine.HistoryTodayPresenter;
import com.binny.openapi.mvp.ui.base.BaseFragment;
import com.binny.openapi.util.UtilsLog;
import com.smart.holder.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binny on 2018/5/30.
 *
 * 历史上的今天
 */

public class HistoryDayFragment extends BaseFragment implements DataCallback<HistoryDayBean>{

    private CommonAdapter mCommonAdapter;
    private List<HistoryDayBean.ResultBean> mHistoryDayBeanList = new ArrayList<>();

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_layout_history_day;
    }

    @Override
    protected void initView(View view) {
        GridView gridView = view.findViewById(R.id.history_day_gv);
        mCommonAdapter = new CommonAdapter(getActivity(),mHistoryDayBeanList,R.layout.item_layout_home_history_lv,new HistoryTodayViewHolderHelper());
        gridView.setAdapter(mCommonAdapter);

    }


    @Override
    protected void initRefreshView(View containerView) {

    }

    @Override
    protected void getData() {
        new HistoryTodayPresenter().getDate(this);
    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onLoadMore() {

    }

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
    public void onSuccess(HistoryDayBean historyDayBean) {
        UtilsLog.i(historyDayBean.toString());
        int size = historyDayBean.getResult().size();
        for (int i = 0; i < size; i++) {
            mHistoryDayBeanList.add(historyDayBean.getResult().get(i));
        }
        mCommonAdapter.notifyDataSetChanged();
    }
}
