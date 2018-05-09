package com.binny.openapi.presenter;

import com.binny.openapi.callback.RegisterCallback;
import com.binny.openapi.module.IRegisterModule;
import com.binny.openapi.module.RegisterModule;
import com.binny.openapi.view.IRegisterView;

/**
 * author  binny
 * date 5/7
 */
public class RegisterPresenter implements IRegisterPresenter {
    private IRegisterView mRegister;
    private IRegisterModule mRegisterModel;

    public RegisterPresenter(IRegisterView register) {
        mRegister = register;
        mRegisterModel = new RegisterModule();
    }
    @Override
    public void getData(String phone, String passwd) {
        mRegisterModel.requestRegister(new RegisterCallback() {
            @Override
            public void onSuccess(String result,String code) {
                mRegister.updateView(result, code);
            }

            @Override
            public void onFailure(String result) {

                mRegister.onError(result);
            }
        }, phone, passwd);
    }
}
