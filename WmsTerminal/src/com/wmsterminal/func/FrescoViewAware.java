package com.wmsterminal.func;

import android.graphics.Bitmap;


import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

public class FrescoViewAware extends ImageViewAware {

	public FrescoViewAware(SimpleDraweeView imageView) {
		super(imageView);
	}

	public FrescoViewAware(SimpleDraweeView imageView, boolean checkActualViewSize) {
		super(imageView, checkActualViewSize);
	}

	@Override
	protected void setImageDrawableInto(Drawable drawable, View view) {
		((SimpleDraweeView)view).getHierarchy().setPlaceholderImage(drawable);
		if( drawable instanceof AnimationDrawable ) {
			((AnimationDrawable)drawable).start();
		}
	}

	@Override
	protected void setImageBitmapInto(Bitmap bitmap, View view) {
		setImageDrawableInto(new BitmapDrawable(view.getContext().getResources(), bitmap), view);
	}
}
