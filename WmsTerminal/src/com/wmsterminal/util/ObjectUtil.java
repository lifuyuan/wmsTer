package com.wmsterminal.util;

import java.lang.reflect.Field;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.annotation.SuppressLint;
import android.content.ContentValues;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

/**
 * <p>
 * ±ê Ìâ: ºËÐÄ¿ò¼Ü
 * </p>
 * <p>
 * Ãè Êö: ¶ÔÏó´¦ÀíÊµÓÃÀà
 * </p>
 * <p>
 * °æ È¨: Copyright (c) 2010
 * </p>
 * <p> * </p>
 * <p>
 * ´´½¨Ê±¼ä: 2010-12-13 ÉÏÎç11:58:35
 * </p>
 *
 * @author ²úÆ·¿ª·¢²¿
 * @version 2.0 ObjectUtil
 */
@SuppressLint("DefaultLocale")
public class ObjectUtil {

    private static final String TAG = ObjectUtil.class.getSimpleName();

    /**
     * TODO isEmpty ÅÐ¶Ï¿Õ
     */
    /**
     * ÅÐ¶ÏStringÀàÐÍ¶ÔÏóÊÇ·ñÎª¿Õ(¿Õ´®»ònull)
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().equals("") || str.trim().equals("null"))
            return true;
        return false;
    }

    /**
     * ÅÐ¶ÏStringÀàÐÍ¶ÔÏóÊÇ·ñÎª¿Õ(Ö»ÓÐ¿Õ´®)
     *
     * @param str
     * @return
     */
    public static boolean isEmptyNotNull(String str) {
        if (str == null || str.trim().equals(""))
            return true;
        return false;
    }

    /**
     * ÅÐ¶ÏÒ»¸ö¶ÔÏóÊý×éÊÇ·ñÎª¿Õ(Ã»ÓÐ³ÉÔ±)
     *
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array) {
        if (array == null || array.length < 1)
            return true;
        return false;
    }

    /**
     * ÅÐ¶ÏÒ»¸ö¶ÔÏóÊý×éÊÇ·ñÎª¿Õ(Ã»ÓÐ³ÉÔ±)
     *
     * @param array
     * @return
     */
    public static boolean isEmpty(int[] array) {
        if (array == null || array.length < 1)
            return true;
        return false;
    }

    /**
     * ÅÐ¶ÏÒ»¸ö¶ÔÏóÊý×éÊÇ·ñÎª¿Õ(Ã»ÓÐ³ÉÔ±)
     *
     * @param array
     * @return
     */
    public static boolean isEmpty(byte[] array) {
        if (array == null || array.length < 1)
            return true;
        return false;
    }

    /**
     * @Title: isEmpty
     * @Description: ÅÐ¶ÏÒ»¸ölistÊÇ·ñÎª¿Õ(Ã»ÓÐ³ÉÔ±)
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç1:16:37
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(List list) {
        if (null == list || "".equals(list) || list.size() < 1)
            return true;
        return false;
    }

    /**
     * @param map
     * @return Jun 28, 2014 10:51:24 AM
     * @Title: isEmpty
     * @Description: ÅÐ¶ÏÒ»¸ömapÊÇ·ñÎª¿Õ
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Map map) {
        if (null == map || "".equals(map) || map.size() < 1)
            return true;
        return false;
    }

    /**
     * @param json
     * @return Aug 27, 20144:24:08 PM
     * @Title: isEmptyJson
     * @Description: JsonÊÇ·ñÎª¿Õ
     */
    public static boolean isEmptyJson(String json) {
        if (isEmpty(json))
            return true;
        if (json.indexOf("]") - json.indexOf("[") < 1)
            return true;
        return false;
    }

    /**
     * @param list
     * @return Aug 23, 2014 4:25:44 PM
     * @Title: getSize
     * @Description: µÃµ½listµÄsize
     */
    @SuppressWarnings("rawtypes")
    public static int getSize(List list) {
        return isEmpty(list) ? 0 : list.size();
    }

