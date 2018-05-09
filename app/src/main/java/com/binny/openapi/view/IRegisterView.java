package com.binny.openapi.view;

/**
 * author  binny
 * date 5/7
 */
public interface IRegisterView extends ILoadingView{
    /** 请求成功 更新UI
     * @param msg
     * @param code
     */
    void updateView(String msg, String code);
    void onError(String error);
}
