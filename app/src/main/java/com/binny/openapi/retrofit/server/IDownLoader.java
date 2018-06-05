package com.binny.openapi.retrofit.server;

import android.graphics.Bitmap;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by binny on 2018/6/5.
 *
 */

public interface IDownLoader {
    @GET()
    Observable<Bitmap> getBitmap();
}
