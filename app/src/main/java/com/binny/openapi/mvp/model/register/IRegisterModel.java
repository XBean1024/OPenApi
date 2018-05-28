package com.binny.openapi.mvp.model.register;

import com.binny.openapi.mvp.bean.RegisterBean;
import com.binny.openapi.mvp.callback.DataCallback;

import java.io.File;

/**
 * author  binny
 * date 5/7
 */
public interface IRegisterModel {
    void requestRegister(DataCallback<RegisterBean> stringCallback, String phone, String passwd, String name, String text, String other, String other2, File imageFile);
}
