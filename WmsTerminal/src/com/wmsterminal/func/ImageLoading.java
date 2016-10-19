package com.wmsterminal.func;

import android.graphics.Bitmap;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import com.wmsterminal.R;
import com.wmsterminal.base.Config;
import com.wmsterminal.util.DebugUtil;
import com.wmsterminal.util.ImageUtil;
import com.wmsterminal.util.ObjectUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

/**
 * @author light
 * @File ImageLoading.java
 * @Package com.crowdfunding.function
 * @Time 2015Äê9ÔÂ6ÈÕÏÂÎç2:06:16
 * @Description Í¼Æ¬¼ÓÔØ
 */
public class ImageLoading {

	/**
	 * @Title: getOptions
	 * @Description: »ñµÃÅäÖÃ
	 * @return Sep 27, 2014 10:44:31 PM
	 */
	public static DisplayImageOptions getOptions() {
		return getOptions(R.drawable.default_pic);
	}

	/**
	 * @Title: getOptions
	 * @Description: »ñµÃÅäÖÃ
	 * @return Sep 27, 2014 10:44:31 PM
	 */
	public static DisplayImageOptions getOptions(int resid) {
		return getOptions(resid, resid);
	}

	/**
	 * @Title: getOptions
	 * @Description: »ñµÃÅäÖÃ
	 * @return Sep 27, 2014 10:44:31 PM
	 */
	public static DisplayImageOptions getOptions(int loadResId, int emptyResId) {
		return new DisplayImageOptions.Builder()
				.showImageForEmptyUri(emptyResId)
				.showImageOnLoading(loadResId)
				.cacheInMemory(true)
				.cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();
	}

	/**
	 * @Title: ImageLoader
	 * @Description: ´ÓÍøÂçÉÏÍ¼Æ¬¼ÓÔØ
	 * @param uri
	 * @param imageView Sep 28, 2014 10:16:55 AM
	 */
	public static void ImageLoader(String uri, ImageView imageView) {
		ImageLoader(uri, imageView, Config.FLAG_SERVER);
	}

	/**
	 * @Title: ImageLoader
	 * @Description: Í¼Æ¬¼ÓÔØ
	 * @param uri
	 * @param imageAware Sep 28, 2014 10:16:55 AM
	 */
	public static void ImageLoader(String uri, ImageView imageView, int flag) {
		ImageLoader(uri, imageView, flag, getOptions());
	}

	/**
	 * @Title: ImageLoader
	 * @Description: ´ÓÍøÂçÉÏÍ¼Æ¬¼ÓÔØ
	 * @param uri
	 * @param imageView Sep 28, 2014 10:16:55 AM
	 */
	public static void ImageLoader(String uri, ImageView imageView, DisplayImageOptions options) {
		ImageLoader(uri, imageView, Config.FLAG_SERVER, options);
	}

	/**
	 * @Title: ofUriByPrefix
	 * @Description: ÎªurlÔö¼ÓÇ°×º
	 * @author: LiuSiQing
	 * @date: 2015-4-1 ÉÏÎç11:38:12
	 */
	public static String ofUriByPrefix(String uri, int flag) {
		if( ObjectUtil.isEmpty(uri) || ofUri(uri) )
			return uri;
		String url;
		switch(flag) {
			case Config.FLAG_HTTP:
				url = Scheme.HTTP.wrap(uri);
				break;
			case Config.FLAG_HTTPS:
				url = Scheme.HTTPS.wrap(uri);
				break;
			case Config.FLAG_FILE:
				url = Scheme.FILE.wrap(uri);
				break;
			case Config.FLAG_CONTENT:
				url = Scheme.CONTENT.wrap(uri);
				break;
			case Config.FLAG_ASSETS:
				url = Scheme.ASSETS.wrap(uri);
				break;
			case Config.FLAG_DRAWABLE:
				url = Scheme.DRAWABLE.wrap(uri);
				break;
			default:
				url = Config.BASE_URL + uri;
				break;
		}
		return url;
	}

	/**
	 * @Title: ofUri
	 * @Description: ofUri ¼ì²éUriÇ°×º
	 * @author: LiuSiQing
	 * @date: 2015-7-20 ÏÂÎç1:44:48
	 */
	public static boolean ofUri(String uri) {
		return Scheme.ofUri(uri) != Scheme.UNKNOWN;
	}

