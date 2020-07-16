package com.yingbao.career.video;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yingbao.career.R;
import com.yingbao.career.utils.PolyvScreenUtils;

public class PolyvPlayerTopFragment extends Fragment implements View.OnClickListener {
    //fragmentView
    private View view;
    // 返回按钮,分享按钮
    private ImageView iv_finish, iv_share;
    // 顶部布局
    private RelativeLayout rl_top;
    // popupwindowView
    private View popupwindow_view;
    // popupwindow
    private PopupWindow popupWindow;
    // 分享控件
    private LinearLayout ll_shareqq, ll_sharewechat, ll_shareweibo;
    private TextView tv_title;

    public static final String SHARE_TEXT = "http://www.polyv.net";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.polyv_fragment_player_top, container, false);
        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (PolyvScreenUtils.isLandscape(getActivity())) {
            rl_top.setVisibility(View.GONE);
        } else {
            rl_top.setVisibility(View.VISIBLE);
        }
    }

    private void findIdAndNew() {
        iv_finish = (ImageView) view.findViewById(R.id.iv_finish);
        iv_share = (ImageView) view.findViewById(R.id.iv_share);
        rl_top = (RelativeLayout) view.findViewById(R.id.rl_top);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
    }

    private void initView() {
//        tv_title.setText(title);
        tv_title.requestFocus();
        iv_finish.setOnClickListener(this);
        iv_share.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findIdAndNew();
        initView();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_share:
                popupWindow.showAsDropDown(iv_share, 0, 1);
                break;
            case R.id.iv_finish:
                getActivity().finish();
                break;
        }
    }
}
