package com.wmsterminal.fragment;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import com.wmsterminal.R;
import com.wmsterminal.activity.LoginActivity;
import com.wmsterminal.activity.ScanSeparateActivity;
import com.wmsterminal.activity.scan.CheckShipmentActivity;
import com.wmsterminal.activity.scan.PalletActivity;
import com.wmsterminal.activity.scan.ReleasePalletActivity;
import com.wmsterminal.activity.scan.ScanReturnParcelActivity;
import com.wmsterminal.base.fragment.BaseFragment;
import com.wmsterminal.util.IntentUtil;

public class ScanPrintFragment extends BaseFragment  {

	
	@ViewInject(R.id.lv_scanprint)
	private ListView lvScan;
	
	
	
	private static final String arg[] = new String[]{
		"returning-parcels","check-for-ishpmt-num","pallets",
		"release-pallet"};
	private int []Imgs={R.drawable.ic_pue,R.drawable.ic_db_event,R.drawable.ic_db_people
			,R.drawable.ic_db_warm}; 

	protected void initSet(View view) {
		
		lvScan.setAdapter(new MyAdapter(getActivity()));
		lvScan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:IntentUtil.startActivity(getActivity(),ScanReturnParcelActivity.class);
					break;
				case 1: IntentUtil.startActivity(getActivity(), CheckShipmentActivity.class);
					break;
				case 2 : IntentUtil.startActivity(getActivity(), PalletActivity.class);	
					break;
				case 3 : IntentUtil.startActivity(getActivity(), ReleasePalletActivity.class);	
				default:
					break;
				}
			}
		});
 	}
 	 /* private ListAdapter getAdapter(){
 	    	
 	    	List<HashMap<String,Object>> data=new ArrayList<HashMap<String,Object>>();
 	    	for (int i = 0; i < Imgs.length; i++) {
 				HashMap<String,Object> item=new HashMap<String, Object>();
 				item.put("names", arg[i]);
 				item.put("images",Imgs[i]);
 				data.add(item);
 			}
 	    	SimpleAdapter simple=new SimpleAdapter(this, data, R.layout.item_scanprint_list,
 	    			new String []{"names","images"}, new int[]{R.id.tv_left_item
 	    			,R.id.iv_left_item});
 	    	SimpleAdapter simple = new SimpleAdapter(this, data, R.layout.item_scanprint_list, 
 	    			new String []{"names","images"}, new int[]{R.id.tv_scan,R.id.img_scan});
 	    	
 	    	return simple;
 	    }*/
 	    
	@Override
	protected void initView(View view) {
		// TODO Auto-generated method stub
		super.initView(view);
		ViewUtils.inject(this, view);
	}

	@Override
	protected int getViewLayout() {
		// TODO Auto-generated method stub
		return R.layout.index_scanprint;
	}
	
	
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
