package com.binny.openapi.mvp.model.gank;

import com.binny.openapi.bean.FuLiBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.retrofit.server.IGankFuLiService;
import com.binny.openapi.util.UtilsRetrofit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/21 17:41
 * @Description:
 */
public class GankFuLiModel {
    public void getGankFuliData(int page, DataCallback<FuLiBean> callback) {
        IGankFuLiService service = UtilsRetrofit.getGankFuLiService();
        service.getGankFuLi(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuLiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuLiBean fuLiBean) {
                        callback.onSuccess(fuLiBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
