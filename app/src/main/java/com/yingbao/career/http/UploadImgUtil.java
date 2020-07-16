package com.yingbao.career.http;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.yingbao.career.common.CommonConstant;
import com.yingbao.career.http.RequestBean.UploadImagesResult;
import com.yingbao.career.http.resultbean.OssInfoResultBean;
import com.yingbao.career.utils.CareerSPHelper;
import com.yingbao.career.utils.FileTool;
import com.yingbao.career.utils.ImageHelper;
import com.yingbao.career.utils.MD5Util;
import com.yingbao.career.utils.SnowFlake;

import java.io.File;
import java.io.IOException;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


public class UploadImgUtil {

    private Context mContext;
    private String mImgPath;
    private OssInfoResultBean.OssTempSecretBean mOssTempSecretBean;
    private UploadImgListener mUploadImgListener;
    private OSS oss;
    private String mImgType;

    private Handler handler;
    private final String IMG_TEMP_DIR = CommonConstant.FileConstant.getIconTempDir();

    private final int ON_SUCCESS = 1;
    private final int ON_FAILURE = 2;

    public static final String HEAD_IMG = "headimg";
    public static final String QUES_IMG = "quesimg";

    public UploadImgUtil(Context mContext, String imgPath, OssInfoResultBean.OssTempSecretBean ossTempSecretBean,
                         String imgType, UploadImgListener uploadImgListener) {
        this.mContext = mContext;
        this.mImgPath = imgPath;
        this.mImgType = imgType;
        this.mUploadImgListener = uploadImgListener;
        this.mOssTempSecretBean = ossTempSecretBean;
        setHandler();
    }

    /**
     * 获取上传的图片
     */
    public void uploadImages() {
        File tempDir = new File(IMG_TEMP_DIR);
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }
        String newPath = IMG_TEMP_DIR + mImgPath.substring(mImgPath.lastIndexOf("/") + 1);
        File newImage = new File(newPath);
        if (!newImage.exists()) {
            try {
                newImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileTool.copyFile(mImgPath, newPath);
        compressImg(new File(newPath));
    }

    /**
     * 压缩图片
     */
    private void compressImg(File photo) {
        Luban.with(mContext)
                .load(photo)
                .ignoreBy(100)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // do nothing.
                    }

                    @Override
                    public void onSuccess(File file) {
                        uploadImg(file.getPath());
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mUploadImgListener != null) {
                            mUploadImgListener.onFailure();
                        }
                    }
                }).launch();
    }

    private void initOSS(Context mContext) {
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(mOssTempSecretBean.getAccessKeyId(),
                mOssTempSecretBean.getAccessKeySecret(), mOssTempSecretBean.getSecurityToken());
        ClientConfiguration conf = new ClientConfiguration();
        oss = new OSSClient(mContext, "https://oss-cn-beijing.aliyuncs.com", credentialProvider, conf);
    }

    private void uploadImg(String mImgPath) {
        initOSS(mContext);
        if (mUploadImgListener != null) {
            mUploadImgListener.onStart();
        }
        final int[] imgSize = ImageHelper.getImageWidthHeight(mImgPath);
        String md5pass = MD5Util.getMD5(String.valueOf(CareerSPHelper.getUserId()), 32);
        String urlObjectKey = md5pass + ".jpg";
        final String objectKey = "avatar" + File.separator +urlObjectKey;
        Log.e("mImgType", objectKey);
        Log.e("mImgPath", mImgPath);
        PutObjectRequest put = new PutObjectRequest("ybkj-image", objectKey, mImgPath);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader("x-oss-object-acl", "public-read");
        put.setMetadata(metadata);
        // 设置文件的访问权限为公共读。
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });
        oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                UploadImagesResult.DataEntity imgData = new UploadImagesResult.DataEntity();
                imgData.setImgKey("/"+urlObjectKey);
                try {
                    imgData.setImgWidth(imgSize[0]);
                    imgData.setImgHeight(imgSize[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Message msg = Message.obtain();
                msg.what = ON_SUCCESS;
                msg.obj = imgData;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    clientException.printStackTrace();
                }
                if (serviceException != null) {
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }

                Message msg = Message.obtain();
                msg.what = ON_FAILURE;
                handler.sendMessage(msg);
            }
        });
    }

    private String getObjectKey() {
        long objectKey = new SnowFlake(5, 1).nextId();
        return String.valueOf(objectKey);
    }

    private void setHandler() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case ON_SUCCESS:
                        UploadImagesResult.DataEntity imgData = (UploadImagesResult.DataEntity) msg.obj;
                        if (mUploadImgListener != null) {
                            mUploadImgListener.onSuccess(imgData);
                        }

                        break;

                    case ON_FAILURE:
                        if (mUploadImgListener != null) {
                            mUploadImgListener.onFailure();
                        }
                        delTempImgFile();
                        break;

                    default:
                        break;
                }

            }
        };
    }


    /**
     * 删除临时图片文件
     */
    private void delTempImgFile() {
        File tempDir = new File(IMG_TEMP_DIR);
        if (tempDir.exists()) {
            File[] files = tempDir.listFiles();
            for (File file : files) {
                file.delete();
            }
        }
    }

    public interface UploadImgListener {
        void onStart();

        void onSuccess(UploadImagesResult.DataEntity imgData);

        void onFailure();
    }
}
