package com.binny.openapi.mvp.presenter.mine;

import com.binny.openapi.mvp.bean.ArticleBean;
import com.binny.openapi.mvp.callback.DataCallback;
import com.binny.openapi.mvp.model.home.ArticleModel;

/**
 * author  binny
 * date 5/22
 */
public class ArticlePresenter {
    private ArticleModel mArticleModel;

    public ArticlePresenter(final DataCallback<ArticleBean> articleView) {
        mArticleModel = new ArticleModel(articleView);
    }

    public void getArticle() {
        mArticleModel.getArticle();
    }

    /**
     * 获取随机的一天的短文
     */
    public void getArticleRandom() {
        mArticleModel.getArticleRandom();
    }
    public void getArticleDay() {
        mArticleModel.getArticleDay();
    }
}
