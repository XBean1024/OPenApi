package com.binny.openapi.retrofit.manager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.binny.openapi.constant.Constant.TOP_API_BASE_URL;

/** topApi的接口
 * author  binny
 * date 5/9
 */
public class ApiTopManager {

    private static ApiTopManager mRetrofitManager;
    private static Retrofit mRetrofitTopApi;

    /**
     * @return 管理类实例
     */
    public static synchronized ApiTopManager getInstance() {
        if (mRetrofitManager == null) {
            mRetrofitManager = new ApiTopManager();
        }
        return mRetrofitManager;
    }

    /**
     * 出初始化 retrofit
     */
    private ApiTopManager() {
        if (mRetrofitTopApi == null) {
            mRetrofitTopApi = new Retrofit.Builder()
                    .baseUrl(TOP_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

    /**
     * @param reqServer  配置网络请求的接口
     * @param <T> 网络请求接口的实例类型
     * @return 返回一个网络接口实例
     */
    public <T> T createTopApiService(Class<T> reqServer) {

        return mRetrofitTopApi.create(reqServer);
    }

}
