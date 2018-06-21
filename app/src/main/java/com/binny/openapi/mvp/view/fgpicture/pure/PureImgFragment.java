package com.binny.openapi.mvp.view.fgpicture.pure;

import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.PictureBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.presenter.Presenter;
import com.binny.openapi.mvp.view.base.AbsTopNavigationTabBaseFragment;
import com.binny.openapi.util.UtilsLog;

import java.util.ArrayList;
import java.util.List;


/**
 * author  binny
 * date 5/9
 * <p>
 * 纯图片 展示
 * {@link com.binny.openapi.constant.ConstantUrl#TOP_API_URL_PICTURE}
 */
public class PureImgFragment extends AbsTopNavigationTabBaseFragment implements DataCallback<PictureBean> {

    private List<PictureBean.DataBean> mPictureBeans = new ArrayList<>();

    private Presenter mPresenterPicture;
    private int mPage = 0;

    private PureImgAdapter mPureImgAdapter;
//    @Override
//    protected int getFragmentLayout() {
//        return R.layout.fragment_common_rv;
//    }
//
//    @Override
//    protected void initView(View view) {
//        RecyclerView recyclerView = view.findViewById(R.id.home_common_rv);
////        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
//        mPureImgAdapter = new PureImgAdapter(initItem(),initListBean());
//        recyclerView.setAdapter(mPureImgAdapter);
//    }



    @Override
    protected void afterInitView() {
        super.afterInitView();
        mPresenterPicture = new Presenter();

    }

    @Override
    protected void getData() {
        mPresenterPicture.getPureImgDate(mPage,this);
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        mPage++;
        mPresenterPicture.getPureImgDate(mPage,this);

    }



    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mPage++;
        mPresenterPicture.getPureImgDate(mPage,this);
    }


    @Override
    protected PictureViewHolderHelper initViewHolderHelper() {
        return new PictureViewHolderHelper();
    }

    @Override
    protected int initItem() {
        return R.layout.item_fragment_picture_lv;
    }

    @Override
    protected List initListBean() {
        return mPictureBeans;
    }

    @Override
    protected void initRefreshView(final View containerView) {
        mRefreshLayout = containerView.findViewById(R.id.common_refreshLayout);
    }


    @Override
    public void onError(final String result) {
        UtilsLog.e(result);
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
        UtilsLog.i(" 拉取几张图片 = "+mPictureBeans.size());
//        mPureImgAdapter.notifyDataSetChanged();
        mCommonAdapter.notifyDataSetChanged();
        mRefreshLayout.finishRefresh();
    }
}
