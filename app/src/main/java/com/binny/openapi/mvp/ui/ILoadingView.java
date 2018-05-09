package com.binny.openapi.mvp.ui;

/**
 * author  binny
 * date 5/8
 */
public interface ILoadingView {
    /**
     * 正在加载
     */
    void onLoading();

    void onLoadDone();
}
