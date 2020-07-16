package com.yingbao.career.http;

import java.io.Serializable;

/**
 * @Description:
 * @Date: 2019/12/25 19:41
 * @Auther: wanyan
 */
public class TestResponse<T> implements Serializable {

    /**
     * code : 0
     * message :
     * result : {}
     * success : true
     * timestamp : 0
     */

    private int code;
    private String message;
    private T result;
    private boolean success;
    private int timestamp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

}
