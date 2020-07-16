/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yingbao.career.http;

import java.io.Serializable;

/**
 * @author qinzishuai
 * 描述：
 * 创建日期：2019/7/12
 */
public class BaseResponse<T> implements Serializable {
    /**
     * {
     * "code": 0,
     * "message": "",
     * "result": {},
     * "success": true,
     * "timestamp": 0
     * }
     */
    private static int SUCCESS_CODE = 0;//成功的code
    private static final long serialVersionUID = 5213230387175987834L;
    /**
     * code : 0
     * message :
     * result : {}
     * success : true
     * timestamp : 0
     */

    private int code;
    private String message;
    private T result;
    private boolean success;
    private long timestamp;

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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", success=" + success +
                ", timestamp=" + timestamp +
                '}';
    }
}
