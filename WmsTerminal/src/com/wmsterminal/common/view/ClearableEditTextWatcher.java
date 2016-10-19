package com.wmsterminal.common.view;

import android.text.Editable;
import android.text.TextWatcher;

public class ClearableEditTextWatcher implements TextWatcher {

	ClearableEditText mClearableEditText;

	public ClearableEditTextWatcher(ClearableEditText clearableEditText) {
		mClearableEditText = clearableEditText;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String text = mClearableEditText.getText().toString();
		if ((text == null) || (text.length() == 0)) {
			mClearableEditText.setClearButtonVisible(false);
		} else {
			mClearableEditText.setClearButtonVisible(true);
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

}
