package com.binny.openapi.retrofit.server;

import com.binny.openapi.mvp.bean.HistoryDayBean;
import com.binny.openapi.mvp.bean.HistoryDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by binny on 2018/5/30.
 * 聚合数据的接口
 */

public interface IJuheService {
    /**
     * @param key 你申请的APPKEY
     * @param date 日期 日期,格式:月/日 如:1/1,/10/1,12/12 如月或者日小于10,前面无需加0
     * @return 被观察者
     */
    @GET("todayOnhistory/queryEvent.php")
    Observable<HistoryDayBean> getHistoryDayDate(@Query("key") String key, @Query("date") String date);

    @GET("todayOnhistory/queryDetail.php")
    Observable<HistoryDetailBean> getDetailDate(@Query("key") String key, @Query("e_id") String id);
}
