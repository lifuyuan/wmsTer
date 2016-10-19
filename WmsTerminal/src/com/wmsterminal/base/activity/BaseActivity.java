package com.wmsterminal.base.activity;



import com.wmsterminal.R;
import com.wmsterminal.common.fragment.HeaderFragment;

import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * @Package: com.mngwyhouhzmb.base
 * @ClassName: BaseActivity
 * @Description: -����Activity
 * @author: LiuSiQing
 * @date: 2015-3-5 ����9:48:51
 */
public abstract class BaseActivity extends BaseResourcesActivity {

	/**
	 * ȫ����ͼ������ͼ
	 */
	protected LinearLayout mLinearLayout;
	/**
	 * ������Ƭ
	 */
	protected HeaderFragment mHeaderFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getViewLayout());
		mLinearLayout = (LinearLayout)findViewById(R.id.layout_of_view);
		mHeaderFragment = (HeaderFragment)getSupportFragmentManager().findFragmentById(R.id.base_header);
		initView();
		initSet();

	}

	/**
	 * @Title: initView
	 * @Description: ��ʼ���ؼ�
	 * @author: LiuSiQing
	 * @date: 2015-6-26 ����11:39:27
	 */
	protected void initView() {
	}

	/**
	 * @Title: initSet
	 * @Description: ��ʼ������
	 * @author: LiuSiQing
	 * @date: 2015-8-6 ����3:54:57
	 */
	protected void initSet() {
	}

	/**
	 * @Title: getViewLayout
	 * @Description: �����ͼ
	 * @author: LiuSiQing
	 * @date: 2015-3-31 ����10:58:24
	 */
	protected int getViewLayout() {
		return R.layout.base_activity;
	}
	
	/**
	 * package: com.crowdfunding.base.activity
	 * class: BaseActivity.java
	 * author: light
	 * date: 2015��9��14�� ����3:27:58
	 * descripe: ��������
	 */
	protected void setBackGroundTheme(int styleId){
		mLinearLayout.setBackgroundResource(styleId);
	}

	/**
	 * ���ñ���
	 */
	protected void setTitleMessage(int resid) {
		mHeaderFragment.setTitle(resid);
	}

	/**
	 * ���ñ���
	 */
	protected void setTitleMessage(String msg) {
		mHeaderFragment.setTitle(msg);
	}
}
