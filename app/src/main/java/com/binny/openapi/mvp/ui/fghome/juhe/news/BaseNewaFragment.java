package com.binny.openapi.mvp.ui.fghome.juhe.news;

import android.view.View;

import com.binny.openapi.mvp.bean.JuheNewsBean;
import com.binny.openapi.mvp.callback.DataCallback;
import com.binny.openapi.mvp.ui.fghome.juhe.JuheBaseFragment;

/**
 * Created by binny on 2018/6/8.
 */

public class BaseNewaFragment extends JuheBaseFragment implements DataCallback<JuheNewsBean>{

    @Override
    protected void initRefreshView(View containerView) {
        super.initRefreshView(containerView);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void getData() {

    }

    @Override
    public void onError(String result) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadDone() {

    }

    @Override
    public void onSuccess(JuheNewsBean juheNewsBean) {

    }
}
