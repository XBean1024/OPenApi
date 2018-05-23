package com.binny.openapi.mvp.callback;

import com.binny.openapi.mvp.bean.ArticleBean;
import com.binny.openapi.mvp.callback.base.OnBaseCallback;
import com.binny.openapi.mvp.ui.ILoadingView;
import com.binny.openapi.mvp.ui.activity.login.ILoginView;

/**
 * author  binny
 * date 5/22
 */
public interface OnArticleCallback extends OnBaseCallback<ArticleBean> ,ILoadingView{
}
