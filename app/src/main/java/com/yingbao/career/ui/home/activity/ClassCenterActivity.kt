package com.yingbao.career.ui.home.activity

import android.content.Intent
import android.os.Bundle
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.ui.home.activity.SubjectActivity.Companion.EXERCISE_TYPE
import com.yingbao.career.utils.DisplayTools
import kotlinx.android.synthetic.main.activity_class_center.*
import kotlinx.android.synthetic.main.white_titlebar.*

class ClassCenterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_center)
        DisplayTools.setTransparentStatusBar(this)
        initData()
        syn_class.setOnClickListener {
            val intent  = Intent(this,CourseListActivity::class.java)
            intent.putExtra(CourseListActivity.SPECILE_TYPE,"103")
            intent.putExtra(CourseListActivity.SHOW_GRADE,true)
            startActivity(intent)
        }
        knowledge_unit.setOnClickListener {
           /* val intent  = Intent(this,CourseListActivity::class.java)
            intent.putExtra(CourseListActivity.Companion.SPECILE_TYPE,"105")
            startActivity(intent)*/
            val onLineIntent = Intent(this,SubjectActivity::class.java)
            onLineIntent.putExtra(EXERCISE_TYPE, 3)
            startActivity(onLineIntent)
        }
        review_exam.setOnClickListener {
            val intent  = Intent(this,CourseListActivity::class.java)
            intent.putExtra(CourseListActivity.SPECILE_TYPE,"104")
            intent.putExtra(CourseListActivity.SHOW_GRADE,false)
            startActivity(intent)
        }
        new_tutorial.setOnClickListener {
            val intent  = Intent(this,CourseListActivity::class.java)
            intent.putExtra(CourseListActivity.SPECILE_TYPE,"108")
            intent.putExtra(CourseListActivity.SHOW_GRADE,false)
            startActivity(intent)
        }
    }

    private fun initData() {
        tv_title.text = "课程中心"
    }
}
