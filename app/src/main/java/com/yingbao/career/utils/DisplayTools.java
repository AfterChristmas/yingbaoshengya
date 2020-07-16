package com.yingbao.career.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.yingbao.career.R;


/**
 * 凤凰学易自定义的Android大小单位转换 及 获得终端分辨率信息的工具类
 *
 * @author 徐金山
 * @version 1.0.0
 */
public class DisplayTools {

    public static void setTransparentStatusBar(Activity activity) {
        //5.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            window.setStatusBarColor(activity.getResources().getColor(R.color.career_window_bg));
            //4.4到5.0
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }
    /**
     * 启用 透明状态栏  全屏
     * @param activity
     */
    public static void setFullScreenTranslucentStatusbar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    /**
     * 显示度量器对象
     */
    private static DisplayMetrics displayMetrics;
    /**
     * 屏幕像素的密度值
     */
    private static float density;

    /**
     * 获得终端屏幕的像素密度值
     *
     * @param context 上下文
     * @return float 返回屏幕的像素密度值。当上下文对象传入null值时，返回信息为0.0。
     */
    public static float getResolutionDensity(Context context) {
        float density = 0f;

        if (null != context) {
            density = context.getResources().getDisplayMetrics().density;
        }

        return density;
    }

    /**
     * 获得终端屏幕的宽度值信息
     *
     * @param context 上下文
     * @return int 返回屏幕的宽信息。当上下文对象传入null值时，返回信息为0。
     */
    public static int getResolutionWidthInfo(Context context) {
        int resolutionWidth = 0;

        if (null != context) {
            if (null == displayMetrics) {
                displayMetrics = context.getApplicationContext().getResources().getDisplayMetrics();
            }

            resolutionWidth = displayMetrics.widthPixels;
            return resolutionWidth;
        } else {
            return resolutionWidth;
        }
    }

    /**
     * 获得终端屏幕的高度值信息
     *
     * @param context 上下文
     * @return int 返回屏幕的高信息。当上下文对象传入null值时，返回信息为0。
     */
    public static int getResolutionHeightInfo(Context context) {
        int resolutionHeight = 0;

        if (null != context) {
            if (null == displayMetrics) {
                displayMetrics = context.getApplicationContext().getResources().getDisplayMetrics();
            }

            resolutionHeight = displayMetrics.heightPixels;
            return resolutionHeight;
        } else {
            return resolutionHeight;
        }
    }

    /**
     * 获得终端屏幕的宽，高信息
     *
     * @param context 上下文
     * @return String 以“宽_高”的格式返回屏幕的宽，高信息。当上下文对象传入null值时，返回信息为空字符串。
     */
    public static String getResolutionInfo(Context context) {
        String resolution = "";

        if (null != context) {
            if (null == displayMetrics) {
                displayMetrics = context.getApplicationContext().getResources().getDisplayMetrics();
            }

            String width = String.valueOf(displayMetrics.widthPixels);
            String height = String.valueOf(displayMetrics.heightPixels);
            resolution = width + "_" + height;
            return resolution;
        } else {
            return resolution;
        }
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue 像素的值
     * @return int 转换后的值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dpValue dip的值
     * @return int 转换后的值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue                                    px的值
     * @param fontScale（DisplayMetrics类中属性scaledDensity）
     * @return int 转换后的值
     */
    public static int px2sp(float pxValue, float fontScale) {
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue sp的值
     * @return int 转换后的值
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }


    /**
     * 获取屏幕宽高
     *
     * @param activity
     * @return
     */
    public static Point getScreenSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 拉申titleBar
     *
     * @param topSpaceView 把1dp拉申到statusBarHeight
     */
    public static void stretchTitleBar(View topSpaceView) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        topSpaceView.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams topLp = (LinearLayout.LayoutParams) topSpaceView.getLayoutParams();
        //取控件textView当前的布局参数
        topLp.height = getStatusBarHeight(topSpaceView.getContext());
        topSpaceView.setLayoutParams(topLp);
    }

    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
