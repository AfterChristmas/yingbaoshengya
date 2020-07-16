package com.yingbao.career.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.yingbao.career.R;
import com.yingbao.career.utils.DisplayTools;


public class IconImageChooseDialog extends DialogFragment implements View.OnClickListener {

    private OnChooseAddTypeListener onChooseAddTypeListener;

    public interface OnChooseAddTypeListener {
        void onTakePhoto();


        void onChooseImgs();
    }

    public void setOnChooseAddTypeListener(OnChooseAddTypeListener onChooseAddTypeListener) {
        this.onChooseAddTypeListener = onChooseAddTypeListener;
    }

    public IconImageChooseDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment_add_imgs, container);
        findViewsAndSetListener(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
        //设置背景为透明
        window.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), android.R.color.transparent));
        //减去状态栏高度
        int screenHeight = DisplayTools.getResolutionHeightInfo(getActivity());
        int statusBarHeight = DisplayTools.getStatusBarHeight(getContext());
        int dialogHeight = screenHeight - statusBarHeight;
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.dimAmount = 0.5f;
        window.setAttributes(layoutParams);

    }

    /**
     * 初始化界面并设置监听
     *
     * @param view
     */
    private void findViewsAndSetListener(View view) {
        TextView btnTakePhoto = view.findViewById(R.id.take_photo_tv);
        btnTakePhoto.setOnClickListener(this);
        TextView btnChooseImgs = view.findViewById(R.id.choose_imgs_tv);
        btnChooseImgs.setOnClickListener(this);
        TextView choose_cancel_tv = view.findViewById(R.id.choose_cancel_tv);
        choose_cancel_tv.setOnClickListener(this);
         view.findViewById(R.id.ll_all).setOnClickListener(view1->{
             dismiss();
         });
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        transaction.add(this, tag);
        int backStackId = transaction.commitAllowingStateLoss();
        return backStackId;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.take_photo_tv) {// 拍照
            if (onChooseAddTypeListener != null) {
                onChooseAddTypeListener.onTakePhoto();
            }
            this.dismiss();
        } else if (id == R.id.choose_imgs_tv) {// 选择图片
            if (onChooseAddTypeListener != null) {
                onChooseAddTypeListener.onChooseImgs();
            }
            this.dismiss();
        } else if (id == R.id.choose_cancel_tv) {// 选择取消
            this.dismiss();
        }
    }

}
