package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2019/12/29 13:23
 * @Auther: wanyan
 */
public class HasCollectSubjectResultBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : [{"sortNo":28,"createBy":"alpha","delFlag_dictText":"正常","stage":"3","stage_dictText":"高中","createTime":"2018-01-15 09:40:42","updateBy":null,"name":"政治","updateTime":"2018-01-15 09:40:42","id":"69078e67-2235-11ea-9df5-5254001d5a46","delFlag":0},{"sortNo":27,"createBy":"alpha","delFlag_dictText":"正常","stage":"3","stage_dictText":"高中","createTime":"2018-01-15 09:40:42","updateBy":null,"name":"生物","updateTime":"2018-01-15 09:40:42","id":"69078e1f-2235-11ea-9df5-5254001d5a46","delFlag":0},{"sortNo":23,"createBy":"alpha","delFlag_dictText":"正常","stage":"3","stage_dictText":"高中","createTime":"2018-01-15 09:40:42","updateBy":null,"name":"数学","updateTime":"2018-01-15 09:40:42","id":"69078cf6-2235-11ea-9df5-5254001d5a46","delFlag":0},{"sortNo":26,"createBy":"alpha","delFlag_dictText":"正常","stage":"3","stage_dictText":"高中","createTime":"2018-01-15 09:40:42","updateBy":null,"name":"化学","updateTime":"2018-01-15 09:40:42","id":"69078dd0-2235-11ea-9df5-5254001d5a46","delFlag":0},{"sortNo":24,"createBy":"alpha","delFlag_dictText":"正常","stage":"3","stage_dictText":"高中","createTime":"2018-01-15 09:40:42","updateBy":null,"name":"英语","updateTime":"2018-01-15 09:40:42","id":"69078d3f-2235-11ea-9df5-5254001d5a46","delFlag":0},{"sortNo":25,"createBy":"alpha","delFlag_dictText":"正常","stage":"3","stage_dictText":"高中","createTime":"2018-01-15 09:40:42","updateBy":null,"name":"物理","updateTime":"2018-01-15 09:40:42","id":"69078d88-2235-11ea-9df5-5254001d5a46","delFlag":0}]
     * timestamp : 1577596944194
     */

    private boolean success;
    private String message;
    private int code;
    private long timestamp;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * sortNo : 28
         * createBy : alpha
         * delFlag_dictText : 正常
         * stage : 3
         * stage_dictText : 高中
         * createTime : 2018-01-15 09:40:42
         * updateBy : null
         * name : 政治
         * updateTime : 2018-01-15 09:40:42
         * id : 69078e67-2235-11ea-9df5-5254001d5a46
         * delFlag : 0
         */

        private int sortNo;
        private String createBy;
        private String delFlag_dictText;
        private String stage;
        private String stage_dictText;
        private String createTime;
        private Object updateBy;
        private String name;
        private String updateTime;
        private String id;
        private int delFlag;

        public int getSortNo() {
            return sortNo;
        }

        public void setSortNo(int sortNo) {
            this.sortNo = sortNo;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getDelFlag_dictText() {
            return delFlag_dictText;
        }

        public void setDelFlag_dictText(String delFlag_dictText) {
            this.delFlag_dictText = delFlag_dictText;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getStage_dictText() {
            return stage_dictText;
        }

        public void setStage_dictText(String stage_dictText) {
            this.stage_dictText = stage_dictText;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }
    }
}
