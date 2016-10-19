package com.wmsterminal.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wmsterminal.R;
import com.wmsterminal.base.activity.BaseActivity;

public class IndexContentActivity extends BaseActivity {

	@ViewInject(R.id.tv_index_content)
	private TextView tvContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected int getViewLayout() {
		// TODO Auto-generated method stub
		return R.layout.activity_index_content;
	}
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		super.initView();
		ViewUtils.inject(this);
	}
	@Override
	protected void initSet() {
		// TODO Auto-generated method stub
		super.initSet();
		//TextView tvContent = (TextView) findViewById(R.id.tv_index_content);
		String content = getIntent().getStringExtra("content");
		tvContent.setText(content);
	}	
	

}
