package com.binny.openapi;

import android.app.Application;
import com.binny.openapi.util.UtilsLog;


/**
 * author  binny
 * date 5/6
 */
public class APP extends Application {
    public static APP mApp;
    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;
        UtilsLog.init();


    }

}
