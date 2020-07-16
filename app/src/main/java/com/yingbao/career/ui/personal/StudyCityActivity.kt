package com.yingbao.career.ui.personal

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.LocationResultBean
import com.yingbao.career.ui.personal.adapter.LocationItemAdapter
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.utils.ToastUtil
import kotlinx.android.synthetic.main.white_titlebar.*
import kotlinx.android.synthetic.main.wygkplus_activity_gaokaolocation.rv_list
import kotlinx.android.synthetic.main.wygkplus_activity_studycity.*
import java.util.*

class StudyCityActivity : BaseActivity() {
    private val provinceList = ArrayList<LocationResultBean.ResultBean>()
    private val cityList = ArrayList<LocationResultBean.ResultBean>()
    private val countryList = ArrayList<LocationResultBean.ResultBean>()
    private val list = ArrayList<LocationResultBean.ResultBean>()
    lateinit var locationAdapter: LocationItemAdapter
    private var showType = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wygkplus_activity_studycity)
        DisplayTools.setTransparentStatusBar(this)

        initData()
        initClick()
        getPrvinceData("0");
    }

    private fun getPrvinceData(parentId:String) {
        RetrofitFactory.getInstance().getLocationByParentId(parentId, object : BaseObserver<List<LocationResultBean.ResultBean>>() {
            override fun onSuccees(resultBean: List<LocationResultBean.ResultBean>) {
                when(showType){
                    0->{
                        provinceList.clear()
                        provinceList.addAll(resultBean)
                        list.clear()
                        list.addAll(provinceList)
                        locationAdapter.notifyDataSetChanged()
                    }
                    1->{
                        cityList.clear()
                        cityList.addAll(resultBean)
                        list.clear()
                        list.addAll(cityList)
                        locationAdapter.notifyDataSetChanged()
                    }
                    2->{
                        countryList.clear()
                        countryList.addAll(resultBean)
                        list.clear()
                        list.addAll(countryList)
                        locationAdapter.notifyDataSetChanged()
                    }
                }


            }

            override fun onFailure(error: String, isNetWorkError: Boolean) {
                ToastUtil.showShort(applicationContext,error)
            }
        })
    }


    private fun initData() {
        tv_title.setText("就读城市")
        tv_edit.visibility = View.GONE

        locationAdapter = LocationItemAdapter(list)
        rv_list.adapter = locationAdapter
        rv_list.setLayoutManager(LinearLayoutManager(applicationContext))

    }

    private fun initClick() {
        locationAdapter.setOnItemClickListener { _, _, position ->

            when(showType){
                0->{
                    if (TextUtils.equals(provinceList.get(position).typeName,"市")){
                        tv_city.setText(provinceList.get(position).name)
                    }
                    showType = 1
                    getPrvinceData(provinceList.get(position).id.toString())
                    tv_province.setText(provinceList.get(position).name)
                }
                1->{
                    if (TextUtils.equals(cityList.get(position).typeName,"区")){
                        val intent = Intent()
                        intent.putExtra("studyCityId",cityList.get(position).id)
                        val cityName = tv_city.text.toString()+cityList.get(position).name
                        intent.putExtra("studyCityName",cityName)
                        setResult(2004,intent)
                        finish()
                    }else{
                        showType = 2
                        getPrvinceData(cityList.get(position).id.toString())
                        tv_city.setText(cityList.get(position).name)
                        view_city.visibility = View.VISIBLE
                    }
                }
                2->{
                    countryList.forEachIndexed { index, resultBean ->
                        if (index == position){
                            resultBean.isCheckState = true
                        }else{
                            resultBean.isCheckState = false
                        }
                    }
                    locationAdapter.notifyDataSetChanged()
                    val intent = Intent()
                    intent.putExtra("studyCountryId",countryList.get(position).id)
                    val cityName = tv_province.text.toString()+tv_city.text.toString()+countryList.get(position).name
                    intent.putExtra("studyCityName",cityName)
                    setResult(2004,intent)
                    finish()

                }
            }


        }
        ll_province.setOnClickListener {
            if (showType > 0) {
                list.clear()
                list.addAll(provinceList)
                locationAdapter.notifyDataSetChanged()
                showType = 0

                tv_province.setText("省")
                tv_city.setText("市")
            }
        }
        ll_city.setOnClickListener {
            if (showType > 1) {
                list.clear()
                list.addAll(cityList)
                locationAdapter.notifyDataSetChanged()
                showType = 1

                tv_city.setText("市")
            }
        }
    }
}
