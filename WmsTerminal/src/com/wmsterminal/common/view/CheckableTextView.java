package com.wmsterminal.common.view;



import com.wmsterminal.R;
import com.wmsterminal.util.DebugUtil;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

public class CheckableTextView extends CheckedTextView {

	private static final String TAG = CheckableTextView.class.getSimpleName();
	public static final int PERSONALITY_RADIO_BUTTON = 0;
	public static final int PERSONALITY_CHECK_BUTTON = 1;
	private int personality;
	private OnCheckedChangeListener mOnCheckedChangeListener;

	public CheckableTextView(Context context) {
		super(context);
	}

	public CheckableTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public CheckableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		// »ñÈ¡×Ô¶¨ÒåÊôÐÔµÄÖµ
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CheckableTextView);
		personality = a.getInt(R.styleable.CheckableTextView_personality, PERSONALITY_RADIO_BUTTON);
		setChecked(isChecked());
		// Give back a previously retrieved StyledAttributes, for later re-use.
		a.recycle();
	}

	/**
	 * @Title: setPersonality
	 * @Description: ÉèÖÃÊôÐÔ
	 * @author: LiuSiQing
	 * @date: 2015-9-1 ÏÂÎç2:24:46
	 */
	public void setPersonality(int personality) {
		if( personality <= 1 && personality >= 0 )
			this.personality = personality;
	}

	@Override
	public boolean performClick() {
		// À¹½Øµã»÷ÊÂ¼þ´¦Àícheck
		if( personality == PERSONALITY_CHECK_BUTTON ) {
			toggle();
		} else if( personality == PERSONALITY_RADIO_BUTTON ) {
			setChecked(true);
		}
		return super.performClick();
	}

	@Override
	public void setChecked(boolean checked) {
		DebugUtil.e(TAG, "setChecked:" + checked);
		super.setChecked(checked);
		if( null != mOnCheckedChangeListener ) {
			mOnCheckedChangeListener.onCheckedChanged(this, checked);
		}
	}

	public interface OnCheckedChangeListener {

		/**
		 * interface definition for a callback to be invoked when the checked
		 * image button changed
		 * 
		 * @param button
		 * @param isChecked
		 * */
		public void onCheckedChanged(CheckedTextView button, boolean isChecked);
	}

	public OnCheckedChangeListener getOnCheckedChangeListener() {
		return mOnCheckedChangeListener;
	}

	public void setOnCheckedChangeListener(OnCheckedChangeListener l) {
		if( !isClickable() ) {
			setClickable(true);
		}
		this.mOnCheckedChangeListener = l;
	}
}
