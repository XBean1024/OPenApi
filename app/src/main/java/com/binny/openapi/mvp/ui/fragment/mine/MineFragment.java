package com.binny.openapi.mvp.ui.fragment.mine;

import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binny.openapi.R;
import com.binny.openapi.mvp.bean.ArticleBean;
import com.binny.openapi.mvp.ui.fragment.BaseFragment;
import com.binny.openapi.retrofit.api.IArticleService;
import com.binny.openapi.util.FileUtils;
import com.binny.openapi.util.JJLogger;
import com.scwang.wave.MultiWaveHeader;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.binny.openapi.retrofit.util.RetrofitServiceUtil.getArticleService;

/**
 * author  binny
 * date 5/9
 */
public class MineFragment extends BaseFragment {
    private MultiWaveHeader waveHeader;
    private CircleImageView profileImage;
    private RelativeLayout rlClearCache;
    private RelativeLayout mRelativeLayout;
    private TextView tvCacheSize;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-05-15 13:10:10 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        waveHeader = (MultiWaveHeader) view.findViewById(R.id.waveHeader);
        profileImage = (CircleImageView) view.findViewById(R.id.header);
        rlClearCache = (RelativeLayout) view.findViewById(R.id.rl_clear_cache);
        tvCacheSize = (TextView) view.findViewById(R.id.tv_cache_size);
        mRelativeLayout = view.findViewById(R.id.test2);
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        findViews(view);
        rlClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils.cleanApplicationData(getActivity(), "");
                String size = FileUtils.getTotalCacheSize(getActivity(), Environment.getExternalStorageDirectory()
                        .getAbsoluteFile() + "");
                JJLogger.logInfo(size);
                tvCacheSize.setText(size);
            }
        });
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                IArticleService service = getArticleService();
                service.getArticleToday()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ArticleBean>() {
                            @Override
                            public void onSubscribe(final Disposable d) {
                                JJLogger.logInfo("tag","ssssssssss");
                            }

                            @Override
                            public void onNext(final ArticleBean articleBean) {
                                JJLogger.logInfo("tag", "onNext = " + articleBean.toString());
                            }

                            @Override
                            public void onError(final Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
//                XHttp.getInstance()
//                        .get("https://interface.meiriyiwen.com/article/day?dev=1")
//                        .setOnXHttpCallback(new OnXHttpCallback() {
//                            @Override
//                            public void onSuccess(Response response) {
//                                Log.i("xxx", "response  " +response.toString());
//                            }
//
//                            @Override
//                            public void onFailure(Exception ex, String errorCode) {
//                                Log.i("xxx", "onFailure  " +ex.toString());
//                                Log.i("xxx", "onFailure  " +errorCode);
//                            }
//                        });
            }
        });
    }

    @Override
    protected void bindData() {
        super.bindData();
        String size = FileUtils.getTotalCacheSize(getActivity(), Environment.getExternalStorageDirectory()
                .getAbsoluteFile() + "");
        JJLogger.logInfo(size);
        tvCacheSize.setText(size);
    }

    @Override
    public void onResume() {
        super.onResume();
        JJLogger.logInfo(this.getClass().getSimpleName(), "onresumen");
    }

    public void setCacheSize() {
        String size = FileUtils.getTotalCacheSize(getActivity(), Environment.getExternalStorageDirectory()
                .getAbsoluteFile() + "");
        JJLogger.logInfo(size);
        if (tvCacheSize != null) {
            tvCacheSize.setText(size);
        }
    }
}
