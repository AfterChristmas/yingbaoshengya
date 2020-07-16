package com.yingbao.career.http.resultbean;

import java.util.Map;

/**
 * @Description:
 * @Date: 2020/5/23 17:45
 * @Auther: wanyan
 */
public class VideoRecordCount {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"viewNum":8,"viewTotalTime":342,"dayViews":{"2020-05-11":57,"2020-05-12":18,"2020-05-13":32,"2020-05-14":33,"2020-05-15":61,"2020-05-19":75,"2020-05-21":50,"2020-05-22":16},"subjectViews":{"政治":32,"历史":18,"数学":136,"语文":66,"英语":33,"地理":57}}
     * timestamp : 1590226970053
     */

    private boolean success;
    private String message;
    private int code;
    private ResultBean result;
    private long timestamp;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultBean {
        /**
         * viewNum : 8
         * viewTotalTime : 342
         * dayViews : {"2020-05-11":57,"2020-05-12":18,"2020-05-13":32,"2020-05-14":33,"2020-05-15":61,"2020-05-19":75,"2020-05-21":50,"2020-05-22":16}
         * subjectViews : {"政治":32,"历史":18,"数学":136,"语文":66,"英语":33,"地理":57}
         */

        private Integer viewNum;
        private Integer viewTotalTime;
        private Map<String, Integer> dayViews;
        private Map<String, Integer> subjectViews;

        public Integer getViewNum() {
            return viewNum;
        }

        public void setViewNum(Integer viewNum) {
            this.viewNum = viewNum;
        }

        public Integer getViewTotalTime() {
            return viewTotalTime;
        }

        public void setViewTotalTime(Integer viewTotalTime) {
            this.viewTotalTime = viewTotalTime;
        }

        public Map<String, Integer> getDayViews() {
            return dayViews;
        }

        public void setDayViews(Map<String, Integer> dayViews) {
            this.dayViews = dayViews;
        }

        public Map<String, Integer> getSubjectViews() {
            return subjectViews;
        }

        public void setSubjectViews(Map<String, Integer> subjectViews) {
            this.subjectViews = subjectViews;
        }
    }
}
