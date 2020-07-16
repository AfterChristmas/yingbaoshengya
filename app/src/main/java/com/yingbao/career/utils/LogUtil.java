package com.yingbao.career.utils;

import android.util.Log;

public class LogUtil {
    public static final boolean IS_DEBUG = Utils.isApkInDebug() ? true : false;

    private static final String TAG = "LogUtil";

    /**
     * 打印“verbose级别”的日志内容
     *
     * @param tag 日志的标签名
     * @param msg 日志信息
     */
    public static void v(String tag, String msg) {
        if (IS_DEBUG) {
            Log.v(tag, msg);
        }
    }

    /**
     * 打印“info级别”的日志内容
     *
     * @param tag 日志的标签名
     * @param msg 日志信息
     */
    public static void i(String tag, String msg) {
        if (IS_DEBUG) {
            Log.i(tag, msg);
        }
    }

    /**
     * 打印“debug级别”的日志内容
     *
     * @param tag 日志的标签名
     * @param msg 日志信息
     */
    public static void d(String tag, String msg) {
        if (IS_DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * 打印“warn级别”的日志内容
     *
     * @param tag 日志的标签名
     * @param msg 日志信息
     */
    public static void w(String tag, String msg) {
        if (IS_DEBUG) {
            Log.w(tag, msg);
        }
    }

    /**
     * 打印“error级别”的日志内容
     *
     * @param tag 日志的标签名
     * @param msg 日志信息
     */
    public static void e(String tag, String msg) {
        if (IS_DEBUG) {
            Log.e(tag, msg);
        }
    }

    /**
     * 打印“任意自定义”的日志内容
     *
     * @param msg 日志信息
     */
    public static void print(String msg) {
        if (IS_DEBUG) {
            System.out.println(msg);
        }
    }


    /**
     * log 过长时打印完整 log
     *
     * @param tag
     * @param msg
     */
    public static void fullLog(String tag, String msg) {
        int maxLogSize = 1000;
        for (int i = 0; i <= msg.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > msg.length() ? msg.length() : end;
            Log.e(tag, msg.substring(start, end));
        }
    }

}

