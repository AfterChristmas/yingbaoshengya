package com.yingbao.career.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.yingbao.career.common.CommonConstant;
import com.yingbao.career.common.MyApplication;
import com.yingbao.career.http.BaseObserver;
import com.yingbao.career.http.RetrofitFactory;
import com.yingbao.career.http.resultbean.UserDetailResultBean;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.Map;


public class CareerSPHelper {

    private static SharedPreferences.Editor mEditor = null;
    private static SharedPreferences mdPreferences = null;
    private static final String fileName = CommonConstant.SpConstant.SP_FILE_NAME;
    private static String[] mSavePreferences = new String[]{
            CommonConstant.SHOW_USER_AGREEMENT
    };

    /**
     * 根据文件名得到Editor对象用于写入数据
     *
     * @param paramContext
     * @param fileName     文件名
     * @return
     */
    private static SharedPreferences.Editor getEditor(Context paramContext, String fileName) {
        if (mEditor == null)
            mEditor = paramContext.getSharedPreferences(fileName, 0).edit();
        return mEditor;
    }

    /**
     * 根据文件名得到SharedPreferences对象用于读取数据
     *
     * @param paramContext
     * @param fileName     文件名
     * @return
     */
    private static synchronized SharedPreferences getSharedPreferences(Context paramContext, String fileName) {
        if (mdPreferences == null)
            mdPreferences = paramContext.getSharedPreferences(fileName, 0);
        return mdPreferences;
    }

    /**
     * 对String类型数据的存储和读取
     */
    public static void putString(Context context, String key, String value) {
        getEditor(context, fileName).putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defaultValue) {
        return getSharedPreferences(context, fileName).getString(key, defaultValue);
    }

    public static String getString(Context context, String key) {
        return getString(context,key, "");
    }
    public static int getInt(Context context, String key, int  defaultValue) {
        return getSharedPreferences(context, fileName).getInt(key, defaultValue);
    }

    public static int getInt(Context context, String key) {
        return getInt(context,key, 0);
    }

    public static void putInt(Context context, String key, int value) {
        getEditor(context, fileName).putInt(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context,key, false);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getEditor(context, fileName).putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSharedPreferences(context, fileName).getBoolean(key, defaultValue);
    }
    /**
     * 清空person_infomation SP文件
     */
    public static void clear() {
        getEditor(MyApplication.context, fileName).clear().commit();
    }

