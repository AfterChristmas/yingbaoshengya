package com.yingbao.career.dialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.yingbao.career.R;
import com.yingbao.career.common.CommonConstant;
import com.yingbao.career.ui.home.activity.WebActivity;
import com.yingbao.career.view.MyClickSpan;


public class UserAgreementDialog extends DialogFragment implements View.OnClickListener {

    private Context mContext;

    private TextView tvUserAgreement;
    private OnOperateListener onOperateListener;

    public static UserAgreementDialog newInstance() {

        Bundle args = new Bundle();

        UserAgreementDialog fragment = new UserAgreementDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_dialog_user_agreement, container);
        findViewsAndSetListener(view);
        setAgreementString();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().setCanceledOnTouchOutside(false);
    }

    /**
     * 初始化界面并设置监听
     *
     * @param view
     */
    private void findViewsAndSetListener(View view) {
        tvUserAgreement = view.findViewById(R.id.tv_user_agreement);
        Button btnAgree = view.findViewById(R.id.btn_agree);
        btnAgree.setOnClickListener(this);
        Button btnNotAgree = view.findViewById(R.id.btn_not_agree);
        btnNotAgree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_agree) {
            if (onOperateListener != null) {
                onOperateListener.onAgree();
            }
            this.dismiss();
        } else if (viewId == R.id.btn_not_agree) {
            if (onOperateListener != null) {
                onOperateListener.onNotAgree();
            }
            this.dismiss();
        }

    }

    private void setAgreementString() {
        MyClickSpan jzjxtAgreementSpan = new MyClickSpan(mContext) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra(WebActivity.URL, CommonConstant.URLConstant.AGREEMENT_URL);
                intent.putExtra(WebActivity.TITLE, getString(R.string.common_user_agreement));
                startActivity(intent);
            }
        };

        MyClickSpan privacyPolicySpan = new MyClickSpan(mContext) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra(WebActivity.URL, CommonConstant.URLConstant.PRIVACY_POLICY_URL);
                intent.putExtra(WebActivity.TITLE, getString(R.string.common_privacy_policy));
                startActivity(intent);
            }
        };

        String jzjxtAgreement = getString(R.string.main_jzjxt_agreement);
        String privacyPolicy = getString(R.string.main_privacy_policy);
        SpannableString jzjxtAgreementSpannable = new SpannableString(jzjxtAgreement);
        SpannableString privacyPolicySpannable = new SpannableString(privacyPolicy);
        jzjxtAgreementSpannable.setSpan(jzjxtAgreementSpan, 0, jzjxtAgreement.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        privacyPolicySpannable.setSpan(privacyPolicySpan, 0, privacyPolicy.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置字体前景色
        jzjxtAgreementSpannable.setSpan(new ForegroundColorSpan(Color.parseColor("#0D6EFF")), 0, jzjxtAgreement.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体前景色
        privacyPolicySpannable.setSpan(new ForegroundColorSpan(Color.parseColor("#0D6EFF")), 0, privacyPolicy.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvUserAgreement.append(getString(R.string.main_user_agreement_start));
        tvUserAgreement.append(jzjxtAgreementSpannable);
        tvUserAgreement.append(getString(R.string.main_agreement_and));
        tvUserAgreement.append(privacyPolicySpannable);
        tvUserAgreement.append(getString(R.string.main_user_agreement_end));
        tvUserAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        tvUserAgreement.setHighlightColor(Color.TRANSPARENT);
    }

    public interface OnOperateListener {
        void onAgree();

        void onNotAgree();
    }

    public UserAgreementDialog setOnOperateListener(OnOperateListener onOperateListener) {
        this.onOperateListener = onOperateListener;
        return this;
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        transaction.add(this, tag);
        int backStackId = transaction.commitAllowingStateLoss();
        return backStackId;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout((int) (dm.widthPixels * 0.9),
                getDialog().getWindow().getAttributes().height);
    }

}
