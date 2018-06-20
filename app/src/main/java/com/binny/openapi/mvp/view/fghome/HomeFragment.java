package com.binny.openapi.mvp.view.fghome;

import android.view.View;

import com.binny.openapi.mvp.view.base.BaseFragment;
import com.binny.openapi.mvp.view.base.NavigationContainerFragment;
import com.binny.openapi.mvp.view.fghome.article.ArticleFragment;
import com.binny.openapi.mvp.view.fghome.juhe.history.JuheHistoryFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheAmusementFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheFashionFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheFinaceFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheGymFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheInternalFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheMilitrayFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheSCFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheSocialFragment;
import com.binny.openapi.mvp.view.fghome.juhe.news.JuheTouTiaoFrgment;

import java.util.List;

/**
 * author  binny
 * date 5/9
 */
public class HomeFragment extends NavigationContainerFragment {

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
    protected void initFragments(List<BaseFragment> fragments) {
        fragments.add(new JuheTouTiaoFrgment());//头条
        fragments.add(new ArticleFragment());//每日一文
        fragments.add(new JuheHistoryFragment());//历史上的今天
        fragments.add(new JuheSocialFragment());//社会
        fragments.add(new JuheInternalFragment());//国内
        fragments.add(new JuheAmusementFragment());//娱乐
        fragments.add(new JuheGymFragment());//体育
        fragments.add(new JuheMilitrayFragment());//军事
        fragments.add(new JuheSCFragment());//科技
        fragments.add(new JuheFinaceFragment());//财经
        fragments.add(new JuheFashionFragment());//时尚
    }
}
