package com.binny.openapi;

import android.app.Application;
import android.content.Context;

import com.binny.openapi.util.JJLogger;
import com.facebook.stetho.Stetho;

import java.util.logging.Level;

import io.flowup.FlowUp;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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

        initStetho(this);

        initFlowUp(this);

    }


    private void initFlowUp(Context context) {
        FlowUp.Builder.with((Application) context)
                .apiKey("775ee1c7f8f043af85a3ce779446d8fa")
                .forceReports(BuildConfig.DEBUG)
                .start();

    }

    private void initStetho(Context context) {
         /*
        * Stetho is a debug bridge for Android applications, enabling the powerful Chrome Developer Tools and much more.
        *
        * https://github.com/facebook/stetho
        * */
        Stetho.initializeWithDefaults(context);
    }
}
