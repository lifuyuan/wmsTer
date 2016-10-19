package com.wmsterminal.util;

import java.io.ByteArrayOutputStream;





import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import org.apache.http.client.methods.HttpRequestBase;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.PopupWindow;

import com.facebook.datasource.DataSource;
import com.lidroid.xutils.http.HttpHandler;

/**
 * @author light
 * @File CloseUtil.java
 * @Package com.crowdfunding.util
 * @Time 2015Äê9ÔÂ6ÈÕÉÏÎç9:33:57
 * @Description IO²Ù×÷
 */
public class CloseUtil {

	private static final String TAG = CloseUtil.class.getSimpleName();

	/**
	 * @Title: close
	 * @Description: ¹Ø±Õ
	 * @author: LiuSiQing
	 * @date: 2015-4-8 ÏÂÎç3:10:47
	 */
	public static void close(Closeable close) {
		try {
			if( null != close )
				close.close();
		} catch(IOException e) {
			DebugUtil.e(TAG, e.toString());
		}
	}

	/**
	 * @Title: close
	 * @Description: ¹Ø±Õ
	 * @author: LiuSiQing
	 * @date: 2015-6-25 ÏÂÎç4:31:23
	 */
	public static void close(DataSource<?> dataSource) {
		if( null != dataSource && !dataSource.isClosed() )
			dataSource.close();
	}

	/**
	 * @Title: reset
	 * @Description: ÖØÖÃ
	 * @author: LiuSiQing
	 * @date: 2015-4-8 ÏÂÎç3:10:47
	 */
	public static void reset(ByteArrayOutputStream out) {
		if( null != out )
			out.reset();
	}

	/**
	 * ÊÍ·ÅBitmap
	 */
	public static void recycle(Bitmap bitmap) {
		if( null != bitmap && !bitmap.isRecycled() )
			bitmap.recycle();
		bitmap = null;
		System.gc();
	}

	/**
	 * @Title: dismiss
	 * @Description: ½«dialogÏú»Ù
	 * @param dialog Aug 28, 2014 11:12:14 AM
	 */
	public static void dismiss(Dialog dialog) {
		if( null != dialog )
			dialog.dismiss();
	}

	/**
	 * @Title: cancel
	 * @Description: È¡ÏûDialog
	 * @author: LiuSiQing
	 * @date: 2015-7-15 ÏÂÎç2:22:02
	 */
	public static void cancel(Dialog dialog) {
		if( null != dialog )
			dialog.cancel();
	}

	/**
	 * @Title: dismiss
	 * @Description: ½«dialogÏú»Ù
	 * @param dialog Aug 28, 2014 11:12:14 AM
	 */
	public static void dismiss(DialogInterface dialog) {
		if( null != dialog )
			dialog.dismiss();
	}

	/**
	 * @Title: dismiss
	 * @Description: ½«PopupWindowÏú»Ù
	 * @param dialog Aug 28, 2014 11:12:14 AM
	 */
	public static void dismiss(PopupWindow window) {
		if( null != window )
			window.dismiss();
	}

	/**
	 * @Title: ÇÐ»»
	 * @Description: PopupWindow ÇÐ»»
	 * @param dialog Aug 28, 2014 11:12:14 AM
	 */
	public static void toggle(PopupWindow window, View view) {
		if( null != window ) {
			if( window.isShowing() )
				window.dismiss();
			else
				window.showAsDropDown(view);
		}
	}

	/**
	 * @Title: hide
	 * @Description: ½«dialogÒþ²Ø
	 * @param dialog Sep 1, 2014 12:19:24 PM
	 */
	public static void hide(Dialog dialog) {
		if( null != dialog && dialog.isShowing() )
			dialog.hide();
	}

	/**
	 * @Title: show
	 * @Description: ½«dialogÕ¹Ê¾
	 * @param dialog Sep 1, 2014 12:19:10 PM
	 */
	public static void show(Dialog dialog) {
		if( null != dialog && !dialog.isShowing() )
			dialog.show();
	}

	/**
	 * ¹Ø±ÕÁ¬½Ó
	 * @param http
	 */
	public static void abort(HttpRequestBase http) {
		if( null != http )
			http.abort();
	}

	/**
	 * @Title: cancel
	 * @Description: ÖÐ¶ÏHttpHandler
	 * @author: LiuSiQing
	 * @date: 2015-7-15 ÏÂÎç2:22:02
	 */
	public static void cancel(HttpHandler<?> handler) {
		if( null != handler )
			handler.cancel();
	}

//	public static void cancel(NetWorkPost post) {
//		if( null != post )
//			post.cancel();
//	}
//
//	/**
//	 * @Title: cancel
//	 * @Description: ÖÐ¶ÏHttp Download
//	 * @author: LiuSiQing
//	 * @date: 2015-7-15 ÏÂÎç2:22:02
//	 */
//	public static void cancel(NetWorkDownload task) {
//		if( null != task )
//			task.cancel();
//	}

	/**
	 * @Title: stopTask 
	 * @Description: ¹Ø±ÕAsyncTask
	 * @param task
	 * Sep 5, 2014 5:26:54 PM
	 */
	@SuppressWarnings("rawtypes")
	public static void cancel(AsyncTask task) {
		if( null != task && !task.isCancelled() )
			task.cancel(true);
	}

	/**
	 * @Title: stopTask 
	 * @Description: ¹Ø±ÕÊý¾ÝÏß³Ì
	 * @param list
	 * Sep 10, 2014 11:11:39 AM
	 */
	@SuppressWarnings("rawtypes")
	public static void cancel(List<AsyncTask> list) {
		if( ObjectUtil.isEmpty(list) )
			return;
		for(AsyncTask task : list)
			if( null != task && !task.isCancelled() )
				task.cancel(true);
	}

	/**
	 * @Title: stopThread 
	 * @Description: Í£Ö¹Ïß³Ì
	 * @param thread
	 * Sep 15, 2014 10:25:57 AM
	 */
	public static void interrupt(Thread thread) {
		if( null != thread && !thread.isInterrupted() ) {
			thread.interrupt();
		}
	}
}
