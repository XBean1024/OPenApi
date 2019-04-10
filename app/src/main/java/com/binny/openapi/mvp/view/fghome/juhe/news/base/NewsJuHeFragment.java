package com.binny.openapi.mvp.view.fghome.juhe.news.base;

import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.JuheNewsBean;
import com.binny.openapi.cache.DiskLruCacheHelper;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.presenter.Presenter;
import com.binny.openapi.mvp.view.base.AbsTopNavigationTabBaseFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheViewHolderHelper;
import com.binny.openapi.util.UtilsLog;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by binny on 2018/6/8.
 *
 * 聚合数据
 */

public class NewsJuHeFragment extends AbsTopNavigationTabBaseFragment implements DataCallback<JuheNewsBean> {

    private Presenter mPresenter;

    protected String mType;
    private ArrayList<JuheNewsBean.ResultBean.DataBean> mDataBeanList = new ArrayList<>();

    @Override
    protected void initRefreshView(View containerView) {
        super.initRefreshView(containerView);
        mRefreshLayout.setEnableLoadMore(false);
//        mRefreshLayout.setEnableRefresh(false);

    }

    @Override
    protected IViewHolderHelper initViewHolderHelper() {
        mPresenter = new Presenter();
        return new JuheViewHolderHelper();
    }

    @Override
    protected int initItem() {
        return R.layout.item_fragment_juhe_news;
    }

    @Override
    protected ArrayList<JuheNewsBean.ResultBean.DataBean> initListBean() {
        return mDataBeanList;
    }


    @Override
    protected void getData() {
        //优先加载本地数据
        if (loadLocal()) {
            return;
        }

        //从网络上加载数据
        mPresenter.getJuheNewsData(mType, this);
    }


    @Override
    public void onError(String result) {
        UtilsLog.e(result);
        mRefreshLayout.finishRefresh(false);
        mRefreshLayout.finishLoadMore(false);
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
        } catch (IOException | NullPointerException e) {
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
            mDataBeanList.add(0, dataBean);
        }
        mCommonAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        mPresenter.getJuheNewsData(mType, this);
    }
}
