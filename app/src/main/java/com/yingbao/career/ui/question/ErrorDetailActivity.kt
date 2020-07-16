package com.yingbao.career.ui.question

import android.os.Bundle
import android.view.View
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.utils.DisplayTools
import kotlinx.android.synthetic.main.white_titlebar.*

class ErrorDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_detail)
        DisplayTools.setTransparentStatusBar(this)
        tv_title.text= "错题本"
        tv_edit.visibility = View.VISIBLE
        tv_edit.text = "移除"

    }
}
