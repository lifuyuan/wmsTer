package com.wmsterminal.base;

import java.lang.Thread.UncaughtExceptionHandler;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.wmsterminal.model.WavsResponse;
//import com.allinmall.exception.CrashHandler;
//ÐÞ¸Ä 1
import com.wmsterminal.util.DebugUtil;
import com.wmsterminal.util.DisplayUtil;
import com.wmsterminal.util.NetworkStatusManager;
import com.wmsterminal.util.SDCardUtil;
import com.wmsterminal.util.StringUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import cn.jpush.android.api.JPushInterface;

/**
 * BaseApplication
 */
public class BaseApplication extends Application {

    /**
     * TAG
     */
    private static final String TAG = BaseApplication.class.getSimpleName();
    /**
     * Ñ¡Ïî¿¨ID
     */
    private static int tabId;

    /**
     * °üÃû
     */
    public String mPackName;
    /**
     * ²¶»ñÒì³£ºóÐÅÏ¢±£´æµØÖ·
     */
    public String mCrashFilePath;

    /**
     * Êý¾Ý¿â×Öµä£¨ÁÙÊ±Ê¹ÓÃ£©
     */
   
    

    public static int getTabId() {
        return tabId;
    }

    public static void setTabId(int tabId) {
        BaseApplication.tabId = tabId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPackName = getPackageName();
        // init fresco
        Fresco.initialize(this);
        // init imageloader
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        // init diaplay
        DisplayUtil.init(this);
        // init ÍøÂç¼àÌý
        NetworkStatusManager.init(this);
        // ²¶»ñ±ÀÀ£Òì³£
        /*if (Config.IS_NEED_CAUGHT_EXEPTION) {
            CrashHandler crashHandler = CrashHandler.getInstance();
			crashHandler.setHost("smtp.sina.com");
			crashHandler.setAddrTo("shallowlight@sina.com");
			crashHandler.setUserName("shallowlight@sina.com");
			crashHandler.setUserPass("www.sina.com1024");
			crashHandler.init(this);
		}*/

        //JPushInterface.setDebugMode(true); 	// ÉèÖÃ¿ªÆôÈÕÖ¾,·¢²¼Ê±Çë¹Ø±ÕÈÕÖ¾
        //JPushInterface.init(this);
    }

    /**
     * Activity¹ÜÀí
     */
    private List<Activity> mList = new LinkedList<Activity>();
    /**
     * ÕýÔÚÑ­»·
     */
    private static boolean IS_FORING = false;

