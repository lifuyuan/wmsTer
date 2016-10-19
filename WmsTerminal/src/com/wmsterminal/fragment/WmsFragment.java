package com.wmsterminal.fragment;


import java.util.ArrayList;


import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wmsterminal.R;
import com.wmsterminal.activity.IndexContentActivity;
import com.wmsterminal.activity.scan.CheckShipmentActivity;
import com.wmsterminal.activity.scan.PalletActivity;
import com.wmsterminal.activity.scan.ReleasePalletActivity;
import com.wmsterminal.activity.scan.ScanReturnParcelActivity;
import com.wmsterminal.activity.wms.IndexInputActivity;
import com.wmsterminal.activity.wms.QueryOutBoundActivity;
import com.wmsterminal.activity.wms.QueryStokeActivity;
import com.wmsterminal.activity.wms.QueryWavsActivity;
import com.wmsterminal.activity.wms.WmsInputActivity;
import com.wmsterminal.activity.wms.WmsInputFailureActivity;
import com.wmsterminal.activity.wms.WmsOutputActivity;
import com.wmsterminal.base.fragment.BaseFragment;
import com.wmsterminal.common.adapter.Adapter;
import com.wmsterminal.common.thread.NetWorkPost;
import com.wmsterminal.common.thread.TaskExecutor;
import com.wmsterminal.model.WmsIndex;
import com.wmsterminal.model.WmsIndex.ContentEntity;
import com.wmsterminal.util.IntentUtil;
import com.wmsterminal.util.SharedUtil;

public class WmsFragment extends BaseFragment implements AdapterView.OnItemClickListener,View.OnClickListener{

	@ViewInject(R.id.listview)
	private ListView lvScan;	
	
	//private WmsAdapter wmsAdapter;
	private WmsIndex wmsIndex;		
	
	private static final String arg[] = new String[]{
		"wms input","query out bound","query wavs",
		"query stoke"};
	private int []Imgs={R.drawable.ic_pue,R.drawable.ic_db_event,R.drawable.ic_db_people
			,R.drawable.ic_db_warm}; 

	
	@Override
	protected int getViewLayout() {
		// TODO Auto-generated method stub
		return R.layout.index_wms;
	}

	@Override
	protected void initView(View view) {
		// TODO Auto-generated method stub
		super.initView(view);
		ViewUtils.inject(this, view);
	}	
	@Override
	protected void initSet(View view) {
		// TODO Auto-generated method stub
		super.initSet(view);
		//listView.setAdapter(wmsAdapter = new WmsAdapter());
		//listView.setOnItemClickListener(this);
		lvScan.setAdapter(new MyAdapter(getActivity()));
		lvScan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:IntentUtil.startActivity(getActivity(),IndexInputActivity.class);
					break;
				case 1: IntentUtil.startActivity(getActivity(), QueryOutBoundActivity.class);
					break;
				case 2 : IntentUtil.startActivity(getActivity(), QueryWavsActivity.class);	
					break;
				case 3 : IntentUtil.startActivity(getActivity(), QueryStokeActivity.class);	
				default:
					break;
				}
			}
		});

		//wmsIndex = new WmsIndex(null);
		//wmsIndex.setContent_entity(content_entity);
		WmsIndex.ContentEntity wc = new WmsIndex.ContentEntity();
		WmsIndex.ContentEntity wc1 = new WmsIndex.ContentEntity();
		List<WmsIndex.ContentEntity> array = new ArrayList<WmsIndex.ContentEntity>();
		wc.setContent("inbound No.:xxxx 2016/6/15");
		wc1.setContent("outbound No.:xxxx 2016/6/17");
	    array.add(wc);	    array.add(wc);	    array.add(wc1);	    array.add(wc);	    array.add(wc1);	    array.add(wc);	    array.add(wc1);	    array.add(wc);	    array.add(wc1);
	    array.add(wc1);	    array.add(wc);	    array.add(wc);	    array.add(wc);	    array.add(wc);	    array.add(wc);
	    //array.add(wc); array.add(wc);	    array.add(wc);	   array.add(wc);
	    //array.add(wc);	    array.add(wc);	    array.add(wc);	    array.add(wc);	    array.add(wc);
		
		//wc.setContent("dd");
		//wmsIndex = new WmsIndex();
		//wmsIndex.setContent_entity(array);
		//wmsAdapter.refresh(wmsIndex.getContent_entity());
		//wmsAdapter.notifyDataSetChanged();
		
		//TaskExecutor.Execute(new NetWorkPost(getActivity(),"wms/inbound-batches", handler).setMapOfData(null,SharedUtil.getToken(getActivity())));
	}
	
	
	
	private Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
		};
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(),WmsInputActivity.class);
		intent.putExtra("batchNum", wmsIndex.getContent_entity().get(position).getContent());
		startActivity(intent);
	}
	
	/*class WmsAdapter extends Adapter<WmsIndex.ContentEntity> {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			if(null == convertView){
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_wms_list, parent, false);
				holder = new ViewHolder();
				holder.tvWmsContent  = (TextView) convertView.findViewById(R.id.tv_wms_list);
				convertView.setTag(holder);
				}else{
					holder = (ViewHolder) convertView.getTag();
				}
			WmsIndex.ContentEntity msIndex =getItem(position);
			holder.tvWmsContent.setText(msIndex.getContent());
			return convertView;
		}
		class ViewHolder{
			TextView tvWmsContent;
		}
	}*/
	
    private final class ViewHolder{
		

		private TextView tvScan;
		private ImageView imgScan;
 	}
	
	private class MyAdapter extends BaseAdapter{

		private LayoutInflater mInflater;
		
		public MyAdapter(Context ctx){
			this.mInflater=LayoutInflater.from(ctx);			
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder;
			if(convertView==null){
			
				convertView=mInflater.inflate(R.layout.item_scanprint_list, null);
				viewHolder=new ViewHolder();
				viewHolder.tvScan=(TextView) convertView.findViewById(R.id.tv_scan);
				viewHolder.imgScan=(ImageView) convertView.findViewById(R.id.img_scan);
				
				convertView.setTag(viewHolder);
			}else{
				viewHolder=(ViewHolder) convertView.getTag();
			}
			
			viewHolder.tvScan.setText(arg[position]);
			viewHolder.imgScan.setImageResource(Imgs[position]);
				
			return convertView;
		}
		
		
	}


	


}
