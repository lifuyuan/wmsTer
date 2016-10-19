package com.wmsterminal.common.dialog;

import java.util.HashMap;
import java.util.Map;
import com.wmsterminal.R;
import com.wmsterminal.activity.LoginActivity;
import com.wmsterminal.base.Config;
import com.wmsterminal.common.listener.OnClickFinishListener;
import com.wmsterminal.common.listener.OnClickIntentListener;
import com.wmsterminal.util.CloseUtil;
import com.wmsterminal.util.DisplayUtil;
import com.wmsterminal.util.ObjectUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @Package: com.mngwyhouhzmb.view.dialog
 * @ClassName: CustomDialog
 * @Description: ×Ô¶¨Òådialog-·ÂIOS
 * @author: LiuSiQing
 * @date: 2015-6-30 ÏÂÎç1:39:57
 */
public class CustomDialog extends Dialog {

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	public CustomDialog(Context context) {
		super(context);
	}

	/**
	 * @Title: showBuilder
	 * @Description: ÏÔÊ¾ÌáÊ¾¿ò
	 * @author: LiuSiQing
	 * @param Context
	 *            context ÉÏÏÂÎÄ
	 * @param boolean
	 *            flag ÊÇ·ñ¿Éµã»÷ÏûÊ§£¬Ä¬ÈÏÎªtrue
	 * @param String
	 *            title ÌáÊ¾
	 * @param String
	 *            message ÄÚÈÝ
	 * @param int
	 *            messageGravity ÄÚÈÝµÄÎ»ÖÃ
	 * @param String
	 *            positiveButtonText È·¶¨°´Å¥
	 * @param DialogInterface
	 *            .OnClickListener positiveButtonListener È·¶¨°´Å¥¼àÌý
	 * @param String
	 *            neutralButtonText ÖÐ¼ä°´Å¥
	 * @param DialogInterface
	 *            .OnClickListener neutralButtonListener ÖÐ¼ä°´Å¥¼àÌý
	 * @param String
	 *            negativeButtonText È¡Ïû°´Å¥
	 * @param DialogInterface
	 *            .OnClickListener negativeButtonListener È¡Ïû°´Å¥¼àÌý
	 * @date: 2015-5-4 ÏÂÎç5:45:34
	 */
	public static Dialog showBuilder(Context context, boolean flag, String title, String message, int messageGravity,
			String positiveButtonText, DialogInterface.OnClickListener positiveButtonListener, String neutralButtonText,
			DialogInterface.OnClickListener neutralButtonListener, String negativeButtonText,
			DialogInterface.OnClickListener negativeButtonListener) {
		if (null == context)
			return null;
		Builder builder = new Builder(context);
		builder.setCancelable(flag).setTitle(title).setMessage(message).setMessageGravity(messageGravity);
		builder.setPositiveButton(positiveButtonText, positiveButtonListener); // È·¶¨°´Å¥
		builder.setNeutralButton(neutralButtonText, neutralButtonListener); // ÖÐ¼ä°´Å¥
		builder.setNegativeButton(negativeButtonText, negativeButtonListener); // È¡Ïû°´Å¥
		Dialog dialog = builder.create();
		dialog.show();
		return dialog;
	}

	/**
	 * @Title: showBuilder
	 * @Description: ÏÔÊ¾ÌáÊ¾¿ò
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç5:50:28
	 */
	public static Dialog showBuilder(Context context, boolean flag, String title, String message, int messageGravity,
			String positiveButtonText, DialogInterface.OnClickListener positiveButtonListener,
			String negativeButtonText, DialogInterface.OnClickListener negativeButtonListener) {
		return showBuilder(context, flag, title, message, messageGravity, positiveButtonText, positiveButtonListener,
				null, null, negativeButtonText, negativeButtonListener);
	}

	/** TODO Cancelable true */
	/**
	 * @Title: showBuilderTwo
	 * @Description: ÏÔÊ¾Á½¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:01:33
	 */
	public static Dialog showBuilderTwo(Context context, String title, String message, String positiveButtonText,
			DialogInterface.OnClickListener positiveButtonListener) {
		return showBuilder(context, true, title, message, Gravity.CENTER, positiveButtonText, positiveButtonListener,
				context.getString(R.string.cancel), null);
	}

