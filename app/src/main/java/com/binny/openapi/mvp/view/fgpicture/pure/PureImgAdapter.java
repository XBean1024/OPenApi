package com.binny.openapi.mvp.view.fgpicture.pure;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.binny.openapi.R;
import com.binny.openapi.bean.ArticleBean;
import com.binny.openapi.bean.PictureBean;
import com.binny.openapi.glide.GlideRoundTransform;
import com.binny.openapi.util.UtilsLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by binny on 2018/6/4.
 *
 */

public class PureImgAdapter extends BaseQuickAdapter<PictureBean.DataBean,BaseViewHolder> {


    public PureImgAdapter(int layoutResId, @Nullable List<PictureBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PictureBean.DataBean item) {
        String time = item.getCreatedAt();

        Glide.with(mContext).load(item.getUrl())
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(mContext, 190, 0,
                        RoundedCornersTransformation.CornerType.ALL))
                .transform(new GlideRoundTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.ALL)// 缓存所有尺寸的图片
                .into((ImageView) helper.getView(R.id.picture_item_img));
    }
}
