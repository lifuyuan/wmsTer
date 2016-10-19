package com.wmsterminal.activity.wms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.R.color;
import android.app.AlertDialog;
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
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wmsterminal.R;
import com.wmsterminal.activity.wms.QueryStokeActivity.MyAdapter;
import com.wmsterminal.base.BaseApplication;
import com.wmsterminal.base.activity.BaseActivity;
import com.wmsterminal.common.thread.NetWorkPost;
import com.wmsterminal.common.thread.TaskExecutor;
import com.wmsterminal.func.HadwareControll;
import com.wmsterminal.model.RequestPallet;
import com.wmsterminal.model.ReturnParcel;
import com.wmsterminal.model.StokeTakingResponse;
import com.wmsterminal.model.UpDataStokeRequest;
import com.wmsterminal.model.WmsInputResponse;
import com.wmsterminal.util.IntentUtil;
import com.wmsterminal.util.ObjectUtil;
import com.wmsterminal.util.RequestUtil;
import com.wmsterminal.util.SharedUtil;

public class UpDataStokeActivity extends BaseActivity implements View.OnClickListener{

	@ViewInject(R.id.tv_shelf_list)
	private TextView tvShelfNum;
	
	@ViewInject(R.id.tv_submitshelf_list)
	private TextView tvSubmit;
	
	@ViewInject(R.id.et_barcode_shelf)
	private EditText etBarcode;
	
	@ViewInject(R.id.et_quantity_shelf)
	private EditText etQuantity;
	
	public static final String KEY_RECEIVEER_ACTION = "com.idatachina.SCANKEYEVENT";

	public Handler mHandler = new MainHandler();
	//public ReadDoSomeThing readDoSomeThing;
	static UpDataStokeActivity ss;
	protected MediaPlayer mediaPlayer = null;
	HadwareControll controll = new HadwareControll(this);
	public boolean btn_scan =false;
	public  boolean et_scan = true;
	
	private int index = 1;
	private int etIndex = 0;
	    
    private int tvIDIndex = 1000;
    
	private  boolean skuno = false;
	private int number =0;
		
	private int mIndex;
	private int mMIndex;
	private String item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		controll.Open();
		controll.m_handler = mHandler;
	    IntentFilter m_keyfilter = new IntentFilter();
		m_keyfilter.addAction(KEY_RECEIVEER_ACTION);
		this.registerReceiver(_keyReceiver,m_keyfilter);
		showProgressDialog("loading...");
		//TaskExecutor.Execute(new NetWorkPost(this,"wms/assigned-operator/wms_hotmail_com/waves/", handler).setMapOfData(null, SharedUtil.getToken(this)).setHttpMethod(HttpMethod.GET));
		TaskExecutor.Execute(new NetWorkPost(this,"wms/assigned-operator/"+SharedUtil.getUser(this)+"/stoke-takings", handler).setMapOfData(null, SharedUtil.getToken(this)).setHttpMethod(HttpMethod.GET));
	}
	
	@Override
	protected void initSet() {
		// TODO Auto-generated method stub
		super.initSet();
		Intent intent  = getIntent();
    	mIndex = intent.getIntExtra("position", 0);
    	mMIndex = intent.getIntExtra("index",0);
    	etQuantity.setText(""+0);
	}
	
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		View view = getLayoutInflater().inflate(R.layout.item_updatashelf_addview, null);
		mLinearLayout.addView(view);
		ViewUtils.inject(this);
	}
	
		
	private Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			hideProgressDialog();
			StokeTakingResponse stokeResponse = null;
			if (msg.what == 0) {
				String json = msg.obj.toString();
				try {
					stokeResponse = JSON.parseObject(json, StokeTakingResponse.class);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (stokeResponse == null) {
		                Toast.makeText(UpDataStokeActivity.this, "abnormal server", Toast.LENGTH_SHORT).show();
		                return;
		            }	
				tvShelfNum.setText("ShelfNum :"+stokeResponse.getStockTakings().get(mIndex).getReferred_shelfs().get(mMIndex).getShelfNum());
				item = stokeResponse.getStockTakings().get(mIndex).getStockTakingNum();
			}else if(msg.what == 1){
				hideProgressDialog();
				String json = msg.obj.toString();
				WmsInputResponse wmsInputResponse = null;	
				try {
					wmsInputResponse = JSON.parseObject(json,WmsInputResponse.class);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

	            if (wmsInputResponse == null) {
	                Toast.makeText(UpDataStokeActivity.this, "abnormal server", Toast.LENGTH_SHORT).show();
	                return;
	            }
	            
	            if (wmsInputResponse.getStatus().equals("success")) {
	                Toast.makeText(UpDataStokeActivity.this, "submit success", Toast.LENGTH_SHORT).show();
	                IntentUtil.startActivity(UpDataStokeActivity.this, QueryStokeActivity.class);
				}else{
					showDialogHint(wmsInputResponse);
				}
				
			}
			
		};
	};
	
	@OnClick({R.id.tv_submitshelf_list})
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if (isFastDoubleClick()) {
            return;
            }   
		UpDataStokeRequest request = new UpDataStokeRequest();
		request.setBarcode(etBarcode.getText().toString().trim());
		request.setShelfNum(tvShelfNum.getText().toString().trim());
		request.setQuantity(Integer.parseInt(etQuantity.getText().toString()));
		if(checkData(request.getShelfNum(), request.getBarcode(),request.getQuantity())){
			Map<String,String> map = RequestUtil.requByJson("reqJson", request);
			Log.e("upstoke",map.get("reqJson"));
			showProgressDialog("loading....");
			TaskExecutor.Execute(new NetWorkPost(this,"wms/stoke-takings/"+item, handler,1).setMapOfData(map,SharedUtil.getToken(this)));
		}
	
	}
	private class MainHandler extends Handler{
		
		public void handleMessage(Message msg){
			
			switch (msg.what) {
			case HadwareControll.BARCODE_READ:				 
				  String result  = msg.obj+"";
				  if (etBarcode.isFocused() == true) {
					etBarcode.setText(result);
				}
				 break;					
			default:
				break;			
		}
		}
	}
	private boolean checkData(String shelt, String barcode,int i) {
        if (ObjectUtil.isEmpty(shelt)) {
        	Toast.makeText(this,"please input shelfnum", Toast.LENGTH_SHORT).show();		      
            return false;
        } else if (ObjectUtil.isEmpty(barcode)) {
        	Toast.makeText(this,"please input barcode", Toast.LENGTH_SHORT).show();		      
            return false;
        } else if (i == 0) {
        	Toast.makeText(this,"please input quantity", Toast.LENGTH_SHORT).show();		      
            return false; 
        } else {
            return true;
        }
    }
	public void showDialogHint(WmsInputResponse rp){
		final List<String> list = new ArrayList<>();
		for (int i = 0; i <rp.getReasons().size()  ; i++) {
				list.add("error"+i+":"+rp.getReasons().get(i).getReason());
			}
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  
        builder.setTitle("Error"); 
        int size = list.size();
        String items[] = list.toArray(new String[size]);
        builder.setItems(items,new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();    
            }  
        });  

        builder.setPositiveButton("Confirm",new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
            }  
        });  
        builder.create().show();  
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

	private BroadcastReceiver _keyReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			
			  Log.d("012","action:"+intent.getAction());
				String key_action = intent.getStringExtra("action");
				String key_code = intent.getStringExtra("code");
			}
		
		};
	
}
