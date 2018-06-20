package com.binny.openapi.retrofit.server;

import com.binny.openapi.bean.HistoryDayBean;
import com.binny.openapi.bean.HistoryDetailBean;
import com.binny.openapi.bean.JuheNewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.binny.openapi.constant.ConstantUrl.JUHE_HISTORY_DETAIL_URL;
import static com.binny.openapi.constant.ConstantUrl.JUHE_HISTORY_URL;
import static com.binny.openapi.constant.ConstantUrl.JUHE_NEWS_URL;

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
    @GET(JUHE_HISTORY_URL)
    Observable<HistoryDayBean> getHistoryDayDate(@Query("key") String key, @Query("date") String date);

    @GET(JUHE_HISTORY_DETAIL_URL)
    Observable<HistoryDetailBean> getDetailDate(@Query("key") String key, @Query("e_id") String id);

    @GET(JUHE_NEWS_URL)
    Observable<JuheNewsBean> getNewsDate(@Query("key") String key, @Query("type") String type);


}
