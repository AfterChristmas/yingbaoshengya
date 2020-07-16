package com.yingbao.career.ui.personal

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
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.utils.EventMessageBean
import com.yingbao.career.utils.EventType
import com.yingbao.career.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import kotlinx.android.synthetic.main.white_titlebar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ForgetPwdActivity : BaseActivity() {
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        DisplayTools.setTransparentStatusBar(this)
        EventBus.getDefault().register(this)
        initData()
        initClick();
}

    private fun initData() {
        tv_title.setText("忘记密码")
        tv_edit.visibility = View.GONE
        timer = TimeCount(60_000, 1000)
    }

    private fun initClick() {
        tv_get_verify.setOnClickListener {
            if(TextUtils.equals(tv_get_verify.text,"获取验证码")){
                getVerifyCode()
            }
        }
        tv_next.setOnClickListener {
            checkCode()
        }
    }

    private fun checkCode() {
        val phone = et_phone_num.text.toString()
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
        val intent = Intent(applicationContext,ResetPwdActivity::class.java)
        intent.putExtra("phoneNum",phone)
        intent.putExtra("checkCode",code)
        startActivity(intent)
       /* RetrofitFactory.getInstance().chekCode(phone,code, object : BaseObserver<BooleanDataBean.ResultBean>() {
            override fun onSuccees(resultBean: BooleanDataBean.ResultBean?) {
                val intent = Intent(applicationContext,LoginUpdatePwdActivity::class.java)
                intent.putExtra("phoneNum",phone)
                intent.putExtra("checkCode",code)
                startActivity(intent)
            }

            override fun onFailure(error: String, isNetWorkError: Boolean) {
                ToastUtil.showShort(applicationContext,error)
            }
        })*/
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
    /**
     * EventBus 的监听
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: EventMessageBean) {
        if (event.messageType == EventType.CLOSE_UPDATE_PASSWORD){
            finish()
        }
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

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        timer.cancel()
    }
}
