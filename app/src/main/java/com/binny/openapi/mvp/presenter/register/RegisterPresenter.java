package com.binny.openapi.mvp.presenter.register;

import com.binny.openapi.mvp.bean.RegisterBean;
import com.binny.openapi.mvp.callback.OnRegisterCallback;
import com.binny.openapi.mvp.model.register.IRegisterModel;
import com.binny.openapi.mvp.model.register.RegisterModel;
import com.binny.openapi.mvp.ui.activity.register.IRegisterView;

import java.io.File;

/**
 * author  binny
 * date 5/7
 */
public class RegisterPresenter implements IRegisterPresenter {
    private IRegisterView mRegister;
    private IRegisterModel mRegisterModel;

    public RegisterPresenter(IRegisterView register) {
        mRegister = register;
        mRegisterModel = new RegisterModel();
    }
    @Override
    public void getData(String phone, String passwd, String name, String text, String other, String other2, File imageFile) {
        mRegisterModel.requestRegister(new OnRegisterCallback() {
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
