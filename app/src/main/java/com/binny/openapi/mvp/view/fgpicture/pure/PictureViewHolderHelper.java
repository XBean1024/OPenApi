package com.binny.openapi.mvp.view.fgpicture.pure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.Toast;

import com.bean.xhttp.XHttp;
import com.bean.xhttp.callback.OnXHttpCallback;
import com.bean.xhttp.response.Response;
import com.binny.openapi.R;
import com.binny.openapi.bean.PictureBean;
import com.binny.openapi.glide.GlideCircleTransform;
import com.binny.openapi.glide.GlideRoundTransform;
import com.binny.openapi.util.BitmapUtils;
import com.binny.openapi.util.UtilsLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.binny.openapi.constant.ConstantParams.PICTURE_FILE_NAME;

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
        String url = iBaseBeanList.get(position).getUrl();

        Glide.with(context).load(url)
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(context, 90, 0,
                        RoundedCornersTransformation.CornerType.ALL))
                .transform(new GlideRoundTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)// 缓存所有尺寸的图片
                .into(viewHolder.mImageView);


        viewHolder.mImageView.setOnLongClickListener(v -> {
            XHttp.getInstance()
                    .get(url)
                    .setTag("bbb")
                    .setTimeout(5000)
                    .setOnXHttpCallback(new OnXHttpCallback() {
                        @Override
                        public void onSuccess(final Response response) {
                            BitmapUtils.savePicture(response.toBitmap(), PICTURE_FILE_NAME, System.currentTimeMillis() + url.substring(url.lastIndexOf("."), url.length()));
                            Toast.makeText(context, "保存成功！", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Exception ex, String errorCode) {
                            UtilsLog.e(ex.getMessage());
                        }
                    });

            return true;
        });
        viewHolder.mTextView.setText("第 " + position + " 张" + iBaseBeanList.get(position).getPublishedAt());
    }
}
