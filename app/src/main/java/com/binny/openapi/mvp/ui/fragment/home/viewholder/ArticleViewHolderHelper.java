package com.binny.openapi.mvp.ui.fragment.home.viewholder;

import android.content.Context;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.mvp.bean.ArticleBean;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

/**
 * author  binny
 * date 5/22
 */
public class ArticleViewHolderHelper implements IViewHolderHelper<ArticleViewHolder, ArticleBean> {

    @Override
    public IViewHolder initItemViewHolder(ArticleViewHolder viewHolder, final View convertView) {
        viewHolder = new ArticleViewHolder();
        viewHolder.articleTime = convertView.findViewById(R.id.article_time);
        viewHolder.articleAuthor = convertView.findViewById(R.id.article_author);
        viewHolder.articleDigest = convertView.findViewById(R.id.article_digest);
        viewHolder.articleTitle = convertView.findViewById(R.id.article_title);
        viewHolder.articleCharacterCount = convertView.findViewById(R.id.article_character_count);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(final Context context, final List<ArticleBean> iBaseBeanList, final ArticleViewHolder viewHolder, final int position) {
        viewHolder.articleTime.setText(iBaseBeanList.get(position).getData().getDate().getCurr());
        viewHolder.articleAuthor.setText("---"+iBaseBeanList.get(position).getData().getAuthor()+"   ");
        viewHolder.articleDigest.setText("        "+iBaseBeanList.get(position).getData().getDigest());
        viewHolder.articleCharacterCount.setText("共"+iBaseBeanList.get(position).getData().getWc()+"字   ");
        viewHolder.articleTitle.setText("《" + iBaseBeanList.get(position).getData().getTitle() + "》");
    }
}
