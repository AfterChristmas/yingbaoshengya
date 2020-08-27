package com.yingbao.career.http;


import com.yingbao.career.common.CommonConstant;
import com.yingbao.career.http.resultbean.AddErrorFavorResultBean;
import com.yingbao.career.http.resultbean.AddPrintRecordResult;
import com.yingbao.career.http.resultbean.BannerResultBean;
import com.yingbao.career.http.resultbean.BooleanDataBean;
import com.yingbao.career.http.resultbean.CollectListResultBean;
import com.yingbao.career.http.resultbean.CourseListResultBean;
import com.yingbao.career.http.resultbean.ErrorDetailResultBean;
import com.yingbao.career.http.resultbean.FavoriteListResult;
import com.yingbao.career.http.resultbean.FavoriteStateResultBean;
import com.yingbao.career.http.resultbean.HasCollectSubjectResultBean;
import com.yingbao.career.http.resultbean.KnowPointVideoList;
import com.yingbao.career.http.resultbean.LocationResultBean;
import com.yingbao.career.http.resultbean.LoginResultBean;
import com.yingbao.career.http.resultbean.OssInfoResultBean;
import com.yingbao.career.http.resultbean.PrintRecordListResult;
import com.yingbao.career.http.resultbean.QuestionInfoResult;
import com.yingbao.career.http.resultbean.QuestionRecordCount;
import com.yingbao.career.http.resultbean.RandomQuestionBean;
import com.yingbao.career.http.resultbean.RecommendCourseListBean;
import com.yingbao.career.http.resultbean.StudySchoolResutlBean;
import com.yingbao.career.http.resultbean.SubjectsResultBean;
import com.yingbao.career.http.resultbean.TestKnowPointResult;
import com.yingbao.career.http.resultbean.UserDetailResultBean;
import com.yingbao.career.http.resultbean.VideoKnowPointResult;
import com.yingbao.career.http.resultbean.VideoListResultBean;
import com.yingbao.career.http.resultbean.VideoRecordCount;
import com.yingbao.career.http.resultbean.VideoRecordTimeLine;
import com.yingbao.career.http.resultbean.WrongRecordListBean;
import com.yingbao.career.utils.CareerSPHelper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description:
 * @Date: 2019/10/23 19:33
 * @Auther: wanyan
 */
public class RetrofitFactory {
    private final ApiService mApiService;
    private static RetrofitFactory mRetrofitFactory;

    private RetrofitFactory() {
        //创建日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//设定日志级别
        //创建OkHttpClient
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)//添加拦截器
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(chain -> {
                    Request oldRequest = chain.request();
                    HttpUrl.Builder urlBuilder = oldRequest.url()
                            .newBuilder()
                            .scheme(oldRequest.url().scheme())
                            .host(oldRequest.url().host());
                    Request newRequest = oldRequest.newBuilder()
                            .method(oldRequest.method(), oldRequest.body())
                            .url(urlBuilder.build())
                            .build();
                    Request.Builder builder = newRequest.newBuilder();
                    builder.addHeader("Accept", "application/json");
                    builder.addHeader("Content-Type", "application/json;charset=UTF-8");
                    builder.addHeader("token", CareerSPHelper.getToken());
                    builder.addHeader("userType", CommonConstant.APP_TYPE);
                    return chain.proceed(builder.build());
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public ApiService API() {
        return mApiService;
    }

    public static RetrofitFactory getInstance() {
        if (mRetrofitFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofitFactory == null) {
                    mRetrofitFactory = new RetrofitFactory();
                }
            }
        }
        return mRetrofitFactory;
    }

