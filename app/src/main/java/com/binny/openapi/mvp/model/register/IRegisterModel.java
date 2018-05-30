package com.binny.openapi.mvp.model.register;

import com.binny.openapi.mvp.bean.RegisterBean;
import com.binny.openapi.mvp.callback.DataCallback;

import java.io.File;

/**
 * author  binny
 * date 5/7
 */
public interface IRegisterModel {
    /**
     * @param stringCallback 请求结果的回调
     * @param phone 请求的手机号
     * @param passwd 请求密码
     * @param name 姓名
     * @param text 备注信息，可以作为签名
     * @param other 备注信息，可以作为签名
     * @param other2 上传头像
     */
    void requestRegister(DataCallback<RegisterBean> stringCallback, String phone, String passwd, String name, String text, String other, String other2, File imageFile);
}
