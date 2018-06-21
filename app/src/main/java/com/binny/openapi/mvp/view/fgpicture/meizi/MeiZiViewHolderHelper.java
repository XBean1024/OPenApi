package com.binny.openapi.mvp.view.fgpicture.meizi;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.bean.xhttp.XHttp;
import com.bean.xhttp.callback.OnXHttpCallback;
import com.bean.xhttp.response.Response;
import com.binny.openapi.R;
import com.binny.openapi.bean.MeiZiTuBean;
import com.binny.openapi.mvp.view.activity.MainActivity;
import com.binny.openapi.mvp.view.activity.WebActivity;
import com.binny.openapi.util.BitmapUtils;
import com.binny.openapi.util.UtilsLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

import static com.binny.openapi.constant.ConstantParams.PICTURE_FILE_NAME;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/20 16:19
 * @Description: 妹子图
 */
public class MeiZiViewHolderHelper implements IViewHolderHelper<MeiZiViewHolder, MeiZiTuBean.ShowapiResBodyBean.NewslistBean> {
    @Override
    public IViewHolder initItemViewHolder(MeiZiViewHolder viewHolder, View convertView) {
        viewHolder = new MeiZiViewHolder();
        viewHolder.mImageView = convertView.findViewById(R.id.meizi_img);
        viewHolder.mDate = convertView.findViewById(R.id.meizi_create_time);
        viewHolder.mTitle = convertView.findViewById(R.id.meizi_title);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<MeiZiTuBean.ShowapiResBodyBean.NewslistBean> iBaseBeanList, MeiZiViewHolder viewHolder, int position) {
        viewHolder.mTitle.setText(iBaseBeanList.get(position).getTitle());
        viewHolder.mDate.setText(iBaseBeanList.get(position).getCtime());
        String picUrl = iBaseBeanList.get(position).getPicUrl();
        String url = iBaseBeanList.get(position).getUrl();

        Glide.with(context).load(picUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)// 缓存所有尺寸的图片
//                .placeholder(R.mipmap.place_holder)
                .into(viewHolder.mImageView);

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
//        MainActivity activity = (MainActivity) context;
//        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity, WebActivity.class);
//                UtilsLog.i(picUrl);
//                intent.putExtra("loadUrl", url);
//                intent.putExtra("adblock", false);
//                intent.putExtra("chaeset", "gb2312");
//                activity.intoActivityWithAnimotion(intent);
//            }
//        });
    }
}
