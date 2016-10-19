package com.wmsterminal.util;

import java.io.ByteArrayInputStream;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import com.wmsterminal.base.Config;
import com.wmsterminal.func.ImageLoading;
import com.wmsterminal.model.ImageZoom;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * @Package com.mngwyhouhzmb.util
 * @Title ImageUtil
 * @Description Í¼Æ¬¹¤¾ßÀà
 * @author LiuSiQing
 * @Time 2014Äê11ÔÂ25ÈÕÏÂÎç12:01:06
 */
@SuppressLint("UseSparseArrays")
public final class ImageUtil {

	private static final String TAG = ImageUtil.class.getSimpleName();

	/**
	 * TODO »ñµÃÍ¼Æ¬
	 *****************************************************************/
	/**
	 * @Title: getShortPhotoFromSDCard
	 * @Description: µÃµ½Ñ¹Ëõ¹ýµÄÍ¼Æ¬
	 * @param path
	 * @param photoName
	 * @return Jul 20, 2014 8:19:26 PM
	 */
	public static Bitmap getShortPhotoFromSDCard(String path, String photoName) {
		String filePath = path + photoName + ".jpg";
		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		/* »ñÈ¡Í¼Æ¬µÄÐý×ª½Ç¶È£¬ÓÐÐ©ÏµÍ³°ÑÅÄÕÕµÄÍ¼Æ¬Ðý×ªÁË£¬ÓÐµÄÃ»ÓÐÐý×ª */
		int degree = readPictureDegree(new File(filePath).getAbsolutePath());
		bitmapOptions.inJustDecodeBounds = true;
		// ½«±£´æÔÚ±¾µØµÄÍ¼Æ¬È¡³ö²¢ËõÐ¡ºóÏÔÊ¾ÔÚ½çÃæÉÏ
		Bitmap bitmap = BitmapFactory.decodeFile(filePath, bitmapOptions);
		int be = (int) (bitmapOptions.outHeight / (float) 195);
		if (be < 1)
			be = 1;
		bitmapOptions.inSampleSize = be;
		bitmapOptions.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(filePath, bitmapOptions);
		return rotaingBitmap(degree, bitmap);
	}

