package com.binny.openapi;

import android.app.Application;
import android.content.Context;

import com.binny.openapi.util.JJLogger;
import com.facebook.stetho.Stetho;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.logging.Level;

import io.flowup.FlowUp;
import okhttp3.OkHttpClient;

/**
 * author  binny
 * date 5/6
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        JJLogger.debug(true);

        initStetho(this);

        initFlowUp(this);

        initOkHttp(this);

    }

    private void initOkHttp(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.OFF);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);
        OkGo.getInstance().init((Application) context)
                .setOkHttpClient(builder.build());
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
