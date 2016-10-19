package com.wmsterminal.common.fragment;




import android.view.View;




import android.widget.CheckedTextView;
import android.widget.TextView;

import com.wmsterminal.R;
import com.wmsterminal.base.fragment.BaseFragment;
import com.wmsterminal.common.view.CheckableTextView;
import com.wmsterminal.common.view.CheckableTextView.OnCheckedChangeListener;

/**
 * 
 ****************************************
 * @package :com.crowdfunding.common.fragment
 * @author :light
 * @date :2015Äê10ÔÂ26ÈÕ ÉÏÎç10:18:06
 * @descripe :µ×²¿Ñ¡Ïî¿¨
 ****************************************
 */
public class FooterFragment extends BaseFragment implements OnCheckedChangeListener {

	/**
	 * wms
	 */
	private CheckableTextView mTabWms;
	/**
	 * scanprint
	 */
	private CheckableTextView mTabScanPrint;
	/**
	 * CheckableTextView Arrays
	 */
	private CheckableTextView[] mCheckableTextViews;

	@Override
	protected int getViewLayout() {
		return R.layout.fragment_footer;
	}

	/**
	 * ³õÊ¼»¯
	 */
	@Override
	protected void initView(View view) {
		super.initView(view);
		mTabWms = (CheckableTextView) view.findViewById(R.id.ctv_wms);
		mTabScanPrint =  (CheckableTextView) view.findViewById(R.id.ctv_scan);
		mTabScanPrint.setText("scan"+"&"+"print");
		mCheckableTextViews = new CheckableTextView[] {mTabWms,mTabScanPrint};
	
	}

	@Override
	protected void initSet(View view) {
		super.initSet(view);
		mTabWms.setOnCheckedChangeListener(this);
		mTabScanPrint.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CheckedTextView button, boolean isChecked) {
		if (isChecked) {
			int position = setChecked(button);
			if (null != mFooterInterface)
				mFooterInterface.onCheckedChanged(position, isChecked);
		}
	}

	/**
	 * 
	 *****************************************
	 * @package   :com.crowdfunding.common.fragment  				
	 * @class     :FooterFragmentNew.java     				
	 * @author    :light          				
	 * @date      :2015Äê10ÔÂ26ÈÕ ÉÏÎç10:20:57  				
	 * @descripe  :TODO          				
	 *****************************************
	 */
	public void setChecked(int position) {
		for (int i = 0; i < mCheckableTextViews.length; i++) {
			CheckedTextView view = mCheckableTextViews[i];
			if (i == position)
				view.setChecked(true);
			else
				view.setChecked(false);
		}
	}

	/**
	 * 
	 *****************************************
	 * @package   :com.crowdfunding.common.fragment  				
	 * @class     :FooterFragmentNew.java     				
	 * @author    :light          				
	 * @date      :2015Äê10ÔÂ26ÈÕ ÉÏÎç10:40:01  				
	 * @descripe  :ÉèÖÃÑ¡ÖÐ         				
	 *****************************************
	 */
	private int setChecked(CheckedTextView button) {
		int position = 0;
		for (int i = 0; i < mCheckableTextViews.length; i++) {
			CheckedTextView view = mCheckableTextViews[i];
			if (button.getId() == view.getId()) {
				position = i;
			} else {
				view.setChecked(false);
			}
		}
		return position;
	}

	/**
	 * 
	 *****************************************
	 * @package   :com.crowdfunding.common.fragment  				
	 * @class     :FooterFragmentNew.java     				
	 * @author    :light          				
	 * @date      :2015Äê10ÔÂ26ÈÕ ÉÏÎç10:41:44  				
	 * @descripe  :»ñÈ¡±»Ñ¡ÖÐµÄTextView          				
	 *****************************************
	 */
	public TextView getCheckedTextView() {
		for (int i = 0; i < mCheckableTextViews.length; i++) {
			CheckableTextView view = mCheckableTextViews[i];
			if (view.isChecked())
				return view;
		}
		return null;
	}

	/**
	 * ½Ó¿Ú
	 */
	private FooterInterface mFooterInterface;

	/**
	 * 
	 *****************************************
	 * @package   :com.crowdfunding.common.fragment  				
	 * @class     :FooterFragmentNew.java     				
	 * @author    :light          				
	 * @date      :2015Äê10ÔÂ26ÈÕ ÉÏÎç10:42:04  				
	 * @descripe  :»ñÈ¡µ×²¿ËéÆ¬µÄ½Ó¿Ú          				
	 *****************************************
	 */
	public FooterInterface getFooterInterface() {
		return mFooterInterface;
	}

	/**
	 * 
	 *****************************************
	 * @package   :com.crowdfunding.common.fragment  				
	 * @class     :FooterFragmentNew.java     				
	 * @author    :light          				
	 * @date      :2015Äê10ÔÂ26ÈÕ ÉÏÎç10:42:28  				
	 * @descripe  :ÉèÖÃµ×²¿ËéÆ¬½Ó¿Ú          				
	 *****************************************
	 */
	public void setFooterInterface(FooterInterface footerInterface) {
		mFooterInterface = footerInterface;
	}

	/**
	 * 
	 ****************************************
	 * @package  :com.crowdfunding.common.fragment
	 * @author   :light
	 * @date     :2015Äê10ÔÂ26ÈÕ ÉÏÎç10:41:17
	 * @descripe :µ×²¿ËéÆ¬½Ó¿Ú
	 ****************************************
	 */
	public interface FooterInterface {

		void onCheckedChanged(int position, boolean isChecked);
	}
}
