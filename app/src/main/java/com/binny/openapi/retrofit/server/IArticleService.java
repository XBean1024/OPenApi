package com.binny.openapi.retrofit.server;

import com.binny.openapi.mvp.bean.ArticleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * author  binny
 * date 5/14
 * <p>
 * 数据类
 */
public interface IArticleService {

    /*
    * https://interface.meiriyiwen.com/article/day?dev=1&date=20170216
    * */

    /*
    * 获取 每日一文 接口 :当天的
    * */
    @GET("article/today")
    @Headers("User-Agent: Retrofit-App")
    Observable<ArticleBean> getArticleToday();

    /*
    * 获取 每日一文 接口 :随机的
    * */
    @GET("article/random")
    @Headers("User-Agent: Retrofit-App")
    Observable<ArticleBean> getArticleRandom();

    /*
    * 获取 每日一文 接口 :指定某一天
    * */
    @GET("article/day?dev=1")
    @Headers("User-Agent: Retrofit-App")
    Observable<ArticleBean> getArticleDay(@Query("date") int date);
}
