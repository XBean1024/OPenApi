package com.binny.openapi.mvp.view.fghome.juhe.news;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.JuheNewsBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

/**
 * Created by binny on 2018/6/8.
 *
 */

public class JuheViewHolderHelper implements IViewHolderHelper <JuheViewHolder,JuheNewsBean.ResultBean.DataBean>{
    @Override
    public IViewHolder initItemViewHolder(JuheViewHolder viewHolder, View convertView) {
        viewHolder = new JuheViewHolder();
        viewHolder.mTitle = convertView.findViewById(R.id.juhe_news_title_tv);
        viewHolder.mAuthor = convertView.findViewById(R.id.juhe_news_author_tv);
        viewHolder.mDate = convertView.findViewById(R.id.juhe_news_date_tv);
        viewHolder.mImageView = convertView.findViewById(R.id.juhe_news_img_iv);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<JuheNewsBean.ResultBean.DataBean> iBaseBeanList, JuheViewHolder viewHolder, int position) {
        String url = iBaseBeanList.get(position).getThumbnail_pic_s();
        if (TextUtils.isEmpty(url)) {
            url= iBaseBeanList.get(position).getThumbnail_pic_s02();
        }if (TextUtils.isEmpty(url)) {
            url= iBaseBeanList.get(position).getThumbnail_pic_s03();
        }
        Glide.with(context).load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)// 缓存所有尺寸的图片
                .thumbnail(0.1f)//先加载原图大小的十分之一
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                        viewHolder.mImageView.setImageBitmap(resource);
                    }
                });
        viewHolder.mAuthor.setText(iBaseBeanList.get(position).getAuthor_name());
        viewHolder.mDate.setText(iBaseBeanList.get(position).getDate());
        viewHolder.mTitle.setText(iBaseBeanList.get(position).getTitle());
    }


}
