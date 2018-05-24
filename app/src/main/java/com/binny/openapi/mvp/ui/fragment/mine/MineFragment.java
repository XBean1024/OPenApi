package com.binny.openapi.mvp.ui.fragment.mine;

import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bean.logger.JJLogger;
import com.binny.openapi.R;
import com.binny.openapi.mvp.ui.fragment.BaseFragment;
import com.binny.openapi.util.FileUtils;
import com.orhanobut.logger.Logger;
import com.scwang.wave.MultiWaveHeader;

import de.hdodenhof.circleimageview.CircleImageView;


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
        waveHeader = (MultiWaveHeader) view.findViewById(R.id.waveHeader);
        profileImage = (CircleImageView) view.findViewById(R.id.header);
        rlClearCache = (RelativeLayout) view.findViewById(R.id.rl_clear_cache);
        tvCacheSize = (TextView) view.findViewById(R.id.tv_cache_size);
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
    protected void bindData() {
        super.bindData();
        String size = FileUtils.getTotalCacheSize(getActivity(), Environment.getExternalStorageDirectory()
                .getAbsoluteFile() + "");
        tvCacheSize.setText(size);
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
