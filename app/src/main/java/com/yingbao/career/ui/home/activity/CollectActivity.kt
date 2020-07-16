package com.yingbao.career.ui.home.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.easefun.polyvsdk.PolyvDownloader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.FavoriteListResult
import com.yingbao.career.ui.home.adapter.CollectListAdapter
import com.yingbao.career.utils.CareerSPHelper
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.video.PolyvPlayerActivity
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.white_titlebar.*

class CollectActivity : BaseActivity() {
    private lateinit var adapter: CollectListAdapter
    private var pageIndex = 1
    private val pageSize = 10
    private val list = ArrayList<FavoriteListResult.ResultBean.RecordsBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
        DisplayTools.setTransparentStatusBar(this)
        initData()
        loadData()
        initClick()
    }


    private fun initClick() {
        adapter.setOnItemClickListener({ adapter, view, position ->
            val intent = PolyvPlayerActivity.newIntent(
                this,
                0,
                list.get(position).video.videoName,
                list.get(position).video.videoId,
                true,
                false,
                PolyvDownloader.FILE_VIDEO
            )
            // 在线视频和下载的视频播放的时候只显示播放器窗口，用该参数来控制
//                intent.putExtra(PolyvMainActivity.IS_VLMS_ONLINE, false);
            // 在线视频和下载的视频播放的时候只显示播放器窗口，用该参数来控制
//                intent.putExtra(PolyvMainActivity.IS_VLMS_ONLINE, false);
            startActivity(intent)
           })
        refresh_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                pageIndex = 1
                loadData()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                pageIndex++
                loadData()
            }
        }
        )

    }

    private fun initData() {
        tv_title.text = "我的收藏"
        adapter = CollectListAdapter( R.layout.item_search_result,list)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_collect_list.layoutManager = layoutManager
        rv_collect_list.adapter = adapter
    }
    private fun loadData() {
        val map = HashMap<String, String>()
        map["pageNo"]= pageIndex.toString()
        map["pageSize" ]= pageSize.toString()
        map["userId" ]= CareerSPHelper.getUserId().toString()
        RetrofitFactory.getInstance()
            .getVideoFavoriteList(map, object : BaseObserver<FavoriteListResult.ResultBean>() {
                override fun onSuccees(resultBean: FavoriteListResult.ResultBean?) {
                    if (resultBean!=null){
                        if (pageIndex == 1) list.clear()
                        list.addAll(resultBean.records)
                        adapter.notifyDataSetChanged()
                    }
                    finishRefresh()
                }

                override fun onFailure(error: String, isNetWorkError: Boolean) {
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
}
