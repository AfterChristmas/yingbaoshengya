package com.yingbao.career.ui.home.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yingbao.career.R;
import com.yingbao.career.common.MyApplication;
import com.yingbao.career.http.resultbean.FavoriteListResult;
import com.yingbao.career.utils.DisplayTools;
import com.yingbao.career.utils.StringTools;

import java.util.List;


public class CollectListAdapter extends BaseQuickAdapter<FavoriteListResult.ResultBean.RecordsBean , BaseViewHolder> {

    public CollectListAdapter(int layoutResId, @Nullable List<FavoriteListResult.ResultBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,FavoriteListResult.ResultBean.RecordsBean itemBean) {
        ImageView iv_preview = helper.getView(R.id.iv_preview);
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_teacher = helper.getView(R.id.tv_teacher);
        TextView tv_subtitle = helper.getView(R.id.tv_subtitle);
        Glide.with(getContext())
                .load(StringTools.getCorrectVideoCoverUrl(itemBean.getVideo().getVideoCover()))
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(DisplayTools.dip2px(MyApplication.context, 8))))//圆角半径
                .into(iv_preview);
        tv_title.setText(itemBean.getVideo().getVideoName());
        tv_teacher.setVisibility(View.GONE);
        tv_subtitle.setVisibility(View.GONE);
//        tv_teacher.setText("主讲人："+itemBean.getCourseTeacherName());
//        tv_subtitle.setText(itemBean.getCourseTeacherIntroduction());
    }

}
