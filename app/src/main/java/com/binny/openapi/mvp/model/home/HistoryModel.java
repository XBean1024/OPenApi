package com.binny.openapi.mvp.model.home;

import android.annotation.SuppressLint;

import com.binny.openapi.mvp.bean.HistoryDayBean;
import com.binny.openapi.mvp.callback.DataCallback;
import com.binny.openapi.retrofit.manager.RetrofitManager;
import com.binny.openapi.retrofit.server.IJuheService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.binny.openapi.constant.UrlConstant.JUHE_HOSITORY_BASE_KEY;

/**
 * author  binny
 * date 5/22
 */
public class HistoryModel {


    public void getArticle(DataCallback<HistoryDayBean> mCallback) {
        IJuheService service = RetrofitManager.getJuheService();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("d/M");
        String date = format.format(Calendar.getInstance().getTime());
        service.getHistoryDayDate(JUHE_HOSITORY_BASE_KEY, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCallback::onSuccess, throwable -> {
                    mCallback.onError(throwable.getMessage());
                });
    }
}
