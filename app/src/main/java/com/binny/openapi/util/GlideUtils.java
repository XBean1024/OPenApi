package com.binny.openapi.util;

import android.content.Context;
import android.widget.ImageView;

import com.binny.openapi.glide.GlideRoundTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/21 18:46
 * @Description:
 */
public class GlideUtils {
    public static void loadImg(Context context, String picUrl, ImageView imageView){
        Glide.with(context).load(picUrl).crossFade()
                .bitmapTransform(new RoundedCornersTransformation(context, 120, 0,
                        RoundedCornersTransformation.CornerType.ALL))
                .transform(new GlideRoundTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)// 缓存所有尺寸的图片
                .into(imageView);
    }
}
