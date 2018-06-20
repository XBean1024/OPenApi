package com.binny.openapi.mvp.view.fgpicture.meizi;

import android.content.Context;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.MeiZiTuBean;
import com.binny.openapi.util.UtilsLog;
import com.bumptech.glide.Glide;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/20 16:19
 * @Description: 妹子图
 */
public class MeiZiViewHolderHelper implements IViewHolderHelper <MeiZiViewHolder,MeiZiTuBean.ShowapiResBodyBean.NewslistBean>{
    @Override
    public IViewHolder initItemViewHolder(MeiZiViewHolder viewHolder, View convertView) {
        viewHolder = new MeiZiViewHolder();
        viewHolder.mImageView = convertView.findViewById(R.id.meizi_img);
        viewHolder.mDate = convertView.findViewById(R.id.meizi_create_time);
        viewHolder.mTitle = convertView.findViewById(R.id.meizi_title);
        UtilsLog.i("初始化 item");
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<MeiZiTuBean.ShowapiResBodyBean.NewslistBean> iBaseBeanList, MeiZiViewHolder viewHolder, int position) {
        UtilsLog.i("绑定 item");
        viewHolder.mTitle.setText(iBaseBeanList.get(position).getTitle());
        viewHolder.mDate.setText(iBaseBeanList.get(position).getCtime());
        Glide.with(context).load(iBaseBeanList.get(position).getUrl()).asBitmap().into(viewHolder.mImageView);
    }
}
