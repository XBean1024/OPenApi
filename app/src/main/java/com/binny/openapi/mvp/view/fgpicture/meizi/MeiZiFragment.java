package com.binny.openapi.mvp.view.fgpicture.meizi;

import android.view.View;
import android.widget.GridView;

import com.bean.xhttp.XHttp;
import com.bean.xhttp.callback.OnXHttpCallback;
import com.bean.xhttp.response.Response;
import com.binny.openapi.R;
import com.binny.openapi.bean.MeiZiTuBean;
import com.binny.openapi.mvp.view.base.AbsTopNavigationTabBaseFragment;
import com.binny.openapi.util.UtilsLog;
import com.google.gson.Gson;
import com.smart.holder.CommonAdapter;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/20 16:09
 * @Description:
 */
public class MeiZiFragment extends AbsTopNavigationTabBaseFragment implements OnXHttpCallback {

    List<MeiZiTuBean.ShowapiResBodyBean.NewslistBean> mNewslistBeans = new ArrayList<>();
    private int mPage =1;

    private final String url = "http://route.showapi.com/197-1";
    private String num = "6";

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_common_grid;
    }

    @Override
    protected void initView(View view) {
        GridView l = view.findViewById(R.id.home_common_lv);
        mCommonAdapter = new CommonAdapter(getActivity(),initListBean(), initItem(),initViewHolderHelper());
        l.setAdapter(mCommonAdapter);
    }
    @Override
    protected IViewHolderHelper initViewHolderHelper() {
        return new MeiZiViewHolderHelper();
    }

    @Override
    protected int initItem() {
        return R.layout.item_fragment_meizi;
    }

    @Override
    protected List initListBean() {
        return mNewslistBeans;
    }

    @Override
    protected void getData() {
        //加载数据  缓存中 或者 网络
       getImgData();
    }

    @Override
    public void onSuccess(Response response) {
        String json = response.toString();
        UtilsLog.json(json);
        Gson gson = new Gson();
        MeiZiTuBean meiZiTuBean = gson.fromJson(json, MeiZiTuBean.class);
        if (meiZiTuBean.getShowapi_res_body()==null||meiZiTuBean.getShowapi_res_body().getCode()!=200) {
            UtilsLog.e(meiZiTuBean.getShowapi_res_error());
            mRefreshLayout.finishLoadMore();
            mRefreshLayout.finishRefresh();
            return;
        }
        List<MeiZiTuBean.ShowapiResBodyBean.NewslistBean> newslistBeans = meiZiTuBean.getShowapi_res_body().getNewslist();
        if (mIsRefresh) {
            mIsRefresh =false;
            mNewslistBeans.addAll(0,newslistBeans);
        }else {
            mNewslistBeans.addAll(newslistBeans);
        }
        mCommonAdapter.notifyDataSetChanged();
        mRefreshLayout.finishLoadMore();
        mRefreshLayout.finishRefresh();
    }
    @Override
    protected void onRefresh() {
        super.onRefresh();
        mPage++;
        getImgData();
    }



    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mPage++;
    }

    @Override
    public void onFailure(Exception ex, String errorCode) {
        UtilsLog.e(ex.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        XHttp.getInstance().cancel("meizitu");
    }

    @Override
    protected void initRefreshView(View containerView) {
        super.initRefreshView(containerView);
        mRefreshLayout.setEnableLoadMore(false);
    }

    private void getImgData(){
        //加载数据  缓存中 或者 网络
        XHttp.getInstance().post(url)
                .setParams("num", num)
                .setParams("showapi_appid", "34276")
                .setParams("showapi_sign", "731d68d6d56b4d789d6571f530ee28ef")
                .setParams("page", String.valueOf(mPage))
                .setTag("meizitu")
                .setOnXHttpCallback(this);
    }
}
