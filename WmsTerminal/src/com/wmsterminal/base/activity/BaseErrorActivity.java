package com.wmsterminal.base.activity;

import com.wmsterminal.util.ErrorUtil;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
/**
 * @Package: com.taskbillapp.base.activity
 * @ClassName: BaseResourcesActivity
 * @Description: »ù´¡´íÎóÐÅÏ¢³éÏóActivity
 * @author: LiuSiQing
 * @date: 2015-4-9 ÉÏÎç10:26:37
 */
@SuppressLint("HandlerLeak")
public abstract class BaseErrorActivity extends DebugFragmentActivity {

	/**
	 * @Title: showError
	 * @Description: ÏÔÊ¾´íÎóÌáÊ¾£¬Èç¹ûÓÐ´í·µ»Ø true
	 * @author: LiuSiQing
	 * @date: 2015-3-5 ÏÂÎç5:46:49
	 */
	protected boolean showErrorJson(String json) {
		return ErrorUtil.showErrorJson(this, json);
	}

	/**
	 * @Title showErrorJsonFinish
	 * @Description ÏÔÊ¾´íÎó£¬²¢¹Ø±ÕÒ³Ãæ
	 * @param json
	 * @return
	 */
	protected boolean showErrorFinish(String error) {
		return ErrorUtil.showErrorFinish(this, error);
	}

	/**
	 * @Title: showError
	 * @Description: ÏÔÊ¾´íÎóÌáÊ¾£¬Èç¹ûÓÐ´í·µ»Ø true
	 * @author: LiuSiQing
	 * @date: 2015-3-5 ÏÂÎç5:46:49
	 */
	protected boolean showError(String error) {
		return ErrorUtil.showError(this, error);
	}

	/**
	 * @Title showErrorJsonFinish
	 * @Description ÏÔÊ¾´íÎó£¬²¢¹Ø±ÕÒ³Ãæ
	 * @param json
	 * @return
	 */
	protected boolean showErrorJsonFinish(String json) {
		return ErrorUtil.showErrorJsonFinish(this, json);
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
	 * @Title isErrorJson
	 * @Description ÊÇ·ñÊÇ´íÎóÐÅÏ¢
	 * @param json
	 * @return
	 */
	protected boolean isErrorJson(String json) {
		return ErrorUtil.isErrorJson(json);
	}

	/**
	 * @Title isError
	 * @Description ÊÇ·ñÊÇ´íÎóÐÅÏ¢
	 * @param error
	 * @return
	 */
	protected boolean isError(String error) {
		return ErrorUtil.isError(error);
	}

	/**
	 * @Title showErrorJsonAgainFinsh
	 * @Description ÔÙÊÔÒ»´Î
	 * @param error
	 * @return
	 */
	protected boolean showErrorJsonAgainFinsh(String json, DialogInterface.OnClickListener l) {
		return ErrorUtil.showErrorJsonAgainFinsh(this, json, l);
	}
}
