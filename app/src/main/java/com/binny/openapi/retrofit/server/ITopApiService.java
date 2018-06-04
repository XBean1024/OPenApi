package com.binny.openapi.retrofit.server;

import com.binny.openapi.mvp.bean.LoginBean;
import com.binny.openapi.mvp.bean.PictureBean;
import com.binny.openapi.mvp.bean.RegisterBean;
import com.binny.openapi.mvp.bean.VideoBean;

import java.io.File;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.binny.openapi.constant.Constant.TOP_API_URL_PICTURE;
import static com.binny.openapi.constant.Constant.TOP_API_URL_VIDEO;
import static com.binny.openapi.constant.Constant.TOP_API_USER_LOGIN;
import static com.binny.openapi.constant.Constant.TOP_API_USER_REGISTER;

/**
 * author  binny
 * date 5/9
 */
public interface ITopApiService {
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

    /*
   * 获取图片的接口
   * */
    @GET(TOP_API_URL_PICTURE)
    Observable<PictureBean> getPictures(@Query("page") int page);

    /*
    * 获取视频的接口
    * */
    @GET(TOP_API_URL_VIDEO)
    Observable<VideoBean> getVideos(@Query("type") String type,
                                    @Query("page") String page);
}
