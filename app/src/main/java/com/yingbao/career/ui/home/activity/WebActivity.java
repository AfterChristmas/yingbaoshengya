package com.yingbao.career.ui.home.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yingbao.career.Base.BaseActivity;
import com.yingbao.career.R;
import com.yingbao.career.utils.DisplayTools;

public class WebActivity extends BaseActivity implements OnClickListener {

    private WebView webContent;

    private String mUrl;
    private String mTitle;

    public static final String URL = "URL";
    public static final String REMOVE_RANDOM = "REMOVE_RANDOM";
    public static final String TITLE = "TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        DisplayTools.setTransparentStatusBar(this);
        getBasicData();
        findViewsAndSetListener();
    }

    private void getBasicData() {
        mUrl = getIntent().getStringExtra(URL);
        boolean removeRandom = getIntent().getBooleanExtra(REMOVE_RANDOM, false);
        if (!removeRandom) {
            // 尾部添加一个随机数参数，防止缓存
            int x = (int) (Math.random() * 10000);
            mUrl = mUrl + "?a=" + x;
        }
        mTitle = getIntent().getStringExtra(TITLE);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void findViewsAndSetListener() {
        TextView tvTitle = findViewById(R.id.tv_title);
        if (mTitle != null) {
            tvTitle.setText(mTitle);
        }
        LinearLayout llWebContent = findViewById(R.id.ll_web_content);
        webContent = new WebView(getApplicationContext());
        llWebContent.addView(webContent);
        webContent.getSettings().setJavaScriptEnabled(true);
        webContent.getSettings().setBlockNetworkImage(false);
        webContent.getSettings().setDefaultTextEncodingName("UTF-8");
        webContent.getSettings().setJavaScriptEnabled(true);
        webContent.getSettings().setLoadWithOverviewMode(true);
        webContent.getSettings().setUseWideViewPort(true);
        webContent.getSettings().setTextZoom(200); // 通过百分比来设置文字的大小，默认值是100
        webContent.loadUrl(mUrl);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        if (webContent != null) {
            ViewParent parent = webContent.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webContent);
            }
            webContent.removeAllViews();
            webContent.destroy();
        }
        super.onDestroy();
    }

}
