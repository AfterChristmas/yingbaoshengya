package com.yingbao.career.ui.personal

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.utils.DisplayTools
import kotlinx.android.synthetic.main.white_titlebar.*
import kotlinx.android.synthetic.main.wygkplus_activity_gender.*

class GenderSelectActivity : BaseActivity() {
    private var gender = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wygkplus_activity_gender)
        DisplayTools.setTransparentStatusBar(this)
        initData()
        initClick()
    }

    private fun initData() {
        gender = intent.getIntExtra("gender",0)
        if (gender == 0){
            iv_boy.visibility = View.VISIBLE
            iv_girl.visibility = View.GONE
        }else{
            iv_girl.visibility = View.VISIBLE
            iv_boy.visibility = View.GONE
        }
        tv_title.setText("我的性别")
        tv_edit.visibility = View.GONE
    }

    private fun initClick() {
        rl_boy.setOnClickListener {
            iv_boy.visibility = View.VISIBLE
            iv_girl.visibility = View.GONE
            val intent = Intent()
            intent.putExtra("gender",0)
            setResult(2001,intent)
            finish()
        }
        rl_girl.setOnClickListener {
            iv_girl.visibility = View.VISIBLE
            iv_boy.visibility = View.GONE
            val intent1 = Intent()
            intent1.putExtra("gender",1)
            setResult(2001,intent1)
            finish()
        }
    }
}
