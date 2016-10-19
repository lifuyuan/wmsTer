package com.wmsterminal.base.fragment;

import com.wmsterminal.util.DebugUtil;


import android.support.v4.app.Fragment;

/**
 * @author light
 * @File DebugFragment.java
 * @Package com.crowdfunding.base.fragment
 * @Time 20152015Äê9ÔÂ2ÈÕÏÂÎç1:51:48
 * @Description FragmentÖÐ»ù±¾µ÷ÊÔ
 */
public class DebugFragment extends Fragment{

	protected void Logd(String tag,String msg) {
		DebugUtil.d(tag,msg);
	}

	protected void Loge(String tag, String msg) {
		DebugUtil.e(tag, msg);
	}

	protected void Logi(String tag, String msg) {
		DebugUtil.i(tag, msg);
	}

	protected void Logw(String tag, String msg) {
		DebugUtil.w(tag, msg);
	}

	protected void Logwtf(String tag, String msg) {
		DebugUtil.wtf(tag, msg);
	}

	protected void Logd(String msg) {
		DebugUtil.d(Thread.currentThread().getStackTrace()[1].getClassName()+"]"+"["+Thread.currentThread().getStackTrace()[1].getMethodName()+"]", msg);
	}

	protected void Loge(String msg) {
		DebugUtil.e(Thread.currentThread().getStackTrace()[1].getClassName()+"]"+"["+Thread.currentThread().getStackTrace()[1].getMethodName()+"]", msg);
	}

	protected void Logi(String msg) {
		DebugUtil.i(Thread.currentThread().getStackTrace()[1].getClassName()+"]"+"["+Thread.currentThread().getStackTrace()[1].getMethodName()+"]", msg);
	}

	protected void Logw(String msg) {
		DebugUtil.w(Thread.currentThread().getStackTrace()[1].getClassName()+"]"+"["+Thread.currentThread().getStackTrace()[1].getMethodName()+"]", msg);
	}

	protected void Logv(String msg) {
		DebugUtil.v(Thread.currentThread().getStackTrace()[1].getClassName()+"]"+"["+Thread.currentThread().getStackTrace()[1].getMethodName()+"]", msg);
	}

	protected void Logwtf(String msg) {
		DebugUtil.wtf(Thread.currentThread().getStackTrace()[1].getClassName()+"]"+"["+Thread.currentThread().getStackTrace()[1].getMethodName()+"]", msg);
	}
}
