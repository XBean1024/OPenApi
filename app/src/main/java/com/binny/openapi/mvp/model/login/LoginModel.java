package com.binny.openapi.mvp.model.login;

import com.binny.openapi.constant.ConstantUrl;
import com.binny.openapi.bean.LoginBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.retrofit.server.ITopApiService;
import com.binny.openapi.util.UtilsRetrofit;
import com.binny.openapi.util.UtilSP;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.binny.openapi.APP.mApp;


/**
 * author  binny
 * date 5/10
 */
public class LoginModel implements ILoginModel {
    @Override
    public void requestLogin(final DataCallback<LoginBean> loginCallback, String phone, String passwd) {
        ITopApiService service = UtilsRetrofit.getTopApiService();
        service.login(ConstantUrl.TOP_API_APP_KEY,phone,passwd)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        loginCallback.onSuccess(loginBean);
                        UtilSP.getInstance(mApp).setFileName("login")
                                .putSerializableObj("login", loginBean)
                                .commit();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
