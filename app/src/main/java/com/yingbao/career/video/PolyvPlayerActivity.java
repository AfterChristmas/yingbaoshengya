package com.yingbao.career.video;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easefun.polyvsdk.PolyvBitRate;
import com.easefun.polyvsdk.PolyvDownloader;
import com.easefun.polyvsdk.PolyvDownloaderErrorReason;
import com.easefun.polyvsdk.PolyvDownloaderManager;
import com.easefun.polyvsdk.PolyvSDKUtil;
import com.easefun.polyvsdk.download.listener.IPolyvDownloaderProgressListener2;
import com.easefun.polyvsdk.marquee.PolyvMarqueeItem;
import com.easefun.polyvsdk.marquee.PolyvMarqueeView;
import com.easefun.polyvsdk.srt.PolyvSRTItemVO;
import com.easefun.polyvsdk.video.PolyvMediaInfoType;
import com.easefun.polyvsdk.video.PolyvPlayErrorReason;
import com.easefun.polyvsdk.video.PolyvSeekType;
import com.easefun.polyvsdk.video.PolyvVideoUtil;
import com.easefun.polyvsdk.video.PolyvVideoView;
import com.easefun.polyvsdk.video.auxiliary.PolyvAuxiliaryVideoView;
import com.easefun.polyvsdk.video.listener.IPolyvOnAdvertisementEventListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnCompletionListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureClickListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureDoubleClickListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureLeftDownListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureLeftUpListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureLongTouchListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureRightDownListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureRightUpListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureSwipeLeftListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureSwipeRightListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnInfoListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnPlayPauseListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnPreparedListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnVideoPlayErrorListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnVideoSRTListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnVideoSRTPreparedListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnVideoStatusListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnVideoTimeoutListener;
import com.easefun.polyvsdk.vo.PolyvADMatterVO;
import com.easefun.polyvsdk.vo.PolyvVideoVO;
import com.yingbao.career.R;
import com.yingbao.career.common.MyApplication;
import com.yingbao.career.http.BaseObserver;
import com.yingbao.career.http.RetrofitFactory;
import com.yingbao.career.http.resultbean.BooleanDataBean;
import com.yingbao.career.http.resultbean.FavoriteStateResultBean;
import com.yingbao.career.http.resultbean.VideoListResultBean;
import com.yingbao.career.ui.home.adapter.VideoPlayListVideoAdapter;
import com.yingbao.career.ui.question.KnowledgeTestActivity;
import com.yingbao.career.utils.CareerSPHelper;
import com.yingbao.career.utils.LogUtil;
import com.yingbao.career.utils.PolyvScreenUtils;
import com.yingbao.career.utils.ToastUtil;
import com.yingbao.career.video.database.PolyvDownloadSQLiteHelper;
import com.yingbao.career.video.utils.PolyvErrorMessageUtils;
import com.yingbao.career.video.widget.PolyvLoadingLayout;
import com.yingbao.career.video.widget.PolyvNetworkDetection;
import com.yingbao.career.video.widget.PolyvPlayerLightView;
import com.yingbao.career.video.widget.PolyvPlayerPlayErrorView;
import com.yingbao.career.video.widget.PolyvPlayerPlayRouteView;
import com.yingbao.career.video.widget.PolyvPlayerPreviewView;
import com.yingbao.career.video.widget.PolyvPlayerProgressView;
import com.yingbao.career.video.widget.PolyvPlayerVolumeView;
import com.yingbao.career.video.widget.PolyvTouchSpeedLayout;

import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import www.linwg.org.lib.LCardView;

