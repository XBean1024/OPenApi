package com.binny.openapi;

import android.app.Application;
import android.content.Context;

import com.binny.openapi.util.JJLogger;

import java.util.logging.Level;


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
        JJLogger.debugWithStackTrace(true);


    }

}