    public ObservableTransformer threadTransformer() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public void addPrintRecord(RequestBody requestBody, BaseObserver<AddPrintRecordResult.ResultBean> scheduler) {
        API().addPrintRecord(requestBody)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getPrintList(Map<String, String> map, BaseObserver<PrintRecordListResult.ResultBean> scheduler) {
        API().getPrintList(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getSubjects(Map<String, String> map, BaseObserver<SubjectsResultBean.ResultBean> scheduler) {
        API().getSubjects(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getVideoKnowPoint(Map<String, String> map, BaseObserver<List<VideoKnowPointResult.ResultBean>> scheduler) {
        API().getVideoKnowPoint(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getTestKnowPoint(Map<String, String> map, BaseObserver<List<TestKnowPointResult.ResultBean>> scheduler) {
        API().getTestKnowPoint(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void addErrorFavorite(RequestBody requestBody, BaseObserver<AddErrorFavorResultBean.ResultBean> scheduler) {
        API().addErrorFavorite(requestBody)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void deleteErrorFavorite(String id, BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().deleteErrorFavorite(id)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void deleteBatchErrorFavorite(String id, BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().deleteBatchErrorFavorite(id)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void moveBatchErrorFavorite(Map<String, String> map, BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().moveBatchErrorFavorite(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getErrorFavorite(String id, BaseObserver<ErrorDetailResultBean.ResultBean> scheduler) {
        API().getErrorFavorite(id)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getHasCollectSubjects(Map<String, String> map, BaseObserver<List<HasCollectSubjectResultBean.ResultBean>> scheduler) {
        API().getHasCollectSubjects(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getCollectListBySubject(Map<String, String> map, BaseObserver<CollectListResultBean.ResultBean> scheduler) {
        API().getCollectListBySubject(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void editErrorFavorite(RequestBody requestBody, BaseObserver<AddErrorFavorResultBean.ResultBean> scheduler) {
        API().editErrorFavorite(requestBody)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void login(String userName, String password, BaseObserver<LoginResultBean.ResultBean> scheduler) {
        API().login(userName,password)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getCodeForPass(String phone, BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().getCodeForPass(phone)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getCodeForBind(String phone, BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().getCodeForBind(phone)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void bandPhone(String phone, String code , BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().bandPhone(phone,code)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void chekCode(String phone, String code , BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().chekCode(phone,code)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void passwordReset(String phone, String code, String password , BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().passwordReset(phone,code,password)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void completeInfo(Map<String, String> map, BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().completeInfo(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getLocationByParentId(String parentId, BaseObserver<List<LocationResultBean.ResultBean>> scheduler) {
        API().getLocationByParentId(parentId)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getSchoolListByProvinceCityAreaId(String areaId, String cityId, BaseObserver<List<StudySchoolResutlBean.ResultBean>> scheduler) {
        API().getSchoolListByProvinceCityAreaId(areaId, cityId)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getUserInfo(int userId, BaseObserver<UserDetailResultBean.ResultBean> scheduler) {
        API().getUserInfo(userId)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getOssStsSignInfo(BaseObserver<OssInfoResultBean.OssTempSecretBean> scheduler) {
        API().getOssStsSignInfo()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void userHeadUpload(String avatarOssName ,BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().userHeadUpload(avatarOssName)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }

    public void addVideoPlayRecord(RequestBody requestBody, BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().addVideoPlayRecord(requestBody)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getCourseList(Map<String, String> map,  BaseObserver<CourseListResultBean.ResultBean> scheduler) {
        API().getCourseList(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getVideoList(String courseId, BaseObserver<VideoListResultBean.ResultBean> scheduler) {
        API().getVideoList(courseId)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getVideoFavoriteList(Map<String, String> map,  BaseObserver<FavoriteListResult.ResultBean> scheduler) {
        API().getVideoFavoriteList(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }

    public void addFavoriteVideo(Map<String,String> map , BaseObserver<FavoriteStateResultBean.ResultBean> scheduler) {
        API().addFavoriteVideo(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void checkVideoFavoriteStatus(int courseId,int videoId, int userId, BaseObserver<FavoriteStateResultBean.ResultBean> scheduler) {
        API().checkVideoFavoriteStatus(courseId,videoId, userId)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }

    public void deleteFavoriteVideo(String id, BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().deleteFavoriteCourse(id)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void queryBanners(BaseObserver<List<BannerResultBean.ResultBean>> scheduler) {
        API().queryBanners()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void addVideoPlayRecord(Map<String,String> map , BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().addVideoPlayRecord(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getRecommendCourseList(Map<String,String> map , BaseObserver<List<RecommendCourseListBean.ResultBean>> scheduler) {
        API().getRecommendCourseList(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void findUserVideoRecord(BaseObserver<VideoRecordCount.ResultBean> scheduler) {
        API().findUserVideoRecord()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void findUserQuestionRecord(Map<String,String> map , BaseObserver<QuestionRecordCount.ResultBean> scheduler) {
        API().findUserQuestionRecord(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getKnowPointVideoList(Map<String,String> map , BaseObserver<KnowPointVideoList.ResultBean> scheduler) {
        API().getKnowPointVideoList(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getVideoRecordTimeLines(Map<String,String> map , BaseObserver<List<VideoRecordTimeLine.ResultBean>> scheduler) {
        API().getVideoRecordTimeLines(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getRandomQuestion(Map<String,String> map , BaseObserver<List<RandomQuestionBean.ResultBean>> scheduler) {
        API().getRandomQuestion(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getKnowpointQuestion(Map<String,String> map , BaseObserver<List<RandomQuestionBean.ResultBean>> scheduler) {
        API().getKnowpointQuestion(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getQuestionInfo(Map<String,String> map , BaseObserver<List<QuestionInfoResult.ResultBean>> scheduler) {
        API().getQuestionInfo(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void addQuestionRecord(Map<String,String> map , BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().addQuestionRecord(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void deleteWrongRecord(Map<String,String> map , BaseObserver<BooleanDataBean.ResultBean> scheduler) {
        API().deleteWrongRecord(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getHasWrongSubjects( BaseObserver<List<Integer>> scheduler) {
        API().getHasErrorSubjects()
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
    public void getWrongRecordList(Map<String,String> map , BaseObserver<List<WrongRecordListBean.ResultBean>> scheduler) {
        API().getWrongRecordList(map)
                .compose(threadTransformer())
                .subscribe(scheduler);
    }
}
