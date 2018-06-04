package com.binny.openapi.mvp.ui.activity;

import android.widget.TextView;

import com.binny.openapi.R;
import com.binny.openapi.constant.Constant;
import com.binny.openapi.mvp.bean.HistoryDetailBean;
import com.binny.openapi.mvp.ui.base.BaseActivity;
import com.binny.openapi.retrofit.manager.RetrofitManager;
import com.binny.openapi.retrofit.server.IJuheService;
import com.binny.openapi.util.UtilsLog;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HistoryDetailActivity extends BaseActivity {

    private String id ;
    private TextView mTextView;
    @Override
    protected void handleIntent() {
        id = getIntent().getStringExtra("e_id");
    }

    @Override
    protected void initView() {
        mTextView = findViewById(R.id.history_detail);
        IJuheService s = RetrofitManager.getJuheService();
        s.getDetailDate(Constant.JUHE_HOSITORY_BASE_KEY,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        UtilsLog.onLoading();

                    }

                    @Override
                    public void onNext(HistoryDetailBean historyDetailBean) {
                        mTextView.setText(historyDetailBean.getResult().get(0).getContent().replace("\r","\n"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        UtilsLog.e(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        UtilsLog.onLoadDone();

                    }
                });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

}
