package com.wmsterminal.activity.wms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;



import java.sql.DatabaseMetaData;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
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
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

import com.alibaba.fastjson.JSON;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wmsterminal.R;
import com.wmsterminal.activity.LoginActivity;
import com.wmsterminal.activity.scan.ScanReturnParcelActivity;
import com.wmsterminal.base.activity.BaseActivity;
import com.wmsterminal.common.thread.NetWorkPost;
import com.wmsterminal.common.thread.TaskExecutor;
import com.wmsterminal.func.HadwareControll;
import com.wmsterminal.func.db.DatabaseHelper;
import com.wmsterminal.model.InputRequest;
import com.wmsterminal.model.ReturnParcel;
import com.wmsterminal.model.SqliteTest;
import com.wmsterminal.model.WmsInputResponse;
import com.wmsterminal.util.IntentUtil;
import com.wmsterminal.util.ObjectUtil;
import com.wmsterminal.util.RequestUtil;
import com.wmsterminal.util.SharedUtil;

public class WmsInputActivity extends BaseActivity implements View.OnClickListener {

	public static final String KEY_RECEIVEER_ACTION = "com.idatachina.SCANKEYEVENT";
	
	@ViewInject(R.id.et_shelfnum_input)
	private EditText etShelfNum;
	
	@ViewInject(R.id.et_barcode_input)
	private EditText etBarCode;
	
	@ViewInject(R.id.et_quantity_input)
	private EditText etQuantity;
	
	@ViewInject(R.id.tv_date_input)
	private TextView tvDate;
	
	@ViewInject(R.id.rb_group_input)
	private RadioGroup rbGroup;
	
	@ViewInject(R.id.rb_broken_input)
	private RadioButton rbBroken;
	
	@ViewInject(R.id.rb_shelf_input)
	private RadioButton rbShelf;
	
	@ViewInject(R.id.rb_nomatch_input)
	private RadioButton rbNoMatch;
	
	@ViewInject(R.id.rb_notfound_input)
	private RadioButton rbNoFound;
	
	@ViewInject(R.id.rb_others_input)
	private RadioButton rbOthers;
	
	@ViewInject(R.id.et_other_input)
	private EditText etOthers;
	
	@ViewInject(R.id.rb_groupOperation_input)
	private RadioGroup rbOperationGroup;
	
	@ViewInject(R.id.rb_mount_input)
	private RadioButton rbMount;
	
	@ViewInject(R.id.rb_register_input)
	private RadioButton rbRegister;
	
	@ViewInject(R.id.rb_unmount_input)
	private RadioButton rbUnMount;
	
	@ViewInject(R.id.rb_release_input)
	private RadioButton rbRelease;
	
	@ViewInject(R.id.tv_input_next)
	private TextView tvNext;
	
	@ViewInject(R.id.tv_input_finish)
	private TextView tvFinish;
	
	@ViewInject(R.id.tv_operation_input)
	private TextView tvOperation;
	
	@ViewInject(R.id.tv_failure_input)
	private TextView tvfailure;
	
	@ViewInject(R.id.rl_failure_input)
	private RelativeLayout rlFailure;
	
	@ViewInject(R.id.rl_operation_input)
	private RelativeLayout rlOperation;
	
    private Handler mHandler = new MainHandler();
    static WmsInputActivity wi;
    private MediaPlayer mediaPlayer = null;
    private HadwareControll controll = new HadwareControll(this);
    private boolean btn_scan = false;
    private boolean et_scan = true;
	private  boolean skuno = false;
	
