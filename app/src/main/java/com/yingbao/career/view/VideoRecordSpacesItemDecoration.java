package com.yingbao.career.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.yingbao.career.common.MyApplication;
import com.yingbao.career.http.resultbean.VideoRecordTimeLine;
import com.yingbao.career.utils.DisplayTools;

import java.util.List;

public class VideoRecordSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private List<VideoRecordTimeLine.ResultBean> data;

    public VideoRecordSpacesItemDecoration(int space, List<VideoRecordTimeLine.ResultBean> data) {
        this.space = space;
        this.data = data;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        int viewPosition = parent.getChildLayoutPosition(view);
        if (viewPosition > 0) {
            //去掉头部的header
            viewPosition = viewPosition - 1;
            if (data.get(viewPosition).getItemType() == VideoRecordTimeLine.ResultBean.VIDEO) {
                if (isLeftVideoView(viewPosition)) {
                    int leftSpace = DisplayTools.dip2px(MyApplication.context, 23f);
                    outRect.left = leftSpace;
                    outRect.right = space / 2;
                } else {
                    int rightSpace = DisplayTools.dip2px(MyApplication.context, 23f);
                    outRect.left = space / 2;
                    outRect.right = rightSpace;
                }
            }
        }
    }
    private boolean isLeftVideoView(int position){
        int latestTimePosition = 0;
        for (int i = 0; i < position; i++) {
            if (data.get(i).getItemType()==VideoRecordTimeLine.ResultBean.TIME){
                latestTimePosition = i;
            }
        }
        int temp =position - latestTimePosition;
        if (temp %2 !=0){
            return true;
        }
        return false;
    }

}