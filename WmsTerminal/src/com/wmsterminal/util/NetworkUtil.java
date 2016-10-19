package com.wmsterminal.util;

import java.io.ByteArrayInputStream;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.wmsterminal.base.Config;
import com.wmsterminal.model.Attachments;
import com.wmsterminal.model.ImageZoom;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.lidroid.xutils.http.client.multipart.content.InputStreamBody;
import com.lidroid.xutils.http.client.multipart.content.StringBody;
import com.lidroid.xutils.util.PreferencesCookieStore;

/**
 * Created by Administrator on 13-11-26.
 */
public class NetworkUtil {

    // private static HttpUtils mHttpUtils;

    /**
     * @Title: getInstence
     * @Description: µ¥ÀýÄ£Ê½»ñÈ¡ÊµÀý¶ÔÏó
     * @author: LiuSiQing
     * @date: 2015-6-26 ÉÏÎç10:39:23
     */
    public synchronized static HttpUtils getInstence(Context context) {
        // ÉèÖÃÇëÇó³¬Ê±Ê±¼äÎª20Ãë
        HttpUtils httpUtils = new HttpUtils(Config.DEFAULT_SOCKET_TIMEOUT);
        httpUtils.configSoTimeout(Config.DEFAULT_SOCKET_TIMEOUT);
        // Ä¬ÈÏ±àÂëGBK
        httpUtils.configResponseTextCharset("UTF-8");
        // ±£´æ·þÎñÆ÷¶Ë(Session)µÄCookie
        PreferencesCookieStore cookieStore = new PreferencesCookieStore(context);
        cookieStore.clear(); // Çå³ýÔ­À´µÄcookie
        httpUtils.configCookieStore(cookieStore);
        return httpUtils;
    }


    /**
     * @Title put
     * @Description ·ÅÈëbody
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç6:13:00
     */
    public static MultipartEntity put(MultipartEntity entity, Map<String, String> map) {
        if (ObjectUtil.isEmpty(map) || null == entity)
            return entity;
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            if (!ObjectUtil.isEmpty(value))
                try {
                    entity.addPart(key, new StringBody(value, Charset.forName("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    DebugUtil.e(NetworkUtil.class.getSimpleName(), e.toString());
                }
        }
        return entity;
    }

    /**
     * @Title: put
     * @Description: ·ÅÈëbody
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç6:13:00
     */
    public static MultipartEntity put(MultipartEntity entity, List<String> list) {
        if (ObjectUtil.isEmpty(list) || null == entity)
            return entity;
        for (int i = 0; i < list.size(); i++) {
            byte[] bytes = ImageUtil.bitmapToByteZoomByPath(list.get(i));
            if (null != bytes)
                entity.addPart("file" + i, new InputStreamBody(new ByteArrayInputStream(bytes), bytes.length));
        }
        return entity;
    }

    /**
     * @Title: put
     * @Description: ·ÅÈëbody
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç6:13:00
     */
    public static MultipartEntity put(MultipartEntity entity, String key, List<String> list) {
        if (ObjectUtil.isEmpty(list) || null == entity)
            return entity;
        for (int i = 0; i < list.size(); i++) {
            DebugUtil.Loge("file:", list.get(i));
            File file = new File(list.get(i));
            if (null != file)
                entity.addPart(key, new FileBody(file));
        }
        return entity;
    }

    /**
     * @Title: put
     * @Description: ·ÅÈëbody
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç6:13:00
     */
    public static MultipartEntity putAttList(MultipartEntity entity, List<Attachments> list) throws FileNotFoundException, IOException {
        if (ObjectUtil.isEmpty(list) || null == entity)
            return entity;
        for (int i = 0; i < list.size(); i++) {
            byte[] bytes = ImageUtil.bitmapToByteZoomByPath(list.get(i).getFile_path());
            if (null != bytes)
                entity.addPart("file" + i, new InputStreamBody(new ByteArrayInputStream(bytes), bytes.length));
        }
        return entity;
    }

    /**
     * @param @param files
     * @throws IOException
     * @throws FileNotFoundException
     * @Title putHandleImageList
     * @Description ·ÅÈëÍ¼Æ¬¶ÔÏó
     */
    public static MultipartEntity putHandleImageList(MultipartEntity entity, List<ImageZoom> list) {
        if (ObjectUtil.isEmpty(list) || null == entity)
            return entity;
        for (int i = 0; i < list.size(); i++) {
            byte[] bytes = ImageUtil.bitmapToByteZoomByPath(list.get(i).getFile_path());
            if (null != bytes)
                entity.addPart("file" + i, new InputStreamBody(new ByteArrayInputStream(bytes), bytes.length));
        }
        return entity;
    }

    /**
     * TODO RequestParams
     */
    /**
     * @Title: put
     * @Description: ·ÅÈëÐÅÏ¢£¬ºóÌ¨ÐèÒªÊ¹ÓÃrequest.getParameter»ñµÃ²ÎÊý
     * @author: LiuSiQing
     * @date: 2015-7-15 ÏÂÎç4:36:22
     */
    public static RequestParams put(RequestParams params, Map<String, String> map) {
        if (ObjectUtil.isEmpty(map) || null == params)
            return params;
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            if (!ObjectUtil.isEmpty(value))
               // params.addQueryStringParameter(key, value);
            	//params.addBodyParameter(key, value);
            	Log.e("params",key+" "+value);
            	params.addBodyParameter(key, value);
              
        }
        return params;
    }

    /**
     * @Title: put
     * @Description: ·ÅÈëbody
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç6:13:00
     */
    public static RequestParams put(RequestParams params, List<String> list) {
        if (ObjectUtil.isEmpty(list) || null == params)
            return params;
        for (int i = 0; i < list.size(); i++) {
            byte[] bytes = ImageUtil.bitmapToByteZoomByPath(list.get(i));
            if (null != bytes)
                params.addBodyParameter("file" + i, new ByteArrayInputStream(bytes), bytes.length);
        }
        return params;
    }

    /**
     * @Title: put
     * @Description: ·ÅÈëbody
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç6:13:00
     */
    public static RequestParams putAttList(RequestParams params, List<Attachments> list) throws FileNotFoundException, IOException {
        if (ObjectUtil.isEmpty(list) || null == params)
            return params;
        for (int i = 0; i < list.size(); i++) {
            byte[] bytes = ImageUtil.bitmapToByteZoomByPath(list.get(i).getFile_path());
            if (null != bytes)
                params.addBodyParameter("file" + i, new ByteArrayInputStream(bytes), bytes.length);
        }
        return params;
    }

    /**
     * @param @param files
     * @throws IOException
     * @throws FileNotFoundException
     * @Title putHandleImageList
     * @Description ·ÅÈëÍ¼Æ¬¶ÔÏó
     */
    public static RequestParams putHandleImageList(RequestParams params, List<ImageZoom> list) {
        if (ObjectUtil.isEmpty(list) || null == params)
            return params;
        for (int i = 0; i < list.size(); i++) {
            byte[] bytes = ImageUtil.bitmapToByteZoomByPath(list.get(i).getFile_path());
            if (null != bytes)
                params.addBodyParameter("file" + i, new ByteArrayInputStream(bytes), bytes.length);
        }
        return params;
    }
}
