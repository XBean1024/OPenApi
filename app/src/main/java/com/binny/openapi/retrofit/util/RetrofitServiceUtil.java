package com.binny.openapi.retrofit.util;


import com.binny.openapi.retrofit.api.ILoginService;
import com.binny.openapi.retrofit.api.IRegisterService;
import com.binny.openapi.retrofit.manager.RetrofitManagerTopApi;

/**
 * author  binny
 * date 5/9
 *
 */
public class RetrofitServiceUtil {

    public static IRegisterService getRegisterService() {
        return RetrofitManagerTopApi.getInstance().createRetrofitService(IRegisterService.class);
    }

    public static ILoginService getLoginService() {
        return RetrofitManagerTopApi.getInstance().createRetrofitService(ILoginService.class);
    }
}
