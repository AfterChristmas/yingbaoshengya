package com.yingbao.career.ui.home.activity

import android.os.Bundle
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.utils.DisplayTools
import kotlinx.android.synthetic.main.white_titlebar.*

class CacheActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)
        DisplayTools.setTransparentStatusBar(this)
        initData()
    }

    private fun initData() {
        tv_title.text = "我的缓存"
    }
}
