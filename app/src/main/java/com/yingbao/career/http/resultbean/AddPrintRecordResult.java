package com.yingbao.career.http.resultbean;

/**
 * @Description:
 * @Date: 2019/12/26 14:30
 * @Auther: wanyan
 */
public class AddPrintRecordResult {

    /**
     * code : 0
     * message :
     * result : {"createBy":"","createTime":"","id":"","isDelete":0,"questionUrl":"","updateBy":"","updateTime":"","userId":""}
     * success : true
     * timestamp : 0
     */

    private int code;
    private String message;
    private ResultBean result;
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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
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

    public static class ResultBean {
        /**
         * createBy :
         * createTime :
         * id :
         * isDelete : 0
         * questionUrl :
         * updateBy :
         * updateTime :
         * userId :
         */

        private String createBy;
        private String createTime;
        private String id;
        private int isDelete;
        private String questionUrl;
        private String updateBy;
        private String updateTime;
        private String userId;

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getQuestionUrl() {
            return questionUrl;
        }

        public void setQuestionUrl(String questionUrl) {
            this.questionUrl = questionUrl;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
