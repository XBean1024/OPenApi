package com.binny.openapi.mvp.ui.fgmine;

import android.Manifest;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binny.openapi.R;
import com.binny.openapi.mvp.callback.OnPermissionCallback;
import com.binny.openapi.mvp.ui.base.BaseFragment;
import com.binny.openapi.util.FileUtils;
import com.binny.openapi.util.UtilsLog;
import com.binny.openapi.util.UtilsPerMission;
import com.jakewharton.rxbinding2.view.RxView;
import com.scwang.wave.MultiWaveHeader;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;



/**
 * author  binny
 * date 5/9
 */
public class MineFragment extends BaseFragment {
    private MultiWaveHeader waveHeader;
    private CircleImageView profileImage;
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
                        UtilsLog.e("请求失败");
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
                FileUtils.cleanApplicationData(getActivity(), "");
                String size = FileUtils.getTotalCacheSize(getActivity(), Environment.getExternalStorageDirectory()
                        .getAbsoluteFile() + "");
                tvCacheSize.setText(size);
            }
        });
    }

    @Override
    protected void initRefreshView(final View containerView) {

    }

    @Override
    protected void getData() {
        super.getData();
        String size = FileUtils.getTotalCacheSize(getActivity(), Environment.getExternalStorageDirectory()
                .getAbsoluteFile() + "");
        tvCacheSize.setText(size);
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
        String size = FileUtils.getTotalCacheSize(getActivity(), Environment.getExternalStorageDirectory()
                .getAbsoluteFile() + "");
        if (tvCacheSize != null) {
            tvCacheSize.setText(size);
        }
    }
}
