package com.yingbao.career.http.resultbean;

/**
 * @Description:
 * @Date: 2019/12/29 11:54
 * @Auther: wanyan
 */
public class AddErrorFavorResultBean {

    /**
     * success : true
     * message : 添加成功！
     * code : 200
     * result : {"id":"1211132527436214273","userId":"202","questionUrl":"http://www.baidu.com","categoryId":null,"createTime":"2019-12-29 11:51:10","createBy":"app","updateTime":null,"updateBy":"","isDelete":0}
     * timestamp : 1577591470952
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
         * id : 1211132527436214273
         * userId : 202
         * questionUrl : http://www.baidu.com
         * categoryId : null
         * createTime : 2019-12-29 11:51:10
         * createBy : app
         * updateTime : null
         * updateBy :
         * isDelete : 0
         */

        private String id;
        private String userId;
        private String questionUrl;
        private Object categoryId;
        private String createTime;
        private String createBy;
        private Object updateTime;
        private String updateBy;
        private int isDelete;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getQuestionUrl() {
            return questionUrl;
        }

        public void setQuestionUrl(String questionUrl) {
            this.questionUrl = questionUrl;
        }

        public Object getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }
    }
}
