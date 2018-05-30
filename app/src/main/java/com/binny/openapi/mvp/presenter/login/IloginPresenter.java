package com.binny.openapi.mvp.presenter.login;

/**
 * author  binny
 * date 5/9
 */
public interface IloginPresenter {
    /**
     * @param phone 手机号
     * @param passwd 密码
     */
    void getData(String phone, String passwd);
}
