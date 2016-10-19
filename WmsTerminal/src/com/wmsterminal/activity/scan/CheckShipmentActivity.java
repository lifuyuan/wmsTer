package com.wmsterminal.activity.scan;

import java.io.IOException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


import android.app.AlertDialog;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewManager;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wmsterminal.R;
import com.wmsterminal.base.activity.BaseActivity;
import com.wmsterminal.common.thread.NetWorkPost;
import com.wmsterminal.common.thread.TaskExecutor;
import com.wmsterminal.func.HadwareControll;
import com.wmsterminal.model.CheckShipment;
import com.wmsterminal.util.IntentUtil;
import com.wmsterminal.util.ObjectUtil;
import com.wmsterminal.util.RequestUtil;
import com.wmsterminal.util.SharedUtil;

public class CheckShipmentActivity extends BaseActivity implements View.OnClickListener{

	@ViewInject(R.id.et_parcelno_cs)
	private EditText etParcel;
	
	@ViewInject(R.id.et_shipmentno_cs)
	private EditText etShipment;
	
	
	@ViewInject(R.id.ll_shipment_scan)
	private LinearLayout llShipment;
	
	private RelativeLayout rlShip;
	
	private LinkedList<EditText> etLink = null;
	
	public static final String KEY_RECEIVEER_ACTION = "com.idatachina.SCANKEYEVENT";

	public Handler mHandler = new MainHandler();
	//public ReadDoSomeThing readDoSomeThing;
	static ScanReturnParcelActivity ss;
	protected MediaPlayer mediaPlayer = null;
	HadwareControll controll = new HadwareControll(this);
	public boolean btn_scan =false;
	public  boolean et_scan = true;
	private boolean skuno = false;

	private int number = 2 ;
	private int index = 2 ;
	private int etIndex = 1;
	private int nuIndex = 3; 
	
    @Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		controll.Open();
		controll.m_handler = mHandler;
	    IntentFilter m_keyfilter = new IntentFilter();
		m_keyfilter.addAction(KEY_RECEIVEER_ACTION);
		this.registerReceiver(_keyReceiver,m_keyfilter);
	}
	
	@Override
	protected void initSet() {
		// TODO Auto-generated method stub
		super.initSet();
		etLink = new LinkedList<EditText>();
		etLink.add(etParcel);
		etLink.add(etShipment);
	}
	
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		View view = getLayoutInflater().inflate(R.layout.scan_chshipment,null);
		mLinearLayout.addView(view);
		ViewUtils.inject(this);
	}

	
	
	private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			hideProgressDialog();
			String json = msg.obj.toString();
			CheckShipment checkShip = null;
					try {
						checkShip =  JSON.parseObject(json, CheckShipment.class);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
			if (checkShip.getStatus().equals("success")) {
				showDialog(CheckShipmentActivity.this,CheckShipmentActivity.class,"submit success","Success");
			}else{
			    showDialog(CheckShipmentActivity.this,CheckShipmentActivity.class,checkShip.getReason(),"Failure");	
			}									
		};
	};	

	private void addContent(){
		View view =null;
		EditText etSkuNo = null;
		TextView tvSkuNo = null;
		if (view ==null) {			
			view = this.getLayoutInflater().inflate(R.layout.item_skuno_list,null);
			llShipment.addView(view);
			etSkuNo = (EditText) view.findViewById(R.id.et_input_sku);
			tvSkuNo = (TextView) view.findViewById(R.id.tv_sku_list);
			rlShip = (RelativeLayout) view.findViewById(R.id.rl_pallet);
			view.setTag(etSkuNo);
			view.setTag(tvSkuNo);
			view.setTag(rlShip);
		}else{
			etSkuNo = (EditText) view.getTag();
			tvSkuNo = (TextView) view.getTag();
			rlShip = (RelativeLayout) view.getTag();
		}
			etSkuNo.requestFocus();
			if (nuIndex%2 != 0) {
				number = (nuIndex+1)/2;
				tvSkuNo.setText("Parcel No "+number+". :");
			}else{
				number = nuIndex/2;
				tvSkuNo.setText("ShipMent No "+number+". :");
				tvSkuNo.setBackgroundColor(Color.argb(255,220,220,220));
				rlShip.setBackgroundColor(Color.argb(255,220,220,220));
			}			
			etLink.add(index,etSkuNo);
			nuIndex++;
	        index++;
		}	
	
	private class MainHandler extends Handler{
		
		public void handleMessage(Message msg){
			
			switch (msg.what) {
			case HadwareControll.BARCODE_READ:
				 
				  String result  = msg.obj+"";
				  
				  if (etParcel.isFocused() == true) {
					  if ( result.length() == 12) {
						  etParcel.setText(result);
						  etShipment.requestFocus();
					  }else{
						  showDialog("please input parcel number", "error");	
						  }					  
				  }else if (etShipment.isFocused() == true) {
					  if (result.length() == 12) {
						  etShipment.setText(result);
						  skuno = true;
					  }else{
						  showDialog("please input shipment number", "error");
					  }					
				  }	
				
				  if (skuno == true) {
					if (etIndex == 1) {
						addContent();
						etIndex++;
					}else{
						for (int i = 2; i < etLink.size(); i++) {
							if (etLink.get(i).isFocused() == true) {
								if(result.length() == 12){	
									etLink.get(i).setText(result);
									if(i == etLink.size()-1)									
									{addContent();
									 etIndex++;									 
									}else{
										etLink.get(++i).requestFocus(); 
									 }
									Log.e("", ""+etIndex);
									if (etIndex%2 == 0&&!ObjectUtil.isEmpty(etLink.get(etIndex-2).getText().toString().trim())
											&&!ObjectUtil.isEmpty(etLink.get(etIndex-1).getText().toString().trim())) 
									{
											loadView(etLink.get(etIndex-2).getText().toString().trim()
													, etLink.get(etIndex-1).getText().toString().trim());
									}
								}else{
									if (i%2==0) {
										  showDialog("please input parcel number", "error");	
									}else{
										  showDialog("please input shipment number", "error");
										 
									}
								}								
								
								break;
							}
						}						
					}   						
				  }  
				  if (etLink.size() <= 3&&!ObjectUtil.isEmpty(etParcel.getText().toString().trim())
						  && !ObjectUtil.isEmpty(etShipment.getText().toString().trim())) {
					  loadView(etParcel.getText().toString().trim(),etShipment.getText().toString().trim());
					  Log.e("",etParcel.getText().toString().trim().length()+"" );
					  if (etLink.size()>=3) {
							etLink.get(2).requestFocus();
						  }
				  }
				
				break;						

			default:
				break;
			}
		}
	}
	
	public void loadView(String parcel,String shipment){
		
		Map<String,String> map = new HashMap<>();
		map.put("ishpmtNum", shipment);
		Map<String,String> mMap = RequestUtil.requByJson("reqJson", map);
		Log.e("shipment",mMap.get("reqJson"));
		TaskExecutor.Execute(new NetWorkPost(this,"wms/parcels/"+parcel+"/check-for-ishpmt-num", handler).setMapOfData(mMap,SharedUtil.getToken(this)));
	}
	
	private BroadcastReceiver _keyReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			
			  Log.d("012","action:"+intent.getAction());
				String key_action = intent.getStringExtra("action");
				String key_code = intent.getStringExtra("code");
			}
		
		};

	
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


	
	
}
