package com.yingbao.career.http.resultbean;

/**
 * @Description:
 * @Date: 2020/2/14 16:40
 * @Auther: wanyandongchen
 */
public class LoginResultBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : {"userId":68107,"token":"68107.a9247f8d42174a11ae5f15949dfc02f1","isCompleteInfo":0,"phone":""}
     * timestamp : 1581669555866
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
         * userId : 68107
         * token : 68107.a9247f8d42174a11ae5f15949dfc02f1
         * isCompleteInfo : 0
         * phone :
         */

        private int userId;
        private String token;
        private int isCompleteInfo;
        private String phone;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getIsCompleteInfo() {
            return isCompleteInfo;
        }

        public void setIsCompleteInfo(int isCompleteInfo) {
            this.isCompleteInfo = isCompleteInfo;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
