package com.yingbao.career.ui.home.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.yingbao.career.R;
import com.yingbao.career.view.CornerImageView;
import com.zhpan.bannerview.holder.ViewHolder;

/**
 * <pre>
 *   Created by zhangpan on 2019-08-14.
 *   Description:
 * </pre>
 */
public class ImageResourceViewHolder implements ViewHolder<String> {

    private int roundCorner;

    public ImageResourceViewHolder(int roundCorner) {
        this.roundCorner = roundCorner;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_slide_mode;
    }

    @Override
    public void onBind(View itemView, String data, int position, int size) {
        CornerImageView imageView = itemView.findViewById(R.id.banner_image);
        Glide.with(imageView.getContext()).load(data).into(imageView);
        imageView.setRoundCorner(roundCorner);
    }

}
