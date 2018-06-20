package com.binny.openapi.mvp.view.fgpicture.meizi;

import com.bean.xhttp.XHttp;
import com.bean.xhttp.callback.OnXHttpCallback;
import com.bean.xhttp.response.Response;
import com.binny.openapi.R;
import com.binny.openapi.bean.MeiZiTuBean;
import com.binny.openapi.mvp.view.base.AbsNavigationContentFragment;
import com.binny.openapi.util.UtilsLog;
import com.google.gson.Gson;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/20 16:09
 * @Description:
 */
public class MeiZiFragment extends AbsNavigationContentFragment implements OnXHttpCallback {

    List<MeiZiTuBean.ShowapiResBodyBean.NewslistBean> mNewslistBeans = new ArrayList<>();
    private int page;


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
        XHttp.getInstance().get("http://route.showapi.com/197-1?showapi_appid=34276&showapi_sign=731d68d6d56b4d789d6571f530ee28ef&num=100&page=0")
                .setTag("meizitu")
                .setOnXHttpCallback(this);
    }

    @Override
    public void onSuccess(Response response) {
        page++;
        String json = response.toString();
        Gson gson = new Gson();
        MeiZiTuBean meiZiTuBean = gson.fromJson(json, MeiZiTuBean.class);
        if (meiZiTuBean.getShowapi_res_body()==null||meiZiTuBean.getShowapi_res_body().getCode()!=200) {
            UtilsLog.e(meiZiTuBean.getShowapi_res_error());
            return;
        }
        List<MeiZiTuBean.ShowapiResBodyBean.NewslistBean> newslistBeans = meiZiTuBean.getShowapi_res_body().getNewslist();
        mNewslistBeans.addAll(newslistBeans);
        mCommonAdapter.notifyDataSetChanged();
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
}