	/**
	 * @Title: showBuilderTwo
	 * @Description: ÏÔÊ¾Á½¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:01:33
	 */
	public static Dialog showBuilderTwo(Context context, String title, String message,
			DialogInterface.OnClickListener positiveButtonListener) {
		return showBuilderTwo(context, title, message, context.getString(R.string.confirm), positiveButtonListener);
	}

	/**
	 * @Title: showBuilderTwo
	 * @Description: ÏÔÊ¾Á½¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:01:33
	 */
	public static Dialog showBuilderTwo(Context context, String message,
			DialogInterface.OnClickListener positiveButtonListener) {
		return showBuilderTwo(context, context.getString(R.string.hint), message, positiveButtonListener);
	}

	/**
	 * @Title: showBuilderOne
	 * @Description: ÏÔÊ¾Ò»¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:01:45
	 */
	public static Dialog showBuilderOne(Context context, String title, String message, String positiveButtonText,
			DialogInterface.OnClickListener positiveButtonListener) {
		return showBuilder(context, true, title, message, Gravity.CENTER, positiveButtonText, positiveButtonListener,
				null, null);
	}

	/**
	 * @Title: showBuilderOne
	 * @Description: ÏÔÊ¾Ò»¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:02:41
	 */
	public static Dialog showBuilderOne(Context context, String title, String message) {
		return showBuilderOne(context, title, message, context.getString(R.string.confirm), null);
	}

	/**
	 * @Title: showBuilderOne
	 * @Description: ÏÔÊ¾Ò»¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:02:56
	 */
	public static Dialog showBuilderOne(Context context, String message) {
		return showBuilderOne(context, context.getString(R.string.hint), message);
	}

	/**
	 * @Title: showBuilderOne
	 * @Description: ÏÔÊ¾Ò»¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:03:07
	 */
	public static Dialog showBuilderOne(Context context, int message) {
		return showBuilderOne(context, context.getString(R.string.hint), context.getString(message));
	}

	/** TODO Cancelable false */
	/**
	 * @Title: showBuilderCancelable
	 * @Description: ÏÔÊ¾Á½¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:03:19
	 */
	public static Dialog showBuilderCancelable(Context context, String title, String message, String positiveButtonText,
			DialogInterface.OnClickListener positiveButtonListener, String negativeButtonText,
			DialogInterface.OnClickListener negativeButtonListener) {
		return showBuilder(context, false, title, message, Gravity.CENTER, positiveButtonText, positiveButtonListener,
				negativeButtonText, negativeButtonListener);
	}

	/**
	 * @Title: showBuilderCancelable
	 * @Description: ÏÔÊ¾Á½¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç5:52:38
	 */
	public static Dialog showBuilderCancelable(Context context, String title, String message, String positiveButtonText,
			DialogInterface.OnClickListener positiveButtonListener) {
		return showBuilderCancelable(context, title, message, positiveButtonText, positiveButtonListener,
				context.getString(R.string.cancel), null);
	}

	/**
	 * @Title: showBuilderCancelableOne
	 * @Description: ÏÔÊ¾Ò»¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:03:40
	 */
	public static Dialog showBuilderCancelableOne(Context context, String title, String message,
			String positiveButtonText, DialogInterface.OnClickListener positiveButtonListener) {
		return showBuilderCancelable(context, title, message, positiveButtonText, positiveButtonListener, null, null);
	}

	/**
	 * @Title: showBuilderCancelableOne
	 * @Description: ÏÔÊ¾Ò»¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:03:47
	 */
	public static Dialog showBuilderCancelableOne(Context context, String title, String message) {
		return showBuilderCancelableOne(context, title, message, context.getString(R.string.confirm), null);
	}

	/**
	 * @Title: showBuilderCancelableOne
	 * @Description: ÏÔÊ¾Ò»¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:03:47
	 */
	public static Dialog showBuilderCancelableOne(Context context, String message,
			DialogInterface.OnClickListener positiveButtonListener) {
		return showBuilderCancelableOne(context, context.getString(R.string.hint), message,
				context.getString(R.string.confirm), positiveButtonListener);
	}

	/**
	 * @Title: showBuilderCancelableOneFinish
	 * @Description: ÏÔÊ¾Ò»¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:03:47
	 */
	public static Dialog showBuilderCancelableOneFinish(Context context, String msg) {
		return showBuilderCancelableOne(context, msg, new OnClickFinishListener((Activity) context));
	}

