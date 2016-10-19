package com.wmsterminal.activity.wms;

import java.util.LinkedList;

import android.R.color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wmsterminal.R;
import com.wmsterminal.base.activity.BaseActivity;

public class WmsOutputActivity extends BaseActivity implements View.OnClickListener {

	@ViewInject(R.id.et_output_skuno)
	private EditText etSkuNo;
	
	@ViewInject(R.id.tv_output_next)
	private TextView tvNext;
	
	@ViewInject(R.id.tv_output_finish)
	private TextView tvFinish;
	
	@ViewInject(R.id.ll_output)
	private LinearLayout llOutPut;	
	
	private LinkedList<EditText> etLink;
	
	int i = 0;

	@Override
	protected void initSet() {
		// TODO Auto-generated method stub
		super.initSet();
		setTitleMessage("outbound");
		etLink = new LinkedList<EditText>();
	}
	
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		View view =getLayoutInflater().inflate(R.layout.wms_output, null);
		mLinearLayout.addView(view);
		ViewUtils.inject(this);
	}
	
	@OnClick({R.id.tv_output_next,R.id.tv_output_finish})
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.tv_output_next:
			EditText et = new EditText(WmsOutputActivity.this);
			et.setTextColor(getResources().getColor(color.black));
			et.setTextSize(12);
			et.setBackground(null);
			et.setHint("²âÊÔ");
			
			llOutPut.addView(et);
			etLink.add(i, et);
			i++;
			String name = etLink.get(i-1).getText().toString().trim();
			Toast.makeText(this,name, Toast.LENGTH_SHORT).show();
			break;
			 
		case R.id.tv_output_finish:
			break;
		default:
			break;
		}
	}

}
