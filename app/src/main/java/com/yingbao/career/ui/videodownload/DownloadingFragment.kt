package com.yingbao.career.ui.videodownload

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arialyy.annotations.Download
import com.arialyy.aria.core.Aria
import com.arialyy.aria.core.common.AbsEntity
import com.arialyy.aria.core.task.DownloadTask
import com.arialyy.aria.util.ALog
import com.yingbao.career.R
import com.yingbao.career.ui.videodownload.adapter.DownloadingVideoAdapter
import com.yingbao.career.utils.EventMessageBean
import com.yingbao.career.utils.EventType
import kotlinx.android.synthetic.main.downloading_fragment.*
import org.greenrobot.eventbus.EventBus


class DownloadingFragment : Fragment() {
    private val TAG = "DownloadingFragment"
    private lateinit  var mAdapter: DownloadingVideoAdapter
    private val mData = mutableListOf<AbsEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.downloading_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Aria.download(this).register()
        val temps = Aria.download(this).allNotCompleteTask
        if (temps != null && !temps.isEmpty()) {
            for (temp in temps) {
                ALog.d(TAG, "state = " + temp.state)
            }
            mData.addAll(temps)
        }
        mAdapter =
            DownloadingVideoAdapter(
                activity,
                mData
            )
        rv_list.setLayoutManager(LinearLayoutManager(activity))
        rv_list.setAdapter(mAdapter)
    }
    companion object {
        fun newInstance(): DownloadingFragment {
            val fragment = DownloadingFragment()
            return fragment
        }
    }
    @Download.onPre
    fun onPre(task: DownloadTask) {
        mAdapter.updateState(task.entity)
        Log.d(TAG, task.taskName + ", " + task.state)
    }

    @Download.onWait
    fun onWait(task: DownloadTask) {
        mAdapter.updateState(task.entity)
    }

    @Download.onTaskStart
    fun taskStart(task: DownloadTask) {
        Log.d(TAG, task.taskName + ", " + task.state)
        mAdapter.updateState(task.entity)
    }

    @Download.onTaskResume
    fun taskResume(task: DownloadTask) {
        Log.d(TAG, task.taskName + ", " + task.state)
        mAdapter.updateState(task.entity)
    }

    @Download.onTaskStop
    fun taskStop(task: DownloadTask) {
        mAdapter.updateState(task.entity)
    }

    @Download.onTaskCancel
    fun taskCancel(task: DownloadTask) {
        mAdapter.updateState(task.entity)
    }

    @Download.onTaskFail
    fun taskFail(task: DownloadTask?) {
        if (task == null || task.entity == null) {
            return
        }
        mAdapter.updateState(task.entity)
    }

    @Download.onTaskComplete
    fun taskComplete(task: DownloadTask) {
        mData.remove(task.entity)
        mAdapter.notifyDataSetChanged()
        EventBus.getDefault().post(EventMessageBean(EventType.DOWNLOAD_COMPLETE))
    }

    @Download.onTaskRunning
    fun taskRunning(task: DownloadTask) {
        mAdapter.setProgress(task.entity)
    }


}
