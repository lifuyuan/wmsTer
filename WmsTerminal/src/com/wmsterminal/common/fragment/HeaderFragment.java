package com.wmsterminal.common.fragment;

import android.annotation.SuppressLint;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.wmsterminal.R;
import com.wmsterminal.activity.LoginActivity;
import com.wmsterminal.activity.MainActivity;
import com.wmsterminal.base.fragment.BaseFragment;
import com.wmsterminal.common.listener.OnClickFinishListener;
import com.wmsterminal.util.IntentUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * @author light
 * @File HeaderFragment.java
 * @Package com.crowdfunding.common.fragment
 * @Time 2015Äê9ÔÂ6ÈÕÏÂÎç11:45:58
 * @Description TODO
 */
@SuppressLint("InflateParams")
public class HeaderFragment extends BaseFragment implements View.OnClickListener {

    /**
     * ±êÌâ
     */
    @ViewInject(R.id.tv_title_head)
    private TextView mTitleHeader;
    /**
     * ·µ»Ø
     */
    @ViewInject(R.id.iv_back_head)
    private View mViewBack;

    @ViewInject(R.id.tv_title_right)
    private TextView mViewRight;

    @ViewInject(R.id.img_title_right)
    private ImageView mImgRight;
    
    @ViewInject(R.id.iv_exit_head)
    private ImageView mImgExit;

    /**
     * Í·²¿Layout
     */
    private RelativeLayout mLayoutHeader;

    @Override
    protected int getViewLayout() {
        return R.layout.base_header;
    }

    /**
     * @Title: InitView
     * @Description: ³õÊ¼»¯¿Ø¼þ
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç9:33:32
     */
    protected void initView(View view) {
        super.initView(view);
        mLayoutHeader = (RelativeLayout) view;
        ViewUtils.inject(this, mLayoutHeader);
        if (null != mViewBack)
            //mViewBack.setOnClickListener(new OnClickFinishListener(getActivity()));
        	//mViewBack.setOnClickListener(IntentUtil.startActivity(getActivity(), MainActivity.class));
        	mViewBack.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					IntentUtil.startActivity(getActivity(), MainActivity.class);
				}
			});
    }

    @OnClick({R.id.iv_exit_head})
    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_exit_head:
				IntentUtil.startActivity(getActivity(), LoginActivity.class);
				getActivity().finish();
			break;

		default:
			break;
		}
	}
    /**
     * @Title: getHeaderView
     * @Description: »ñµÃÍ·²¿ÊÓÍ¼
     * @author: LiuSiQing
     * @date: 2015-6-30 ÏÂÎç4:24:49
     */
    public RelativeLayout getHeaderView() {
        return mLayoutHeader;
    }

    /**
     * @Title: getActionView
     * @Description: »ñµÃAction
     * @author: LiuSiQing
     * @date: 2015-6-30 ÏÂÎç4:23:29
     */
    public TextView getActionView() {
        return mViewRight;
    }

    /**
     * @Title: getActionView
     * @Description: »ñµÃAction
     * @author: LiuSiQing
     * @date: 2015-6-30 ÏÂÎç4:23:29
     */
    public ImageView getActionImgView() {
        return mImgRight;
    }

    /**
     * @Title: setTitle
     * @Description: ÉèÖÃ±êÌâ
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç9:27:21
     */
    public void setTitle(int resid) {
        if (null != mTitleHeader)
            mTitleHeader.setText(resid);
    }

    /**
     * @Title: setTitle
     * @Description: ÉèÖÃ±êÌâ
     * @author: LiuSiQing
     * @date: 2015-3-5 ÏÂÎç9:27:21
     */
    public void setTitle(String msg) {
        if (null != mTitleHeader)
            mTitleHeader.setText(msg);
    }

    /**
     * @Title: setBackOnClickListener
     * @Description: ÉèÖÃBackµÄ¼àÌý
     * @author: LiuSiQing
     * @date: 2015-7-6 ÏÂÎç5:42:43
     */
    public void setBackOnClickListener(OnClickListener l) {
        this.mViewBack.setOnClickListener(l);
    }

    /**
     * @Title: setTitleOnClickListener
     * @Description: ÉèÖÃTitleµÄ¼àÌý
     * @author: LiuSiQing
     * @date: 2015-7-6 ÏÂÎç5:42:43
     */
    public void setTitleOnClickListener(OnClickListener l) {
        this.mTitleHeader.setOnClickListener(l);
    }

    /**
     * @Title: setBackVisibility
     * @Description: ÉèÖÃBackÏÔÊ¾
     * @author: LiuSiQing
     * @date: 2015-7-6 ÉÏÎç11:38:18
     */
    public void setBackVisibility(int visibility) {
        mViewBack.setVisibility(visibility);
    }

    /**
     * »ñÈ¡ÓÒ±ßµÄ¿Ø¼þ²¢ÏÔÊ¾
     */
    public View getRightView() {
        mViewRight.setVisibility(View.VISIBLE);
        return mViewRight;
    }

	

}
