package com.binny.openapi.retrofit.manager.topapi;

import com.binny.openapi.retrofit.api.IArticleService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.binny.openapi.constant.Constant.ARTICLE_BASE_URL;
import static com.binny.openapi.constant.Constant.TOP_API_BASE_URL;

/**
 * author  binny
 * date 5/9
 */
public class RtftManagerTopApi {

    private static RtftManagerTopApi mRetrofitManager;
    private static Retrofit mRetrofitTopApi;
    private static Retrofit mRetrofitArticleApi;

    public static synchronized RtftManagerTopApi getInstance() {
        if (mRetrofitManager == null) {
            mRetrofitManager = new RtftManagerTopApi();
        }
        return mRetrofitManager;
    }

    private RtftManagerTopApi() {
        if (mRetrofitTopApi == null) {
            mRetrofitTopApi = new Retrofit.Builder()
                    .baseUrl(TOP_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

    public <T> T createTopApiService(Class<T> reqServer) {

        return mRetrofitTopApi.create(reqServer);
    }

}
