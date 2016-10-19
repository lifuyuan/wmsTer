package com.wmsterminal.activity;

import java.io.IOException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wmsterminal.R;
import com.wmsterminal.base.activity.BaseActivity;
import com.wmsterminal.func.HadwareControll;

public class ScanSeparateActivity extends BaseActivity {

	public static final String KEY_RECEIVEER_ACTION = "com.idatachina.SCANKEYEVENT";
	@ViewInject(R.id.et_no_shelf)
	private EditText etShelf;
	
	@ViewInject(R.id.et_no_sku)
	private EditText etSku;
	
	@ViewInject(R.id.tv_stock_next)
	private TextView tvSepNext;
	
	public Handler mHandler = new MainHandler();
	//public ReadDoSomeThing readDoSomeThing;
	static ScanSeparateActivity ss;
	protected MediaPlayer mediaPlayer = null;
	HadwareControll controll = new HadwareControll(this);
	public boolean btn_scan =false;
	public  boolean et_scan = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		controll.Open();
		controll.m_handler = mHandler;
		byte []ff = null;
		ss = this;
		IntentFilter m_keyfilter = new IntentFilter();
		m_keyfilter.addAction(KEY_RECEIVEER_ACTION);
		this.registerReceiver(_keyReceiver,m_keyfilter);
	}
	
	@Override
	protected int getViewLayout() {
		// TODO Auto-generated method stub
		return R.layout.index_stocktaking;
	}
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		ViewUtils.inject(this);
	}
	@Override
	protected void initSet() {
		// TODO Auto-generated method stub
		super.initSet();
	
	}	
	
	private BroadcastReceiver _keyReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			
			  Log.d("012","action:"+intent.getAction());
				String key_action = intent.getStringExtra("action");
				String key_code = intent.getStringExtra("code");
			}
		
		};
	
	private class MainHandler extends Handler{
		
		public void handleMessage(Message msg){
			
			switch (msg.what) {
			case HadwareControll.BARCODE_READ:
				 
				  String result  = msg.obj+"";
				 if (et_scan) {
					etShelf.append(result);
					et_scan = false;
				}else{
					etSku.append(result);
					et_scan = true;
				}
				break;
				
			

			default:
				break;
			}
		}
	}
	
	private String toHexString(byte[] byteArray, int size) {
		if (byteArray == null || byteArray.length < 1)
			throw new IllegalArgumentException(
					"this byteArray must not be null or empty");
		final StringBuilder hexString = new StringBuilder(2 * size);
		for (int i = 0; i < size; i++) {
			if ((byteArray[i] & 0xff) < 0x10)//
				hexString.append("0");
			hexString.append(Integer.toHexString(0xFF & byteArray[i]));
			if (i != (byteArray.length - 1))
				hexString.append("");
		}
		return hexString.toString().toUpperCase();
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		Log.d("012","keyCode:"+keyCode);
		if ((keyCode == KeyEvent.KEYCODE_F9 || keyCode == KeyEvent.KEYCODE_F10
				|| keyCode == KeyEvent.KEYCODE_F11)&&event.getRepeatCount()==0) {
			controll.scan_start();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_F9 || keyCode == KeyEvent.KEYCODE_F10
				|| keyCode == KeyEvent.KEYCODE_F11) {
			controll.scan_stop();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	private void playBeepSound() {
		if (mediaPlayer != null) {
			mediaPlayer.start();
		}
		
		//Controll.playbeep();
		

	}

	private void initBeepSound() {
		if (mediaPlayer == null) {
			mediaPlayer = new MediaPlayer();
			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				if (mediaPlayer != null) {
					mediaPlayer.setDataSource(file.getFileDescriptor(),
							file.getStartOffset(), file.getLength());
					file.close();

					mediaPlayer.prepare();
				}
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	protected void onDestroy() {
		controll.Close();
		controll.m_handler = null;

		super.onDestroy();
	}


	
}
