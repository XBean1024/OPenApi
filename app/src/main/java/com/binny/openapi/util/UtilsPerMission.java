package com.binny.openapi.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import com.binny.openapi.callback.OnPermissionCallback;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * 作者: binny
 * 时间: 5/24
 * 描述:
 */
public class UtilsPerMission {
    @SuppressLint("CheckResult")
    public static void getPermission(OnPermissionCallback permissionCallback, Activity activity, View view, String... permission) {
        RxPermissions rxPermissions = new RxPermissions(activity);

        RxView.clicks(view)
                .compose(rxPermissions.ensure(permission))
                .subscribe(granted -> {
                    if (granted) {
                        permissionCallback.onGranted();
                    } else {
                        permissionCallback.onDeny();
                    }
                });
    }@SuppressLint("CheckResult")
    public static void getPermission(OnPermissionCallback permissionCallback, Activity activity, String... permission) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        // Must be done during an initialization phase like onCreate
        rxPermissions
                .request(permission)
                .subscribe(granted -> {
                    if (granted) {
                        permissionCallback.onGranted();
                    } else {
                        permissionCallback.onDeny();
                    }
                });
    }
}
