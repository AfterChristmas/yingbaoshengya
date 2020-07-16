package com.yingbao.career.ui.personal

import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_update_phone.*
import kotlinx.android.synthetic.main.activity_update_pwd.tv_update
import kotlinx.android.synthetic.main.white_titlebar.*
import org.greenrobot.eventbus.EventBus

class ResetPwdActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pwd)
        DisplayTools.setTransparentStatusBar(this)
        tv_update.setOnClickListener {
            updatePwd()
        }
        initData()
    }
    private fun initData() {
        tv_title.setText("修改密码")
        tv_edit.visibility = View.GONE
    }
    private fun updatePwd() {
        val phone = intent.getStringExtra("phoneNum")
        val code = intent.getStringExtra("checkCode")

        val new_pwd = et_password_confirm.text.toString().trim();
        if (TextUtils.isEmpty(new_pwd)) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.input_new_pwd))
            return
        }
        if (new_pwd.length < 6 || new_pwd.length > 16) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.pwd_length_error))
            return
        }

        RetrofitFactory.getInstance().passwordReset(phone,code, new_pwd,object : BaseObserver<BooleanDataBean.ResultBean>() {
            override fun onSuccees(resultBean: BooleanDataBean.ResultBean?) {
                ToastUtil.showShort(applicationContext,"密码修改成功")
                val messageBean = EventMessageBean(EventType.CLOSE_UPDATE_PASSWORD)
                EventBus.getDefault().post(messageBean)
                finish()

            }

            override fun onFailure(error: String, isNetWorkError: Boolean) {
                ToastUtil.showShort(applicationContext,error)
            }
        })
    }
}
