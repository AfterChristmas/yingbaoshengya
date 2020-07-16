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
import com.yingbao.career.common.MyApplication
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.QuestionRecordCount
import com.yingbao.career.http.resultbean.VideoRecordCount
import com.yingbao.career.http.resultbean.VideoRecordTimeLine
import com.yingbao.career.ui.home.adapter.RecordVideoAdapter
import com.yingbao.career.utils.CareerSPHelper
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.utils.LogUtil
import com.yingbao.career.utils.TimeUtil
import com.yingbao.career.video.PolyvPlayerActivity
import com.yingbao.career.view.PieChartView
import com.yingbao.career.view.VideoRecordSpacesItemDecoration
import com.yingbao.career.view.WrapGridLayoutManager
import kotlinx.android.synthetic.main.header_video_record.*
import kotlinx.android.synthetic.main.question_record_fragment.*
import kotlinx.android.synthetic.main.question_record_fragment.refresh_layout
import kotlinx.android.synthetic.main.video_record_fragment.*
import java.util.*
import kotlin.collections.HashMap


class QuestionRecordFragment : Fragment(){

    private lateinit  var chart_days: BarChart
    private lateinit  var pieChartView: PieChartView
    val subjectMap = TreeMap<String,Int>()
    val dayMap = TreeMap<String,Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.question_record_fragment, container, false)
        chart_days = view.findViewById(R.id.chart_days)
        pieChartView = view.findViewById(R.id.pieChartView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        loadData()
    }
    private fun initData() {
        TimeUtil.getDays(7).forEach {
            LogUtil.e("dayName ==",it)
            dayMap[it] = 0
        }
    }

    private fun loadData() {
        loadCountData()
    }


    private fun loadCountData() {
        val map = HashMap<String,String>()
        map["userId"] = CareerSPHelper.getUserId().toString()
        RetrofitFactory.getInstance()
            .findUserQuestionRecord(map,object : BaseObserver<QuestionRecordCount.ResultBean>() {
                override fun onSuccees(t: QuestionRecordCount.ResultBean?) {
                    if (t != null) {
                        tv_question_count.text = t.questionNum.toString()
                        tv_right.text = (t.accuracy*100 / t.questionNum).toString()
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
            var percent = it.value*100/totalTime
            list.add(PieChartView.Part("${it.key}:${it.value}道 $percent%", percent, resources.obtainTypedArray(R.array.subject_array_color).getColor(position,0)))
            position++
        }
        pieChartView.setPartsData(list)
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
        barDataSet1.color = Color.parseColor("#0D6EFF") // 柱子的颜色
        barDataSet1.setDrawValues(false) // 不显示值
        barDataSet1.isHighlightEnabled = false
        barDataSet1.label = "道" // 设置标签之后，图例的内容默认会以设置的标签显示
        //禁用图例
        chart_days.getLegend().setEnabled(true)
        //禁用描述
        //        barChart.getDescription().setEnabled(false);
        chart_days.setBackgroundColor(Color.WHITE)
        // 设置柱子上数据显示的格式
        barDataSet1.valueFormatter =
            IValueFormatter { value, entry, dataSetIndex, viewPortHandler ->
                // 此处的value默认保存一位小数
                value.toString() + "道"
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
            LogUtil.e("x key", TimeUtil.getWeek(it.key))
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
        fun newInstance(): QuestionRecordFragment {
            val fragment = QuestionRecordFragment()
            return fragment
        }
    }

}
