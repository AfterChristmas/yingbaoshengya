package com.yingbao.career.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * @author Wang Lele
 */
public class MyClickSpan extends ClickableSpan {

    private Context context;

    public MyClickSpan(Context context) {
        this.context = context;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.parseColor("#2096D7"));
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View view) {
    }
}

