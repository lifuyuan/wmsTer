package com.wmsterminal.util;

import com.wmsterminal.base.Config;


import android.util.Log;

/**
 * ***************************************
 *
 * @author :light
 * @package :com.allinmall.util
 * @date :2015Äê11ÔÂ11ÈÕ ÏÂÎç4:05:26
 * @descripe :µ÷ÊÔ
 * ***************************************
 */
public class DebugUtil {

    private static String TAG = "wms";

    public static String setTAG() {

        try {
            TAG = "[" + Thread.currentThread().getStackTrace()[1].getClassName() + "]" + "[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]";
        } catch (Exception e) {
            Log.e(TAG, "ÎÞ·¨³É¹¦ÉèÖÃTAG");
        }

        return TAG;
    }

    public static void v(String tag, String msg) {
        if (Config.DEBUG) {
            Log.v(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (Config.DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (Config.DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (Config.DEBUG) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (Config.DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void wtf(String tag, String msg) {
        if (Config.DEBUG) {
            Log.wtf(TAG, msg);
        }
    }

    private static final boolean DEBUG = Config.DEBUG;

    public static void Logd(String tag, String msg) {
        if (DEBUG)
            Log.d(tag, msg);
    }

    public static void Loge(String tag, String msg) {
        if (DEBUG)
            Log.e(tag, msg);
    }

    public static void Logi(String tag, String msg) {
        if (DEBUG)
            Log.i(tag, msg);
    }

    public static void Logw(String tag, String msg) {
        if (DEBUG)
            Log.w(tag, msg);
    }

    public static void Logwtf(String tag, String msg) {
        if (DEBUG)
            Log.wtf(tag, msg);
    }

    public static void Logv(String tag, String msg) {
        if (DEBUG)
            Log.v(tag, msg);
    }
}
