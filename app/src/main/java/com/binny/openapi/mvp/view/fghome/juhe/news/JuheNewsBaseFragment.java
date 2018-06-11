package com.binny.openapi.mvp.view.fghome.juhe.news;

import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.JuheNewsBean;
import com.binny.openapi.cache.DiskLruCacheHelper;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.presenter.juhe.JuheNewsPresenter;
import com.binny.openapi.mvp.view.fghome.juhe.abs.AbsJuheBaseFragment;
import com.binny.openapi.util.UtilsLog;
import com.smart.holder.CommonAdapter;

import java.io.IOException;
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
//        mRefreshLayout.setEnableRefresh(false);

    }

    @Override
    protected void initAdapter() {
        mPresenter = new JuheNewsPresenter();
        mCommonAdapter = new CommonAdapter(getActivity(), mDataBeanList, R.layout.item_layout_juhe_news, new JuheViewHolderHelper());
    }

    @Override
    protected void getData() {
        if (loadLocal()) {
            return;
        }
        loadNet();
    }

    /**
     * 从网络上加载数据
     */
    @Override
    protected void loadNet() {
        UtilsLog.i("loadNet");
        mPresenter.getNewsData(mType, this);
    }

    @Override
    public void onError(String result) {
        UtilsLog.e(result);
        loadLocal();
    }

    /**
     * @return {@code false 加载本地数据失败}
     */
    private boolean loadLocal() {
        UtilsLog.i("取本地");

        JuheNewsBean.ResultBean resultBean = null;
        try {
            resultBean = new DiskLruCacheHelper(getActivity(), "juhenews").getSerializable("resultBean" + this.mType);
            int size = resultBean.getData().size();
            JuheNewsBean.ResultBean.DataBean dataBean = null;
            for (int i = 0; i < size; i++) {
                dataBean = new JuheNewsBean.ResultBean.DataBean();
                dataBean.setAuthor_name(resultBean.getData().get(i).getAuthor_name());
                dataBean.setDate(resultBean.getData().get(i).getDate());
                dataBean.setUrl(resultBean.getData().get(i).getUrl());
                dataBean.setThumbnail_pic_s(resultBean.getData().get(i).getThumbnail_pic_s());
                dataBean.setThumbnail_pic_s02(resultBean.getData().get(i).getThumbnail_pic_s02());
                dataBean.setThumbnail_pic_s03(resultBean.getData().get(i).getThumbnail_pic_s03());
                dataBean.setTitle(resultBean.getData().get(i).getTitle());
                mDataBeanList.add(dataBean);
            }
            mCommonAdapter.notifyDataSetChanged();
            return true;
        } catch (IOException |NullPointerException  e) {
            UtilsLog.e(e.getMessage());
            return false;
        }
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
        mRefreshLayout.finishRefresh();
        JuheNewsBean.ResultBean resultBean;
        resultBean = juheNewsBean.getResult();
        if (resultBean == null) {
            loadLocal();
            return;
        }

        try {
            new DiskLruCacheHelper(getActivity(), "juhenews").putSerializableBean("resultBean" + this.mType, resultBean);
        } catch (IOException e) {
            UtilsLog.e(e.getMessage());
        }

        int size = juheNewsBean.getResult().getData().size();
        JuheNewsBean.ResultBean.DataBean dataBean = null;
        for (int i = 0; i < size; i++) {
            dataBean = new JuheNewsBean.ResultBean.DataBean();
            dataBean.setAuthor_name(resultBean.getData().get(i).getAuthor_name());
            dataBean.setDate(resultBean.getData().get(i).getDate());
            dataBean.setUrl(resultBean.getData().get(i).getUrl());
            dataBean.setThumbnail_pic_s(resultBean.getData().get(i).getThumbnail_pic_s());
            dataBean.setThumbnail_pic_s02(resultBean.getData().get(i).getThumbnail_pic_s02());
            dataBean.setThumbnail_pic_s03(resultBean.getData().get(i).getThumbnail_pic_s03());
            dataBean.setTitle(resultBean.getData().get(i).getTitle());
            mDataBeanList.add(0,dataBean);
        }
        mCommonAdapter.notifyDataSetChanged();

    }
}
