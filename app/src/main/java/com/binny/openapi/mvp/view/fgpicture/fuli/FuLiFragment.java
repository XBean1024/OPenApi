package com.binny.openapi.mvp.view.fgpicture.fuli;

import android.widget.Toast;

import com.bean.xhttp.XHttp;
import com.bean.xhttp.callback.OnXHttpCallback;
import com.bean.xhttp.response.Response;
import com.binny.openapi.R;
import com.binny.openapi.bean.FuLiBean;
import com.binny.openapi.mvp.view.base.AbsTopNavigationTabBaseFragment;
import com.binny.openapi.util.URLEncoderURI;
import com.binny.openapi.util.UtilsLog;
import com.google.gson.Gson;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/21 15:42
 * @Description:
 */
public class FuLiFragment extends AbsTopNavigationTabBaseFragment implements OnXHttpCallback {
    private List<FuLiBean.ResultsBean> mResultsBeans = new ArrayList<>();
    private String num="10";
    private int page = 1;

    @Override
    protected IViewHolderHelper initViewHolderHelper() {
        return new FuLiViewHolderHelper();
    }

    @Override
    protected int initItem() {
        return R.layout.item_fragment_fuli;
    }

    @Override
    protected List initListBean() {
        return mResultsBeans;
    }

    @Override
    protected void getData() {

    }



    @Override
    public void onSuccess(Response response) {
        String json = response.toString();
        UtilsLog.json(json);
        Gson gson = new Gson();
        FuLiBean fuLiBean = gson.fromJson(json, FuLiBean.class);
        if (fuLiBean.isError()) {
            Toast.makeText(mActivity, "0000", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mIsRefresh) {
            mIsRefresh =false;
            mResultsBeans.addAll(0,fuLiBean.getResults());
        }else {
            mResultsBeans.addAll(fuLiBean.getResults());
        }
        mCommonAdapter.notifyDataSetChanged();
        mRefreshLayout.finishRefresh();

    }

    @Override
    public void onFailure(Exception ex, String errorCode) {
        Toast.makeText(mActivity, "1111", Toast.LENGTH_SHORT).show();
        UtilsLog.e(ex.getMessage());
        mRefreshLayout.finishRefresh();

    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        page++;
    }
}
