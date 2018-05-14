package com.binny.openapi.retrofit.api;

import com.binny.openapi.mvp.bean.PictureBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.binny.openapi.constant.Constant.URL_PICTURE;
import static com.binny.openapi.constant.Constant.URL_VIDEO;

/**
 * author  binny
 * date 5/14
 *
 * 数据类
 */
public interface IDataService {
    @GET(URL_PICTURE)
    Observable<PictureBean> getPictures(@Query("page") String page);

    @GET(URL_VIDEO)
    Observable<PictureBean> getVideos(@Query("type") String type,
                                      @Query("page") String page);

}
