package com.yingbao.career.http;

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

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @Description:
 * @Date: 2019/10/23 19:28
 * @Auther: wanyan
 */
public interface ApiService {

    String BASE_URL =  "http://api.shipin211.com/";

    //网络请求时长
    int HTTP_TIME =5000;
    //http://api.gkbbapp.com/  http://api.gkbbapp.com/api/OtherApi/GetHomeTabInfo

    /***
     * 添加打印记录
     * @return
     */
    @POST("/edu/eduPrintRecord/add")
    Observable<BaseResponse<AddPrintRecordResult.ResultBean>> addPrintRecord(@Body RequestBody requestBody);
    /***
     * 打印历史列表
     * @return
     */
    @GET("/edu/eduPrintRecord/list")
    Observable<BaseResponse<PrintRecordListResult.ResultBean>> getPrintList(@QueryMap Map<String, String> map);
    /***
     * 获取知识点的科目列表
     * @return
     */
    @GET("/edu/eduSubject/list")
    Observable<BaseResponse<SubjectsResultBean.ResultBean>> getSubjects(@QueryMap Map<String, String> map);
    /***
     * 根据父级id获取知识点  视频的知识点
     * @return
     */
    @GET("/edu/KnowledgePoint/lists")
    Observable<BaseResponse<List<VideoKnowPointResult.ResultBean>>> getVideoKnowPoint(@QueryMap Map<String, String> map);
    /***
     * 根据父级id获取知识点  练习的知识点
     * @return
     */
    @GET("/edu/newKnowledgePoint/lists")
    Observable<BaseResponse<List<TestKnowPointResult.ResultBean>>> getTestKnowPoint(@QueryMap Map<String, String> map);

    /***
     * 添加收藏到错题
     * @return
     */
    @POST("/edu/eduFavorite/add")
    Observable<BaseResponse<AddErrorFavorResultBean.ResultBean>> addErrorFavorite(@Body RequestBody requestBody);
    /***
     * 删除收藏到错题
     * @return
     */
    @DELETE("/edu/eduFavorite/delete")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> deleteErrorFavorite(@Query("id") String id);
    /***
     * 删除收藏到错题
     * @return
     */
    @GET("/edu/eduFavorite/queryById")
    Observable<BaseResponse<ErrorDetailResultBean.ResultBean>> getErrorFavorite(@Query("id") String id);

    /***
     * 获取科目列表
     * @return
     */
    @GET("/edu/eduSubject/hasFavoriteList")
    Observable<BaseResponse<List<HasCollectSubjectResultBean.ResultBean>>> getHasCollectSubjects(@QueryMap Map<String, String> map);

    /***
     * 获取收藏的列表
     * @return
     */
    @GET("/edu/eduFavorite/listBySubject")
    Observable<BaseResponse<CollectListResultBean.ResultBean>> getCollectListBySubject(@QueryMap Map<String, String> map);
    /***
     * 批量删除收藏的错题
     * @return
     */
    @DELETE("/edu/eduFavorite/deleteBatch")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> deleteBatchErrorFavorite(@Query("ids") String ids);
    /***
     * 移动收藏的错题
     * @return
     */
    @GET("/edu/eduFavorite/moveBatchToSubject")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> moveBatchErrorFavorite(@QueryMap Map<String, String> map);
    /***
     * 修改收藏的错题
     * @return
     */
    @PUT("/edu/eduFavorite/edit")
    Observable<BaseResponse<AddErrorFavorResultBean.ResultBean>> editErrorFavorite(@Body RequestBody requestBody);

