package com.yingbao.career.ui.home.activity

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.common.CommonConstant
import com.yingbao.career.dialog.UserAgreementDialog
import com.yingbao.career.utils.CareerSPHelper
import com.yingbao.career.utils.GlideRoundImage
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*


/**
 * @Description:
 * @Date: 2020/6/9 09:14
 * @Auther: wanyan
 */
class SplashActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val myOptions = RequestOptions().transform(GlideRoundImage(application, 6))
        Glide.with(application)
            .load(R.mipmap.ic_launcher) //                .centerCrop()
            .apply(myOptions)
            .into(
                iv_splash_icon
            )
    }

    override fun onResume() {
        super.onResume()
        checkShowAgreementOrNot()
    }
    /**
     *检查是否显示用户协议
     */
    private fun checkShowAgreementOrNot() {
        val showAgreement = CareerSPHelper.getBoolean(applicationContext,CommonConstant.SHOW_USER_AGREEMENT, true)
        if (showAgreement) {
            showUserAgreementDialog()
        } else {
            sleepAndShowLogo();
        }
    }
    /**
     * 休眠 1000 ms
     */
    private fun sleepAndShowLogo() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
               startActivity(Intent(applicationContext, HomeActivity::class.java))
                this@SplashActivity.finish()
            }
        }, 1500)
    }

    /**
     * 显示用户协议对话框
     */
    private fun showUserAgreementDialog() {
        UserAgreementDialog.newInstance()
            .setOnOperateListener(object : UserAgreementDialog.OnOperateListener {
                override fun onAgree() {
                    CareerSPHelper.putBoolean(applicationContext,CommonConstant.SHOW_USER_AGREEMENT, false)
                    sleepAndShowLogo()
                }

                override fun onNotAgree() {
                    CareerSPHelper.putBoolean(applicationContext,CommonConstant.SHOW_USER_AGREEMENT, true)
                    this@SplashActivity.finish()
                }

            }).show(supportFragmentManager.beginTransaction(), null)
    }

}