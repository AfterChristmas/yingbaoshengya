package com.yingbao.career.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 快/慢滑动ScrollView
 * 
 * @author 王月
 * 
 */
public class ScrollViewCus extends ScrollView {

	public ScrollViewCus(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ScrollViewCus(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScrollViewCus(Context context) {
		super(context);
	}

	/**
	 * 滑动事件(可设置滑动速度，例如：velocityY/2)
	 */
	@Override
	public void fling(int velocityY) {
		super.fling(velocityY);
	}
}