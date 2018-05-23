package com.binny.openapi.mvp.callback.base;

import java.io.Serializable;

/**
 * author  binny
 * date 5/9
 * 请求成功和失败的接口
 */
public interface OnBaseCallback<T extends Serializable> extends OnSuccessCallback<T>, OnErrorCallback {
}
