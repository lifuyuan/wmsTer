package com.wmsterminal.common.thread;

import java.util.ArrayList;



import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.wmsterminal.base.Config;
import com.wmsterminal.model.Attachments;
import com.wmsterminal.model.ImageZoom;
import com.wmsterminal.util.CloseUtil;
import com.wmsterminal.util.DebugUtil;
import com.wmsterminal.util.ErrorUtil;
import com.wmsterminal.util.NetworkUtil;
import com.wmsterminal.util.ObjectUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.http.client.multipart.MultipartEntity;

/**
 * @Package: com.mngwyhouhzmb.common.thread
 * @ClassName: NetWorkTask
 * @Description: ÍøÂçÁ¬½ÓÏß³Ì
 * @author: LiuSiQing
 * @date: 2015-6-25 ÏÂÎç2:39:59
 */
public class NetWorkPost implements Runnable {

    private static final String TAG = NetWorkPost.class.getSimpleName();
    /**
     * ÏìÓ¦Öµ
     */
    private int mWhat;
    /**
     * ÍøÂçÁ¬½ÓÂ·¾¶
     */
    protected String mUrl;
    /**
     * Êý¾Ý
     */
    protected Map<String, String> mMap;
    /**
     * listÊý¾Ý
     */
    protected List<Map<String,String>> mDataList;
    /**
     * Í¼Æ¬Â·¾¶
     */
    protected List<String> mList;
    /**
     * Ö÷Ïß³Ì
     */
    private Handler mHandler;
    /**
     * Ë¯ÃßÊ±¼ä
     */
    private long mSleep = 0;
    /**
     * Context
     */
    protected Context mContext;
    /**
     * Ç°×ºµØÖ·
     */
    protected String mHttpPath;
    /**
     * Message Arg1
     */
    private int mArg1;
    /**
     * HttpMethod
     */
    protected HttpMethod mHttpMethod;
    
    private String mToken;

    public NetWorkPost(Context context, String url, Handler handler, int what) {
        this.mContext = context;
        this.mUrl = url;
        this.mHandler = handler;
        this.mWhat = what;
    }

    public NetWorkPost(Context context, String url, Handler handler) {
        this(context, url, handler, 0);
    }

    public NetWorkPost(Context context, String url) {
        this(context, url, null);
    }
    
    /**
     * ÉèÖÃÊý¾Ý
     * @param list
     * @return
     */
    
    public NetWorkPost setListOfData(List<Map<String,String>> list){
	
    	this.mDataList = list;
    	return this;
   	}

    /**
     * @Title: setMapOfData
     * @Description: ÉèÖÃÊý¾Ý
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç2:49:21
     */
    public NetWorkPost setMapOfData(Map<String, String> map,String token) {
        this.mMap = map;
        this.mToken = token;
        return this;
    }
    
    public NetWorkPost setMapOfData(Map<String, String> map) {
        this.mMap = map;
        return this;
    }


    /**
     * @Title: setListOfString
     * @Description: ÉèÖÃ¸½¼þµØÖ·ÐÅÏ¢
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç2:52:44
     */
    public NetWorkPost setListOfString(List<String> list) {
        this.mList = list;
        return this;
    }

    /**
     * @Title: setListOfAttachments
     * @Description: ÉèÖÃ¸½¼þµØÖ·ÐÅÏ¢
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç2:52:41
     */
    public NetWorkPost setListOfAttachments(List<Attachments> list) {
        List<String> temp = new ArrayList<String>();
        for (Attachments att : list) {
            temp.add(att.getFile_path());
        }
        this.mList = temp;
        return this;
    }

    /**
     * @Title: setListOfAttachments
     * @Description: ÉèÖÃ¸½¼þµØÖ·ÐÅÏ¢
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç2:52:41
     */
    public NetWorkPost setListOfHandleImage(List<ImageZoom> list) {
        List<String> temp = new ArrayList<String>();
        for (ImageZoom handle : list) {
            temp.add(handle.getFile_path());
        }
        this.mList = temp;
        return this;
    }

    /**
     * @Title: setSleep
     * @Description: ÉèÖÃË¯ÃßÊ±¼ä
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç2:53:13
     */
    public NetWorkPost setSleep(long sleep) {
        this.mSleep = sleep;
        return this;
    }

    /**
     * @Title: setHttpPath
     * @Description: ÉèÖÃÁ¬½ÓµØÖ·Ç°×º
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç2:53:24
     */
    public NetWorkPost setHttpPath(String http) {
        this.mHttpPath = http;
        return this;
    }

    /**
     * @Title: setArgOne
     * @Description: ÉèÖÃArg1
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç3:09:08
     */
    public NetWorkPost setArgOne(int arg1) {
        this.mArg1 = arg1;
        return this;
    }

