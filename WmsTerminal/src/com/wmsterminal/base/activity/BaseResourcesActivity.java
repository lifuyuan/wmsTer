package com.wmsterminal.base.activity;


import com.wmsterminal.activity.scan.PalletActivity;
import com.wmsterminal.base.BaseApplication;


import com.wmsterminal.base.Config;
//import com.allinmall.common.dialog.CustomDialog;
//ÐÞ¸Ä1
import com.wmsterminal.util.DateUtil;
import com.wmsterminal.util.IntentUtil;
import com.wmsterminal.util.NetworkStatusManager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.TextView;

/**
 * @Package: com.taskbillapp.base.activity
 * @ClassName: BaseResourcesActivity
 * @Description: »ù´¡×ÊÔ´³éÏóActivity
 * @author: LiuSiQing
 * @date: 2015-4-9 ÉÏÎç10:26:10
 */
@SuppressLint("Override") public abstract class BaseResourcesActivity extends BaseErrorActivity {

	/**
	 * ¸Ä±ä
	 */
	protected boolean mChange;
	/**
	 * ½ø¶ÈÌõ
	 */
	protected ProgressDialog mProgressDialog;
	/**
	 * ´´½¨ÊÓÍ¼
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		((BaseApplication)getApplication()).addActivity(this);
		systemTint();
	}

	@TargetApi(Build.VERSION_CODES.KITKAT)
	protected void systemTint() {
		
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//ÉèÖÃ³ÉÈ«ÆÁÄ£Ê½  
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//ÊúÆÁ 
//		// Í¸Ã÷×´Ì¬À¸
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//		// Í¸Ã÷µ¼º½À¸
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//		SystemBarTintManager tintManager = new SystemBarTintManager(this);
//		// ¼¤»î×´Ì¬À¸ÉèÖÃ
//		tintManager.setStatusBarTintEnabled(true);
//		// ¼¤»îµ¼º½À¸ÉèÖÃ
//		tintManager.setNavigationBarTintEnabled(true);
		// ÉèÖÃÒ»¸öÑÕÉ«¸øÏµÍ³À¸
		// tintManager.setTintColor(Color.parseColor("#99000FF"));
//		tintManager.setStatusBarTintResource(R.drawable.bg_header_blue_deep);
//		tintManager.setTintResource(R.drawable.bg_header_blue_deep);
		// ÉèÖÃÒ»¸öÑùÊ½±³¾°¸øµ¼º½À¸
		// tintManager.setNavigationBarTintResource();
		// ÉèÖÃÒ»¸ö×´Ì¬À¸×ÊÔ´
		// tintManager.setStatusBarTintDrawable(MyDrawable);
	}

	/**
	 * TODO Activity¹ÜÀíStart ÐÂÔöactivity
	 */
	public BaseResourcesActivity() {
		
	}

	/**
	 * @Title: finish
	 * @Description: finish RESULT_OK
	 * @author: LiuSiQing
	 * @date: 2015-6-29 ÉÏÎç11:48:09
	 */
	public void finishBefore(Intent intent) {
		if( mChange )
			setResult(RESULT_OK, intent);
	}

	@Override
	public void finish() {
		finishBefore(getIntent());
		super.finish();
		((BaseApplication)getApplication()).removeActivity(this);
	}

	/**
	 * ¸Ä±äÒ³Ãæ
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if( keyCode == KeyEvent.KEYCODE_BACK ) // °´ÏÂ¼üÅÌÉÏ·µ»Ø°´Å¥
			finishBefore(getIntent());
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * @Title: finishAndRemove
	 * @Description: ¹Ø±Õ²¢ÒÆ³ö
	 * @author: LiuSiQing
	 * @date: 2015-3-25 ÉÏÎç10:28:14
	 */
	protected void finishAndRemove() {
		((BaseApplication)getApplication()).removeActivityFinish(this);
	}

	/**
	 * TODO ×ÊÔ´¹ÜÀíSTART Ä¬ÈÏ»ù´¡ÅäÖÃ£¬²»ÊÜandroidÏµÍ³¶¨ÒåÓ°Ïì
	 */
	@Override
	public Resources getResources() {
		Resources res = super.getResources();
		Configuration config = new Configuration();
		config.setToDefaults();
		res.updateConfiguration(config, res.getDisplayMetrics());
		return res;
	}

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
	 * @author light
	 * @date 2016Äê1ÔÂ15ÈÕÏÂÎç1:35:56
	 * @package com.allinmall.base.activity
	 * @param id
	 * @return
	 * @descripe TODO
	 */
	protected float getDimension(int id) {
		return getResources().getDimension(id);
	}

