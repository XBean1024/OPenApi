package com.binny.openapi.retrofit.manager;

import com.binny.openapi.constant.ConstantUrl;
import com.binny.openapi.retrofit.server.IGankFuLiService;
import com.binny.openapi.retrofit.server.IJuheService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.binny.openapi.constant.ConstantUrl.JUHE_BASE_URL;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/21 16:57
 * @Description:
 */
public class ApiGankManager {
    private static final ApiGankManager ourInstance = new ApiGankManager();
    private Retrofit mRetrofitArticleApi;
    private IGankFuLiService mJuheService;

    public static ApiGankManager getInstance() {
        return ourInstance;
    }

    private ApiGankManager() {
        if (mRetrofitArticleApi == null) {
            mRetrofitArticleApi = new Retrofit.Builder()
                    .baseUrl(ConstantUrl.GANK_FILI_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

    public IGankFuLiService createArticleRetrofitService() {

        if (mJuheService != null) {
            return mJuheService;
        }
        return mRetrofitArticleApi.create(IGankFuLiService.class);
    }
}
