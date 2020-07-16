package com.yingbao.career.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017/11/16.
 */

public class MyQuesView extends YbWebView {

    private Context mContext;

    public MyQuesView(Context context) {
        super(context);
        this.mContext = context;
        initQuesView();
    }

    public MyQuesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initQuesView();
    }

    public MyQuesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initQuesView();
    }


    /**
     * 对webview进行初始设置
     */
    private void initQuesView() {
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 加载图片部分
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setBlockNetworkImage(false);
        webSettings.setLoadsImagesAutomatically(true);
        // 屏蔽长按
        this.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }


    /**
     * 设置完整的 html 字符串
     *
     * @param htmlText
     */
    public void setFullHtmlText(String htmlText) {
        this.loadDataWithBaseURL(null, htmlText, "text/html", "UTF-8", null);
    }

    /**
     * 设置文字
     *
     * @param text
     */
    public void setText(String text) {
        String htmlText = getHTMLText(text);
        this.loadDataWithBaseURL(null, htmlText, "text/html", "UTF-8", null);
    }

    /**
     * @param text
     * @return
     */
    private String getHTMLText(String text) {
        String path = "file:///android_asset/jqmath/";
        String htmlText = "<html lang='zh-CN'><head>"
                + "<style type='text/css'> u{color: darkblue} </style>"
                + "<link rel='stylesheet' href='" + path + "jqmath-0.4.3.css' />"
                + "<script src='" + path + "jquery-1.4.3.min.js'></script>"
                + "<script src='" + path + "jqmath-etc-0.4.6.min.js' charset='utf-8'></script>"
                + "<link rel='stylesheet' href='" + path + "ques.css' type='text/css' />" // 处理题文显示波浪线的问题
                + "<style>img{max-width:100% !important;}</style>"
                + "</head><body style='margin:0;padding:0;word-wrap:break-word;word-break:break-all;line-height:1.5;'>" + text + "</body>";
        return htmlText;
    }
}
