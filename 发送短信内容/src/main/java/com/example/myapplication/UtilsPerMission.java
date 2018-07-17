package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 作者: binny
 * 时间: 5/24
 * 描述:
 */
public class UtilsPerMission {
    @SuppressLint("CheckResult")
    public static void getPermission(final OnPermissionCallback permissionCallback, Activity activity, View view, String... permission) {
        RxPermissions rxPermissions = new RxPermissions(activity);

        RxView.clicks(view)
                .compose(rxPermissions.ensure(permission))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            permissionCallback.onGranted();
                        } else {
                            permissionCallback.onDeny();
                        }
                    }
                });
    }@SuppressLint("CheckResult")
    public static void getPermission(final OnPermissionCallback permissionCallback, Activity activity, String... permission) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        // Must be done during an initialization phase like onCreate
        rxPermissions
                .request(permission)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            permissionCallback.onGranted();
                        } else {
                            permissionCallback.onDeny();
                        }
                    }
                });
    }
}
