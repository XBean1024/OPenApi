package com.binny.openapi.mvp.ui.fgpicture;

import android.view.View;
import android.widget.ListView;

import com.binny.openapi.R;
import com.binny.openapi.mvp.bean.PictureBean;
import com.binny.openapi.mvp.callback.DataCallback;
import com.binny.openapi.mvp.presenter.picture.PresenterPicture;
import com.binny.openapi.mvp.ui.base.BaseFragment;
import com.binny.openapi.util.UtilsLog;
import com.smart.holder.CommonAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * author  binny
 * date 5/9
 */
public class PictureFragment extends BaseFragment implements DataCallback<PictureBean> {

    private ListView mListView;
    private CommonAdapter mAdapter;
    private List<PictureBean.DataBean> mPictureBeans = new ArrayList<>();

    private PresenterPicture mPresenterPicture;
    private int mPage = 0;

    @Override
    protected void getData() {
        super.getData();
        mPresenterPicture.getDate(mPage);
    }

    @Override
    protected void onRefresh() {
        UtilsLog.i("下啦刷新。。。。");
        mPage++;
        mPresenterPicture.getDate(mPage);
    }

    @Override
    protected void onLoadMore() {
        UtilsLog.i("上啦刷新。。。。");
        mPage++;
        mPresenterPicture.getDate(mPage);
    }

    @Override
    protected void initView(View view) {
        mListView = view.findViewById(R.id.picture_lv);
        mAdapter = new CommonAdapter(getActivity(), mPictureBeans, R.layout.item_layout_picture_lv, new PictureViewHolderHelper());
        mPresenterPicture = new PresenterPicture(this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void initRefreshView(final View containerView) {
        mRefreshLayout = containerView.findViewById(R.id.picture_refreshLayout);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_picture;
    }

    @Override
    public void onError(final String result) {
        mRefreshLayout.finishRefresh(false);
        mRefreshLayout.finishLoadMore(false);
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
    public void onSuccess(final PictureBean pictureBean) {
        int size = pictureBean.getData().size();
        PictureBean.DataBean dataBean;
        UtilsLog.i(" 拉取几张图片 = "+size);
        for (int i = 0; i < size; i++) {
            dataBean = pictureBean.getData().get(i);
            if (mIsRefresh) {
                mPictureBeans.add(0, dataBean);
            } else {
                mPictureBeans.add(dataBean);
            }
        }
        mIsRefresh = false;
        mIsLoadMore = false;
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.finishRefresh();
    }
}
