package com.yingbao.career.ui.question;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yingbao.career.R;
import com.yingbao.career.http.resultbean.WrongRecordListBean;
import com.yingbao.career.view.MyQuesView;

import java.util.List;


public class ErrorNoteFragmentAdapter extends BaseQuickAdapter<WrongRecordListBean.ResultBean, BaseViewHolder> {
    public ErrorNoteFragmentAdapter(@Nullable List<WrongRecordListBean.ResultBean> data) {
        super(R.layout.wygk_item_error_note, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WrongRecordListBean.ResultBean item) {
        MyQuesView wv_webview = helper.findView(R.id.wv_webview);
        TextView tv_subject = helper.findView(R.id.tv_subject);
        TextView tv_time = helper.findView(R.id.tv_time);
        wv_webview.setText(item.getQuestionStem());
        tv_time.setText(item.getAnswerTime());
        tv_subject.setText(item.getSubjectName());
    }

}