    /**
     * @author light
     * @date 2015Äê12ÔÂ21ÈÕÏÂÎç3:01:58
     * @package com.allinmall.base
     * @descripe ²¶»ñ±ÀÀ£Òì³£
     */
    @SuppressWarnings("unused")
    private void cauchException() {

        Intent intent = new Intent();
        // ²ÎÊý1£º°üÃû£¬²ÎÊý2£º³ÌÐòÈë¿ÚµÄactivity
        intent.setClassName(mPackName, mPackName + ".MainActivity");
        PendingIntent restartIntent = null;
        restartIntent = PendingIntent.getActivity(getApplicationContext(), -1, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
        // ³ÌÐò±ÀÀ£Ê±´¥·¢Ïß³Ì
        EXUncaughtExceptionHandler uncaughtExceptionHandler = new EXUncaughtExceptionHandler(restartIntent);
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
    }

    /**
     * @author :light
     * @package :com.crowdfunding.base
     * @date :2015Äê11ÔÂ10ÈÕ ÉÏÎç11:01:37
     * @descripe :´´½¨·þÎñÓÃÓÚ²¶»ñÏµÍ³±ÀÀ£µÄÒì³£
     */
    private class EXUncaughtExceptionHandler implements UncaughtExceptionHandler {

        private PendingIntent mRestartIntent;

        private EXUncaughtExceptionHandler(PendingIntent intent) {
            this.mRestartIntent = intent;
        }

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {

            // ±£´æ´íÎóÈÕÖ¾
            SDCardUtil.saveCatchInfo2File(ex, mCrashFilePath);
            // 1ÃëÖÓºóÖØÆôÓ¦ÓÃ
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            am.set(AlarmManager.RTC, System.currentTimeMillis() + 500, mRestartIntent);
            // ¹Ø±Õµ±Ç°Ó¦ÓÃ
            finish();
            finishProgram();
        }
    }

    /**
     * @package :com.crowdfunding.base
     * @class :BaseApplication.java
     * @author :light
     * @date :2015Äê11ÔÂ10ÈÕ ÉÏÎç11:02:39
     * @descripe :½«Ä³¸ö»î¶¯Ìí¼Ó½ø¼¯ºÏ½øÐÐ¹ÜÀí
     */
    public void addActivity(Activity activity) {

        if (!IS_FORING)
            mList.add(activity);
    }

    /**
     * @package :com.crowdfunding.base
     * @class :BaseApplication.java
     * @author :light
     * @date :2015Äê11ÔÂ10ÈÕ ÉÏÎç11:03:13
     * @descripe :´Ó»î¶¯¹ÜÀíÖÐÒÆ³ýÄ³¸ö»î¶¯
     */
    public void removeActivity(Activity activity) {

        if (null != activity && mList.contains(activity) && !IS_FORING)
            mList.remove(activity);
    }

    /**
     * @package :com.crowdfunding.base
     * @class :BaseApplication.java
     * @author :light
     * @date :2015Äê11ÔÂ10ÈÕ ÉÏÎç11:00:27
     * @descripe :´Ó¼¯ºÏÖÐÒÆ³ýÄ³¸ö»î¶¯£¬²¢½«»î¶¯¹Ø±Õ
     */
    public void removeActivityFinish(Activity activity) {

        removeActivity(activity);
        if (null != activity && !activity.isFinishing() && !IS_FORING)
            activity.finish();
    }

    /**
     * @Title: exit
     * @Description: ¹Ø±ÕËùÓÐactivity²¢ÍË³ö³ÌÐò(ÎÞÒâÒå) Aug 23, 2014 2:03:06 PM
     */
    public void exit() {

        try {
            finish();
        } catch (Exception e) {
            DebugUtil.e(TAG, e.toString());
        } finally {
            System.exit(0);
        }
    }

    /**
     * @package :com.crowdfunding.base
     * @class :BaseApplication.java
     * @author :light
     * @date :2015Äê11ÔÂ10ÈÕ ÉÏÎç10:59:55
     * @descripe :¹Ø±ÕËùÓÐ»î¶¯
     */
    public void finish() {

        IS_FORING = true;
        for (Activity activity : mList)
            if (activity != null && !activity.isFinishing())
                activity.finish();
        mList.clear();
        IS_FORING = false;
    }

    /**
     * @package :com.crowdfunding.base
     * @class :BaseApplication.java
     * @author :light
     * @date :2015Äê11ÔÂ10ÈÕ ÉÏÎç10:59:34
     * @descripe :ÍË³öÓ¦ÓÃ³ÌÐò
     */
    public void finishProgram() {

        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * @package :com.crowdfunding.base
     * @class :BaseApplication.java
     * @author :light
     * @date :2015Äê11ÔÂ10ÈÕ ÉÏÎç11:04:26
     * @descripe :¹Ø±ÕÖ¸¶¨Àà¶ÔÏóµÄ»î¶¯
     */
    @SuppressWarnings("rawtypes")
    public void finish(Class clazz) {

        if (null == clazz)
            return;
        finish(clazz.getSimpleName());
    }

    /**
     * @package :com.crowdfunding.base
     * @class :BaseApplication.java
     * @author :light
     * @date :2015Äê11ÔÂ10ÈÕ ÉÏÎç11:05:00
     * @descripe :¹Ø±ÕÖ¸¶¨Ãû³ÆµÄ»î¶¯
     */
    public void finish(String simpleName) {

        IS_FORING = true;
        List<Activity> list = new ArrayList<Activity>();
        for (Activity activity : mList)
            if (StringUtil.equals(activity.getClass().getSimpleName(), simpleName) && activity != null
                    && !activity.isFinishing()) {
                activity.finish();
            } else list.add(activity);
        mList = list;
        IS_FORING = false;
    }

    /**
     * ÄÚ´æ½ÏµÍÊ±£¬Ö÷¶¯´¥·¢À¬»øÊÕ¼¯
     */
    @Override
    public void onLowMemory() {

        super.onLowMemory();
        System.gc();
    }
}
