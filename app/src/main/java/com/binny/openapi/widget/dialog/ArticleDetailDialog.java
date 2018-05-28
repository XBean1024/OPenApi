package com.binny.openapi.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.binny.openapi.R;

import java.util.Objects;

import static com.binny.openapi.util.UtilString.getHtml;

/**
 * 作者: binny
 * 时间: 5/23
 * 描述: 用于显示 每日一文 文章详细内容的对话框
 */
public class ArticleDetailDialog extends Dialog {
    private TextView mArticleDetailDialogTitle;
    private TextView mArticleDetailDialogAuthor;
    private WebView mWebView;
    private TextView mArticleDetailDialogCharacterCount;

    public ArticleDetailDialog setDrawableId(int drawableId) {
        mDrawableId = drawableId;
        return this;
    }

    private int mDrawableId;

    public ArticleDetailDialog setContent(String content) {
        mContent = content;
        return this;
    }

    private String mContent;

    public ArticleDetailDialog setTextTitle(CharSequence title) {
        mTitle = title;
        return this;
    }

    private CharSequence mTitle;

    /**
     * 设置字数
     *
     * @param characterCount
     */
    public ArticleDetailDialog setCharacterCount(CharSequence characterCount) {
        mCharacterCount = characterCount;
        return this;
    }

    private CharSequence mCharacterCount;

    /**
     * 设置作者的名字
     *
     * @param authorText
     */
    public ArticleDetailDialog setAuthorName(CharSequence authorText) {
        mAuthorName = authorText;
        return this;
    }

    private CharSequence mAuthorName;//作者名字

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-05-23 15:48:46 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        mArticleDetailDialogTitle = findViewById(R.id.article_detail_dialog_title);
        mArticleDetailDialogAuthor = findViewById(R.id.article_detail_dialog_author);
        mWebView = findViewById(R.id.article_detail_dialog_web_view);
        mArticleDetailDialogCharacterCount = findViewById(R.id.article_detail_dialog_character_count);
    }

    public ArticleDetailDialog(@NonNull Context context) {
        super(context);
    }

    public ArticleDetailDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.app_article_deatil_dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.layout_article_detail_dailog);
        setCanceledOnTouchOutside(true);
        Window dialogWindow = getWindow();
        dialogWindow.setWindowAnimations(R.style.push_up_in_out);//设置对话框的进出效果
        findViews();
        bindView();
    }

    private void bindView() {
        mArticleDetailDialogAuthor.setText(mAuthorName);
        mArticleDetailDialogCharacterCount.setText(mCharacterCount);
        mArticleDetailDialogTitle.setText(mTitle);
        String html = getHtml(mContent, "#ffffff");
        mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        mWebView.setWebViewClient(new WebViewClient());
    }

}
