package com.wmsterminal.common.listener;

import java.io.Serializable;


import java.util.Iterator;
import java.util.Map;

import com.wmsterminal.util.CloseUtil;
import com.wmsterminal.util.ObjectUtil;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 
 ****************************************
 * @package  :com.allinmall.common.listener
 * @author   :light
 * @date     :2015Äê11ÔÂ12ÈÕ ÏÂÎç4:11:50
 * @descripe :TODO
 ****************************************
 */
@SuppressWarnings("rawtypes")
public class OnClickIntentListener implements DialogInterface.OnClickListener, OnClickListener {

	/**
	 * activityÉÏÏÂÎÄÓÃÓÚIntentÌø×ª
	 */
	private Activity mActivity;
	/**
	 * IntentÌø×ªµÄÏÂ¸öclass
	 */
	private Class mClazz;
	/**
	 * ÊÇ·ñ¹Ø±Õactivity
	 */
	private boolean mClose = false;
	/**
	 * IntentÌø×ªÖÐ´«µÝÊý¾Ý
	 */
	private Map<String, Object> mMap;

	public OnClickIntentListener(Activity activity, Class clazz) {
		this.mActivity = activity;
		this.mClazz = clazz;
	}

	public OnClickIntentListener(Activity activity, Class clazz, boolean close) {
		this(activity, clazz);
		this.mClose = close;
	}

	public OnClickIntentListener(Activity activity, Class clazz, Map<String, Object> map) {
		this(activity, clazz);
		this.mMap = map;
	}

	public OnClickIntentListener(Activity activity, Class clazz, boolean close, Map<String, Object> map) {
		this(activity, clazz, close);
		this.mMap = map;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		intent();
		CloseUtil.dismiss(dialog);
	}

	@Override
	public void onClick(View v) {
		intent();
	}

	/**
	 * @Title: intent
	 * @Description: Ìø×ª
	 * @author: LiuSiQing
	 * @date: 2015-6-30 ÏÂÎç1:46:09
	 */
	private void intent() {
		Intent intent = new Intent(mActivity, mClazz);
		if( !ObjectUtil.isEmpty(mMap) ) {
			Iterator<String> mIterator = mMap.keySet().iterator();
			while( mIterator.hasNext() ) {
				String key = mIterator.next();
				Object obj = mMap.get(key);
				if( null == obj )
					continue;
				if( obj instanceof String )
					intent.putExtra(key, (String)obj);
				else if( obj instanceof Integer )
					intent.putExtra(key, (Integer)obj);
				else if( obj instanceof Boolean )
					intent.putExtra(key, (Boolean)obj);
				else if( obj instanceof Float )
					intent.putExtra(key, (Float)obj);
				else if( obj instanceof Double )
					intent.putExtra(key, (Double)obj);
				else if( obj instanceof Serializable )
					intent.putExtra(key, (Serializable)obj);
				else if( obj instanceof Parcelable )
					intent.putExtra(key, (Parcelable)obj);
			}
		}
		mActivity.startActivity(intent);
		if( mClose )
			mActivity.finish();
	}
}
