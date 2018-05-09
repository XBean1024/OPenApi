package com.binny.openapi.module;

import com.binny.openapi.model.RegisterBean;
import com.binny.openapi.callback.RegisterCallback;
import com.binny.openapi.net.NetUtils;
import com.binny.openapi.callback.StringCallback;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;

import static com.binny.openapi.constant.Constant.APP_KEY;
import static com.binny.openapi.constant.WebApi.REGISTER_URL;

/**
 * author  binny
 * date 5/7
 */
public class RegisterModule implements IRegisterModule {

    @Override
    public void requestRegister(final RegisterCallback stringCallback, String phone, String passwd) {
        HttpParams params = new HttpParams();
        params.put("key", APP_KEY);
        params.put("phone", phone);
        params.put("passwd", passwd);
        NetUtils.string(REGISTER_URL, params, new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                RegisterBean bean = gson.fromJson(result,RegisterBean.class);
                result = bean.getMsg();
                int code = bean.getCode();
                stringCallback.onSuccess(result, String.valueOf(code));
            }

            @Override
            public void onFailure(String result) {
                Gson gson = new Gson();
                RegisterBean bean = gson.fromJson(result,RegisterBean.class);
                result = bean.getMsg();
                stringCallback.onFailure(result);
            }
        });
    }
}
