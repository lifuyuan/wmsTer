package com.wmsterminal.util;

/**
 * ÆÁÄ»ÐÅÏ¢Util
 */
public class DisplayUtil extends LocalDisplay {

	public static int getWidthPercent(double percent) {
		return (int) (SCREEN_WIDTH_PIXELS * percent);
	}

	public static int getHeightPercent(double percent) {
		return (int) (SCREEN_HEIGHT_PIXELS * percent);
	}
}
