package com.yingbao.career.ui.personal.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yingbao.career.R;
import com.yingbao.career.http.resultbean.LocationResultBean;

import java.util.List;


public class LocationItemAdapter extends BaseQuickAdapter<LocationResultBean.ResultBean, BaseViewHolder> {
    //private List<GetYourOwnerBeanResult.DataBean> data;
    private boolean isEdit = false;
    public LocationItemAdapter(@Nullable List<LocationResultBean.ResultBean> data) {
        super(R.layout.wygkplus_item_location, data);
        //this.data=data;
    }

    @Override
    protected void convert(BaseViewHolder helper, LocationResultBean.ResultBean item) {
        ImageView iv_select = helper.getView(R.id.iv_select);
        TextView tv_location = helper.getView(R.id.tv_location);
        tv_location.setText(item.getName());
    }

}
