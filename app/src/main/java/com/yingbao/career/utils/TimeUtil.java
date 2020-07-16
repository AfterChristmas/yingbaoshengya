package com.yingbao.career.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");
    
    public static final long ONESEC_MILLISECONDS = 6000L;//6*1000;
    public static final long ONEMIN_MILLISECONDS = 60000L;//60*1000;
    public static final long ONEHOU_MILLISECONDS = 3600000L;//60*60*1000;
    public static final long ONEDAY_MILLISECONDS = 86400000L;//24*60*60*1000;
	/**
	 * 时间戳转年月日时分秒
	 * @param timeStamp
	 * @return
	 */
	public static String timeStamp2Date(long timeStamp){
		//System.out.println(timeStamp);
		Date date = new Date(timeStamp);
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastDate = simpleFormat.format(date);
		//System.out.println(lastDate);
		return lastDate;
	}
	/**
	 * 时间戳转可读的格式，如年月日
	 * @param timeStamp
	 * @param format 如："yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String timeStamp2Date(long timeStamp, SimpleDateFormat format){
		//System.out.println(timeStamp);
		Date date = new Date(timeStamp);
		String lastDate = format.format(date);
		//System.out.println(lastDate);
		return lastDate;
	}
	/**
	 * 时间戳转可读的格式，如年月日
	 * @param timeStamp
	 * @return
	 */
	public static String timeStamp2ShortDate(long timeStamp){
		//System.out.println(timeStamp);
		Date date = new Date(timeStamp);
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd");
		String lastDate = simpleFormat.format(date);
		//System.out.println(lastDate);
		return lastDate;
	}
	/**
	 * 年月日时分秒转时间戳
	 * @param dateStr
	 * @return
	 */
	public static long date2TimeStamp(String dateStr){
		if (TextUtils.isEmpty(dateStr)){
			return 0;
		}
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = simpleFormat.parse(dateStr);
			long timestamp = date.getTime();
			//System.out.println(timestamp);
			return timestamp;
		} catch (ParseException e) {
			e.printStackTrace();
			//返回 0  就是1970年1月1日
			return 0;
		}
	}
	/**
	 * 年月日时分秒转时间戳
	 * @param dateStr
	 * @return
	 */
	public static String date2YearMonth(String dateStr){
		if (TextUtils.isEmpty(dateStr)){
			return "";
		}
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = simpleFormat.parse(dateStr);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			String week = sdf.format(date);
			//System.out.println(timestamp);
			return week;
		} catch (ParseException e) {
			e.printStackTrace();
			//返回 0  就是1970年1月1日
			return "";
		}
	}
	/**
	 * 年月日时分秒转时间戳
	 * @param dateStr
	 * @return
	 */
	public static String date2Week(String dateStr){
		if (TextUtils.isEmpty(dateStr)){
			return "星期一";
		}
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = simpleFormat.parse(dateStr);
			SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
			String week = sdf.format(date);
			//System.out.println(timestamp);
			return week;
		} catch (ParseException e) {
			e.printStackTrace();
			//返回 0  就是1970年1月1日
			return "星期一";
		}
	}
	/**
	 * 年月日时分秒转时间戳
	 * @param dateStr
	 * @return
	 */
	public static String date2Day(String dateStr){
		if (TextUtils.isEmpty(dateStr)){
			return "";
		}
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = simpleFormat.parse(dateStr);
			SimpleDateFormat sdf = new SimpleDateFormat("dd");
			String week = sdf.format(date);
			//System.out.println(timestamp);
			return week;
		} catch (ParseException e) {
			e.printStackTrace();
			//返回 0  就是1970年1月1日
			return "";
		}
	}
	/**
	 * 年月日时分秒转时间戳
	 * @param dateStr
	 * @return
	 */
	public static String date2Month(String dateStr){
		if (TextUtils.isEmpty(dateStr)){
			return "";
		}
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = simpleFormat.parse(dateStr);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
			String week = sdf.format(date);
			//System.out.println(timestamp);
			return week;
		} catch (ParseException e) {
			e.printStackTrace();
			//返回 0  就是1970年1月1日
			return "";
		}
	}
	/**
	 * 年月日时分秒转时间戳
	 * @param dateStr
	 * @return
	 */
	public static long noSecondDate2TimeStamp(String dateStr){
		if (TextUtils.isEmpty(dateStr)){
			return 0;
		}
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date = simpleFormat.parse(dateStr);
			long timestamp = date.getTime();
			//System.out.println(timestamp);
			return timestamp;
		} catch (ParseException e) {
			e.printStackTrace();
			//返回 0  就是1970年1月1日
			return 0;
		}
	}
	/**
	 * 年月日秒转时间戳
	 * @param dateStr
	 * @return
	 */
	public static long simpleDate2TimeStamp(String dateStr){
		if (TextUtils.isEmpty(dateStr)){
			return 0;
		}
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = simpleFormat.parse(dateStr);
			long timestamp = date.getTime();
			//System.out.println(timestamp);
			return timestamp;
		} catch (ParseException e) {
			e.printStackTrace();
			//返回 0  就是1970年1月1日
			return 0;
		}
	}
	/**
	 * 长日期转短日期
	 * 年月日时分秒转年月日
	 * @param date 如：2016-08-01 14:42:56
	 */
	public static String long2Short(String date) {
		if (TextUtils.isEmpty(date)){
			return  "无";
		}
		Date dateLong = new Date(date2TimeStamp(date));
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String lastDate = simpleFormat.format(dateLong);
		//System.out.println(lastDate);
		return lastDate;
	}
	/**
	 * 长日期转短日期
	 * 年月日时分秒转年月日
	 * @param date 如：2016-08-01 14:42:56
	 */
	public static String long2ShortYMD(String date) {
		Date dateLong = new Date(date2TimeStamp(date));
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		String lastDate = simpleFormat.format(dateLong);
		return lastDate;
	}
	/**
	 * 长日期转短日期
	 * 年月日时分秒转月日(忽略年)
	 * @param date 如：2016-08-01 14:42:56 -> 08-01
	 */
	public static String long2ShortMd(String date) {
		Date dateLong = new Date(date2TimeStamp(date));
		SimpleDateFormat simpleFormat = new SimpleDateFormat("MM-dd");
		String lastDate = simpleFormat.format(dateLong);
		//System.out.println(lastDate);
		return lastDate;
	}

	public static Date formatCheckIndate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		// 字符串转日期
		try {
			return 	format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isInThreeDays(String date) {
        //三天以内   显示红点
//超过3天  不 显示红点
        return (System.currentTimeMillis() - date2TimeStamp(date)) < 3 * ONEDAY_MILLISECONDS;
	}

	/**
	 * 长日期转短日期
	 * 年月日时分秒转月日(忽略年)
	 *
	 * @param spendTime 单位秒
	 */
	public static String time2MinSec(long spendTime, boolean canIgnoreHour) {
		if (spendTime == 0) {
			return "0";
		}
		long hour = spendTime / (60 * 60);
		long min = (spendTime - hour * 60 * 60) / 60;
		long seconds = spendTime  - hour * 60 * 60 - min * 60;
		if (canIgnoreHour){
			if (hour == 0) {
				if (min == 0) {
					return seconds + "秒";
				} else {
					return min + "分" + seconds + "秒";
				}
			} else {
				return hour + "小时" + min + "分" + seconds + "秒";
			}
		}else {
			return hour + "小时" + min + "分" + seconds + "秒";
		}
	}

	/**
	 * .Description://根据字符日期返回星期几
	 * .Author:麦克劳林
	 * .@Date: 2018/12/29
	 */
	public static String getWeek(String dateTime){
		String week = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(dateTime);
			SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
			week = dateFm.format(date);
			week=week.replaceAll("星期","周");
		}catch (ParseException e){
			e.printStackTrace();
		}
		return week;
	}

	/**
	 * 获取过去7天内的日期数组
	 * @param intervals      intervals天内
	 * @return              日期数组
	 */
	public static ArrayList<String> getDays(int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = intervals -1; i >= 0; i--) {
			pastDaysList.add(getPastDate(i));
		}
		return pastDaysList;
	}
	/**
	 * 获取过去第几天的日期
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}
}
