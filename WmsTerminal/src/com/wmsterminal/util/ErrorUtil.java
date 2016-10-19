package com.wmsterminal.util;




import com.wmsterminal.R;
import com.wmsterminal.base.Config;
import com.wmsterminal.common.dialog.CustomDialog;
import com.wmsterminal.common.listener.OnClickFinishListener;
//import com.allinmall.common.dialog.CustomDialog;
//import com.allinmall.common.listener.OnClickFinishListener;
//ÐÞ¸Ä
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

/**
 * ***************************************
 *
 * @author :light
 * @package :com.allinmall.util
 * @date :2015Äê11ÔÂ11ÈÕ ÏÂÎç4:42:20
 * @descripe :´íÎó´¦Àí
 * ***************************************
 */
public class ErrorUtil {

    /**
     * @Title: getMessage
     * @Description: Æ´½ÓÒì³£ÐÅÏ¢
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:35:08
     */
    public static String getMessage(Exception e) {
        return getMessage(e.getMessage());
    }

    public static String getMessage(String e) {
        return Config.ERROR + e;
    }

    /**
     * @Title: getErrorFromJson
     * @Description: »ñµÃ´íÎóÐÅÏ¢
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:34:57
     */
    public static String getErrorFromJson(String json) {
        if (null != json && json.indexOf(Config.ERROR) != -1)
            return json.substring(Config.ERROR.length());
        return null;
    }

    /**
     * @Title: isError
     * @Description: ÊÇ·ñÊÇ´íÎóÐÅÏ¢
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:34:24
     */
    public static boolean isError(String error) {
        return ObjectUtil.isEmpty(error) ? false : true;
    }

    /**
     * @Title: isErrorJson
     * @Description: ÊÇ·ñÊÇ´íÎóÐÅÏ¢
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:34:43
     */
    public static boolean isErrorJson(String json) {
        return isError(getErrorFromJson(json));
    }


    /**
     * @param context ÉÏÏÂÎÄ error ´íÎóÐÅÏ¢ close ÊÇ·ñ¹Ø±Õ show ÊÇ·ñÏÔÊ¾ ocl ÌáÊ¾¿òµã»÷¼àÌý
     * @Title: showError
     * @Description: ÏÔÊ¾´íÎó
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:20:25
     */
    public static boolean showError(Context context, String error, boolean close) {
        if (isError(error)) {
            if (close)
                CustomDialog.showBuilderCancelableOne(context, context.getString(R.string.hint), error,
                        context.getString(R.string.close), new OnClickFinishListener((Activity) context));
            else
                CustomDialog.showBuilderOne(context, error);
            return true;
        }
        return false;
    }

    /**
     * @param context ÉÏÏÂÎÄ json ÐÅÏ¢ close ÊÇ·ñ¹Ø±Õ show ÊÇ·ñÏÔÊ¾ ocl ÌáÊ¾¿òµã»÷¼àÌý
     * @Title: showErrorJson
     * @Description: ÏÔÊ¾´íÎó
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:20:25
     */
    public static boolean showErrorJson(Context context, String json, boolean close) {
        return showError(context, getErrorFromJson(json), close);
    }

    /**
     * @Title: showErrorJsonFinish
     * @Description: ÏÔÊ¾ËùÓÐ´íÎóÐÅÏ¢
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:28:13
     */
    public static boolean showErrorJson(Context context, String json) {
        return showErrorJson(context, json, false);
    }

    /**
     * @Title: showErrorJson
     * @Description: ÏÔÊ¾´íÎó
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:30:13
     */
    public static boolean showErrorJsonFinish(Context context, String json) {
        return showErrorJson(context, json, true);
    }

    /**
     * @Title: showError
     * @Description: ÏÔÊ¾´íÎó
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:28:13
     */
    public static boolean showError(Context context, String error) {
        return showError(context, error, false);
    }

    /**
     * @Title: showErrorErrorFinish
     * @Description: ÏÔÊ¾´íÎó£¬²¢¹Ø±ÕÒ³Ãæ
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:27:32
     */
    public static boolean showErrorFinish(Context context, String error) {
        return showError(context, error, true);
    }

    /**
     * @Title: isNetworkErrorJson
     * @Description: ÊÇ·ñÊÇÍøÂç´íÎó
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:33:14
     */
    public static boolean isNetWorkErrorJson(String json) {
        return isNetWorkError(getErrorFromJson(json));
    }

    /**
     * @Title: isErrorAndNetWorkError
     * @Description: ÊÇ·ñÊÇÍøÂç´íÎó
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç5:33:09
     */
    public static boolean isNetWorkError(String error) {
        if (isError(error) && StringUtil.equals(Config.NETWORK_ERROR, error))
            return true;
        return false;
    }

    /**
     * @Title: showErrorJsonAgainFinsh
     * @Description: ÔÚÊÔÒ»´Î
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç2:09:33
     */
    public static boolean showErrorJsonAgainFinsh(Context context, String json, DialogInterface.OnClickListener l) {
        String error = getErrorFromJson(json);
        if (isError(error)) {
        /*	CustomDialog.showBuilderCancelable(context, context.getString(R.string.exception_hint), error,
					context.getString(R.string.try_again), l, context.getString(R.string.cancel),
					new OnClickFinishListener((Activity) context));*/
            return true;
        }
        return false;
    }
}