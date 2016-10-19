package com.wmsterminal.activity.wms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.R.integer;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.okhttp.OkHttpClient;
import com.wmsterminal.R;
import com.wmsterminal.activity.LoginActivity;
import com.wmsterminal.activity.MainActivity;
import com.wmsterminal.activity.scan.ScanReturnParcelActivity;
import com.wmsterminal.base.activity.BaseActivity;
import com.wmsterminal.common.adapter.Adapter;
import com.wmsterminal.common.thread.AnswerService;
import com.wmsterminal.common.thread.CustomJsonObjectRequest;
import com.wmsterminal.common.thread.HttpRequester;
import com.wmsterminal.common.thread.NetWorkPost;
import com.wmsterminal.common.thread.OkHttpStack;
import com.wmsterminal.common.thread.PatchTask;
import com.wmsterminal.common.thread.TaskExecutor;
import com.wmsterminal.model.OutBoundResponse;
import com.wmsterminal.model.ReturnParcel;
import com.wmsterminal.model.StokeTakingResponse;
import com.wmsterminal.model.OutBoundResponse.Outboundorders;
import com.wmsterminal.model.StokeTakingResponse.StockTakings;
import com.wmsterminal.model.WavsResponse;
import com.wmsterminal.model.WavsResponse.OutboundWaves;
import com.wmsterminal.util.IntentUtil;
import com.wmsterminal.util.ObjectUtil;
import com.wmsterminal.util.RequestUtil;
import com.wmsterminal.util.SharedUtil;


public class QueryStokeActivity extends BaseActivity  {

	@ViewInject(R.id.lv_scanprint)
	private ListView lvOutBound;
	
	List<String> listBoundNum = null;	
	
