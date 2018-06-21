package com.binny.openapi.mvp.view.fgpicture.fuli;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.bean.xhttp.XHttp;
import com.bean.xhttp.callback.OnXHttpCallback;
import com.bean.xhttp.response.Response;
import com.binny.openapi.R;
import com.binny.openapi.bean.FuLiBean;
import com.binny.openapi.glide.GlideRoundTransform;
import com.binny.openapi.util.BitmapUtils;
import com.binny.openapi.util.GlideUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.binny.openapi.constant.ConstantParams.PICTURE_FILE_NAME;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/21 15:43
 * @Description:
 */
public class FuLiViewHolderHelper implements IViewHolderHelper<FuLiViewHolder, FuLiBean.ResultsBean> {
    @Override
    public IViewHolder initItemViewHolder(FuLiViewHolder viewHolder, View convertView) {
        viewHolder = new FuLiViewHolder();
        viewHolder.mImageView = convertView.findViewById(R.id.meizi_img);
//        viewHolder.mImageView = convertView.findViewById(R.id.fuli_img);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<FuLiBean.ResultsBean> iBaseBeanList, FuLiViewHolder viewHolder, int position) {

        String picUrl = iBaseBeanList.get(position).getUrl();

        GlideUtils.loadImg(context,picUrl,viewHolder.mImageView);
        viewHolder.mImageView.setOnLongClickListener(v -> {
            XHttp.getInstance()
                    .get(picUrl)
                    .setTag("bbb")
                    .setTimeout(5000)
                    .setOnXHttpCallback(new OnXHttpCallback() {
                        @Override
                        public void onSuccess(final Response response) {
                            BitmapUtils.savePicture(response.toBitmap(), PICTURE_FILE_NAME, System.currentTimeMillis() + picUrl.substring(picUrl.lastIndexOf("."), picUrl.length()));
                            Toast.makeText(context, "保存成功！", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Exception ex, String errorCode) {
                        }
                    });

            return true;
        });
    }
}
