package com.wmsterminal.base.fragment;

import com.wmsterminal.util.DateUtil;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author light
 * @File BaseFragment.java
 * @Package com.crowdfunding.base.fragment
 * @Time 20152015Äê9ÔÂ2ÈÕÏÂÎç3:08:38
 * @Description Fragment»ùÀà
 */
public abstract class BaseFragment extends BaseErrorFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = getActivity().getLayoutInflater().inflate(getViewLayout(), null);
		initView(view);
		initSet(view);
		return view;
	}

	/**
	 * @Title: initView
	 * @Description: ³õÊ¼»¯¿Ø¼þ
	 * @author: LiuSiQing
	 * @date: 2015-8-6 ÏÂÎç4:20:42
	 */
	protected void initView(View view) {
	}

	/**
	 * @Title: initSet
	 * @Description: ³õÊ¼»¯ÉèÖÃ
	 * @author: LiuSiQing
	 * @date: 2015-8-6 ÏÂÎç4:20:33
	 */
	protected void initSet(View view) {
	}

	/**
	 * ÊÇ·ñÊÇ¿ìËÙµã»÷
	 */
	protected boolean isFastDoubleClick() {
		return DateUtil.isFastDoubleClick();
	}

	protected abstract int getViewLayout();
}
