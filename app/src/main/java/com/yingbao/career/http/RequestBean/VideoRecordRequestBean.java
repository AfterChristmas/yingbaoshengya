package com.yingbao.career.http.RequestBean;

/**
 * @Description:
 * @Date: 2020/5/7 20:45
 * @Auther: wanyan
 */
public class VideoRecordRequestBean {

    /**
     * courseId : 0
     * createBy :
     * createTime :
     * id : 0
     * playTime :
     * playTotalCount : 0
     * playTotalTime : 0
     * subjectId : 0
     * updateBy :
     * updateTime :
     * userId : 0
     * videoId : 0
     */

    private int courseId;
    private String createBy;
    private String createTime;
    private int id;
    private String playTime;
    private int playTotalCount;
    private int playTotalTime;
    private int subjectId;
    private String updateBy;
    private String updateTime;
    private int userId;
    private int videoId;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public int getPlayTotalCount() {
        return playTotalCount;
    }

    public void setPlayTotalCount(int playTotalCount) {
        this.playTotalCount = playTotalCount;
    }

    public int getPlayTotalTime() {
        return playTotalTime;
    }

    public void setPlayTotalTime(int playTotalTime) {
        this.playTotalTime = playTotalTime;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "{\"createBy\" : " + (createBy == null ? null : "\"" + createBy + "\"") + ",\"createTime\" : " + (createTime == null ? null : "\"" + createTime + "\"") + ",\"id\" : " + id + ",\"playTime\" : " + (playTime == null ? null : "\"" + playTime + "\"") + ",\"playTotalCount\" : " + playTotalCount + ",\"playTotalTime\" : " + playTotalTime + ",\"subjectId\" : " + subjectId + ",\"updateBy\" : " + (updateBy == null ? null : "\"" + updateBy + "\"") + ",\"updateTime\" : " + (updateTime == null ? null : "\"" + updateTime + "\"") + ",\"userId\" : " + userId + ",\"videoId\" : " + videoId + "}";
    }
}