	/**
	 * @Title: decodeSmallBitmap
	 * @Description: µÃµ½bitmap
	 * @param filepath
	 * @param filewidth
	 * @return Jul 23, 2014 3:53:36 PM
	 */
	public static Bitmap decodeSmallBitmap(String filepath, int filewidth) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true; // Í¨¹ýÕâ¸öbitmap»ñÈ¡Í¼Æ¬µÄ¿íºÍ¸ß,ÏÖÔÚµÄbitmap²¢²»ÊÇÊµ¼Ê´æÔÚµÄ
		Bitmap bitmap = BitmapFactory.decodeFile(filepath, options);
		if (bitmap == null)
			DebugUtil.d(TAG, "bitmapÎª¿Õ");
		float realWidth = options.outWidth;
		float realHeight = options.outHeight;
		int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / filewidth); // ¼ÆËãËõ·Å
		// int scale = (int)realWidth / filewidth;
		if (scale <= 1)
			scale = 1;
		options.inSampleSize = scale;
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inJustDecodeBounds = false;
		options.inPurgeable = true;
		options.inInputShareable = true;
		bitmap = BitmapFactory.decodeFile(filepath, options); // ×¢ÒâÕâ´ÎÒª°Ñoptions.inJustDecodeBoundsÉèÎªfalse,Õâ´ÎÍ¼Æ¬ÊÇÒª¶ÁÈ¡³öÀ´µÄ
		return bitmap;
	}

	/**
	 * @Title: getImageByte
	 * @Description: µÃµ½Í¼Æ¬µÄbyte[]
	 * @param bitmap
	 * @param size
	 * @return Jul 23, 2014 7:09:16 PM
	 */
	public static byte[] getImageByte(Bitmap bitmap, int size) {
		if (null == bitmap)
			return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // ½«Í¼Æ¬°´100%Ñ¹ËõÎªJPEG£¬²¢¸³Öµ¸øbaos
		int options = 100 * 1024 * size / baos.toByteArray().length;
		if (options > 100)
			options = 100;
		if (baos.toByteArray().length / 1024 > size) {
			baos.reset();
			bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		return baos.toByteArray();
	}

	/**
	 * ¸ù¾ÝÂ·¾¶»ñµÃÍ»ÆÆ²¢°´480*800Ñ¹Ëõ·µ»ØbitmapÓÃÓÚÏÔÊ¾
	 * 
	 * @param imagesrc
	 * @return
	 */
	public static Bitmap getBitmapZoom480800(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 480, 800);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}

	/**
	 * @Title: getBitmap
	 * @Description: Í¨¹ýÂ·¾¶µÃµ½Í¼Æ¬
	 * @param filePath
	 *            Â·¾¶
	 * @return Jul 20, 2014 10:11:08 PM
	 */
	public static Bitmap getBitmap(String filePath) {
		return BitmapFactory.decodeFile(filePath);
	}

	/**
	 * @Title: getPhoto
	 * @Description: Í¨¹ýÂ·¾¶ºÍÃû³ÆµÃµ½Í¼Æ¬
	 * @param path
	 *            Â·¾¶
	 * @param photoName
	 *            Ãû³Æ
	 * @return Jul 20, 2014 10:11:28 PM
	 */
	public static Bitmap getBitmap(String phonePath, String photoName) {
		return getBitmap(SDCardUtil.getFilePathOfPhoto(phonePath, photoName));
	}

	/**
	 * @Title: decodeBitmap
	 * @Description: ÒÀ¾ÝÒªµÃµ½µÄÎÄ¼þ¿í¶ÈÀ´»ñµÃBitmapÎÄ¼þ
	 * @author: LiuSiQing
	 * @date: 2015-4-8 ÏÂÎç2:27:46
	 */
	public static Bitmap decodeBitmap(String filepath, int widthSize) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true; // Í¨¹ýÕâ¸öbitmap»ñÈ¡Í¼Æ¬µÄ¿íºÍ¸ß,ÏÖÔÚµÄbitmap²¢²»ÊÇÊµ¼Ê´æÔÚµÄ
		Bitmap bitmap = BitmapFactory.decodeFile(filepath, options);
		if (bitmap == null)
			DebugUtil.d(TAG, "bitmapÎª¿Õ");
		float width = options.outWidth;
		float height = options.outHeight;
		int scale = (int) ((height > width ? height : width) / widthSize); // ¼ÆËãËõ·Å
		if (scale <= 1)
			scale = 1;
		options.inSampleSize = scale;
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inJustDecodeBounds = false; // ×¢ÒâÕâ´ÎÒª°Ñoptions.inJustDecodeBoundsÉèÎªfalse,Õâ´ÎÍ¼Æ¬ÊÇÒª¶ÁÈ¡³öÀ´µÄ
		options.inPurgeable = true;
		options.inInputShareable = true;
		bitmap = BitmapFactory.decodeFile(filepath, options);
		return bitmap;
	}

	/**
	 * @Title: getZoomPhotoFromSDCard
	 * @Description: µÃµ½Ñ¹Ëõ¹ýµÄÍ¼Æ¬,°´200KÑ¹Ëõ
	 * @param path
	 * @param photoName
	 * @return Jul 20, 2014 8:19:26 PM
	 */
	public static Bitmap getPhotoZoom(String photoPath, String photoName) {
		return getPhotoZoom(SDCardUtil.getFilePathOfPhoto(photoPath, photoName), 200);
	}

	/**
	 * @Title: getZoomPhotoFromSDCard
	 * @Description: µÃµ½Ñ¹Ëõ¹ýµÄÍ¼Æ¬
	 * @param path
	 * @param photoName
	 * @return Jul 20, 2014 8:19:26 PM
	 */
	public static Bitmap getPhotoZoom(String path, int size) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		int degree = readPictureDegree(new File(path).getAbsolutePath()); // »ñÈ¡Í¼Æ¬µÄÐý×ª½Ç¶È£¬ÓÐÐ©ÏµÍ³°ÑÅÄÕÕµÄÍ¼Æ¬Ðý×ªÁË£¬ÓÐµÄÃ»ÓÐÐý×ª
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(path, options); // ½«±£´æÔÚ±¾µØµÄÍ¼Æ¬È¡³ö²¢ËõÐ¡ºóÏÔÊ¾ÔÚ½çÃæÉÏ
		int sample = (int) (options.outHeight / size);
		if (sample < 1)
			sample = 1;
		options.inSampleSize = sample;
		options.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(path, options);
		return rotaingBitmap(degree, bitmap);
	}

	/**
	 * TODO Ñ¹ËõÍ¼Æ¬
	 *****************************************************************/
	/**
	 * @Title: getBitmapZoomOne
	 * @Description: °´±ÈÀýËõÐ¡£¬Ö»Ñ¹ËõÒ»´Î
	 * @param bitmap
	 *            Í¼Æ¬
	 * @param size
	 *            ÏÞÖÆ´óÐ¡
	 * @return Jul 22, 2014 11:19:17 AM
	 */
	public static Bitmap getBitmapZoomOne(Bitmap bitmap, int size) {
		if (null == bitmap)
			return bitmap;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // ½«Í¼Æ¬°´100%Ñ¹ËõÎªJPEG£¬²¢¸³Öµ¸øbaos
		int options = 100 * 1024 * size / baos.toByteArray().length;
		if (options > 100)
			options = 100;
		if (baos.toByteArray().length / 1024 > size) {
			baos.reset();
			bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		bitmap.recycle();
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray()); // °ÑÑ¹ËõºóµÄÊý¾Ýbaos´æ·Åµ½ByteArrayInputStreamÖÐ
		return BitmapFactory.decodeStream(bais, null, null); // °ÑByteArrayInputStreamÊý¾ÝÉú³ÉÍ¼Æ¬²¢·µ»Ø
	}

	/**
	 * @Title: getBitmapZoom
	 * @Description: °´±ÈÀýËõÐ¡
	 * @param bitmap
	 *            Í¼Æ¬
	 * @param size
	 *            ÏÞÖÆ´óÐ¡
	 * @return Jul 22, 2014 11:19:36 AM
	 */
	public static Bitmap getBitmapZoom(Bitmap bitmap, int size) {
		byte[] bytes = bitmapToByteZoom(bitmap, size, false);
		if (null != bytes) {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes); // °ÑÑ¹ËõºóµÄÊý¾Ýbaos´æ·Åµ½ByteArrayInputStreamÖÐ
			return BitmapFactory.decodeStream(bais, null, null); // °ÑByteArrayInputStreamÊý¾ÝÉú³ÉÍ¼Æ¬²¢·µ»Ø
		}
		return null;
	}

	/**
	 * @Title: zoomBitmap
	 * @Description: µ÷ÕûbitmapµÄ¿íºÍ¸ß
	 * @return Sep 4, 2014 3:42:43 PM
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
		if (bitmap == null)
			return bitmap;
		int bitmapWidth = bitmap.getWidth();
		int bitmapHeight = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / bitmapWidth);
		float scaleHeight;
		if (height == 0)
			scaleHeight = scaleWidth;
		else
			scaleHeight = ((float) height / bitmapHeight);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, true);
		return newbmp;
	}

	/**
	 * @Title: zoomBitmap
	 * @Description: °´¿íµÄ±ÈÁÐµ÷ÕûÍ¼Æ¬
	 * @param drawable
	 * @param w
	 * @return Sep 9, 2014 4:34:42 PM
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int width) {
		return zoomBitmap(bitmap, width, 0);
	}

	/**
	 * @Title: zoomDrawable
	 * @Description: µ÷ÕûDrawableµÄ¿íºÍ¸ß ×ª»»³Ébitmap×ö´¦Àí
	 * @author: LiuSiQing
	 * @date: 2015-4-8 ÏÂÎç2:38:14
	 */
	public static Drawable zoomDrawable(Drawable drawable, int width, int height) {
		if (null == drawable)
			return null;
		return bitmapToDrawable(zoomBitmap(drawableToBitmap(drawable), width, height));
	}

	/**
	 * @Title: calculateInSampleSize
	 * @Description: ¼ÆËãÑ¹Ëõ±ÈÁÐ
	 * @author: LiuSiQing
	 * @date: 2015-4-8 ÏÂÎç2:38:55
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
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
			inSampleSize = heightRatio > widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * @Title: zoomBitmapBy480800
	 * @Description: ¸ù¾Ý480*800½øÐÐÑ¹Ëõ
	 * @author: LiuSiQing
	 * @date: 2015-4-8 ÏÂÎç2:39:13
	 */
	public static Bitmap zoomBitmapBy480800(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 480, 800);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}

	/**
	 * TODO ´¦ÀíÍ¼Æ¬
	 *****************************************************************/
	/**
	 * @Title: rotaingBitmap
	 * @Description: Ðý×ªÍ¼Æ¬
	 * @author: LiuSiQing
	 * @date: 2015-4-8 ÏÂÎç2:42:43
	 */
	public static Bitmap rotaingBitmap(float degree, Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	}

	/**
	 * @Title: readPictureDegree
	 * @Description: »ñµÃÐý×ªÍ¼Æ¬µÄ½Ç¶È
	 * @param path
	 * @return Sep 4, 2014 3:52:02 PM
	 */
	public static int readPictureDegree(String path) {
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				return 90;
			case ExifInterface.ORIENTATION_ROTATE_180:
				return 180;
			case ExifInterface.ORIENTATION_ROTATE_270:
				return 270;
			}
		} catch (IOException e) {
			DebugUtil.e(TAG, e.toString());
		}
		return 0;
	}

