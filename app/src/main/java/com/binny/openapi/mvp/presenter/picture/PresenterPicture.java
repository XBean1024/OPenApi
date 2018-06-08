package com.binny.openapi.mvp.presenter.picture;

import com.binny.openapi.bean.PictureBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.model.picture.PictureModel;

/**
 * Created by binny on 2018/5/30.
 *
 * 获取图片
 */

public class PresenterPicture {
    private DataCallback<PictureBean> mCallbackl;
    public PresenterPicture(DataCallback<PictureBean> callbackl) {

        mCallbackl = callbackl;
    }

    public void getDate(int page) {
        new PictureModel().getData(page,mCallbackl);
    }
}
