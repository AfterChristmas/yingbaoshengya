package com.yingbao.career.ui.home.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.easefun.polyvsdk.PolyvDownloader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.KnowPointVideoList
import com.yingbao.career.ui.home.adapter.KnowVideoListAdapter
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.video.PolyvPlayerActivity
import kotlinx.android.synthetic.main.activity_course_list.*
import kotlinx.android.synthetic.main.white_titlebar.*

class VideoListActivity : BaseActivity() {
    private val list = ArrayList<KnowPointVideoList.ResultBean.RecordsBean?>()
    private lateinit var adapter: KnowVideoListAdapter
    private var knowPointId = 0
    private var subjectId = 1001
    private var pageIndex = 0
    private val pageSize = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)
        DisplayTools.setTransparentStatusBar(this)
        tv_title.text = "视频列表"
        getBasicData()
        initData()
        loadData()
        initClick()
    }

    private fun getBasicData() {
        knowPointId = intent.getIntExtra(KNOWPOINTID,0)
        subjectId = intent.getIntExtra(SUBJECTID,1001)
    }


    private fun initClick() {
        refresh_layout.setEnableAutoLoadMore(true)
        refresh_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                pageIndex = 1
                loadData()
            }
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                pageIndex++
                loadData()
            }
        })
        adapter.setOnItemClickListener({ _, _, position ->
            val intent = PolyvPlayerActivity.newIntent(
                this,
                0,
                list.get(position)!!.videoName,
                list.get(position)!!.videoId,
                true,
                false,
                PolyvDownloader.FILE_VIDEO
            )
            startActivity(intent)
        })
    }

    private fun initData() {
        adapter = KnowVideoListAdapter(
            R.layout.item_know_video,
            list
        )
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_search.layoutManager = layoutManager
        rv_search.adapter = adapter
    }

    private fun loadData() {
        val map = HashMap<String, String>()
        map["pageNo"]= pageIndex.toString()
        map["pageSize" ]= pageSize.toString()
        map["knowledgePointId"] = knowPointId.toString()
        map["subjectId"] = subjectId.toString()
        RetrofitFactory.getInstance().getKnowPointVideoList(
            map,
            object : BaseObserver<KnowPointVideoList.ResultBean>() {
                override  fun onSuccees(resultBeans: KnowPointVideoList.ResultBean) {
                    if (resultBeans != null && resultBeans.size > 0) {
                        if (pageIndex == 1) list.clear()
                        list.addAll(resultBeans.records)
                        adapter.notifyDataSetChanged()
                        finishRefresh()
                    }
                }

                override fun onFailure(error: String,isNetWorkError: Boolean) {
                    finishRefresh()
                }
            })
    }

    /**
     * 结束刷新效果
     */
    private fun finishRefresh() {
        refresh_layout.finishRefresh()
        refresh_layout.finishLoadMore()
    }
    companion object{
        const val KNOWPOINTID = "knowPointId"
        const val SUBJECTID = "subjectId"
    }
}
