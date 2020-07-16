package com.yingbao.career.ui.question.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yingbao.career.R;
import com.yingbao.career.http.BaseObserver;
import com.yingbao.career.http.RetrofitFactory;
import com.yingbao.career.http.resultbean.WrongRecordListBean;
import com.yingbao.career.ui.question.ErrorNoteFragmentAdapter;
import com.yingbao.career.ui.question.WrongDetailActivity;
import com.yingbao.career.utils.CareerSPHelper;
import com.yingbao.career.utils.EventMessageBean;
import com.yingbao.career.utils.EventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ErrorNoteFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ErrorNoteFragment";
    private static final String SUBJECTID = "subjectid";
    private ArrayList<WrongRecordListBean.ResultBean> list = new ArrayList<>();
    private RecyclerView rv_list;
    private Context mContext;
    private ErrorNoteFragmentAdapter errorNoteFragmentAdapter;
    private SmartRefreshLayout refresh_layout;
    private int pageIndex =1;
    private CheckBox cb_bottom_delete;
    private ImageView iv_bottom_delete;
    private ImageView iv_change_subject;
    private boolean isEdit = false;
    private RelativeLayout rl_bottom_delete;
    private int pageSize = 10;
    private int subjectId = 16;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public ErrorNoteFragment() {
    }


    public static ErrorNoteFragment newInstance(int  subjectId) {
        ErrorNoteFragment fragment = new ErrorNoteFragment();
        Bundle args = new Bundle();
        args.putInt(SUBJECTID, subjectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subjectId = getArguments().getInt(SUBJECTID);
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wygkplus_error_book_fragment, container, false);
        initView(view);
        initClick();
        return view;
    }

    /**
     * EventBus 的监听
     *
     * @param event
     */
    public void onEventMainThread(EventMessageBean event) {
        if (event.messageType == EventType.EDIT_ERROR_BOOK) {

        }
    }

    private void initView(View view) {
        cb_bottom_delete = view.findViewById(R.id.cb_bottom_delete);
        iv_bottom_delete = view.findViewById(R.id.iv_bottom_delete);
        iv_change_subject = view.findViewById(R.id.iv_change_subject);
        rv_list = view.findViewById(R.id.rv_list);
        rl_bottom_delete = view.findViewById(R.id.rl_bottom_delete);
        refresh_layout = view.findViewById(R.id.refresh_layout);
        errorNoteFragmentAdapter = new ErrorNoteFragmentAdapter(list);
        rv_list.setAdapter(errorNoteFragmentAdapter);
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        refresh_layout.setOnRefreshListener(refreshlayout -> {
            pageIndex = 1;
            loadData();
        });
        refresh_layout.setOnLoadMoreListener(refreshlayout -> {
            pageIndex++;
            loadData();
        });
        loadData();
    }
    /**
     * 结束刷新效果
     */
    private void finishRefresh() {
        refresh_layout.finishRefresh();
        refresh_layout.finishLoadMore();
    }
    private void initClick() {
        iv_change_subject.setOnClickListener(this);
        errorNoteFragmentAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(getActivity(), WrongDetailActivity.class);
            intent.putExtra("questionId", list.get(position).getQuestionId());
            intent.putExtra("recordChildId", list.get(position).getChildQuestionId());
            intent.putExtra("subjectName", list.get(position).getSubjectName());
            intent.putExtra("addTime", list.get(position).getAnswerTime());
            intent.putExtra("answer", list.get(position).getAnswerRecord());
            intent.putExtra("recordId", list.get(position).getId());
            intent.putExtra("subjectId", subjectId);
            startActivity(intent);
        });
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_change_subject){
            showChangeSubjectDialog();
        }
    }
    /**
     * 显示清空试题篮对话框
     */
    private void showChangeSubjectDialog() {
    }

    private void loadData() {
    Map<String,String> map = new HashMap<>();
        map.put("userId", String.valueOf(CareerSPHelper.getUserId()));
        map.put("pageNo", String.valueOf(pageIndex));
        map.put("pageSize", String.valueOf(pageSize));
        map.put("subjectId",String.valueOf(subjectId));
        RetrofitFactory.getInstance().getWrongRecordList(map, new BaseObserver<List<WrongRecordListBean.ResultBean>>() {
            @Override
            protected void onSuccees(List<WrongRecordListBean.ResultBean> resultBean)  {
                Log.e(TAG, "onSuccees: "+"成功");
                if (pageIndex == 1){
                    list.clear();
                }
                if (resultBean.size() != 0) {
                    list.addAll(resultBean);
                    errorNoteFragmentAdapter.notifyDataSetChanged();
                }
                finishRefresh();
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) {

            }
        });
    }
    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessageBean messageBean){
        if (messageBean.messageType == EventType.DELETE_WRONG_RECORD){
            if (subjectId == 0){
                for (int i = list.size() - 1; i >= 0; i--) {
                    if (list.get(i).getId() == messageBean.intContent){
                        list.remove(i);
                    }
                }
                if (list.size() == 0){
                    EventBus.getDefault().post(new EventMessageBean(EventType.RELOAD_WRONG_RECORD_TABS));
                }else {
                    errorNoteFragmentAdapter.notifyDataSetChanged();
                }
            }else {
                if (subjectId == messageBean.subIntContent){
                    for (int i = list.size() - 1; i >= 0; i--) {
                        if (list.get(i).getId() == messageBean.intContent){
                            list.remove(i);
                        }
                    }
                    if (list.size() == 0){
                        EventBus.getDefault().post(new EventMessageBean(EventType.RELOAD_WRONG_RECORD_TABS));
                    }else {
                        errorNoteFragmentAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

}
