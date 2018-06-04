package com.binny.openapi.retrofit.manager;


import com.binny.openapi.retrofit.server.IArticleService;
import com.binny.openapi.retrofit.server.IJuheService;
import com.binny.openapi.retrofit.server.ITopApiService;

/**
 * author  binny
 * date 5/9
 *
 */
public class RetrofitManager {

    public static ITopApiService getTopApiService() {
        return ApiTopManager.getInstance().createTopApiService(ITopApiService.class);
    }
    public static IArticleService getArticleService() {
        return ApiArticleManger.getInstance().createArticleRetrofitService();
    }public static IJuheService getJuheService() {
        return ApiJuheManager.getInstance().createArticleRetrofitService();
    }
}
