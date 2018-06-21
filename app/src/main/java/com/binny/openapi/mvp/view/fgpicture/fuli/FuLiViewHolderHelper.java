package com.binny.openapi.mvp.view.fgpicture.fuli;

import android.content.Context;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.FuLiBean;
import com.bumptech.glide.Glide;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/21 15:43
 * @Description:
 */
public class FuLiViewHolderHelper implements IViewHolderHelper<FuLiViewHolder,FuLiBean.ResultsBean> {
    @Override
    public IViewHolder initItemViewHolder(FuLiViewHolder viewHolder, View convertView) {
        viewHolder = new FuLiViewHolder();
        viewHolder.mImageView = convertView.findViewById(R.id.fuli_img);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<FuLiBean.ResultsBean> iBaseBeanList, FuLiViewHolder viewHolder, int position) {
        Glide.with(context).load(iBaseBeanList.get(position).getUrl())
                .into(viewHolder.mImageView);
    }
}
