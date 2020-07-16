package com.yingbao.career.ui.home.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.http.resultbean.CourseListResultBean
import com.yingbao.career.ui.home.adapter.CourseListAdapter
import com.yingbao.career.ui.home.adapter.SearchGradeListAdapter
import com.yingbao.career.ui.home.bean.FilterTagBean
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.utils.ToastUtil
import com.yingbao.career.video.PolyvPlayerActivity
import kotlinx.android.synthetic.main.activity_course_list.*

class CourseListActivity : BaseActivity() {
    private lateinit var classAdapter: SearchGradeListAdapter
    private val list = ArrayList<CourseListResultBean.ResultBean.RecordsBean>()
    private lateinit var adapter: CourseListAdapter
    //科目
    private val classList = ArrayList<FilterTagBean>()
    private val subjectList = ArrayList<FilterTagBean>()
    lateinit var subjectAdapter: SearchGradeListAdapter
    //高一高二
    private val gradeList = ArrayList<FilterTagBean>()
    lateinit var gradeAdapter: SearchGradeListAdapter
    private var specialType = "103"
    private var mGradeId = 0
    private var mSubjectId = 0
    private var mCourseName = ""
    private var pageIndex = 1
    private val pageSize = 10
    private var  isShowClassTypes = false
    private var  isShowGradeTypes = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)
        DisplayTools.setTransparentStatusBar(this)
        getBasicData()
        initData()
        loadData()
        initClick()
        initFileterView()
    }

    private fun getBasicData() {
        specialType = intent.getStringExtra(SPECILE_TYPE)
        isShowGradeTypes = intent.getBooleanExtra(SHOW_GRADE,false)
        isShowClassTypes = intent.getBooleanExtra("show_course",false)
    }

    private fun initFileterView() {
        val subjectNameArray = resources.getStringArray(R.array.subject_name_array)
        val subjectIdsArray = resources.getIntArray(R.array.subject_ids_array)
        subjectNameArray.forEach {
            val tagBean = FilterTagBean()
            tagBean.tagName = it
            subjectList.add(tagBean)
        }
        subjectIdsArray.forEachIndexed { index, s ->
            subjectList[index].tagId = s
        }
        subjectAdapter = SearchGradeListAdapter(this,subjectList)
        rv_subjects.setLayoutManager(GridLayoutManager(this, 3))
        rv_subjects.setAdapter(subjectAdapter)
        subjectAdapter.setOnItemClickListener({_,_,position->
            subjectList.forEachIndexed { index, filterTagBean ->
                if (index == position){
                    filterTagBean.isChecked = true
                }else{
                    filterTagBean.isChecked = false
                }
            }
            subjectAdapter.notifyDataSetChanged()

        })

        if (isShowClassTypes){
            val tagCourse1= FilterTagBean()
            tagCourse1.tagId = 103
            tagCourse1.tagName = "同步课程"
            val tagCourse2= FilterTagBean()
            tagCourse2.tagId = 104
            tagCourse2.tagName = "备考专题"
            classList.add(tagCourse1)
            classList.add(tagCourse2)
            classAdapter = SearchGradeListAdapter(this,classList)
            rv_class.setLayoutManager(GridLayoutManager(this, 3))
            rv_class.setAdapter(classAdapter)
            rv_class.visibility = View.VISIBLE
            tv_class.visibility = View.VISIBLE
            classAdapter.setOnItemClickListener({_,_,position->
                classList.forEachIndexed { index, filterTagBean ->
                    if (index == position){
                        filterTagBean.isChecked = true
                    }else{
                        filterTagBean.isChecked = false
                    }
                }
                classAdapter.notifyDataSetChanged()
            })
        }else{
            rv_class.visibility = View.GONE
            tv_class.visibility = View.GONE
        }

        if (isShowGradeTypes){
            rv_grade.visibility = View.VISIBLE
            tv_grade.visibility = View.VISIBLE
            val tagClass1= FilterTagBean()
            tagClass1.tagId = 16
            tagClass1.tagName = "高一"
            val tagClass2= FilterTagBean()
            tagClass2.tagId = 17
            tagClass2.tagName = "高二"
            val tagClass3= FilterTagBean()
            tagClass3.tagId = 18
            tagClass3.tagName = "高三"
            gradeList.add(tagClass1)
            gradeList.add(tagClass2)
            gradeList.add(tagClass3)
            gradeAdapter = SearchGradeListAdapter(this,gradeList)
            rv_grade.setLayoutManager(GridLayoutManager(this, 3))
            rv_grade.setAdapter(gradeAdapter)
            gradeAdapter.setOnItemClickListener({_,_,position->
                gradeList.forEachIndexed { index, filterTagBean ->
                    if (index == position){
                        filterTagBean.isChecked = true
                    }else{
                        filterTagBean.isChecked = false
                    }
                }
                gradeAdapter.notifyDataSetChanged()
            })
        }else{
            rv_grade.visibility = View.GONE
            tv_grade.visibility = View.GONE
        }
    }

    private fun initClick() {
//        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        val width = resources.displayMetrics.widthPixels * 2 / 3
        val params = cl_paper_filter.getLayoutParams() as DrawerLayout.LayoutParams
        params.width = width
        cl_paper_filter.setLayoutParams(params)
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

        ll_filter.setOnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.END)) {
                drawer_layout.closeDrawer(GravityCompat.END)
            } else {
                drawer_layout.openDrawer(GravityCompat.END)
            }
        }
        refresh_layout.setEnableAutoLoadMore(true)
        refresh_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                pageIndex = 1
                loadData()
            }
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                pageIndex++
                loadData()
            }
        })
        adapter.setOnItemClickListener({ _, _, position ->
            val intent = Intent(this, PolyvPlayerActivity::class.java)
            intent.putExtra(PolyvPlayerActivity.COURSE_ID, list.get(position).courseId)
            intent.putExtra(PolyvPlayerActivity.COURSE_NAME, list.get(position).courseName)
            intent.putExtra(PolyvPlayerActivity.COURSE_TEACHER, list.get(position).courseTeacherName)
            intent.putExtra(PolyvPlayerActivity.COURSE_INFO, "")
            intent.putExtra(PolyvPlayerActivity.COURSE_START_TIME, list.get(position).courseStartTime)
            startActivity(intent)
        })
        btn_reset_filter.setOnClickListener {
            subjectList.forEach {
                it.isChecked = false
            }
            subjectAdapter.notifyDataSetChanged()
            gradeList.forEach {
                it.isChecked = false
            }
            gradeAdapter.notifyDataSetChanged()
        }
        btn_sure_filter.setOnClickListener {
            var courseId = 0
            var subjectId = 0
            var gradeId = 0
            classList.forEach {
                if (it.isChecked){
                    courseId = it.tagId
                }
            }
            subjectList.forEach {
                if (it.isChecked){
                    subjectId = it.tagId
                }
            }
            gradeList.forEach {
                if (it.isChecked){
                    gradeId = it.tagId
                }
            }

            if (courseId == 0 && subjectId == 0&& gradeId == 0){
                ToastUtil.showShort(applicationContext,"请选择筛选的条件")
                return@setOnClickListener
            }
            mSubjectId = subjectId
            if (isShowGradeTypes){
                mGradeId = gradeId
            }
            if (isShowClassTypes){
                specialType = courseId.toString()
            }
            pageIndex = 1
            drawer_layout.closeDrawer(GravityCompat.END)
            loadData()
        }
        et_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (TextUtils.isEmpty(et_search.text.toString())){
                    ToastUtil.showShort(applicationContext,"请输入搜索课程的名称")
                    return@OnEditorActionListener true
                }
                mCourseName = et_search.text.toString()
                pageIndex = 1
                loadData()
                //隐藏键盘
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0)
                return@OnEditorActionListener true
            }
            false
        })

        // 监控
        et_search.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
             /* if (TextUtils.isEmpty(et_search.getText().toString().trim({ it <= ' ' }))) {
                    iv_clear_hint.setVisibility(View.GONE)
                    Name.clear()
                    highSchoolAdapter.notifyDataSetChanged()
                } else {
                    iv_clear_hint.setVisibility(View.VISIBLE)
                    pageIndex = 1
                    SearchUni(ed_search.getText().toString().trim({ it <= ' ' }))
                }*/
            }
        })
    }

    private fun initData() {
        adapter = CourseListAdapter(
            R.layout.item_search_result,
            list
        )
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_search.layoutManager = layoutManager
        rv_search.adapter = adapter
    }

    private fun loadData() {
        val map = HashMap<String, String>()
        map["pageNo"]= pageIndex.toString()
        map["pageSize" ]= pageSize.toString()
        map["specialType"] = specialType
        map["grade"] = mGradeId.toString()
        map["subject"] = mSubjectId.toString()
        map["courseName"] = mCourseName
        RetrofitFactory.getInstance()
            .getCourseList(map, object : BaseObserver<CourseListResultBean.ResultBean>() {
                override fun onSuccees(resultBean: CourseListResultBean.ResultBean) {
                    if (pageIndex == 1) list.clear()
                    list.addAll(resultBean.records)
                    adapter.notifyDataSetChanged()
                    finishRefresh()
                }

                override fun onFailure(error: String, isNetWorkError: Boolean) {
                    finishRefresh()
                }
            })
    }
    /**
     * 结束刷新效果
     */
    private fun finishRefresh() {
        refresh_layout.finishRefresh()
        refresh_layout.finishLoadMore()
    }

    override fun onResume() {
        super.onResume()
    }
    companion object{
        const val SPECILE_TYPE = "special_type"
        const val SHOW_GRADE = "show_grade"
    }
}
