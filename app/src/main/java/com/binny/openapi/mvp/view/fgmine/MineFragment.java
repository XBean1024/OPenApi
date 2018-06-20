package com.binny.openapi.mvp.view.fgmine;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binny.openapi.R;
import com.binny.openapi.callback.OnPermissionCallback;
import com.binny.openapi.constant.ConstantParams;
import com.binny.openapi.mvp.view.base.AbsBaseFragment;
import com.binny.openapi.util.FileUtils;
import com.binny.openapi.util.UtilsLog;
import com.binny.openapi.util.UtilsPerMission;
import com.jakewharton.rxbinding2.view.RxView;
import com.scwang.wave.MultiWaveHeader;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;



/**
 * author  binny
 * date 5/9
 */
public class MineFragment extends AbsBaseFragment {
    private MultiWaveHeader waveHeader;
    private ImageView profileImage;
    private RelativeLayout rlClearCache;
    private TextView tvCacheSize;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-05-15 13:10:10 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        profileImage = view.findViewById(R.id.header);
        rlClearCache = view.findViewById(R.id.rl_clear_cache);
        tvCacheSize = view.findViewById(R.id.tv_cache_size);
        RelativeLayout textView = view.findViewById(R.id.test);
        RxView.clicks(textView).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                UtilsPerMission.getPermission(new OnPermissionCallback() {
                    @Override
                    public void onGranted() {
                        UtilsLog.i("请求成功");
                    }

                    @Override
                    public void onDeny() {
                        UtilsLog.e("请求权限失败");
                    }
                },getActivity(),textView, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        findViews(view);
        rlClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("敏感操作，谨慎选择");
                builder.setMessage("确定要删除所有缓存么，包括文字、picture、音乐和视频?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FileUtils.cleanApplicationData(getActivity(), ConstantParams.PATH_PICTURE);
                        String size = getSize();
                        tvCacheSize.setText(size);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            UtilsLog.i("取消删除缓存!");
                    }
                });
                builder.show();

            }
        });
    }

    @Override
    protected void initRefreshView(final View containerView) {

    }

    @Override
    protected void getData() {
        String size = getSize();
        tvCacheSize.setText(size);
    }

    private String getSize() {
        String size = FileUtils.getTotalCacheSize(getActivity(), ConstantParams.PATH_PICTURE);
        return size;
    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onLoadMore() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setCacheSize() {
        String size = getSize();
        if (tvCacheSize != null) {
            tvCacheSize.setText(size);
        }
    }
}
