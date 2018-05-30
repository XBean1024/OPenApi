package com.binny.openapi.mvp.ui.fghome.vpfragment;

import android.view.View;
import android.widget.ListView;

import com.binny.openapi.R;
import com.binny.openapi.mvp.bean.ArticleBean;
import com.binny.openapi.mvp.callback.DataCallback;
import com.binny.openapi.mvp.presenter.mine.ArticlePresenter;
import com.binny.openapi.mvp.ui.base.BaseFragment;
import com.binny.openapi.mvp.ui.fghome.viewholder.ArticleViewHolderHelper;
import com.binny.openapi.util.Utils;
import com.binny.openapi.util.UtilsLog;
import com.smart.holder.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author  binny
 * date 5/22
 */
public class ArticleFragment extends BaseFragment implements DataCallback<ArticleBean> {
    private ArticlePresenter mArticlePresenter;

    private ListView mListView;
    private List<ArticleBean> mArticleBeans = new ArrayList<>();
    private CommonAdapter mCommonAdapter;


    public ArticleFragment() {
        mArticlePresenter = new ArticlePresenter(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_layout_home_article;
    }

    @Override
    protected void initView(final View view) {
        mListView = view.findViewById(R.id.article_list_view);
        mCommonAdapter = new CommonAdapter<>(getActivity(), mArticleBeans, R.layout.item_layout_home_article_lv, new ArticleViewHolderHelper());
        mListView.setAdapter(mCommonAdapter);
    }

    @Override
    protected void initRefreshView(final View containerView) {
        mRefreshLayout = containerView.findViewById(R.id.article_refreshLayout);
    }

    @Override
    public void onError(final String result) {
        mRefreshLayout.finishRefresh(false);
        mRefreshLayout.finishLoadMore(false);
    }

    @Override
    protected void getData() {
        super.getData();
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
    protected void onRefresh() {
        mArticlePresenter.getArticleRandom();
    }


    @Override
    protected void onLoadMore() {
        Utils.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mArticlePresenter.getArticleRandom();
            }
        }, 500);

    }
}
