package com.binny.openapi.mvp.presenter;

import com.binny.openapi.bean.ArticleBean;
import com.binny.openapi.bean.FuLiBean;
import com.binny.openapi.bean.HistoryDayBean;
import com.binny.openapi.bean.JuheNewsBean;
import com.binny.openapi.bean.LoginBean;
import com.binny.openapi.bean.PictureBean;
import com.binny.openapi.bean.RegisterBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.model.gank.GankFuLiModel;
import com.binny.openapi.mvp.model.home.ArticleModel;
import com.binny.openapi.mvp.model.juhe.HistoryModel;
import com.binny.openapi.mvp.model.juhe.JuheNewsModel;
import com.binny.openapi.mvp.model.login.ILoginModel;
import com.binny.openapi.mvp.model.login.LoginModel;
import com.binny.openapi.mvp.model.picture.PictureModel;
import com.binny.openapi.mvp.model.register.RegisterModel;

import java.io.File;

/**
 * author  binny
 * date 5/22
 */
public class Presenter {
    private ArticleModel mArticleModel;//每日一文

    /**
     * 登录的函数
     *
     * @param phone                 电话
     * @param passwd                密码
     * @param loginBeanDataCallback 回调
     */
    public void getLoginData(String phone, String passwd, DataCallback<LoginBean> loginBeanDataCallback) {
        ILoginModel model = new LoginModel();
        model.requestLogin(new DataCallback<LoginBean>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoadDone() {

            }

            @Override
            public void onError(String result) {
                loginBeanDataCallback.onError(result);
            }

            @Override
            public void onSuccess(LoginBean loginBean) {
                loginBeanDataCallback.onSuccess(loginBean);
            }
        }, phone, passwd);
    }

    /**
     * 注册的业务处理函数
     *
     * @param registerBeanDataCallback 请求结果的回调
     */

    public void getRegisterData(String phone, String passwd, String name, String text, String other, String other2, File imageFile, DataCallback<RegisterBean> registerBeanDataCallback) {
        new RegisterModel().requestRegister(new DataCallback<RegisterBean>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoadDone() {

            }

            @Override
            public void onSuccess(RegisterBean registerBean) {
                registerBeanDataCallback.onSuccess(registerBean);
            }

            @Override
            public void onError(String result) {
                registerBeanDataCallback.onError(result);
            }
        }, phone, passwd, name, text, other, other2, imageFile);
    }


    /**
     * 默认构造函数
     */
    public Presenter() {

    }

    /**
     * 每日一文的
     *
     * @param articleBeanDataCallback 每日一文的请求回调
     */

    public void getArticle(int type, final DataCallback<ArticleBean> articleBeanDataCallback) {
        if (mArticleModel == null) {
            mArticleModel = new ArticleModel(articleBeanDataCallback);
        }
        switch (type) {
            case 0:
                mArticleModel.getArticle();
                break;
            case 1:
                //获取随机的一天的短文
                mArticleModel.getArticleRandom();
                break;
            case 2:
                //获取指定的一天
                mArticleModel.getArticleDay();
                break;
        }
    }


    /**
     * 获取 图片 下面纯图的 数据
     *
     * @param page                    请求第几页
     * @param pictureBeanDataCallback 回调
     */
    public void getPureImgDate(int page, DataCallback<PictureBean> pictureBeanDataCallback) {
        new PictureModel().getData(page, pictureBeanDataCallback);
    }

    /**
     * 获取聚合数据的 方法
     *
     * @param type     tab 的类型
     * @param callback 请求回调
     */
    public void getJuheNewsData(String type, DataCallback<JuheNewsBean> callback) {
        new JuheNewsModel().getData(type, callback);
    }

    /**
     * 历史上的今天
     *
     * @param historyDayBeanDataCallback 历史上的今天的请求回调
     */
    public void getHistoryDate(DataCallback<HistoryDayBean> historyDayBeanDataCallback) {
        new HistoryModel().getArticle(historyDayBeanDataCallback);
    }
    public void getGankFuliData(int page, DataCallback<FuLiBean> callback){
        new GankFuLiModel().getGankFuliData(page,callback);
    }
}
