package com.yingbao.career.ui.personal

import android.content.Intent
import android.os.Bundle
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
import kotlinx.android.synthetic.main.wygkplus_activity_gaokaolocation.*
import java.util.*

class GaoKaoLocationActivity : BaseActivity() {
    private val list = ArrayList<LocationResultBean.ResultBean>()
    lateinit var locationAdapter: LocationItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wygkplus_activity_gaokaolocation)
        DisplayTools.setTransparentStatusBar(this)
        initData()
        initClick()
        getPrvinceData();
    }

    private fun getPrvinceData() {
        RetrofitFactory.getInstance().getLocationByParentId("0", object : BaseObserver<List<LocationResultBean.ResultBean>>() {
            override fun onSuccees(resultBean: List<LocationResultBean.ResultBean>) {
                list.addAll(resultBean)
                locationAdapter.notifyDataSetChanged()

            }

            override fun onFailure(error: String, isNetWorkError: Boolean) {
                ToastUtil.showShort(applicationContext,error)
            }
        })
    }


    private fun initData() {
        tv_title.setText("高考所在地")
        tv_edit.visibility = View.GONE

        locationAdapter = LocationItemAdapter(list)
        rv_list.adapter = locationAdapter
        rv_list.setLayoutManager(LinearLayoutManager(applicationContext))

    }

    private fun initClick() {
        locationAdapter.setOnItemClickListener { _, _, position ->
            list.forEachIndexed { index, resultBean ->
                if (index == position){
                   resultBean.isCheckState = true
                }else{
                    resultBean.isCheckState = false
                }
            }
            locationAdapter.notifyDataSetChanged()
            val intent = Intent()
            intent.putExtra("provinceId",list.get(position).id)
            intent.putExtra("provinceName",list.get(position).name)
            setResult(2003,intent)
            finish()
        }
    }
}
