package com.yingbao.career.ui.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yingbao.career.Base.BaseActivity;
import com.yingbao.career.R;
import com.yingbao.career.http.BaseObserver;
import com.yingbao.career.http.RetrofitFactory;
import com.yingbao.career.http.resultbean.BannerResultBean;
import com.yingbao.career.http.resultbean.RecommendCourseListBean;
import com.yingbao.career.ui.home.adapter.HomeClassVideoAdapter;
import com.yingbao.career.ui.home.adapter.ImageResourceViewHolder;
import com.yingbao.career.ui.personal.LoginActivity;
import com.yingbao.career.ui.personal.MeActivity;
import com.yingbao.career.ui.videodownload.DownloadActivity;
import com.yingbao.career.utils.CareerSPHelper;
import com.yingbao.career.utils.DisplayTools;
import com.yingbao.career.utils.EventMessageBean;
import com.yingbao.career.utils.EventType;
import com.yingbao.career.utils.ToastUtil;
import com.yingbao.career.video.PolyvPlayerActivity;
import com.yingbao.career.view.SpacesItemDecoration;
import com.yingbao.career.view.WrapGridLayoutManager;
import com.yingbao.career.view.YScrollView;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.bannerview.utils.BannerUtils;
import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.enums.IndicatorStyle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;


