package com.yingbao.career.http.resultbean;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Date: 2019/12/26 18:17
 * @Auther: wanyan
 */
public class PrintRecordListResult {

    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : {"records":[{"questionUrl":"","createBy":"app","createTime":"2019-12-26 15:18:16","updateBy":"","isDelete":0,"updateTime":null,"id":"1210097477512896514","userId":""},{"questionUrl":"","createBy":"app","createTime":"2019-12-26 15:19:33","updateBy":"","isDelete":0,"updateTime":null,"id":"1210097802047168514","userId":""},{"questionUrl":"","createBy":"app","createTime":"2019-12-26 15:32:27","updateBy":"","isDelete":0,"updateTime":null,"id":"1210101047213727746","userId":""},{"questionUrl":"","createBy":"app","createTime":"2019-12-26 15:32:35","updateBy":"","isDelete":0,"updateTime":null,"id":"1210101081514745858","userId":""},{"questionUrl":"","createBy":"app","createTime":"2019-12-26 15:54:40","updateBy":"","isDelete":0,"updateTime":null,"id":"1210106638782996481","userId":""},{"questionUrl":"https://da-yin-ji.oss-cn-beijing.aliyuncs.com/joker/2af28ee3b3f4428a9ec872ac59b426ef.jpg","createBy":"app","createTime":"2019-12-26 16:02:40","updateBy":"","isDelete":0,"updateTime":null,"id":"1210108649918210050","userId":"202"},{"questionUrl":"111","createBy":"app","createTime":"2019-12-26 15:09:37","updateBy":"","isDelete":0,"updateTime":null,"id":"123","userId":""}],"total":7,"size":10,"current":1,"orders":[],"searchCount":true,"pages":1}
     * timestamp : 1577355082788
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
         * records : [{"questionUrl":"","createBy":"app","createTime":"2019-12-26 15:18:16","updateBy":"","isDelete":0,"updateTime":null,"id":"1210097477512896514","userId":""},{"questionUrl":"","createBy":"app","createTime":"2019-12-26 15:19:33","updateBy":"","isDelete":0,"updateTime":null,"id":"1210097802047168514","userId":""},{"questionUrl":"","createBy":"app","createTime":"2019-12-26 15:32:27","updateBy":"","isDelete":0,"updateTime":null,"id":"1210101047213727746","userId":""},{"questionUrl":"","createBy":"app","createTime":"2019-12-26 15:32:35","updateBy":"","isDelete":0,"updateTime":null,"id":"1210101081514745858","userId":""},{"questionUrl":"","createBy":"app","createTime":"2019-12-26 15:54:40","updateBy":"","isDelete":0,"updateTime":null,"id":"1210106638782996481","userId":""},{"questionUrl":"https://da-yin-ji.oss-cn-beijing.aliyuncs.com/joker/2af28ee3b3f4428a9ec872ac59b426ef.jpg","createBy":"app","createTime":"2019-12-26 16:02:40","updateBy":"","isDelete":0,"updateTime":null,"id":"1210108649918210050","userId":"202"},{"questionUrl":"111","createBy":"app","createTime":"2019-12-26 15:09:37","updateBy":"","isDelete":0,"updateTime":null,"id":"123","userId":""}]
         * total : 7
         * size : 10
         * current : 1
         * orders : []
         * searchCount : true
         * pages : 1
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

        public static class RecordsBean implements Serializable {
            /**
             * questionUrl :
             * createBy : app
             * createTime : 2019-12-26 15:18:16
             * updateBy :
             * isDelete : 0
             * updateTime : null
             * id : 1210097477512896514
             * userId :
             */

            private String questionUrl;
            private String createBy;
            private String createTime;
            private String updateBy;
            private int isDelete;
            private Object updateTime;
            private String id;
            private String userId;
            private int checkState = -1;

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

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
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
            public int getCheckState() {
                return checkState;
            }

            public void setCheckState(int checkState) {
                this.checkState = checkState;
            }
        }
    }
}
