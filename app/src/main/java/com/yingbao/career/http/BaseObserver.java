package com.yingbao.career.http;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.yingbao.career.common.MyApplication;
import com.yingbao.career.utils.ToastUtil;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @Description:
 * @Date: 2019/10/23 19:46
 * @Auther: wanyan
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {
    private static final String TAG = "BaseObserver";
    private Context mContext;
    //对应HTTP的状态码
    private static final int UNAU = 402;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    //出错提示
    private final String networkMsg = "网络错误";
    private final String cookieOutMsg = "登录过期，请重新登录";
    private final String parseMsg = "服务器数据解析错误";
    private final String unknownMsg = "未知错误";
    private final String connectMsg = "连接服务器错误,请检查网络";
    private final String connectOutMsg = "连接服务器超时,请检查网络";

    protected  BaseObserver(Context context){
        this.mContext = context;
    }
    protected BaseObserver(){}

    @Override
    public void onNext(BaseResponse<T> tBaseEntity) {
        Log.e(TAG, "onNext: "+tBaseEntity.toString() );
        if (tBaseEntity.isSuccess()){
            try {
                onSuccees(tBaseEntity.getResult());
            } catch (Exception e) {
                e.printStackTrace();
                onError(new Throwable(parseMsg));
            }
        }else {
            onError(new Throwable(tBaseEntity.getMessage()));
        }
    }

    /**
     * 返回成功
     *
     * @param t
     */
    protected abstract void onSuccees(T t) ;

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        Throwable throwable = e;
        //获取最根源的异常
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }
        String error = null;
        if (e instanceof ConnectException) {
            Log.d(TAG, "ConnectException");
            error = connectMsg;
        } else if (e instanceof HttpException) {             //HTTP错误
            Log.d(TAG, "HttpException");
            HttpException httpException = (HttpException) e;
            error=e.getLocalizedMessage()+"";
        } else if (e instanceof ApiException) {    //服务器返回的错误
            ApiException apiException = (ApiException) e;
            switch (apiException.getErrorCode()) {
                case "10007":
                    error = parseMsg;
                    break;
                case "10008":
                    error = cookieOutMsg;
                    break;
                case "11111":
                    error = cookieOutMsg;
                    break;
                default:
                    error = e.getLocalizedMessage();
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            Log.d(TAG, "JsonParseException JSONException");
            error = parseMsg;
        } else if (e instanceof IOException) {
            Log.d(TAG, "IOException");
            if (e instanceof SocketTimeoutException) {
                Log.d(TAG, "SocketTimeoutException");
                error = connectOutMsg;
            } else {
                if ("Canceled".equals(e.getMessage()) || "Socket closed".equals(e.getMessage()))
                    return;
                else
                    error = connectMsg;
            }
        } else {
            error = e.getLocalizedMessage();
        }
        ToastUtil.showShort(MyApplication.context, error);
        onFailure(error, false);
    }
    /**
     * 返回失败
     *
     * @param error
     * @param isNetWorkError 是否是网络错误
     */
    protected abstract void onFailure(String error, boolean isNetWorkError) ;

    @Override
    public void onSubscribe(Disposable d) {

    }
}
