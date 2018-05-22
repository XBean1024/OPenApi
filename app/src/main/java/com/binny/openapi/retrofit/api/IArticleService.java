package com.binny.openapi.retrofit.api;

import com.binny.openapi.mvp.bean.ArticleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
    Observable<ArticleBean> getArticleToday();

    /*
    * 获取 每日一文 接口 :随机的
    * */
    @GET("article/random")
    Observable<ArticleBean> getArticleRandom();

    /*
    * 获取 每日一文 接口 :指定某一天
    * */
    @GET("article/day?dev=1")
    Observable<ArticleBean> getArticleDay(@Query("date") int date);
}
