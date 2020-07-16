package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2020/5/16 20:12
 * @Auther: wanyan
 */
public class BannerResultBean {

    /**
     * code : 0
     * message :
     * result : [{"createBy":"","createTime":"","hrefUrl":"","id":0,"imageUrl":"","name":"","updateBy":"","updateTime":""}]
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

    public static class ResultBean {
        /**
         * createBy :
         * createTime :
         * hrefUrl :
         * id : 0
         * imageUrl :
         * name :
         * updateBy :
         * updateTime :
         */

        private String createBy;
        private String createTime;
        private String hrefUrl;
        private int id;
        private String imageUrl;
        private String name;
        private String updateBy;
        private String updateTime;

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

        public String getHrefUrl() {
            return hrefUrl;
        }

        public void setHrefUrl(String hrefUrl) {
            this.hrefUrl = hrefUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
    }
}
