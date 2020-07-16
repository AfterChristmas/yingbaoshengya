package com.yingbao.career.Base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.avast.android.dialogs.fragment.ProgressDialogFragment
import com.kongzue.dialog.v3.TipDialog
import com.kongzue.dialog.v3.WaitDialog
import com.yingbao.career.R

/**
 * @Description:
 * @Date: 2019/11/20 00:08
 * @Auther: wanyan
 */
open class BaseActivity : AppCompatActivity() {
    fun clickFinish(view: View?) {
        finish()
    }


    fun showWaitDialog(): TipDialog {
        return showWaitDialog(resources.getString(R.string.common_waiting))
    }

    fun showWaitDialog(msg: String?): TipDialog {
        return WaitDialog.show(this, msg).setCancelable(true)
    }

    fun showWarnMessage(msg: String?): TipDialog {
        return TipDialog.show(this, msg, TipDialog.TYPE.WARNING)
    }

    fun showErrorMessage(msg: String?): TipDialog {
        return TipDialog.show(this, msg, TipDialog.TYPE.ERROR)
    }

    fun showSuccessMessage(msg: String?): TipDialog {
        return TipDialog.show(this, msg, TipDialog.TYPE.SUCCESS)
    }

    fun showCommonWaitDialog() {
        WaitDialog.show(this, resources.getString(R.string.common_waiting)).cancelable = true
    }

    fun showCommonWaitDialog(msg: String?) {
        WaitDialog.show(this, msg).cancelable = true
    }

    fun showDisCanclableCommonWaitDialog(msg: String?) {
        WaitDialog.show(this, msg).cancelable
    }

    fun dismissWaitDialog() {
        WaitDialog.dismiss()
    }

}