	/**
	 * @Title: showBuilderCancelableOneFinishResult
	 * @Description: ÏÔÊ¾Ò»¸ö°´Å¥µÄµ¯´°
	 * @author: LiuSiQing
	 * @date: 2015-5-4 ÏÂÎç6:03:47
	 */
//	public static Dialog showBuilderCancelableOneFinishResult(Context context, String msg) {
//		return showBuilderCancelableOne(context, msg, new OnClickFinishResult(context));
//	}
//
	/** TODO ÌØÊâ */
	/**
	 * @Title showBuilderLogin
	 * @Description µÇÂ¼À¹½Ø
	 * @param context
	 * @return
	 */
	public static Dialog showBuilderLogin(Context context,String flag,String proid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == flag) {
			map.put("flag", Config.DEFAULT);
		}else {
			map.put("flag", flag);
			if (null != proid) {
				map.put(Config.PRO_ID, proid);
			}
		}
		DialogInterface.OnClickListener onclick = new OnClickIntentListener((Activity) context, LoginActivity.class,
				map);
		return showBuilderTwo(context, "ÌáÊ¾", "ÇëµÇÂ¼/×¢²áºó²Ù×÷£¡", "È·¶¨", onclick);
	}

	/**
	 * @Title showBuilderInfo
	 * @Description ÏÔÊ¾ÏêÇé
	 * @param context
	 * @param view
	 * @return
	 */
	public static Dialog showBuilderInfo(Context context, View view) {
		if (null == view)
			return null;
		String text = ((TextView) view).getText().toString();
		if (ObjectUtil.isEmpty(text))
			return null;
		CustomDialog.Builder builder = new CustomDialog.Builder(context);
		builder.setMessage(text);
		builder.setPositiveButton(R.string.confirm, null);
		Dialog dialog = builder.create();
		CloseUtil.show(dialog);
		return dialog;
	}

	/**
	 * Helper class for creating a custom dialog
	 */
	public static class Builder {

		private Context context;
		private String title;
		private String message;
		private String positiveButtonText, neutralButtonText, negativeButtonText;
		private int titleGravity = Gravity.CENTER;
		private int messageGravity = Gravity.CENTER;
		private View contentView;
		private DialogInterface.OnClickListener positiveButtonListener, neutralButtonListener, negativeButtonListener;
		private int layout = R.layout.custom_dialog;
		private int style = R.style.Dialog;
		private boolean flag = true;

		public Builder(Context context) {
			this.context = context;
		}

		/**
		 * Set the Dialog message from String
		 * 
		 * @param title
		 * @return
		 */
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * @Title: setMessageGravity
		 * @Description: Set the Dialog message's gravity
		 * @author: LiuSiQing
		 * @date: 2015-5-4 ÏÂÎç5:35:26
		 */
		public Builder setMessageGravity(int gravity) {
			this.messageGravity = gravity;
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 * @Title: setTitleGravity
		 * @Description: Set the Dialog title's gravity
		 * @author: LiuSiQing
		 * @date: 2015-5-4 ÏÂÎç5:35:26
		 */
		public Builder setTitleGravity(int gravity) {
			this.titleGravity = gravity;
			return this;
		}

		/**
		 * Set a custom content view for the Dialog. If a message is set, the
		 * contentView is not added to the Dialog...
		 * 
		 * @param v
		 * @return
		 */
		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * Set the neutral button resource and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNeutralButton(int neutralButtonText, DialogInterface.OnClickListener listener) {
			this.neutralButtonText = (String) context.getText(neutralButtonText);
			this.neutralButtonListener = listener;
			return this;
		}

		/**
		 * Set the neutral button text and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNeutralButton(String neutralButtonText, DialogInterface.OnClickListener listener) {
			this.neutralButtonText = neutralButtonText;
			this.neutralButtonListener = listener;
			return this;
		}

		/**
		 * Set the positive button resource and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = (String) context.getText(positiveButtonText);
			this.positiveButtonListener = listener;
			return this;
		}

		/**
		 * Set the positive button text and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonListener = listener;
			return this;
		}

		/**
		 * Set the negative button resource and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener) {
			this.negativeButtonText = (String) context.getText(negativeButtonText);
			this.negativeButtonListener = listener;
			return this;
		}

		/**
		 * Set the negative button text and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonListener = listener;
			return this;
		}

		/**
		 * @Title setStyle
		 * @Description ÉèÖÃÑùÊ½
		 * @param style
		 * @return
		 */
		public Builder setStyle(int style) {
			this.style = style;
			return this;
		}

		/**
		 * @Title setLayout
		 * @Description ÉèÖÃÊÓÍ¼
		 * @param layout
		 * @return
		 */
		public Builder setLayout(int layout) {
			this.layout = layout;
			return this;
		}

		public Builder setCancelable(boolean flag) {
			this.flag = flag;
			return this;
		}

		/**
		 * Create the custom dialog
		 */
		public CustomDialog create() {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final CustomDialog dialog = new CustomDialog(context, style);
			View view = inflater.inflate(layout, null);
			dialog.addContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
			if (null != layout) {
				int width = DisplayUtil.getWidthPercent(2d / 3);
				FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, LayoutParams.WRAP_CONTENT);
				params.gravity = Gravity.CENTER;
				layout.setLayoutParams(params);
			}
			// set the dialog title
			TextView tv_title = (TextView) view.findViewById(R.id.title);
			if (null != tv_title) {
				tv_title.setGravity(titleGravity);
				tv_title.getPaint().setFakeBoldText(true);
				tv_title.setText(title);
			}
			Button neutralButton = (Button) view.findViewById(R.id.neutralButton);
			// set the neutral button
			if (null != neutralButton) {
				if (null != negativeButtonText && null != neutralButtonText && null != positiveButtonText) {
					neutralButton.setText(neutralButtonText);
					if (neutralButtonListener != null) {
						neutralButton.setOnClickListener(new View.OnClickListener() {

							public void onClick(View v) {
								neutralButtonListener.onClick(dialog, DialogInterface.BUTTON_NEUTRAL);
							}
						});
					} else {
						neutralButton.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
					}
				} else {
					// if no confirm button or cancle button or neutral just set
					// the visibility to GONE
					view.findViewById(R.id.neutralButton).setVisibility(View.GONE);
					view.findViewById(R.id.single_line).setVisibility(View.GONE);
				}
			}
			Button negativeButton = (Button) view.findViewById(R.id.negativeButton);
			if (null != negativeButton) {
				if (negativeButtonText != null) { // set the cancel button
					negativeButton.setText(negativeButtonText);
					if (negativeButtonListener != null) {
						negativeButton.setOnClickListener(new View.OnClickListener() {

							public void onClick(View v) {
								negativeButtonListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
							}
						});
					} else
						negativeButton.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
				} else { // if no confirm button just set the visibility to GONE
					view.findViewById(R.id.negativeButton).setVisibility(View.GONE);
					view.findViewById(R.id.second_line).setVisibility(View.GONE);
					view.findViewById(R.id.positiveButton).setBackgroundResource(R.drawable.dialog_single_btn);
				}
			}
			Button positiveButton = (Button) view.findViewById(R.id.positiveButton);
			if (null != positiveButton) {
				if (positiveButtonText != null) { // set the confirm button
					positiveButton.setText(positiveButtonText);
					if (positiveButtonListener != null) {
						positiveButton.setOnClickListener(new View.OnClickListener() {

							public void onClick(View v) {
								positiveButtonListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
							}
						});
					} else
						positiveButton.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
				} else { // if no confirm button just set the visibility to GONE
					view.findViewById(R.id.positiveButton).setVisibility(View.GONE);
					view.findViewById(R.id.second_line).setVisibility(View.GONE);
					view.findViewById(R.id.negativeButton).setBackgroundResource(R.drawable.dialog_single_btn);
				}
			}
			TextView textMessage = (TextView) view.findViewById(R.id.message);
			if (null != textMessage) {
				if (message != null) { // set the content message
					textMessage.setGravity(messageGravity);
					textMessage.setText(message);
				} else
					textMessage.setVisibility(View.GONE);
			}
			LinearLayout content = (LinearLayout) view.findViewById(R.id.content);
			if (null != content && null != contentView) {
				// if no message set add the contentView to the dialog body
				content.removeAllViews();
				content.addView(contentView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			}
			// dialog.setContentView(view);
			dialog.setCancelable(flag);
			return dialog;
		}
	}
}
