package com.binny.openapi.mvp.presenter.login;

import com.binny.openapi.mvp.bean.LoginBean;
import com.binny.openapi.mvp.callback.LoginCallback;
import com.binny.openapi.mvp.model.login.ILoginModel;
import com.binny.openapi.mvp.model.login.LoginModel;
import com.binny.openapi.mvp.ui.activity.login.ILoginView;

/**
 * author  binny
 * date 5/9
 */
public class LoginPresenter implements IloginPresenter {
    private ILoginView mLoginView;

    public LoginPresenter(ILoginView loginView) {
        mLoginView = loginView;
    }

    @Override
    public void getData(String phone, String passwd) {
        ILoginModel  model = new LoginModel();
        model.requestLogin(new LoginCallback() {
            @Override
            public void onError(String result) {
                mLoginView.onError(result);
            }

            @Override
            public void onSuccess(LoginBean loginBean) {
                    mLoginView.onSuccess(loginBean);
            }
        },phone,passwd);
    }
}
