package com.wmsterminal.common.view;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class ClearableEditTextTouch implements OnTouchListener{

	ClearableEditText mClearableEditText;
	public ClearableEditTextTouch(ClearableEditText clearableEditText){
		mClearableEditText=clearableEditText;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(mClearableEditText.getCompoundDrawables()[2] == null){
			return false;
		}
		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
			if (event.getX() > mClearableEditText.getWidth()
					- mClearableEditText.getPaddingRight()
					- mClearableEditText.mDrawable.getIntrinsicWidth()) {
				mClearableEditText.setText("");
				mClearableEditText.setClearButtonVisible(false);
				if (mClearableEditText.mOnTextClearedListener != null) {
					mClearableEditText.mOnTextClearedListener.clearComplete();
				}
			}
			return true;
		default:
			break;
		}
		
		return false;
	}

}
