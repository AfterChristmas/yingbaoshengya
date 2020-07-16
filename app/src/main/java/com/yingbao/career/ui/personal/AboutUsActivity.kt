package com.yingbao.career.ui.personal

import android.content.Intent
import android.os.Bundle
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.common.CommonConstant
import com.yingbao.career.ui.home.activity.WebActivity
import com.yingbao.career.utils.DisplayTools
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.white_titlebar.*

class AboutUsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        DisplayTools.setTransparentStatusBar(this)
        tv_title.text = "关于我们"
        tv_xieyi.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            intent.putExtra(WebActivity.URL, CommonConstant.URLConstant.AGREEMENT_URL)
            intent.putExtra(WebActivity.TITLE, getString(R.string.common_user_agreement))
            startActivity(intent)
        }
        tv_zhengce.setOnClickListener {
            val intent =  Intent(this, WebActivity::class.java)
            intent.putExtra(WebActivity.URL, CommonConstant.URLConstant.PRIVACY_POLICY_URL)
            intent.putExtra(WebActivity.TITLE, getString(R.string.common_privacy_policy))
            startActivity(intent)
        }
    }
}
