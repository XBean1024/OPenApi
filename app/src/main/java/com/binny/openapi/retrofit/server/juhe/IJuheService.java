package com.binny.openapi.retrofit.server.juhe;

import com.binny.openapi.mvp.bean.HistoryDayBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by binny on 2018/5/30.
 */

public interface IJuheService {
    @GET("japi/toh")
    Observable<HistoryDayBean> getHistoryDayDate(@Query("key") String key, @Query("v") String v, @Query("month") int month, @Query("day") int day);
}
