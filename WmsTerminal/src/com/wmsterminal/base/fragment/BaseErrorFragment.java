package com.wmsterminal.base.fragment;

import com.wmsterminal.util.ErrorUtil;

import android.content.DialogInterface;

/**
 * @author light
 * @File BaseErrorFragment.java
 * @Package com.crowdfunding.base.fragment
 * @Time 20152015Äê9ÔÂ2ÈÕÏÂÎç3:07:16
 * @Description Fragment»ù´¡´íÎó´¦Àí
 */
public class BaseErrorFragment extends BaseResourcesFragment {

	/**
	 * @Title: showError
	 * @Description: ÏÔÊ¾´íÎóÌáÊ¾£¬Èç¹ûÓÐ´í·µ»Ø true
	 * @author: LiuSiQing
	 * @date: 2015-3-5 ÏÂÎç5:46:49
	 */
	protected boolean showErrorJson(String json) {
		return ErrorUtil.showErrorJson(getActivity(), json);
	}

	/**
	 * @Title showErrorJsonFinish
	 * @Description ÏÔÊ¾´íÎó£¬²¢¹Ø±ÕÒ³Ãæ
	 * @param json
	 * @return
	 */
	protected boolean showErrorJsonFinish(String json) {
		return ErrorUtil.showErrorJsonFinish(getActivity(), json);
	}
	
	/**
	 * @Title showErrorJsonAgainFinsh
	 * @Description ÔÙÊÔÒ»´Î
	 * @param error
	 * @return
	 */
	protected boolean showErrorJsonAgainFinsh(String json, DialogInterface.OnClickListener l) {
		return ErrorUtil.showErrorJsonAgainFinsh(getActivity(), json, l);
	}

	/**
	 * @Title: showErrorJson
	 * @Description: ÏÔÊ¾È«²¿´íÎóÐÅÏ¢
	 * @author: LiuSiQing
	 * @date: 2015-3-5 ÏÂÎç5:46:36
	 */
	protected boolean showError(String error) {
		return ErrorUtil.showError(getActivity(), error);
	}

	/**
	 * @Title: showErrorFinish
	 * @Description: ÏÔÊ¾´íÎóÐÅÏ¢£¬²¢¹Ø±Õ
	 * @author: LiuSiQing
	 * @date: 2015-3-5 ÏÂÎç5:46:36
	 */
	protected boolean showErrorFinish(String error) {
		return ErrorUtil.showErrorFinish(getActivity(), error);
	}

	/**
	 * @Title showNetworkError
	 * @Description ÊÇ·ñÊÇÍøÂç´íÎó
	 * @param json
	 * @return
	 */
	protected boolean isNetWorkErrorJson(String json) {
		return ErrorUtil.isNetWorkErrorJson(json);
	}

	/**
	 * @Title showNetworkErrorStr
	 * @Description ÊÇ·ñÊÇÍøÂç´íÎó
	 * @param error
	 * @return
	 */
	protected boolean isNetWorkError(String error) {
		return ErrorUtil.isNetWorkError(error);
	}

	/**
	 * @Title isError
	 * @Description ÊÇ·ñÊÇ´íÎóÐÅÏ¢
	 * @param json
	 * @return
	 */
	protected boolean isErrorJson(String json) {
		return ErrorUtil.isErrorJson(json);
	}
}
