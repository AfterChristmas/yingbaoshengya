package com.yingbao.career.ui.personal

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.wildma.pictureselector.FileUtils
import com.wildma.pictureselector.ImageUtils
import com.wildma.pictureselector.PictureBean
import com.wildma.pictureselector.PictureSelectUtils
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.common.MyApplication.Companion.context
import com.yingbao.career.dialog.IconImageChooseDialog
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RequestBean.UploadImagesResult
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.UploadImgUtil
import com.yingbao.career.http.resultbean.BooleanDataBean
import com.yingbao.career.http.resultbean.OssInfoResultBean
import com.yingbao.career.utils.*
import kotlinx.android.synthetic.main.activity_personal_info.*
import kotlinx.android.synthetic.main.white_titlebar.*
import java.io.File

class PersonalInfoActivity : BaseActivity() ,View.OnClickListener{
    private val REQUEST_CAMERA_PERMISSION = 1
    private val mCropWidth = 200
    private val mCropHeight = 200
    private val mRatioWidth = 1
    private val mRatioHeight = 1
    private val mCropEnabled = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)
        DisplayTools.setTransparentStatusBar(this)
        tv_title.setText("个人资料")
        tv_edit.visibility = View.GONE
        rl_usericon.setOnClickListener(this)
        initData()
    }

    private fun initData() {
        tv_user_account.text = CareerSPHelper.getUserId().toString()
        tv_phone.text = CareerSPHelper.getUserPhone()
        tv_user_name.text = CareerSPHelper.getUserName()
        tv_gender.text = if (CareerSPHelper.getUserGender() == 0) "男" else "女"
        tv_location.text = CareerSPHelper.getUserLocation()
        tv_school.text = CareerSPHelper.getUserSchool()
        tv_grade.text = when (CareerSPHelper.getUserGrade()) {
            1 -> "高一"
            2 -> "高二"
            3 -> "高三"
            else -> "高一"
        }
        tv_class.text = CareerSPHelper.getUserClass()
        Glide.with(applicationContext).load(CareerSPHelper.getUserImageUrl())
            .into(iv_usericon)
    }

    private fun showPhotoChooseDialog() {
        val addImgsDialog = IconImageChooseDialog()
        addImgsDialog.show(supportFragmentManager.beginTransaction(), "QUES_ANSWER_IMG")
        addImgsDialog.setOnChooseAddTypeListener(object :
            IconImageChooseDialog.OnChooseAddTypeListener {
            override fun onTakePhoto() {
                takePhoto()
            }
            override fun onChooseImgs() {
                chooseFromGallery()
            }
        })
    }

    private fun chckPermissions() {
        if (!HardwareTools.hasCamera(applicationContext)) {
            ToastUtil.showShort(context, "没有检测到摄像头")
            return
        }

        if (PermissionUtils.checkAndApplyPermissionActivity(
                this,
                arrayOf<String>(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), REQUEST_CAMERA_PERMISSION
            )
        ) {
            showPhotoChooseDialog()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, @NonNull permissions: Array<String>,
        @NonNull grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (PermissionUtils.checkPermission(grantResults)) {
            when (requestCode) {
                REQUEST_CAMERA_PERMISSION -> showPhotoChooseDialog()
            }
        }
    }

    private fun chooseFromGallery() {
        //使用第三方的来实现
        PictureSelectUtils.getByAlbum(this)
    }

    private fun takePhoto() {
        PictureSelectUtils.getByCamera(this)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        /*选择图片中途按返回键*/
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            if (requestCode == PictureSelectUtils.GET_BY_ALBUM || requestCode == PictureSelectUtils.GET_BY_CAMERA || requestCode == PictureSelectUtils.CROP
            ) {
                return
            }
        }
        val picturePath = PictureSelectUtils.onActivityResult(
            this,
            requestCode,
            resultCode,
            data,
            mCropEnabled,
            mCropWidth,
            mCropHeight,
            mRatioWidth,
            mRatioHeight
        )
        if (!TextUtils.isEmpty(picturePath)) {
            val bean = PictureBean()
            bean.path = picturePath
            bean.isCut = mCropEnabled
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                bean.uri = ImageUtils.getImageUri(this, picturePath)
            } else {
                bean.uri = Uri.fromFile(File(picturePath))
            }
            uploadImages(bean.path)
        }
    }

    /**
     * 上传图片
     */
    private fun uploadImages(imgPath: String) {
        showCommonWaitDialog()
        //先获取临时密钥  然后再上传
        RetrofitFactory.getInstance().getOssStsSignInfo(
            object : BaseObserver<OssInfoResultBean.OssTempSecretBean?>() {
                override fun onSuccees(ossTempSecretBean: OssInfoResultBean.OssTempSecretBean?) {
                    uploadImage(imgPath, ossTempSecretBean)
                }

                override fun onFailure(
                    error: String,
                    isNetWorkError: Boolean
                ) {
                    dismissWaitDialog()
                    showErrorMessage(error)
                }

            })
    }

    private fun uploadImage(
        imgPath: String,
        ossTempSecretBean: OssInfoResultBean.OssTempSecretBean?
    ) {
        UploadImgUtil(applicationContext, imgPath, ossTempSecretBean, UploadImgUtil.HEAD_IMG,
            object : UploadImgUtil.UploadImgListener {
                override fun onStart() {
                }

                override fun onSuccess(imgData: UploadImagesResult.DataEntity) {
                    Log.e("uploadFile", imgData.imgKey)
                    iv_usericon.setImageBitmap(BitmapFactory.decodeFile(imgPath))
                    userHeadUpload(imgData.imgKey)
                }

                override fun onFailure() {
                    dismissWaitDialog()
                    showErrorMessage("上传失败！")
                    FileUtils.deleteAllCacheImage(applicationContext);
                }
            }).uploadImages()
    }

    private fun userHeadUpload(headUrl :String) {
        RetrofitFactory.getInstance().userHeadUpload(
            headUrl,
            object : BaseObserver<BooleanDataBean.ResultBean?>() {
                override fun onSuccees(userDetailResultBean: BooleanDataBean.ResultBean?) {
                    FileUtils.deleteAllCacheImage(applicationContext);
                    dismissWaitDialog()
                }
                override fun onFailure(
                    error: String,
                    isNetWorkError: Boolean
                ) {
                    dismissWaitDialog()
                    showErrorMessage(error)
                    FileUtils.deleteAllCacheImage(applicationContext);
                }

            })
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.rl_usericon->{
                //请求应用需要的所有权限
                chckPermissions()
            }
        }
    }
}
