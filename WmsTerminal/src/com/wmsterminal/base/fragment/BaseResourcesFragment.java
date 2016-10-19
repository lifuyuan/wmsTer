package com.wmsterminal.base.fragment;

import com.wmsterminal.base.Config;

//import com.allinmall.common.dialog.CustomDialog;
//ÐÞ¸Ä1 
import com.wmsterminal.util.NetworkStatusManager;

import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;

/**
 * @Package: com.taskbillapp.base.activity
 * @ClassName: BaseResourcesActivity
 * @Description: »ù´¡×ÊÔ´³éÏóActivity
 * @author: LiuSiQing
 * @date: 2015-4-9 ÉÏÎç10:26:10
 */
public abstract class BaseResourcesFragment extends DebugFragment {

	/**
	 * @Title: getColor
	 * @Description: »ñµÃÑÕÉ«
	 * @author: LiuSiQing
	 * @date: 2015-3-26 ÉÏÎç10:43:24
	 */
	protected int getColor(int id) {
		return getResources().getColor(id);
	}

	/**
	 * @Title: getDimension
	 * @Description: »ñµÃdimen
	 * @author: LiuSiQing
	 * @date: 2015-3-26 ÉÏÎç10:43:24
	 */
	protected float getDimension(int id) {
		return getResources().getDimension(id);
	}

	/**
	 * @Title: getDimensionInt
	 * @Description: »ñµÃdimen int
	 * @author: LiuSiQing
	 * @date: 2015-3-26 ÉÏÎç10:43:24
	 */
	protected int getDimensionInt(int id) {
		return (int)getDimension(id);
	}

	/** 
	 * TODO ÎÄ×ÖÐÅÏ¢
	 */
//	/**
//	 * @Title: getMaoHao
//	 * @Description: Ã°ºÅ
//	 * @author: LiuSiQing
//	 * @date: 2015-3-14 ÉÏÎç11:19:12
//	 */
//	protected String getStringMaoHao() {
//		return getString(R.string.maohao);
//	}

	/**
	 * TODO ¶¯»­Ð§¹ûSTART
	 * @Title: setViewGoneAnimation
	 * @Description: Ïû³ý¶¯»­²¢Òþ²Ø
	 * @author: LiuSiQing
	 * @date: 2015-3-25 ÉÏÎç10:51:37
	 */
	protected void setViewGoneAnimation(View view) {
		view.clearAnimation();
		view.setVisibility(View.GONE);
	}

	/**
	 * @Title: setViewGoneAnimation
	 * @Description: Ïû³ý¶¯»­²¢Òþ²Ø
	 * @author: LiuSiQing
	 * @date: 2015-3-25 ÉÏÎç10:51:37
	 */
	protected void setViewVisibleAnimation(View view, Animation animation) {
		view.setVisibility(View.VISIBLE);
		view.startAnimation(animation);
	}

	/**
	 * @Title: setVisibility
	 * @Description: ÉèÖÃÏÔÊ¾
	 * @author: LiuSiQing
	 * @date: 2015-3-11 ÏÂÎç3:22:07
	 */
	public void setVisibility(int visibility) {
		if( null != getView() )
			getView().setVisibility(visibility);
	}

	/**
	* @Title: checkNetworkStatusIs
	* @Description: ÅÐ¶ÏÍøÂçÊÇ·ñÁ¬½Ó
	* @author: LiuSiQing
	* @date: 2015-7-27 ÏÂÎç1:51:48
	*/
	protected boolean checkNetworkStatusIsConnected() {
		if( NetworkStatusManager.getInstance(getActivity()).isConnected() )
			return true;
		return false;
	}

	/**
	 * @Title: checkNetworkStatusIs
	 * @Description: ÅÐ¶ÏÍøÂçÊÇ·ñÁ¬½Ó£¬Èç¹ûÎ´Á¬½ÓÔòµ¯³ö¶Ô»°¿ò
	 * @author: LiuSiQing
	 * @date: 2015-7-27 ÏÂÎç1:51:48
	 */
	protected boolean checkNetworkStatusIsConnectedAndShowDialog() {
		boolean isConnected = checkNetworkStatusIsConnected();
		if( !isConnected ) {
			//ÐÞ¸ÄCustomDialog.showBuilderOne(getActivity(), Config.ERROR_NETWORK_OFF);
			reset();
		}
		return isConnected;
	}

	/**
	 * @Title: reset
	 * @Description: ¸´Î»
	 * @author: LiuSiQing
	 * @date: 2015-7-27 ÏÂÎç1:55:51
	 */
	protected void reset() {
	}
	
	/**
	 * ÉèÖÃ×´Ì¬À¸ÎªÍ¸Ã÷
	 *****************************************
	 * @package   :com.crowdfunding.base.fragment  				
	 * @class     :BaseResourcesFragment.java     				
	 * @author    :light          				
	 * @date      :2015Äê11ÔÂ12ÈÕ ÏÂÎç12:15:56  				
	 * @descripe  :TODO          				
	 *****************************************
	 */
	protected void setStatusTransparent(){
		getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	}
}
