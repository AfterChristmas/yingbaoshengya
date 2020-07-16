package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2019/12/28 10:24
 * @Auther: wanyan
 */
public class VideoKnowPointResult {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : [{"subject":1001,"type_dictText":"","pid":3094395,"updateTime":null,"type":"NODE","subject_dictText":"","createBy":null,"knowledgePointName":"汉语拼音的朗读原则","createTime":"2020-04-01","updateBy":null,"grade":0,"hasChild":0,"id":3094396,"knowledgePointCode":"A01A01","grade_dictText":""},{"subject":1001,"type_dictText":"","pid":3094395,"updateTime":null,"type":"NODE","subject_dictText":"","createBy":null,"knowledgePointName":"汉字读音识记的方法","createTime":"2020-04-01","updateBy":null,"grade":0,"hasChild":0,"id":3094408,"knowledgePointCode":"A01A02","grade_dictText":""},{"subject":1001,"type_dictText":"","pid":3094395,"updateTime":null,"type":"NODE","subject_dictText":"","createBy":null,"knowledgePointName":"日常常见错别字  ","createTime":"2020-04-01","updateBy":null,"grade":0,"hasChild":0,"id":3094409,"knowledgePointCode":"A01A03","grade_dictText":""}]
     * timestamp : 1590197564510
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
         * subject : 1001
         * type_dictText :
         * pid : 3094395
         * updateTime : null
         * type : NODE
         * subject_dictText :
         * createBy : null
         * knowledgePointName : 汉语拼音的朗读原则
         * createTime : 2020-04-01
         * updateBy : null
         * grade : 0
         * hasChild : 0
         * id : 3094396
         * knowledgePointCode : A01A01
         * grade_dictText :
         */

        private int subject;
        private String type_dictText;
        private int pid;
        private Object updateTime;
        private String type;
        private String subject_dictText;
        private Object createBy;
        private String knowledgePointName;
        private String createTime;
        private Object updateBy;
        private int grade;
        private int hasChild;
        private int id;
        private String knowledgePointCode;
        private String grade_dictText;

        public int getSubject() {
            return subject;
        }

        public void setSubject(int subject) {
            this.subject = subject;
        }

        public String getType_dictText() {
            return type_dictText;
        }

        public void setType_dictText(String type_dictText) {
            this.type_dictText = type_dictText;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSubject_dictText() {
            return subject_dictText;
        }

        public void setSubject_dictText(String subject_dictText) {
            this.subject_dictText = subject_dictText;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getKnowledgePointName() {
            return knowledgePointName;
        }

        public void setKnowledgePointName(String knowledgePointName) {
            this.knowledgePointName = knowledgePointName;
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

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getHasChild() {
            return hasChild;
        }

        public void setHasChild(int hasChild) {
            this.hasChild = hasChild;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKnowledgePointCode() {
            return knowledgePointCode;
        }

        public void setKnowledgePointCode(String knowledgePointCode) {
            this.knowledgePointCode = knowledgePointCode;
        }

        public String getGrade_dictText() {
            return grade_dictText;
        }

        public void setGrade_dictText(String grade_dictText) {
            this.grade_dictText = grade_dictText;
        }
    }
}
