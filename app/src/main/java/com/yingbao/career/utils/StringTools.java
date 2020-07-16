package com.yingbao.career.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * String类型数据处理类。
 *
 * @author lwm
 */
public class StringTools {

    public static String getEllipticalString(String originalStr, int maxLength) {
        if (originalStr == null) {
            return null;
        }

        if (originalStr.length() <= maxLength) {
            return originalStr;
        } else {
            return originalStr.substring(0, maxLength) + "...";
        }
    }


    /**
     * 如果小数部分不为0则显示两位小数，如果小数部分为0则不显示
     *
     * @param num
     * @return
     */
    public static String formatFloatNumber(float num) {
        if (Math.round(num) - num == 0) {
            return String.valueOf(Math.round(num));
        } else {
            return String.format("%.2f", num);
        }
    }

    /**
     * 将时间字符串转化为时间戳
     */
    public static long getTime(String user_time) {
        long re_time = 0;
        //2017_5_8_16_10_00
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            try {
                re_time = Long.parseLong(str);
            } catch (NumberFormatException ex) {
                re_time = 0;
            }

        } catch (ParseException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return re_time;
    }

    public static String getTopicGoodEmojis(int goodCount, int replyCount) {
        int unicodeJoy = 0x1F44D;
        int replyJoy = 0x1F4AC;
        return new String(Character.toChars(unicodeJoy)) + goodCount + "\n" + new String(Character.toChars(replyJoy)) + replyCount;
    }

    public static String getTopicReplyEmojis(int replyCount) {
        int replyJoy = 0x1F4AC;
        return new String(Character.toChars(replyJoy)) + replyCount;
    }

    /**
     * 删除html标签
     *
     * @param orginalStr
     * @return
     */
    public static String removeHtmlTag(String orginalStr) {
        String newString = orginalStr.replaceAll("\\<.*?>", "")
                .replace("&nbsp;", "")
                .replace("\n", "")
                .replace("\t", "")
                .replace("&gt;", ">")
                .replace("&lt;", "<")
                .replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replace("&apos;", "'")
                .replace("&cent;", "￠")
                .replace("&euro;", "€")
                .replace("&pound;", "£")
                .replace("&yen;", "￥");
        return newString;
    }


