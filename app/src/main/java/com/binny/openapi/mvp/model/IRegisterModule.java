package com.binny.openapi.mvp.model;

import com.binny.openapi.mvp.callback.register.RegisterCallback;

import java.io.File;

/**
 * author  binny
 * date 5/7
 */
public interface IRegisterModule {
    void requestRegister(RegisterCallback stringCallback, String phone, String passwd, String name, String text, String other, String other2, File imageFile);
}
