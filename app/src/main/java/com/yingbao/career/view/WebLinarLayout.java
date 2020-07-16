package com.yingbao.career.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.yingbao.career.common.MyApplication;
import com.yingbao.career.utils.DisplayTools;

public class WebLinarLayout extends LinearLayout {

    public WebLinarLayout(Context context) {
        super(context);
    }

    public WebLinarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebLinarLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return false;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(DisplayTools.dip2px(MyApplication.context,150f), MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}