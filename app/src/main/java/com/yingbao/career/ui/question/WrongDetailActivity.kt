package com.yingbao.career.ui.question

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import com.google.gson.Gson
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.common.MyApplication
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.BooleanDataBean
import com.yingbao.career.http.resultbean.QuestionInfoResult
import com.yingbao.career.http.resultbean.RandomQuestionBean
import com.yingbao.career.ui.question.bean.QuesDetail
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.utils.EventMessageBean
import com.yingbao.career.utils.EventType
import com.yingbao.career.view.MyQuesView
import kotlinx.android.synthetic.main.activity_online_test.btnSplit
import kotlinx.android.synthetic.main.activity_online_test.linerSplit
import kotlinx.android.synthetic.main.activity_online_test.ques_parent_body_LL
import kotlinx.android.synthetic.main.activity_online_test.rl_titlebar
import kotlinx.android.synthetic.main.activity_online_test.sView
import kotlinx.android.synthetic.main.activity_wrong_detail.*
import kotlinx.android.synthetic.main.fragment_question.ll_question_parse
import kotlinx.android.synthetic.main.fragment_question.ques_content_layout
import kotlinx.android.synthetic.main.fragment_question.ques_options_layout
import kotlinx.android.synthetic.main.fragment_question.ques_parse_layout
import kotlinx.android.synthetic.main.white_titlebar.*
import org.greenrobot.eventbus.EventBus


class WrongDetailActivity : BaseActivity(), View.OnClickListener {

