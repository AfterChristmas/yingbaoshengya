package com.yingbao.career.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017/11/16.
 */

public class YbWebView extends WebView {
    private Context mContext;
    public YbWebView(Context context) {
        this(context, null);
        this.mContext=context;
    }

    public YbWebView(Context context, AttributeSet attrs) {
        this(context, attrs, -300553);
        this.mContext=context;
    }

    public YbWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initWebView();
    }

    /**
     * 对webview进行初始设置
     */
    private void initWebView() {
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSavePassword(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        requestDisallowInterceptTouchEvent(true);
        // 加载图片部分
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setBlockNetworkImage(false);
        webSettings.setLoadsImagesAutomatically(true);
        this.setWebViewClient(new YbWebViewClient());
    }
    /**
     * dp转换成px
     *
     * @param context Context
     * @param dp      dp
     * @return px值
     */
    private int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
