package com.binny.openapi.mvp.view.fghome.juhe.history;

import com.binny.openapi.R;
import com.binny.openapi.bean.HistoryDayBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.presenter.juhe.HistoryTodayPresenter;
import com.binny.openapi.mvp.view.fghome.juhe.abs.AbsJuheBaseFragment;
import com.binny.openapi.util.UtilsLog;
import com.smart.holder.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binny on 2018/6/8.
 *
 */

public class JuheHistoryFragment extends AbsJuheBaseFragment implements DataCallback<HistoryDayBean> {
    protected List<HistoryDayBean.ResultBean> mHistoryDayBeanList = new ArrayList<>();

    @Override
    protected void initAdapter() {
        mCommonAdapter = new CommonAdapter(getActivity(),mHistoryDayBeanList, R.layout.item_layout_home_history_lv,new HistoryTodayViewHolderHelper());

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

    @Override
    protected void getData() {
        new HistoryTodayPresenter().getDate(this);
    }
}
