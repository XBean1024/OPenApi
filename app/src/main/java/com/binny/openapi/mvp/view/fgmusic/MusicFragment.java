package com.binny.openapi.mvp.view.fgmusic;

import android.view.View;
import android.widget.ImageView;

import com.binny.openapi.R;
import com.binny.openapi.mvp.view.base.BaseFragment;

/**
 * author  binny
 * date 5/9
 */
public class MusicFragment extends BaseFragment {
    private ImageView imageView;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_music;
    }

    @Override
    protected void initView(View view) {
        imageView = view.findViewById(R.id.top_img);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(imageView).init();

    }

    @Override
    protected void initRefreshView(final View containerView) {

    }

    @Override
    protected void getData() {

    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onLoadMore() {

    }
}
