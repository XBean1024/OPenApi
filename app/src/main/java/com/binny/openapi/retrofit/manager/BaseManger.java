package com.binny.openapi.retrofit.manager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * describe:
 *
 * @author 596928539@qq.com
 * @date 2019/04/10
 */
public class BaseManger {
    protected static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(30, TimeUnit.SECONDS).
            readTimeout(30, TimeUnit.SECONDS).
            writeTimeout(30, TimeUnit.SECONDS).build();
}
