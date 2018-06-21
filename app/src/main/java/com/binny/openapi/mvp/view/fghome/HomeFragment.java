package com.binny.openapi.mvp.view.fghome;

import com.binny.openapi.mvp.view.base.AbsBaseFragment;
import com.binny.openapi.mvp.view.base.AbsBottomNavigationTabBaseFragment;
import com.binny.openapi.mvp.view.fghome.article.ArticleFragment;
import com.binny.openapi.mvp.view.fghome.juhe.history.HistoryViewHolderFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.AmusementFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.FashionFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.FinaceFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.GymFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.InternalFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.MilitrayFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.SCFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.SocialFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.TouTiaoFrgment;

import java.util.List;

/**
 * author  binny
 * date 5/9
 */
public class HomeFragment extends AbsBottomNavigationTabBaseFragment {

    /**
     */
    @Override
    protected String[] initTitles() {

        return  new String[]{
                "头条",
                "每日一文",
                "历史上的今天",
                "社会",
                "国内",
                "娱乐",
                "体育",
                "军事",
                "科技",
                "财经",
                "时尚"
        };
    }


    /**
     * @param fragments 集合
     */
    @Override
    protected void initFragments(List<AbsBaseFragment> fragments) {
        fragments.add(new TouTiaoFrgment());//头条
        fragments.add(new ArticleFragment());//每日一文
        fragments.add(new HistoryViewHolderFragment());//历史上的今天
        fragments.add(new SocialFragment());//社会
        fragments.add(new InternalFragment());//国内
        fragments.add(new AmusementFragment());//娱乐
        fragments.add(new GymFragment());//体育
        fragments.add(new MilitrayFragment());//军事
        fragments.add(new SCFragment());//科技
        fragments.add(new FinaceFragment());//财经
        fragments.add(new FashionFragment());//时尚
    }
}
