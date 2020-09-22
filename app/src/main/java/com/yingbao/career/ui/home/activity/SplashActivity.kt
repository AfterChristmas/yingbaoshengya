package com.yingbao.career.ui.home.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.easefun.polyvsdk.PolyvDownloaderManager
import com.easefun.polyvsdk.PolyvSDKClient
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta
import com.tencent.bugly.beta.UpgradeInfo
import com.tencent.bugly.beta.ui.BetaActivity
import com.tencent.bugly.beta.ui.UILifecycleListener
import com.tencent.bugly.crashreport.CrashReport
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.common.CommonConstant
import com.yingbao.career.dialog.UserAgreementDialog
import com.yingbao.career.ui.personal.MeActivity
import com.yingbao.career.utils.CareerSPHelper
import com.yingbao.career.utils.GlideRoundImage
import kotlinx.android.synthetic.main.activity_splash.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
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
                    initPllyv()
                    initBugly()
                }

                override fun onNotAgree() {
                    CareerSPHelper.putBoolean(applicationContext,CommonConstant.SHOW_USER_AGREEMENT, true)
                    this@SplashActivity.finish()
                }

            }).show(supportFragmentManager.beginTransaction(), null)
    }


    private fun initPllyv() {
        //获取 PolyvSDKClient实例

        //获取 PolyvSDKClient实例
        val client = PolyvSDKClient.getInstance()
        //设置SDK加密串
        //设置SDK加密串
        val sdkStr =
            "Ej4Cg0MRd4YI5rWc3UjyGc2BZ9cI5IMYpbSbop03Yoje5qb8cWp3xeAMzYJBHB3lD+8DZZCvSO8wtOHUpCrs26/i8LYFSBVRlpULVDSlFIOd10IMhTs5HQb/psnVnMXUrvMi+okM1xn/wC4ln5+j5A=="
        val sdkKey = "VXtlHmwfS2oYm0CZ"
        val sdkSecret = "2u9gDPKdX6GyQJKU"
        client.settingsWithConfigString(sdkStr, sdkKey, sdkSecret)
        //初始化SDK设置
        //初始化SDK设置
        client.initSetting(applicationContext)
        //设置学员唯一标识
        //设置学员唯一标识
        client.viewerId = "441446"
        //设置下载保存目录
        //设置下载保存目录
        val filePath = CommonConstant.FileConstant.getVideoDownloadDir()
        val file = File(filePath)
        if (!file.exists()) {
            file.mkdirs()
        }
        PolyvSDKClient.getInstance().setDownloadDir(file)
        // 设置下载队列总数，多少个视频能同时下载。(默认是1，设置负数和0是没有限制)
        PolyvDownloaderManager.setDownloadQueueCount(1)
    }

    private fun initBugly() {
        val context = applicationContext
        // 获取当前包名
        val packageName = context.packageName
        // 获取当前进程名
        val processName = getProcessName(Process.myPid())
        // 设置是否为上报进程
        val strategy = CrashReport.UserStrategy(context)
        strategy.isUploadProcess = processName == null || processName == packageName
        // bugly延迟3秒初始化
        Beta.initDelay = 3 * 1000
        // 指定自定义布局id
        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog
        // 指定升级弹窗只能在主页弹出
        Beta.canShowUpgradeActs.add(HomeActivity::class.java)
        Beta.canShowUpgradeActs.add(MeActivity::class.java)
        Beta.upgradeDialogLifecycleListener = object : UILifecycleListener<UpgradeInfo?> {
            override fun onCreate(
                context: Context?,
                view: View?,
                upgradeInfo: UpgradeInfo?
            ) {
                // 解决弹窗时状态栏为黑色的问题.
                if (context is BetaActivity) {
                    val window: Window = context.window
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                }
            }

            override fun onStart(
                context: Context?,
                view: View?,
                upgradeInfo: UpgradeInfo?
            ) {
            }

            override fun onResume(
                context: Context?,
                view: View?,
                upgradeInfo: UpgradeInfo?
            ) {
            }

            override fun onPause(
                context: Context?,
                view: View?,
                upgradeInfo: UpgradeInfo?
            ) {
            }

            override fun onStop(
                context: Context?,
                view: View?,
                upgradeInfo: UpgradeInfo?
            ) {
            }

            override fun onDestroy(
                context: Context?,
                view: View?,
                upgradeInfo: UpgradeInfo?
            ) {
            }
        }

        // 初始化Bugly
        Bugly.init(context, "c3c53578fd", false,strategy);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName: String = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                if (reader != null) {
                    reader.close()
                }
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
        return null
    }
}