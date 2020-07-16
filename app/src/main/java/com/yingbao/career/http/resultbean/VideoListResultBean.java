package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2020/5/12 17:29
 * @Auther: wanyan
 */
public class VideoListResultBean {

    /**
     * code : 0
     * message :
     * result : {"courseCover":"","courseId":0,"courseName":"","courseStartTime":"","courseTeacherId":0,"courseTeacherIntroduction":"","courseTeacherName":"","gradeId":0,"specialId":0,"subjectId":0,"videos":[{"courseId":0,"createBy":"","createTime":"","id":0,"knowledgePointId":0,"updateBy":"","updateTime":"","videoCover":"","videoDownloadUrl":"","videoDuration":0,"videoId":"","videoMd5":"","videoName":"","videoPlayUrl":"","videoRemark":"","videoSize":0,"videoTag":""}]}
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
         * courseCover :
         * courseId : 0
         * courseName :
         * courseStartTime :
         * courseTeacherId : 0
         * courseTeacherIntroduction :
         * courseTeacherName :
         * gradeId : 0
         * specialId : 0
         * subjectId : 0
         * videos : [{"courseId":0,"createBy":"","createTime":"","id":0,"knowledgePointId":0,"updateBy":"","updateTime":"","videoCover":"","videoDownloadUrl":"","videoDuration":0,"videoId":"","videoMd5":"","videoName":"","videoPlayUrl":"","videoRemark":"","videoSize":0,"videoTag":""}]
         */

        private String courseCover;
        private int courseId;
        private String courseName;
        private String courseStartTime;
        private int courseTeacherId;
        private String courseTeacherIntroduction;
        private String courseTeacherName;
        private int gradeId;
        private int specialId;
        private int subjectId;
        private List<VideosBean> videos;

        public String getCourseCover() {
            return courseCover;
        }

        public void setCourseCover(String courseCover) {
            this.courseCover = courseCover;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseStartTime() {
            return courseStartTime;
        }

        public void setCourseStartTime(String courseStartTime) {
            this.courseStartTime = courseStartTime;
        }

        public int getCourseTeacherId() {
            return courseTeacherId;
        }

        public void setCourseTeacherId(int courseTeacherId) {
            this.courseTeacherId = courseTeacherId;
        }

        public String getCourseTeacherIntroduction() {
            return courseTeacherIntroduction;
        }

        public void setCourseTeacherIntroduction(String courseTeacherIntroduction) {
            this.courseTeacherIntroduction = courseTeacherIntroduction;
        }

        public String getCourseTeacherName() {
            return courseTeacherName;
        }

        public void setCourseTeacherName(String courseTeacherName) {
            this.courseTeacherName = courseTeacherName;
        }

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public int getSpecialId() {
            return specialId;
        }

        public void setSpecialId(int specialId) {
            this.specialId = specialId;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class VideosBean {
            /**
             * courseId : 0
             * createBy :
             * createTime :
             * id : 0
             * knowledgePointId : 0
             * updateBy :
             * updateTime :
             * videoCover :
             * videoDownloadUrl :
             * videoDuration : 0
             * videoId :
             * videoMd5 :
             * videoName :
             * videoPlayUrl :
             * videoRemark :
             * videoSize : 0
             * videoTag :
             */

            private int courseId;
            private String createBy;
            private String createTime;
            private int id;
            private int knowledgePointId;
            private String updateBy;
            private String updateTime;
            private String videoCover;
            private String videoDownloadUrl;
            private int videoDuration;
            private String videoId;
            private String videoMd5;
            private String videoName;
            private String videoPlayUrl;
            private String videoRemark;
            private int videoSize;
            private String videoTag;
            private boolean isPlaying;

            public boolean isPlaying() {
                return isPlaying;
            }

            public void setPlaying(boolean playing) {
                isPlaying = playing;
            }

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

            public int getKnowledgePointId() {
                return knowledgePointId;
            }

            public void setKnowledgePointId(int knowledgePointId) {
                this.knowledgePointId = knowledgePointId;
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

            public String getVideoCover() {
                return videoCover;
            }

            public void setVideoCover(String videoCover) {
                this.videoCover = videoCover;
            }

            public String getVideoDownloadUrl() {
                return videoDownloadUrl;
            }

            public void setVideoDownloadUrl(String videoDownloadUrl) {
                this.videoDownloadUrl = videoDownloadUrl;
            }

            public int getVideoDuration() {
                return videoDuration;
            }

            public void setVideoDuration(int videoDuration) {
                this.videoDuration = videoDuration;
            }

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }

            public String getVideoMd5() {
                return videoMd5;
            }

            public void setVideoMd5(String videoMd5) {
                this.videoMd5 = videoMd5;
            }

            public String getVideoName() {
                return videoName;
            }

            public void setVideoName(String videoName) {
                this.videoName = videoName;
            }

            public String getVideoPlayUrl() {
                return videoPlayUrl;
            }

            public void setVideoPlayUrl(String videoPlayUrl) {
                this.videoPlayUrl = videoPlayUrl;
            }

            public String getVideoRemark() {
                return videoRemark;
            }

            public void setVideoRemark(String videoRemark) {
                this.videoRemark = videoRemark;
            }

            public int getVideoSize() {
                return videoSize;
            }

            public void setVideoSize(int videoSize) {
                this.videoSize = videoSize;
            }

            public String getVideoTag() {
                return videoTag;
            }

            public void setVideoTag(String videoTag) {
                this.videoTag = videoTag;
            }
        }
    }
}
