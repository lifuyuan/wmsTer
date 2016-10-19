package com.wmsterminal.activity.wms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wmsterminal.R;
import com.wmsterminal.activity.scan.PalletActivity;
import com.wmsterminal.activity.scan.ScanReturnParcelActivity;
import com.wmsterminal.activity.wms.QueryOutBoundActivity.MyAdapter;
import com.wmsterminal.activity.wms.QueryOutBoundActivity.ViewHolder;
import com.wmsterminal.base.activity.BaseActivity;
import com.wmsterminal.common.adapter.Adapter;
import com.wmsterminal.common.thread.NetWorkPost;
import com.wmsterminal.common.thread.TaskExecutor;
import com.wmsterminal.func.HadwareControll;
import com.wmsterminal.model.OutBoundResponse;
import com.wmsterminal.model.WmsInputResponse;
import com.wmsterminal.model.OutBoundResponse.Outboundorders;
import com.wmsterminal.model.OutBoundResponse.Outboundorders.ReferredSkus;
import com.wmsterminal.model.StokeTakingResponse.StockTakings.Referred_shelfs;
import com.wmsterminal.util.IntentUtil;
import com.wmsterminal.util.RequestUtil;
import com.wmsterminal.util.SharedUtil;

import android.R.array;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UpDateOutBoundActivity extends BaseActivity {
	
	@ViewInject(R.id.tv_update_outbound)
	private TextView tvOutBoundNum;
	
	@ViewInject(R.id.lv_upout)
	private ListView lvUpDate;
	
	private int mIndex;
	private int num = 0;
	public static final String KEY_RECEIVEER_ACTION = "com.idatachina.SCANKEYEVENT";

	private LinkedList<TextView> oosQuantityLink = new LinkedList<>();
	
	public ArrayList<Boolean> lDropDown ;	

	
	public Handler mHandler = new MainHandler();
	//public ReadDoSomeThing readDoSomeThing;
	static ScanReturnParcelActivity ss;
	protected MediaPlayer mediaPlayer = null;
	HadwareControll controll = new HadwareControll(this);
	ArrayList<Integer> arrayList = new ArrayList<>();
	MyAdapter adapter;
	OutBoundResponse outBoundResponse = null;
	
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
		Intent intent  = getIntent();
    	mIndex = intent.getIntExtra("position", 0);
		Log.e("index", ""+mIndex);
		showProgressDialog("loading...");
		TaskExecutor.Execute(new NetWorkPost(this,"wms/assigned-operator/"+SharedUtil.getUser(this)+"/outbound-orders/", handler).setMapOfData(null, SharedUtil.getToken(this)).setHttpMethod(HttpMethod.GET));
			
	}
		
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		View view = getLayoutInflater().inflate(R.layout.wms_upout, null);
		mLinearLayout.addView(view);
		ViewUtils.inject(this);
	}
	
	
		private class MainHandler extends Handler{
			
			public void handleMessage(Message msg){
				
				switch (msg.what) {
				case HadwareControll.BARCODE_READ:				 
					  String result  = msg.obj+"";				 
					  for (int i = 0; i < outBoundResponse.getOutboundorders().get(mIndex).getReferred_skus().size(); i++) {
						if (lDropDown.get(i) == true) {							
							int num = arrayList.get(i);
							if (num >0) {
								arrayList.set(i,--num);
								adapter.notifyDataSetChanged();
							}
							
						}
					  }
					  												  	
					 break;	
					 
				default:
					break;
			}
			}
			}
		
		class ViewHolder {
			public TextView tvShelfNum;
			public TextView tvQuantity;
			public LinearLayout llBottom;
			public RelativeLayout rlupout;
			public LinearLayout llbtn;
			public TextView btnPicked;
			public TextView btnOos;
		}

		public class MyAdapter extends Adapter<ReferredSkus>{
			
			public OutBoundResponse mData;
			ViewHolder holder = null;
			
			public MyAdapter(OutBoundResponse data){
				
				super();
				this.mData = data;
				lDropDown = new ArrayList<Boolean>();
				for (int i = 0; i < mData.getOutboundorders().get(mIndex).getReferred_skus().size(); i++) {
					lDropDown.add(false);
				}
				
			}
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if (convertView == null) {
					holder = new ViewHolder();
					convertView = getLayoutInflater().inflate(R.layout.item_updataoutbound_list, null);
					holder.tvShelfNum = (TextView) convertView.findViewById(R.id.tv_shelf_upout);
					holder.tvQuantity = (TextView) convertView.findViewById(R.id.tv_quantity_upout);
					holder.llBottom = (LinearLayout) convertView.findViewById(R.id.ll_upout);
					holder.rlupout =  (RelativeLayout) convertView.findViewById(R.id.rl_upout);
					holder.llbtn = (LinearLayout) convertView.findViewById(R.id.ll_btn_upout);
					holder.btnPicked = (TextView) convertView.findViewById(R.id.tv_btnpicked_wavs);
					holder.btnOos = (TextView) convertView.findViewById(R.id.tv_btnoos_wavs);

					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}
				
				ReferredSkus reShelfs = getItem(position);
				holder.tvShelfNum.setText("GoodName : "+reShelfs.getGoodsName());
				holder.tvQuantity.setText("Quantity : "+reShelfs.getQuantity());
				
				//Log.e("", ""+ObjectUtil.getSize(mData.getOutboundorders().get(position).getReferredSkus()));
				holder.llBottom.removeAllViews();							
				TextView tvOrderNum = null;
				View view =null;
					if (view ==null) {			
						view = getLayoutInflater().inflate(R.layout.item_ordernum_addview,null);
						holder.llBottom.addView(view);
						tvOrderNum = (TextView) view.findViewById(R.id.tv_order_list);
						view.setTag(tvOrderNum);
					}else{
						tvOrderNum = (EditText) view.getTag();
					}
		
					tvOrderNum.setText("OosNum : "+arrayList.get(position));
					oosQuantityLink.add(tvOrderNum);
				if (lDropDown.get(position)) {
												
					holder.llBottom.setVisibility(View.VISIBLE);
				} else {
					holder.llBottom.setVisibility(View.GONE);		// 
				}
				
				if (position == getCount()-1 ) {					
					holder.llbtn.setVisibility(View.VISIBLE);
				} else {
					holder.llbtn.setVisibility(View.GONE);		// 
				}			
								
				final int index = position;
				
				holder.btnPicked.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int j = 0;
						for (int i = 0; i < arrayList.size(); i++) {
							if(arrayList.get(i)==0){
								++j;
							}else{
								Toast.makeText(UpDateOutBoundActivity.this, "please verify the barcode", Toast.LENGTH_SHORT).show();
							}
							if (j == arrayList.size()) {
								loadView("picked");				

							}
							
						}
						}
				});
				
				holder.btnOos.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						loadView("oos");							}
				});			
							
				// 
				holder.rlupout.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						boolean bFlagTemp = lDropDown.get(index);
						Log.e("bFlagTemp",bFlagTemp+"");
						for (int i = 0; i < getCount(); i++) {
							lDropDown.set(i, false);
						}
						lDropDown.set(index, !bFlagTemp);
						Log.e("view",""+!lDropDown.get(index));						
						notifyDataSetChanged();
					}
				});			
				return convertView;
			}
		}
		
		private void loadView(String meth){
			
			showProgressDialog("loading...");
			Map<String,String> map = new HashMap<>();
			map.put("outboundNum", outBoundResponse.getOutboundorders().get(mIndex).getOutboundnum());
			map.put("status", meth);
			Map<String,String> mMap = RequestUtil.requByJson("reqJson", map);
			Log.e("upoutpick", mMap.get("reqJson"));
			TaskExecutor.Execute(new NetWorkPost(this,"wms/assigned-operator/"+SharedUtil.getUser(this)+"/outbound-orders/"+outBoundResponse.getOutboundorders().get(mIndex).getOrdernum(), handler,1).setMapOfData(mMap, SharedUtil.getToken(this)));
			
		}
		
		private Handler handler = new Handler(){
			
			public void handleMessage(android.os.Message msg) {
				super.handleMessage(msg);
				hideProgressDialog();
				if (msg.what == 0) {
					String json = msg.obj.toString();
					try {
						outBoundResponse = JSON.parseObject(json, OutBoundResponse.class);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					if (outBoundResponse == null) {
			                Toast.makeText(UpDateOutBoundActivity.this, "abnormal server", Toast.LENGTH_SHORT).show();
			                return;
			            }
					
					for (int i = 0; i < outBoundResponse.getOutboundorders().get(mIndex).getReferred_skus().size(); i++) {
						arrayList.add(outBoundResponse.getOutboundorders().get(mIndex).getReferred_skus().get(i).getQuantity());
					}
					adapter = new MyAdapter(outBoundResponse);
				    lvUpDate.setAdapter(adapter);
					adapter.refresh(outBoundResponse.getOutboundorders().get(mIndex).getReferred_skus());
					adapter.notifyDataSetChanged();
					tvOutBoundNum.setText("OutBound: "+outBoundResponse.getOutboundorders().get(mIndex).getOutboundnum());
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
		                Toast.makeText(UpDateOutBoundActivity.this, "abnormal server", Toast.LENGTH_SHORT).show();
		                return;
		            }
		            
		            if (wmsInputResponse.getStatus().equals("success")) {
		                Toast.makeText(UpDateOutBoundActivity.this, "submit success", Toast.LENGTH_SHORT).show();
		                IntentUtil.startActivity(UpDateOutBoundActivity.this, QueryOutBoundActivity.class);
					}else{
						showDialogHint(wmsInputResponse);
					}
				}
				
			};
		};
		
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
	
		

	
	
	/*public void addContent1(int i){
		
		View view = null;
		TextView tvGoods;
		TextView tvQuantity;
		TextView tvOosQuantity;
		
		if(view == null){
			
			view = this.getLayoutInflater().inflate(R.layout.item_wavs_list, null); 
			llWavs.addView(view);
			tvGoods = (TextView) findViewById(R.id.tv_goods_wavs);
			tvQuantity = (TextView) findViewById(R.id.tv_quantity_wavs);
			tvOosQuantity = (TextView) findViewById(R.id.tv_oos_wavs);
			view.setTag(tvGoods);
			view.setTag(tvQuantity);
			view.setTag(tvOosQuantity);
		}else{
			tvGoods = (TextView) view.getTag();
			tvQuantity = (TextView) view.getTag();
			tvOosQuantity = (TextView) view.getTag();
		}
		Log.e("goods", ""+3);
		tvGoods.setText("good"+i);
		tvQuantity.setText("quantity"+4);
		tvOosQuantity.setText("oos"+4);
		mGoodLink.add(num,tvGoods);
		mQuantityLink.add(num,tvQuantity);
		mOosLink.add(num,tvQuantity);
		++num;
	}**/
	
	
		

}