package com.yingbao.career.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class InterceptLayoutViewPager extends ViewPager {
	
	private int mLastX;
	private int mLastY;
	
	private int mItemCount;
	private int mCurrentPositon;
	private int mPositionOffsetPixels;
	
	private boolean fragmentCanSlide = false;



	public InterceptLayoutViewPager(Context context) {
		super(context);
	}
	
    public InterceptLayoutViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);  
    } 
    
    
    @Override
    public void setAdapter(PagerAdapter arg0) {
    	mItemCount = arg0.getCount();
    	super.setAdapter(arg0);
    }

	@Override
    public boolean dispatchTouchEvent(MotionEvent event) {
    	int x = (int) event.getX();
    	int y = (int) event.getY();
    	ViewParent parent = getParent();
    	switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			fragmentCanSlide = false;
			if (parent!=null) {
				parent.requestDisallowInterceptTouchEvent(true);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			int diffX = x - mLastX;
			int diffY = y - mLastY;
			if (mCurrentPositon==0&&diffX>0&&mPositionOffsetPixels==0) {
				parent.requestDisallowInterceptTouchEvent(false);
			}
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		default:break;
		}


    	mLastX = x;
    	mLastY = y;
    	
    	return super.dispatchTouchEvent(event);
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
    	switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (fragmentCanSlide) {
				return false;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (fragmentCanSlide) {
				return false;
			}
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		}
    	return super.onInterceptTouchEvent(event);
    }
    

    @Override
    protected void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    	mCurrentPositon = position;
    	mPositionOffsetPixels = positionOffsetPixels;
    	super.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }
    
	public boolean isFragmentCanSlide() {
		return fragmentCanSlide;
	}

	public void setFragmentCanSlide(boolean fragmentCanSlide) {
		this.fragmentCanSlide = fragmentCanSlide;
	}
}
