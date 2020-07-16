package com.yingbao.career.http.resultbean;

/**
 * @Description:
 * @Date: 2020/5/15 11:14
 * @Auther: wanyan
 */
public class FavoriteStateResultBean {


    /**
     * code : 0
     * message :
     * result : {"courseId":0,"favoriteTime":"","id":0,"userId":0}
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
         * courseId : 0
         * favoriteTime :
         * id : 0
         * userId : 0
         */

        private int courseId;
        private String favoriteTime;
        private int id;
        private int userId;

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
    }
}
