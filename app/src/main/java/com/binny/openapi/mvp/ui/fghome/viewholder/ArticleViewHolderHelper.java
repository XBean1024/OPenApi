package com.binny.openapi.mvp.ui.fghome.viewholder;

import android.content.Context;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.mvp.bean.ArticleBean;
import com.binny.openapi.widget.dialog.ArticleDetailDialog;
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
        viewHolder.mArticleTime = convertView.findViewById(R.id.article_time);
        viewHolder.mArticleAuthor = convertView.findViewById(R.id.article_author);
        viewHolder.mArticleDigest = convertView.findViewById(R.id.article_digest);
        viewHolder.mArticleTitle = convertView.findViewById(R.id.article_title);
        viewHolder.mArticleCharacterCount = convertView.findViewById(R.id.article_character_count);
        viewHolder.mArticleCardView = convertView.findViewById(R.id.article_card_view);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(final Context context, final List<ArticleBean> iBaseBeanList, final ArticleViewHolder viewHolder, final int position) {
        if (position == 0) {
            viewHolder.mArticleCardView.setBackground(context.getResources().getDrawable(R.drawable.article_card_view_shape_current_day));
        } else if (position == 1) {
            viewHolder.mArticleCardView.setBackground(context.getResources().getDrawable(R.drawable.article_card_view_shape_blue));
        } else {
            viewHolder.mArticleCardView.setBackground(context.getResources().getDrawable(R.drawable.article_card_view_shape_gray));

        }
        String time = iBaseBeanList.get(position).getData().getDate().getCurr();
        viewHolder.mArticleTime.setText(time);
        final String author = iBaseBeanList.get(position).getData().getAuthor();
        viewHolder.mArticleAuthor.setText("---" + author + "   ");
        String digest = iBaseBeanList.get(position).getData().getDigest();
        viewHolder.mArticleDigest.setText("        " + digest);
        final String count = "共" + iBaseBeanList.get(position).getData().getWc() + "字   ";
        viewHolder.mArticleCharacterCount.setText(count);
        final String title = "《" + iBaseBeanList.get(position).getData().getTitle() + "》";
        viewHolder.mArticleTitle.setText(title);
        final String content = iBaseBeanList.get(position).getData().getContent();
        viewHolder.mArticleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ArticleDetailDialog(context)
                        .setAuthorName(author)
                        .setCharacterCount(count)
                        .setContent(content)
                        .setTextTitle(title)
                        .show();
            }
        });
    }
}
