package com.binny.openapi.mvp.model.register;

import com.binny.openapi.mvp.bean.RegisterBean;
import com.binny.openapi.mvp.callback.register.RegisterCallback;
import com.binny.openapi.retrofit.api.IUserService;
import com.binny.openapi.retrofit.util.RetrofitServiceUtil;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.binny.openapi.constant.Constant.APP_KEY_TOP_API;

/**
 * author  binny
 * date 5/7
 */
public class RegisterModel implements IRegisterModel {

    @Override
    public void requestRegister(final RegisterCallback registerCallback, String phone, String password, String name, String text, String other, String other2, File imageFile) {
        IUserService service = RetrofitServiceUtil.getLoginService();
        service.register(APP_KEY_TOP_API, name, phone, password, text, other, other2, imageFile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        registerCallback.onSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
