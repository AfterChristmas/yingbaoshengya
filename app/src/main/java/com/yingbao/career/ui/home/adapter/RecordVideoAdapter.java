package com.yingbao.career.ui.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alorma.timeline.RoundTimelineView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yingbao.career.R;
import com.yingbao.career.http.resultbean.VideoRecordTimeLine;
import com.yingbao.career.utils.DisplayTools;
import com.yingbao.career.utils.LogUtil;
import com.yingbao.career.utils.TimeUtil;

import java.util.List;


public class RecordVideoAdapter extends BaseMultiItemQuickAdapter<VideoRecordTimeLine.ResultBean, BaseViewHolder> {
    private List<VideoRecordTimeLine.ResultBean> list;

    public RecordVideoAdapter(@Nullable List<VideoRecordTimeLine.ResultBean> data) {
        super( data);
        this.list = data;
        addItemType(VideoRecordTimeLine.ResultBean.VIDEO, R.layout.item_record_video);
        addItemType(VideoRecordTimeLine.ResultBean.TIME, R.layout.item_record_video_time);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoRecordTimeLine.ResultBean homeVideoBean) {

        // 根据返回的 type 分别设置数据
        switch (helper.getItemViewType()) {
            case VideoRecordTimeLine.ResultBean.VIDEO:
                RoundTimelineView timelineView = helper.getView(R.id.time_line);
                TextView tv_time = helper.getView(R.id.tv_time);
                TextView tv_class_name = helper.getView(R.id.tv_class_name);
                LogUtil.e("testPsition==",helper.getAdapterPosition()+"");
                if (isLeftVideoView(helper.getLayoutPosition())) {
                    timelineView.setVisibility(View.VISIBLE);
                } else {
                    timelineView.setVisibility(View.GONE);
                }
                tv_class_name.setText(homeVideoBean.getVideoName());
                Glide.with(getContext()).load(homeVideoBean.getVideoCover()).into((ImageView) helper.getView(R.id.iv_classimage));
                break;
            case VideoRecordTimeLine.ResultBean.TIME:
                TextView tv_time_date = helper.getView(R.id.tv_time_date);
                tv_time_date.setText(TimeUtil.long2ShortYMD(homeVideoBean.getPlayDate()));
                break;
            default:
                break;
        }

    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (position > 0) {
            Context  mContext =getContext();
            switch (holder.getItemViewType()) {
                case VideoRecordTimeLine.ResultBean.VIDEO:
                    float spanWidth = DisplayTools.dip2px(mContext, 23 * 2 + 15);
                    float itemWidth = (DisplayTools.getResolutionWidthInfo(mContext) - spanWidth) / 2;
                    float imageHight = (itemWidth - DisplayTools.dip2px(mContext, 12)) / 1.6f;
                    LinearLayout.LayoutParams svContentLaoutParams = (LinearLayout.LayoutParams) holder.getView(R.id.sv_content).getLayoutParams();
                    if (isLeftVideoView(holder.getLayoutPosition())) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.getView(R.id.iv_classimage).getLayoutParams();
                        layoutParams.height = (int) imageHight;
                        holder.getView(R.id.iv_classimage).setLayoutParams(layoutParams);

                        svContentLaoutParams.rightMargin = DisplayTools.dip2px(mContext, 0);
                        if (isNeedTopMargin(holder.getLayoutPosition())){
                            svContentLaoutParams.topMargin = DisplayTools.dip2px(mContext, 10);
                        }else {
                            svContentLaoutParams.topMargin = DisplayTools.dip2px(mContext, 0);
                        }
                        holder.getView(R.id.sv_content).setLayoutParams(svContentLaoutParams);
                        holder.itemView.getLayoutParams().width = (int) itemWidth;
                    } else {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.getView(R.id.iv_classimage).getLayoutParams();
                        layoutParams.height = (int) imageHight;
                        holder.getView(R.id.iv_classimage).setLayoutParams(layoutParams);

                        svContentLaoutParams.rightMargin = DisplayTools.dip2px(mContext, 12);
                        if (isNeedTopMargin(holder.getLayoutPosition())){
                            svContentLaoutParams.topMargin = DisplayTools.dip2px(mContext, 10);
                        }else {
                            svContentLaoutParams.topMargin = DisplayTools.dip2px(mContext, 0);
                        }
                        holder.getView(R.id.sv_content).setLayoutParams(svContentLaoutParams);
                        holder.itemView.getLayoutParams().width = (int) itemWidth;
                    }
                    break;
                case VideoRecordTimeLine.ResultBean.TIME:
                    break;
                default:

                    break;
            }
            super.onBindViewHolder(holder, position);
        }
    }
    private boolean isLeftVideoView(int position){
        if (position>0) {
            position = position-1;
        }
        int latestTimePosition = 0;
        for (int i = 0; i < position; i++) {
            if (list.get(i).getItemType()==VideoRecordTimeLine.ResultBean.TIME){
                latestTimePosition = i;
            }
        }
        int temp =position - latestTimePosition;
        if (temp %2 !=0){
            Log.e("position is Left ==",position+"   left");
            return true;
        }
        Log.e("position is Left ==",position+"right");
        return false;
    }

    private boolean isNeedTopMargin(int position){
        if (position>0) {
            position = position-1;
        }
        int latestTimePosition = 0;
        for (int i = 0; i < position; i++) {
            if (list.get(i).getItemType()==VideoRecordTimeLine.ResultBean.TIME){
                latestTimePosition = i;
            }
        }
        int temp =position - latestTimePosition;
        if (temp ==1 || temp ==2 ){
            return true;
        }
        return false;
    }
}
