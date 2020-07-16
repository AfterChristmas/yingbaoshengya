package com.yingbao.career.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;


/**
 * 负责人： 完颜东臣
 * @info  
 * @time 2017年2月22日14:03:46
 */
public class YScrollView extends ScrollView {
	private ScrollViewListener scrollViewListener = null;
	private Context context;
	private boolean intercepted=true;
	private boolean mDragging;
	private boolean mIntercepted;
	private boolean mFirstScroll;
	private ScrollState mScrollState;
	private ObservableScrollViewCallbacks mCallbacks;
	private int mScrollY;

	private int mPrevScrollY;

	public YScrollView(Context context) {
		super(context);
		this.context=context;
	}

	public YScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context=context;
	}

	public YScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
	}

	public void setScrollViewListener(ScrollViewListener scrollViewListener) {
		this.scrollViewListener = scrollViewListener;
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		Parcelable superState = super.onSaveInstanceState();
		SavedState ss = new SavedState(superState);
		ss.prevScrollY = mPrevScrollY;
		ss.scrollY = mScrollY;
		return ss;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		SavedState ss = (SavedState) state;
		mPrevScrollY = ss.prevScrollY;
		mScrollY = ss.scrollY;
		super.onRestoreInstanceState(ss.getSuperState());
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if (scrollViewListener != null) {
			scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
		}

		if (mFirstScroll) {
			mFirstScroll = false;
		}

		mScrollY = y;

		if (mFirstScroll) {
			mFirstScroll = false;
		}

		if (mPrevScrollY < y) {
			mScrollState = ScrollState.UP;
		} else if (y < mPrevScrollY) {
			mScrollState = ScrollState.DOWN;
			//} else {
			// Keep previous state while dragging.
			// Never makes it STOP even if scrollY not changed.
			// Before Android 4.4, onTouchEvent calls onScrollChanged directly for ACTION_MOVE,
			// which makes mScrollState always STOP when onUpOrCancelMotionEvent is called.
			// STOP state is now meaningless for ScrollView.
		}
		mPrevScrollY = y;
	}

	@Override
	protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
		return 0;
	}

	public void setScrollViewCallbacks(ObservableScrollViewCallbacks listener) {
		mCallbacks = listener;
	}

	private void dispatchOnUpOrCancelMotionEvent(ScrollState scrollState) {
		if (mCallbacks != null) {
			mCallbacks.onUpOrCancelMotionEvent(scrollState);
		}
		/*if (mCallbackCollection != null) {
			for (int i = 0; i < mCallbackCollection.size(); i++) {
				ObservableScrollViewCallbacks callbacks = mCallbackCollection.get(i);
				callbacks.onUpOrCancelMotionEvent(scrollState);
			}
		}*/
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getActionMasked()) {
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_MOVE:
			case MotionEvent.ACTION_CANCEL:
				mIntercepted = false;
				mDragging = false;
				dispatchOnUpOrCancelMotionEvent(mScrollState);
				break;
		}
		return super.onTouchEvent(ev);
	}
	static class SavedState extends BaseSavedState {
		int prevScrollY;
		int scrollY;

		/**
		 * Called by onSaveInstanceState.
		 */
		SavedState(Parcelable superState) {
			super(superState);
		}

		/**
		 * Called by CREATOR.
		 */
		private SavedState(Parcel in) {
			super(in);
			prevScrollY = in.readInt();
			scrollY = in.readInt();
		}

		@Override
		public void writeToParcel(Parcel out, int flags) {
			super.writeToParcel(out, flags);
			out.writeInt(prevScrollY);
			out.writeInt(scrollY);
		}

		public static final Parcelable.Creator<SavedState> CREATOR
				= new Parcelable.Creator<SavedState>() {
			@Override
			public SavedState createFromParcel(Parcel in) {
				return new SavedState(in);
			}

			@Override
			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		};
	}
}
