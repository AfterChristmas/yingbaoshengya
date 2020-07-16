package com.yingbao.career.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by NO on 2017/9/14.
 */

public class Utility {
    public  static void checkBlueboothPermission(Activity context, String[] requestPermissions, Callback callback){
        boolean hasPermissionDismiss=false;//有权限没有通过
        if (Build.VERSION.SDK_INT >= 23) {
            //校验是否已具有模糊定位权限
            for (int i = 0; i < requestPermissions.length; i++) {
                if (ContextCompat.checkSelfPermission(context, requestPermissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context, requestPermissions, 100);
                    hasPermissionDismiss =true;
                }
            }
            if (!hasPermissionDismiss)
            callback.permit();
        } else {
            //系统不高于6.0直接执行
            callback.permit();
        }
    }
    public interface Callback {
        /**
         * API>=23 允许权限
         */
        void permit();


//        /**
//         * API<23 无需授予权限
//         */
//        void pass();
    }
    public static Bitmap Tobitmap(Bitmap bitmap, int targetWidth, int height) {

        Bitmap target = Bitmap.createBitmap(targetWidth, height, bitmap.getConfig());
        Canvas targetCanvas = new Canvas(target);
        targetCanvas.drawBitmap(bitmap, null, new Rect(0, 0, target.getWidth(), target.getHeight()), null);

        int width = target.getWidth();
        int heigit = target.getHeight();
        int printImageLength = (int) (targetWidth*2.2);
        if (heigit > targetWidth * 2.2) {
            // 计算缩放比例
            float scaleHeight = ((float) printImageLength) / heigit;
            // 取得想要缩放的matrix参数
            Matrix matrix = new Matrix();
            matrix.postScale(scaleHeight, scaleHeight);
            // 得到新的图片
            target = Bitmap.createBitmap(target, 0, 0, width, heigit, matrix, true);

            Bitmap.Config c = Bitmap.Config.RGB_565;
            Bitmap bgwhite = Bitmap.createBitmap(targetWidth, printImageLength, c);
            Canvas canvas = new Canvas(bgwhite);
            canvas.drawColor(Color.WHITE);
            int left = (384 - target.getWidth()) / 2;
            canvas.drawBitmap(target, left, 0, new Paint());
            canvas.save();
            target = bgwhite;

        }
        return target;
    }
    public static int getHeight(int width,int pageWidthPoint,int pageHeightPoint){
        double bili=width/(double)pageWidthPoint;
        return  (int) (pageHeightPoint*bili);
    }
    public static String getStr(byte[] data, int start, int end, int tag){
        if (start>data.length||end>data.length||start>end)
            return "";
        int j=0;
        byte[] bytes = new byte[end - start + 1];
        for (int i = 0; i < bytes.length; i++) {
            if (tag==data[start-1+i]){
                j=i;
                break;
            }
            bytes[i]=data[start-1+i];
        }
        if (j==0){
            j= bytes.length;
        }
//        Log.d("Print","bytes:"+ HPRTPrinterHelper.bytetohex(bytes)+"j:"+j);
        byte[] bytes1 = new byte[j];
        for (int i = 0; i < bytes1.length; i++) {
            bytes1[i]=bytes[i];
        }
//        Log.d("Print","bytes1:"+ HPRTPrinterHelper.bytetohex(bytes1));
        return new String(bytes1);
    }
}
