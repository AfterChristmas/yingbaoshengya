package com.yingbao.career.ui.home.adapter;

import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yingbao.career.R;
import com.yingbao.career.http.resultbean.RecommendCourseListBean;
import com.yingbao.career.utils.DisplayTools;
import com.yingbao.career.utils.StringTools;
import com.yingbao.career.utils.TimeUtil;

import java.util.List;


public class HomeClassVideoAdapter extends BaseQuickAdapter<RecommendCourseListBean.ResultBean, BaseViewHolder> {

    public HomeClassVideoAdapter(int layoutResId, @Nullable List<RecommendCourseListBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendCourseListBean.ResultBean homeVideoBean) {
        Glide.with(getContext())
                .load(StringTools.getCorrectVideoCoverUrl(homeVideoBean.getCourseCover()))
                .into((ImageView) helper.getView(R.id.iv_classimage));
        helper.setText(R.id.tv_class_name,homeVideoBean.getCourseName());
        helper.setText(R.id.tv_teacher,homeVideoBean.getCourseTeacherName());
        helper.setText(R.id.tv_time, TimeUtil.long2Short(homeVideoBean.getCourseStartTime()));
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        float spanWidth = DisplayTools.dip2px(getContext(), 45);
        float itemWidth = (DisplayTools.getResolutionWidthInfo(getContext())-spanWidth)/2;
        float imageHight =itemWidth/1.9f;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.getView(R.id.iv_classimage).getLayoutParams();
        layoutParams.height = (int) imageHight;
        holder.getView(R.id.iv_classimage).setLayoutParams(layoutParams);
        holder.itemView.getLayoutParams().width = (int) itemWidth;
        super.onBindViewHolder(holder, position);
    }
}
