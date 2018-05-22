package com.binny.openapi.mvp.ui.fragment.home;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.binny.openapi.R;
import com.binny.openapi.mvp.ui.fragment.BaseFragment;
import com.binny.openapi.util.JJLogger;

/**
 * author  binny
 * date 5/9
 */
public class HomeFragment extends BaseFragment {
    private WebView mWebView;

    @Override
    protected void initView(View view) {
        mWebView = view.findViewById(R.id.wb_home);
        mWebView.loadUrl("https://guirenfu.m.hys06.club");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13 ");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
                    return true;
                }
                return false;
            }

        });
}

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

}