    private var subjectName: String = ""
    private var addTime: String = ""
    private var answer: String = ""
    private lateinit var quesParseWebv: MyQuesView
    private var recordChildId = 0
    private lateinit var quesBodyWebv: MyQuesView
    private lateinit var quesParentbodyWebv: MyQuesView
    private lateinit var quesWebOption: MyQuesView
    private var questionId: Int = 0
    private var recordId: Int = 0
    private var subjectId: Int = 0
    private lateinit var quesDetail: QuesDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrong_detail)
        DisplayTools.setTransparentStatusBar(this)
        initView()
        getBasicData()
        initData()
        loadQuestion()
    }

    private fun initView() {
        tv_title.text = "错题本"
        tv_edit.visibility = View.VISIBLE
        tv_edit.text = "移除"
        quesParentbodyWebv = MyQuesView(applicationContext)
        quesParentbodyWebv.setOverScrollMode(View.OVER_SCROLL_NEVER)
        quesParentbodyWebv.setTag(0)
        quesParentbodyWebv.setScrollBarSize(0)
        ques_parent_body_LL.addView(quesParentbodyWebv)
        btnSplit.setOnTouchListener(MoveSplit())
        tv_edit.setOnClickListener(this)
    }

    private fun getBasicData() {
        questionId = intent.getIntExtra("questionId", 0)
        recordChildId = intent.getIntExtra("recordChildId", 0)
        recordId = intent.getIntExtra("recordId", 0)
        subjectId = intent.getIntExtra("subjectId", 0)
        subjectName = intent.getStringExtra("subjectName")
        addTime = intent.getStringExtra("addTime")
        answer = intent.getStringExtra("answer")
        tv_time.text = addTime
        tv_subject.text = subjectName
    }

    private fun loadQuestion() {
        showWaitDialog()
        val map = HashMap<String, String>()
        map["questionId"] = questionId.toString()
        RetrofitFactory.getInstance()
            .getQuestionInfo(map, object : BaseObserver<List<QuestionInfoResult.ResultBean>>() {
                override fun onSuccees(list: List<QuestionInfoResult.ResultBean>?) {
                    if (list != null) {

                        if (list != null) {
                            if (list.size == 1) {
                                val detailTemp = list.get(0);
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
                                quesDetail.categoryId = questionId
                                setSplitView("")
                                setBodyOption()
                            } else {
                                var cailiaoContent = ""
                                var parentId = 0
                                for (i in list.indices) {
                                    if (list.get(i).isCailiao == 1) {
                                        cailiaoContent = list.get(i).stem
                                        parentId = list.get(i).id
                                        break
                                    }
                                }
                                list.forEach {
                                    if (it.isCailiao == 0 && it.id == recordChildId) {
                                        quesDetail = QuesDetail()
                                        quesDetail.id = it.id
                                        quesDetail.isCailiaoQues = 1
                                        quesDetail.isSelect = it.isSelect
                                        quesDetail.categoryId = questionId
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
                                        //设置
                                        setSplitView(cailiaoContent)
                                        setBodyOption()
                                    }
                                }
                            }

                        }

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
    }

    /**
     * [start]移动公共题干题的显示高度
     */
    inner class MoveSplit : View.OnTouchListener {
        var lastY = 0f
        override fun onTouch(arg0: View, event: MotionEvent): Boolean {
            val bottom =
                DisplayTools.getResolutionHeightInfo(MyApplication.context) - arg0.height - DisplayTools.getStatusBarHeight(
                    MyApplication.context
                )
            val top: Int = rl_titlebar.getBottom() + arg0.height + DisplayTools.getStatusBarHeight(
                MyApplication.context
            )
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

    private fun setSplitView(str: String) {
        if (!TextUtils.isEmpty(str)) {
            linerSplit.visibility = View.VISIBLE
            quesParentbodyWebv.setText(str)
        } else {
            linerSplit.visibility = View.GONE
        }
    }

    private fun setBodyOption() {
        // 题文
        quesBodyWebv = MyQuesView(this)
        quesBodyWebv.setBackgroundColor(Color.parseColor("#F9FAFB")); // 设置背景色
        ques_content_layout.addView(quesBodyWebv)
        setQuesBody()
        if (quesDetail.isSelect == 0) {

        } else if (quesDetail.isSelect == 1) {
            setSingleChoiceOptions()
            setMyAnswer();
        }
        setParseView()
    }

    private fun setMyAnswer() {
        if (!TextUtils.isEmpty(answer)) {
            ll_answer.visibility = View.VISIBLE
            tv_answer.text = answer
        }
    }

    private fun setParseView() {
        ques_parse_layout.visibility = View.VISIBLE
        quesParseWebv = MyQuesView(this)
        quesParseWebv.setBackgroundColor(Color.parseColor("#F9FAFB")); // 设置背景色
        ll_question_parse.addView(quesParseWebv)
        val quesParse = quesDetail.quesParse
        if (quesParse != null && !quesParse.equals("")) {
            quesParseWebv.setText(quesParse)
        } else {
            quesParseWebv.setText("暂无解析")
        }
    }

    /**
     * 设置题文显示
     */
    private fun setQuesBody() {
        val quesBody = quesDetail.quesBody
        if (quesBody != null && !quesDetail.quesBody.equals("")) {
            ques_content_layout.setVisibility(View.VISIBLE)
            quesBodyWebv.setText(quesBody)
        } else {
            ques_content_layout.setVisibility(View.GONE)
        }
    }

    /**
     * 设置单选题选项
     */
    private fun setSingleChoiceOptions() {
        val optionA = quesDetail.optionA
        val optionB = quesDetail.optionB
        val optionC = quesDetail.optionC
        val optionD = quesDetail.optionD
        val optionE = quesDetail.optionE
        val optionF = quesDetail.optionF
        val optionG = quesDetail.optionG
        val sb = StringBuffer()
        if (optionA != null && optionA.trim { it <= ' ' } != "") {
            sb.append("A: $optionA<br/>")
        }
        if (optionB != null && optionB.trim { it <= ' ' } != "") {
            sb.append("B: $optionB<br/>")
        }
        if (optionC != null && optionC.trim { it <= ' ' } != "") {
            sb.append("C: $optionC<br/>")
        }
        if (optionD != null && optionD.trim { it <= ' ' } != "") {
            sb.append("D: $optionD<br/>")
        }
        if (optionE != null && optionE.trim { it <= ' ' } != "") {
            sb.append("E: $optionE<br/>")
        }
        if (optionF != null && optionF.trim { it <= ' ' } != "") {
            sb.append("F $optionF<br/>")
        }
        if (optionG != null && optionG.trim { it <= ' ' } != "") {
            sb.append("G $optionG<br/>")
        }
        quesWebOption = MyQuesView(this)
        quesWebOption.setBackgroundColor(Color.parseColor("#F9FAFB")); // 设置背景色
        quesWebOption.setText(sb.toString())
        ques_options_layout.addView(quesWebOption)
    }

    private val optionViewList = mutableListOf<MyQuesView>()


    /**
     * 设置单选题显示
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun setSingleSelectionView(option: String, optionContent: String) {
        val abcdLayout =
            View.inflate(this, R.layout.layout_ques_options, null)
        val btnOption =
            abcdLayout.findViewById<View>(R.id.option_BTN) as Button
        val btn_option =
            abcdLayout.findViewById<View>(R.id.btn_option) as Button
        val rl_option =
            abcdLayout.findViewById<View>(R.id.rl_option) as RelativeLayout
        val mqvOptionContent =
            abcdLayout.findViewById<View>(R.id.web_option) as MyQuesView
        btnOption.text = option
        mqvOptionContent.setText(optionContent)
        optionViewList.add(mqvOptionContent)
        abcdLayout.tag = option
        ques_options_layout.addView(abcdLayout)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_edit -> {
                deleteWrongRecord()
            }
        }
    }

    private fun deleteWrongRecord() {
        showWaitDialog("正在删除")
        val param = HashMap<String, String>()
        param["id"] = recordId.toString()
        RetrofitFactory.getInstance().deleteWrongRecord(param,object :BaseObserver<BooleanDataBean.ResultBean>(){
            override fun onSuccees(t: BooleanDataBean.ResultBean?) {
                dismissWaitDialog()
                val messageBean = EventMessageBean(EventType.DELETE_WRONG_RECORD)
                messageBean.intContent = recordId
                messageBean.subIntContent = subjectId
                EventBus.getDefault().post(messageBean)
                finish()
            }

            override fun onFailure(error: String?, isNetWorkError: Boolean) {
                showErrorMessage(error)
            }

        })
    }
}
