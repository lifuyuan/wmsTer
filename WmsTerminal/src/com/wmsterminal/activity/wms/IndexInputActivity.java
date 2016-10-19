package com.wmsterminal.activity.wms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wmsterminal.R;
import com.wmsterminal.activity.LoginActivity;
import com.wmsterminal.base.activity.BaseActivity;
import com.wmsterminal.common.thread.NetWorkPost;
import com.wmsterminal.common.thread.TaskExecutor;
import com.wmsterminal.model.IndexInputReponse;
import com.wmsterminal.util.IntentUtil;
import com.wmsterminal.util.SharedUtil;

public class IndexInputActivity extends BaseActivity implements  View.OnClickListener {
	
	@ViewInject(R.id.lv_scanprint)
	private ListView mListView;
	
	private Object tv_wms_list;
	
	private List<String> listBatchNum = null;
	
	IndexInputReponse inputReponse = null;

	@Override
	protected void initSet() {
		// TODO Auto-generated method stub
		super.initSet();
		showProgressDialog("load...");
		TaskExecutor.Execute(new NetWorkPost(this,"wms/assigned-operator/wms_hotmail_com/inbound-batches", handler).setMapOfData(null, SharedUtil.getToken(this)).setHttpMethod(HttpMethod.GET));
		//TaskExecutor.Execute(new NetWorkPost(this,"wms/assigned-operator/xx/outbound-orders/", handler).setMapOfData(null, SharedUtil.getToken(this)));
	
	}
	
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		View view = getLayoutInflater().inflate(R.layout.index_scanprint, null);
		mLinearLayout.addView(view);
		ViewUtils.inject(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	private Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			hideProgressDialog();
			String json = msg.obj.toString();
			try {
				inputReponse = JSON.parseObject(json, IndexInputReponse.class);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			if (inputReponse == null) {
				  Toast.makeText(IndexInputActivity.this, "abnormal server", Toast.LENGTH_SHORT).show();
	                return;
			}			
			setValue(inputReponse);
			
		};
		
		public void setValue(final IndexInputReponse value){
			
			listBatchNum = new ArrayList<>();
			for (int i = 0; i < value.getInboundbatchnums().size(); i++) {
				listBatchNum.add("BanchNum :"+value.getInboundbatchnums().get(i).getInboundbatchnum());
			}
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(IndexInputActivity.this, 
					 R.layout.item_wms_list, R.id.tv_wms_list, listBatchNum);
			mListView.setAdapter(arrayAdapter);
			mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				 //IntentUtil.startActivity(IndexInputActivity.this,
				//WmsInputActivity.class);
				Map<String,String> map = new HashMap<>();
				map.put("batchNum", value.getInboundbatchnums().get(position).getInboundbatchnum());
				IntentUtil.startActivity(IndexInputActivity.this,
						WmsInputActivity.class, map);				
			}
		});
		}
	};


	

}
