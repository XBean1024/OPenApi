package com.binny.openapi.retrofit.manager;

import com.binny.openapi.retrofit.server.IGankFuLiService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by binny on 2018/6/5.
 *
 */

public class DownloaderManager extends BaseManger{

    private Retrofit mRetrofit;

    public DownloaderManager(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public IGankFuLiService getRetrofit() {
        return mRetrofit.create(IGankFuLiService.class);
    }
}
