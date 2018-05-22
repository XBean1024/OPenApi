package com.binny.openapi.mvp.ui.fragment.picture;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.mvp.ui.fragment.BaseFragment;

/**
 * author  binny
 * date 5/9
 */
public class PictureFragment extends BaseFragment {
    private RecyclerView picture_rv;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_picture;
    }

    @Override
    protected void initView(View view) {
        picture_rv = view.findViewById( R.id.picture_rv );
    }

    @Override
    protected void bindData() {
        super.bindData();
    }
}
