package com.yingbao.career.http;


import com.yingbao.career.utils.CareerSPHelper;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Wang Lele
 */
public class UploadImageManager {

    private static final int DEFAULT_TIMEOUT = 120;
    private static UploadImageManager instance = new UploadImageManager();
    private Retrofit retrofit;

    private UploadImageManager() {
        try {
            //创建日志拦截器
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//设定日志级别
            OkHttpClient.Builder httpClientBuilder = getOkHttpClientBuilder()
                    .addInterceptor(httpLoggingInterceptor)//添加拦截器
                    .addInterceptor(chain -> {
                        Request oldRequest = chain.request();
                        HttpUrl.Builder urlBuilder = oldRequest.url()
                                .newBuilder()
                                .scheme(oldRequest.url().scheme())
                                .host(oldRequest.url().host());

                        Request newRequest = oldRequest.newBuilder()
                                .method(oldRequest.method(), oldRequest.body())
                                .url(urlBuilder.build())
                                .build();

                        Request.Builder builder = newRequest.newBuilder();
                        builder.addHeader("Accept", "application/json");
                        builder.addHeader("token", CareerSPHelper.getToken());
                        return chain.proceed(builder.build());
                    })
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            // 日志显示级别
            HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BASIC;
            //新建log拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor((message) -> {
            });
            loggingInterceptor.setLevel(level);
            //OkHttp进行添加拦截器loggingInterceptor
            httpClientBuilder.addInterceptor(loggingInterceptor);

            retrofit = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public synchronized <T> T getService(Class<T> tClass) {
        return retrofit.create(tClass);
    }

    public static UploadImageManager getInstance() {
        return instance;
    }

    private static OkHttpClient.Builder getOkHttpClientBuilder() throws NoSuchAlgorithmException, KeyManagementException {
        final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };

        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(sslSocketFactory);
        builder.hostnameVerifier((hostname, session) -> {
            return true;
        });

        return builder;
    }

}