public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private BannerViewPager<String, ImageResourceViewHolder> mViewPager;
    int mSlideMode = IndicatorSlideMode.NORMAL;
    private final float BANNER_SCALE = (float) 1.9;
    private ImageView iv_testonline;
    private ImageView iv_knowledgetest;
    private ImageView iv_classtest;
    private ImageView icon_person;
    private RecyclerView rv_recommend;
    private HomeClassVideoAdapter homeClassVideoAdapter;
    private HomeClassVideoAdapter bestHomeClassVideoAdapter;
    private YScrollView sv_content;
    List<RecommendCourseListBean.ResultBean> recommendCourseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_home_page);
        DisplayTools.setTransparentStatusBar(this);
        EventBus.getDefault().register(this);
        initView();
        loadData();
    }

    private void loadData() {
        Glide.with(getApplicationContext())
                .load(CareerSPHelper.getUserImageUrl())
                .error(R.mipmap.ic_launcher)
                .into(icon_person);
        initBannerData();
        initRecomendClassData();
        loadRecommendCourseList();
        CareerSPHelper.getUserInfo();
    }

    private void loadRecommendCourseList() {
        Map<String,String> params = new HashMap<>();
        params.put("specialType","103");
        params.put("grade",String.valueOf(CareerSPHelper.getUserGrade()));
        params.put("num","4");
        RetrofitFactory.getInstance().getRecommendCourseList(params, new BaseObserver<List<RecommendCourseListBean.ResultBean>>() {
            @Override
            protected void onSuccees(List<RecommendCourseListBean.ResultBean> resultBeans) {
                if (resultBeans!=null&& resultBeans.size()>0){
                    recommendCourseList.clear();
                    recommendCourseList.addAll(resultBeans);
                    homeClassVideoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) {
                    ToastUtil.showShort(getApplicationContext(),"获取推荐课程失败！");
            }
        });

    }

    private void initRecomendClassData() {
        WrapGridLayoutManager layoutManager = new WrapGridLayoutManager(this, 2);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(DisplayTools.dip2px(getApplicationContext(),15));
        rv_recommend.setLayoutManager(layoutManager);
        rv_recommend.addItemDecoration(itemDecoration);
        homeClassVideoAdapter = new HomeClassVideoAdapter(R.layout.item_home_video, recommendCourseList);
        rv_recommend.setAdapter(homeClassVideoAdapter);
        homeClassVideoAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (CareerSPHelper.getUserId() == 0){
                LoginActivity.Companion.starLogin(this);
                return;
            }
            Intent intent = new Intent(this, PolyvPlayerActivity.class);
            intent.putExtra(PolyvPlayerActivity.COURSE_ID, recommendCourseList.get(position).getCourseId());
            intent.putExtra(PolyvPlayerActivity.COURSE_NAME, recommendCourseList.get(position).getCourseName());
            intent.putExtra(PolyvPlayerActivity.COURSE_TEACHER, recommendCourseList.get(position).getCourseTeacherName());
            intent.putExtra(PolyvPlayerActivity.COURSE_INFO, "");
            intent.putExtra(PolyvPlayerActivity.COURSE_START_TIME, recommendCourseList.get(position).getCourseStartTime());
            startActivity(intent);
        });
    }

    private void initView() {
        mViewPager = findViewById(R.id.banner_view);
        iv_testonline = findViewById(R.id.iv_testonline);
        iv_knowledgetest = findViewById(R.id.iv_knowledgetest);
        iv_classtest = findViewById(R.id.iv_classtest);
        icon_person = findViewById(R.id.icon_person);
        rv_recommend = findViewById(R.id.rv_recommend);
        sv_content = findViewById(R.id.sv_content);

        findViewById(R.id.ll_video_class).setOnClickListener(this);
        findViewById(R.id.ll_smart_exercise).setOnClickListener(this);
        findViewById(R.id.ll_collect).setOnClickListener(this);
        findViewById(R.id.ll_cache).setOnClickListener(this);
        findViewById(R.id.iv_testonline).setOnClickListener(this);
        findViewById(R.id.rl_search).setOnClickListener(this);
        findViewById(R.id.icon_person).setOnClickListener(this);
        findViewById(R.id.iv_knowledgetest).setOnClickListener(this);
        findViewById(R.id.iv_classtest).setOnClickListener(this);
        findViewById(R.id.ll_more).setOnClickListener(this);
        findViewById(R.id.tv_jubao).setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.12377.cn/");
            Intent intent =new  Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        rv_recommend.setHasFixedSize(true);
        rv_recommend.setNestedScrollingEnabled(false);

        float banner_width = DisplayTools.getResolutionWidthInfo(getApplicationContext()) - DisplayTools.dip2px(getApplicationContext(), 15);
        int banner_height = (int) (banner_width / BANNER_SCALE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mViewPager.getLayoutParams();
        layoutParams.height = banner_height;
        mViewPager.setLayoutParams(layoutParams);
        mViewPager.setIndicatorSliderGap(BannerUtils.dp2px(6))
                .setRoundCorner(BannerUtils.dp2px(6))
                .setHolderCreator(() -> new ImageResourceViewHolder(0));

        // 设置随机练习图片的宽高
        int margin = DisplayTools.dip2px(this, 15);
        int imageWidth = (DisplayTools.getResolutionWidthInfo(getApplicationContext()) - margin*4) / 3;

        ViewGroup.MarginLayoutParams testMargin = new ViewGroup.MarginLayoutParams(iv_testonline.getLayoutParams());
        testMargin.setMargins(margin, 0, 0, 0);// 在左边距20像素，顶边距0像素的位置显示第一个图片
        LinearLayout.LayoutParams layoutParamsLeft = new LinearLayout.LayoutParams(testMargin);
        layoutParamsLeft.width = imageWidth; // 设置图片的宽度
        layoutParamsLeft.height = imageWidth;// 设置图片的高度
        iv_testonline.setLayoutParams(layoutParamsLeft);


        ViewGroup.MarginLayoutParams knowMargin = new ViewGroup.MarginLayoutParams(iv_knowledgetest.getLayoutParams());
        knowMargin.setMargins(margin*2, 0, margin, 0);// 在左边距20像素，顶边距0像素的位置显示第一个图片
        LinearLayout.LayoutParams knowMainParam = new LinearLayout.LayoutParams(knowMargin);
        knowMainParam.width = imageWidth; // 设置图片的宽度
        knowMainParam.height = imageWidth;// 设置图片的高度
        iv_knowledgetest.setLayoutParams(knowMainParam);


        ViewGroup.MarginLayoutParams classMargin = new ViewGroup.MarginLayoutParams(iv_classtest.getLayoutParams());
        classMargin.setMargins(0, 0, margin, 0);// 在左边距20像素，顶边距0像素的位置显示第一个图片
        LinearLayout.LayoutParams classMainParam = new LinearLayout.LayoutParams(classMargin);
        classMainParam.width = imageWidth; // 设置图片的宽度
        classMainParam.height = imageWidth;// 设置图片的高度
        iv_classtest.setLayoutParams(classMainParam);

        OverScrollDecoratorHelper.setUpOverScroll(sv_content);
    }


    private void initBannerData() {
        RetrofitFactory.getInstance().queryBanners(new BaseObserver<List<BannerResultBean.ResultBean>>() {
            @Override
            protected void onSuccees(List<BannerResultBean.ResultBean> resultBeans) {
                List<String> imageUrls = new ArrayList<>();
                for (int i = 0; i < resultBeans.size(); i++) {
                    imageUrls.add(resultBeans.get(i).getImageUrl());
                }
                int checkedWidth = getResources().getDimensionPixelOffset(R.dimen.dp_10);
                int normalWidth = getNormalWidth();
                mViewPager.setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                        .setIndicatorGravity(IndicatorGravity.CENTER)
                        .setIndicatorSliderGap(BannerUtils.dp2px(4))
                        .setPageMargin(0)
                        .setCanLoop(true)
                        .setInterval(5000)
                        .setIndicatorSlideMode(mSlideMode)
                        .setIndicatorHeight(getResources().getDimensionPixelOffset(R.dimen.dp_4))
                        .setOnPageClickListener(position ->jumpToPage(resultBeans.get(position).getHrefUrl()))
                        .setIndicatorSliderColor(getResources().getColor(R.color.career_ACACAC), getResources().getColor(R.color.career_white))
                        .setIndicatorSliderWidth(normalWidth, checkedWidth).create(imageUrls);
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) {

            }
        });

    }

    private void jumpToPage(String hrefUrl) {
        String[] urlArray = hrefUrl.split(":");
        if (TextUtils.equals(urlArray[0],"local")){
            if (CareerSPHelper.getUserId() == 0){
                LoginActivity.Companion.starLogin(this);
                return;
            }
            if (TextUtils.equals(urlArray[1],"syncclass")){
                Intent intent  = new Intent(this,CourseListActivity.class);
                intent.putExtra("special_type","103");
                intent.putExtra("show_grade",true);
                startActivity(intent);
            }else  if (TextUtils.equals(urlArray[1],"testquestion")){
                Intent onLineIntent = new Intent(this,SmartExerciseActivity.class);
                startActivity(onLineIntent);
            }else  if (TextUtils.equals(urlArray[1],"testcourse")){
                Intent intent  = new Intent(this,CourseListActivity.class);
                intent.putExtra("special_type","104");
                intent.putExtra("show_grade",false);
                startActivity(intent);
            }
        }
    }

    private int getNormalWidth() {
        if (mSlideMode == IndicatorSlideMode.SMOOTH || mSlideMode == IndicatorSlideMode.WORM) {
            return getResources().getDimensionPixelOffset(R.dimen.dp_10);
        } else {
            return getResources().getDimensionPixelOffset(R.dimen.dp_4);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mViewPager != null) {
            mViewPager.stopLoop();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessageBean messageBean){
        if (messageBean.messageType == EventType.LOGIN_OUT || messageBean.messageType == EventType.GET_USERINFO_SUCCESS){
            loadRecommendCourseList();
            Glide.with(getApplicationContext())
                    .load(CareerSPHelper.getUserImageUrl())
                    .error(R.mipmap.ic_launcher)
                    .into(icon_person);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (mViewPager != null) {
            mViewPager.startLoop();
        }
    }

    @Override
    public void onClick(View v) {
        if (CareerSPHelper.getUserId() == 0){
            LoginActivity.Companion.starLogin(this);
            return;
        }
        if (v.getId() == R.id.ll_video_class){
            startActivity(new Intent(this,ClassCenterActivity.class));
        }else  if (v.getId() == R.id.ll_smart_exercise){
            Intent onLineIntent = new Intent(this,SmartExerciseActivity.class);
            startActivity(onLineIntent);
        }else  if (v.getId() == R.id.iv_knowledgetest){
            Intent knowledgeIntent = new Intent(this, SubjectActivity.class);
            knowledgeIntent.putExtra(SubjectActivity.Companion.getEXERCISE_TYPE(),1);
            startActivity(knowledgeIntent);
        }else  if (v.getId() == R.id.iv_classtest){
            Intent classIntent = new Intent(this, SubjectActivity.class);
            classIntent.putExtra(SubjectActivity.Companion.getEXERCISE_TYPE(),2);
            startActivity(classIntent);
        }else  if (v.getId() == R.id.ll_collect){
            startActivity(new Intent(this,CollectActivity.class));
        }else  if (v.getId() == R.id.ll_cache){
            startActivity(new Intent(this, DownloadActivity.class));
        }else  if (v.getId() == R.id.iv_testonline){
            startActivity(new Intent(this, SubjectActivity.class));
        }else  if (v.getId() == R.id.rl_search){
            Intent intent  = new Intent(this, CourseListActivity.class);
            intent.putExtra("special_type","103");
            intent.putExtra("show_grade",true);
            intent.putExtra("show_course",true);
            startActivity(intent);
        } else if (v.getId() == R.id.icon_person) {
            startActivity(new Intent(this, MeActivity.class));
        } else if (v.getId() == R.id.ll_more) {
            Intent intent  = new Intent(this,CourseListActivity.class);
            intent.putExtra(CourseListActivity.SPECILE_TYPE,"103");
            intent.putExtra(CourseListActivity.SHOW_GRADE,true);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