	/**
	 * @Title: ImageLoader
	 * @Description: Í¼Æ¬¼ÓÔØ
	 * @param uri
	 * @param imageAware Sep 28, 2014 10:16:55 AM
	 */
	public static void ImageLoader(String uri, ImageView imageView, int flag, DisplayImageOptions options) {
		String url = ofUriByPrefix(uri, flag);
		if( ObjectUtil.isEmpty(url) ) {
			DebugUtil.e("ImageLoader", "the path is empty!");
			return;
		}
		if( imageView instanceof SimpleDraweeView )
			ImageLoader.getInstance().displayImage(url, new FrescoViewAware((SimpleDraweeView)imageView), options);
		else
			ImageLoader.getInstance().displayImage(url, imageView, options);
	}

	/**
	 * TODO Drawable
	 * @Title: ImageLoader
	 * @Description: Í¼Æ¬¼ÓÔØ
	 * @author: LiuSiQing
	 * @date: 2015-7-20 ÏÂÎç1:55:07
	 */
	public static Drawable ImageLoaderOfDrawable(String uri, int flag, DisplayImageOptions options) {
		return ImageUtil.bitmapToDrawable(ImageLoaderOfBitmap(uri, flag, options));
	}

	/**
	 * @Title: ImageLoader
	 * @Description: Í¼Æ¬¼ÓÔØ
	 * @author: LiuSiQing
	 * @date: 2015-7-20 ÏÂÎç1:55:47
	 */
	public static Drawable ImageLoaderOfDrawable(String uri, int flag) {
		return ImageLoaderOfDrawable(uri, flag, getOptions());
	}

	/**
	 * @Title: ImageLoader
	 * @Description: Í¼Æ¬¼ÓÔØ
	 * @author: LiuSiQing
	 * @date: 2015-7-20 ÏÂÎç1:55:47
	 */
	public static Drawable ImageLoaderOfDrawable(String uri) {
		return ImageLoaderOfDrawable(uri, Config.FLAG_SERVER);
	}

	/**
	 * TODO Bitmap
	 * @Title: ImageLoader
	 * @Description: Í¼Æ¬¼ÓÔØ
	 * @author: LiuSiQing
	 * @date: 2015-7-20 ÏÂÎç1:55:47
	 */
	public static Bitmap ImageLoaderOfBitmap(String uri, int flag, DisplayImageOptions options) {
		String url = ofUriByPrefix(uri, flag);
		if( ObjectUtil.isEmpty(url) )
			return null;
		return ImageLoader.getInstance().loadImageSync(url, options);
	}

	/**
	 * TODO Bitmap
	 * @Title: ImageLoader
	 * @Description: Í¼Æ¬¼ÓÔØ
	 * @author: LiuSiQing
	 * @date: 2015-7-20 ÏÂÎç1:55:47
	 */
	public static Bitmap ImageLoaderOfBitmapByZoom(String uri, int flag) {
		String url = ofUriByPrefix(uri, flag);
		if( ObjectUtil.isEmpty(url) )
			return null;
		return ImageLoader.getInstance().loadImageSync(url, new ImageSize(720, 1280));
	}

	/**
	 * @Title: ImageLoader
	 * @Description: Í¼Æ¬¼ÓÔØ
	 * @author: LiuSiQing
	 * @date: 2015-7-20 ÏÂÎç1:55:47
	 */
	public static Bitmap ImageLoaderOfBitmap(String uri, int flag) {
		return ImageLoaderOfBitmap(uri, flag, getOptions());
	}

	/**
	 * TODO Byte
	 * @Title: ImageLoader
	 * @Description: Í¼Æ¬¼ÓÔØ
	 * @author: LiuSiQing
	 * @date: 2015-7-20 ÏÂÎç1:55:47
	 */
	public static byte[] ImageLoaderOfByte(String uri, int flag, DisplayImageOptions options) {
		return ImageUtil.bitmapToByte(ImageLoaderOfBitmap(uri, flag, options));
	}

	/**
	 * @Title: ImageLoader
	 * @Description: Í¼Æ¬¼ÓÔØ
	 * @author: LiuSiQing
	 * @date: 2015-7-20 ÏÂÎç1:55:47
	 */
	public static byte[] ImageLoaderOfByte(String uri, int flag) {
		return ImageLoaderOfByte(uri, flag, getOptions());
	}

	/**
	 * TODO Byte
	 * @Title: ImageLoader
	 * @Description: Í¼Æ¬¼ÓÔØ
	 * @author: LiuSiQing
	 * @date: 2015-7-20 ÏÂÎç1:55:47
	 */
	public static byte[] ImageLoaderOfByte(String uri) {
		return ImageLoaderOfByte(uri, Config.FLAG_SERVER);
	}
}
