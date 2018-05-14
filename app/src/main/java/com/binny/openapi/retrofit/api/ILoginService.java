package com.binny.openapi.retrofit.api;

import com.binny.openapi.mvp.bean.LoginBean;
import com.binny.openapi.mvp.bean.RegisterBean;

import java.io.File;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

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
    @GET("createUser")
    Observable<RegisterBean> register(@Query("key")
                                              String ksy,
                                      @Query("name")
                                              String name,
                                      @Query("phone")
                                              String phone,
                                      @Query("passwd")
                                              String passwd,
                                      @Query("text")
                                              String text,
                                      @Query("other2")
                                              String other,
                                      @Query("other")
                                              String other1,
                                      @Query("image")
                                              File imageFile
    );
}
