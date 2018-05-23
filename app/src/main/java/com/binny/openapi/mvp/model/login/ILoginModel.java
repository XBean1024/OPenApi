package com.binny.openapi.mvp.model.login;

import com.binny.openapi.mvp.callback.OnLoginCallback;

/**
 * author  binny
 * date 5/10
 */
public interface ILoginModel {
    void requestLogin(OnLoginCallback loginCallback, String phone, String passwd);
}
