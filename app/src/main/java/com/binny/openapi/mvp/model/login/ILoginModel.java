package com.binny.openapi.mvp.model.login;

import com.binny.openapi.mvp.callback.LoginCallback;

/**
 * author  binny
 * date 5/10
 */
public interface ILoginModel {
    void requestLogin(LoginCallback loginCallback, String phone, String passwd);
}
