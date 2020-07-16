package com.yingbao.career.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * 负责人：完颜东臣 
 *
 * @author xsc
 *
 */
public class BitmapUtil {
	/**
	 * 根据已有图片的宽高和显示区域的宽高计算出图片需要缩放的倍数
	 * @param options
	 * @param reqWidth 显示区域的宽
	 * @param reqHeight 显示区域的高
	 * @return
	 */
	private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
		// 源图片的高度和宽度
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			// 计算出实际宽高和目标宽高的比率
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			// 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
			// 一定都会大于等于目标的宽和高。
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 从SD卡解码一张用于显示的图片
	 * @param filePath
	 * @param reqWidth 显示宽
	 * @param reqHeight 显示高
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromFile(String filePath, int reqWidth, int reqHeight) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}
	/**
	 * 图片缩放
	 *
	 * @param res
	 * @param pW
	 * @param pH
	 * @return
	 */
	public static Bitmap zoomBitmap(Resources res, int resId, int pW, int pH) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		Bitmap pBitmap = BitmapFactory.decodeResource(res, resId, options);
		int _width =pBitmap.getWidth();
		int _heigth =pBitmap.getHeight();
		// 获取缩放比
		float _ScaleW = (float) pW / _width;
		float _ScaleH = (float) pH / _heigth;

		Matrix _Matrix = new Matrix();// 创建Matrix矩阵对象

		_Matrix.setScale(_ScaleW, _ScaleH);// 设置宽高的缩放比

		return Bitmap.createBitmap(pBitmap, 0, 0, _width, _heigth, _Matrix,
				true);// 对截原图的0，0坐标到_width，_heigth的图片进行_Matrix处理
	}

	/**
	 * 缩小并裁减图片Bitmap的方法
	 *
	 * @param srcBmp   原Bitmap
	 * @param newWidth  目标宽度
	 * @param newHeight 目标高度
	 * @return Bitmap
	 */
	public static Bitmap zoom(Bitmap srcBmp, int newWidth, int newHeight) {
		if (srcBmp == null) {
			return Bitmap.createBitmap(100,100, Bitmap.Config.RGB_565);
		}
		int oldWidth = srcBmp.getWidth();
		int oldHeight = srcBmp.getHeight();
		// Log.d("GKBBhomepage","bmpPath:"+bmpPath+" | width:"+oldWidth+"
		// height: "+oldHeight);
		if (oldWidth == 0) {
			return Bitmap.createBitmap(100,100, Bitmap.Config.RGB_565);
		}

		float scaleW = (float) newWidth / oldWidth;
		float scaleH = (float) newHeight / oldHeight;
		float scale = 1;
		if (scaleW > scaleH) {
			scale = scaleW;
		} else {
			scale = scaleH;
		}

		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);
		//GKBBLogUtil.d("oldWidth:"+oldWidth+"|oldHeight:"+oldHeight);
		//GKBBLogUtil.d("source --->oldWidth:"+source.getWidth()+"|oldHeight:"+source.getHeight());
		if (oldWidth == 0) {
			return Bitmap.createBitmap(100,100, Bitmap.Config.RGB_565);
		}
		Bitmap resize = Bitmap.createBitmap(srcBmp, 0, 0, oldWidth, oldHeight, matrix, true);
		return resize;
	}


	//将bitmap转换成byte
	public static byte[] getByteFromBitmap(Bitmap bitmap){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}
	//将byte转换成bitmap
	public static Bitmap getBitmapFromByte(byte[] temp){
		if(temp != null){
			Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
			return bitmap;
		}else{
			return null;
		}
	}

}
