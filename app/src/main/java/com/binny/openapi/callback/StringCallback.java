package com.binny.openapi.callback;

/**
 * author  binny
 * date 5/6
 */
public interface StringCallback {
    void onSuccess(String result);

    void onFailure(String result);
}
