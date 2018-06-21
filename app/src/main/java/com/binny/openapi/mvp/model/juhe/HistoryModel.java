package com.binny.openapi.mvp.model.juhe;

import android.annotation.SuppressLint;

import com.binny.openapi.bean.HistoryDayBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.util.UtilsRetrofit;
import com.binny.openapi.retrofit.server.IJuheService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.binny.openapi.constant.ConstantUrl.JUHE_HOSITORY_KEY;

/**
 * author  binny
 * date 5/22
 */
public class HistoryModel {


    @SuppressLint("CheckResult")
    public void getArticle(DataCallback<HistoryDayBean> mCallback) {
        IJuheService service = UtilsRetrofit.getJuheService();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("M/d");
        String date = format.format(Calendar.getInstance().getTime());
        service.getHistoryDayDate(JUHE_HOSITORY_KEY, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCallback::onSuccess, throwable -> {
                    mCallback.onError(throwable.getMessage());
                });
    }
}
