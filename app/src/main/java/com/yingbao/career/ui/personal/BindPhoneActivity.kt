package com.yingbao.career.ui.personal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.View
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.BooleanDataBean
import com.yingbao.career.utils.*
import kotlinx.android.synthetic.main.activity_bind_phone.*
import kotlinx.android.synthetic.main.white_titlebar.*
import org.greenrobot.eventbus.EventBus

class BindPhoneActivity : BaseActivity() {
    private lateinit var timer: CountDownTimer

    companion object {
        fun starBindPhone(context: Context, isCompleteInfo: Int) {
            val intent = Intent(context, BindPhoneActivity::class.java);
            intent.putExtra("isCompleteInfo", isCompleteInfo)
            context.startActivity(intent)

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bind_phone)
        DisplayTools.setTransparentStatusBar(this)
        initData()
        initClick()
    }

    private fun initData() {
        tv_title.setText("绑定手机号")
        tv_edit.visibility = View.GONE
        timer = TimeCount(60_000, 1000)    }

    private fun initClick() {
        tv_get_verify.setOnClickListener {
            if(TextUtils.equals(tv_get_verify.text,"获取验证码")){
                getVerifyCode()
            }
        }
        tv_bind.setOnClickListener {
            bindPhone()
        }
    }

    private fun bindPhone() {
        val phone = tv_phone.text.toString()
        val code = et_code.text.toString()
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.phone_is_null))
            return
        }
        if (phone.length!=11) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.phone_is_error))
            return
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.checkcode_is_null))
            return
        }
        RetrofitFactory.getInstance().bandPhone(phone,code, object : BaseObserver<BooleanDataBean.ResultBean>() {
            override fun onSuccees(resultBean: BooleanDataBean.ResultBean?) {
                CareerSPHelper.setUserPhone(phone)
                ToastUtil.showShort(applicationContext,"绑定成功")
                if (intent.getIntExtra("isCompleteInfo",0) == 0) {
                    FillPersonInfoActivity.startFillPerson(this@BindPhoneActivity)
                    finish()
                } else {
                    val messageBean = EventMessageBean(EventType.CLOSE_LOGIN)
                    EventBus.getDefault().post(messageBean)
//                    startActivity(Intent(applicationContext,ErrorBookAty ::class.java))
                    finish()
                }
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
        val phone = tv_phone.text.toString()
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.phone_is_null))
            return
        }
        if (phone.length!=11) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.phone_is_error))
            return
        }
        RetrofitFactory.getInstance().getCodeForBind(phone, object : BaseObserver<BooleanDataBean.ResultBean>() {
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
