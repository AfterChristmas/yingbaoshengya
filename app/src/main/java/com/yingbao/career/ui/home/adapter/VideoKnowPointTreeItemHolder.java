package com.yingbao.career.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopeer.shadow.ShadowView;
import com.unnamed.b.atv.model.TreeNode;
import com.yingbao.career.R;
import com.yingbao.career.common.MyApplication;
import com.yingbao.career.http.resultbean.VideoKnowPointResult;
import com.yingbao.career.utils.DisplayTools;


/**
 * Created by Bogdan Melnychuk on 2/12/15.
 */
public class VideoKnowPointTreeItemHolder extends TreeNode.BaseNodeViewHolder<VideoKnowPointTreeItemHolder.IntelligenceIconTreeItem> {
    private TextView tvValue;
    private ImageView arrowView;
    private ShadowView shadow_view;

    public VideoKnowPointTreeItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IntelligenceIconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.wygkplus_knowpoint_item_tree_view, null, false);
        tvValue = (TextView) view.findViewById(R.id.label_TV);
        shadow_view =  view.findViewById(R.id.shadow_view);
        tvValue.setText(value.knowledgePointBean.getKnowledgePointName());
        arrowView = (ImageView) view.findViewById(R.id.expand_icon_IV);
        if (value.knowledgePointBean.getHasChild()!= 1) {
            arrowView.setVisibility(View.GONE);
        } else {
            arrowView.setVisibility(View.VISIBLE);
        }
        shadow_view.setShadowMarginTop(DisplayTools.dip2px(MyApplication.context, 0));
        shadow_view.setShadowMarginBottom(DisplayTools.dip2px(MyApplication.context, 0));
        shadow_view.setShadowMarginLeft(DisplayTools.dip2px(MyApplication.context, 0));
        shadow_view.setShadowMarginRight(DisplayTools.dip2px(MyApplication.context, 0));
        return view;
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setImageResource(active ? R.drawable.knowpoint_down : R.drawable.knowpoint_right);
    }

    public static class IntelligenceIconTreeItem {
        public VideoKnowPointResult.ResultBean knowledgePointBean;

        public IntelligenceIconTreeItem(VideoKnowPointResult.ResultBean knowledgePointBean) {
            this.knowledgePointBean = knowledgePointBean;
        }
    }
}