public class PolyvPlayerActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = PolyvPlayerActivity.class.getSimpleName();
    private PolyvPlayerTopFragment topFragment;
    private ImageView iv_screencast_search, iv_screencast_search_land;
    /**
     * 播放器的parentView
     */
    private RelativeLayout viewLayout = null;
    /**
     * 播放主视频播放器
     */
    private PolyvVideoView videoView = null;
    /**
     * 跑马灯控件
     */
    private PolyvMarqueeView marqueeView = null;
    private PolyvMarqueeItem marqueeItem = null;
    /**
     * 视频控制栏
     */
    private PolyvPlayerMediaController mediaController = null;
    /**
     * 底部字幕文本视图
     */
    private TextView srtTextView = null;
    /**
     * 顶部字幕文本试图
     */
    private TextView topSrtTextView = null;
    /**
     * 用于播放广告片头的播放器
     */
    private PolyvAuxiliaryVideoView auxiliaryVideoView = null;
    /**
     * 视频广告，视频片头加载缓冲视图
     */
    private ProgressBar auxiliaryLoadingProgress = null;
    /**
     * 广告倒计时
     */
    private TextView advertCountDown = null;
    /**
     * 缩略图界面
     */
    private PolyvPlayerPreviewView firstStartView = null;
    /**
     * 手势出现的亮度界面
     */
    private PolyvPlayerLightView lightView = null;
    /**
     * 手势出现的音量界面
     */
    private PolyvPlayerVolumeView volumeView = null;
    /**
     * 手势出现的进度界面
     */
    private PolyvPlayerProgressView progressView = null;
    /**
     * 手势出现的快进界面
     */
    private PolyvTouchSpeedLayout touchSpeedLayout = null;
    /**
     * 视频加载缓冲视图
     */
    private PolyvLoadingLayout loadingLayout = null;
    /**
     * 视频播放错误提示界面
     */
    private PolyvPlayerPlayErrorView playErrorView = null;
    /**
     * 线路切换界面
     */
    private PolyvPlayerPlayRouteView playRouteView = null;

    private int fastForwardPos = 0;
    private boolean isPlay = false;

    private boolean isBackgroundPlay = true;

    private String vid;
    private String videoName;
    private String videoCover;
    private int videoId;
    private int bitrate  = PolyvBitRate.ziDong.getNum();
    private boolean isMustFromLocal;
    private int fileType;

    private PolyvNetworkDetection networkDetection;
    private LinearLayout flowPlayLayout,ll_download,ll_collect;
    private TextView flowPlayButton, cancelFlowPlayButton,tv_download_status;
    private View.OnClickListener flowButtonOnClickListener;

    private BroadcastReceiver pipReceiver;
    private boolean isInPictureInPictureMode;

    private boolean isOnBackKeyPressed;
    private ServiceConnection playConnection;
    private PolyvBackgroundPlayService.PlayBinder playBinder;

    private float beforeTouchSpeed;
    public  static final String COURSE_ID = "course_id";
    public  static final String V_ID = "video_id";
    public  static final String COURSE_NAME = "course_name";
    public  static final String COURSE_TEACHER = "course_teacher";
    public  static final String COURSE_START_TIME = "course_Start_Time";
    public  static final String COURSE_INFO = "course_info";
    private int courseId = 0 ;
    private RecyclerView rv_video_list;
    private VideoPlayListVideoAdapter videoPlayListVideoAdapter;
    private List<VideoListResultBean.ResultBean.VideosBean> videoList = new  ArrayList<>();
    private int  collectId = -1;
    private int knowledgePointId = 0;
    private ImageView iv_collect;
    private TextView tv_collect;
    private TextView tv_video_list_title;
    private TextView tv_course_name;
    private TextView tv_course_teacher;
    private TextView tv_course_start_time;
    private TextView tv_course_info;
    private TextView tv_course_info_title;
    private TextView tv_subject;
    private LCardView ll_jump_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            savedInstanceState.putParcelable("android:support:fragments", null);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polyv_activity_player);
        PolyvScreenUtils.hideStatusBar(this);
        getBasicData();
        findIdAndNew();
        initVideoView();
        initPlayErrorView();
        initRouteView();
        PolyvScreenUtils.generateHeight16_9(this);
        initNetworkDetection(fileType);
        initInfoView();

        PolyvBackgroundPlayService.bindService(this, playConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                playBinder = (PolyvBackgroundPlayService.PlayBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        });
    }

    private void initInfoView() {
        if (courseId!=0){
            tv_video_list_title.setVisibility(View.VISIBLE);
            rv_video_list.setVisibility(View.VISIBLE);
            tv_course_info_title.setVisibility(View.VISIBLE);
            tv_course_info.setVisibility(View.VISIBLE);
            tv_subject.setVisibility(View.VISIBLE);
            tv_course_start_time.setVisibility(View.VISIBLE);
            tv_course_teacher.setVisibility(View.VISIBLE);
            initCourseInfoView();
            initViewListAdatper();
            loadCourserInfo();
        }else {
            tv_video_list_title.setVisibility(View.GONE);
            rv_video_list.setVisibility(View.GONE);
            tv_course_info_title.setVisibility(View.GONE);
            tv_course_info.setVisibility(View.GONE);
            tv_subject.setVisibility(View.GONE);
            tv_course_start_time.setVisibility(View.GONE);
            tv_course_teacher.setVisibility(View.GONE);
            tv_course_name.setText(videoName);
            play(vid, bitrate, true, isMustFromLocal);
            setJumpTestView();
            setLoadStateView();
            getCollectState();
        }
    }

    private void setJumpTestView(){
        if (knowledgePointId != 0) {
            ll_jump_test.setVisibility(View.VISIBLE);
        } else {
            ll_jump_test.setVisibility(View.GONE);
        }
    }
    private void initViewListAdatper() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_video_list.setLayoutManager(layoutManager);
        videoPlayListVideoAdapter = new VideoPlayListVideoAdapter(R.layout.item_video_videoplay, videoList);
        rv_video_list.setAdapter(videoPlayListVideoAdapter);
        videoPlayListVideoAdapter.setOnItemClickListener((adapter, view, position) -> {
            uploadWatchTime();
            vid = videoList.get(position).getVideoId();
            videoName = videoList.get(position).getVideoName();
            videoCover = videoList.get(position).getVideoCover();
            videoId = videoList.get(position).getId();
            knowledgePointId = videoList.get(position).getKnowledgePointId();
            play(vid, bitrate, true, isMustFromLocal);
            setJumpTestView();
            for (int i = 0; i < videoList.size(); i++) {
                if (i==position){
                    videoList.get(i).setPlaying(true);
                }else {
                    videoList.get(i).setPlaying(false);
                }
            }
            videoPlayListVideoAdapter.notifyDataSetChanged();
            setLoadStateView();
            getCollectState();
        });
    }

    private void getBasicData() {
        courseId = getIntent().getIntExtra(COURSE_ID,0);
        if (courseId == 0){
            vid = getIntent().getStringExtra(V_ID);
            videoId = getIntent().getIntExtra("videoId",0);
            videoName = getIntent().getStringExtra("videoName");
            videoCover = getIntent().getStringExtra("videoCover");
            knowledgePointId = getIntent().getIntExtra("knowledgePointId",0);
        }
        isMustFromLocal = getIntent().getBooleanExtra("isMustFromLocal", false);
        fileType = getIntent().getIntExtra("fileType", PolyvDownloader.FILE_VIDEO);
    }

    private void loadCourserInfo() {
        RetrofitFactory.getInstance()
                .getVideoList(String.valueOf(courseId), new BaseObserver<VideoListResultBean.ResultBean>() {
                    @Override
                    protected void onSuccees(VideoListResultBean.ResultBean resultBean) {
                        LogUtil.e("videoPlay== ",resultBean.getVideos().size()+"");
                        videoList.addAll(resultBean.getVideos());
                        videoPlayListVideoAdapter.notifyDataSetChanged();
                        if (videoList.size()==0){
                            ToastUtil.showShort(getApplicationContext(),"未找到相关视频！");
                            finish();
                            return;
                        }
                        vid = videoList.get(0).getVideoId();
                        videoName = videoList.get(0).getVideoName();
                        videoCover = videoList.get(0).getVideoCover();
                        videoId = videoList.get(0).getId();
                        knowledgePointId = videoList.get(0).getKnowledgePointId();
                        play(vid, bitrate, false, isMustFromLocal);
                        setJumpTestView();
                        for (int i = 0; i < videoList.size(); i++) {
                            if (i==0){
                                videoList.get(i).setPlaying(true);
                            }else {
                                videoList.get(i).setPlaying(false);
                            }
                        }
                        videoPlayListVideoAdapter.setTeacherName(resultBean.getCourseTeacherName());
                        videoPlayListVideoAdapter.notifyDataSetChanged();
                        setLoadStateView();
                        getCollectState();
                    }

                    @Override
                    protected void onFailure(String error, boolean isNetWorkError) {

                    }
                });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        vid = intent.getStringExtra(V_ID);
        videoName = intent.getStringExtra("videoName");
        videoCover = intent.getStringExtra("videoCover");
        boolean startNow = intent.getBooleanExtra("startNow", false);
        isMustFromLocal = intent.getBooleanExtra("isMustFromLocal", false);
        fileType = intent.getIntExtra("fileType", PolyvDownloader.FILE_VIDEO);
        mediaController.changeToSmallScreen();
        play(vid, bitrate, startNow, isMustFromLocal);
        setJumpTestView();
    }

    private void initNetworkDetection(int fileType) {
        networkDetection = new PolyvNetworkDetection(this);
        mediaController.setPolyvNetworkDetetion(networkDetection, flowPlayLayout, flowPlayButton, cancelFlowPlayButton, fileType);//传给mediaController，在切换清晰度时验证
        networkDetection.setOnNetworkChangedListener(new PolyvNetworkDetection.IOnNetworkChangedListener() {
            @Override
            public void onChanged(int networkType) {
                if (videoView.isLocalPlay())
                    return;
                if (networkDetection.isMobileType()) {
                    if (!networkDetection.isAllowMobile()) {
                        if (videoView.isPlaying()) {
                            videoView.pause(true);
                            flowPlayLayout.setVisibility(View.VISIBLE);
                            cancelFlowPlayButton.setVisibility(View.GONE);
                        }
                    }
                } else if (networkDetection.isWifiType()) {
                    if (flowPlayLayout.getVisibility() == View.VISIBLE) {
                        flowPlayLayout.setVisibility(View.GONE);
                        if (videoView.isInPlaybackState()) {
                            videoView.start();
                        } else {
                            play(vid, bitrate, true, isMustFromLocal);
                        }
                    }
                }
            }
        });
    }


    private void findIdAndNew() {
        viewLayout = (RelativeLayout) findViewById(R.id.view_layout);
        videoView = (PolyvVideoView) findViewById(R.id.polyv_video_view);
        marqueeView = (PolyvMarqueeView) findViewById(R.id.polyv_marquee_view);
        mediaController = (PolyvPlayerMediaController) findViewById(R.id.polyv_player_media_controller);
        srtTextView = (TextView) findViewById(R.id.srt);
        topSrtTextView = (TextView) findViewById(R.id.top_srt);
        auxiliaryVideoView = (PolyvAuxiliaryVideoView) findViewById(R.id.polyv_auxiliary_video_view);
        auxiliaryLoadingProgress = (ProgressBar) findViewById(R.id.auxiliary_loading_progress);
        advertCountDown = (TextView) findViewById(R.id.count_down);
        firstStartView = (PolyvPlayerPreviewView) findViewById(R.id.polyv_player_first_start_view);
        lightView = (PolyvPlayerLightView) findViewById(R.id.polyv_player_light_view);
        volumeView = (PolyvPlayerVolumeView) findViewById(R.id.polyv_player_volume_view);
        progressView = (PolyvPlayerProgressView) findViewById(R.id.polyv_player_progress_view);
        touchSpeedLayout = (PolyvTouchSpeedLayout) findViewById(R.id.polyv_player_touch_speed_layout);
        loadingLayout = (PolyvLoadingLayout) findViewById(R.id.loading_layout);
        playErrorView = (PolyvPlayerPlayErrorView) findViewById(R.id.polyv_player_play_error_view);
        playRouteView = (PolyvPlayerPlayRouteView) findViewById(R.id.polyv_player_play_route_view);
        flowPlayLayout = (LinearLayout) findViewById(R.id.flow_play_layout);
        flowPlayButton = (TextView) findViewById(R.id.flow_play_button);
        cancelFlowPlayButton = (TextView) findViewById(R.id.cancel_flow_play_button);
        tv_download_status = (TextView) findViewById(R.id.tv_download_status);
        iv_collect = (ImageView) findViewById(R.id.iv_collect);
        tv_collect = (TextView) findViewById(R.id.tv_collect);
        tv_video_list_title = (TextView) findViewById(R.id.tv_video_list_title);
        ll_jump_test =  findViewById(R.id.ll_jump_test);

        iv_screencast_search = (ImageView) mediaController.findViewById(R.id.iv_screencast_search);
        iv_screencast_search_land = (ImageView) mediaController.findViewById(R.id.iv_screencast_search_land);
        tv_course_name = findViewById(R.id.tv_course_name);
        tv_course_teacher = findViewById(R.id.tv_course_teacher);
        tv_course_start_time = findViewById(R.id.tv_course_start_time);
        tv_course_info = findViewById(R.id.tv_course_info);
        tv_course_info_title = findViewById(R.id.tv_course_info_title);
        ll_download = findViewById(R.id.ll_download);
        ll_collect =  findViewById(R.id.ll_collect);
        rv_video_list =  findViewById(R.id.rv_video_list);
        tv_subject =  findViewById(R.id.tv_subject);
        //投屏功能默认隐藏，如果需要请注释下面两行代码
        iv_screencast_search.setVisibility(View.GONE);
        iv_screencast_search_land.setVisibility(View.GONE);

        mediaController.initConfig(viewLayout);
        auxiliaryVideoView.setPlayerBufferingIndicator(auxiliaryLoadingProgress);
        videoView.setMediaController(mediaController);
        videoView.setAuxiliaryVideoView(auxiliaryVideoView);
        videoView.setPlayerBufferingIndicator(loadingLayout);
        loadingLayout.bindVideoView(videoView);
        // 设置跑马灯
        videoView.setMarqueeView(marqueeView, marqueeItem = new PolyvMarqueeItem()
                .setStyle(PolyvMarqueeItem.STYLE_ROLL) //样式
                .setDuration(10000) //时长
                .setText("POLYV Android SDK") //文本
                .setSize(16) //字体大小
                .setColor(Color.YELLOW) //字体颜色
                .setTextAlpha(70) //字体透明度
                .setInterval(1000) //隐藏时间
                .setLifeTime(1000) //显示时间
                .setTweenTime(1000) //渐隐渐现时间
                .setHasStroke(true) //是否有描边
                .setBlurStroke(true) //是否模糊描边
                .setStrokeWidth(3) //描边宽度
                .setStrokeColor(Color.MAGENTA) //描边颜色
                .setReappearTime(3000) // 设置跑马灯再次出现的间隔
                .setStrokeAlpha(70)); //描边透明度
        ll_jump_test.setOnClickListener(this);
    }

    private void initCourseInfoView() {
        if (courseId != 0) {
            tv_course_name.setText(getIntent().getStringExtra(COURSE_NAME));
            tv_course_teacher.setText(getIntent().getStringExtra(COURSE_TEACHER));
            tv_course_start_time.setText(getIntent().getStringExtra(COURSE_START_TIME));
            String courseInfo = getIntent().getStringExtra(COURSE_INFO);
            if (!TextUtils.isEmpty(courseInfo)){
                tv_course_info_title.setVisibility(View.VISIBLE);
                tv_course_info.setVisibility(View.VISIBLE);
                tv_course_info.setText(courseInfo);
            }else {
                tv_course_info_title.setVisibility(View.GONE);
                tv_course_info.setVisibility(View.GONE);
            }
        }else {
            //视频模式

        }

    }

    private void initVideoView() {
        videoView.setOpenAd(false);
        videoView.setOpenTeaser(true);
        videoView.setOpenQuestion(true);
        videoView.setOpenSRT(false);
        videoView.setOpenPreload(true, 2);
        videoView.setOpenMarquee(false);
        videoView.setAutoContinue(true);
        videoView.setNeedGestureDetector(true);
        videoView.setSeekType(PolyvSeekType.SEEKTYPE_NORMAL);
        videoView.setLoadTimeoutSecond(false, 60);//加载超时时间，单位：秒。false：不开启。
        videoView.setBufferTimeoutSecond(false, 30);//缓冲超时时间，单位：秒。false：不开启。
        videoView.disableScreenCAP(this, false);//防录屏开关，true为开启，如果开启防录屏，投屏功能将不可用

        videoView.setOnPreparedListener(new IPolyvOnPreparedListener2() {
            @Override
            public void onPrepared() {
                mediaController.preparedView();
                progressView.setViewMaxValue(videoView.getDuration());
                // 没开预加载在这里开始弹幕
                // danmuFragment.start();
            }
        });


        videoView.setOnInfoListener(new IPolyvOnInfoListener2() {
            @Override
            public boolean onInfo(int what, int extra) {
                switch (what) {
                    case PolyvMediaInfoType.MEDIA_INFO_BUFFERING_START:
                        touchSpeedLayout.updateStatus(true);
                        break;
                    case PolyvMediaInfoType.MEDIA_INFO_BUFFERING_END:
                        touchSpeedLayout.updateStatus(false);
                        break;
                }

                return true;
            }
        });

        videoView.setOnPlayPauseListener(new IPolyvOnPlayPauseListener() {
            @Override
            public void onPause() {
                mediaController.updatePictureInPictureActions(R.drawable.polyv_btn_play_port, "pause", 1, 1);
            }

            @Override
            public void onPlay() {
                mediaController.updatePictureInPictureActions(R.drawable.polyv_btn_pause_port, "start", 2, 2);
            }

            @Override
            public void onCompletion() {
                mediaController.updatePictureInPictureActions(R.drawable.polyv_btn_play_port, "pause", 1, 1);
            }
        });


        videoView.setOnVideoTimeoutListener(new IPolyvOnVideoTimeoutListener() {
            @Override
            public void onBufferTimeout(int timeoutSecond, int times) {//在一个缓冲里，每超过设置的timeoutSecond都会回调一次，需开启该功能才会回调
                Toast.makeText(PolyvPlayerActivity.this, "视频加载速度缓慢，请切换到低清晰度的视频或调整网络", Toast.LENGTH_LONG).show();
            }
        });

        videoView.setOnVideoStatusListener(new IPolyvOnVideoStatusListener() {
            @Override
            public void onStatus(int status) {
                if (status < 60) {
                    Toast.makeText(PolyvPlayerActivity.this, "状态错误 " + status, Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, String.format("状态正常 %d", status));
                }
            }
        });

        videoView.setOnVideoPlayErrorListener(new IPolyvOnVideoPlayErrorListener2() {
            @Override
            public boolean onVideoPlayError(@PolyvPlayErrorReason.PlayErrorReason int playErrorReason) {
                playErrorView.show(playErrorReason, videoView);
                return true;
            }
        });

        //为了能更好的统一错误处理，这个错误回调合并到setOnVideoPlayErrorListener(IPolyvOnVideoPlayErrorListener2)中，对应的错误类型是PolyvPlayErrorReason.VIDEO_ERROR。
        //为了向后兼容，以前的程序不受影响，当设置了这个错误回调时，setOnVideoPlayErrorListener(IPolyvOnVideoPlayErrorListener2)错误回调不会被触发。
        //没有设置这个错误回调时，setOnVideoPlayErrorListener(IPolyvOnVideoPlayErrorListener2)错误回调才会触发。
//        videoView.setOnErrorListener(new IPolyvOnErrorListener2() {
//            @Override
//            public boolean onError() {
//                playErrorView.show(PolyvPlayErrorReason.VIDEO_ERROR, videoView);
//                return true;
//            }
//        });



        videoView.setOnAdvertisementEventListener(new IPolyvOnAdvertisementEventListener2() {
            @Override
            public void onShow(PolyvADMatterVO adMatter) {
                Log.i(TAG, "开始播放视频广告");
            }

            @Override
            public void onClick(PolyvADMatterVO adMatter) {
                if (!TextUtils.isEmpty(adMatter.getAddrUrl())) {
                    try {
                        new URL(adMatter.getAddrUrl());
                    } catch (MalformedURLException e1) {
                        Log.e(TAG, PolyvSDKUtil.getExceptionFullMessage(e1, -1));
                        return;
                    }

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(adMatter.getAddrUrl()));
                    startActivity(intent);
                }
            }
        });




        videoView.setOnVideoSRTPreparedListener(new IPolyvOnVideoSRTPreparedListener() {
            @Override
            public void onVideoSRTPrepared() {
                mediaController.preparedSRT(videoView);
            }
        });

        videoView.setOnVideoSRTListener(new IPolyvOnVideoSRTListener() {
            @Override
            public void onVideoSRT(@Nullable List<PolyvSRTItemVO> subTitleItems) {
                srtTextView.setText("");
                topSrtTextView.setText("");

                if (subTitleItems != null) {
                    for (PolyvSRTItemVO srtItemVO : subTitleItems) {
                        if (srtItemVO.isBottomCenterSubTitle()) {
                            srtTextView.setText(srtItemVO.getSubTitle());
                        } else if (srtItemVO.isTopCenterSubTitle()) {
                            topSrtTextView.setText(srtItemVO.getSubTitle());
                        }
                    }
                }

                srtTextView.setVisibility(View.VISIBLE);
                topSrtTextView.setVisibility(View.VISIBLE);
            }
        });

        videoView.setOnGestureLeftUpListener(new IPolyvOnGestureLeftUpListener() {

            @Override
            public void callback(boolean start, boolean end) {
                Log.d(TAG, String.format("LeftUp %b %b brightness %d", start, end, videoView.getBrightness(PolyvPlayerActivity.this)));
                if(mediaController.isLocked()){
                    return;
                }

                int brightness = videoView.getBrightness(PolyvPlayerActivity.this) + 5;
                if (brightness > 100) {
                    brightness = 100;
                }

                videoView.setBrightness(PolyvPlayerActivity.this, brightness);
                lightView.setViewLightValue(brightness, end);
            }
        });

        videoView.setOnGestureLeftDownListener(new IPolyvOnGestureLeftDownListener() {

            @Override
            public void callback(boolean start, boolean end) {
                Log.d(TAG, String.format("LeftDown %b %b brightness %d", start, end, videoView.getBrightness(PolyvPlayerActivity.this)));
                if(mediaController.isLocked()){
                    return;
                }
                int brightness = videoView.getBrightness(PolyvPlayerActivity.this) - 5;
                if (brightness < 0) {
                    brightness = 0;
                }

                videoView.setBrightness(PolyvPlayerActivity.this, brightness);
                lightView.setViewLightValue(brightness, end);
            }
        });

        videoView.setOnGestureRightUpListener(new IPolyvOnGestureRightUpListener() {

            @Override
            public void callback(boolean start, boolean end) {
                Log.d(TAG, String.format("RightUp %b %b volume %d", start, end, videoView.getVolume()));
                // 加减单位最小为10，否则无效果
                if(mediaController.isLocked()){
                    return;
                }
                int volume = videoView.getVolume() + 10;
                if (volume > 100) {
                    volume = 100;
                }

                videoView.setVolume(volume);
                volumeView.setViewVolumeValue(volume, end);
            }
        });

        videoView.setOnGestureRightDownListener(new IPolyvOnGestureRightDownListener() {

            @Override
            public void callback(boolean start, boolean end) {
                Log.d(TAG, String.format("RightDown %b %b volume %d", start, end, videoView.getVolume()));
                // 加减单位最小为10，否则无效果
                if (mediaController.isLocked()) {
                    return;
                }
                int volume = videoView.getVolume() - 10;
                if (volume < 0) {
                    volume = 0;
                }

                videoView.setVolume(volume);
                volumeView.setViewVolumeValue(volume, end);
            }
        });

        videoView.setOnGestureSwipeLeftListener(new IPolyvOnGestureSwipeLeftListener() {

            @Override
            public void callback(boolean start, int times, boolean end) {
                // 左滑事件
                Log.d(TAG, String.format("SwipeLeft %b %b", start, end));
                if(mediaController.isLocked()){
                    return;
                }
                mediaController.hideTickTips();
                if (fastForwardPos == 0) {
                    fastForwardPos = videoView.getCurrentPosition();
                }

                if (end) {
                    if (fastForwardPos < 0)
                        fastForwardPos = 0;
                    videoView.seekTo(fastForwardPos);
                    if (videoView.isCompletedState()) {
                        videoView.start();
                    }
                    fastForwardPos = 0;
                } else {
                    fastForwardPos -= 1000 * times;
                    if (fastForwardPos <= 0)
                        fastForwardPos = -1;
                }
                progressView.setViewProgressValue(fastForwardPos, videoView.getDuration(), end, false);
            }
        });

        videoView.setOnGestureSwipeRightListener(new IPolyvOnGestureSwipeRightListener() {

            @Override
            public void callback(boolean start, int times, boolean end) {
                // 右滑事件
                Log.d(TAG, String.format("SwipeRight %b %b", start, end));
                if(mediaController.isLocked()){
                    return;
                }
                mediaController.hideTickTips();
                if (fastForwardPos == 0) {
                    fastForwardPos = videoView.getCurrentPosition();
                }

                if (end) {
                    if (fastForwardPos > videoView.getDuration())
                        fastForwardPos = videoView.getDuration();
                    if (!videoView.isCompletedState()) {
                        videoView.seekTo(fastForwardPos);
                    } else if (videoView.isCompletedState() && fastForwardPos != videoView.getDuration()) {
                        videoView.seekTo(fastForwardPos);
                        videoView.start();
                    }
                    fastForwardPos = 0;
                } else {
                    fastForwardPos += 1000 * times;
                    if (fastForwardPos > videoView.getDuration())
                        fastForwardPos = videoView.getDuration();
                }
                progressView.setViewProgressValue(fastForwardPos, videoView.getDuration(), end, true);
            }
        });

        videoView.setOnGestureClickListener(new IPolyvOnGestureClickListener() {
            @Override
            public void callback(boolean start, boolean end) {
                if ((videoView.isInPlaybackState() || videoView.isExceptionCompleted()) && mediaController != null)
                    if (mediaController.isShowing())
                        mediaController.hide();
                    else
                        mediaController.show();
            }
        });

        videoView.setOnGestureDoubleClickListener(new IPolyvOnGestureDoubleClickListener() {
            @Override
            public void callback() {
                if ((videoView.isInPlaybackState() || videoView.isExceptionCompleted()) && mediaController != null && !mediaController.isLocked())
                    mediaController.playOrPause();
            }
        });

        videoView.setOnGestureLongTouchListener(new IPolyvOnGestureLongTouchListener() {
            @Override
            public void callback(boolean isTouchLeft, boolean start, boolean end) {
                if (start) {
                    beforeTouchSpeed = videoView.getSpeed();
                    if (beforeTouchSpeed < 2 && videoView.isPlaying() && !mediaController.isLocked()) {
                        videoView.setSpeed(2);
                        touchSpeedLayout.show();
                    }
                } else {
                    videoView.setSpeed(beforeTouchSpeed);
                    mediaController.initSpeedView((int) (beforeTouchSpeed * 10));
                    touchSpeedLayout.hide();
                }
            }
        });
        videoView.setOnCompletionListener(new IPolyvOnCompletionListener2() {
            @Override
            public void onCompletion() {
                //提交播放视频的个数
                uploadPlayVideo();
            }
        });

        flowPlayButton.setOnClickListener(flowButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkDetection.allowMobile();
                flowPlayLayout.setVisibility(View.GONE);
                play(vid, bitrate, true, isMustFromLocal);
            }
        });
        cancelFlowPlayButton.setOnClickListener(v -> {
            flowPlayLayout.setVisibility(View.GONE);
            videoView.start();
        });

        String customTeaserUrl="https://w.wallhaven.cc/full/13/wallhaven-13x79v.jpg";
        int customTeaserDuration=3;
        videoView.setCustomTeaser(customTeaserUrl,customTeaserDuration);
        firstStartView.setFinishClickListener(v -> finish());
        mediaController.setFinishClickListener(v -> finish());
        ll_collect.setOnClickListener(v -> {
            if (collectId!=-1){
                cancleCollectVideo();
            }else {
                collectVideo();
            }
        });
        ll_download.setOnClickListener(v -> {
            if (mDownloadState == 0) {
                loadVideo();
            } else if (mDownloadState == 1) {
                ToastUtil.showShort(MyApplication.context,"已缓存");
            } else {
                ToastUtil.showShort(MyApplication.context,"缓存中");
            }
        });

    }
    private void collectVideo() {
        Map<String,String> params = new HashMap<>();
        params.put("courseId",String.valueOf(courseId));
        params.put("videoId",String.valueOf(videoId));
        RetrofitFactory.getInstance().addFavoriteVideo(params, new BaseObserver<FavoriteStateResultBean.ResultBean>() {
            @Override
            protected void onSuccees(FavoriteStateResultBean.ResultBean resultBean) {
                    if (resultBean!=null){
                      collectId =   resultBean.getId();
                      setCollectView(true);
                    }
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) {

            }
        });

    }

    private void cancleCollectVideo() {
        RetrofitFactory.getInstance().deleteFavoriteVideo(String.valueOf(collectId), new BaseObserver<BooleanDataBean.ResultBean>() {
            @Override
            protected void onSuccees(BooleanDataBean.ResultBean resultBean) {
                collectId = -1;
                setCollectView(false);
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) {

            }
        });

    }
    private void getCollectState() {
        RetrofitFactory.getInstance().checkVideoFavoriteStatus(courseId,videoId,
                CareerSPHelper.getUserId(),new BaseObserver<FavoriteStateResultBean.ResultBean>() {
            @Override
            protected void onSuccees(FavoriteStateResultBean.ResultBean resultBean) {
                if (resultBean!=null){
                    collectId = resultBean.getId();
                    setCollectView(true);
                }else {
                    setCollectView(false);
                }

            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) {

            }
        });

    }

    private void setCollectView(boolean collectState) {
        if (collectState){
           tv_collect.setText("已收藏");
           iv_collect.setImageResource(R.drawable.video_collected);
        }else {
            tv_collect.setText("收藏");
            iv_collect.setImageResource(R.drawable.video_collect);
        }

    }

    private  int   mDownloadState = 0;
    @SuppressLint("CheckResult")
    private void setLoadStateView() {
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            e.onNext(getDownloadState());
            e.onComplete();
        }).subscribeOn(Schedulers.io())// 指定 Observable（被观察者 事件） 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 observer（观察者） 的回调发生在主线程
                .subscribe(downloadState -> {
                    mDownloadState = downloadState;
                    if (downloadState == 0) {
                        tv_download_status.setText("缓存");
                    } else if (downloadState == 1) {
                        tv_download_status.setText("已缓存");
                    } else {
                        tv_download_status.setText("缓存中");
                        setDownloadListener();
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void setDownloadListener() {
        Observable.create((ObservableOnSubscribe<PolyvDownloadInfo>) e -> {
            LinkedList<PolyvDownloadInfo> downloadInfoById = PolyvDownloadSQLiteHelper.getInstance(getApplicationContext()).getDownloadInfoById(vid);
            if (downloadInfoById.size() > 0) {
                e.onNext(downloadInfoById.get(0));
            }
            e.onComplete();
        }).subscribeOn(Schedulers.io())// 指定 Observable（被观察者 事件） 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 observer（观察者） 的回调发生在主线程
                .subscribe(polyvDownloadInfo ->
                        PolyvDownloaderManager.getPolyvDownloader(vid, bitrate, fileType).setPolyvDownloadProressListener2(new MyDownloadListener(getApplicationContext(), polyvDownloadInfo))
                );
    }

    /**
     * 初始化视频播放错误提示界面
     */
    private void initPlayErrorView() {
        playErrorView.setRetryPlayListener(() -> play(vid, bitrate, true, isMustFromLocal));

        playErrorView.setShowRouteViewListener(() -> playRouteView.show(videoView));
    }

    /**
     * 初始化线路切换界面
     */
    private void initRouteView() {
        playRouteView.setChangeRouteListener(new PolyvPlayerPlayRouteView.IChangeRouteListener() {
            @Override
            public void onChange(int route) {
                playErrorView.hide();
                videoView.changeRoute(route);
            }
        });
    }

    /**
     * 播放视频
     *
     * @param vid             视频id
     * @param bitrate         码率（清晰度）
     * @param startNow        是否现在开始播放视频
     * @param isMustFromLocal 是否必须从本地（本地缓存的视频）播放
     */
    public void play(final String vid, final int bitrate, boolean startNow, final boolean isMustFromLocal) {
        this.vid = vid;
        this.bitrate = bitrate;
        this.isMustFromLocal = isMustFromLocal;
        if (TextUtils.isEmpty(vid)) return;

        if (networkDetection.isMobileType() && !networkDetection.isAllowMobile()) {
            if (PolyvDownloader.FILE_VIDEO == fileType) {
                if (bitrate != 0 && !PolyvVideoUtil.validateLocalVideo(vid, bitrate).hasLocalVideo() ||
                        (bitrate == 0 && !PolyvVideoUtil.validateLocalVideo(vid).hasLocalVideo())) {
                    flowPlayButton.setOnClickListener(flowButtonOnClickListener);
                    flowPlayLayout.setVisibility(View.VISIBLE);
                    cancelFlowPlayButton.setVisibility(View.GONE);
                    return;
                }
            } else {
                if (bitrate != 0 && PolyvVideoUtil.validateMP3Audio(vid, bitrate) == null && !PolyvVideoUtil.validateLocalVideo(vid, bitrate).hasLocalVideo() ||
                        (bitrate == 0 && PolyvVideoUtil.validateMP3Audio(vid).size() == 0) && !PolyvVideoUtil.validateLocalVideo(vid).hasLocalVideo()) {
                    flowPlayButton.setOnClickListener(flowButtonOnClickListener);
                    flowPlayLayout.setVisibility(View.VISIBLE);
                    cancelFlowPlayButton.setVisibility(View.GONE);
                    return;
                }
            }
        }



        if (videoView.isDisableScreenCAP()) {
            iv_screencast_search.setVisibility(View.GONE);
            iv_screencast_search_land.setVisibility(View.GONE);
        }


        videoView.release();
        srtTextView.setVisibility(View.GONE);
        topSrtTextView.setVisibility(View.GONE);
        mediaController.hide();
        mediaController.resetView();
        loadingLayout.setVisibility(View.GONE);
        auxiliaryVideoView.hide();
        auxiliaryLoadingProgress.setVisibility(View.GONE);
        advertCountDown.setVisibility(View.GONE);
        firstStartView.hide();
        progressView.resetMaxValue();

        if (PolyvDownloader.FILE_VIDEO == fileType) {
            videoView.setPriorityMode(PolyvVideoVO.MODE_VIDEO);
        } else if (PolyvDownloader.FILE_AUDIO == fileType) {
            videoView.setPriorityMode(PolyvVideoVO.MODE_AUDIO);
        }
        if (startNow) {
            //调用setVid方法视频会自动播放
            videoView.setVid(vid, bitrate, isMustFromLocal);
        } else {
            //视频不播放，先显示一张缩略图
            firstStartView.setCallback(new PolyvPlayerPreviewView.Callback() {

                @Override
                public void onClickStart() {
                    //在播放视频时设置viewerId方法使用示例
                    videoView.setVidWithViewerId(vid, bitrate, isMustFromLocal,"123");
                }
            });

            firstStartView.show(vid);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isInPipMode()) {
            if (!isBackgroundPlay) {
                //回来后继续播放
                if (isPlay) {
                    videoView.onActivityResume();
                }
            } else if (playBinder != null) {
                playBinder.stop();
            }
        }
        mediaController.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaController.pause();
        if (!isInPipMode()) {
            if (!isBackgroundPlay || isInPictureInPictureMode) {
                //弹出去暂停
                isPlay = videoView.onActivityStop();
            } else if (playBinder != null && !isOnBackKeyPressed) {
                playBinder.start("正在后台播放视频", "点击进入播放页面", R.mipmap.ic_launcher);
            }
        }
        PolyvScreenUtils.removePIPSingleInstanceTask(this, PolyvPlayerActivity.class.getName(), isInPictureInPictureMode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        uploadWatchTime();
        videoView.destroy();
        firstStartView.hide();
        mediaController.disable();
        networkDetection.destroy();
        if (playBinder != null) {
            playBinder.stop();
        }
        unbindService(playConnection);
    }

    private void uploadWatchTime() {
        // getWatchTimeDuration() // 用户观看时间，假如用2倍速播放到视频的第60s，该方法返回30。
        // getVideoContentPlayedTime() // 视频内容播放时间，假如用2倍速播放到视频的第60s，该方法返回60
        if (videoView.getWatchTimeDuration()==0){
            return;
        }
        int duration = videoView.getWatchTimeDuration();
        Map<String,String> params = new HashMap<>();
        params.put("duration",String.valueOf(duration));
        params.put("videoId", String.valueOf(videoId));
        params.put("courseId", String.valueOf(courseId));
        RetrofitFactory.getInstance().addVideoPlayRecord(params, new BaseObserver<BooleanDataBean.ResultBean>() {
            @Override
            protected void onSuccees(BooleanDataBean.ResultBean resultBean) {
                LogUtil.e("success","-----duration");
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) {

            }
        });
    }

    private void uploadPlayVideo() {
        int duration = videoView.getWatchTimeDuration();
        Map<String,String> params = new HashMap<>();
        params.put("videoId", String.valueOf(videoId));
        params.put("courseId", String.valueOf(courseId));
        params.put("duration",String.valueOf(duration));
        RetrofitFactory.getInstance().addVideoPlayRecord(params, new BaseObserver<BooleanDataBean.ResultBean>() {
            @Override
            protected void onSuccees(BooleanDataBean.ResultBean resultBean) {
                LogUtil.e("success","-----videoId");
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) {

            }
        });
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        this.isInPictureInPictureMode = isInPictureInPictureMode;
        if (isInPictureInPictureMode) {
            // Starts receiving events from action items in PiP mode.
            pipReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (intent == null
                            || !"media_control".equals(intent.getAction())
                            || !(videoView.isInPlaybackState() || videoView.isExceptionCompleted())) {
                        return;
                    }

                    // This is where we are called back from Picture-in-Picture action
                    // items.
                    final int controlType = intent.getIntExtra("control_type", 0);
                    switch (controlType) {
                        case 1:
                            videoView.start();
                            break;
                        case 2:
                            videoView.pause();
                            break;
                    }
                }
            };
            registerReceiver(pipReceiver, new IntentFilter("media_control"));

            if (playBinder != null) {
                playBinder.start("正在小窗播放视频", "点击进入播放页面", R.mipmap.ic_launcher);
            }
        } else {
            // We are out of PiP mode. We can stop receiving events from it.
            if (pipReceiver != null) {
                unregisterReceiver(pipReceiver);
                pipReceiver = null;
            }

            if (playBinder != null) {
                playBinder.stop();
            }
        }
    }

    private boolean isInPipMode() {
        if (Build.VERSION.SDK_INT >= 26) {
            return isInPictureInPictureMode();
        }
        return false;
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private boolean hideViewOnTouchOutside(MotionEvent ev, View view) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN && view.getVisibility() == View.VISIBLE) {
            int[] location = new int[2];
            view.getLocationInWindow(location);
            if (ev.getX() < location[0]) {
                    view.setVisibility(View.GONE);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mediaController != null && mediaController.isLocked()) {
                return true;
            }
            if (mediaController != null && mediaController.isFullScreen()) {
                mediaController.changeToSmallScreen();
                return true;
            }
            isOnBackKeyPressed = true;
        }

        return super.onKeyDown(keyCode, event);
    }


    public static Intent newIntent(Context context,int courseId,String videoName, String vid, boolean startNow, boolean isMustFromLocal, int fileType) {
        Intent intent = new Intent(context, PolyvPlayerActivity.class);
        intent.putExtra(V_ID, vid);
        intent.putExtra(COURSE_ID, courseId);
        intent.putExtra("videoName", videoName);
        intent.putExtra("startNow", startNow);
        intent.putExtra("isMustFromLocal", isMustFromLocal);
        intent.putExtra("fileType", fileType);
        return intent;
    }
    public static Intent newIntent(Context context,int courseId,int videoId,String videoName, String vid, boolean startNow, boolean isMustFromLocal, int fileType) {
        Intent intent = new Intent(context, PolyvPlayerActivity.class);
        intent.putExtra(V_ID, vid);
        intent.putExtra(COURSE_ID, courseId);
        intent.putExtra("videoId", videoId);
        intent.putExtra("videoName", videoName);
        intent.putExtra("startNow", startNow);
        intent.putExtra("isMustFromLocal", isMustFromLocal);
        intent.putExtra("fileType", fileType);
        return intent;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.ll_jump_test){
            if (knowledgePointId!=0){
                Intent  intent = new Intent(PolyvPlayerActivity.this, KnowledgeTestActivity.class);
                intent.putExtra("kpId", knowledgePointId);
                startActivity(intent);
            }else {
                ToastUtil.showShort(getApplicationContext(),"暂无练习题");
            }
        }
    }

    /**
     * 播放模式
     *
     * @author TanQu
     */
    public enum PlayMode {
        /**
         * 横屏
         */
        landScape(3),
        /**
         * 竖屏
         */
        portrait(4);

        private final int code;

        private PlayMode(int code) {
            this.code = code;
        }

        /**
         * 取得类型对应的code
         *
         * @return
         */
        public int getCode() {
            return code;
        }

        public static PlayMode getPlayMode(int code) {
            switch (code) {
                case 3:
                    return landScape;
                case 4:
                    return portrait;
            }

            return null;
        }
    }
    private static PolyvDownloadSQLiteHelper downloadSQLiteHelper;

    private void loadVideo(){
        downloadSQLiteHelper = PolyvDownloadSQLiteHelper.getInstance(getApplicationContext());
        LoadVideoInfoTask loadVideoInfoTask = new LoadVideoInfoTask(new ILoadVideoInfoListener() {

            @Override
            public void onloaded(final PolyvVideoVO v) {
                if (v == null) {
                    Toast.makeText(getApplicationContext(), "获取下载信息失败，请重试", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 码率数
                String[] items = PolyvBitRate.getBitRateNameArray(v.getDfNum());
                final AlertDialog.Builder selectDialog = new AlertDialog.Builder(PolyvPlayerActivity.this).setTitle("请选择下载码率").setSingleChoiceItems(items, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int bitrate = which + 1;

                                final PolyvDownloadInfo downloadInfo = new PolyvDownloadInfo(
                                        vid,
                                        v.getDuration(),
                                        v.getFileSizeMatchVideoType(bitrate,PolyvDownloader.FILE_VIDEO),
                                        bitrate,
                                        videoName,
                                        courseId,
                                        videoCover);
                                //设置下载的文件类型，视频/音频，可以用v.hasAudioPath()判断是否有音频
                                downloadInfo.setFileType(PolyvDownloader.FILE_VIDEO);
                                Log.i("videoAdapter", downloadInfo.toString());
                                if (downloadSQLiteHelper != null && !downloadSQLiteHelper.isAdd(downloadInfo)) {
                                    downloadSQLiteHelper.insert(downloadInfo);
                                    PolyvDownloader polyvDownloader = PolyvDownloaderManager.getPolyvDownloader(vid, bitrate, downloadInfo.getFileType());
                                    polyvDownloader.setPolyvDownloadProressListener2(new MyDownloadListener(getApplicationContext(), downloadInfo));
                                    polyvDownloader.start(getApplicationContext());
                                    mDownloadState = 2;
                                    tv_download_status.setText("缓存中");
                                } else {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "下载任务已经增加到队列", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                dialog.dismiss();
                            }
                        });

                selectDialog.show().setCanceledOnTouchOutside(true);
            }
        });

        loadVideoInfoTask.execute(vid);
    }
    private  class MyDownloadListener implements IPolyvDownloaderProgressListener2 {
        private long total;
        private WeakReference<Context> contextWeakReference;
        private PolyvDownloadInfo downloadInfo;

        MyDownloadListener(Context context, PolyvDownloadInfo downloadInfo) {
            this.contextWeakReference = new WeakReference<>(context);
            this.downloadInfo = downloadInfo;
        }

        @Override
        public void onDownloadSuccess(int bitrate) {
            if (total == 0)
                total = 1;

            downloadInfo.setBitrate(bitrate);
            downloadSQLiteHelper.update(downloadInfo, total, total);
            mDownloadState = 1;
            tv_download_status.setText("已缓存");
        }

        @Override
        public void onDownloadFail(@NonNull PolyvDownloaderErrorReason errorReason) {
            String errorMsg = PolyvErrorMessageUtils.getDownloaderErrorMessage(errorReason.getType(), downloadInfo.getFileType());
            errorMsg += "(error code " + errorReason.getType().getCode() + ")";
            Log.e(TAG, errorMsg);
            if (contextWeakReference.get() != null)
                Toast.makeText(contextWeakReference.get(), errorMsg, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onDownload(long current, long total) {
            this.total = total;
        }
    }
    private static class LoadVideoInfoTask extends AsyncTask<String, Void, PolyvVideoVO> {

        private final ILoadVideoInfoListener l;

        LoadVideoInfoTask(ILoadVideoInfoListener l) {
            this.l = l;
        }

        @Override
        protected PolyvVideoVO doInBackground(String... params) {
            try {
                return PolyvSDKUtil.loadVideoJSON2Video(params[0]);
            } catch (Exception e) {
                Log.e(TAG, PolyvSDKUtil.getExceptionFullMessage(e, -1));
                return null;
            }
        }

        @Override
        protected void onPostExecute(PolyvVideoVO v) {
            super.onPostExecute(v);
            l.onloaded(v);
        }
    }
    private interface ILoadVideoInfoListener {
        void onloaded(PolyvVideoVO v);
    }

    /**
     *0  未下载  1 下载  2 下载中
     * @return
     */
    private int getDownloadState() {
        int isFinished = 0;
        List<PolyvDownloadInfo> downloadInfos = PolyvDownloadSQLiteHelper.getInstance(getApplicationContext()).getDownloadInfoById(vid);
        if (downloadInfos == null) {
            return 0;
        }
        for (PolyvDownloadInfo downloadInfo : downloadInfos) {
            long percent = downloadInfo.getPercent();
            long total = downloadInfo.getTotal();
            int progress = 0;
            if (total != 0) {
                progress = (int) (percent * 100 / total);
            }
            if (progress == 100) {
                isFinished = 1;
            } else {
                isFinished = 2;
            }
        }
        return isFinished;
    }
}