	MyAdapter adapter=null;
	
	
	@Override
	protected void initSet() {
		// TODO Auto-generated method stub
		super.initSet();
		showProgressDialog("loading...");
		//TaskExecutor.Execute(new NetWorkPost(this,"wms/assigned-operator/wms_hotmail_com/waves/", handler).setMapOfData(null, SharedUtil.getToken(this)).setHttpMethod(HttpMethod.GET));
		TaskExecutor.Execute(new NetWorkPost(this,"wms/assigned-operator/wms_hotmail_com/stoke-takings", handler).setMapOfData(null, SharedUtil.getToken(this)).setHttpMethod(HttpMethod.GET));
		
	}
	
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		View view = getLayoutInflater().inflate(R.layout.index_scanprint, null);
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
		                Toast.makeText(QueryStokeActivity.this, "abnormal server", Toast.LENGTH_SHORT).show();
		                return;
		            }		
				adapter = new MyAdapter(stokeResponse);
			    lvOutBound.setAdapter(adapter);
				adapter.refresh(stokeResponse.getStockTakings());
				adapter.notifyDataSetChanged();
			}else if(msg.what == 1){
				
			}
			
		};
	};
	
	class ViewHolder {
		public TextView tvTitle;
		public LinearLayout llBottom;
	}
	
	public class MyAdapter extends Adapter<StockTakings>{
		
		public ArrayList<Boolean> lDropDown ;	
		public ArrayList<Boolean> lClick ;
		public StokeTakingResponse mData;
		public LinkedList<View> mView;
		ViewHolder holder = null;
		
		public MyAdapter(StokeTakingResponse data){
			
			super();
			this.mData = data;
			lDropDown = new ArrayList<Boolean>();
			lClick = new ArrayList<Boolean>();
			mView = new LinkedList<>();
			for (int i = 0; i < mData.getStockTakings().size(); i++) {
				lDropDown.add(false);
				lClick.add(false);
			}
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = getLayoutInflater().inflate(R.layout.item_common_list, null);

				holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_common_list);
				holder.llBottom = (LinearLayout) convertView
						.findViewById(R.id.ll_addview);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			StockTakings stokeTaking = getItem(position);
			holder.tvTitle.setText("StockNum : "+stokeTaking.getStockTakingNum());
			
			//Log.e("", ""+ObjectUtil.getSize(mData.getOutboundorders().get(position).getReferredSkus()));
				if (lDropDown.get(position)) {
					holder.llBottom.removeAllViews();
					for (int i = 0; i < mData.getStockTakings().get(position).getReferred_shelfs().size(); i++) 
					{				
					TextView tvShelfNum = null;
					TextView tvStatusNum = null;
					RelativeLayout rlShelf = null;;
					View view =null;
						if (view ==null) {			
							view = getLayoutInflater().inflate(R.layout.item_shelfnum_addview,null);
							holder.llBottom.addView(view);
							tvShelfNum = (TextView) view.findViewById(R.id.tv_shelfnum_list);
							tvStatusNum = (TextView) view.findViewById(R.id.tv_status_list);
							rlShelf = (RelativeLayout) view.findViewById(R.id.rl_shelfnum);
							view.setTag(tvShelfNum);
							view.setTag(tvStatusNum);
							view.setTag(rlShelf);
						}else{
							tvShelfNum =  (TextView) view.getTag();
							tvStatusNum =  (TextView) view.getTag();
							rlShelf =   (RelativeLayout) view.getTag();
						}
					
						rlShelf.setBackgroundColor(Color.argb(255, 220, 220, 220));
						tvShelfNum.setText("ShelfNum : "+stokeTaking.getReferred_shelfs().get(position).getShelfNum());
						tvStatusNum.setText("Status : "+stokeTaking.getReferred_shelfs().get(position).getStatus());
					}
				holder.llBottom.setVisibility(View.VISIBLE);
				//lClick.add(position,true);

			} else {
				holder.llBottom.setVisibility(View.GONE);
			}
		
			
			final int index = position;
			
			holder.llBottom.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//showDialogHint(mData.getOutboundWaves().get(index));
					Intent intent = new Intent(QueryStokeActivity.this,ShelfNumListActivity.class);
					intent.putExtra("position", index);
					Log.e("idnex 1", ""+index);
					startActivity(intent);
				}
			});
			
			holder.tvTitle.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					boolean bFlagTemp = lDropDown.get(index);
					Log.e("bFlagTemp",bFlagTemp+"");
					for (int i = 0; i < getCount(); i++) {
						lDropDown.set(i, false);
					}
					lDropDown.set(index, !bFlagTemp);
					Log.e("view",""+!lDropDown.get(index));
					if (!lDropDown.get(index)) {
						//holder.llBottom.removeAllViews();
					}
					notifyDataSetChanged();
					//Log.e("2",lClick.get(index)+"");
				}
			});			
			return convertView;
		}
	}
	
	public void showDialogHint(OutboundWaves obr){
		final List<String> list = new ArrayList<>();
		for (int i = 0; i <obr.getReferred_skus().size()  ; i++) {
				list.add("Goods :"+obr.getReferred_skus().get(i).getGoodsName()+
						" Quantity : "+obr.getReferred_skus().get(i).getQuantity());
			}
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Setting");
        int size = list.size();
        String items[] = list.toArray(new String[size]);
        builder.setItems(items,new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();    
            }  
        });  
        builder.setNegativeButton("Submit", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				loadView();
            	dialog.dismiss();  
			}
		});
        
        builder.setPositiveButton("StockOut",new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
				//loadView();
            	//String url="http://106.185.35.33:9001/wms/assigned-operator/wms_hotmail_com/outbound-orders/";
        		//String result = new HttpRequester().requestByGet(url);
        		//System.out.println("AskService result="+result);
            	//getAnswerList("");
				loadView();
            	Map<String,String> map = new HashMap<>();
            	map.put("", "");
            	Map<String,String> mMap = RequestUtil.requByJson("reqJson", map);
            	dialog.dismiss();  
            }  
        });  
        builder.create().show();  
	}
	
	public void loadView(){
		
		Map<String, String> map = new HashMap<>();
		map.put("status", "picked");
		Map<String,String> mMap = RequestUtil.requByJson("reqJson", map);
		Log.e("status", mMap.get("reqJson"));
		showProgressDialog("loading....");
		TaskExecutor.Execute(new NetWorkPost(this, 
				"wms/assigned-operator/wms_hotmail_com/outbound-orders/ORDER_TEST_001",handler,1).
				setMapOfData(mMap,SharedUtil.getToken(this)));
			}
	
	
	
	public void setOutBoundValues(){
		
		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(QueryOutBoundActivity.this,
				R.layout.item_wms_list,R.id.tv_wms_list,date);
		lvOutBound.setAdapter(adapter);
		lvOutBound.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				popupWindow.showAsDropDown(view);
			}
		});	
		//View view = getLayoutInflater().inflate(R.layout.index_scanprint, null);
		ViewLeft view1 = new ViewLeft(this);

		popupWindow= new PopupWindow(view1,ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT,true);//
		popupWindow.setTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setOutsideTouchable(true);*/
	}
	
	

	public void loadView2(){
		String httpurl = "http://106.185.35.33:9001/wms/assigned-operator/wms_hotmail_com/outbound-orders/ORDER_TEST_001/";
		//String httpurl = "http://106.185.35.33:9001/wms/assigned-operator/wms_hotmail_com/outbound-orders/";
		  
		JsonObjectRequest request = new JsonObjectRequest(Method.PATCH, httpurl,
				 new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject arg0) {
						// TODO Auto-generated method stub
						//showProgressDialog("aaaa");
						Message msg=Message.obtain();
						msg.obj=arg0;//
						mJsonHandler.sendMessage(msg);//
						System.out.println(arg0.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						System.out.println(arg0.toString());
					}
				}){
			
				@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						// TODO Auto-generated method stub
					Map<String, String> params = new HashMap<String, String>();
					params.put("status", "picked"); 	
					return params;
					}
				@Override
				public Map<String, String> getHeaders()
						throws AuthFailureError {
					// TODO Auto-generated method stub
					HashMap<String, String> headers = new HashMap<String, String>();  
	       	        headers.put("authorization", SharedUtil.getToken(QueryStokeActivity.this)); 		             
	       	        return headers;				
	       	        }
			};
		Volley.newRequestQueue(this,new OkHttpStack(new OkHttpClient())).add(request);//将request添加到队列中
	
	}
	
	private Handler mJsonHandler = new Handler() {
		public void handleMessage(Message msg) {
			hideProgressDialog();
		}
	};	
}
