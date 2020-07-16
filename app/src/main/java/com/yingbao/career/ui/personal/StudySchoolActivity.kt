package com.yingbao.career.ui.personal

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.promeg.pinyinhelper.Pinyin
import com.github.promeg.pinyinhelper.PinyinMapDict
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.StudySchoolResutlBean
import com.yingbao.career.ui.personal.adapter.StudySchoolAdapter
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.utils.ToastUtil
import kotlinx.android.synthetic.main.white_titlebar.*
import kotlinx.android.synthetic.main.wygkplus_activity_gaokaolocation.rv_list
import kotlinx.android.synthetic.main.wygkplus_activity_studyschool.*
import java.util.*

class StudySchoolActivity : BaseActivity() {


    private val list = ArrayList<StudySchoolResutlBean.ResultBean>()
    val letterList = ArrayList<String>()
    lateinit var locationAdapter: StudySchoolAdapter
    private var studyCityId: String = ""
    private var studyCountryId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wygkplus_activity_studyschool)
        DisplayTools.setTransparentStatusBar(this)
        initData()
        initClick()
        getPrvinceData(studyCountryId,studyCityId);
    }

    private fun getPrvinceData(areaId:String,cityId:String) {
        RetrofitFactory.getInstance().getSchoolListByProvinceCityAreaId(areaId,cityId, object : BaseObserver<List<StudySchoolResutlBean.ResultBean>>() {
            override fun onSuccees(resultBean: List<StudySchoolResutlBean.ResultBean>) {
                list.clear()
                list.addAll(resultBean)
                list.forEachIndexed { _, resultBean1 ->
                    try {
                        resultBean1.index = Pinyin.toPinyin(resultBean1.name, ",")[0].toString()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        resultBean1.index = ""
                    }

                    if (!letterList.contains(resultBean1.index)) {
                        letterList.add(resultBean1.index)
                    }
                }


                //将出现的字母保存起来
                val charArray = CharArray(letterList.size)
                for (i in letterList.indices) {
                    charArray[i] = letterList.get(i).get(0)
                }
                ////将出现的字母排序
                Arrays.sort(charArray)
                val strArray = arrayOfNulls<String>(letterList.size)
                for (i in charArray.indices) {
                    strArray[i] = charArray[i].toString()
                }
                //设置到字母view
                side_bar.setIndexItems(*strArray)
                Collections.sort(list, PinyinComarator())
                locationAdapter.notifyDataSetChanged()
            }

            override fun onFailure(error: String, isNetWorkError: Boolean) {
                ToastUtil.showShort(applicationContext,error)
            }
        })
    }


    private fun initData() {
        tv_title.setText("就读学校")
        tv_edit.visibility = View.GONE
        val cityId = intent.getIntExtra("studyCityId",-1)
        val countryId = intent.getIntExtra("studyCountryId",-1)
        if (cityId==-1)
            studyCityId =""
        else
            studyCityId=cityId.toString()
        if (countryId==-1)
            studyCountryId =""
        else
            studyCountryId=countryId.toString()

        locationAdapter = StudySchoolAdapter(R.layout.wygkplus_item_study_shool,list)
        rv_list.adapter = locationAdapter
        rv_list.setLayoutManager(LinearLayoutManager(applicationContext))
        Pinyin.init(Pinyin.newConfig().with(object : PinyinMapDict() {
            override fun mapping(): Map<String, Array<String>> {
                val map = HashMap<String, Array<String>>()
                map["重庆"] = arrayOf("CHONG", "QING")
                return map
            }
        }))

    }

    private fun initClick() {
        locationAdapter.setOnItemClickListener { _, _, position ->
            val intent = Intent()
            intent.putExtra("studySchoolId",list.get(position).id)
            val cityName =list.get(position).name
            intent.putExtra("studySchoolName",cityName)
            setResult(2005,intent)
            finish()
        }
        side_bar.setOnSelectIndexItemListener {
            for (i in list.indices) {
                if (list.get(i).index.equals(it)) {
                    (rv_list.getLayoutManager() as LinearLayoutManager).scrollToPositionWithOffset(i, 0)
                }
            }
        }
    }

    /**
     * 汉字按字母顺序排列的比较器
     */
    internal inner class PinyinComarator : Comparator<StudySchoolResutlBean.ResultBean> {

        override fun compare(o1: StudySchoolResutlBean.ResultBean?, o2: StudySchoolResutlBean.ResultBean?): Int {
            if (o1 == null || o2 == null) {
                return 0
            }
            val str1 = o1.name
            val str2 = o2.name
            if (TextUtils.isEmpty(str1) || TextUtils.isEmpty(str2)) {
                return 0
            }
            val pinyinHead = Pinyin.toPinyin(str1, ",")
            val pinyinRear = Pinyin.toPinyin(str2, ",")

            val a = pinyinHead.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val b = pinyinRear.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val len = if (a.size < b.size) a.size else b.size
            for (i in 0 until len) {
                val head = a[i]
                val rear = b[i]
                val inLen = if (head.length < rear.length) head.length else rear.length
                //GKBBLogUtil.d("Begin compare head:"+head+"|rear:"+rear);
                for (j in 0 until inLen) {
                    val c1 = head[j]
                    val c2 = rear[j]
                    if (c1 > c2) {
                        //GKBBLogUtil.d("c1:"+c1+" > |c2:"+c2);
                        return 1
                    } else if (c1 == c2) {
                        //GKBBLogUtil.d("c1:"+c1+" == |c2:"+c2);
                        continue
                    } else if (c1 < c2) {
                        //GKBBLogUtil.d("c1:"+c1+" <|c2:"+c2);
                        return -1
                    }
                }
                //GKBBLogUtil.d("End compare head:"+head+"|rear:"+rear);
            }
            return 0
        }
    }
}
