package com.yingbao.career.common;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.Objects;

/**
 * @Description:
 * @Date: 2020/4/25 00:19
 * @Auther: wanyan
 */
public interface CommonConstant {
    // 是否显示用户协议
    String SHOW_USER_AGREEMENT = "SHOW_USER_AGREEMENT";

    interface SpConstant {
        String SP_FILE_NAME = "career";
        String ID = "userId";
        String PHONE = "phone";
        String TOKEN = "token";
        String NAME = "name";
        String HEAD_ICON = "iamgeUrl";
        String ACCOUNT = "USER_ACCOUNT";
        String GENTDER = "USER_GENTDER";
        String LOCATION = "USER_LOCATION";
        String SCHOOL = "USER_SCHOOL";
        String CLASS = "USER_CLASS";
        String GRADE = "USER_GRADE";
    }

    /**
     * 文件操作类
     */
    final class FileConstant {

        private static final String SEARCH_IMAGE = "search_image";
        public static final String ROOT_FOLDER_NAME = "gaokaocareer";
        private static final String IMG_TEMP = "temp";
        private static final String VIDEO_FILE = "video";
        private static final String ICON_FILE = "icon_file";
        private static final String DIR_DCIM = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        private static final String PRIVATE_EXTERNAL_STORAGE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        // 图片保存目录
        public static final String DIR_IMG = DIR_DCIM + File.separator + ROOT_FOLDER_NAME + File.separator;

        // 临时图片目录
        public static final String DIR_IMG_TEMP = DIR_IMG + File.separator
                + IMG_TEMP + File.separator;

        public static String getSearchImageTempDir(Context context) {
            return context.getFilesDir().getAbsolutePath() + File.separator +
                    SEARCH_IMAGE + File.separator;
        }

        public static String getIconTempDir() {
           return Objects.requireNonNull( MyApplication.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)).getAbsolutePath()
                    + File.separator + IMG_TEMP + File.separator;
        }
        //视频下载的目录 保存再data/android/packageName/download/video
        public static String getVideoDownloadDir() {
            return  MyApplication.context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator +
                    VIDEO_FILE + File.separator;
        }
    }

    public static final class URLConstant {
        // 用户协议文档地址
        public static final String AGREEMENT_URL = "http://api.shipin211.com/docs/career_agreement.html";
        // 隐私权限
        public static final String PRIVACY_POLICY_URL = "http://api.shipin211.com/docs/career_privacy.html";

    }
}
