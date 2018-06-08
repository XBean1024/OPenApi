package com.binny.openapi.mvp.presenter.juhe;

import com.binny.openapi.bean.JuheNewsBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.model.juhe.JuheNewsModel;

/**
 * Created by binny on 2018/6/8.
 */

public class JuheNewsPresenter {
    public void getNewsData(String type,DataCallback<JuheNewsBean> callback){
        new JuheNewsModel().getData(type,callback);
    }
}
