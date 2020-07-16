package com.yingbao.career.ui.question.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.BooleanDataBean
import com.yingbao.career.ui.question.bean.QuesDetail
import com.yingbao.career.view.MyQuesView
import kotlinx.android.synthetic.main.fragment_question.*

/**
 * @Description:
 * @Date: 2020/4/19 12:58
 * @Auther: wanyan
 */
class QuestionFragment : Fragment() {

    private lateinit var quesParseWebv: MyQuesView
    private lateinit var quesBodyWebv: MyQuesView
    private lateinit var quesDetail: QuesDetail
    private var clickCount = 0

    companion object {
        val QUESTION = "question"
        fun newInstance(question: QuesDetail): QuestionFragment? {
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putSerializable(QUESTION, question)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    fun getLayoutId(): Int {
        return R.layout.fragment_question
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            quesDetail = arguments!!.getSerializable(QUESTION) as QuesDetail
            setBodyOption()
        }

    }

    private fun setBodyOption() {
        // 题文
        quesBodyWebv = MyQuesView(activity)
        quesBodyWebv.setBackgroundColor(Color.parseColor("#F9FAFB")); // 设置背景色
        ques_content_layout.addView(quesBodyWebv)
        setQuesBody()
        if (quesDetail.isSelect ==0){

        }else if (quesDetail.isSelect ==1){
            setSingleChoiceOptions()
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
        if (optionA != null && optionA.trim { it <= ' ' } != "") {
            setSingleSelectionView("A", optionA)
        }
        if (optionB != null && optionB.trim { it <= ' ' } != "") {
            setSingleSelectionView("B", optionB)
        }
        if (optionC != null && optionC.trim { it <= ' ' } != "") {
            setSingleSelectionView("C", optionC)
        }
        if (optionD != null && optionD.trim { it <= ' ' } != "") {
            setSingleSelectionView("D", optionD)
        }
        if (optionE != null && optionE.trim { it <= ' ' } != "") {
            setSingleSelectionView("E", optionE)
        }
        if (optionF != null && optionF.trim { it <= ' ' } != "") {
            setSingleSelectionView("F", optionF)
        }
        if (optionG != null && optionG.trim { it <= ' ' } != "") {
            setSingleSelectionView("G", optionG)
        }

    }
    private val optionViewList = mutableListOf<MyQuesView>()


    /**
     * 设置单选题显示
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun setSingleSelectionView(option: String, optionContent: String) {
        val abcdLayout =
            View.inflate(activity,R.layout.layout_ques_options, null)
        val btnOption =
            abcdLayout.findViewById<View>(R.id.option_BTN) as Button
        val btn_option =
            abcdLayout.findViewById<View>(R.id.btn_option) as Button
        val rl_option =
            abcdLayout.findViewById<View>(R.id.rl_option) as RelativeLayout
        val mqvOptionContent =
            abcdLayout.findViewById<View>(R.id.web_option) as MyQuesView
        btnOption.text = option
        if (quesDetail.quesDoneAnswer.contains(option)) {
            setOptionChoseState(btnOption,rl_option,true)
            setAnswerCorrectState(quesDetail.quesAnswer.contains(option))
            setParseView()
        } else {
            setOptionChoseState(btnOption,rl_option,false)
        }
        mqvOptionContent.setText(optionContent)
        optionViewList.add(mqvOptionContent)
        abcdLayout.tag = option
        abcdLayout.setOnClickListener(
            SingleClickListener(
                btnOption,
                rl_option,
                option
            )
        )
        btnOption.setOnClickListener(
            SingleClickListener(
                btnOption,
                rl_option,
                option
            )
        )
        btn_option.setOnClickListener(
            SingleClickListener(
                btnOption,
                rl_option,
                option
            )
        )
        ques_options_layout.addView(abcdLayout)
    }
    private fun setOptionChoseState(textView: TextView,relativeLayout: RelativeLayout,chooseState:Boolean){
        if (chooseState){
            textView.setTextColor(Color.WHITE)
            relativeLayout.setBackgroundColor(Color.parseColor("#FFAD00"))
        }else{
            relativeLayout.setBackgroundColor(Color.WHITE)
            textView.setTextColor(Color.parseColor("#242424"))
        }
    }
    private fun setAnswerCorrectState(isRight:Boolean){
        shadow_answer_state.visibility = View.VISIBLE
        if (isRight){
            rl_answer_state.setBackgroundColor(activity!!.resources.getColor(R.color.career_17CF89))
            iv_answer_state.setImageResource(R.drawable.answer_right)
            tv_answer_state.setText(activity!!.getString(R.string.answer_right))
        }else{
            rl_answer_state.setBackgroundColor(activity!!.resources.getColor(R.color.career_FFC3C3))
            iv_answer_state.setImageResource(R.drawable.answer_wrong)
            tv_answer_state.setText(activity!!.getString(R.string.answer_error))
        }

    }

    private fun setParseView() {
        ques_parse_layout.visibility = View.VISIBLE
        quesParseWebv = MyQuesView(activity)
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
     * 单击监听
     *
     * @author Administrator
     */
    inner class SingleClickListener(
        private val btnSelect: Button,
        private val rl_option: RelativeLayout,
        private val strChoose: String
    ) :
        View.OnClickListener {
        override fun onClick(v: View) {
            if (TextUtils.isEmpty(quesDetail.quesDoneAnswer)){
                quesDetail.setQuesDoneAnswer(strChoose)
                setOptionChoseState(btnSelect,rl_option,true)
                setAnswerCorrectState(quesDetail.quesAnswer.contains(strChoose))
                setParseView()
                addRecord(strChoose,quesDetail.quesAnswer.contains(strChoose))
            }
//            insertStudentAnswer(strChoose)
//            if (onSwitchQuesListener != null) {
//                onSwitchQuesListener.onSwitchQues()
//            }
        }

    }

    private fun addRecord(option:String,rightStatus:Boolean) {
        val  param = HashMap<String,String>()
        param["answerRecord"]=option
        if (quesDetail.isCailiaoQues==0){
            param["questionId"]=quesDetail.id.toString()
        }else{
            param["questionId"]=quesDetail.parentId.toString()
            param["childQuestionId"]=  quesDetail.id.toString()
        }
        param["rightStatus"]= if (rightStatus) 1.toString() else (-1).toString()
        param["subjectId"]=quesDetail.categoryId.toString()
        RetrofitFactory.getInstance().addQuestionRecord(param,object :BaseObserver<BooleanDataBean.ResultBean>(){
            override fun onSuccees(t: BooleanDataBean.ResultBean?) {
            }

            override fun onFailure(error: String?, isNetWorkError: Boolean) {
            }

        })

    }
}