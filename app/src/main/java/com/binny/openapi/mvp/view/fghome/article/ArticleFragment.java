package com.binny.openapi.mvp.view.fghome.article;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.ArticleBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.presenter.mine.ArticlePresenter;
import com.binny.openapi.mvp.view.base.BaseFragment;
import com.binny.openapi.util.Utils;
import com.binny.openapi.util.UtilsLog;
import com.binny.openapi.widget.dialog.ArticleDetailDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.impl.RefreshHeaderWrapper;
import com.smart.holder.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author  binny
 * date 5/22
 */
public class ArticleFragment extends BaseFragment implements DataCallback<ArticleBean> {
    private ArticlePresenter mArticlePresenter;

    private List<ArticleBean> mArticleBeans = new ArrayList<>();
    private CommonAdapter mCommonAdapter;

    private ArticleAdapter mArticleAdapter;

    public ArticleFragment() {
        mArticlePresenter = new ArticlePresenter(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_layout_home_article;
    }

    @Override
    protected void initView(final View view) {
//        ListView listView = view.findViewById(R.id.article_list_view);
        RecyclerView listView = view.findViewById(R.id.article_list_view);
        listView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mArticleAdapter = new ArticleAdapter(R.layout.item_layout_home_article_lv, mArticleBeans);
        mArticleAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mArticleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<ArticleBean> dataBean = adapter.getData();
//                Toast.makeText(mActivity, ""+dataBean.get(position).getData().getAuthor(), Toast.LENGTH_SHORT).show();

//                String time = dataBean.get(0).getData().getDate().getCurr();

                final String author = dataBean.get(position).getData().getAuthor();

//                String digest = dataBean.get(0).getData().getDigest();

                final String count = "共" + dataBean.get(position).getData().getWc() + "字   ";

                final String title = "《" + dataBean.get(position).getData().getTitle() + "》";

                final String content = dataBean.get(position).getData().getContent();
                new ArticleDetailDialog(mActivity)
                        .setAuthorName(author)
                        .setCharacterCount(count)
                        .setContent(content)
                        .setTextTitle(title)
                        .show();
            }
        });
        listView.setAdapter(mArticleAdapter);

//        mCommonAdapter = new CommonAdapter<>(getActivity(), mArticleBeans, R.layout.item_layout_home_article_lv, new ArticleViewHolderHelper());
//        listView.setAdapter(mCommonAdapter);
    }

    @Override
    protected void initRefreshView(final View containerView) {
        mRefreshLayout = containerView.findViewById(R.id.article_refreshLayout);
        View imageView = LayoutInflater.from(mActivity).inflate(R.layout.header_layout,null);
        mRefreshLayout.setRefreshHeader(new RefreshHeaderWrapper(imageView));
    }

    @Override
    public void onError(final String result) {
        mRefreshLayout.finishRefresh(false);
        mRefreshLayout.finishLoadMore(false);
    }

    @Override
    protected void getData() {
        mArticlePresenter.getArticle();
    }


    @Override
    public void onSuccess(final ArticleBean articleBean) {
        if (mIsRefresh) {
            mIsRefresh = false;
            if (mArticleBeans.size() > 0) {
                mArticleBeans.add(1, articleBean);
            } else {
                mArticleBeans.add(articleBean);
            }
            mRefreshLayout.finishRefresh();
        } else if (mIsLoadMore) {
            mIsLoadMore = false;
            mArticleBeans.add(articleBean);
            mRefreshLayout.finishLoadMore();
        } else {

            UtilsLog.i(articleBean.toString());
            mArticleBeans.add(articleBean);
        }
//        mCommonAdapter.notifyDataSetChanged();
        mArticleAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoading() {
        UtilsLog.i("onLoading");
    }

    @Override
    public void onLoadDone() {
        UtilsLog.i("onLoadDone");
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
