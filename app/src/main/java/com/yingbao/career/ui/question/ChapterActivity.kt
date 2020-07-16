package com.yingbao.career.ui.question

import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import com.unnamed.b.atv.model.TreeNode
import com.unnamed.b.atv.model.TreeNode.TreeNodeClickListener
import com.unnamed.b.atv.view.AndroidTreeView
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.CourseListResultBean
import com.yingbao.career.http.resultbean.VideoKnowPointResult
import com.yingbao.career.http.resultbean.VideoListResultBean
import com.yingbao.career.ui.home.adapter.VideoKnowPointTreeItemHolder
import com.yingbao.career.utils.CareerSPHelper
import com.yingbao.career.utils.DisplayTools
import kotlinx.android.synthetic.main.activity_chapter_point.*
import kotlinx.android.synthetic.main.white_titlebar.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.set

class ChapterActivity : BaseActivity(), TreeNodeClickListener {
    private var pageIndex = 1
    private val pageSize = 100
    private var specialType = "103"
    private var subjectId = 0
    private lateinit var treeNode: TreeNode
    private lateinit var tView: AndroidTreeView
    var list: MutableList<VideoKnowPointResult.ResultBean> =ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_point)
        DisplayTools.setTransparentStatusBar(this)
        tv_title.text = "课本章节"
        getBasicData()
        initKnowPointTree()
        loadData()
    }

    private fun getBasicData() {
        subjectId = intent.getIntExtra("subjectId",1001)
    }

    private fun initKnowPointTree() {
        treeNode = TreeNode.root()
        tView = AndroidTreeView(this, treeNode)
        tView.setDefaultAnimation(false)
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom)
        tView.setDefaultViewHolder(VideoKnowPointTreeItemHolder::class.java)
        tView.setDefaultNodeClickListener(this)
        rl_container.removeAllViews()
        rl_container.addView(
            tView.getView(),
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
    }
    private fun loadData() {
        val map = HashMap<String, String>()
        map["pageNo"]= pageIndex.toString()
        map["pageSize" ]= pageSize.toString()
        map["specialType"] = specialType
        map["grade"] = CareerSPHelper.getUserGrade().toString()
        map["subject"] = subjectId.toString()
        RetrofitFactory.getInstance()
            .getCourseList(map, object : BaseObserver<CourseListResultBean.ResultBean>() {
                override fun onSuccees(resultBean: CourseListResultBean.ResultBean) {
                    if(resultBean!=null){
                        resultBean.records.forEach {
                            val  point = VideoKnowPointResult.ResultBean()
                            point.pid = 0
                            point.hasChild = 1
                            point.id = it.courseId
                            point.knowledgePointName = it.courseName
                            list.add(point)
                        }
                        showKnowledgePoints()
                    }

                }

                override fun onFailure(error: String, isNetWorkError: Boolean) {
                }
            })
    }

    override fun onClick(node: TreeNode, value: Any) {
        val item = value as VideoKnowPointTreeItemHolder.IntelligenceIconTreeItem
        treeNode = node
        if (!node.isExpanded && treeNode.children.isEmpty() && item.knowledgePointBean.hasChild == 1) {
            loadPointList(item.knowledgePointBean.id)
        } else if (item.knowledgePointBean.hasChild != 1) {
            if (item.knowledgePointBean.id==0){
                showWarnMessage("该章节暂无练习题")
                return
            }
            val intent = Intent(this, KnowledgeTestActivity::class.java)
            intent.putExtra("subjectId", subjectId)
            intent.putExtra("kpId", item.knowledgePointBean.id)
            startActivity(intent)
        }
    }

    /**
     * 显示知识点
     */
    private fun showKnowledgePoints() {
        tView.isSelectionModeEnabled = true
        var childTreeNode: TreeNode
        for (knowledgePointBean in list) {
            childTreeNode = TreeNode(VideoKnowPointTreeItemHolder.IntelligenceIconTreeItem(knowledgePointBean))
            tView.addNode(treeNode, childTreeNode)
        }
    }
    private fun loadPointList(courseId:Int) {
        RetrofitFactory.getInstance()
            .getVideoList(
                courseId.toString(),
                object : BaseObserver<VideoListResultBean.ResultBean?>() {
                    override fun onSuccees(resultBean: VideoListResultBean.ResultBean?) {
                        if(resultBean!=null){
                            list.clear()
                            resultBean.videos.forEach {
                                val  point = VideoKnowPointResult.ResultBean()
                                point.hasChild = 0
                                point.pid = courseId
                                point.id = it.knowledgePointId
                                point.knowledgePointName = it.videoName
                                list.add(point)
                            }
                            showKnowledgePoints()
                        }
                    }

                    override fun onFailure(error: String,isNetWorkError: Boolean) {

                    }
                })
    }
}
