package com.binny.openapi.mvp.ui.fragment.home.vpfragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

import com.binny.openapi.R;
import com.binny.openapi.mvp.bean.ArticleBean;
import com.binny.openapi.mvp.callback.DataCallback;
import com.binny.openapi.mvp.presenter.mine.ArticlePresenter;
import com.binny.openapi.mvp.ui.fragment.BaseFragment;
import com.binny.openapi.mvp.ui.fragment.home.viewholder.ArticleViewHolderHelper;
import com.binny.openapi.util.Utils;
import com.binny.openapi.util.UtilsLog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smart.holder.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author  binny
 * date 5/22
 */
public class ArticleFragment extends BaseFragment implements DataCallback<ArticleBean>, OnRefreshListener, OnLoadMoreListener {
    private ArticlePresenter mArticlePresenter;

    private ListView mListView;
    private List<ArticleBean> mArticleBeans = new ArrayList<>();
    private CommonAdapter mCommonAdapter;
    private RefreshLayout mRefreshLayout;

    private boolean mIsRefresh;
    private boolean mIsLoadMore;

    public ArticleFragment() {
        mArticlePresenter = new ArticlePresenter(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_home_article_fragment;
    }

    @Override
    protected void initView(final View view) {
        mListView = view.findViewById(R.id.article_list_view);
        mRefreshLayout = view.findViewById(R.id.article_refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setEnableLoadMore(false);
        mCommonAdapter = new CommonAdapter<>(getActivity(), mArticleBeans, R.layout.layout_home_article_lv_item, new ArticleViewHolderHelper());
        mListView.setAdapter(mCommonAdapter);
    }

    @Override
    public void onError(final String result) {
        mRefreshLayout.finishRefresh(false);
        mRefreshLayout.finishLoadMore(false);
    }

    @Override
    protected void bindData() {
        super.bindData();
        mArticlePresenter.getArticle();
    }


    @Override
    public void onSuccess(final ArticleBean articleBean) {
        if (mIsRefresh) {
            mIsRefresh = false;
            if (mArticleBeans.size()>0) {
                mArticleBeans.add(1, articleBean);
            }else {
                mArticleBeans.add(articleBean);
            }
            mRefreshLayout.finishRefresh();
        } else if (mIsLoadMore) {
            mIsLoadMore = false;
            mArticleBeans.add(articleBean);
            mRefreshLayout.finishLoadMore();
        } else {
            mArticleBeans.add(articleBean);
        }
        mCommonAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoading() {
        UtilsLog.i( "onLoading");
    }

    @Override
    public void onLoadDone() {
        UtilsLog.i( "onLoadDone");
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mIsRefresh = true;
        mArticlePresenter.getArticleRandom();

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mIsLoadMore = true;
        Utils.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mArticlePresenter.getArticleRandom();
            }
        }, 500);

    }
}
