package com.yingbao.career.ui.home.activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.utils.DisplayTools
import kotlinx.android.synthetic.main.activity_smart_exercise.*
import kotlinx.android.synthetic.main.white_titlebar.*

class SmartExerciseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_exercise)
        DisplayTools.setTransparentStatusBar(this)
        initData()
    }



    private fun initData() {
        tv_title.text = "智能练习"
        val banner_width = (DisplayTools.getResolutionWidthInfo(applicationContext) - DisplayTools.dip2px(
            applicationContext, 20f
        )).toFloat()
        val banner_height = (banner_width / 2.18).toInt()
        val layoutParams = class_exercise.getLayoutParams() as LinearLayout.LayoutParams
        layoutParams.height = banner_height
        val knowLayoutParams = online_exercise.getLayoutParams() as LinearLayout.LayoutParams
        knowLayoutParams.height = banner_height
        val examParams = knowledge_exercise.getLayoutParams() as LinearLayout.LayoutParams
        examParams.height = banner_height
        online_exercise.setOnClickListener {
            val intent = Intent(SmartExerciseActivity@this,SubjectActivity::class.java)
            intent.putExtra(SubjectActivity.EXERCISE_TYPE,0)
            startActivity(intent)
        }
        knowledge_exercise.setOnClickListener {
            val intent = Intent(SmartExerciseActivity@this,SubjectActivity::class.java)
            intent.putExtra(SubjectActivity.EXERCISE_TYPE,1)
            startActivity(intent)
        }
        class_exercise.setOnClickListener {
            val intent = Intent(SmartExerciseActivity@this,SubjectActivity::class.java)
            intent.putExtra(SubjectActivity.EXERCISE_TYPE,2)
            startActivity(intent)
        }
    }
}
