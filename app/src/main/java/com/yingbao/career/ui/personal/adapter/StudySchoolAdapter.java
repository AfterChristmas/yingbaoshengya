package com.yingbao.career.ui.personal.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yingbao.career.R;
import com.yingbao.career.http.resultbean.StudySchoolResutlBean;

import java.util.List;


/**
 * Created by xsc on 2017/11/27.
 * 省份adapter
 */

public class StudySchoolAdapter extends BaseQuickAdapter<StudySchoolResutlBean.ResultBean, BaseViewHolder> {

    public StudySchoolAdapter(int layoutResId, List<StudySchoolResutlBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudySchoolResutlBean.ResultBean item) {

        TextView tvIndex = helper.getView(R.id.tv_province_index);
        TextView tvName = helper.getView(R.id.tv_province_name);
        View v_province_divider = helper.getView(R.id.v_province_divider);

        tvName.setText(item.getName());
       int position = helper.getAdapterPosition();
        List<StudySchoolResutlBean.ResultBean> list = getData();
        if (position == 0 || !TextUtils.equals(item.getIndex(), list.get(position - 1).getIndex())) {
            tvIndex.setText(item.getIndex());
            tvIndex.setVisibility(View.VISIBLE);
        } else {
            tvIndex.setVisibility(View.GONE);
        }
        //分割线的显示控制
        if (position < list.size()-1) {
            if (TextUtils.equals(item.getIndex(), list.get(position + 1).getIndex())) {
                v_province_divider.setVisibility(View.VISIBLE);
            } else {
                v_province_divider.setVisibility(View.GONE);
            }
        } else {//hide
            v_province_divider.setVisibility(View.GONE);
        }
    }
}
