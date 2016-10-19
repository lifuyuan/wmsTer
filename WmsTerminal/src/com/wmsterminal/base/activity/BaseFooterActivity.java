package com.wmsterminal.base.activity;



import com.wmsterminal.R;

import com.wmsterminal.common.fragment.FooterFragment;
import com.wmsterminal.common.fragment.FooterFragment.FooterInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Window;
import android.widget.LinearLayout;

public abstract class BaseFooterActivity extends BaseResourcesActivity implements FooterInterface{
	
	/**
	 * Ö÷ÊÓÍ¼
	 */
	protected LinearLayout mLinearLayout;
	
	/**
	 * µ×²¿ÊÓÍ¼
	 */
	protected FooterFragment mFooterFragment;
	/**
	 * Í·²¿ÊÓÍ¼
	 */
	protected LinearLayout mHeaderLinearLayout;
	/**
	 * ËéÆ¬¹ÜÀíÀà
	 */
	protected FragmentManager fm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (null != savedInstanceState) {
			onSaveInstanceState(savedInstanceState);
		}
        requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.base_activity_footer);
		fm = getSupportFragmentManager();
		mFooterFragment = (FooterFragment) fm.findFragmentById(R.id.base_footer);
		mLinearLayout = (LinearLayout) findViewById(R.id.layout_of_view);
		//mHeaderLinearLayout = (LinearLayout) findViewById(R.id.base_header);
		initView();
		initSet();
	}

	/**
	 * package: com.crowdfunding.base.activity
	 * class: BaseFooterActivity.java
	 * author: light
	 * date: 2015Äê9ÔÂ15ÈÕ ÏÂÎç2:25:39
	 * descripe:³õÊ¼»¯¿Ø¼þ 
	 */
	protected void initView() {
	}
	
	/**
	 * package: com.crowdfunding.base.activity
	 * class: BaseFooterActivity.java
	 * author: light
	 * date: 2015Äê9ÔÂ15ÈÕ ÏÂÎç2:25:59
	 * descripe: ³õÊ¼»¯Êý¾Ý
	 */
	protected void initSet() {
	}
	
	/**
	 * package: com.crowdfunding.base.activity
	 * class: BaseActivity.java
	 * author: light
	 * date: 2015Äê9ÔÂ14ÈÕ ÏÂÎç3:27:58
	 * descripe: ÉèÖÃÖ÷Ìâ
	 */
	protected void setBackGroundTheme(int styleId){
		mLinearLayout.setBackgroundResource(styleId);
	}

}
