package com.yingbao.career.http;

import com.yingbao.career.http.RequestBean.UploadSearchImageResult;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author Wang Lele
 */
public interface UploadImageService {
    String BASE_URL = "http://jokerapi.keepfly.cn";

    /**
     * 上传图片
     */
    @Multipart
    @POST("/image/upload")
    Call<UploadSearchImageResult> uploadSearchQuesImage(@Part MultipartBody.Part image);
}
