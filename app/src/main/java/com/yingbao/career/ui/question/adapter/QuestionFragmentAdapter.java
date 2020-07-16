package com.yingbao.career.ui.question.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.yingbao.career.ui.question.bean.QuesDetail;
import com.yingbao.career.ui.question.fragment.QuestionFragment;

import java.util.List;

/**
 * @author ch
 * @date 2020/1/10 16:34
 * @desc
 */
public class QuestionFragmentAdapter extends FragmentStateAdapter {

    private List<QuesDetail> questionIdList;

    public QuestionFragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<QuesDetail> fragments) {
        super(fragmentActivity);
        this.questionIdList = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return QuestionFragment.Companion.newInstance(questionIdList.get(position));
    }

    @Override
    public int getItemCount() {
        return questionIdList == null ? 0 : questionIdList.size();
    }
}