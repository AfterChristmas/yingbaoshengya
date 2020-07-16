package com.yingbao.career.ui.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;
import com.yingbao.career.Base.BaseActivity;
import com.yingbao.career.R;
import com.yingbao.career.http.BaseObserver;
import com.yingbao.career.http.RetrofitFactory;
import com.yingbao.career.http.resultbean.VideoKnowPointResult;
import com.yingbao.career.ui.home.adapter.VideoKnowPointTreeItemHolder;
import com.yingbao.career.utils.DisplayTools;
import com.yingbao.career.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @Date: 2019/12/26 21:03
 * @Auther: wanyan
 */
public class VideoKnowPointsAty extends BaseActivity implements TreeNode.TreeNodeClickListener {
    private TextView tv_edit;
    private TextView tv_title;
    private int subjectId = 1001;
    private String subjectName = "语文";
    private static final String TAG = "KnowPointsAty";
    private RelativeLayout container;
    private TreeNode treeNode;
    private AndroidTreeView tView;
    List<VideoKnowPointResult.ResultBean> mResultBeans = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wygkplus_know_point_aty);
        DisplayTools.setTransparentStatusBar(this);
        initView();
        initKnowPointTree();
        initData();
        initClick();

    }


    private void initClick() {

    }

    private void initData() {
        loadKnowPoints(0);
    }

    private void initView() {
        subjectId = getIntent().getIntExtra("subjectId",1001);
        subjectName = getIntent().getStringExtra("subjectName");
        tv_edit = findViewById(R.id.tv_edit);
        tv_title = findViewById(R.id.tv_title);
        container = findViewById(R.id.container);
        tv_edit.setVisibility(View.GONE);
        tv_title.setText(subjectName);
    }
    private void initKnowPointTree() {
        treeNode = TreeNode.root();
        tView = new AndroidTreeView(this, treeNode);
        tView.setDefaultAnimation(false);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        tView.setDefaultViewHolder(VideoKnowPointTreeItemHolder.class);
        tView.setDefaultNodeClickListener(this);
        container.removeAllViews();
        container.addView(tView.getView(),
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
    }
    private void loadKnowPoints(int pid) {
        Map<String,String> map = new HashMap<>();
        map.put("pid", String.valueOf(pid));
        map.put("stageId", String.valueOf(4));
        map.put("subjectId",String.valueOf(subjectId));
        RetrofitFactory.getInstance().getVideoKnowPoint(map, new BaseObserver<List<VideoKnowPointResult.ResultBean>>() {
            @Override
            protected void onSuccees(List<VideoKnowPointResult.ResultBean> resultBeans)  {
                LogUtil.e(TAG, resultBeans.size()+"");
                mResultBeans.clear();
                mResultBeans.addAll(resultBeans);
                showKnowledgePoints();

            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) {
                Log.e(TAG, "onFailure: "+error );
            }
        });
    }

    @Override
    public void onClick(TreeNode node, Object value) {
        VideoKnowPointTreeItemHolder.IntelligenceIconTreeItem item = (VideoKnowPointTreeItemHolder.IntelligenceIconTreeItem) value;
        treeNode = node;
        if (!node.isExpanded() && treeNode.getChildren().isEmpty() && item.knowledgePointBean.getHasChild()== 1) {
            loadKnowPoints(item.knowledgePointBean.getId());
        } else if (item.knowledgePointBean.getHasChild()!=1) {
            Intent  intent = new Intent(VideoKnowPointsAty.this,VideoListActivity.class);
            intent.putExtra(VideoListActivity.KNOWPOINTID,item.knowledgePointBean.getId());
            intent.putExtra(VideoListActivity.SUBJECTID,item.knowledgePointBean.getSubject());
            startActivity(intent);
        }
    }


    /**
     * 显示知识点
     */
    private void showKnowledgePoints() {
        tView.setSelectionModeEnabled(true);
        TreeNode childTreeNode;
        for (VideoKnowPointResult.ResultBean knowledgePointBean : mResultBeans) {
//            if (knowledgePointBean.getType().equals("NODE")) {
                childTreeNode = new TreeNode(new VideoKnowPointTreeItemHolder.IntelligenceIconTreeItem(knowledgePointBean));
//            } else {
//                childTreeNode = new TreeNode(new TreeItemHolder.IntelligenceIconTreeItem(knowledgePointBean)).setViewHolder(new SelectableHeaderHolder(this));
//            }
            tView.addNode(treeNode, childTreeNode);
        }

    }
}
