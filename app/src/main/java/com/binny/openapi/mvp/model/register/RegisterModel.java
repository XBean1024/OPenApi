package com.binny.openapi.mvp.model.register;

import com.binny.openapi.mvp.bean.RegisterBean;
import com.binny.openapi.mvp.callback.DataCallback;
import com.binny.openapi.retrofit.server.topapi.user.ITopUserService;
import com.binny.openapi.util.RetrofitServiceUtil;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.binny.openapi.constant.Constant.TOP_API_APP_KEY;

/**
 * author  binny
 * date 5/7
 */
public class RegisterModel implements IRegisterModel {

    @Override
    public void requestRegister(final DataCallback<RegisterBean> registerCallback, String phone, String password, String name, String text, String other, String other2, File imageFile) {
        ITopUserService service = RetrofitServiceUtil.getLoginService();
        service.register(TOP_API_APP_KEY, name, phone, password, text, other, other2, imageFile)
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
