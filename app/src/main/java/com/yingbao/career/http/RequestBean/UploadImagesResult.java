package com.yingbao.career.http.RequestBean;

/**
 * Created by Administrator on 2016/12/29.
 */
public class UploadImagesResult {

    /**
     * Message : 请求成功
     * BussCode : 1000
     * Data : {"ImgWidth":100,"ImgHeight":100,"ImgKey":"zyt_582a6683cc79f45278ff58bf.jpg"}
     * Code : 1200
     */
    private String Message;
    private int BussCode;
    private DataEntity Data;
    private int Code;

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public void setBussCode(int BussCode) {
        this.BussCode = BussCode;
    }

    public void setData(DataEntity Data) {
        this.Data = Data;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public int getBussCode() {
        return BussCode;
    }

    public DataEntity getData() {
        return Data;
    }

    public int getCode() {
        return Code;
    }

    public static class DataEntity {
        /**
         * ImgWidth : 100
         * ImgHeight : 100
         * ImgKey : zyt_582a6683cc79f45278ff58bf.jpg
         */
        private int ImgWidth;
        private int ImgHeight;
        private String ImgKey;

        public void setImgWidth(int ImgWidht) {
            this.ImgWidth = ImgWidht;
        }

        public void setImgHeight(int ImgHeight) {
            this.ImgHeight = ImgHeight;
        }

        public void setImgKey(String ImgKey) {
            this.ImgKey = ImgKey;
        }

        public int getImgWidth() {
            return ImgWidth;
        }

        public int getImgHeight() {
            return ImgHeight;
        }

        public String getImgKey() {
            return ImgKey;
        }
    }
}
