package com.yingbao.career.ui.videodownload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arialyy.aria.core.Aria
import com.arialyy.aria.core.download.DownloadEntity
import com.yingbao.career.R
import com.yingbao.career.ui.videodownload.adapter.DownloadVideoAdapter
import com.yingbao.career.utils.EventMessageBean
import com.yingbao.career.utils.EventType
import kotlinx.android.synthetic.main.downloading_fragment.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class DownloadedFragment : Fragment() {
    private lateinit var errorNoteFragmentAdapter: DownloadVideoAdapter
    private val mData = mutableListOf<DownloadEntity>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.downloading_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        EventBus.getDefault().register(this)
        errorNoteFragmentAdapter =
            DownloadVideoAdapter(
                R.layout.item_downloaded_video,
                mData
            )
        rv_list.setAdapter(errorNoteFragmentAdapter)
        rv_list.setLayoutManager(LinearLayoutManager(activity))
        // 先注册需要点击的子控件id（注意，请不要写在convert方法里）
        errorNoteFragmentAdapter.addChildClickViewIds(R.id.tv_delete);
        errorNoteFragmentAdapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id==R.id.tv_delete){
                Aria.download(this).load(mData.get(position).id).cancel(true);
                mData.removeAt(position)
                errorNoteFragmentAdapter.notifyDataSetChanged()
            }
        }
        loadData()
    }

    private fun loadData() {
        val temps = Aria.download(this).allCompleteTask
        mData.clear()
        mData.addAll(temps)
        errorNoteFragmentAdapter.notifyDataSetChanged()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMain(event:EventMessageBean){
        if (event.messageType == EventType.DOWNLOAD_COMPLETE){
            loadData()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    companion object {
        fun newInstance(): DownloadedFragment {
            val fragment = DownloadedFragment()
            return fragment
        }
    }
}
