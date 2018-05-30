package com.binny.openapi.util;

import com.orhanobut.logger.Logger;

/**
 * 作者: binny
 * 时间: 5/24
 * 描述:
 */
public class UtilsLog {
    public static void init(String arg) {
        Logger.init(arg);
    }
    public static void init() {
        Logger.init();
    }
    public static void d(String args) {
        Logger.d(args);
    }
    public static void i(String args) {
        Logger.i(args);
    }

    public static void e(String error) {
        Logger.e(error);
    }

    public static void e(String articleModel, String s) {
        Logger.e(articleModel,s);
    }

    public static void i(String tag, String s) {
        Logger.i(s);
    }

    public static void onLoadDone() {
        UtilsLog.i("数据完成加载......");
    }
    public static void onLoading() {
        UtilsLog.i("数据正在加载......");
    }
}
