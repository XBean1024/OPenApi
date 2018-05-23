package com.binny.openapi.mvp.callback.base;

import java.io.Serializable;

/**
 * author  binny
 * date 5/9
 * 请求数据的回到接口
 */
public interface OnSuccessCallback<T extends Serializable> {
    void onSuccess(T t);
}
