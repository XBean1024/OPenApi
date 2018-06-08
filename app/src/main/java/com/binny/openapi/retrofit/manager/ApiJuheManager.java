package com.binny.openapi.retrofit.manager;

import com.binny.openapi.retrofit.server.IJuheService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.binny.openapi.constant.UrlConstant.JUHE_BASE_URL;

/**
 * Created by binny on 2018/5/30.
 */

public class ApiJuheManager {
    private static final ApiJuheManager ourInstance = new ApiJuheManager();
    private Retrofit mRetrofitArticleApi;
    private IJuheService mJuheService;

    public static ApiJuheManager getInstance() {
        return ourInstance;
    }

    private ApiJuheManager() {
        if (mRetrofitArticleApi == null) {
            mRetrofitArticleApi = new Retrofit.Builder()
                    .baseUrl(JUHE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

    public IJuheService createArticleRetrofitService() {

        if (mJuheService != null) {
            return mJuheService;
        }
        return mRetrofitArticleApi.create(IJuheService.class);
    }
}
