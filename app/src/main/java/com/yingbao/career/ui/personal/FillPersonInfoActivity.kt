package com.yingbao.career.ui.personal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.BooleanDataBean
import com.yingbao.career.utils.*
import kotlinx.android.synthetic.main.white_titlebar.*
import kotlinx.android.synthetic.main.wygkplus_activity_fillpersoninfo.*
import org.greenrobot.eventbus.EventBus

class FillPersonInfoActivity : BaseActivity(),View.OnClickListener {
    private val genderRequestCode =  20001;
    private val gradeRequestCode =  20002;
    private val gaokaoLocationRequestCode =  20003;
    private val studyCityRequestCode =  20004;
    private val studySchoolRequestCode =  20005;
    private var gender = 0;
    private var grade = 0;
    private var provinceId = -1;
    private var provinceName = "";
    private var studyCityId = -1;
    private var studyCityName = "";
    private var studySchoolId = -1;
    private var studyCountryId = -1;
    private var studySchoolName = "";

    companion object {

        fun startFillPerson(context: Context) {
            val intent = Intent(context, FillPersonInfoActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wygkplus_activity_fillpersoninfo)
        DisplayTools.setTransparentStatusBar(this)
        initData()
        initClick()
    }

    private fun initClick() {
        rl_gender.setOnClickListener(this)
        rl_grade.setOnClickListener(this)
        rl_gaokao_location.setOnClickListener(this)
        rl_study_city.setOnClickListener(this)
        rl_study_school.setOnClickListener(this)
        tv_save.setOnClickListener(this)
    }

    private fun initData() {
        tv_title.setText("完善信息")
        tv_edit.visibility = View.GONE
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.rl_gender -> {
                val intent = Intent(applicationContext, GenderSelectActivity::class.java)
                intent.putExtra("gender",gender)
                startActivityForResult(intent, genderRequestCode)
            }
            R.id.rl_grade -> {
                val intent = Intent(applicationContext, GradeSelectActivity::class.java)
                intent.putExtra("grade",grade)
                startActivityForResult(intent, gradeRequestCode)
            }
            R.id.rl_gaokao_location -> {
                val intent = Intent(applicationContext, GaoKaoLocationActivity::class.java)
                intent.putExtra("provinceId",provinceId)
                startActivityForResult(intent, gaokaoLocationRequestCode)
            }
            R.id.rl_study_city -> {
                val intent = Intent(applicationContext, StudyCityActivity::class.java)
                intent.putExtra("studyCityId",studyCityId)
                startActivityForResult(intent, studyCityRequestCode)
            }
            R.id.rl_study_school -> {
                if (studyCityId == -1 && studyCountryId == -1) {
                    ToastUtil.showShort(applicationContext,"请先选择就读城市")
                    return
                }
                val intent = Intent(applicationContext, StudySchoolActivity::class.java)
                intent.putExtra("studyCityId",studyCityId)
                intent.putExtra("studyCountryId",studyCountryId)
                startActivityForResult(intent, studySchoolRequestCode)
            }
            R.id.tv_save -> {
                saveUserInfo()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            genderRequestCode -> {
                if (data != null) {
                    gender = data.getIntExtra("gender", 0)
                    if (gender == 0){
                        tv_gender.setText("男")
                    }else{
                        tv_gender.setText("女")
                    }
                }
            }
            gradeRequestCode -> {
                if (data != null) {
                    grade = data.getIntExtra("grade", 0)
                    if (grade == 0){
                        tv_grade.setText("高一")
                    }else if (grade ==1){
                        tv_grade.setText("高二")
                    }else{
                        tv_grade.setText("高三")
                    }
                }
            }
            gaokaoLocationRequestCode -> {
                if (data != null) {
                    provinceId = data.getIntExtra("provinceId",-1)
                    provinceName = data.getStringExtra("provinceName")
                    if (TextUtils.isEmpty(provinceName)) {
                        tv_gaokao_location.setText("请选择")
                    } else {
                        tv_gaokao_location.setText(provinceName)
                    }
                }
            }
            studyCityRequestCode -> {
                if (data != null) {
                    studyCityId = data.getIntExtra("studyCityId",-1)
                    studyCountryId = data.getIntExtra("studyCountryId",-1)
                    studyCityName = data.getStringExtra("studyCityName")
                    if (TextUtils.isEmpty(studyCityName)) {
                        tv_study_city.setText("请选择")
                    } else {
                        tv_study_city.setText(studyCityName)
                    }
                }
            }
            studySchoolRequestCode -> {
                if (data != null) {
                    studySchoolId = data.getIntExtra("studySchoolId",-1)
                    studySchoolName = data.getStringExtra("studySchoolName")
                    if (TextUtils.isEmpty(studySchoolName)) {
                        tv_study_school.setText("请选择")
                    } else {
                        if(studySchoolName.length>10){
                            val  tempStudySchoolName = studySchoolName.substring(0,10);
                            tv_study_school.setText(tempStudySchoolName+"...")
                        }else{
                            tv_study_school.setText(studySchoolName)
                        }
                    }
                }
            }
        }
    }
    private fun saveUserInfo() {
        if (TextUtils.isEmpty(et_name.text.toString())){
            ToastUtil.showShort(applicationContext,"请输入姓名")
            return
        }
        if (provinceId==-1){
            ToastUtil.showShort(applicationContext,"请选择高考所在地")
            return
        }
        if (studyCityId==-1&& studyCountryId==-1){
            ToastUtil.showShort(applicationContext,"请选择就读城市")
            return
        }
        if (studySchoolId==-1){
            ToastUtil.showShort(applicationContext,"请选择就读学校")
            return
        }
        if (grade==-1){
            ToastUtil.showShort(applicationContext,"请选择所在年级")
            return
        }
        if (TextUtils.isEmpty(et_class.text.toString())){
            ToastUtil.showShort(applicationContext,"请输入所在班级")
            return
        }

        val params = HashMap<String,String>()
        params.put("name",et_name.text.toString())
        params.put("provinceId",provinceId.toString())
        params.put("provinceName",provinceName)
        params.put("sex",gender.toString())
        if (studyCityId==-1){
            params.put("cityId",studyCountryId.toString())
        }else{
            params.put("cityId",studyCityId.toString())
        }
        params.put("cityName",studyCityName)
        params.put("schoolId", studySchoolId.toString())
        params.put("schoolName", studySchoolName)
        params.put("gradeId",when(grade){
            0 ->"16"
            1->"17"
            2->"18"
            else->"16"
        })
        params.put("clazzName",et_class.text.toString())


        RetrofitFactory.getInstance().completeInfo(params, object : BaseObserver<BooleanDataBean.ResultBean>() {
            override fun onSuccees(booleanDataBean: BooleanDataBean.ResultBean?) {
                val messageBean = EventMessageBean(EventType.CLOSE_LOGIN)
                EventBus.getDefault().post(messageBean)
                finish()
                CareerSPHelper.setUserName(et_name.text.toString())
                CareerSPHelper.getUserInfo()
            }

            override fun onFailure(error: String, isNetWorkError: Boolean) {
                ToastUtil.showShort(applicationContext,error)
            }
        })
    }

}
