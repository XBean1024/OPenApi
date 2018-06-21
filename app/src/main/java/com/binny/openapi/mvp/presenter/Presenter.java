package com.binny.openapi.mvp.presenter;

import com.binny.openapi.bean.ArticleBean;
import com.binny.openapi.bean.HistoryDayBean;
import com.binny.openapi.bean.JuheNewsBean;
import com.binny.openapi.bean.LoginBean;
import com.binny.openapi.bean.PictureBean;
import com.binny.openapi.bean.RegisterBean;
import com.binny.openapi.callback.DataCallback;
import com.binny.openapi.mvp.model.home.ArticleModel;
import com.binny.openapi.mvp.model.juhe.HistoryModel;
import com.binny.openapi.mvp.model.juhe.JuheNewsModel;
import com.binny.openapi.mvp.model.login.ILoginModel;
import com.binny.openapi.mvp.model.login.LoginModel;
import com.binny.openapi.mvp.model.picture.PictureModel;
import com.binny.openapi.mvp.model.register.IRegisterModel;
import com.binny.openapi.mvp.model.register.RegisterModel;

import java.io.File;

/**
 * author  binny
 * date 5/22
 */
public class Presenter {
    private DataCallback<RegisterBean> mRegister;
    private IRegisterModel mRegisterModel;
    private DataCallback<LoginBean> mLoginView;

    public void initLoginModel(DataCallback<LoginBean> loginView) {
        mLoginView = loginView;
    }

    public void getLoginData(String phone, String passwd) {
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
                mLoginView.onError(result);
            }

            @Override
            public void onSuccess(LoginBean loginBean) {
                mLoginView.onSuccess(loginBean);
            }
        },phone,passwd);
    }
    /**
     * 注册的业务处理函数
     *
     * @param registerBeanDataCallback 请求结果的回调
     */
    public void initRegisterModel(DataCallback<RegisterBean> registerBeanDataCallback) {
        mRegister = registerBeanDataCallback;
        mRegisterModel = new RegisterModel();
    }

    public void getData(String phone, String passwd, String name, String text, String other, String other2, File imageFile) {
        mRegisterModel.requestRegister(new DataCallback<RegisterBean>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoadDone() {

            }

            @Override
            public void onSuccess(RegisterBean registerBean) {
                mRegister.onSuccess(registerBean);
            }

            @Override
            public void onError(String result) {
                mRegister.onError(result);
            }
        }, phone, passwd, name, text, other, other2, imageFile);
    }

    private ArticleModel mArticleModel;

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
    public void initArticleModel(final DataCallback<ArticleBean> articleBeanDataCallback) {
        mArticleModel = new ArticleModel(articleBeanDataCallback);
    }

    public void getArticle() {
        mArticleModel.getArticle();
    }


    /**
     * 获取随机的一天的短文
     */
    public void getArticleRandom() {
        mArticleModel.getArticleRandom();
    }

    /**
     * 获取指定的一天
     */
    public void getArticleDay() {
        mArticleModel.getArticleDay();
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
}
