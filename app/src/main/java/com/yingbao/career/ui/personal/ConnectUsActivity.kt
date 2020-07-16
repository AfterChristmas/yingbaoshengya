package com.yingbao.career.ui.personal

import android.os.Bundle
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.utils.DisplayTools
import kotlinx.android.synthetic.main.white_titlebar.*

class ConnectUsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_us)
        DisplayTools.setTransparentStatusBar(this)
        tv_title.text = "联系我们"
    }
}
