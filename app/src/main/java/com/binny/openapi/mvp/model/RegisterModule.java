package com.binny.openapi.mvp.model;


import android.util.Log;

import com.binny.openapi.mvp.bean.RegisterBean;
import com.binny.openapi.mvp.callback.register.RegisterCallback;
import com.binny.openapi.retrofit.api.IRegisterService;
import com.binny.openapi.retrofit.util.RetrofitServiceUtil;

import java.io.File;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.binny.openapi.constant.Constant.APP_KEY_Top_Api;

/**
 * author  binny
 * date 5/7
 */
public class RegisterModule implements IRegisterModule {

    @Override
    public void requestRegister(final RegisterCallback registerCallback, String phone, String password, String name, String text, String other, String other2, File imageFile) {
        IRegisterService service = RetrofitServiceUtil.getRegisterService();
        service.register(APP_KEY_Top_Api, name, phone, password, text, other, other2, imageFile)
                .subscribeOn(Schedulers.newThread())
                .doOnNext(new Action1<RegisterBean>() {
                    @Override
                    public void call(RegisterBean registerBean) {
                        Log.i("[beautyBean]", "call = " + Thread.currentThread().getId());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        registerCallback.onError(e.getMessage());

                    }

                    @Override
                    public void onNext(RegisterBean beautyBean) {
                        registerCallback.onSuccess(beautyBean);
                    }
        });
    }
}
