package com.binny.openapi.mvp.view.fghome.juhe.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.text.TextUtils;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.JuheNewsBean;
import com.binny.openapi.mvp.view.activity.MainActivity;
import com.binny.openapi.mvp.view.activity.WebActivity;
import com.binny.openapi.util.UtilsLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by binny on 2018/6/8.
 */

public class JuheViewHolderHelper implements IViewHolderHelper<JuheViewHolder, JuheNewsBean.ResultBean.DataBean> {
    @Override
    public IViewHolder initItemViewHolder(JuheViewHolder viewHolder, View convertView) {
        viewHolder = new JuheViewHolder();
        viewHolder.mTitle = convertView.findViewById(R.id.juhe_news_title_tv);
        viewHolder.mAuthor = convertView.findViewById(R.id.juhe_news_author_tv);
        viewHolder.mDate = convertView.findViewById(R.id.juhe_news_date_tv);
        viewHolder.mImageView = convertView.findViewById(R.id.juhe_news_img_iv);
        viewHolder.mRelativeLayout = convertView.findViewById(R.id.item_rl);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<JuheNewsBean.ResultBean.DataBean> iBaseBeanList, JuheViewHolder viewHolder, int position) {
        String imgUrl = iBaseBeanList.get(position).getThumbnail_pic_s();
        if (TextUtils.isEmpty(imgUrl)) {
            imgUrl= iBaseBeanList.get(position).getThumbnail_pic_s02();
        }if (TextUtils.isEmpty(imgUrl)) {
            imgUrl= iBaseBeanList.get(position).getThumbnail_pic_s03();
        }
        Glide.with(context).load(imgUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, 30, 0,
                        RoundedCornersTransformation.CornerType.ALL))
                .crossFade()
                .into(viewHolder.mImageView);
        String content = iBaseBeanList.get(position).getAuthor_name();

        viewHolder.mAuthor.setText("来源：" + content);
        viewHolder.mDate.setText(iBaseBeanList.get(position).getDate());
        String title = iBaseBeanList.get(position).getTitle();
        viewHolder.mTitle.setText(title);
        String loadUrl = iBaseBeanList.get(position).getUrl();
        MainActivity activity = (MainActivity) context;
        viewHolder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, WebActivity.class);
                UtilsLog.i(loadUrl);
                intent.putExtra("loadUrl", loadUrl);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                intent.putExtra("adblock", true);
                activity.intoActivityWithAnimotion(intent);
            }
        });
    }



}
