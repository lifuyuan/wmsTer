package com.wmsterminal.activity.wms;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wmsterminal.R;
import com.wmsterminal.base.activity.BaseActivity;

public class WmsInputFailureActivity extends BaseActivity implements View.OnClickListener
   ,OnCheckedChangeListener{

	@ViewInject(R.id.et_ifailure_no)
	private EditText etNo;
	
	@ViewInject(R.id.et_ifailure_others)
	private EditText etOthers;
	
	@ViewInject(R.id.cb_ifailure_broken)
	private CheckBox cbBroken;
	
	@ViewInject(R.id.cb_ifailure_overshelflife)
	private CheckBox cbOverShelf;
	
	@ViewInject(R.id.cb_ifailure_quantitynotmatch)
	private CheckBox cbQuantity;
	
	@ViewInject(R.id.cb_ifailure_notfound)
	private CheckBox cbNotfound;
	
	@ViewInject(R.id.cb_ifailure_others)
	private CheckBox cbOthers;
	
	@ViewInject(R.id.tv_ifailure_next)
	private TextView tvNext;
	
	@ViewInject(R.id.tv_ifailure_finish)
	private TextView tvFinish;
	@Override
	protected void initSet() {
		// TODO Auto-generated method stub
		super.initSet();
		setTitleMessage("inbound failure report");
		cbBroken.setOnCheckedChangeListener(this);
		cbOverShelf.setOnCheckedChangeListener(this);
		cbNotfound.setOnCheckedChangeListener(this);
		cbQuantity.setOnCheckedChangeListener(this);
		cbOthers.setOnCheckedChangeListener(this);
	}
	
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		View view = getLayoutInflater().inflate(R.layout.wms_input_failure, null);
		mLinearLayout.addView(view);
		ViewUtils.inject(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
		switch (buttonView.getId()) {
		case R.id.cb_ifailure_broken:
			if(isChecked){
				Toast.makeText(this, "broken", Toast.LENGTH_SHORT).show(); 
			}
			break;
		case R.id.cb_ifailure_overshelflife:
			if(isChecked){
				Toast.makeText(this, "overshelf", Toast.LENGTH_SHORT).show(); 
			}
			break;	
		case R.id.cb_ifailure_quantitynotmatch:
			if(isChecked){
				Toast.makeText(this, "quanity", Toast.LENGTH_SHORT).show(); 
			}
			break;	
		case R.id.cb_ifailure_notfound:
			if(isChecked){
				Toast.makeText(this, "notfound", Toast.LENGTH_SHORT).show(); 
			}
			break;
		case R.id.cb_ifailure_others:
			if(isChecked){
				Toast.makeText(this, "others", Toast.LENGTH_SHORT).show(); 
			}
			break;		

		default:
			break;
		}
	}

}
