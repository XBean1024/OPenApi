package com.binny.openapi.util;


import com.binny.openapi.retrofit.api.IArticleService;
import com.binny.openapi.retrofit.api.topapi.user.ITopUserService;
import com.binny.openapi.retrofit.manager.RtftArticleManger;
import com.binny.openapi.retrofit.manager.topapi.RtftManagerTopApi;

/**
 * author  binny
 * date 5/9
 *
 */
public class RetrofitServiceUtil {

    public static ITopUserService getLoginService() {
        return RtftManagerTopApi.getInstance().createTopApiService(ITopUserService.class);
    }
    public static IArticleService getArticleService() {
        return RtftArticleManger.getInstance().createArticleRetrofitService();
    }
}
