package com.wmsterminal.util;

import java.lang.reflect.Field;


import java.lang.reflect.Method;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.wmsterminal.model.User;

/**
 * @Package: com.mngwyhouhzmb.util
 * @ClassName: SharedUtil
 * @Description: ÐÅÏ¢¹¤¾ßÀà
 * @author: LiuSiQing
 * @date: 2015-6-25 ÏÂÎç1:43:41
 */
public class SharedUtil {

    private static final String TAG = SharedUtil.class.getSimpleName();

    /**
     * @param context  Ò³Ãæ
     * @param obj      ¶ÔÏó
     * @param fileName ±£´æµÄÎÄ¼þÃû
     * @param saveNull ±£´æ¿ÕÐÅÏ¢ Oct 8, 2014 5:05:48 PM
     * @Title: setValue
     * @Description: ±£´æÐÅÏ¢
     */
    public static void setValue(Context context, Object obj, String fileName, boolean saveNull) {
        if (null == obj || ObjectUtil.isEmpty(fileName))
            return;
        try {
            Editor edit = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
            Field[] field = obj.getClass().getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                String name = field[i].getName();
                if (StringUtil.equals("serialVersionUID", name))
                    continue;
                name = StringUtil.upperFirstWord(name);
                Method m = obj.getClass().getMethod("get" + name);
                if (null == m)
                    continue;
                Object o = m.invoke(obj);
                String value = null;
                if (null != o) {
                    if (o instanceof String)
                        value = (String) o;
                    else
                        value = String.valueOf(o);
                }
                if (null != value)
                    edit.putString(name, value.toString());
                else if (saveNull)
                    edit.putString(name, "");
            }
            edit.commit();
        } catch (Exception e) {
            DebugUtil.e(TAG, e.toString());
        }
    }

    /**
     * @param context  Ò³Ãæ
     * @param obj      ¶ÔÏó
     * @param fileName ±£´æµÄÎÄ¼þÃû Oct 8, 2014 5:05:48 PM
     * @Title: setValueNull
     * @Description: ±£´æÐÅÏ¢£¬°üÀ¨¿ÕµÄÐÅÏ¢
     */
    public static void setValueNull(Context context, Object obj, String fileName) {
        setValue(context, obj, fileName, true);
    }

    /**
     * @param context  Ò³Ãæ
     * @param obj      ¶ÔÏó
     * @param fileName ±£´æµÄÎÄ¼þÃû Oct 8, 2014 5:05:48 PM
     * @Title: setValueNotNull
     * @Description: ±£´æÐÅÏ¢£¬²»°üÀ¨¿ÕµÄÐÅÏ¢
     */
    public static void setValueNotNull(Context context, Object obj, String fileName) {
        setValue(context, obj, fileName, false);
    }

    /**
     * @param context
     * @param obj
     * @param fileName
     * @return Oct 8, 2014 5:13:47 PM
     * @Title: getValue
     * @Description: µÃµ½±¾µØÐÅÏ¢
     */
    public static Object getValue(Context context, Object obj, String fileName) {
        if (null == obj || ObjectUtil.isEmpty(fileName))
            return null;
        try {
            SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            Field[] field = obj.getClass().getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                String name = field[i].getName();
                if (StringUtil.equals("serialVersionUID", name))
                    continue;
                name = StringUtil.upperFirstWord(name);
                Method m = obj.getClass().getMethod("set" + name, String.class);
                if (null == m)
                    continue;
                String value = preferences.getString(name, "");
                m.invoke(obj, value);
            }
        } catch (Exception e) {

            DebugUtil.e(TAG, e.toString());
            return null;
        }
        return obj;
    }

    /**
     * @param context
     * @param fileName
     * @param name
     * @param value    Sep 25, 2014 10:40:23 AM
     * @Title: setUser
     * @Description: ½«Ä³¸ö×Ö¶Î±£´æÔÚ±¾µØ
     */
    public static void setValue(Context context, String fileName, String name, String value) {
        Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        editor.putString(StringUtil.upperFirstWord(name), value).apply();
    }

    /**
     * @param context
     * @param fileName
     * @param name
     * @return Sep 25, 2014 10:42:11 AM
     * @Title: getValue
     * @Description: ´Ó±¾µØÈ¡³öÄ³¸ö×Ö¶Î
     */
    public static String getValue(Context context, String fileName, String name) {
        if (null == context)
            return "";
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE).getString(name, "");
    }

    /**
     * ½«sessionÖÃ¿Õ
     */
    public static void clearSession(Context context) {
        setValue(context, "USER", "accessid", null);
    }

    /**
     * Çå³ý±¾µØÓÃ»§ÐÅÏ¢
     */
    public static void clearUser(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }

    /**
     * <br>±êÌâ: ±£´æÓÃ»§ÐÅÏ¢
     * <br>¼ò½é:
     * <br>×÷Õß: shuhaiyang
     * <br>Ê±¼ä: 2016/3/12 16:25
     */
    public static void saveUser(Context context, User user) {
        Editor editor = context.getSharedPreferences("USER", Context.MODE_PRIVATE).edit();
        editor.putString("name", user.getUsername());
        editor.putString("pass", user.getPassword());
        editor.putString("token", user.getToken());
        editor.apply();
    }

    /**
     * <br>±êÌâ: »ñÈ¡ÓÃ»§ÐÅÏ¢
     * <br>¼ò½é:
     * <br>×÷Õß: shuhaiyang
     * <br>Ê±¼ä: 2016/3/12 16:25
     */
    public static String getUser(Context context,String name) {
        return getValue(context, "USER",name);
    }
    
    public static String getUser(Context context) {
        return getValue(context, "USER","name").replace(".","_").replace("@", "_");
    }

    /**
     * <br>±êÌâ: »ñÈ¡tokenÖµ
     * <br>¼ò½é:
     * <br>×÷Õß: shuhaiyang
     * <br>Ê±¼ä: 2016/3/12 16:25
     */
    public static String getToken(Context context) {
        return getValue(context, "USER", "token");
    }

}
