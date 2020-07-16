package com.yingbao.career.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public final class ImageHelper {


    /**
     * 尺寸压缩
     *
     * @param filePath
     * @return
     */
    private static Bitmap matrixCompress(String filePath) {
        Bitmap originalBitmap = BitmapFactory.decodeFile(filePath);
        float scale = getScale(originalBitmap);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        Bitmap newBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(),
                originalBitmap.getHeight(), matrix, true);
        return newBitmap;
    }

    /**
     * 获取图片尺寸压缩比例
     *
     * @param bitmap
     * @return
     */
    private static float getScale(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scale = 1.0f;
        if (height >= width) {
            if (height > 1000) {
                scale = 1000.0f / (float) height;
            }
        } else {
            if (width > 1000) {
                scale = 1000.0f / (float) width;
            }
        }
        return scale;
    }

    public static int[] getImageWidthHeight(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        int imgWidth = options.outWidth;
        int imgHeight = options.outHeight;
        return new int[]{imgWidth, imgHeight};
    }

    public static boolean isLargeSize(File file) {
        long fileSize = getFileSize(file);
        //XyLog.e("file_size", String.valueOf(fileSize));
        return fileSize > 5 * 1024 * 1024;
    }

    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     */
    private static long getFileSize(File file) {
        long size = 0;
        try {
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                size = fis.available();
            }
        } catch (Exception e) {
            size = 0;
        }
        return size;
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    /**
     * 根据路径获得图片并压缩返回bitmap用于显示
     *
     * @param filePath
     * @return
     */

    public static void getSmallBitmapFile(String filePath, Context context) {
        ImageHelper.isLargeSize(new File(filePath));
        Bitmap bitmap = matrixCompress(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageHelper.isLargeSize(new File(filePath));
    }

    /***
     * 根据资源文件获取Bitmap
     *
     * @param fileName
     * @param screenWidth
     * @param screenHight
     * @return
     */
    public static Bitmap ReadBitmapByfileName(String fileName, int screenWidth,
                                              int screenHight) {
        Bitmap retBmp = null;

        File file = new File(fileName);
        try {

            if (file.exists()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Config.ARGB_8888;
                options.inInputShareable = true;
                options.inPurgeable = true;
                FileInputStream fis = new FileInputStream(file);
                retBmp = BitmapFactory.decodeStream(fis, null, options);
                fis.close();
                fis = null;
            }
        } catch (Exception ex) {
        }
        return getBitmap(retBmp, screenWidth, screenHight);
    }

    /***
     * 等比例压缩图片
     *
     * @param bitmap
     * @param screenWidth
     * @param screenHight
     * @return
     */
    public static Bitmap getBitmap(Bitmap bitmap, int screenWidth,
                                   int screenHight) {
        if (bitmap == null) {
            return null;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scale = (float) screenWidth / w;
        float scale2 = (float) screenHight / h;

        // scale = scale < scale2 ? scale : scale2;

        // 保证图片不变形.
        matrix.postScale(scale, scale);
        // w,h是原图的属性.
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    /**
     * 读取照片exif信息中的旋转角度
     *
     * @param path 照片路径
     * @return 角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片，使图片保持正确的方向。
     *
     * @param bitmap  原始图片
     * @param degrees 原始图片的角度
     * @return Bitmap 旋转后的图片
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        if (degrees == 0 || null == bitmap) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return bmp;
    }

    /**
     * 保存方法
     */
    public static void saveBitmap(Bitmap bmp, String path) {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
