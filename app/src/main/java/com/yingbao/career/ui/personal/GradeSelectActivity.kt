package com.yingbao.career.ui.personal

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.utils.DisplayTools
import kotlinx.android.synthetic.main.white_titlebar.*
import kotlinx.android.synthetic.main.wygkplus_activity_grade.*

class GradeSelectActivity : BaseActivity() {
    private var grade = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wygkplus_activity_grade)
        DisplayTools.setTransparentStatusBar(this)
        initData()
        initClick()
    }

    private fun initData() {
        grade = intent.getIntExtra("grade",0)
        when(grade){
            0->{
               iv_first.visibility = View.VISIBLE
               iv_second.visibility = View.GONE
               iv_third.visibility = View.GONE
            }
            1->{
                iv_first.visibility = View.GONE
                iv_second.visibility = View.VISIBLE
                iv_third.visibility = View.GONE
            }
            2->{
                iv_first.visibility = View.GONE
                iv_second.visibility = View.GONE
                iv_third.visibility = View.VISIBLE
            }
        }
        tv_title.setText("所在年级")
        tv_edit.visibility = View.GONE
    }

    private fun initClick() {
        rl_first.setOnClickListener {
            iv_first.visibility = View.VISIBLE
            iv_second.visibility = View.GONE
            iv_third.visibility = View.GONE
            val intent = Intent()
            intent.putExtra("grade",0)
            setResult(2002,intent)
            finish()
        }
        rl_second.setOnClickListener {
            iv_first.visibility = View.GONE
            iv_second.visibility = View.VISIBLE
            iv_third.visibility = View.GONE
            val intent2 = Intent()
            intent2.putExtra("grade",1)
            setResult(2002,intent2)
            finish()
        }
        rl_third.setOnClickListener {
            iv_first.visibility = View.GONE
            iv_second.visibility = View.GONE
            iv_third.visibility = View.VISIBLE
            val intent3 = Intent()
            intent3.putExtra("grade",2)
            setResult(2002,intent3)
            finish()
        }
    }
}
