package com.binny.openapi.mvp.view.fghome.article;

import android.support.annotation.Nullable;

import com.binny.openapi.R;
import com.binny.openapi.bean.ArticleBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by binny on 2018/6/4.
 *
 */

public class ArticleAdapter extends BaseQuickAdapter<ArticleBean,BaseViewHolder> {
    public ArticleAdapter(int layoutResId, @Nullable List<ArticleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {


        String time = item.getData().getDate().getCurr();

        final String author = item.getData().getAuthor();

        String digest = item.getData().getDigest();

        final String count = "共(" + item.getData().getWc() + ")字   ";

        final String title = "《" + item.getData().getTitle() + "》";
        int position = helper.getLayoutPosition();
        if (position == 0) {
            helper.setBackgroundRes(R.id.article_card_view,R.drawable.article_card_view_shape_current_day);
        } else if (position == 1) {
            helper.setBackgroundRes(R.id.article_card_view,R.drawable.article_card_view_shape_blue);
        } else {
            helper.setBackgroundRes(R.id.article_card_view,R.drawable.article_card_view_shape_gray);
        }
        helper.setText(R.id.article_time,time);
        helper.setText(R.id.article_author,author);
        helper.setText(R.id.article_digest,digest);
        helper.setText(R.id.article_title,time);
        helper.setText(R.id.article_character_count,count);
    }
}
