package com.binny.openapi.net;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * author  binny
 * date 5/6
 */
public class NetUtils {
    public static void string(String url, HttpParams params, final com.binny.openapi.callback.StringCallback stringCallback){
        OkGo.<String>get(url)//
                .params(params)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //注意这里已经是在主线程了
                        String data = response.body();//这个就是返回来的结果
                        stringCallback.onSuccess(data);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        stringCallback.onFailure(response.body());
                    }
                });
    }
}
