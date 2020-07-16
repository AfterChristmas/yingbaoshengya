package com.yingbao.career.ui.question

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.common.MyApplication
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.RandomQuestionBean
import com.yingbao.career.ui.question.adapter.QuestionFragmentAdapter
import com.yingbao.career.ui.question.bean.QuesDetail
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.utils.StringTools
import com.yingbao.career.utils.TimerTools
import com.yingbao.career.utils.ToastUtil
import com.yingbao.career.view.MyQuesView
import kotlinx.android.synthetic.main.activity_online_test.*
import kotlinx.android.synthetic.main.item_home_video.tv_time


class KnowledgeTestActivity : BaseActivity(),View.OnClickListener {

    private lateinit var quesParentbodyWebv: MyQuesView
    private var kpId: Int = 0
    private var pageNo: Int = 1
    private var pageSize: Int = 5
    private lateinit var viewPageAdapter: QuestionFragmentAdapter
    private var timerTools: TimerTools? = null
    private  val quesList = mutableListOf<QuesDetail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online_test)
        DisplayTools.setTransparentStatusBar(this)
        initView()
        getBasicData()
        initData()
        loadQuestion(0)
    }

    private fun initView() {
        quesParentbodyWebv = MyQuesView(applicationContext)
        quesParentbodyWebv.setOverScrollMode(View.OVER_SCROLL_NEVER)
        quesParentbodyWebv.setTag(0)
        quesParentbodyWebv.setScrollBarSize(0)
        ques_parent_body_LL.addView(quesParentbodyWebv)
        btnSplit.setOnTouchListener(MoveSplit())
        ll_next_question.setOnClickListener(this)
    }

    private fun getBasicData() {
        kpId = intent.getIntExtra("kpId", 1001)
    }

    private fun loadQuestion(itemPosition:Int) {
        showWaitDialog()
        val map = HashMap<String,String>()
        map["kpId"] = kpId.toString()
        map["pageNo"] = pageNo.toString()
        map["pageSize"] = pageSize.toString()
        RetrofitFactory.getInstance().getKnowpointQuestion(map,object :BaseObserver<List<RandomQuestionBean.ResultBean>>() {
            override fun onSuccees(list: List<RandomQuestionBean.ResultBean>?) {
                var quesDetail: QuesDetail? = null
                if (list != null) {
                    if (list.size == 0) {
                        if (pageNo==1){
                            showWarnMessage("没有对应的题目哦！")
                            dismissWaitDialog()
                            finish()
                        }else{
                            showWarnMessage("题目已全部练习")
                        }
                        return
                    }
                    for (questionEntity in list) {
                        if (questionEntity.questionDetails.size == 1) {
                            val detailTemp = questionEntity.questionDetails.get(0);
                            quesDetail = QuesDetail()
                            quesDetail.id = detailTemp.id
                            quesDetail.isSelect = detailTemp.isSelect
                            quesDetail.quesBody =
                                detailTemp.stem.replace("【题文】", "")
                                    .replace("　", "&nbsp;")
                            quesDetail.quesAnswer =
                                detailTemp.answer.replace("【答案】", "")
                                    .replace("　", "&nbsp;")
                            quesDetail.quesParse =
                                detailTemp.analysis.replace("　", "&nbsp;")


                            val gosn = Gson()
                            val optionsObj = gosn.fromJson(
                                detailTemp.options,
                                RandomQuestionBean.ResultBean.QuestionDetailsBean.OptionEntity::class.java
                            )
                            if (optionsObj != null) {
                                if (optionsObj.getA() != null) {
                                    quesDetail.optionA =
                                        optionsObj.getA().replace("<br/>", "")
                                }
                                if (optionsObj.getB() != null) {
                                    quesDetail.optionB =
                                        optionsObj.getB().replace("<br/>", "")
                                }
                                if (optionsObj.getC() != null) {
                                    quesDetail.optionC =
                                        optionsObj.getC().replace("<br/>", "")
                                }
                                if (optionsObj.getD() != null) {
                                    quesDetail.optionD =
                                        optionsObj.getD().replace("<br/>", "")
                                }
                                if (optionsObj.getE() != null) {
                                    quesDetail.optionE =
                                        optionsObj.getE().replace("<br/>", "")
                                }
                                if (optionsObj.getF() != null) {
                                    quesDetail.optionF =
                                        optionsObj.getF().replace("<br/>", "")
                                }
                                if (optionsObj.getG() != null) {
                                    quesDetail.optionG =
                                        optionsObj.getG().replace("<br/>", "")
                                }
                            }
                            quesDetail.isCailiaoQues = 0
                            quesDetail.categoryId = questionEntity.subjectId
                            quesList.add(quesDetail)
                        } else {
                            var cailiaoContent = ""
                            var parentId = 0
                            for (i in questionEntity.questionDetails.indices) {
                                if (questionEntity.questionDetails.get(i).isCailiao == 1) {
                                    cailiaoContent = questionEntity.questionDetails.get(i).stem
                                    parentId = questionEntity.questionDetails.get(i).id
                                    break
                                }
                            }
                            questionEntity.questionDetails.forEach {
                                if (it.isCailiao == 0) {
                                    var quesDetail = QuesDetail()
                                    quesDetail.id = it.id
                                    quesDetail.isCailiaoQues = 1
                                    quesDetail.isSelect = it.isSelect
                                    quesDetail.categoryId = questionEntity.subjectId
                                    quesDetail.quesBody =
                                        it.stem.replace("【题文】", "")
                                            .replace("　", "&nbsp;")
                                    quesDetail.quesAnswer =
                                        it.answer.replace("【答案】", "")
                                            .replace("　", "&nbsp;")
                                    quesDetail.quesParse = it.analysis
                                        .replace("　", "&nbsp;")
                                    val gosn = Gson()
                                    val optionsObject = gosn.fromJson(
                                        it.options,
                                        RandomQuestionBean.ResultBean.QuestionDetailsBean.OptionEntity::class.java
                                    )
                                    if (optionsObject != null) {
                                        if (optionsObject.getA() != null) {
                                            quesDetail.optionA =
                                                optionsObject.getA()
                                                    .replace("<br/>", "")
                                        }
                                        if (optionsObject.getB() != null) {
                                            quesDetail.optionB =
                                                optionsObject.getB()
                                                    .replace("<br/>", "")
                                        }
                                        if (optionsObject.getC() != null) {
                                            quesDetail.optionC =
                                                optionsObject.getC()
                                                    .replace("<br/>", "")
                                        }
                                        if (optionsObject.getD() != null) {
                                            quesDetail.optionD =
                                                optionsObject.getD()
                                                    .replace("<br/>", "")
                                        }
                                        if (optionsObject.getE() != null) {
                                            quesDetail.optionE =
                                                optionsObject.getE()
                                                    .replace("<br/>", "")
                                        }
                                        if (optionsObject.getF() != null) {
                                            quesDetail.optionF =
                                                optionsObject.getF()
                                                    .replace("<br/>", "")
                                        }
                                        if (optionsObject.getG() != null) {
                                            quesDetail.optionG =
                                                optionsObject.getG()
                                                    .replace("<br/>", "")
                                        }
                                    }
                                    // 设置小题特有的属性
                                    quesDetail.parentQuesBody = cailiaoContent
                                    quesDetail.parentId = parentId
                                    quesList.add(quesDetail)
                                }
                            }
                        }
                        viewPageAdapter.notifyDataSetChanged()
                        view_pager2.setCurrentItem(itemPosition)
                        if (itemPosition == 0) {
                            startTimer()
                        }
                    }
                } else {
                    showWarnMessage("题目已全部练习")
                }

                dismissWaitDialog()
            }



            override fun onFailure(error: String?, isNetWorkError: Boolean) {
                showErrorMessage(error)
                dismissWaitDialog()
            }

        })
    }

    private fun initData() {
        view_pager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
        view_pager2.isUserInputEnabled = false
        viewPageAdapter =
            QuestionFragmentAdapter(
                this,
                quesList
            )
        view_pager2.setAdapter(viewPageAdapter)
        view_pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tv_count.text =  "第${position + 1}题"
                val ques: QuesDetail = quesList.get(position)
                if (ques.parentId !== 0) {
                    linerSplit.visibility = View.VISIBLE
                    if (ques.parentId !== quesParentbodyWebv.tag.toString().toInt()) {
                        quesParentbodyWebv.setText(ques.parentQuesBody)
                        quesParentbodyWebv.tag = ques.parentId
                        txtParentType.text = ques.parentQuesType
                    }
                } else {
                    linerSplit.visibility = View.GONE
                }
            }
        })

    }

    /**
     * 开始计时
     */
    private fun startTimer() {
        if (timerTools != null) {
            timerTools!!.setIsRun(false)
            timerTools = null
        }
        timerTools = TimerTools()
        timerTools!!.setRemainTime(0)
        timerTools!!.setHandler(object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what == TimerTools.SHOW_COUNTDOWN) {
                    try {
                        var spendTime = Integer.parseInt(msg.obj.toString())
                        tv_time.text = StringTools.generateBiaoZhunTime(spendTime)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
        timerTools!!.start()
    }

    /**
     * 停止倒计时
     */
    private fun stopTimer() {
        if (timerTools != null) {
            timerTools!!.setIsRun(false)
            timerTools = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    /**
     * [start]移动公共题干题的显示高度
     */
    inner   class MoveSplit : View.OnTouchListener {
        var lastY = 0f
        override fun onTouch(arg0: View,event: MotionEvent): Boolean {
            val bottom = DisplayTools.getResolutionHeightInfo(MyApplication.context) - arg0.height - DisplayTools.getStatusBarHeight(MyApplication.context)
            val top: Int = rl_titlebar.getBottom() + arg0.height +DisplayTools.getStatusBarHeight(MyApplication.context)
            val bottomY = 0
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    lastY = event.rawY
                    if (lastY >= bottom) {
                        lastY = bottom.toFloat()
                        return false
                    }
                    if (lastY <= top) {
                        lastY = top.toFloat()
                        return false
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    if (event.rawY > bottom) {
                        lastY = bottom.toFloat()
                        return false
                    }
                    if (event.rawY < top) {
                        lastY = top.toFloat()
                        return false
                    }
                    val dy = event.rawY - lastY
                    Log.v("a", event.rawY.toString() + "," + lastY)
                    if (dy > 1 || dy < 1) {
                        val lp: ViewGroup.LayoutParams = sView.getLayoutParams()
                        lp.height = lp.height + dy.toInt()
                        sView.setLayoutParams(lp)
                        Handler().postDelayed({ quesParentbodyWebv.postInvalidate() }, 10)
                    }
                    lastY = event.rawY
                }
                MotionEvent.ACTION_UP -> {
                }
            }
            Log.e("y", lastY.toString() + "")
            return false
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.ll_next_question ->{
                if (quesList.size== 0){
                    ToastUtil.showShort(applicationContext,"暂无练习题")
                    return
                }
                var currentItem = view_pager2.currentItem
                if (quesList.size>2){
                    currentItem++
                    if (currentItem<quesList.size){
                        if (currentItem == quesList.size-1){
                            pageNo++
                            loadQuestion(currentItem)
                        }else{
                            view_pager2.setCurrentItem(currentItem)
                        }

                    }else{
                        //需要加载数据
                        loadQuestion(currentItem)
                    }
                }else{
                    view_pager2.setCurrentItem(currentItem)
                }
            }
        }
    }
}
