package com.binny.openapi.retrofit.api.topapi;

import com.binny.openapi.mvp.bean.PictureBean;
import com.binny.openapi.mvp.bean.VideoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.binny.openapi.constant.Constant.TOP_API_URL_PICTURE;
import static com.binny.openapi.constant.Constant.TOP_API_URL_VIDEO;

/**
 * author  binny
 * date 5/14
 *
 * 数据类
 */
public interface IDataService {
    /*
    * 获取图片的接口
    * */
    @GET(TOP_API_URL_PICTURE)
    Observable<PictureBean> getPictures(@Query("page") String page);

    /*
    * 获取视频的接口
    * */
    @GET(TOP_API_URL_VIDEO)
    Observable<VideoBean> getVideos(@Query("type") String type,
                                    @Query("page") String page);
}
