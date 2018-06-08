package com.binny.openapi.mvp.presenter.login;

import com.binny.openapi.bean.LoginBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.model.login.ILoginModel;
import com.binny.openapi.mvp.model.login.LoginModel;

/**
 * author  binny
 * date 5/9
 */
public class LoginPresenter implements IloginPresenter {
    private DataCallback<LoginBean> mLoginView;

    public LoginPresenter(DataCallback<LoginBean> loginView) {
        mLoginView = loginView;
    }

    @Override
    public void getData(String phone, String passwd) {
        ILoginModel  model = new LoginModel();
        model.requestLogin(new DataCallback<LoginBean>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoadDone() {

            }

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
