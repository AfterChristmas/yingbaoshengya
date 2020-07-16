package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2020/5/12 17:41
 * @Auther: wanyan
 */
public class FavoriteListResult {
    /**
     * code : 0
     * message :
     * result : {"current":0,"pages":0,"records":[{"courseId":0,"favoriteTime":"","video":{"courseId":0,"createBy":"","createTime":"","id":0,"knowledgePointId":0,"updateBy":"","updateTime":"","videoCover":"","videoDownloadUrl":"","videoDuration":0,"videoId":"","videoMd5":"","videoName":"","videoPlayUrl":"","videoRemark":"","videoSize":0,"videoTag":""}}],"searchCount":true,"size":0,"total":0}
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
         * current : 0
         * pages : 0
         * records : [{"courseId":0,"favoriteTime":"","video":{"courseId":0,"createBy":"","createTime":"","id":0,"knowledgePointId":0,"updateBy":"","updateTime":"","videoCover":"","videoDownloadUrl":"","videoDuration":0,"videoId":"","videoMd5":"","videoName":"","videoPlayUrl":"","videoRemark":"","videoSize":0,"videoTag":""}}]
         * searchCount : true
         * size : 0
         * total : 0
         */

        private int current;
        private int pages;
        private boolean searchCount;
        private int size;
        private int total;
        private List<RecordsBean> records;

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * courseId : 0
             * favoriteTime :
             * video : {"courseId":0,"createBy":"","createTime":"","id":0,"knowledgePointId":0,"updateBy":"","updateTime":"","videoCover":"","videoDownloadUrl":"","videoDuration":0,"videoId":"","videoMd5":"","videoName":"","videoPlayUrl":"","videoRemark":"","videoSize":0,"videoTag":""}
             */

            private int courseId;
            private String favoriteTime;
            private VideoBean video;

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getFavoriteTime() {
                return favoriteTime;
            }

            public void setFavoriteTime(String favoriteTime) {
                this.favoriteTime = favoriteTime;
            }

            public VideoBean getVideo() {
                return video;
            }

            public void setVideo(VideoBean video) {
                this.video = video;
            }

            public static class VideoBean {
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
}
