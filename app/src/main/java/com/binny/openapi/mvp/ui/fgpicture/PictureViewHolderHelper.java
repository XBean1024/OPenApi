package com.binny.openapi.mvp.ui.fgpicture;

import android.content.Context;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.mvp.bean.PictureBean;
import com.bumptech.glide.Glide;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

/**
 * author  binny
 * date 5/30
 */
public class PictureViewHolderHelper implements IViewHolderHelper<PictureViewHolder, PictureBean.DataBean> {


    @Override
    public IViewHolder initItemViewHolder(PictureViewHolder viewHolder, final View convertView) {
        viewHolder = new PictureViewHolder();
        viewHolder.mTextView = convertView.findViewById(R.id.picture_item_title_tv);
        viewHolder.mImageView = convertView.findViewById(R.id.picture_item_img);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(final Context context, final List<PictureBean.DataBean> iBaseBeanList, final PictureViewHolder viewHolder, final int position) {
        Glide.with(context).load(iBaseBeanList.get(position).getUrl()).into(viewHolder.mImageView);
        viewHolder.mTextView.setText("第 " + position + " 张" + iBaseBeanList.get(position).getPublishedAt());
    }
}
