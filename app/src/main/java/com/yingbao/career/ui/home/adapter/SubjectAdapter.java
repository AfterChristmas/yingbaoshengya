package com.yingbao.career.ui.home.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yingbao.career.R;
import com.yingbao.career.common.MyApplication;
import com.yingbao.career.ui.home.bean.SubjectItemBean;
import com.yingbao.career.utils.DisplayTools;

import java.util.List;


public class SubjectAdapter extends BaseQuickAdapter<SubjectItemBean, BaseViewHolder> {

    public SubjectAdapter(int layoutResId, @Nullable List<SubjectItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectItemBean homeVideoBean) {
        ImageView iconView = helper.getView(R.id.iv_subjectimage);
        iconView.setImageResource(MyApplication.context.getResources().obtainTypedArray(R.array.nine_subject_icons).getResourceId(homeVideoBean.getSubjectIndex(),R.drawable.yuwen_subject_bg));
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        float spanWidth = DisplayTools.dip2px(getContext(), 60);
        float itemWidth = (DisplayTools.getResolutionWidthInfo(getContext())-spanWidth)/3;
        holder.itemView.getLayoutParams().height = (int) itemWidth;
        super.onBindViewHolder(holder, position);
    }
}
