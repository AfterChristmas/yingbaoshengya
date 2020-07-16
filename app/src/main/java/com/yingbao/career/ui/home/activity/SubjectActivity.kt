package com.yingbao.career.ui.home.activity

import android.content.Intent
import android.os.Bundle
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.ui.home.adapter.SubjectAdapter
import com.yingbao.career.ui.home.bean.SubjectItemBean
import com.yingbao.career.ui.question.ChapterActivity
import com.yingbao.career.ui.question.OnlineTestActivity
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.view.SubjectSpacesItemDecoration
import com.yingbao.career.view.WrapGridLayoutManager
import kotlinx.android.synthetic.main.activity_subject.*
import kotlinx.android.synthetic.main.white_titlebar.*

class SubjectActivity : BaseActivity() {

    companion object{
        var EXERCISE_TYPE = "exercise_type"
    }
    val list = ArrayList<SubjectItemBean>()
    lateinit var subjectAdapter: SubjectAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)
        DisplayTools.setTransparentStatusBar(this)
        initData()
    }

    private fun initData() {
        var exerciseType = intent.getIntExtra(EXERCISE_TYPE,0)
        when(exerciseType){
            0-> tv_title.text = "在线练习"
            1-> tv_title.text = "知识点专项练习"
            2-> tv_title.text = "课堂练习"
            3-> tv_title.text = "知识点课"
        }
        val layoutManager = WrapGridLayoutManager(this, 3)
        val itemDecoration = SubjectSpacesItemDecoration(DisplayTools.dip2px(applicationContext, 15f))
        rv_subjects.setLayoutManager(layoutManager)
        rv_subjects.addItemDecoration(itemDecoration)
        subjectAdapter = SubjectAdapter(R.layout.item_subject, list)
        rv_subjects.setAdapter(subjectAdapter)

        val subjectNameArray = resources.getStringArray(R.array.subject_name_array)
        val subjectIdsArray = resources.getIntArray(R.array.subject_ids_array)
        subjectNameArray.forEach {
            val subjectItemBean = SubjectItemBean()
            subjectItemBean.subjectName = it
            list.add(subjectItemBean)
        }
        subjectIdsArray.forEachIndexed { index, s ->
            list[index].subjectId = s
            list[index].subjectIndex = index
        }

        subjectAdapter.notifyDataSetChanged()
        subjectAdapter.setOnItemClickListener { adapter, view, position ->
            when(exerciseType){
                0 -> {
                    val intent = Intent(this@SubjectActivity, OnlineTestActivity::class.java)
                    intent.putExtra("subjectId", list[position].subjectId)
                    intent.putExtra("subjectName", list[position].subjectName)
                    startActivity(intent)
                }
                1 -> {
                    val intent = Intent(this@SubjectActivity, TestKnowPointsAty::class.java)
                    intent.putExtra("subjectId",list[position].subjectId)
                    intent.putExtra("subjectName",list[position].subjectName)
                    startActivity(intent)
                }
                2-> {
                    val chapterIntent = Intent(this@SubjectActivity,ChapterActivity::class.java)
                    chapterIntent.putExtra("subjectId",list[position].subjectId)
                    chapterIntent.putExtra("subjectName",list[position].subjectName)
                    startActivity(chapterIntent)
                }
                3-> {
                    val intentVideoKonwlege = Intent(this@SubjectActivity,VideoKnowPointsAty::class.java)
                    intentVideoKonwlege.putExtra("subjectId",list[position].subjectId)
                    intentVideoKonwlege.putExtra("subjectName",list[position].subjectName)
                    startActivity(intentVideoKonwlege)

                }
            }
        }
    }
}
