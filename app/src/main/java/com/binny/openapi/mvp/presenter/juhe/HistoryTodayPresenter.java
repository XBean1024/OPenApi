package com.binny.openapi.mvp.presenter.juhe;

import com.binny.openapi.bean.HistoryDayBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.model.juhe.HistoryModel;

/**
 * Created by binny on 2018/6/1.
 * 分发 业务逻辑
 */

public class HistoryTodayPresenter {

    public void getDate(DataCallback<HistoryDayBean> callback) {
        new HistoryModel().getArticle(callback);
    }
}
