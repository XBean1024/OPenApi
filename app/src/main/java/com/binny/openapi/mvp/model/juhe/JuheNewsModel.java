package com.binny.openapi.mvp.model.juhe;

import com.binny.openapi.bean.JuheNewsBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.retrofit.server.IJuheService;
import com.binny.openapi.util.UtilsRetrofit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.binny.openapi.constant.ConstantUrl.JUHE_NEWS_KEY;

/**
 * Created by binny on 2018/6/8.
 */

public class JuheNewsModel {
    public void getData(String type, DataCallback<JuheNewsBean> callback) {

        IJuheService service = UtilsRetrofit.getJuheService();
        service.getNewsDate(JUHE_NEWS_KEY,type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JuheNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callback.onLoading();
                    }

                    @Override
                    public void onNext(JuheNewsBean juheNewsBean) {
                        callback.onSuccess(juheNewsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        callback.onLoadDone();
                    }
                });

    }
}
