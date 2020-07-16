package com.yingbao.career.http.resultbean;

/**
 * @Description:
 * @Date: 2020/5/1 11:10
 * @Auther: wanyan
 */
public class OssInfoResultBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : {"accessKeyId":"STS.NTZFRgA35FFUSFYDQpj6DKtZq","accessKeySecret":"GdCuqNRwUoQZWTTGCkznWJV4Lo8KGFr8YV4TyUEbLP1t","securityToken":"CAIS8wF1q6Ft5B2yfSjIr5fvDejTrOwU8YS+UWDooFElZrlopLHxkzz2IHlMeXJoB+0dtfswlWpQ7/YflqJ6TZNfQk3IKMJo9syabPk7l8yT1fau5Jko1beHewHKeTOZsebWZ+LmNqC/Ht6md1HDkAJq3LL+bk/Mdle5MJqP+/UFB5ZtKWveVzddA8pMLQZPsdITMWCrVcygKRn3mGHdfiEK00he8TohsPXlk5LBtkKH0wWjlLIvyt6vcsT+Xa5FJ4xiVtq55utye5fa3TRYgxowr/4u0fccp2qc5Y7CUgMJukXbKYvd6sV0MBR+fbMqxgWHcXEDDZcagAGCW4prKCv+hWN2tpCplnezNhonZKlOihBNDw3G2BS7coX+D2zkaK5ioxqBPT9xTNj57QPAQlirc0YsZhCPuyjbmN0oVXl++JlVbyWH4RqwWSU0zzRVeSKLTOe97vLRdKK0HDzb24LqDaFYE+4eVx/rQ7zpy/rZncUewVCmkqmlOw==","requestId":"6E607272-585C-44EA-A958-BDA68958BA9A"}
     * timestamp : 1588302543179
     */

    private boolean success;
    private String message;
    private int code;
    private OssTempSecretBean result;
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

    public OssTempSecretBean getResult() {
        return result;
    }

    public void setResult(OssTempSecretBean result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class OssTempSecretBean {
        /**
         * accessKeyId : STS.NTZFRgA35FFUSFYDQpj6DKtZq
         * accessKeySecret : GdCuqNRwUoQZWTTGCkznWJV4Lo8KGFr8YV4TyUEbLP1t
         * securityToken : CAIS8wF1q6Ft5B2yfSjIr5fvDejTrOwU8YS+UWDooFElZrlopLHxkzz2IHlMeXJoB+0dtfswlWpQ7/YflqJ6TZNfQk3IKMJo9syabPk7l8yT1fau5Jko1beHewHKeTOZsebWZ+LmNqC/Ht6md1HDkAJq3LL+bk/Mdle5MJqP+/UFB5ZtKWveVzddA8pMLQZPsdITMWCrVcygKRn3mGHdfiEK00he8TohsPXlk5LBtkKH0wWjlLIvyt6vcsT+Xa5FJ4xiVtq55utye5fa3TRYgxowr/4u0fccp2qc5Y7CUgMJukXbKYvd6sV0MBR+fbMqxgWHcXEDDZcagAGCW4prKCv+hWN2tpCplnezNhonZKlOihBNDw3G2BS7coX+D2zkaK5ioxqBPT9xTNj57QPAQlirc0YsZhCPuyjbmN0oVXl++JlVbyWH4RqwWSU0zzRVeSKLTOe97vLRdKK0HDzb24LqDaFYE+4eVx/rQ7zpy/rZncUewVCmkqmlOw==
         * requestId : 6E607272-585C-44EA-A958-BDA68958BA9A
         */

        private String accessKeyId;
        private String accessKeySecret;
        private String securityToken;
        private String requestId;

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }

        public String getSecurityToken() {
            return securityToken;
        }

        public void setSecurityToken(String securityToken) {
            this.securityToken = securityToken;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }
    }
}
