package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2019/12/29 13:54
 * @Auther: wanyan
 */
public class CollectListResultBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : {"records":[{"questionUrl":"","createBy":"app","createTime":"2019-12-28 12:35:32","updateBy":"","categoryId_dictText":"","isDelete":0,"updateTime":"","subjectId_dictText":"","id":"1210781299493691394","userId":"","categoryId":"","subjectId":""}],"total":19,"size":10,"current":1,"orders":[],"searchCount":true,"pages":2}
     * timestamp : 1577965604178
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
         * records : [{"questionUrl":"","createBy":"app","createTime":"2019-12-28 12:35:32","updateBy":"","categoryId_dictText":"","isDelete":0,"updateTime":"","subjectId_dictText":"","id":"1210781299493691394","userId":"","categoryId":"","subjectId":""}]
         * total : 19
         * size : 10
         * current : 1
         * orders : []
         * searchCount : true
         * pages : 2
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;
        private List<?> orders;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public static class RecordsBean {
            /**
             * questionUrl :
             * createBy : app
             * createTime : 2019-12-28 12:35:32
             * updateBy :
             * categoryId_dictText :
             * isDelete : 0
             * updateTime :
             * subjectId_dictText :
             * id : 1210781299493691394
             * userId :
             * categoryId :
             * subjectId :
             */

            private String questionUrl;
            private String createBy;
            private String createTime;
            private String updateBy;
            private String categoryId_dictText;
            private int isDelete;
            private String updateTime;
            private String subjectId_dictText;
            private String id;
            private String userId;
            private String categoryId;
            private String subjectId;
            private boolean isChecked = false;

            public String getQuestionUrl() {
                return questionUrl;
            }

            public void setQuestionUrl(String questionUrl) {
                this.questionUrl = questionUrl;
            }

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

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public String getCategoryId_dictText() {
                return categoryId_dictText;
            }

            public void setCategoryId_dictText(String categoryId_dictText) {
                this.categoryId_dictText = categoryId_dictText;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getSubjectId_dictText() {
                return subjectId_dictText;
            }

            public void setSubjectId_dictText(String subjectId_dictText) {
                this.subjectId_dictText = subjectId_dictText;
            }

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

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(String subjectId) {
                this.subjectId = subjectId;
            }

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }
        }
    }
}
