package com.binny.openapi.mvp.model.home;

import com.binny.openapi.mvp.bean.HistoryDayBean;
import com.binny.openapi.mvp.callback.DataCallback;
import com.binny.openapi.retrofit.manager.RetrofitManager;
import com.binny.openapi.retrofit.server.IJuheService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.binny.openapi.constant.Constant.JUHE_HOSITORY_BASE_KEY;

/**
 * author  binny
 * date 5/22
 */
public class HistoryModel {


    public void getArticle(DataCallback<HistoryDayBean> mCallback) {
        IJuheService service = RetrofitManager.getJuheService();
        SimpleDateFormat format = new SimpleDateFormat("d/M");
        String date = format.format(Calendar.getInstance().getTime());
        service.getHistoryDayDate(JUHE_HOSITORY_BASE_KEY, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryDayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCallback.onLoading();
                    }

                    @Override
                    public void onNext(HistoryDayBean historyDayBean) {
                        mCallback.onSuccess(historyDayBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mCallback.onLoadDone();
                    }
                });
    }


}
