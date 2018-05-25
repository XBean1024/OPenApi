package com.binny.openapi.mvp.model.mine;

import com.bean.xhttp.XHttp;
import com.bean.xhttp.callback.OnXHttpCallback;
import com.bean.xhttp.response.Response;
import com.binny.openapi.mvp.bean.ArticleBean;
import com.binny.openapi.mvp.callback.OnArticleCallback;
import com.binny.openapi.mvp.ui.fragment.home.vpfragment.IArticleView;
import com.binny.openapi.retrofit.api.IArticleService;
import com.binny.openapi.util.UtilsLog;
import com.google.gson.Gson;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.binny.openapi.util.RetrofitServiceUtil.getArticleService;

/**
 * author  binny
 * date 5/22
 */
public class ArticleModel {
    private OnArticleCallback mCallback;

    public ArticleModel(OnArticleCallback callback) {
        mCallback = callback;
    }

    public void getArticle() {
        IArticleService service = getArticleService();
        service.getArticleToday()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleBean>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        mCallback.onLoading();
                    }

                    @Override
                    public void onNext(final ArticleBean articleBean) {
                        mCallback.onSuccess(articleBean);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        mCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mCallback.onLoadDone();
                    }
                });
//        XHttp.getInstance()
//                .get("https://interface.meiriyiwen.com/article/today?dev=1")
//                .setOnXHttpCallback(new OnXHttpCallback() {
//                    @Override
//                    public void onSuccess(Response response) {
//                        Gson gson = new Gson();
//                        ArticleBean articleBean = gson.fromJson(response.toString(), ArticleBean.class);
//                        mCallback.onSuccess(articleBean);
//                    }
//
//                    @Override
//                    public void onFailure(Exception ex, String errorCode) {
//                        UtilsLog.i("ArticleModel","onDeny"+ex.getMessage());
//                    }
//                });
    }

    public void getArticleRandom() {
        XHttp.getInstance()
                .get("https://interface.meiriyiwen.com/article/random?dev=1")
                .setOnXHttpCallback(new OnXHttpCallback() {
                    @Override
                    public void onSuccess(Response response) {
                        Gson gson = new Gson();
                        ArticleBean articleBean = gson.fromJson(response.toString(), ArticleBean.class);
                        mCallback.onSuccess(articleBean);
                    }

                    @Override
                    public void onFailure(Exception ex, String errorCode) {
                        UtilsLog.i("ArticleModel","onDeny"+ex.getMessage());
                    }
                });
    }

    public void getArticleDay() {
        XHttp.getInstance()
                .get("https://interface.meiriyiwen.com/article/day?dev=1")
                .setOnXHttpCallback(new OnXHttpCallback() {
                    @Override
                    public void onSuccess(Response response) {
                        Gson gson = new Gson();
                        ArticleBean articleBean = gson.fromJson(response.toString(), ArticleBean.class);
                        mCallback.onSuccess(articleBean);
                    }

                    @Override
                    public void onFailure(Exception ex, String errorCode) {
                        UtilsLog.e("ArticleModel","onDeny"+ex.getMessage());
                    }
                });
    }
}