    /**
     * @Title: setHttpMethod
     * @Description: ÉèÖÃHttpMethod
     * @author: LiuSiQing
     * @date: 2015-7-15 ÏÂÎç1:24:20
     */
    public NetWorkPost setHttpMethod(HttpMethod httpMethod) {
        this.mHttpMethod = httpMethod;
        return this;
    }

    /**
     * HttpHandler
     */
    protected HttpHandler<String> mHttpHandler;

    @Override
    public void run() {
        try {
            sleep();
//			setSessionInfo();
            HttpUtils http = NetworkUtil.getInstence(mContext);
            RequestParams params = new RequestParams("utf-8");
            MultipartEntity entity = new MultipartEntity();
            //NetworkUtil.put(params, mMap);
            if(mMap != null){
            	Log.e("request", mMap.get("reqJson"));
            	params.setBodyEntity(new StringEntity(mMap.get("reqJson"), "utf-8"));
            }
            if (mToken != null) 
                {params.addHeader("authorization",mToken);	
            	Log.e("token", mToken);}
            if (mList != null)
                DebugUtil.Loge("size", mList.size() + "");
            //NetworkUtil.put(entity, "attachment", mList);
            if (null == mHttpPath)
            { mHttpPath = Config.BASE_URL;
        	   Log.e("token1", mUrl);
            }
            if (null == mHttpMethod)
                mHttpMethod = HttpMethod.POST;
            	
            Log.e("method", ""+mHttpMethod);
            	mHttpHandler = http.send(mHttpMethod,mHttpPath+mUrl,params, new NetWorkCallBack());
        } catch (Exception e) {
            DebugUtil.e(TAG, e.toString());
            sendHandler(e.toString());
        }
    }
   
    /**
     * @Title: cancel
     * @Description: È¡Ïû
     * @author: LiuSiQing
     * @date: 2015-8-2 ÏÂÎç6:03:55
     */
    public void cancel() {
        CloseUtil.cancel(mHttpHandler);
    }

    /**
     * @Title: sendHandler
     * @Description: ÍÆËÍ¸øUIÏß³Ì
     * @author: LiuSiQing
     * @date: 2015-6-26 ÉÏÎç10:52:26
     */
    protected void sendHandler(String json) {
        if (null == mHandler)
            return;
        Message msg = mHandler.obtainMessage();
        msg.what = mWhat;
        msg.obj = json;
        msg.arg1 = mArg1;
        mHandler.sendMessage(msg);  // ÍÆËÍ¸øÖ÷UIÏß³Ì
    }

    /**
     * @Package: com.mngwyhouhzmb.common.thread
     * @ClassName: NetWorkCallBack
     * @Description: »Øµ÷
     * @author: LiuSiQing
     * @date: 2015-6-26 ÉÏÎç10:51:12
     */
    protected class NetWorkCallBack extends RequestCallBack<String> {

        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            if (Config.DEBUG) {
                Log.e("request1:", getRequestUrl());
                Log.e("response:", responseInfo.result);                               
            }
            sendHandler(responseInfo.result);
        }

        @Override
        public void onFailure(HttpException error, String msg) {
            Log.e("request2:", getRequestUrl());
            Log.i("´íÎó·µ»Ø£º", error.getMessage() + "\n" + msg);
            sendHandler(ErrorUtil.getMessage(Config.NETWORK_ERROR));
//			sendHandler(ObjectUtil.isEmpty(msg) ? error.toString() : msg);
        }
    }

    /**
     * @Title: sleep
     * @Description: sleep
     * @author: LiuSiQing
     * @date: 2015-6-25 ÏÂÎç6:33:16
     */
    protected void sleep() {
        try {
            if (mSleep > 0)
                Thread.sleep(mSleep);
        } catch (Exception e) {
            DebugUtil.e(TAG, e.toString());
        }
    }

//	/**
//	 * @Title: setSessionInfo
//	 * @Description: ÉèÖÃSessionÐÅÏ¢
//	 * @author: LiuSiQing
//	 * @date: 2015-6-25 ÏÂÎç2:57:25
//	 */
//	protected void setSessionInfo() {
//		if( null == mMap )
//			mMap = new HashMap<String, String>();
//		String session_id = SharedUtil.getUserinfo(mContext, "Accessid");
//		if( !ObjectUtil.isEmpty(session_id) )
//			mMap.put("session_id", session_id);
//		if( !ObjectUtil.isEmpty(mMap.get("au_name")) )
//			return;
//		String au_name = SharedUtil.getUserinfo(mContext, "au_name");
//		if( !ObjectUtil.isEmpty(au_name) )
//			mMap.put("au_name", au_name);
//	}
}
