package com.wmsterminal.util;

import java.util.Map;



import com.wmsterminal.base.BaseApplication;

import android.app.Activity;
import android.content.Intent;

/**
 * package: com.crowdfunding.util author: light date: 2015Äê9ÔÂ14ÈÕ ÉÏÎç10:28:57
 * descripe: Ìø×ª¹¤¾ßintent
 */
public class IntentUtil {
	
	/**
	 * @author light
	 * @date 2015Äê12ÔÂ9ÈÕÉÏÎç11:37:07
	 * @package com.allinmall.util
	 * @param activity
	 *            ÉÏÏÂÎÄ
	 * @param clazz
	 *            Ìø×ªµÄÒ³Ãæ
	 * @descripe Ò³ÃæÌø×ª
	 */
	@SuppressWarnings("rawtypes")
	public static void startActivity(Activity activity, Class clazz) {
		
		Intent intent = new Intent(activity, clazz);
		activity.startActivity(intent);
	}
	
	/**
	 * @author light
	 * @date 2015Äê12ÔÂ9ÈÕÉÏÎç11:36:48
	 * @package com.allinmall.util
	 * @param activity
	 * @param clazz
	 * @param map
	 * @descripe Ð¯´øÊý¾ÝÌø×ªÒ³Ãæ
	 */
	@SuppressWarnings("rawtypes")
	public static void startActivity(Activity activity, Class clazz, Map<String, String> map) {
		
		Intent intent = new Intent(activity, clazz);
		if (!ObjectUtil.isEmpty(map)) {
			Object key[] = map.keySet().toArray();
			for (int j = 0; j < key.length; j++)
				intent.putExtra((String) key[j], map.get((String) key[j]));
		}
		activity.startActivity(intent);
	}
	
	/**
	 * @author light
	 * @date 2015Äê12ÔÂ9ÈÕÉÏÎç11:33:48
	 * @package com.allinmall.util
	 * @param activity
	 * @param clazz
	 * @todo Ìø×ª²¢¹Ø±Õµ±Ç°Ò³Ãæ
	 */
	@SuppressWarnings("rawtypes")
	public static void startActivityFinish(Activity activity, Class clazz) {
		
		startActivity(activity, clazz);
		if (!activity.isFinishing())
			activity.finish();
	}
	
	/**
	 * @author light
	 * @date 2015Äê12ÔÂ9ÈÕÉÏÎç11:24:35
	 * @package com.allinmall.util
	 * @param activity
	 * @param clazz
	 * @param map
	 * @todo TODO
	 */
	@SuppressWarnings("rawtypes")
	public static void startActivityFinish(Activity activity, Class clazz, Map<String, String> map) {
		
		startActivity(activity, clazz, map);
		activity.finish();
	}
	
	/**
	 * @author light
	 * @date 2015Äê12ÔÂ9ÈÕÉÏÎç11:39:37
	 * @package com.allinmall.util
	 * @param activity
	 * @param clazz
	 * @descripe TODO
	 */
	@SuppressWarnings("rawtypes")
	public static void startActivityFinishAll(Activity activity, Class clazz) {
		
		((BaseApplication) activity.getApplication()).finish();
		startActivityFinish(activity, clazz);
	}
	
	/**
	 * @package :com.crowdfunding.util
	 * @class :IntentUtil.java
	 * @author :light
	 * @date :2015Äê11ÔÂ6ÈÕ ÉÏÎç10:18:56
	 * @descripe :ÏÈ¹Ø±ÕÒ³ÃæÕ»ÖÐÒÑ´æÔÚµÄActivity£¬È»ºóÌø×ª£¬²¢¹Ø±Õµ±Ç°Ò³Ãæ
	 */
	@SuppressWarnings("rawtypes")
	public static void startActivityRemove(Activity activity, Class clazz) {
		
		((BaseApplication) activity.getApplication()).finish(clazz);
		startActivityFinish(activity, clazz);
	}
	
	/**
	 * @package :com.crowdfunding.util
	 * @class :IntentUtil.java
	 * @author :light
	 * @date :2015Äê11ÔÂ6ÈÕ ÉÏÎç10:20:00
	 * @descripe :TODO
	 */
	public static void startActivityRemove(Activity activity, @SuppressWarnings("rawtypes")
	Class clazz, Map<String, String> map) {
		
		((BaseApplication) activity.getApplication()).finish(clazz);
		startActivityFinish(activity, clazz, map);
	}
}
