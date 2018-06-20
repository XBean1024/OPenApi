package com.binny.openapi.mvp.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bean.xhttp.XHttp;
import com.bean.xhttp.callback.OnXHttpCallback;
import com.bean.xhttp.response.Response;
import com.binny.openapi.R;
import com.binny.openapi.mvp.view.base.BaseActivity;
import com.binny.openapi.util.UtilsLog;
import com.binny.openapi.widget.titlebar.SimpleTitleBar;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebActivity extends BaseActivity {

    private WebView mWebView;
    private java.lang.String mLoadUrl;
    private String mTitle;
    private String mContent;

    private boolean bNeedAdBlock;//是否去出第三方广告链接

    private SimpleTitleBar mSimpleTitleBar;

    @Override
    protected void handleIntent() {
        mLoadUrl = getIntent().getStringExtra("loadUrl");
        mTitle = getIntent().getStringExtra("title");
        mContent = getIntent().getStringExtra("content");
        bNeedAdBlock = getIntent().getBooleanExtra("adblock", false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        mSimpleTitleBar = findViewById(R.id.simple_title_bar);
        mSimpleTitleBar.setTitle("");
        mImmersionBar.titleBar(mSimpleTitleBar).init();
        this.mWebView = findViewById(R.id.web_view_protocol);
        WebSettings webSettings = mWebView.getSettings();
        //支持javascript
        webSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置缩放
        webSettings.setBuiltInZoomControls(true);
        //隐藏缩放控件
        webSettings.setDisplayZoomControls(false);
        //扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                adBlock(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        if (bNeedAdBlock) {
            adBlock(mLoadUrl);
        } else {
            mWebView.loadUrl(mLoadUrl);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void setListener() {
        super.setListener();
        mSimpleTitleBar.setOnClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText(mWebView.getTitle(), "33333", mLoadUrl);
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XHttp.getInstance().cancel("adblock");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        UtilsLog.i(mWebView.getUrl());
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void adBlock(String url) {
        XHttp.getInstance()
                .get(url)
                .setTag("adblock")
                .setOnXHttpCallback(new OnXHttpCallback() {
                    @Override
                    public void onSuccess(Response response) {
                        String html = response.toString();
                        UtilsLog.i(html);
//                        ReadStreamOfJson(html);
                        final String regexStr = "<script\\b[^>]*?src=\"([^\"]*?)\"[^>]*></script>";
                        Pattern p = Pattern.compile(regexStr);
                        String strUrl = url;
                        String host = "";
                        try {
                            URL urls = new URL(strUrl);
                            host = urls.getHost();
                            strUrl = String.format("%s://%s", urls.getProtocol(), urls.getHost());

                            Matcher m = p.matcher(html);
                            while (m.find()) {
                                if (!(m.group().contains(host))) {
                                    html = html.replace(m.group(), "");
                                }
                            }

                            mWebView.loadDataWithBaseURL(strUrl, html,
                                    "text/html", "utf-8", url);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Exception ex, String errorCode) {

                    }
                });
    }

    /**
     * 分享文字内容
     *
     * @param title   分享对话框标题
     * @param subject 主题
     * @param content 分享内容（文字）
     */
    private void shareText(String title, String subject, String content) {
        if (content == null || "".equals(content)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if (subject != null && !"".equals(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, "rrrrr");
        }

        intent.putExtra(Intent.EXTRA_TEXT, title + "\n" + content);

        // 设置弹出框标题
        if (title != null && !"".equals(title)) { // 自定义标题
            startActivity(Intent.createChooser(intent, title));
        } else { // 系统默认标题
            startActivity(intent);
        }
    }

}
