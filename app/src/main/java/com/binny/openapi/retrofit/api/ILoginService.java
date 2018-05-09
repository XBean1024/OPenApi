package com.binny.openapi.retrofit.api;

import com.binny.openapi.mvp.bean.LoginBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author  binny
 * date 5/9
 */
public interface ILoginService {
    @GET("login")
    Observable<LoginBean> login(@Query("key")
                                        String key,
                                @Query("phone")
                                        String phone,
                                @Query("passwd")
                                        String passwd

    );
}
