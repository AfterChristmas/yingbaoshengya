package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2019/12/28 10:24
 * @Auther: wanyan
 */
public class TestKnowPointResult {


    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : [{"pathIds":"46230","isUse":1,"type_dictText":"","hasChild_dictText":"","updateTime":"2018-08-26","subjectId_dictText":"","type":"NODE","isUse_dictText":"","parentId":0,"subjectId":1001,"createBy":"","depth":1,"grabStatus":4,"createTime":"2018-08-26","updateBy":"","hasChild":1,"name":"文章作者","id":46230,"stageId_dictText":"","stageId":4,"ordinal":1},{"pathIds":"47314","isUse":1,"type_dictText":"","hasChild_dictText":"","updateTime":"2018-08-26","subjectId_dictText":"","type":"NODE","isUse_dictText":"","parentId":0,"subjectId":1001,"createBy":"","depth":1,"grabStatus":2,"createTime":"2018-08-26","updateBy":"","hasChild":1,"name":"语言知识","id":47314,"stageId_dictText":"","stageId":4,"ordinal":2},{"pathIds":"47654","isUse":1,"type_dictText":"","hasChild_dictText":"","updateTime":"2018-08-26","subjectId_dictText":"","type":"NODE","isUse_dictText":"","parentId":0,"subjectId":1001,"createBy":"","depth":1,"grabStatus":2,"createTime":"2018-08-26","updateBy":"","hasChild":1,"name":"阅读与鉴赏","id":47654,"stageId_dictText":"","stageId":4,"ordinal":3},{"pathIds":"47692","isUse":1,"type_dictText":"","hasChild_dictText":"","updateTime":"2018-08-26","subjectId_dictText":"","type":"NODE","isUse_dictText":"","parentId":0,"subjectId":1001,"createBy":"","depth":1,"grabStatus":4,"createTime":"2018-08-26","updateBy":"","hasChild":1,"name":"写作","id":47692,"stageId_dictText":"","stageId":4,"ordinal":4},{"pathIds":"65768","isUse":1,"type_dictText":"","hasChild_dictText":"","updateTime":"2018-08-26","subjectId_dictText":"","type":"NODE","isUse_dictText":"","parentId":0,"subjectId":1001,"createBy":"","depth":1,"grabStatus":2,"createTime":"2018-08-26","updateBy":"","hasChild":1,"name":"语言文字运用","id":65768,"stageId_dictText":"","stageId":4,"ordinal":0}]
     * timestamp : 1590389312330
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
         * pathIds : 46230
         * isUse : 1
         * type_dictText :
         * hasChild_dictText :
         * updateTime : 2018-08-26
         * subjectId_dictText :
         * type : NODE
         * isUse_dictText :
         * parentId : 0
         * subjectId : 1001
         * createBy :
         * depth : 1
         * grabStatus : 4
         * createTime : 2018-08-26
         * updateBy :
         * hasChild : 1
         * name : 文章作者
         * id : 46230
         * stageId_dictText :
         * stageId : 4
         * ordinal : 1
         */

        private String pathIds;
        private int isUse;
        private String type_dictText;
        private String hasChild_dictText;
        private String updateTime;
        private String subjectId_dictText;
        private String type;
        private String isUse_dictText;
        private int parentId;
        private int subjectId;
        private String createBy;
        private int depth;
        private int grabStatus;
        private String createTime;
        private String updateBy;
        private int hasChild;
        private String name;
        private int id;
        private String stageId_dictText;
        private int stageId;
        private int ordinal;

        public String getPathIds() {
            return pathIds;
        }

        public void setPathIds(String pathIds) {
            this.pathIds = pathIds;
        }

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public String getType_dictText() {
            return type_dictText;
        }

        public void setType_dictText(String type_dictText) {
            this.type_dictText = type_dictText;
        }

        public String getHasChild_dictText() {
            return hasChild_dictText;
        }

        public void setHasChild_dictText(String hasChild_dictText) {
            this.hasChild_dictText = hasChild_dictText;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIsUse_dictText() {
            return isUse_dictText;
        }

        public void setIsUse_dictText(String isUse_dictText) {
            this.isUse_dictText = isUse_dictText;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public int getGrabStatus() {
            return grabStatus;
        }

        public void setGrabStatus(int grabStatus) {
            this.grabStatus = grabStatus;
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

        public int getHasChild() {
            return hasChild;
        }

        public void setHasChild(int hasChild) {
            this.hasChild = hasChild;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStageId_dictText() {
            return stageId_dictText;
        }

        public void setStageId_dictText(String stageId_dictText) {
            this.stageId_dictText = stageId_dictText;
        }

        public int getStageId() {
            return stageId;
        }

        public void setStageId(int stageId) {
            this.stageId = stageId;
        }

        public int getOrdinal() {
            return ordinal;
        }

        public void setOrdinal(int ordinal) {
            this.ordinal = ordinal;
        }
    }
}
