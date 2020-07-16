package com.yingbao.career.ui.home.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yingbao.career.R;
import com.yingbao.career.http.resultbean.VideoListResultBean;

import java.util.List;


public class VideoPlayListVideoAdapter extends BaseQuickAdapter<VideoListResultBean.ResultBean.VideosBean, BaseViewHolder> {
    private String teacherName;

    public VideoPlayListVideoAdapter(int layoutResId, @Nullable List<VideoListResultBean.ResultBean.VideosBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoListResultBean.ResultBean.VideosBean item) {
        helper.setText(R.id.tv_title, item.getVideoName());
        TextView tv_title= helper.getView(R.id.tv_title);
        TextView tv_teacher= helper.getView(R.id.tv_teacher);
        tv_title.setText(item.getVideoName());
        tv_teacher.setText(teacherName);
        if (item.isPlaying()){
            tv_title.setTextColor(getContext().getResources().getColor(R.color.career_0A72FE));
        }else {
            tv_title.setTextColor(getContext().getResources().getColor(R.color.career_textmain_242424));
        }
    }
    public void setTeacherName(String teacherName){
        this.teacherName = teacherName;
    }

}
