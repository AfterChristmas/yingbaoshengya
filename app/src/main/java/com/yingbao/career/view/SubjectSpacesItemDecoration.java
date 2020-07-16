package com.yingbao.career.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SubjectSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SubjectSpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) % 3 == 0) {
            outRect.left = space;
            outRect.right = space / 2;
        } else if (parent.getChildLayoutPosition(view) % 3 == 1) {
            outRect.left = space/2;
            outRect.right = space / 2;
        }else {
            outRect.left = space / 2;
            outRect.right = space;
        }
    }
}