package com.yingbao.career.http.resultbean;

import java.util.Map;

/**
 * @Description:
 * @Date: 2020/5/31 01:40
 * @Auther: wanyan
 */
public class QuestionRecordCount {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"questionNum":4,"accuracy":0,"dayViews":{"2020-05-30":4},"subjectViews":{"物理":1,"数学":3}}
     * timestamp : 1590860354596
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
         * questionNum : 4
         * accuracy : 0
         * dayViews : {"2020-05-30":4}
         * subjectViews : {"物理":1,"数学":3}
         */

        private int questionNum;
        private int accuracy;
        private Map<String, Integer> dayViews;
        private Map<String, Integer> subjectViews;

        public int getQuestionNum() {
            return questionNum;
        }

        public void setQuestionNum(int questionNum) {
            this.questionNum = questionNum;
        }

        public int getAccuracy() {
            return accuracy;
        }

        public void setAccuracy(int accuracy) {
            this.accuracy = accuracy;
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
