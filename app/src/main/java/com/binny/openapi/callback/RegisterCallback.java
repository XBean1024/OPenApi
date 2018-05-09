package com.binny.openapi.callback;

/**
 * author  binny
 * date 5/7
 */
public interface RegisterCallback {
    void onSuccess(String result, String code);

    void onFailure(String result);
}
