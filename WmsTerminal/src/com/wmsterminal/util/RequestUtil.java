package com.wmsterminal.util;
import java.util.HashMap;

import java.util.Map;
//ÐÞ¸Ä 3
import com.wmsterminal.base.Config;
import com.wmsterminal.model.RequHeader;
import com.wmsterminal.model.RequestModel;
import com.google.gson.Gson;

public class RequestUtil {
    /**
     * <br>±êÌâ: ·â×°Êý¾Ý
     * <br>¼ò½é: ·â×°ÇëÇóÊý¾Ý£¬¸ñÊ½Îªkey=string£¬value=json¸ñÊ½
     * <br>×÷Õß: shuhaiyang
     * <br>Ê±¼ä: 2016/3/12 10:08
     */
    public static Map<String, String> requByJson(String key, Object value) {
        Map<String, String> map = new HashMap<>();
        map.put(key, new Gson().toJson(value));
        return map;
    }

    /**
     * <br>±êÌâ: ·â×°Êý¾Ý
     * <br>¼ò½é: ·â×°ÇëÇóÊý¾Ý£¬¸ñÊ½Îªkey=string£¬value=json¸ñÊ½
     * <br>×÷Õß: shuhaiyang
     * <br>Ê±¼ä: 2016/3/12 10:08
     */
    public static Map<String, String> requByJsonWithTokenOnly(String key, String token) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return requByJson(key, map);
    }

    /**
     * ¿ÕÍ·ÇëÇó
     */
    public static Map<String, String> RequIncludeHeaderAndMessage(Object o) {

        RequHeader requHeader = new RequHeader();
        requHeader.setSesssion_id("");
        RequestModel model = new RequestModel();
        model.setHead(requHeader);
        model.setMessage(o);

        String requ_message = ObjectUtil.BenToJson(model);
        Map<String, String> map = new HashMap<String, String>();//ÐÞ¸Ä
        map.put(Config.REQUEST_FLAG, requ_message);

        return map;
    }

    /**
     * ´«ÈëÇëÇóÊµÌå
     *
     * @param request
     * @return
     */
    public static Map<String, String> RequCommon(RequestModel request) {
        if (null == request) {
            return null;
        }
        String requ_message = ObjectUtil.BenToJson(request);
        Map<String, String> map = new HashMap<String, String>();
        map.put(Config.REQUEST_FLAG, requ_message);
        return map;

    }

    /**
     * messageÎªÒ»¸ömap
     */
    public static Map<String, String> RequCom(Map<String, String> map) {
        if (null == map) {
            return null;
        }
        RequHeader requHeader = new RequHeader();
        RequestModel model = new RequestModel();
        model.setHead(requHeader);
        Gson gson = new Gson();
        String message = gson.toJson(map);
        model.setMessage(message);
        String requ_message = ObjectUtil.BenToJson(model);
        Map<String, String> mapre = new HashMap<String, String>();
        mapre.put(Config.REQUEST_FLAG, requ_message);
        return mapre;
    }
}
