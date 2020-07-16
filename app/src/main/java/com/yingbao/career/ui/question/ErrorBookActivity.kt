package com.yingbao.career.ui.question

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.http.BaseObserver
import com.yingbao.career.http.RetrofitFactory
import com.yingbao.career.ui.home.bean.SubjectItemBean
import com.yingbao.career.ui.question.fragment.ErrorNoteFragment
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.utils.EventMessageBean
import com.yingbao.career.utils.EventType
import com.yingbao.career.view.ScaleTransitionPagerTitleView
import kotlinx.android.synthetic.main.activity_error_book.*
import kotlinx.android.synthetic.main.wygkplus_slidingtab_layout.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class ErrorBookActivity : BaseActivity() {

    private lateinit var mTabAdapter: TabPagerAdapter
    private val list = ArrayList<SubjectItemBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_book)
        DisplayTools.setTransparentStatusBar(this)
        getHasCollectSujects()
        EventBus.getDefault().register(this)
    }

    private fun getHasCollectSujects() {
        showWaitDialog()
        RetrofitFactory.getInstance()
            .getHasWrongSubjects(object : BaseObserver<MutableList<Int>>() {
                override fun onSuccees(fList: MutableList<Int>) {
                    list.clear()
                    if (fList==null || fList.size==0){
                        layout_container.visibility = View.GONE
                        tv_empty.visibility = View.VISIBLE
                    }else{
                        layout_container.visibility = View.VISIBLE
                        tv_empty.visibility = View.GONE
                        val allSubject =   SubjectItemBean()
                        allSubject.subjectId = 0
                        allSubject.subjectName ="全部"
                        list.add(allSubject)
                        val subjectIdsArray = resources.getIntArray(R.array.subject_ids_array)
                        val subjectNameArray = resources.getStringArray(R.array.subject_name_array)
                        val subjectMap = LinkedHashMap<Int, String>()
                        for (i in 0 until Math.min(subjectNameArray.size, subjectIdsArray.size)) {
                            subjectMap[subjectIdsArray.get(i)] = subjectNameArray.get(i)
                        }
                        fList.forEach {
                            val subject =   SubjectItemBean()
                            subject.subjectId = it
                            subject.subjectName =subjectMap[it]
                            list.add(subject)
                        }
                    }
                    setViewPageData()
                    dismissWaitDialog()
                }

                override fun onFailure(error: String, isNetWorkError: Boolean) {
                    layout_container.visibility = View.GONE
                    tv_empty.visibility = View.VISIBLE
                    dismissWaitDialog()
                    showErrorMessage(error)
                }
            })
    }
    private fun setViewPageData() {
        mTabAdapter = TabPagerAdapter()
        mViewPager.isFragmentCanSlide = true
        mViewPager.adapter = mTabAdapter
        mTabAdapter.setFragments(list)//需要更新
        mTabAdapter.notifyDataSetChanged()
        mViewPager.offscreenPageLimit = 1
        mViewPager.currentItem = 0
        val commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return  list.size
            }
            override  fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView =  ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(list.get(index).subjectName);
                simplePagerTitleView.setTextSize(16f);
                simplePagerTitleView.setNormalColor(Color.parseColor("#666666"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#FFAD00"));
                simplePagerTitleView.setOnClickListener { mViewPager.currentItem = index }
                return simplePagerTitleView
            }

            override  fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                indicator.setColors(Color.parseColor("#FFAD00"))
                return indicator
            }
        }
        tabs_subjects.navigator = commonNavigator
        ViewPagerHelper.bind(tabs_subjects, mViewPager);
    }

    protected inner class TabPagerAdapter() : FragmentStatePagerAdapter(supportFragmentManager) {
        private var subjectIds = mutableListOf<SubjectItemBean>()
        internal fun setFragments(fragments: List<SubjectItemBean>) {
            subjectIds.clear()
            subjectIds.addAll(fragments)
            notifyDataSetChanged()
        }

        override fun getItem(position: Int): Fragment {
            return ErrorNoteFragment.newInstance(subjectIds[position].subjectId)
        }

        override fun getCount(): Int {
            return subjectIds.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return getTabTitle(position)
        }

        override fun getItemPosition(`object`: Any): Int {
            return PagerAdapter.POSITION_NONE
        }

        fun getTabTitle(position: Int): CharSequence {
            return subjectIds[position].subjectName
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(messageBean: EventMessageBean) {
        if (messageBean.messageType == EventType.RELOAD_WRONG_RECORD_TABS) {
            getHasCollectSujects()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
