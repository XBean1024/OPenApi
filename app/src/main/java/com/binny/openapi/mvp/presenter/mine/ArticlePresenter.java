package com.binny.openapi.mvp.presenter.mine;

import com.binny.openapi.mvp.model.mine.ArticleModel;
import com.binny.openapi.mvp.ui.fragment.home.vpfragment.IArticleView;

/**
 * author  binny
 * date 5/22
 */
public class ArticlePresenter {
    private ArticleModel mArticleModel;

    public ArticlePresenter(final IArticleView articleView) {
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
