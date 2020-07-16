package com.yingbao.career.ui.question.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.easefun.polyvsdk.PolyvDownloader
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.VideoRecordCount
import com.yingbao.career.http.resultbean.VideoRecordTimeLine
import com.yingbao.career.ui.home.adapter.RecordVideoAdapter
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.utils.LogUtil
import com.yingbao.career.utils.TimeUtil
import com.yingbao.career.video.PolyvPlayerActivity
import com.yingbao.career.view.PieChartView
import com.yingbao.career.view.VideoRecordSpacesItemDecoration
import com.yingbao.career.view.WrapGridLayoutManager
import kotlinx.android.synthetic.main.header_video_record.*
import kotlinx.android.synthetic.main.video_record_fragment.*
import java.util.*
import java.util.Map.Entry.comparingByKey
import kotlin.collections.HashMap


class VideoRecordFragment : Fragment(), View.OnClickListener {
    private lateinit  var headView: View
    private lateinit  var chart_days: BarChart
    private lateinit  var pieChartView: PieChartView
    private lateinit var homeClassVideoAdapter: RecordVideoAdapter
    val list = ArrayList<VideoRecordTimeLine.ResultBean>()
    val subjectMap = TreeMap<String,Int>()
    val dayMap = TreeMap<String,Int>()
    val timeLineDayMap = HashMap<String,String>()
    private val  pageSize = 10
    private var pageIndex = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.video_record_fragment, container, false)
        initView(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        refresh_layout.setEnableLoadMore(true)
        refresh_layout.setEnableRefresh(false)
        loadData()
        initClick()
    }
    private fun initData() {
        val layoutManager = WrapGridLayoutManager(activity, 2)
        rv_list.setLayoutManager(layoutManager)
        val itemDecoration = VideoRecordSpacesItemDecoration(DisplayTools.dip2px(context, 10f),list)
        rv_list.addItemDecoration(itemDecoration)
        homeClassVideoAdapter = RecordVideoAdapter( list)
        homeClassVideoAdapter.setGridSpanSizeLookup{ gridLayoutManager,viewType,  position->
            list.get(position).spanSize
        }
        rv_list.setAdapter(homeClassVideoAdapter)
        homeClassVideoAdapter.addHeaderView(headView)
        TimeUtil.getDays(7).forEach {
            LogUtil.e("dayName ==",it)
            dayMap[it] = 0
        }
    }

    private fun loadData() {
        loadCountData()
        loadTimeLineData()
    }

    private fun loadTimeLineData() {
        val map = HashMap<String, String>()
        map["pageNo"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()
        RetrofitFactory.getInstance()
            .getVideoRecordTimeLines(map,
                object : BaseObserver<List<VideoRecordTimeLine.ResultBean>>() {
                    override fun onSuccees(datas: List<VideoRecordTimeLine.ResultBean>?) {
                        if (datas != null) {
                            if (pageIndex == 1) {
                                list.clear()
                            }
                            datas.forEachIndexed { _, resultBean ->
                                val dateTime = TimeUtil.long2ShortYMD(resultBean.playDate)
                                resultBean.setItemType(VideoRecordTimeLine.ResultBean.VIDEO)
                                resultBean.spanSize = VideoRecordTimeLine.ResultBean.VIDEO_SPAN_SIZE
                                if (!timeLineDayMap.containsKey(dateTime)){
                                    val timeResultBean = VideoRecordTimeLine.ResultBean()
                                    timeResultBean.setItemType(VideoRecordTimeLine.ResultBean.TIME)
                                    timeResultBean.spanSize = VideoRecordTimeLine.ResultBean.TIME_SPAN_SIZE
                                    timeResultBean.playDate = resultBean.playDate
                                    list.add(timeResultBean)
                                    timeLineDayMap.put(dateTime,dateTime)
                                }
                                list.add(resultBean)
                            }
                            LogUtil.e("listSize==",list.size.toString())
                            homeClassVideoAdapter.notifyDataSetChanged()
                            finishRefresh()
                        }
                    }

                    override fun onFailure(error: String?, isNetWorkError: Boolean) {
                        finishRefresh()
                    }

                })
    }

    private fun loadCountData() {
        RetrofitFactory.getInstance()
            .findUserVideoRecord(object : BaseObserver<VideoRecordCount.ResultBean>() {
                override fun onSuccees(t: VideoRecordCount.ResultBean?) {
                    if (t != null) {
                        tv_video_count.text = t.viewNum.toString()
                        tv_video_time.text = (t.viewTotalTime / 60).toString()
                        val dayViews = t.dayViews
                        dayViews.forEach {
                            LogUtil.e("dayView==", it.key + "::" + it.value)
                            if (dayMap.containsKey(it.key)) {
                                dayMap[it.key] = it.value
                            }
                        }
                        val subjectViews = t.subjectViews
                        subjectViews.forEach {
                            LogUtil.e("dayView==", it.key + "::" + it.value)
                            subjectMap[it.key] = it.value
                        }

                        initBarChart()
                        initPieChart()
                    }
                }

                override fun onFailure(error: String?, isNetWorkError: Boolean) {
                }

            })
    }

    /**
     * 点击update按钮开始绘制，每次随机数更换数值
     */
    fun initPieChart() {
        val list = ArrayList<PieChartView.Part>()
        //这里用随机数配置份儿，只是写入对应的值就行，会根据所有添加的数据进行百分比配置
        var totalTime = 0
        subjectMap.forEach {
          totalTime += it.value
        }
        var position = 0
        subjectMap.forEach {
            var time = it.value/60
            var timeString = if (time==0) {"${it.value}秒"}else {"${time}分钟"}
            var percent = it.value*100/totalTime
            list.add(PieChartView.Part("${it.key}:${timeString} $percent%", percent, resources.obtainTypedArray(R.array.subject_array_color).getColor(position,0)))
            position++
        }
        pieChartView.setPartsData(list)
    }

    private fun initView(view: View) {
        headView = LayoutInflater.from(activity).inflate(R.layout.header_video_record, rv_list, false)
        chart_days =headView.findViewById(R.id.chart_days)
        pieChartView =headView.findViewById(R.id.pieChartView)

    }

    private fun initClick() {
        homeClassVideoAdapter.setOnItemClickListener({ adapter, view, position ->
            val intent = PolyvPlayerActivity.newIntent(
                activity,
                0,
                list.get(position).videoId,
                list.get(position)!!.videoName,
                list.get(position)!!.polyVideoId,
                true,
                false,
                PolyvDownloader.FILE_VIDEO
            )
            startActivity(intent)
        })
        refresh_layout.setOnRefreshLoadMoreListener(object :OnRefreshLoadMoreListener{
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
    /**
     * 结束刷新效果
     */
    private fun finishRefresh() {
        refresh_layout.finishRefresh()
        refresh_layout.finishLoadMore()
    }
    override fun onClick(v: View) {
    }


    private fun initBarChart() {
        chart_days.getDescription().setEnabled(false) // 不显示描述
        chart_days.setExtraOffsets(10f, 10f, 10f, 10f) // 设置饼图的偏移量，类似于内边距 ，设置视图窗口大小
        // 设置是否可以缩放图表
        chart_days.setScaleEnabled(false)
        // 设置是否可以用手指移动图表
        chart_days.setDragEnabled(false)
        //设置是否显示边框
        setAxis() // 设置坐标轴
        setLegend() // 设置图例
        setData()  //  设置数据
    }

    /**
     * 因为此处的 barData.setBarWidth(0.3f);，也就是说柱子的宽度是0.3f
     * 所以第二个柱子的值要比第一个柱子的值多0.3f，这样才会并列显示两根柱子
     */
    private fun setData() {
        val sets = ArrayList<IBarDataSet>()
        // 此处有两个DataSet，所以有两条柱子，BarEntry（）中的x和y分别表示显示的位置和高度
        // x是横坐标，表示位置，y是纵坐标，表示高度
        val barEntries1 = ArrayList<BarEntry>()
        var position = 0
        dayMap.forEach {
            barEntries1.add(BarEntry(position.toFloat(), it.value.toFloat()))
            position++
        }
        val barDataSet1 = BarDataSet(barEntries1, "")
        barDataSet1.valueTextColor = Color.RED // 值的颜色
        barDataSet1.valueTextSize = 15f // 值的大小
        barDataSet1.color = Color.parseColor("#FFAD00") // 柱子的颜色
        barDataSet1.setDrawValues(false) // 不显示值
        barDataSet1.isHighlightEnabled = false
        barDataSet1.label = "min" // 设置标签之后，图例的内容默认会以设置的标签显示
        //禁用图例
        chart_days.getLegend().setEnabled(true)
        //禁用描述
        //        barChart.getDescription().setEnabled(false);
        chart_days.setBackgroundColor(Color.WHITE)
        // 设置柱子上数据显示的格式
        barDataSet1.valueFormatter =
            IValueFormatter { value, entry, dataSetIndex, viewPortHandler ->
                // 此处的value默认保存一位小数
                value.toString() + "min"
            }
        // 设置柱子上数据显示的格式
     /*   barDataSet1.setValueFormatter(ValueFormatter { value, entry, dataSetIndex, viewPortHandler ->
            // 此处的value默认保存一位小数
            value.toString() + "min"
        })*/
        sets.add(barDataSet1)

        val barData = BarData(sets)
        barData.barWidth = 0.85f // 设置柱子的宽度
        chart_days.setData(barData)
    }

    private fun setLegend() {
        val legend = chart_days.getLegend()
        legend.setFormSize(0f) // 图例的图形大小
        legend.setTextSize(10f) // 图例的文字大小
        legend.setTextColor(Color.parseColor("#666666"))
        legend.setDrawInside(true) // 设置图例在图中
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL) // 图例的方向为垂直
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT) //显示位置，水平右对齐
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP) // 显示位置，垂直上对齐
        // 设置水平与垂直方向的偏移量
        //        legend.setYOffset(85f);
        //        legend.setXOffset(30f);
    }

    private fun setAxis() {
        // 设置x轴
        val xAxis = chart_days.getXAxis()
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)  // 设置x轴显示在下方，默认在上方
        xAxis.setDrawGridLines(false) // 将此设置为true，绘制该轴的网格线。
        xAxis.setLabelCount(7)  // 设置x轴上的标签个数
        xAxis.setTextSize(10f) // x轴上标签的大小
        xAxis.setTextColor(Color.parseColor("#666666"))
        xAxis.setAxisLineWidth(0f)
        xAxis.setAxisLineColor(Color.parseColor("#E6E6E6"))
        val labelName = ArrayList<String>()
        var  maxTime  = 0
        var minTime = 0
        dayMap.forEach {
            LogUtil.e("x key",it.key)
            labelName.add(TimeUtil.getWeek(it.key))
            LogUtil.e("x key",TimeUtil.getWeek(it.key))
            if (it.value > maxTime) {
                maxTime = it.value
            }
            if (it.value < minTime) {
                minTime = it.value
            }
        }
        // 设置x轴显示的值的格式
        xAxis.valueFormatter =
            IAxisValueFormatter { value, axis ->
                if (value.toInt() < labelName.size) {
                    labelName[value.toInt()]
                } else {
                    ""
                }
            }
        xAxis.setYOffset(5f) // 设置标签对x轴的偏移量，垂直方向

        // 设置y轴，y轴有两条，分别为左和右
        val yAxis_right = chart_days.getAxisRight()
        //        yAxis_right.setAxisMaximum(1200f);  // 设置y轴的最大值
        //        yAxis_right.setAxisMinimum(0f);  // 设置y轴的最小值
        yAxis_right.setEnabled(false)  // 不显示右边的y轴

        val yAxis_left = chart_days.getAxisLeft()

        if (maxTime == 0 ){
            maxTime = 100
        }
        yAxis_left.setAxisMaximum(maxTime.toFloat())
        yAxis_left.setAxisMinimum(minTime.toFloat())
        yAxis_left.setTextSize(10f) // 设置y轴的标签大小
        //        yAxis_left.setAxisLineColor(Color.WHITE);
        //不显示轴线
        yAxis_left.setDrawAxisLine(false)
        yAxis_left.setTextColor(Color.parseColor("#666666"))
    }
    companion object {
        fun newInstance(): VideoRecordFragment {
            val fragment = VideoRecordFragment()
            return fragment
        }
    }

}
