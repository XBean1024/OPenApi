package com.binny.openapi.mvp.view.activity;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bean.xhttp.XHttp;
import com.bean.xhttp.callback.OnXHttpCallback;
import com.bean.xhttp.response.Response;
import com.binny.openapi.R;
import com.binny.openapi.mvp.view.base.BaseActivity;
import com.binny.openapi.util.UtilsLog;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebActivity extends BaseActivity {

    private WebView mWebView;
    private java.lang.String loadUrl;

    private boolean bNeedAdBlock;//是否去出第三方广告链接

    @Override
    protected void handleIntent() {
        loadUrl = getIntent().getStringExtra("url");
        bNeedAdBlock = getIntent().getBooleanExtra("adblock", false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
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
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        if (bNeedAdBlock) {
            adBlock();
        }else {
            mWebView.loadUrl(loadUrl);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_protocol;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void adBlock() {
        XHttp.getInstance()
                .get(loadUrl)
                .setTag("adblock")
                .setOnXHttpCallback(new OnXHttpCallback() {
                    @Override
                    public void onSuccess(Response response) {
                        String html = response.toString();
                        UtilsLog.i(html);
//                        ReadStreamOfJson(html);
                        final String regexStr = "<script\\b[^>]*?src=\"([^\"]*?)\"[^>]*></script>";
                        Pattern p = Pattern.compile(regexStr);
                        String strUrl = loadUrl;
                        String host = "";
                        try {
                            URL url = new URL(strUrl);
                            host =url.getHost();
                            strUrl = String.format("%s://%s", url.getProtocol(), url.getHost());

                            Matcher m = p.matcher(html);
                            while (m.find()) {
                                if (!(m.group().contains(host))) {
                                    html = html.replace(m.group(), "");
                                }
                            }
                            mWebView.loadDataWithBaseURL(strUrl, html,
                                    "text/html", "utf-8", "file:///android_asset/error_page.html");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Exception ex, String errorCode) {

                    }
                });
    }

}
