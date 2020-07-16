package com.yingbao.career.http.resultbean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @Description:
 * @Date: 2020/5/23 15:48
 * @Auther: wanyan
 */
public class VideoRecordTimeLine {

    /**
     * code : 0
     * message :
     * result : [{"courseId":0,"knowledgePointId":0,"percentage":0,"playDate":"","polyVideoId":"","videoCover":"","videoId":0,"videoName":""}]
     * success : true
     * timestamp : 0
     */

    private int code;
    private String message;
    private boolean success;
    private int timestamp;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements MultiItemEntity {
        /**
         * courseId : 0
         * knowledgePointId : 0
         * percentage : 0
         * playDate :
         * polyVideoId :
         * videoCover :
         * videoId : 0
         * videoName :
         */
        public static final int VIDEO = 0;
        public static final int TIME = 1;
        public static final int VIDEO_SPAN_SIZE = 1;
        public static final int TIME_SPAN_SIZE = 2;
        private int courseId;
        private int knowledgePointId;
        private String playDate;
        private String polyVideoId;
        private String videoCover;
        private int videoId;
        private String videoName;
        private int itemType;
        //一行有几个布局
        private int spanSize;

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getKnowledgePointId() {
            return knowledgePointId;
        }

        public void setKnowledgePointId(int knowledgePointId) {
            this.knowledgePointId = knowledgePointId;
        }

        public String getPlayDate() {
            return playDate;
        }

        public void setPlayDate(String playDate) {
            this.playDate = playDate;
        }

        public String getPolyVideoId() {
            return polyVideoId;
        }

        public void setPolyVideoId(String polyVideoId) {
            this.polyVideoId = polyVideoId;
        }

        public String getVideoCover() {
            return videoCover;
        }

        public void setVideoCover(String videoCover) {
            this.videoCover = videoCover;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public String getVideoName() {
            return videoName;
        }

        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public int getSpanSize() {
            return spanSize;
        }

        public void setSpanSize(int spanSize) {
            this.spanSize = spanSize;
        }
    }
}
