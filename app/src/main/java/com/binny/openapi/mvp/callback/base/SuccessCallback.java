package com.binny.openapi.mvp.callback.base;

import java.io.Serializable;

/**
 * author  binny
 * date 5/9
 * 请求数据的回到接口
 */
public interface SuccessCallback<T extends Serializable> {
    void onSuccess(T t);
}
