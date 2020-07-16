package com.yingbao.career.ui.question

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.ui.question.fragment.QuestionRecordFragment
import com.yingbao.career.ui.question.fragment.VideoRecordFragment
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.view.ScaleTransitionPagerTitleView
import kotlinx.android.synthetic.main.white_titlebar.*
import kotlinx.android.synthetic.main.wygkplus_slidingtab_layout.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import java.util.*

class StudyRecordActivity : BaseActivity() {

    private lateinit var mTabAdapter: TabPagerAdapter
    private val mFragments = LinkedList<Fragment>()//每一页Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_record)
        DisplayTools.setTransparentStatusBar(this)
        tv_title.text = "学习记录"
        setViewPageData()
    }


    private fun setViewPageData() {
        mTabAdapter = TabPagerAdapter()
        mViewPager.isFragmentCanSlide = true
        mViewPager.adapter = mTabAdapter
        mFragments.clear()
        mFragments.add(VideoRecordFragment.newInstance())
        mFragments.add(QuestionRecordFragment.newInstance())
        mTabAdapter.setFragments(mFragments)//需要更新
        mTabAdapter.notifyDataSetChanged()
        mViewPager.offscreenPageLimit = 1
        mViewPager.currentItem = 0

        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {

            override fun getCount(): Int {
                return  2
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView = ScaleTransitionPagerTitleView(context);
                if (index == 0) {
                    simplePagerTitleView.setText("视频统计");
                } else {
                    simplePagerTitleView.setText("习题统计");
                }
                simplePagerTitleView.setTextSize(16f);
                simplePagerTitleView.setNormalColor(Color.parseColor("#666666"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#0D6EFF"));
                simplePagerTitleView.setOnClickListener { mViewPager.currentItem = index }
                return simplePagerTitleView
            }

            override  fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.lineWidth=UIUtil.dip2px(context,32.0).toFloat()
                indicator.setColors(Color.parseColor("#0D6EFF"))
                return indicator
            }
        }
        tabs_subjects.navigator = commonNavigator
        ViewPagerHelper.bind(tabs_subjects, mViewPager);
    }

    protected inner class TabPagerAdapter () : FragmentStatePagerAdapter(supportFragmentManager) {
        private val mFragments: ArrayList<Fragment>

        init {
            mFragments = ArrayList()
        }

        internal fun setFragments(fragments: List<Fragment>) {
            mFragments.clear()
            mFragments.addAll(fragments)
            notifyDataSetChanged()
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return getTabTitle(position)
        }

        override fun getItemPosition(`object`: Any): Int {
            return PagerAdapter.POSITION_NONE
        }

        fun getTabTitle(position: Int): CharSequence {
            if (position == 0) {
                return  "视频统计"
            } else {
                return  "习题统计"
            }
        }

    }
}
