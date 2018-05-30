package com.binny.openapi.util;


import com.binny.openapi.retrofit.server.IArticleService;
import com.binny.openapi.retrofit.server.topapi.user.ITopUserService;
import com.binny.openapi.retrofit.manager.ApiArticleManger;
import com.binny.openapi.retrofit.manager.topapi.ApiTopManager;

/**
 * author  binny
 * date 5/9
 *
 */
public class RetrofitServiceUtil {

    public static ITopUserService getLoginService() {
        return ApiTopManager.getInstance().createTopApiService(ITopUserService.class);
    }
    public static IArticleService getArticleService() {
        return ApiArticleManger.getInstance().createArticleRetrofitService();
    }
}
