package com.wmsterminal.common.view;

import java.lang.reflect.Field;
import com.wmsterminal.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class ClearableEditText extends EditText {

	float mDensityScale;
	int mWidth = -1;
	int mHeight = -1;
	public Drawable mDrawable;
	OnTextClearedListener mOnTextClearedListener;

	public ClearableEditText(Context context) {
		super(context);
	}

	public ClearableEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		initClearableEditText(context, attrs);
	}

	public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initClearableEditText(context, attrs);
	}
	@SuppressWarnings("ResourceType")
	public void initClearableEditText(Context context, AttributeSet attrs) {
		mDensityScale = context.getResources().getDisplayMetrics().density;
		//TypedArray typedArray = context.obtainStyledAttributes(attrs,
				//R.styleable.clearableEditText);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, 
				R.styleable.clearableEditText);
		mDrawable = typedArray.getDrawable(0);
		mWidth = typedArray.getDimensionPixelSize(1, -1);
		mHeight = typedArray.getDimensionPixelSize(2, -1);
		if (mDrawable != null) {
			if (mWidth == -1 || mHeight == -1) {
				mWidth = (int) (19.0F * mDensityScale);
				mHeight = (int) (19.0F * mDensityScale);
			}
			mDrawable.setBounds(0, 0, mWidth, mHeight);
			setClearButtonVisible(false);
		}
		setOnTouchListener(new ClearableEditTextTouch(this));
		addTextChangedListener(new ClearableEditTextWatcher(this));
		typedArray.recycle();
		String str = getText().toString();
		if ((str == null) || (str.length() == 0)) {
			setClearButtonVisible(false);

		} else {
			setClearButtonVisible(true);

		}
	}
	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
		if (!focused) {
			setCursorVisible(false);
		} else {
			setCursorVisible(true);
		}
	}
	public void setClearButtonVisible(boolean visible) {
		if (visible) {
			setCompoundDrawables(getCompoundDrawables()[0],
					getCompoundDrawables()[1], mDrawable,
					getCompoundDrawables()[3]);
		} else {
			setCompoundDrawables(getCompoundDrawables()[0],
					getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
		}
	}

	public abstract interface OnTextClearedListener {
		public abstract void clearComplete();
	}

	
}