	/**
	 * @author light
	 * @date 2016Äê1ÔÂ15ÈÕÏÂÎç1:36:03
	 * @package com.allinmall.base.activity
	 * @param id
	 * @return
	 * @descripe TODO
	 */
	protected int getDimensionInt(int id) {
		return (int)getDimension(id);
	}

	/**
	 * TODO ¶¯»­Ð§¹ûSTART
	 * 
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
	 * ÉèÖÃ´ÖÌå×Ö
	 */
	protected void setFakeBoldText(int id) {
		setFakeBoldText(null, id);
	}

	/**
	 * ÉèÖÃ´ÖÌå×Ö
	 */
	protected void setFakeBoldText(View view, int id) {
		TextView text;
		if( null != view )
			text = (TextView)view.findViewById(id);
		else
			text = (TextView)findViewById(id);
		if( null == text )
			return;
		text.getPaint().setFakeBoldText(true);
	}

//	/**
//	 * TODO ¼«¹âÍÆËÍSTART
//	 * ÆÁÄ»¸ßÁÁ
//	 */
//	@Override
//	protected void onResume() {
//		JPushInterface.onResume(this);
//		// getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // Ó¦ÓÃÔËÐÐÊ±£¬±£³ÖÆÁÄ»¸ßÁÁ£¬²»ËøÆÁ
//		super.onResume();
//	}
//
//	/**
//	 * Çå³ý¸ßÁÁ
//	 */
//	@Override
//	protected void onPause() {
//		JPushInterface.onPause(this);
//		// getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // Çå³ýËø
//		super.onPause();
//	}

	/**
	 * @Title: isFastDoubleClick
	 * @Description: ÊÇ·ñÊÇ¿ìËÙË«»÷
	 * @author: LiuSiQing
	 * @date: 2015-5-14 ÉÏÎç11:22:37
	 */
	protected boolean isFastDoubleClick() {
		return DateUtil.isFastDoubleClick();
	}

	/**
	 * @Title: checkNetworkStatusIs
	 * @Description: ÅÐ¶ÏÍøÂçÊÇ·ñÁ¬½Ó
	 * @author: LiuSiQing
	 * @date: 2015-7-27 ÏÂÎç1:51:48
	 */
	protected boolean checkNetworkStatusIsConnected() {
		if( NetworkStatusManager.getInstance(this).isConnected() )
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
			//ÐÞ¸ÄCustomDialog.showBuilderOne(this, Config.ERROR_NETWORK_OFF);
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
	
	protected void setStatusTransparent(){
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	}
	
	/**
	 * @author light
	 * @date 2016Äê1ÔÂ15ÈÕÏÂÎç12:03:47
	 * @package com.allinmall.base.activity
	 * @descripe ÏÔÊ¾½ø¶ÈÌõ
	 */
	protected void showProgressDialog(String message){
		mProgressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setMessage(message);
		mProgressDialog.show();
	}
	
	/**
	 * @author light
	 * @date 2016Äê1ÔÂ15ÈÕÏÂÎç12:05:21
	 * @package com.allinmall.base.activity
	 * @descripe Òþ²Ø½ø¶ÈÌõ
	 */
	protected void hideProgressDialog(){
		if (null != mProgressDialog) {
			mProgressDialog.dismiss();
		}
	}
	
	/**
	 *  @author yue
	 *	@descripe ´´½¨¶Ô»°¿ò
	 */
	protected void showDialog(final Activity ct ,final Class cx ,String messages,String title){		
		
			//³õÊ¼»¯Ò»¸öAlertDialog¶ÔÏó
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			
			//ÎªdialogÉèÖÃÍ¼±ê
			//alertDialog.setIcon(getResources().getDrawable(R.drawable.advise));
			
			//ÎªdialogÉèÖÃ±êÌâ
			alertDialog.setTitle(title);
		    alertDialog.setMessage(messages);
		
			//ÎªdialogÉèÖÃÒ»¸ö°´Å¥
			alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Continue", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					IntentUtil.startActivity(ct,cx);				}
			});	
			
	       alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Confirm", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();				}
			});	
		   alertDialog.show();
		
		
	}
	
	protected void showDialog(String messages,String title){		
		
		//³õÊ¼»¯Ò»¸öAlertDialog¶ÔÏó
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		
		//ÎªdialogÉèÖÃÍ¼±ê
		//alertDialog.setIcon(getResources().getDrawable(R.drawable.advise));
		
		//ÎªdialogÉèÖÃ±êÌâ
		alertDialog.setTitle(title);
	    alertDialog.setMessage(messages);
	
		
       alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Confirm", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();				}
		});	
	   alertDialog.show();
	
	
}
	
}