//	/**
//	 * TODO ±£´æÍ¼Æ¬
//	 *****************************************************************/
//	/**
//	 * @Title: savePhotoZoom
//	 * @Description: ËõÐ¡±£´æ
//	 * @author: LiuSiQing
//	 * @date: 2015-4-20 ÏÂÎç11:01:49
//	 */
//	public static boolean savePhotoZoom(ImageZoom zoom) {
//		if (!SDCardUtil.createFileOfPath(zoom.getFile_path())) {
//			zoom.setStatus(Config.FAIL_INT);
//			zoom.setCount(zoom.getCount() + 1);
//			return false;
//		}
//		FileOutputStream out = null;
//		try {
//			byte[] bytes = bitmapToByteZoomByPath(zoom.getFile_path());
//			File file = new File(zoom.getFile_path()); // Ä¿±êµØÖ·
//			out = new FileOutputStream(file);
//			out.write(bytes);
//			out.flush();
//			zoom.setFile_size(String.valueOf(bytes.length));
//			zoom.setStatus(Config.SUCCESS_INT);
//			return true;
//		} catch (Exception e) {
//			DebugUtil.e(TAG, e.toString());
//			zoom.setStatus(Config.FAIL_INT);
//			zoom.setCount(zoom.getCount() + 1);
//			return false;
//		} finally {
//			CloseUtil.close(out);
//		}
//	}

	/**
	 * @Title: savePhoto
	 * @Description: ±£´æÎÄ¼þ
	 * @param context
	 *            Aug 1, 2014 4:10:05 PM
	 */
	public static boolean savePhoto(Bitmap bitmap, String filePath, String photoName, Context context) {
		if (null == bitmap) // BitmapÎª¿Õ
			return false;
		if (!SDCardUtil.createFileOfPath(filePath)) // ÎÞ·¨´´½¨ÎÄ¼þ
			return false;
		String url = SDCardUtil.getFilePathOfPhoto(filePath, photoName);
		File file = new File(url);
		FileOutputStream fileOutputStream = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			bitmap.compress(CompressFormat.JPEG, 100, out);// 100%È¡³öÍ¼Æ¬·ÅÈëÊä³öÁ÷ÖÐ
			byte[] bytes = out.toByteArray();// ×ª»»
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
			sendIntent(context, url); // Ë¢ÐÂÏµÍ³Ïà²á
			return true;
		} catch (Exception e) {
			file.delete();
			DebugUtil.e(TAG, e.toString());
			return false;
		} finally {
			CloseUtil.close(fileOutputStream);
			CloseUtil.reset(out);
			CloseUtil.close(out);
			CloseUtil.recycle(bitmap);
		}
	}

	/**
	 * @Title: savePhotoToSDCard
	 * @Description: ±£´æÖÁSD¿¨
	 * @author: LiuSiQing
	 * @date: 2015-4-8 ÏÂÎç3:17:21
	 */
	public static void savePhotoToSDCard(Context context, Bitmap photoBitmap, String photoPath, String photoName) {
		if (SDCardUtil.checkSDCardIsExit())
			savePhoto(context, photoBitmap, photoPath, photoName);
	}

	/**
	 * @Title: savePhoto
	 * @Description: ±£´æÍ¼Æ¬
	 * @author: LiuSiQing
	 * @date: 2015-4-8 ÏÂÎç3:26:16
	 */
	public static boolean savePhoto(Context context, Bitmap bitmap, String photoPath, String photoName) {
		String path = SDCardUtil.filePathAddEndSprit(photoPath);
		SDCardUtil.createFileOfPath(path);
		return savePhoto(context, bitmap, path + SDCardUtil.fileNameAddEndJPG(photoName));
	}

	/**
	 * @Title: savePhoto
	 * @Description: ±£´æÍ¼Æ¬
	 * @author: LiuSiQing
	 * @date: 2015-4-8 ÏÂÎç3:26:25
	 */
	public static boolean savePhoto(Context context, Bitmap bitmap, String pathAll) {
		return saveZoomPhoto(context, bitmap, pathAll, 0);
	}
	
	/**
	 * TODO ±£´æÍ¼Æ¬
	 *****************************************************************/
	/**
	 * @Title: savePhotoZoom
	 * @Description: ËõÐ¡±£´æ
	 * @author: LiuSiQing
	 * @date: 2015-4-20 ÏÂÎç11:01:49
	 */
	public static boolean savePhotoZoom(ImageZoom zoom) {
		if (!SDCardUtil.createFileOfPath(zoom.getFile_path())) {
			zoom.setStatus(Config.FAIL_INT);
			zoom.setCount(zoom.getCount() + 1);
			return false;
		}
		FileOutputStream out = null;
		try {
			byte[] bytes = bitmapToByteZoomByPath(zoom.getFile_path());
			File file = new File(zoom.getFile_path()); // Ä¿±êµØÖ·
			out = new FileOutputStream(file);
			out.write(bytes);
			out.flush();
			zoom.setFile_size(String.valueOf(bytes.length));
			zoom.setStatus(Config.SUCCESS_INT);
			return true;
		} catch (Exception e) {
			DebugUtil.e(TAG, e.toString());
			zoom.setStatus(Config.FAIL_INT);
			zoom.setCount(zoom.getCount() + 1);
			return false;
		} finally {
			CloseUtil.close(out);
		}
	}

	/**
	 * @Title savePhotoZoom
	 * @Description Ñ¹ËõÍ¼Æ¬²¢±£´æ
	 */
	public static boolean saveZoomPhoto(Context context, Bitmap bitmap, String filePath, int size) {
		File file = new File(filePath);
		FileOutputStream fileOut = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			bitmap.compress(CompressFormat.JPEG, 100, out);
			int options = 100; // 100%
			while (0 != size && out.toByteArray().length / 1024 > size) { // Ö»ÓÐsize²»Îª0µÄÇé¿öÏÂ²Å½øÐÐÑ¹Ëõ
				if (options < 10)
					break;
				options -= 10;
				out.reset();
				bitmap.compress(Bitmap.CompressFormat.JPEG, options, out);
			}
			byte[] bytes = out.toByteArray();// ×ª»»
			fileOut = new FileOutputStream(file);
			if (bytes != null) {
				fileOut.write(bytes);
				fileOut.flush();
			}
			sendIntent(context, filePath); // Ë¢ÐÂÏµÍ³Ïà²á
			return true;
		} catch (Exception e) {
			SDCardUtil.deleteFromFile(file);
			DebugUtil.e(TAG, e.toString());
			return false;
		} finally {
			CloseUtil.close(fileOut);
			CloseUtil.reset(out);
		}
	}

	/**
	 * @Title: readAndSaveTemp
	 * @Description: ¶ÁÈ¡ºÍ±£´æÍ¼Æ¬
	 * @author: LiuSiQing
	 * @date: 2015-4-8 ÏÂÎç3:36:10
	 */
	public static String readAndSaveTemp(Context context, String photoPath, String photoName) {
		String filePath = photoPath + photoName; // È«Â·¾¶
		File file = new File(filePath);
		String fileName = DateUtil.getSysDate() + DateUtil.getSysTime();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		int degree = readPictureDegree(file.getAbsolutePath()); // »ñÈ¡Í¼Æ¬µÄÐý×ª½Ç¶È£¬ÓÐÐ©ÏµÍ³°ÑÅÄÕÕµÄÍ¼Æ¬Ðý×ªÁË£¬ÓÐµÄÃ»ÓÐÐý×ª
		Bitmap bitmap = rotaingBitmap(degree, BitmapFactory.decodeFile(filePath, options));
		if (savePhoto(context, bitmap, photoPath, fileName)) // ÏÈ±£´æµ½sd¿¨ÉÏ
			return fileName;
		return null;
	}

	/**
	 * TODO Ô²½Ç
	 *****************************************************************/
	/**
	 * @Title: getRoundedCornerBitmap
	 * @Description: µÃµ½Ô²½ÇÍ¼Æ¬
	 * @param bitmap
	 * @param roundPx
	 * @return Sep 4, 2014 3:42:25 PM
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		return getRoundedCornerBitmap(bitmap, roundPx, roundPx);
	}

	/**
	 * @Title: getRoundedCornerBitmap
	 * @Description: µÃµ½Ô²½ÇÍ¼Æ¬
	 * @param bitmap
	 * @param roundPx
	 * @param roundPy
	 * @return Sep 4, 2014 3:54:43 PM
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx, float roundPy) {
		if (null == bitmap)
			return bitmap;
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, w, h);
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPy, paint); // roundPx,roundPyÈç¹û·Ö±ðÎª¿íºÍ¸ßµÄ1/2Ê±ÎªÔ²ÐÎ
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * TODO ÍÆËÍ¸øÏµÍ³
	 *****************************************************************/
	/**
	 * @Title: sendIntent
	 * @Description: ½«urlÖÐµÄÍ¼Æ¬ÍÆËÍ¸øÍ¼¿â
	 * @param context
	 * @param url
	 *            Sep 4, 2014 3:50:54 PM
	 */
	public static void sendIntent(Context context, String url) {
		sendIntent(context, Uri.fromFile(new File(url)));
	}

	/**
	 * @Title sendIntent
	 * @Description ½«uriÖÐµÄÍ¼Æ¬ÍÆËÍ¸øÍ¼¿â
	 * @param context
	 * @param uri
	 */
	public static void sendIntent(Context context, Uri uri) {
		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		intent.setData(uri);
		context.sendBroadcast(intent);
	}

	/**
	 * TODO ×ª»»
	 *****************************************************************/
	/**
	 * @Title: bitmapToDrawable
	 * @Description: bitmap×ªDrawable
	 * @param bitmap
	 * @return Sep 4, 2014 4:36:32 PM
	 */
	@SuppressWarnings("deprecation")
	public static Drawable bitmapToDrawable(Bitmap bitmap) {
		if (null == bitmap)
			return null;
		return new BitmapDrawable(bitmap);
	}

	/**
	 * @Title: byteToDrawable
	 * @Description: byte×ªDrawable
	 * @param byteArray
	 * @return Sep 4, 2014 3:58:35 PM
	 */
	public static Drawable byteToDrawable(byte[] byteArray) {
		ByteArrayInputStream ins = null;
		if (byteArray != null)
			ins = new ByteArrayInputStream(byteArray);
		return Drawable.createFromStream(ins, null);
	}

	/**
	 * @Title: drawableToBitmap
	 * @Description: drawable×ªBitmap
	 * @param drawable
	 * @return Sep 4, 2014 4:36:44 PM
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		if (null == drawable)
			return null;
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * @Title: inputStreamToBitmap
	 * @Description: inputStream×ªBitmap
	 * @param inputStream
	 * @return
	 * @throws Exception
	 *             Sep 4, 2014 3:58:56 PM
	 */
	public static Bitmap inputStreamToBitmap(InputStream inputStream) throws Exception {
		return BitmapFactory.decodeStream(inputStream);
	}

	/**
	 * @Title: byteToBitmap
	 * @Description: byte×ªBitmap
	 * @param byteArray
	 * @return Sep 4, 2014 3:58:45 PM
	 */
	public static Bitmap byteToBitmap(byte[] byteArray) {
		if (ObjectUtil.isEmpty(byteArray))
			return null;
		return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
	}

	/**
	 * @Title: bitmapToBytes
	 * @Description: bitmap×ªBytes
	 * @param bitmap
	 * @return Sep 4, 2014 3:58:21 PM
	 */
	public static byte[] bitmapToByte(Bitmap bitmap) {
		byte[] bytes = null;
		if (bitmap != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			bytes = baos.toByteArray();
		}
		return bytes;
	}

	/**
	 * @Title: bitmapToBytes
	 * @Description: Í¨¹ýpathµÃµ½bitmap×ªBytes
	 * @param path
	 * @return Jul 22, 2014 6:40:31 PM
	 */
	public static byte[] bitmapToByte(String path) {
		byte[] bytes = null;
		Bitmap bitmap = getBitmap(path);
		if (bitmap != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			bytes = baos.toByteArray();
		}
		return bytes;
	}

	/**
	 * @Title: bitmapTobyteBySize
	 * @Description: bitmap×ªBytes¸ù¾Ýsize´óÐ¡
	 * @param bitmap
	 * @param size
	 * @return Jul 23, 2014 7:09:16 PM
	 */
	public static byte[] bitmapToByteBySize(Bitmap bitmap, int size) {
		if (null == bitmap)
			return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // ½«Í¼Æ¬°´100%Ñ¹ËõÎªJPEG£¬²¢¸³Öµ¸øbaos
		if (baos.toByteArray().length / 1024 > size) { // Èç¹û100%ÏÂ±ÈsizeÕâ½øÐÐÒ»´ÎÑ¹Ëõ
			int options = 100 * 1024 * size / baos.toByteArray().length;
			if (options < 100) { // Ö»ÓÐÐ¡ÓÚ100%Ê±²ÅÔÙ´ÎÑ¹Ëõ
				baos.reset();
				bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
			}
		}
		return baos.toByteArray();
	}

	/**
	 * @Title: drawableToBytes
	 * @Description: Drawable×ªbyte[]
	 * @param drawable
	 * @return Sep 4, 2014 3:57:46 PM
	 */
	public static byte[] drawableToByte(Drawable drawable) {
		if (null == drawable)
			return null;
		BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
		Bitmap bitmap = bitmapDrawable.getBitmap();
		return bitmapToByte(bitmap);
	}

	// /**
	// * @Title: base64ToBytes
	// * @Description: ×ª³Ébase64byte[]
	// * @param base64
	// * @return
	// * @throws IOException Sep 4, 2014 3:56:56 PM
	// */
	// public static byte[] base64ToByte(String base64) throws IOException {
	// return Base64.decode(base64);
	// }
	//
	// /**
	// * @Title: bytesTobase64
	// * @Description: ×ª³Ébase64String
	// * @param bytes
	// * @return Sep 4, 2014 3:57:24 PM
	// */
	// public static String byteToBase64(byte[] bytes) {
	// return Base64.encode(bytes);
	// }

	/**
	 * @Title: InternetUrlToBitmap
	 * @Description: »ñÈ¡ÍøÂçÍ¼Æ¬£¬ÐèÒªÉÏÍøÈ¨ÏÞ
	 * @throws Exception
	 *             Sep 11, 2014 10:28:23 AM
	 */
	public static Bitmap InternetUrlToBitmap(String url) throws Exception {
		return inputStreamToBitmap(new URL(url).openStream());
	}

	/**
	 * @Title: filePathToByteZoom
	 * @Description: ¸ù¾ÝÎÄ¼þÂ·¾¶×ª»»³Ébyte£¬»áÐý×ªÍ¼Æ¬£¬²¢Ñ¹ËõÖÁ200KÒÔÏÂ
	 * @author: LiuSiQing
	 * @date: 2015-4-20 ÏÂÎç11:14:10
	 */
	public static byte[] filePathToByteZoom(Context context, String filePath) {
		return filePathToByteZoom(context, filePath, 200);
	}

	/**
	 * @Title: filePathToByte
	 * @Description: ¸ù¾ÝÎÄ¼þÂ·¾¶×ª»»³Ébyte£¬»áÐý×ªÍ¼Æ¬£¬²¢Ñ¹Ëõ£¬×îºóÊÍ·Åbitmap
	 * @author: LiuSiQing
	 * @date: 2015-4-20 ÏÂÎç11:14:10
	 */
	public static byte[] filePathToByteZoom(Context context, String filePath, int size) {
		Bitmap bitmap = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			Uri uri = Uri.parse(filePath);
			bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
			bitmap = rotaingBitmap(readPictureDegree(filePath), bitmap);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // ½«Í¼Æ¬°´100%Ñ¹ËõÎªJPEG£¬²¢¸³Öµ¸øbaos
			if (size > 0) {
				int option = 100;
				while (baos.toByteArray().length / 1024 > size) {
					if (option <= 10) // ·ÀÖ¹ÎÞÏÞÑ­»·
						break;
					baos.reset(); // ÖØÖÃbaos¼´Çå¿Õbaos
					option -= 10; // Ã¿´Î¶¼¼õÉÙ10
					bitmap.compress(Bitmap.CompressFormat.JPEG, option, baos); // ÕâÀïÑ¹Ëõoptions%£¬°ÑÑ¹ËõºóµÄÊý¾Ý´æ·Åµ½baosÖÐ
				}
			}
			return baos.toByteArray(); // °ÑÑ¹ËõºóµÄÊý¾Ýbaos·µ»Ø
		} catch (Exception e) {
			DebugUtil.e(TAG, e.toString());
			return null;
		} finally {
			CloseUtil.reset(baos);
			CloseUtil.close(baos);
			CloseUtil.recycle(bitmap);
		}
	}

	/**
	 * @Title: bitmapToByteZoomByUri
	 * @Description: bitmap×ª»»Îªbytes£¬¸ù¾ÝPath
	 * @author: LiuSiQing
	 * @date: 2015-6-25 ÏÂÎç5:52:47
	 */
	public static byte[] bitmapToByteZoomByPathSendIntentForFile(Context context, String filePath) {
		try {
			if (!ObjectUtil.isEmpty(filePath)) {
				ImageUtil.sendIntent(context, filePath);
				return ImageUtil.bitmapToByteZoom(ImageLoading.ImageLoaderOfBitmapByZoom(filePath, Config.FLAG_FILE),
						200, false);
			}
		} catch (Exception e) {
			DebugUtil.e(TAG, e.toString());
		}
		return null;
	}

	/**
	 * @Title: bitmapToByteZoomByUri
	 * @Description: bitmap×ª»»Îªbytes£¬¸ù¾ÝPath
	 * @author: LiuSiQing
	 * @date: 2015-6-25 ÏÂÎç5:52:47
	 */
	public static byte[] bitmapToByteZoomByPath(String filePath) {
		return bitmapToByteZoomByPath(filePath, Config.FLAG_FILE);
	}

	/**
	 * @Title: bitmapToByteZoomByUri
	 * @Description: bitmap×ª»»Îªbytes£¬¸ù¾ÝPath
	 * @author: LiuSiQing
	 * @date: 2015-6-25 ÏÂÎç5:52:47
	 */
	public static byte[] bitmapToByteZoomByPath(String filePath, int flag) {
		try {
			if (!ObjectUtil.isEmpty(filePath))
				return ImageUtil.bitmapToByteZoom(ImageLoading.ImageLoaderOfBitmapByZoom(filePath, flag), 200, false);
		} catch (Exception e) {
			DebugUtil.e(TAG, e.toString());
		}
		return null;
	}

	/**
	 * @Title: bitmapToByteZoomByUri
	 * @Description: bitmap×ª»»Îªbytes£¬¸ù¾ÝUri
	 * @author: LiuSiQing
	 * @date: 2015-6-25 ÏÂÎç5:52:47
	 */
	public static byte[] bitmapToByteZoomByUri(Uri uri) {
		DataSource<CloseableReference<CloseableImage>> dataSource = null;
		CloseableReference<CloseableImage> imageReference = null;
		try {
			dataSource = Fresco.getImagePipeline().fetchDecodedImage(getImageRequest(uri), null);
			imageReference = dataSource.getResult();
			if (imageReference != null && imageReference.get() instanceof CloseableBitmap) {
				return ImageUtil.bitmapToByteZoom(((CloseableBitmap) imageReference.get()).getUnderlyingBitmap(), 200,
						false);
			}
			return null;
		} catch (Exception e) {
			DebugUtil.e(TAG, e.toString());
			return null;
		} finally {
			dataSource.close();
			CloseableReference.closeSafely(imageReference);
		}
	}

	/**
	 * @Title: bitmapToByteZoomByUri
	 * @Description: bitmap×ª»»ÎªDrawable£¬¸ù¾ÝImageRequest
	 * @author: LiuSiQing
	 * @date: 2015-6-25 ÏÂÎç5:52:47
	 */
	public static Drawable bitmaoToDrawableByImageRequest(ImageRequest request) {
		DataSource<CloseableReference<CloseableImage>> dataSource = null;
		CloseableReference<CloseableImage> imageReference = null;
		try {
			dataSource = Fresco.getImagePipeline().fetchDecodedImage(request, null);
			imageReference = dataSource.getResult();
			if (imageReference != null && imageReference.get() instanceof CloseableBitmap) {
				return bitmapToDrawable(((CloseableBitmap) imageReference.get()).getUnderlyingBitmap());
			}
			return null;
		} catch (Exception e) {
			DebugUtil.e(TAG, e.toString());
			return null;
		} finally {
			dataSource.close();
			CloseableReference.closeSafely(imageReference);
		}
	}

	/**
	 * @Title: bitmapToByteZoom
	 * @Description: Ñ¹ËõÍ¼Æ¬²¢×ª³Ébyte
	 * @author: LiuSiQing
	 * @date: 2015-6-25 ÏÂÎç3:29:57
	 */
	public static byte[] bitmapToByteZoom(Bitmap bitmap, int size, boolean recycle) {
		try {
			if (null == bitmap)
				return null;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // ½«Í¼Æ¬°´100%Ñ¹ËõÎªJPEG£¬²¢¸³Öµ¸øbaos
			int option = 100;
			while (baos.toByteArray().length / 1024 > size) {
				if (option <= 10) // ·ÀÖ¹ÎÞÏÞÑ­»·
					break;
				baos.reset(); // ÖØÖÃbaos¼´Çå¿Õbaos
				option -= 10; // Ã¿´Î¶¼¼õÉÙ10
				bitmap.compress(Bitmap.CompressFormat.JPEG, option, baos); // ÕâÀïÑ¹Ëõoptions%£¬°ÑÑ¹ËõºóµÄÊý¾Ý´æ·Åµ½baosÖÐ
			}
			return baos.toByteArray(); // °ÑÑ¹ËõºóµÄÊý¾Ýbaos·µ»Ø
		} catch (Exception e) {
			DebugUtil.e(TAG, e.toString());
			return null;
		} finally {
			if (recycle)
				CloseUtil.recycle(bitmap);
		}
	}

	/**
	 * TODO µÚÈý·½Í¼Æ¬´¦Àí
	 */
	/**
	 * @Title: getImageRequest
	 * @Description: »ñµÃImageRequest
	 * @author: LiuSiQing
	 * @date: 2015-6-25 ÏÂÎç3:58:10
	 */
	public static ImageRequest getImageRequest(Uri uri) {
		return getImageRequest(uri, 720, 1280);
	}

	/**
	 * @Title: getImageRequest
	 * @Description: »ñµÃImageRequest
	 * @author: LiuSiQing
	 * @date: 2015-7-6 ÏÂÎç4:25:24
	 */
	public static ImageRequest getImageRequest(Uri uri, int width, int height) {
		return ImageRequestBuilder.newBuilderWithSource(uri).setAutoRotateEnabled(true)
				.setLocalThumbnailPreviewsEnabled(true).setLowestPermittedRequestLevel(RequestLevel.FULL_FETCH)
				.setProgressiveRenderingEnabled(false).setResizeOptions(new ResizeOptions(width, height)).build();
	}
	
	/** 
     *  
     * @param imgPath 
     * @param bitmap 
     * @param imgFormat Í¼Æ¬¸ñÊ½ 
     * @return 
     */  
    public static String imgToBase64(String imgPath) { 
    	Bitmap bitmap = null;
        if (imgPath !=null && imgPath.length() > 0) {  
            bitmap = readBitmap(imgPath);  
        }  
        if(null == bitmap){
        	Log.e(TAG, "Í¼Æ¬×ªÎ»Í¼Ê±Îª¿Õ");
        }  
        ByteArrayOutputStream out = null;  
        try {  
            out = new ByteArrayOutputStream();  
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);  
  
            out.flush();  
            out.close();  
  
            byte[] imgBytes = out.toByteArray();  
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);  
        } catch (Exception e) {  
            return null;  
        } finally {  
            try {  
                out.flush();  
                out.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }
    
    private static Bitmap readBitmap(String imgPath) {  
        try {  
            return BitmapFactory.decodeFile(imgPath);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
  
    /** 
     *  
     * @param base64Data 
     * @param imgName 
     * @param imgFormat Í¼Æ¬¸ñÊ½ 
     */  
    public static void base64ToBitmap(String base64Data,String imgName,String imgFormat) {  
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);  
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);  
  
        File myCaptureFile = new File("/sdcard/", imgName);  
        FileOutputStream fos = null;  
        try {  
            fos = new FileOutputStream(myCaptureFile);  
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        boolean isTu = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);  
        if (isTu) {  
            // fos.notifyAll();  
            try {  
                fos.flush();  
                fos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } else {  
            try {  
                fos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
