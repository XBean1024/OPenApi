package com.binny.openapi.retrofit.server.topapi.user;

import com.binny.openapi.mvp.bean.LoginBean;
import com.binny.openapi.mvp.bean.RegisterBean;

import java.io.File;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.binny.openapi.constant.Constant.TOP_API_USER_LOGIN;
import static com.binny.openapi.constant.Constant.TOP_API_USER_REGISTER;

/**
 * author  binny
 * date 5/9
 */
public interface ITopUserService {
    @GET(TOP_API_USER_LOGIN)
    Observable<LoginBean> login(@Query("key")
                                        String key,
                                @Query("phone")
                                        String phone,
                                @Query("passwd")
                                        String passwd

    );
    @GET(TOP_API_USER_REGISTER)
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
