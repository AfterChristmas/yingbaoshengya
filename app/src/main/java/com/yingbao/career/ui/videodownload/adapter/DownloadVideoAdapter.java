package com.yingbao.career.ui.videodownload.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arialyy.aria.core.download.DownloadEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yingbao.career.R;

import java.util.List;


public class DownloadVideoAdapter extends BaseQuickAdapter<DownloadEntity, BaseViewHolder> {

    public DownloadVideoAdapter(int layoutResId, @Nullable List<DownloadEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DownloadEntity homeVideoBean) {
        ImageView iv_preview = helper.getView(R.id.iv_preview);
        TextView tv_title = helper.getView(R.id.tv_title);
        tv_title.setText(homeVideoBean.getFileName());
//        Glide.with(getContext()).load(homeVideoBean.getSubjectIconUrl()).into((ImageView) iconView);
//        iconView.setImageResource(R.drawable.chinese_exercise);
    }


}
