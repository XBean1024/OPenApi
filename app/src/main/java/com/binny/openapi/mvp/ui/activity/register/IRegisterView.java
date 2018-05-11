package com.binny.openapi.mvp.ui.activity.register;

import com.binny.openapi.mvp.bean.RegisterBean;
import com.binny.openapi.mvp.callback.base.BaseCallback;
import com.binny.openapi.mvp.callback.base.ErrorCallcabk;
import com.binny.openapi.mvp.ui.ILoadingView;

/**
 * author  binny
 * date 5/7
 */
public interface IRegisterView extends BaseCallback<RegisterBean>,ILoadingView {

}
