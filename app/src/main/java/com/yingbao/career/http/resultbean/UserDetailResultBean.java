package com.yingbao.career.http.resultbean;

/**
 * @Description:
 * @Date: 2020/4/29 22:44
 * @Auther: wanyan
 */
public class UserDetailResultBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"id":67751,"userId":68111,"realName":"西西","imageUrl":null,"sex":0,"birthday":null,"phone":"","qq":null,"qqNickName":null,"examinationType":null,"schoolId":428728,"schoolName":"天津市河东区教育局","gradeId":1,"clazzId":0,"clazzName":"33班","address":"天津市-天津市河东区","inputUserCode":null,"createDate":"2020-03-11 20:55:09","updateDate":"2020-04-26 23:22:20","isBindEmail":0,"isBindPhone":1,"isCompleteInfo":0,"completeInfoDate":"2020-04-26 23:22:20","studyProvinceId":120000,"studyProvinceName":"天津市","majorCanQueryCount":0,"majorHadQueryCount":0,"isPresent":0,"isCanPresent":0,"operatePresentTime":null,"operatePresentUserName":null}
     * timestamp : 1588171438106
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
         * id : 67751
         * userId : 68111
         * realName : 西西
         * imageUrl : null
         * sex : 0
         * birthday : null
         * phone :
         * qq : null
         * qqNickName : null
         * examinationType : null
         * schoolId : 428728
         * schoolName : 天津市河东区教育局
         * gradeId : 1
         * clazzId : 0
         * clazzName : 33班
         * address : 天津市-天津市河东区
         * inputUserCode : null
         * createDate : 2020-03-11 20:55:09
         * updateDate : 2020-04-26 23:22:20
         * isBindEmail : 0
         * isBindPhone : 1
         * isCompleteInfo : 0
         * completeInfoDate : 2020-04-26 23:22:20
         * studyProvinceId : 120000
         * studyProvinceName : 天津市
         * majorCanQueryCount : 0
         * majorHadQueryCount : 0
         * isPresent : 0
         * isCanPresent : 0
         * operatePresentTime : null
         * operatePresentUserName : null
         */

        private int id;
        private int userId;
        private String realName;
        private String imageUrl;
        private int sex;
        private Object birthday;
        private String phone;
        private Object qq;
        private Object qqNickName;
        private Object examinationType;
        private int schoolId;
        private String schoolName;
        private int gradeId;
        private int clazzId;
        private String clazzName;
        private String address;
        private Object inputUserCode;
        private String createDate;
        private String updateDate;
        private int isBindEmail;
        private int isBindPhone;
        private int isCompleteInfo;
        private String completeInfoDate;
        private int studyProvinceId;
        private String studyProvinceName;
        private int majorCanQueryCount;
        private int majorHadQueryCount;
        private int isPresent;
        private int isCanPresent;
        private Object operatePresentTime;
        private Object operatePresentUserName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getQq() {
            return qq;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public Object getQqNickName() {
            return qqNickName;
        }

        public void setQqNickName(Object qqNickName) {
            this.qqNickName = qqNickName;
        }

        public Object getExaminationType() {
            return examinationType;
        }

        public void setExaminationType(Object examinationType) {
            this.examinationType = examinationType;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public int getClazzId() {
            return clazzId;
        }

        public void setClazzId(int clazzId) {
            this.clazzId = clazzId;
        }

        public String getClazzName() {
            return clazzName;
        }

        public void setClazzName(String clazzName) {
            this.clazzName = clazzName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getInputUserCode() {
            return inputUserCode;
        }

        public void setInputUserCode(Object inputUserCode) {
            this.inputUserCode = inputUserCode;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public int getIsBindEmail() {
            return isBindEmail;
        }

        public void setIsBindEmail(int isBindEmail) {
            this.isBindEmail = isBindEmail;
        }

        public int getIsBindPhone() {
            return isBindPhone;
        }

        public void setIsBindPhone(int isBindPhone) {
            this.isBindPhone = isBindPhone;
        }

        public int getIsCompleteInfo() {
            return isCompleteInfo;
        }

        public void setIsCompleteInfo(int isCompleteInfo) {
            this.isCompleteInfo = isCompleteInfo;
        }

        public String getCompleteInfoDate() {
            return completeInfoDate;
        }

        public void setCompleteInfoDate(String completeInfoDate) {
            this.completeInfoDate = completeInfoDate;
        }

        public int getStudyProvinceId() {
            return studyProvinceId;
        }

        public void setStudyProvinceId(int studyProvinceId) {
            this.studyProvinceId = studyProvinceId;
        }

        public String getStudyProvinceName() {
            return studyProvinceName;
        }

        public void setStudyProvinceName(String studyProvinceName) {
            this.studyProvinceName = studyProvinceName;
        }

        public int getMajorCanQueryCount() {
            return majorCanQueryCount;
        }

        public void setMajorCanQueryCount(int majorCanQueryCount) {
            this.majorCanQueryCount = majorCanQueryCount;
        }

        public int getMajorHadQueryCount() {
            return majorHadQueryCount;
        }

        public void setMajorHadQueryCount(int majorHadQueryCount) {
            this.majorHadQueryCount = majorHadQueryCount;
        }

        public int getIsPresent() {
            return isPresent;
        }

        public void setIsPresent(int isPresent) {
            this.isPresent = isPresent;
        }

        public int getIsCanPresent() {
            return isCanPresent;
        }

        public void setIsCanPresent(int isCanPresent) {
            this.isCanPresent = isCanPresent;
        }

        public Object getOperatePresentTime() {
            return operatePresentTime;
        }

        public void setOperatePresentTime(Object operatePresentTime) {
            this.operatePresentTime = operatePresentTime;
        }

        public Object getOperatePresentUserName() {
            return operatePresentUserName;
        }

        public void setOperatePresentUserName(Object operatePresentUserName) {
            this.operatePresentUserName = operatePresentUserName;
        }
    }
}
