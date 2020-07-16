package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2020/2/15 16:17
 * @Auther: wanyandongchen
 */
public class LocationResultBean {

    /**
     * success : true
     * message : 请求成功
     * code : 200
     * result : [{"id":110000,"name":"北京市","parentId":0,"typeName":"市"},{"id":120000,"name":"天津市","parentId":0,"typeName":"市"},{"id":130000,"name":"河北省","parentId":0,"typeName":"省"},{"id":140000,"name":"山西省","parentId":0,"typeName":"省"},{"id":150000,"name":"内蒙古自治区","parentId":0,"typeName":"省"},{"id":210000,"name":"辽宁省","parentId":0,"typeName":"省"},{"id":220000,"name":"吉林省","parentId":0,"typeName":"省"},{"id":230000,"name":"黑龙江省","parentId":0,"typeName":"省"},{"id":310000,"name":"上海市","parentId":0,"typeName":"市"},{"id":320000,"name":"江苏省","parentId":0,"typeName":"省"},{"id":330000,"name":"浙江省","parentId":0,"typeName":"省"},{"id":340000,"name":"安徽省","parentId":0,"typeName":"省"},{"id":350000,"name":"福建省","parentId":0,"typeName":"省"},{"id":360000,"name":"江西省","parentId":0,"typeName":"省"},{"id":370000,"name":"山东省","parentId":0,"typeName":"省"},{"id":410000,"name":"河南省","parentId":0,"typeName":"省"},{"id":420000,"name":"湖北省","parentId":0,"typeName":"省"},{"id":430000,"name":"湖南省","parentId":0,"typeName":"省"},{"id":440000,"name":"广东省","parentId":0,"typeName":"省"},{"id":450000,"name":"广西壮族自治区","parentId":0,"typeName":"省"},{"id":460000,"name":"海南省","parentId":0,"typeName":"省"},{"id":500000,"name":"重庆市","parentId":0,"typeName":"市"},{"id":510000,"name":"四川省","parentId":0,"typeName":"省"},{"id":520000,"name":"贵州省","parentId":0,"typeName":"省"},{"id":530000,"name":"云南省","parentId":0,"typeName":"省"},{"id":540000,"name":"西藏自治区","parentId":0,"typeName":"省"},{"id":610000,"name":"陕西省","parentId":0,"typeName":"省"},{"id":620000,"name":"甘肃省","parentId":0,"typeName":"省"},{"id":630000,"name":"青海省","parentId":0,"typeName":"省"},{"id":640000,"name":"宁夏回族自治区","parentId":0,"typeName":"省"},{"id":650000,"name":"新疆维吾尔自治区","parentId":0,"typeName":"省"},{"id":710000,"name":"台湾省","parentId":0,"typeName":"省"},{"id":810000,"name":"香港特别行政区","parentId":0,"typeName":"省"},{"id":820000,"name":"澳门特别行政区","parentId":0,"typeName":"省"}]
     * timestamp : 1581754621063
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
         * id : 110000
         * name : 北京市
         * parentId : 0
         * typeName : 市
         */

        private int id;
        private String name;
        private int parentId;
        private String typeName;
        private boolean checkState = false;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public boolean isCheckState() {
            return checkState;
        }

        public void setCheckState(boolean checkState) {
            this.checkState = checkState;
        }
    }
}
