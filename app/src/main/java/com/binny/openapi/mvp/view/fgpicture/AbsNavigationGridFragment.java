package com.binny.openapi.mvp.view.fgpicture;

import android.view.View;
import android.widget.GridView;

import com.binny.openapi.R;
import com.binny.openapi.mvp.view.base.AbsNavigationContentListFragment;
import com.smart.holder.CommonAdapter;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/21 10:21
 * @Description:
 */
public abstract class AbsNavigationGridFragment extends AbsNavigationContentListFragment {
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_common_grid;
    }

    @Override
    protected void initView(View view) {
        GridView l = view.findViewById(R.id.home_common_gv);
        mCommonAdapter = new CommonAdapter(getActivity(),initListBean(), initItem(),initViewHolderHelper());
        l.setAdapter(mCommonAdapter);
    }
}
