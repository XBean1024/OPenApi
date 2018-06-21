package com.binny.openapi.mvp.view.fgpicture;

import com.binny.openapi.mvp.view.base.AbsBaseFragment;
import com.binny.openapi.mvp.view.base.AbsBottomNavigationTabBaseFragment;
import com.binny.openapi.mvp.view.fgpicture.fuli.FuLiFragment;
import com.binny.openapi.mvp.view.fgpicture.meizi.MeiZiFragment;
import com.binny.openapi.mvp.view.fgpicture.pure.PureImgFragment;

import java.util.List;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/20 14:21
 * @Description: 图片类  与 底部 图片 tab 对应
 */
public class PictureFragment extends AbsBottomNavigationTabBaseFragment {

    @Override
    protected String[] initTitles() {
        return new String[]{
                "纯图",
                "妹子图",
                "福利"
        };
    }

    @Override
    protected void initFragments(List<AbsBaseFragment> fragments) {
        fragments.add(new PureImgFragment());
        fragments.add(new MeiZiFragment());
        fragments.add(new FuLiFragment());
    }
}
