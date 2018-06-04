package com.binny.openapi.mvp.model.picture;

import com.binny.openapi.mvp.bean.PictureBean;
import com.binny.openapi.mvp.callback.DataCallback;
import com.binny.openapi.retrofit.manager.RetrofitManager;
import com.binny.openapi.retrofit.server.ITopApiService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by binny on 2018/5/30.
 */

public class PictureModel {

    public void getData(int page, DataCallback<PictureBean> callback) {
        ITopApiService service = RetrofitManager.getTopApiService();
        service.getPictures(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PictureBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callback.onLoading();

                    }

                    @Override
                    public void onNext(PictureBean pictureBean) {
                        callback.onSuccess(pictureBean);
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
