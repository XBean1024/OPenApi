package com.binny.openapi.mvp.presenter.register;

import com.binny.openapi.bean.RegisterBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.model.register.IRegisterModel;
import com.binny.openapi.mvp.model.register.RegisterModel;

import java.io.File;

/**
 * author  binny
 * date 5/7
 */
public class RegisterPresenter implements IRegisterPresenter {
    private DataCallback<RegisterBean> mRegister;
    private IRegisterModel mRegisterModel;

    public RegisterPresenter(DataCallback<RegisterBean> register) {
        mRegister = register;
        mRegisterModel = new RegisterModel();
    }
    @Override
    public void getData(String phone, String passwd, String name, String text, String other, String other2, File imageFile) {
        mRegisterModel.requestRegister(new DataCallback<RegisterBean>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoadDone() {

            }

            @Override
            public void onSuccess(RegisterBean registerBean) {
                mRegister.onSuccess(registerBean);
            }

            @Override
            public void onError(String result) {
                mRegister.onError(result);
            }
        }, phone, passwd, name, text, other, other2, imageFile);
    }
}
