package com.binny.openapi.mvp.callback;

import com.binny.openapi.mvp.callback.base.OnErrorCallback;
import com.binny.openapi.mvp.callback.base.OnSuccessCallback;
import com.binny.openapi.mvp.ui.ILoadingView;

import java.io.Serializable;

/**
 * author  binny
 * date 5/9
 * 请求成功和失败的接口
 *
 *  T 为 请求的实体类型
 */
public interface DataCallback<T extends Serializable> extends OnSuccessCallback<T>, OnErrorCallback,ILoadingView {
}