    /**
     * @param list
     * @return Aug 23, 2014 4:25:44 PM
     * @Title: length
     * @Description: µÃµ½objsµÄlength
     */
    public static int length(Object[] objs) {
        return isEmpty(objs) ? 0 : objs.length;
    }

    /**
     * @param list
     * @param position
     * @return Aug 23, 2014 4:57:28 PM
     * @Title: getObject
     * @Description: µÃµ½listµÄitem
     */
    @SuppressWarnings("rawtypes")
    public static Object getObject(List list, int position) {
        return (isEmpty(list) || position >= list.size() || position < 0) ? null : list.get(position);
    }

    /**
     * TODO ×ª»»
     */
    /**
     * @param json
     * @param clazz
     * @return Jun 23, 2014 10:54:33 AM
     * @Title: JsonToObj
     * @Description: json×ª»»³Éobj[]
     */
    public static Object[] JsonToObj(String json, Class<?> clazz) {
        JSONArray JsonArray = JSON.parseArray(json);
        Object[] obj = new Object[JsonArray.size()];
        for (int i = 0; i < JsonArray.size(); i++) {
            JSONObject jsonObject = JsonArray.getJSONObject(i);
            obj[i] = JSON.toJavaObject(jsonObject, clazz);
        }
        return obj;
    }

    /**
     * @Title: JsonToObject
     * @Description: json×ª»»³Éobj
     * @author: LiuSiQing
     * @date: 2015-7-2 ÏÂÎç2:16:34
     */
    public static Object JsonToObject(String json, Class<?> clazz) {
        return JSONObject.parseObject(json, clazz);
    }

    // /**
    // * @Title JsonToObj
    // * @Description ÓÃÓÚÒ»¼ü½É·Ñ½Ó¿Ú
    // * @param json
    // * @param key
    // * @return
    // */
    // public static String JsonToObj(String json, String key) {
    // if( ErrorUtil.isErrorJson(json) )
    // return json;
    // JSONObject jsonObject = JSONObject.parseObject(json);
    // String flag = jsonObject.getString("flag");
    // if( StringUtil.equalsIgnoreCase(Config.SUCCESS, flag) )
    // return jsonObject.getString(key);
    // else
    // return ErrorUtil.getMessage(flag);
    // }

    // /**
    // * @Title JsonToList
    // * @Description JSON×ª»»
    // * @param json
    // * @param keys
    // * @return
    // */
    // public static Map<String, String> JsonToMap(String json, String[] keys) {
    // Map<String, String> map = new HashMap<String, String>();
    // if( ErrorUtil.isErrorJson(json) ) {
    // map.put("flag", json);
    // return map;
    // }
    // JSONObject jsonObject = JSONObject.parseObject(json);
    // for(int i = 0; i < keys.length; i++) {
    // String key = keys[i];
    // String value = jsonObject.getString(key);
    // if( StringUtil.equals("flag", key) &&
    // !StringUtil.equalsIgnoreCase(Config.SUCCESS_WXZJ, value) ) {
    // map.put("flag", ErrorUtil.getMessage(value));
    // } else
    // map.put(key, value);
    // }
    // return map;
    // }

    /**
     * @param rsText
     * @param pClass
     * @return Jun 23, 2014 10:55:08 AM
     * @Title: JsonArryToObj
     * @Description: jsonArray×ª»»³ÉList<obj[]>
     */
    public static List<Object[]> JsonArryToObj(String rsText, Class<?>[] pClass) {
        JSONArray JsonArray = new JSONArray();
        if (!ObjectUtil.isEmpty(rsText))
            JsonArray = JSON.parseArray(rsText);
        List<Object[]> mList = new ArrayList<Object[]>();
        for (int i = 0; i < JsonArray.size(); i++) {
            String mTemp = JsonArray.get(i).toString();
            JSONArray mTempJsonArray = new JSONArray();
            if (!ObjectUtil.isEmpty(mTemp))
                mTempJsonArray = JSON.parseArray(mTemp);
            Object[] objs = new Object[mTempJsonArray.size()];
            for (int j = 0; j < mTempJsonArray.size(); j++) {
                JSONObject jsonObject = mTempJsonArray.getJSONObject(j);
                objs[j] = JSON.toJavaObject(jsonObject, pClass[i]);
            }
            mList.add(objs);
        }
        return mList;
    }

