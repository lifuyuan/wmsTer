package com.wmsterminal.func;

import java.util.Stack;
import android.app.Activity;

/**
 * @ ActivityÕ»¹ÜÀí¹¤¾ß¡£Í¨¹ýÕ»ÊµÏÖ
 * @author RA
 * 
 */
public class StackManager {
	/**
	 * Stack ÖÐ¶ÔÓ¦µÄActivityÁÐ±í  £¨Ò²¿ÉÒÔÐ´×ö Stack<Activity>£©
	 */
	private static Stack mActivityStack;
	private static StackManager mInstance;

	/**
	 * @ÃèÊö »ñÈ¡Õ»¹ÜÀí¹¤¾ß
	 * @return ActivityManager
	 */
	public static StackManager getStackManager() {
		if (mInstance == null) {
			mInstance = new StackManager();
		}
		return mInstance;
	}

	/**
	 * ÍÆ³öÕ»¶¥Activity
	 */
	public void popActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			mActivityStack.remove(activity);
			activity = null;
		}
	}

	/**
	 * »ñµÃµ±Ç°Õ»¶¥Activity
	 */
	public Activity currentActivity() {
		//lastElement()»ñÈ¡×îºó¸ö×ÓÔªËØ£¬ÕâÀïÊÇÕ»¶¥µÄActivity
		if(mActivityStack == null || mActivityStack.size() ==0){
			return null;
		}
		Activity activity = (Activity) mActivityStack.lastElement();
		return activity;
	}

	/**
	 * ½«µ±Ç°ActivityÍÆÈëÕ»ÖÐ
	 */
	public void pushActivity(Activity activity) {
		if (mActivityStack == null) {
			mActivityStack = new Stack();
		}
		mActivityStack.add(activity);
	}

	/**
	 * µ¯³öÖ¸¶¨µÄclsssËùÔÚÕ»¶¥²¿µÄÖÐËùÓÐActivity
	 * @clsss : Ö¸¶¨µÄÀà 
	 */
	public void popTopActivitys(Class clsss) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(clsss)) {
				break;
			}
			popActivity(activity);
		}
	}
	
	/**
	 * µ¯³öÕ»ÖÐËùÓÐActivity
	 */
	public void popAllActivitys() {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			popActivity(activity);
		}
	}
}