    @FormUrlEncoded
    @POST("/api/login")
    Observable<BaseResponse<LoginResultBean.ResultBean>> login(@Field("userName") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/getCodeForPass")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> getCodeForPass(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("/api/getCodeForBind")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> getCodeForBind(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("/api/bandPhone")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> bandPhone(@Field("phone") String phone, @Field("code") String code);

    @FormUrlEncoded
    @POST("/api/chekCode")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> chekCode(@Field("phone") String phone, @Field("code") String code);

    @FormUrlEncoded
    @POST("/api/passwordReset")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> passwordReset(@Field("phone") String phone, @Field("code") String code, @Field("password") String password);

    @POST("/api/completeInfo")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> completeInfo(@QueryMap Map<String, String> params);

    @GET("/api/provinceCityArea/getByParentId")
    Observable<BaseResponse<List<LocationResultBean.ResultBean>>> getLocationByParentId(@Query("parentId") String parentId);


    @GET("/api/getSchoolListByProvinceCityAreaId")
    Observable<BaseResponse<List<StudySchoolResutlBean.ResultBean>>> getSchoolListByProvinceCityAreaId(@Query("areaId") String areaId, @Query("cityId") String cityId);
    @FormUrlEncoded
    @POST("/api/getUserInfo")
    Observable<BaseResponse<UserDetailResultBean.ResultBean> >getUserInfo(@Field("userId") int userId);
    @GET("/api/getOssStsSignInfo")
    Observable<BaseResponse<OssInfoResultBean.OssTempSecretBean>> getOssStsSignInfo();
    @FormUrlEncoded
    @POST("/api/userHeadUpload")
    Observable<BaseResponse<BooleanDataBean.ResultBean> > userHeadUpload(@Field("avatarOssName") String avatarOssName);

    /***
     * 添加视频播放记录
     * @return
     */
    @POST("/edu/userVideoRecord/add")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> addVideoPlayRecord(@Body RequestBody requestBody);

    //通过条件查询课程信息
    @GET("/edu/course/lists")
    Observable<BaseResponse<CourseListResultBean.ResultBean>> getCourseList(@QueryMap Map<String, String> map);

    //通过courseId查询视频列表
    @GET("/edu/course/videoList")
    Observable<BaseResponse<VideoListResultBean.ResultBean>> getVideoList(@Query("courseId") String courseId);

    //通过knowledgePointId查询视频列表
    @GET("/edu/KnowledgePoint/videoList")
    Observable<BaseResponse<KnowPointVideoList.ResultBean>> getKnowPointVideoList(@QueryMap Map<String, String> map);

    //获取收藏的课程列表
    @GET("/edu/userVideoFavorite/lists")
    Observable<BaseResponse<FavoriteListResult.ResultBean>> getVideoFavoriteList(@QueryMap Map<String, String> map);

    //yb_user_course_favorite-添加
    @POST("/edu/userVideoFavorite/uploadRecord")
    Observable<BaseResponse<FavoriteStateResultBean.ResultBean> > addFavoriteVideo(@QueryMap Map<String, String> map);
    //yb_user_course_favorite-通过id删除

    @DELETE("/edu/userVideoFavorite/delete")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> deleteFavoriteCourse(@Query("id") String id);
    //yb_user_course_favorite-通过id删除

    @GET("/edu/userVideoFavorite/checkStatus")
    Observable<BaseResponse<FavoriteStateResultBean.ResultBean>> checkVideoFavoriteStatus(@Query("courseId") int courseId,@Query("videoId") int videoId, @Query("userId") int userId);
    @GET("/edu/bannerInfo/queryBanners")
    Observable<BaseResponse<List<BannerResultBean.ResultBean>>> queryBanners();

    //提交用户的视频记录
    @POST("/edu/userVideoRecord/playTimes")
    Observable<BaseResponse<BooleanDataBean.ResultBean> > addVideoPlayRecord(@QueryMap Map<String, String> map);

    //获取推荐视频的列表
    @GET("/edu/course/randomLists")
    Observable<BaseResponse<List<RecommendCourseListBean.ResultBean>> > getRecommendCourseList(@QueryMap Map<String, String> map);
    //获取推荐视频的列表
    @GET("/edu/userVideoRecord/lists")
    Observable<BaseResponse<VideoRecordCount.ResultBean> > findUserVideoRecord();

    //获取推荐视频的列表
    @GET("/edu/userVideoRecord/timeLines")
    Observable<BaseResponse<List<VideoRecordTimeLine.ResultBean>> > getVideoRecordTimeLines(@QueryMap Map<String, String> map);

    //获取随机的题目
    @GET("/edu/question/random")
    Observable<BaseResponse<List<RandomQuestionBean.ResultBean>> > getRandomQuestion(@QueryMap Map<String, String> map);
    //获取知识点题目
    @GET("/edu/question/knowledgePointId")
    Observable<BaseResponse<List<RandomQuestionBean.ResultBean>> > getKnowpointQuestion(@QueryMap Map<String, String> map);
    //获取题目信息
    @GET("/edu/questionDetail/infos")
    Observable<BaseResponse<List<QuestionInfoResult.ResultBean>> > getQuestionInfo(@QueryMap Map<String, String> map);
    //上报答题记录
    @POST("/edu/userQuestionRecord/addRecord")
    Observable<BaseResponse<BooleanDataBean.ResultBean> > addQuestionRecord(@QueryMap Map<String, String> map);

    @POST("/edu/userQuestionRecord/wrongSubjectList")
    Observable<BaseResponse<List<Integer>>> getHasErrorSubjects();

    @GET("/edu/userQuestionRecord/wrongRecordList")
    Observable<BaseResponse<List<WrongRecordListBean.ResultBean>>> getWrongRecordList(@QueryMap Map<String, String> map);

    @DELETE("/edu/userQuestionRecord/delete")
    Observable<BaseResponse<BooleanDataBean.ResultBean>> deleteWrongRecord(@QueryMap Map<String, String> map);

    @GET("/edu/userQuestionRecord/statistics")
    Observable<BaseResponse<QuestionRecordCount.ResultBean>> findUserQuestionRecord(@QueryMap Map<String, String> map);
}
