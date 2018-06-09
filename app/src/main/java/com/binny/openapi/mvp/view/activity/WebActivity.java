package com.binny.openapi.mvp.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
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
    private java.lang.String loadUrl;

    private boolean bNeedAdBlock;//是否去出第三方广告链接

    private SimpleTitleBar mSimpleTitleBar;

    @Override
    protected void handleIntent() {
        loadUrl = getIntent().getStringExtra("url");
        bNeedAdBlock = getIntent().getBooleanExtra("adblock", false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        mSimpleTitleBar = findViewById(R.id.simple_title_bar);
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
        } else {
            mWebView.loadUrl(loadUrl);
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
                Toast.makeText(mActivity, "分享", Toast.LENGTH_SHORT).show();
                shareText("1111","33333","ssssss");
            }
        });
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
                            host = url.getHost();
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

    /**
     * 分享文字内容
     *
     * @param title 分享对话框标题
     * @param subject  主题
     * @param content  分享内容（文字）
     */
    private void shareText(String title, String subject, String content) {
        if (content == null || "".equals(content)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if (subject != null && !"".equals(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);

        // 设置弹出框标题
        if (title != null && !"".equals(title)) { // 自定义标题
            startActivity(Intent.createChooser(intent, title));
        } else { // 系统默认标题
            startActivity(intent);
        }
    }
}