	private String mProblem ="";
	private String mOperation = null;
	private String date;
	private String mBatchNum;
	private Boolean mFailure = false;
	private Boolean mOperat = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	controll.Open();
    	controll.m_handler = mHandler;
    	IntentFilter m_keyfilter = new IntentFilter();
    	m_keyfilter.addAction(KEY_RECEIVEER_ACTION);
    	this.registerReceiver(_keyReceiver,m_keyfilter);
    	rbGroup.setOnCheckedChangeListener(listener);
    	rbOperationGroup.setOnCheckedChangeListener(mListener);
    	Intent intent  = getIntent();
    	mBatchNum = intent.getStringExtra("batchNum");
    }
	
	protected void initSet() {
		// TODO Auto-generated method stub
		super.initSet();
		setTitleMessage("inbound");
		etQuantity.setText("1");
		rbGroup.setVisibility(View.GONE);
		rbOperationGroup.setVisibility(View.GONE);
	}
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		View view =getLayoutInflater().inflate(R.layout.wms_input, null);
		mLinearLayout.addView(view);
		ViewUtils.inject(this);

	}
	
	private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.rb_broken_input:				
				mProblem = "broken";
				break;			
			case R.id.rb_shelf_input:				
				mProblem = "over_shelf_life";
				break;				
			case R.id.rb_nomatch_input:				
				mProblem = "quantity_not_match";
				break;				
			case R.id.rb_notfound_input:				
				mProblem = "";
				break;				
			case R.id.rb_others_input:				
				mProblem = etOthers.getText().toString().trim();
				break;
			default:
				break;			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener mListener = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			
			switch (checkedId) {
			case R.id.rb_mount_input:
				mOperation = "register-and-mount";
				break;
			case R.id.rb_register_input:
				mOperation = "register";
				break;
			case R.id.rb_unmount_input:
				mOperation = "unmount-and-release";
				break;
			case R.id.rb_release_input:
				mOperation = "release";
				break;	
			default:
				break;
			}
		}
	};
	
	
	
	@OnClick({R.id.tv_date_input,R.id.tv_input_next,R.id.tv_input_finish,
		R.id.rl_failure_input,R.id.rl_operation_input})
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.rl_failure_input:
			
			if(!mFailure)
			{rbGroup.setVisibility(View.VISIBLE);
				mFailure = !mFailure;
			}else{
				rbGroup.setVisibility(View.GONE);
				mFailure = !mFailure;
			}	
			
			break;
			
		case R.id.rl_operation_input:
			
			if(!mOperat)
			{rbOperationGroup.setVisibility(View.VISIBLE);
				mOperat = !mOperat;
			}else{
				rbOperationGroup.setVisibility(View.GONE);
				mOperat = !mOperat;
			}	
			
			break;		
					
		case R.id.tv_input_next:
			
			IntentUtil.startActivity(this, WmsInputActivity.class);
	        break;

		case R.id.tv_input_finish:
			if (isFastDoubleClick()) {
                return;
                }          
			if(checkData(etShelfNum.getText().toString(), etBarCode.getText().toString(), date,mOperation)){
			showProgressDialog("loading...");	
			InputRequest inputRequest = new InputRequest();
			inputRequest.setShelfNum(etShelfNum.getText().toString().trim());
			inputRequest.setBarcode(etBarCode.getText().toString().trim());
			inputRequest.setQuantity(Integer.parseInt(etQuantity.getText().toString()));
			inputRequest.setDeadlineOfShelfLife(date);
			inputRequest.setProblem(mProblem);
			inputRequest.setOperation(mOperation);
			Map<String,String> mMap = RequestUtil.requByJson("reqJson", inputRequest);
			Log.e("input",mMap.get("reqJson"));
			TaskExecutor.Execute(new NetWorkPost(this, "wms/assigned-operator/"+SharedUtil.getUser(this)+"/inbound-batches/"+mBatchNum+"/shelf-operations", handler).setMapOfData(mMap,SharedUtil.getToken(this)));
			}	
			break;
			
			case R.id.tv_date_input:
				 Calendar c = Calendar.getInstance();
				 int myear = c.get(Calendar.YEAR);
				 int month = c.get(Calendar.MONTH);
				 int day = c.get(Calendar.DAY_OF_MONTH);
				 DatePickerDialog datePicker=new DatePickerDialog(WmsInputActivity.this, new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						//Toast.makeText(WmsInputActivity.this, year+"year "+(monthOfYear+1)+"month "+dayOfMonth+"day", Toast.LENGTH_SHORT).show();
						//date = new java.sql.Date(year, monthOfYear, dayOfMonth);
						//Date date1 = new Date();
						//date = new Date(year-(monthOfYear+1)-dayOfMonth);
						tvDate.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);										
						tvDate.setTextColor(Color.argb(255, 0, 0,0));
						tvDate.setTextSize(12);
						date = tvDate.getText().toString();

									Log.e("", date.toString());

					}
				}, myear, month,day);
				datePicker.show();
				break;
		default:
			break;
		}
	}	
	 private boolean checkData(String shelt, String barcode,String date,String opert) {
	        if (ObjectUtil.isEmpty(shelt)) {
	        	Toast.makeText(this,"please input shelfnum", Toast.LENGTH_SHORT).show();		      
	            return false;
	        } else if (ObjectUtil.isEmpty(barcode)) {
	        	Toast.makeText(this,"please input barcode", Toast.LENGTH_SHORT).show();		      
	            return false;
	        } else if (ObjectUtil.isEmpty(date)) {
	        	Toast.makeText(this,"please input date", Toast.LENGTH_SHORT).show();		      
	            return false;   
	        } else if (ObjectUtil.isEmpty(opert)) {
	        	Toast.makeText(this,"please choose opoeration", Toast.LENGTH_SHORT).show();		      
	            return false;   
	        } else {
	            return true;
	        }
	    }
	
	
	private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			
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
                Toast.makeText(WmsInputActivity.this, "abnormal server", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (wmsInputResponse.getStatus().equals("success")) {
                Toast.makeText(WmsInputActivity.this, "submit success", Toast.LENGTH_SHORT).show();
			}else{
				showDialogHint(wmsInputResponse);
			}
		}
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
		private class MainHandler extends Handler{
			
			public void handleMessage(Message msg){
				
				switch (msg.what) {
				case HadwareControll.BARCODE_READ:
					 
					  String result  = msg.obj+"";
					  Loge("", result);
					  if (etShelfNum.isFocused() == true) {
						  etShelfNum.setText(result);
						  etBarCode.requestFocus();
					  }
					  if(etBarCode.isFocused() == true){
						  etBarCode.setText(result);
						  etQuantity.requestFocus();
					  }
					  				
				    break;					
				default:
					break;
				}
			}

			private EditText findViewById(int etInputSku) {
				// TODO Auto-generated method stub
				return null;
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
