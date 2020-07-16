package com.yingbao.career.ui.videodownload

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.arialyy.aria.core.Aria
import com.yingbao.career.Base.BaseActivity
import com.yingbao.career.R
import com.yingbao.career.utils.DisplayTools
import com.yingbao.career.video.PolyvDownloadFragment
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

class DownloadActivity : BaseActivity() {

    private lateinit var mTabAdapter: TabPagerAdapter
    private val mFragments = LinkedList<Fragment>()//每一页Fragment
    private lateinit  var downloadedFragment: PolyvDownloadFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_record)
        DisplayTools.setTransparentStatusBar(this)
        tv_title.text = "缓存下载"
        setViewPageData()
        Aria.download(this).resumeAllTask();
    }


    private fun setViewPageData() {
        mTabAdapter = TabPagerAdapter()
        mViewPager.isFragmentCanSlide = true
        mViewPager.adapter = mTabAdapter
        mFragments.clear()
        var bundle = Bundle()
        downloadedFragment = PolyvDownloadFragment()
        bundle.putBoolean("isFinished", true)
        downloadedFragment.setArguments(bundle)
        val downloadingFragment = PolyvDownloadFragment()
        bundle = Bundle()
        bundle.putBoolean("isFinished", false)
        downloadingFragment.setArguments(bundle)
        mFragments.add(downloadedFragment)
        mFragments.add(downloadingFragment)
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
                    simplePagerTitleView.setText("已下载");
                } else {
                    simplePagerTitleView.setText("下载中");
                }
                simplePagerTitleView.setTextSize(16f);
                simplePagerTitleView.setNormalColor(Color.parseColor("#666666"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#FFAD00"));
                simplePagerTitleView.setOnClickListener { mViewPager.currentItem = index }
                return simplePagerTitleView
            }

            override  fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.lineWidth=UIUtil.dip2px(context,32.0).toFloat()
                indicator.setColors(Color.parseColor("#FFAD00"))
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
                return  "已下载"
            } else {
                return  "下载中"
            }
        }

    }

    fun getDownloadedFragment(): PolyvDownloadFragment? {
        return downloadedFragment
    }
}
