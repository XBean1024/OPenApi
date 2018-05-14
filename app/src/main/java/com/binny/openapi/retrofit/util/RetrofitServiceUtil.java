package com.binny.openapi.retrofit.util;


import com.binny.openapi.retrofit.api.IUserService;
import com.binny.openapi.retrofit.manager.RetrofitManagerTopApi;

/**
 * author  binny
 * date 5/9
 *
 */
public class RetrofitServiceUtil {

    public static IUserService getLoginService() {
        return RetrofitManagerTopApi.getInstance().createRetrofitService(IUserService.class);
    }
}
