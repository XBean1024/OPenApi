package com.binny.openapi.mvp.callback.base;

/**
 * author  binny
 * date 5/9
 * 请求 失败的接口
 */
public interface ErrorCallcabk {
    void onError(String result);
}
