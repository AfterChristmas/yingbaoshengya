package com.yingbao.career.http;

import android.annotation.SuppressLint;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class SSLUtils {

    /**
     * filedownload 使用okhttp作为下载引擎忽略掉SSL验证, 添加证书不好使
     *
     * @return
     */
    public static OkHttpClient.Builder createOkHttp() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(20_000, TimeUnit.SECONDS);
        builder.sslSocketFactory(SSLUtils.createSSLSocketFactory(), xtm);
        builder.hostnameVerifier(new HostnameVerifier() {
            @SuppressLint("BadHostnameVerifier")
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        return builder;
    }

    @SuppressLint("TrulyRandom")
    public static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{xtm}, new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    private static X509TrustManager xtm = new X509TrustManager() {
        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

}
