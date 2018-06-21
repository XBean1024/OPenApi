package com.binny.openapi.retrofit.server;

import android.graphics.Bitmap;

import com.binny.openapi.bean.FuLiBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by binny on 2018/6/5.
 *
 */

public interface IGankFuLiService {
    @GET("data/福利/10/{page}")
    Observable<FuLiBean> getGankFuLi(@Path("page") int page);
}
