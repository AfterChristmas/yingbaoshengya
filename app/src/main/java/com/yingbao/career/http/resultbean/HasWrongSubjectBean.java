package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2019/12/29 13:23
 * @Auther: wanyan
 */
public class HasWrongSubjectBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : [1002,1004,1006]
     * timestamp : 1590833972331
     */

    private boolean success;
    private String message;
    private int code;
    private long timestamp;
    private List<Integer> result;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<Integer> getResult() {
        return result;
    }

    public void setResult(List<Integer> result) {
        this.result = result;
    }
}