    /**
     * 判断是否有新版本
     *
     * @param curVersion
     * @param newVersion
     * @return
     */
    public static boolean isNewVersion(String curVersion, String newVersion) {
        try {
            String[] curVersionArray = curVersion.split("\\.");
            String[] newVersionArray = newVersion.split("\\.");
            for (int i = 0; i < curVersionArray.length && i < newVersionArray.length; i++) {
                int result = Integer.valueOf(curVersionArray[i]) - Integer.valueOf(newVersionArray[i]);
                if (result < 0) {
                    return true;
                } else if (result > 0) {
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    public static String sortString(String str) {
        char tempArray[] = str.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static String getCorrectVideoCoverUrl(String url) {
      if (url.startsWith("http://img.edu_videocc.net")){
          url = url.replace("http://img.edu_videocc.net","http://img.videocc.net");
      }
        return url;
    }

    /**
     * 根据试题id选择图片所属路径。
     */
    public static String getImgFolder(String quesID, String quesName)
            throws Exception {

        int id = Integer.parseInt(quesID);
        String imgPath = null;
        return imgPath;

    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    //过滤答案中各种字符，包括全角半角空格
    public static String getQuesAnswer(String quesAnswer) {
        if (quesAnswer != null) {
            return quesAnswer.replace("【答案】", "").replace("&nbsp;", "").replace("&NBSP;", "").replace("<BR/>", "").replace("<br/>", "").replace("　", "").replace(" ", "").trim().toUpperCase();
        } else {
            return null;
        }
    }

    //判断是否是单选题
    public static boolean isSingleQues(int quesTypeId) {
        if (quesTypeId == 2 || quesTypeId == 3 || quesTypeId == 6) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将url转成文件名
     *
     * @param url
     * @return
     */
    public static String convertUrlToFileName(String url) {
        String fileName = url.split("\\?")[0];
        String fileNames[] = fileName.split("\\/");
        fileName = fileNames[fileNames.length - 1];
        return fileName;
    }

    /**
     * 根据题型判断是否客观题
     *
     * @param quesTypeId
     * @return
     */
    public static boolean isObjectiveQues(int quesTypeId) {
        return (quesTypeId == 2 || quesTypeId == 3 || quesTypeId == 4 || quesTypeId == 5 || quesTypeId == 6 || quesTypeId == 7);
    }

    /**
     * 获取随机验证码
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 验证手机格式
     * 手机端弃用该方法，只判断手机号是否为空，所有的验证由服务端判断，保持统一
     */
    @Deprecated
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        //String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "^1[3|4|5|7|8]\\d{9}$";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 计算字符str中包含多少个strInclude
     *
     * @param str
     * @param strInclude
     * @return 包含个数
     */
    public static int stringNumbers(String str, String strInclude) {
        int counter = 0;
        if (!str.contains(strInclude)) {
            return 0;
        } else if (str.contains(strInclude)) {
            counter++;
            stringNumbers(str.substring(str.indexOf(strInclude) + strInclude.length()), strInclude);
            return counter;
        }
        return 0;
    }

    /**
     * 判断答案是不是只有a、b、c、d字母
     *
     * @param quesAnswer
     * @return
     */
    public static boolean isABCD(String quesAnswer) {

        String[] strs = (quesAnswer.replace("【答案】", "")
                .replace("<p>", "").replace("</p>", "").replace(".", "")
                .replace("<br />", "").replace("&nbsp;", "").trim().trim()).split("");
        for (int i = 0; i < strs.length; i++) {
            if (!"abcdABCD".contains(strs[i])) {//答案有噪音
                return false;
            }
        }
        if (strs.length < 1) {
            return false;
        }

        return true;
    }

    /**
     * 设置字体大小
     *
     * @param str   需要设置的字符串。
     * @param color (用getResource的方式获取自定义color。) 颜色。
     *              <p>
     *              return SpannableString 设置了颜色的字符串。
     */
    public static SpannableString getColorSpan(String str, int color,
                                               int colorEndIndex) {

        SpannableString spanString = new SpannableString(str);

        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spanString.setSpan(span, 0, colorEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, colorEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new RelativeSizeSpan(1.5f), 0, colorEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spanString;

    }


    /**
     * 得到设置了大小和颜色的试题数量指示的文本。
     *
     * @param pageNum  当前页页码。
     * @param endIndex 文本中，被设置的区间的结束点。
     * @param textSize 文字大小。
     * @param color    (用getResource的方式获取自定义color。) 文字颜色
     * @return spanString 设置好的文本。
     */
    public static SpannableString getSpanStr(int pageNum, int endIndex,
                                             int textSize, int color) {

        SpannableString spanString = new SpannableString(pageNum + 1 + "");
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(textSize);

        spanString.setSpan(span, 0, endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ForegroundColorSpan span1 = new ForegroundColorSpan(color);
        spanString.setSpan(span1, 0, endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spanString;

    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String convertThumbUptoStr(int thumbUpNumber) {
        return thumbUpNumber > 10000 ? "1w+次" : thumbUpNumber + "次";
    }

    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.format("%2d时%2d分%2d秒", hours, minutes, seconds) : String.format("%2d分%2d秒", minutes, seconds);
    }

    public static String generateBiaoZhunTime(int time) {
        int seconds = time % 60;
        int minutes = (time / 60) % 60;
        int hours = time / 3600;

        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * 将double转为数值，并最多保留num位小数。例如当num为2时，1.268为1.27，1.2仍为1.2；1仍为1，而非1.00;100.00则返回100。
     *
     * @param d
     * @param num 小数位数
     * @return
     */
    public static String double2String(double d, int num) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(num);//保留两位小数
        nf.setGroupingUsed(false);//去掉数值中的千位分隔符

        String temp = nf.format(d);
        if (temp.contains(".")) {
            String s1 = temp.split("\\.")[0];
            String s2 = temp.split("\\.")[1];
            for (int i = s2.length(); i > 0; --i) {
                if (!s2.substring(i - 1, i).equals("0")) {
                    return s1 + "." + s2.substring(0, i) + "%";
                }
            }
            return s1 + "%";
        }
        return temp + "%";
    }

    /*
     * 将时间戳转换为时间
     *
     * s就是时间戳
     */

    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //如果它本来就是long类型的,则不用写这一步
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToDay(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //如果它本来就是long类型的,则不用写这一步
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}
