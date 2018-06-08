package com.binny.openapi.callback;

/**
 * 作者: binny
 * 时间: 5/24
 * 描述:
 */
public interface OnPermissionCallback {
    void onGranted();
    void onDeny();
}
