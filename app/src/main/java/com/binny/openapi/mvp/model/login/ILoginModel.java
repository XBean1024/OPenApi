package com.binny.openapi.mvp.model.login;

import com.binny.openapi.bean.LoginBean;
import com.binny.openapi.callback.DataCallback;

/**
 * author  binny
 * date 5/10
 */
public interface ILoginModel {
    void requestLogin(DataCallback<LoginBean> loginCallback, String phone, String passwd);
}
