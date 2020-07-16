package com.yingbao.career.ui.question

import android.os.Bundle
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.utils.DisplayTools
import kotlinx.android.synthetic.main.white_titlebar.*

class KnowPointActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_know_point)
        DisplayTools.setTransparentStatusBar(this)
        tv_title.text = "语文知识点"
    }
}
