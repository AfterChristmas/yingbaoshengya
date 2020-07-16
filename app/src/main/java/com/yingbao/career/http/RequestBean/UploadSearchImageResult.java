package com.yingbao.career.http.RequestBean;

/**
 * @Description:
 * @Date: 2019/11/28 14:59
 * @Auther: wanyan
 */
public class UploadSearchImageResult {


    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : {"url":"https://da-yin-ji.oss-cn-beijing.aliyuncs.com/joker/8879e9a4c2ef40189e2df9b0d22a0f28.jpg"}
     * timestamp : 1581835118891
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
         * url : https://da-yin-ji.oss-cn-beijing.aliyuncs.com/joker/8879e9a4c2ef40189e2df9b0d22a0f28.jpg
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
