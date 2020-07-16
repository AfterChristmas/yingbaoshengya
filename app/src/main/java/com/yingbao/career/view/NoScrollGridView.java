package com.yingbao.career.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 负责人：完颜东臣 
 * @deprecated:不能滑动的gridview 
 * 				解决在scrlloview中gridview无法计算高度的问题
 * @author wydc
 */
public class NoScrollGridView extends GridView {


	public NoScrollGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, mExpandSpec);
	}

}
