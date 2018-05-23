package com.binny.openapi;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.vise.log.ViseLog;
import com.vise.log.inner.LogcatTree;


/**
 * author  binny
 * date 5/6
 */
public class APP extends Application {
    public static Context mApp;
    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;

        ViseLog.getLogConfig()
                .configAllowLog(true)//是否输出日志
                .configShowBorders(true)//是否排版显示
                .configTagPrefix("ViseLog")//设置标签前缀
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")//个性化设置标签，默认显示包名
                .configLevel(Log.VERBOSE);//设置日志最小输出级别，默认Log.VERBOSE
        ViseLog.plant(new LogcatTree());//添加打印日志信息到Logcat的树
    }

}
