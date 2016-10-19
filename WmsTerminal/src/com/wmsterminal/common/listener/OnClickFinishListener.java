package com.wmsterminal.common.listener;

import com.wmsterminal.util.CloseUtil;


import com.wmsterminal.util.DateUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author light
 * @File OnClickFinishListener.java
 * @Package com.crowdfunding.common.listener
 * @Time 2015Äê9ÔÂ6ÈÕÉÏÎç9:48:05
 * @Description µã»÷¹Ø±ÕActivityµÄ¼àÌý
 */
public class OnClickFinishListener implements OnClickListener, DialogInterface.OnClickListener {

	/**
	 * Activity
	 */
	private Activity mActivity;
	/**
	 * Dialog
	 */
	private Dialog mDialog;

	public OnClickFinishListener(Activity activity, Dialog dialog) {
		this.mActivity = activity;
		this.mDialog = dialog;
	}

	public OnClickFinishListener(Activity context) {
		this(context, null);
	}

	@Override
	public void onClick(View v) {
		if( DateUtil.isFastDoubleClick() || null == mActivity || mActivity.isFinishing() )
			return;
		finish();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if( DateUtil.isFastDoubleClick() || null == mActivity || mActivity.isFinishing() )
			return;
		finish();
		CloseUtil.dismiss(dialog);
	}

	/**
	 * @Title: finish
	 * @Description: ¹Ø±Õ
	 * @author: LiuSiQing
	 * @date: 2015-3-25 ÉÏÎç10:02:56
	 */
	private void finish() {
		mActivity.finish();
		CloseUtil.dismiss(mDialog);
	}
}
