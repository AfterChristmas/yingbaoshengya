package com.yingbao.career.ui.personal

import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.View
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.BooleanDataBean
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.utils.EventMessageBean
import com.yingbao.career.utils.EventType
import com.yingbao.career.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_update_pwd.*
import kotlinx.android.synthetic.main.white_titlebar.*
import org.greenrobot.eventbus.EventBus

class UpdatePwdActivity : BaseActivity() {
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pwd)
        DisplayTools.setTransparentStatusBar(this)
        tv_get_verify.setOnClickListener {
            if(TextUtils.equals(tv_get_verify.text,"获取验证码")){
                getVerifyCode()
            }
        }
        tv_update.setOnClickListener {
            updatePwd()
        }
        initData()
    }
    private fun initData() {
        tv_title.setText("修改密码")
        tv_edit.visibility = View.GONE
        timer = TimeCount(60_000, 1000)

    }
    private fun updatePwd() {
        val phone = et_phone_num.text.toString().trim()
        val new_pwd = et_password.text.toString().trim();
        val code = et_code.text.toString().trim();

        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.phone_is_null))
            return
        }
        if (TextUtils.isEmpty(new_pwd)) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.input_new_pwd))
            return
        }
        if (new_pwd.length < 6 || new_pwd.length > 16) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.pwd_length_error))
            return
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.checkcode_is_null))
            return
        }
        RetrofitFactory.getInstance().passwordReset(phone,code, new_pwd,object : BaseObserver<BooleanDataBean.ResultBean>() {
            override fun onSuccees(resultBean: BooleanDataBean.ResultBean?) {
                ToastUtil.showShort(applicationContext,"密码修改成功")
                val messageBean = EventMessageBean(EventType.UPDATE_PASSWORD_SUCCESS)
                EventBus.getDefault().post(messageBean)
                finish()

            }

            override fun onFailure(error: String, isNetWorkError: Boolean) {
                ToastUtil.showShort(applicationContext,error)
            }
        })
    }

    /**
     * 获取验证码
     */
    private fun getVerifyCode() {
        val phone = et_phone_num.text.toString()
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.phone_is_null))
            return
        }
        if (phone.length!=11) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.phone_is_error))
            return
        }
        RetrofitFactory.getInstance().getCodeForPass(phone, object : BaseObserver<BooleanDataBean.ResultBean>() {
            override fun onSuccees(resultBean: BooleanDataBean.ResultBean?) {
                timer.start()
                ToastUtil.showShort(applicationContext,"验证码已发送至+86 $phone")
            }

            override fun onFailure(error: String, isNetWorkError: Boolean) {
                ToastUtil.showShort(applicationContext,error)
            }
        })
    }

    /***
     * 倒计时的类
     */
    private inner class TimeCount internal constructor(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
//            tv_get_verify.setTextColor(resources.getColor(R.color.color_373737))
            tv_get_verify.setText((millisUntilFinished / 1000).toString() + "秒")
        }

        override fun onFinish() {
            tv_get_verify.setClickable(true)
            tv_get_verify.setText("获取验证码")
//            tv_get_verify.setTextColor(resources.getColor(R.color.main_color))
        }

    }
}
