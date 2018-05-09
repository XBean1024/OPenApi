package com.binny.openapi.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.binny.openapi.R;

public class ProtocolItemActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol);
        this.mWebView = (WebView) findViewById(R.id.web_view_protocol);

        final String loadUrl = "http://www.hbpu.edu.cn/";
//        final String loadUrl = "file:///android_asset/RegisterProtocol.html";
        try {

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
            mWebView.loadUrl(loadUrl);
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.setWebChromeClient(new WebChromeClient(){
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    setTitle(title);
                }
            });
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