    public static String BenToJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * @param object
     * @return Jun 23, 2014 10:57:36 AM
     * @Title: ObjToMap
     * @Description: obj×ª»»³Émap
     */
    public static Map<String, String> ObjToMap(Map<String, String> map, Object object) {
        if (null == map)
            map = new HashMap<String, String>();
        try {
            Field[] field = object.getClass().getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                String name = field[i].getName();
                name = StringUtil.upperFirstWord(name);
                Method m = null;
                try {
                    m = object.getClass().getMethod("get" + name);
                } catch (Exception e) {
                    continue;
                }
                Object obj = m.invoke(object);
                if (null == obj)
                    continue;
                String value;
                if (obj instanceof String) // String
                    value = (String) obj;
                else
                    value = String.valueOf(obj);
                map.put(name.toLowerCase(), value);
            }
        } catch (Exception e) {
            DebugUtil.e(TAG, e.toString());
        }
        return map;
    }

    /**
     * @param obj
     * @return Jun 23, 2014 10:57:36 AM
     * @Title: ObjToMap
     * @Description: obj×ª»»³Émap
     */
    public static Map<String, String> ObjToMap(Object obj) {
        return ObjToMap(null, obj);
    }

    /**
     * @param obj
     * @return Jun 23, 2014 10:57:36 AM
     * @Title: ObjToMap
     * @Description: obj×ª»»³Émap
     */
    public static ContentValues ObjToContent(Object obj) {
        ContentValues values = new ContentValues();
        try {
            Field[] field = obj.getClass().getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                String name = StringUtil.upperFirstWord(field[i].getName());
                Method m = null;
                try {
                    m = obj.getClass().getMethod("get" + name);
                } catch (Exception e) {
                    continue;
                }
                if (null == m)
                    continue;
                String value = (String) m.invoke(obj);
                if (null != value)
                    values.put(name, value.toString());
            }
            return values;
        } catch (Exception e) {
            DebugUtil.e(TAG, e.toString());
            return null;
        }
    }

    /**
     * @param json
     * @return Jun 23, 2014 10:59:12 AM
     * @Title: JsonToMap
     * @Description: json×ªmap
     */
    public static HashMap<String, String> JsonToMap(String json) {
        String[] a = null;
        String[] b = null;
        HashMap<String, String> map = new HashMap<String, String>();
        // ´«ÈëµÄjson´®ÊÇ¿ÕµÄ»°¾Í·µ»Ø¿Õ
        if ("{}".equals(json)) {
            return map;
        } else if (ObjectUtil.isEmpty(json)) {
            return map;
        } else {
            // ·ñÔò²ð·ÖÕûºÏ³Émap
            a = json.substring(1, json.length() - 1).split(",");
            for (int i = 0; i < a.length; i++) {
                b = a[i].split("=");
                // Èç¹ûÒ³ÃæÉÏÃ»ÓÐÌîÊý¾Ý£¬ÔòbÖÐÖ»»áÓÐ1¸ö³¤¶È£¬ËùÒÔÐèÒªÊÖ¶¯¸øÒ»¸önull£¬¶ÔÓ¦b[0]Õâ¸ökey
                if (b.length <= 1) {
                    map.put(b[0].trim(), "");
                } else {
                    map.put(b[0].trim(), b[1]);
                }
            }
            return map;
        }
    }

    /**
     * ****************************************
     *
     * @package :com.crowdfunding.util
     * @class :ObjectUtil.java
     * @author :light
     * @date :2015Äê10ÔÂ20ÈÕ ÉÏÎç9:43:18
     * @descripe :TODO
     * ****************************************
     */
    public static String MapToJson(Map<? extends Object, ? extends Object> map, String keyName, String valueName) {
        String string = "";
        if (map != null && keyName != null && valueName != null) {
            List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
            for (Entry<? extends Object, ? extends Object> m : map.entrySet()) {
                Map<Object, Object> newMap = new HashMap<Object, Object>();
                newMap.put(keyName, m.getKey());
                newMap.put(valueName, m.getValue());
                list.add(newMap);
            }
            Gson gson = new Gson();
            string = gson.toJson(list);
        }
        return string;
    }

    /**
     * ****************************************
     *
     * @package :com.crowdfunding.util
     * @class :ObjectUtil.java
     * @author :light
     * @date :2015Äê10ÔÂ20ÈÕ ÉÏÎç9:43:30
     * @descripe :Map×ªString
     * ****************************************
     */
    @SuppressWarnings("rawtypes")
    public static String MapToString(Map<String, String> map) {
        Entry entry;
        StringBuffer sb = new StringBuffer();
        for (Iterator<?> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            entry = (Entry) iterator.next();
            sb.append(entry.getKey().toString()).append("'").append(null == entry.getValue() ? "" :
                    entry.getValue().toString()).append(iterator.hasNext() ? "^" : "");
        }
        return sb.toString();
    }

    /**
     * @param img
     * @return Jun 27, 2014 11:12:54 AM
     * @Title: StringToByte
     * @Description: ºóÌ¨Ê¹ÓÃ"|"×é³Éstring×ª»»³Ébyte[]
     */
    public static byte[] StringToByte(String img) {
        byte[] isoret = null;
        if (!ObjectUtil.isEmpty(img)) {
            String[] byteArry = StringUtil.split(img, "|");
            isoret = new byte[byteArry.length];
            for (int i = 0; i < byteArry.length; i++) {
                isoret[i] = Byte.valueOf(byteArry[i]);
            }
        }
        return isoret;
    }

    /**
     * ****************************************
     *
     * @package :com.crowdfunding.util
     * @class :ObjectUtil.java
     * @author :light
     * @date :2015Äê10ÔÂ21ÈÕ ÉÏÎç10:35:28
     * @descripe :List×ªString
     * ****************************************
     */
    public static String ListToString(List<?> list) {
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == null || list.get(i) == "") {
                    continue;
                }
                // Èç¹ûÖµÊÇlistÀàÐÍÔòµ÷ÓÃ×Ô¼º  
                if (list.get(i) instanceof List) {
                    sb.append(ListToString((List<?>) list.get(i)));
                    sb.append("#");
                } else {
                    sb.append(list.get(i));
                    sb.append("#");
                }
            }
        }
        return sb.toString();
    }

    /**
     * @param source ±»¸´ÖÆµÄ¶ÔÏó
     * @param dest   Ä¿±ê¶ÔÏó Jul 7, 2014 9:52:06 AM
     * @Title: copy
     * @Description: ¸´ÖÆ¶ÔÏóÖÐÍ¬ÃûµÄÊôÐÔÖÁÄ¿±ê¶ÔÏó
     */
    public static void copy(Object source, Object dest) {
        try {
            Field[] sourceFileds = source.getClass().getDeclaredFields();
            for (int i = 0; i < sourceFileds.length; i++) {
                try {
                    Field field = sourceFileds[i];
                    Object obj = field.get(source);
                    field.set(dest, obj);
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            DebugUtil.e("Exception", e.toString());
        }
    }

    /**
     * @param source ±»¸´ÖÆµÄ¶ÔÏó
     * @param dest   Ä¿±ê¶ÔÏó Jul 7, 2014 12:33:25 PM
     * @Title: copyNoNull
     * @Description: ¸´ÖÆ¶ÔÏóÖÐÍ¬ÃûµÄÊôÐÔÖÁÄ¿±ê¶ÔÏó(²»¸´ÖÆ¿ÕÖµ)
     */
    public static void copyNoNull(Object source, Object dest) {
        try {
            Field[] sourceFileds = source.getClass().getDeclaredFields();
            for (int i = 0; i < sourceFileds.length; i++) {
                try {
                    Field field = sourceFileds[i];
                    Object obj = field.get(source);
                    if (null != obj)
                        field.set(dest, obj);
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            DebugUtil.e(TAG, e.toString());
        }
    }
}
