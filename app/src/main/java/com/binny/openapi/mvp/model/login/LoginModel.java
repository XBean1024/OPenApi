package com.binny.openapi.mvp.model.login;

import com.binny.openapi.constant.Constant;
import com.binny.openapi.mvp.bean.LoginBean;
import com.binny.openapi.mvp.callback.LoginCallback;
import com.binny.openapi.retrofit.api.IUserService;
import com.binny.openapi.retrofit.util.RetrofitServiceUtil;
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
    public void requestLogin(final LoginCallback loginCallback, String phone, String passwd) {
        IUserService service = RetrofitServiceUtil.getLoginService();
        service.login(Constant.APP_KEY_TOP_API,phone,passwd)
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
                                .putObj("login", loginBean)
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
