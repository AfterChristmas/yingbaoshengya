package com.yingbao.career.ui.personal

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.gyf.immersionbar.ImmersionBar
import com.tencent.bugly.beta.Beta
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.UserDetailResultBean
import com.yingbao.career.ui.home.activity.CollectActivity
import com.yingbao.career.ui.question.ErrorBookActivity
import com.yingbao.career.ui.question.StudyRecordActivity
import com.yingbao.career.ui.videodownload.DownloadActivity
import com.yingbao.career.utils.CareerSPHelper
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.utils.EventMessageBean
import com.yingbao.career.utils.EventType
import kotlinx.android.synthetic.main.activity_me.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me)
        DisplayTools.setFullScreenTranslucentStatusbar(this)
        EventBus.getDefault().register(this)
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        tv_title.text="我的"
        val layoutParams = rl_titlebar.layoutParams as RelativeLayout.LayoutParams
        if (Build.VERSION.SDK_INT > 18) {
            layoutParams.setMargins(0,DisplayTools.getStatusBarHeight(applicationContext),0,0)
        }else{
            layoutParams.setMargins(0,DisplayTools.dip2px(applicationContext,10f),0,0)
        }
        rl_titlebar.layoutParams = layoutParams
        initData()
        initClick()
    }

    private fun initData() {
        showWaitDialog()
        RetrofitFactory.getInstance().getUserInfo(
            CareerSPHelper.getUserId(),
            object : BaseObserver<UserDetailResultBean.ResultBean?>() {
                override fun onSuccees(userDetailResultBean: UserDetailResultBean.ResultBean?) {
                    if (userDetailResultBean != null) {
                        if (!TextUtils.isEmpty(userDetailResultBean.imageUrl)) {
                            CareerSPHelper.setUserImageUrl(userDetailResultBean.imageUrl)
                        }
                        CareerSPHelper.setUserId(userDetailResultBean.userId)
                        if (!TextUtils.isEmpty(userDetailResultBean.phone)) {
                            CareerSPHelper.setUserPhone(userDetailResultBean.phone)
                        }
                        if (!TextUtils.isEmpty(userDetailResultBean.realName)) {
                            CareerSPHelper.setUserName(userDetailResultBean.realName)
                        }
                        CareerSPHelper.setUserGender(userDetailResultBean.sex)
                        if (!TextUtils.isEmpty(userDetailResultBean.address)) {
                            CareerSPHelper.setUserLocation(userDetailResultBean.address)
                        }
                        if (!TextUtils.isEmpty(userDetailResultBean.schoolName)) {
                            CareerSPHelper.setUserSchool(userDetailResultBean.schoolName)
                        }
                        CareerSPHelper.setUserGrade(userDetailResultBean.gradeId)
                        if (!TextUtils.isEmpty(userDetailResultBean.clazzName)) {
                            CareerSPHelper.setUserClass(userDetailResultBean.clazzName)
                        }
                        tv_username.text = CareerSPHelper.getUserName()
                        Glide.with(applicationContext).load(userDetailResultBean.imageUrl)
                            .into(user_icon)
                        tv_school_class.text =
                            "${CareerSPHelper.getUserSchool()}     ${CareerSPHelper.getUserClass()}"
                    }
                    dismissWaitDialog()
                }
                override fun onFailure(
                    error: String,
                    isNetWorkError: Boolean
                ) {
                    showErrorMessage(error)
                }

            })
    }

    private fun initClick() {
        rl_person_info.setOnClickListener {
            startActivity(Intent(this@MeActivity, PersonalInfoActivity::class.java))
        }
        rl_about_us.setOnClickListener {
            startActivity(Intent(this@MeActivity, AboutUsActivity::class.java))
        }
        rl_connect.setOnClickListener {
            startActivity(Intent(this@MeActivity, ConnectUsActivity::class.java))
        }
        rl_update_pwd.setOnClickListener {
            startActivity(Intent(this@MeActivity, UpdatePwdActivity::class.java))
        }
        rl_update_phone.setOnClickListener {
            startActivity(Intent(this@MeActivity, UpdatePhoneActivity::class.java))
        }
        ll_error_book.setOnClickListener {
            startActivity(Intent(this@MeActivity, ErrorBookActivity::class.java))
        }
        ll_study_record.setOnClickListener {
            startActivity(Intent(this@MeActivity, StudyRecordActivity::class.java))
        }
        ll_collect.setOnClickListener {
            startActivity(Intent(this@MeActivity, CollectActivity::class.java))
        }
        ll_cache.setOnClickListener {
            startActivity(Intent(this@MeActivity, DownloadActivity::class.java))
        }
        rl_update_app.setOnClickListener {
            Beta.checkAppUpgrade()
        }
        tv_logout.setOnClickListener {
            CareerSPHelper.clearLoginInfo()
            EventBus.getDefault().post(EventMessageBean(EventType.LOGIN_OUT))
            finish()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(eventMessageBean: EventMessageBean){
        if(eventMessageBean.messageType == EventType.UPDATE_PASSWORD_SUCCESS){
            finish()
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