    public static void clearLoginInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences(MyApplication.context,fileName);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            if (!containKey(key)) {
                removeString(key);
            }
        }
    }
    private static boolean containKey(String targetValue) {
        return Arrays.asList(mSavePreferences).contains(targetValue);
    }
    /**
     * 移除字段
     *
     * @return
     */
    public static void removeString(String key) {
        getEditor(MyApplication.context, fileName).remove(key).commit();
    }
    /**
     * 清除sp中的key
     */
    public static void removeKey(Context context, String key) {
        getEditor(context, fileName).remove(key).commit();
    }

    public static String getToken() {
        return getString(MyApplication.context, CommonConstant.SpConstant.TOKEN, "");
    }

    public static int getUserId() {
        return getInt(MyApplication.context, CommonConstant.SpConstant.ID, 0);
    }
    public static String getUserPhone() {
        return getString(MyApplication.context, CommonConstant.SpConstant.PHONE, "");
    }
    public static void setToken(String token) {
         putString(MyApplication.context, CommonConstant.SpConstant.TOKEN, token);
    }

    public static void setUserId(int userId) {
         putInt(MyApplication.context, CommonConstant.SpConstant.ID, userId);
    }
    public static void setUserPhone(String phone) {
         putString(MyApplication.context, CommonConstant.SpConstant.PHONE, phone);
    }
    public static String getUserName() {
        return getString(MyApplication.context, CommonConstant.SpConstant.NAME, "");
    }
    public static void setUserName(String name) {
        putString(MyApplication.context, CommonConstant.SpConstant.NAME, name);
    }
    public static void setUserImageUrl(String imageUrl) {
        putString(MyApplication.context, CommonConstant.SpConstant.HEAD_ICON, imageUrl);
    }
    public static String getUserImageUrl() {
        return getString(MyApplication.context, CommonConstant.SpConstant.HEAD_ICON, "");
    }

    public static void setUserAccount(String imageUrl) {
        putString(MyApplication.context, CommonConstant.SpConstant.ACCOUNT, imageUrl);
    }
    public static String getUserAccount() {
        return getString(MyApplication.context, CommonConstant.SpConstant.ACCOUNT, "");
    }
    public static void setUserGender(int imageUrl) {
        putInt(MyApplication.context, CommonConstant.SpConstant.GENTDER, imageUrl);
    }
    public static int getUserGender() {
        return getInt(MyApplication.context, CommonConstant.SpConstant.GENTDER, 0);
    }
    public static void setUserLocation(String imageUrl) {
        putString(MyApplication.context, CommonConstant.SpConstant.LOCATION, imageUrl);
    }
    public static String getUserLocation() {
        return getString(MyApplication.context, CommonConstant.SpConstant.LOCATION, "");
    }
    public static void setUserSchool(String imageUrl) {
        putString(MyApplication.context, CommonConstant.SpConstant.SCHOOL, imageUrl);
    }
    public static String getUserSchool() {
        return getString(MyApplication.context, CommonConstant.SpConstant.SCHOOL, "");
    }
    public static void setUserClass(String imageUrl) {
        putString(MyApplication.context, CommonConstant.SpConstant.CLASS, imageUrl);
    }
    public static String getUserClass() {
        return getString(MyApplication.context, CommonConstant.SpConstant.CLASS, "");
    }
    public static void setUserGrade(int grade) {
        putInt(MyApplication.context, CommonConstant.SpConstant.GRADE, grade);
    }
    public static int getUserGrade() {
        return getInt(MyApplication.context, CommonConstant.SpConstant.GRADE, 16);
    }
    /**
     * 根据本地userId判断是否登录
     * @return
     */
	public static boolean isLogin(){
		int  userId = getInt(MyApplication.context, CommonConstant.SpConstant.ID, 0);
		if (userId == 0) {//未登录状态默认ID为0
			return false;
		}else{
			return true;
		}
	}

	public static void getUserInfo(){
	    if (CareerSPHelper.getUserId() == 0){
	        return;
        }
        RetrofitFactory.getInstance().getUserInfo(CareerSPHelper.getUserId(), new BaseObserver<UserDetailResultBean.ResultBean>() {
            @Override
            protected void onSuccees(UserDetailResultBean.ResultBean userDetailResultBean) {
                if (userDetailResultBean != null) {
                    if (!TextUtils.isEmpty(userDetailResultBean.getImageUrl())) {
                        CareerSPHelper.setUserImageUrl(userDetailResultBean.getImageUrl());
                    }
                    CareerSPHelper.setUserId(userDetailResultBean.getUserId());
                    if (!TextUtils.isEmpty(userDetailResultBean.getPhone())) {
                        CareerSPHelper.setUserPhone(userDetailResultBean.getPhone());
                    }
                    if (!TextUtils.isEmpty(userDetailResultBean.getRealName())) {
                        CareerSPHelper.setUserName(userDetailResultBean.getRealName());
                    }
                    CareerSPHelper.setUserGender(userDetailResultBean.getSex());
                    if (!TextUtils.isEmpty(userDetailResultBean.getAddress())) {
                        CareerSPHelper.setUserLocation(userDetailResultBean.getAddress());
                    }
                    if (!TextUtils.isEmpty(userDetailResultBean.getSchoolName())) {
                        CareerSPHelper.setUserSchool(userDetailResultBean.getSchoolName());
                    }
                    CareerSPHelper.setUserGrade(userDetailResultBean.getGradeId());
                    if (!TextUtils.isEmpty(userDetailResultBean.getClazzName())) {
                        CareerSPHelper.setUserClass(userDetailResultBean.getClazzName());
                    }
                    EventBus.getDefault().post(new EventMessageBean(EventType.GET_USERINFO_SUCCESS));
                }
            }

            @Override
            protected void onFailure(String error, boolean isNetWorkError) {
            }
        });
    }

}


