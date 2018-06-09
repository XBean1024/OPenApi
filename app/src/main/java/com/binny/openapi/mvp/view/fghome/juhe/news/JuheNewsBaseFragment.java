package com.binny.openapi.mvp.view.fghome.juhe.news;

import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.JuheNewsBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.presenter.juhe.JuheNewsPresenter;
import com.binny.openapi.mvp.view.fghome.juhe.abs.AbsJuheBaseFragment;
import com.binny.openapi.util.UtilsLog;
import com.smart.holder.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binny on 2018/6/8.
 */

public class JuheNewsBaseFragment extends AbsJuheBaseFragment implements DataCallback<JuheNewsBean> {

    private JuheNewsPresenter mPresenter;

    protected String mType;
    private List<JuheNewsBean.ResultBean.DataBean> mDataBeanList = new ArrayList<>();

    @Override
    protected void initRefreshView(View containerView) {
        super.initRefreshView(containerView);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
    }

    @Override
    protected void initAdapter() {
        mCommonAdapter = new CommonAdapter(getActivity(), mDataBeanList, R.layout.item_layout_juhe_news, new JuheViewHolderHelper());
    }

    @Override
    protected void getData() {
        mPresenter = new JuheNewsPresenter();
        mPresenter.getNewsData(mType, this);
    }

    @Override
    public void onError(String result) {
        UtilsLog.e(result);
    }

    @Override
    public void onLoading() {
        UtilsLog.onLoading();
    }

    @Override
    public void onLoadDone() {
        UtilsLog.onLoadDone();

    }

    @Override
    public void onSuccess(JuheNewsBean juheNewsBean) {
        int size = juheNewsBean.getResult().getData().size();
        JuheNewsBean.ResultBean.DataBean dataBean;
        for (int i = 0; i < size; i++) {
            dataBean = new JuheNewsBean.ResultBean.DataBean();
            dataBean.setAuthor_name(juheNewsBean.getResult().getData().get(i).getAuthor_name());
            dataBean.setDate(juheNewsBean.getResult().getData().get(i).getDate());
            dataBean.setUrl(juheNewsBean.getResult().getData().get(i).getUrl());
            dataBean.setThumbnail_pic_s(juheNewsBean.getResult().getData().get(i).getThumbnail_pic_s());
            dataBean.setThumbnail_pic_s02(juheNewsBean.getResult().getData().get(i).getThumbnail_pic_s02());
            dataBean.setThumbnail_pic_s03(juheNewsBean.getResult().getData().get(i).getThumbnail_pic_s03());
            dataBean.setTitle(juheNewsBean.getResult().getData().get(i).getTitle());
            mDataBeanList.add(dataBean);
        }
        mCommonAdapter.notifyDataSetChanged();
    }
}
