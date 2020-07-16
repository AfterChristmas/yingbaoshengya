package com.yingbao.career.ui.personal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.gyf.immersionbar.ImmersionBar
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.LoginResultBean
import com.yingbao.career.utils.*
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LoginActivity : BaseActivity() ,View.OnClickListener{
    companion object {
        fun starLogin(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
        fun starLoginWithNewTag(context: Context, loginListener: OnLoginListener) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
            context.startActivity(intent)
        }
        fun getLoginIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        DisplayTools.setFullScreenTranslucentStatusbar(this)
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        tv_login.setOnClickListener(this)
        tv_forget_pwd.setOnClickListener(this)
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: EventMessageBean) {
        if (event.messageType == EventType.CLOSE_LOGIN){
            finish()
        }
    }
    override fun onClick(view: View) {
        when (view.id) {
            R.id.tv_login -> {
                login()
            }
            R.id.tv_forget_pwd -> {
                startActivity(Intent(applicationContext,ForgetPwdActivity::class.java))
            }
        }
    }
    private fun login() {
        val userName = et_card_num.text.toString()
        val password = et_password.text .toString()
        if (TextUtils.isEmpty(userName)) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.cardnum_is_null))
            return
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort(applicationContext,resources.getString(R.string.pwd_is_null))
            return
        }

        RetrofitFactory.getInstance().login(userName,password, object : BaseObserver<LoginResultBean.ResultBean>() {
            override fun onSuccees(resultBean: LoginResultBean.ResultBean) {
                Log.e("test",resultBean.token)
                CareerSPHelper.setToken(resultBean.token)
                CareerSPHelper.setUserId(resultBean.userId)
                if(TextUtils.isEmpty(resultBean.phone)){
                    BindPhoneActivity.starBindPhone(this@LoginActivity,resultBean.isCompleteInfo)
                }else{
                    CareerSPHelper.setUserPhone(resultBean.phone)
                    if (resultBean.isCompleteInfo == 0) {
                        FillPersonInfoActivity.startFillPerson(this@LoginActivity)
                    } else {
                        CareerSPHelper.getUserInfo()
                        finish()
                    }
                }

            }
            override fun onFailure(error: String, isNetWorkError: Boolean) {
                ToastUtil.showShort(applicationContext,error)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
