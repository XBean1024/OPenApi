package com.binny.openapi.module;

import com.binny.openapi.callback.RegisterCallback;

/**
 * author  binny
 * date 5/7
 */
public interface IRegisterModule {
    void requestRegister(RegisterCallback stringCallback, String phone, String passwd);
}
