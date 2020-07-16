package com.yingbao.career.ui.home.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yingbao.career.R;
import com.yingbao.career.ui.home.bean.FilterTagBean;

import java.util.List;

/**
 * @author Wang Lele
 */
public class SearchGradeListAdapter extends BaseQuickAdapter<FilterTagBean, BaseViewHolder> {

    public SearchGradeListAdapter(Context context, List<FilterTagBean> filterTagBeanList) {
        super(R.layout.item_search_filtert, filterTagBeanList);
    }

    @Override
    protected void convert(BaseViewHolder holder, FilterTagBean filterTagBean) {
        holder.setText(R.id.tv_paper_grade, filterTagBean.getTagName());
        holder.setBackgroundResource(R.id.tv_paper_grade, filterTagBean.isChecked() ?
                R.drawable.search_filter_chosed : R.drawable.search_filter_nomal);
        holder.setTextColor(R.id.tv_paper_grade, getContext().getResources().getColor(filterTagBean.isChecked() ?
                R.color.career_white : R.color.career_212332));
    }
}