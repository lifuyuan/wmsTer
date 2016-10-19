package com.wmsterminal.util;

import java.text.ParseException;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import com.wmsterminal.base.Config;

import android.annotation.SuppressLint;

/**
 * @Package: com.mngwyhouhzmb.util
 * @ClassName: DateUtil
 * @Description: ÈÕÆÚ´¦ÀíÊµÓÃÀà
 * @author: LiuSiQing
 * @date: 2015-6-26 ÉÏÎç11:07:21
 */
@SuppressLint({ "UseValueOf", "SimpleDateFormat" })
public class DateUtil {
	
	private static final String TAG = DateUtil.class.getSimpleName();
	
	/**
	 * »ñÈ¡yyyyMMdd¸ñÊ½µÄÏµÍ³ÈÕÆÚ£¬Ä¬ÈÏÈ¡·þÎñÆ÷µ±Ç°ÈÕÆÚ£¬ÈçÏµÍ³²ÎÊý±íÄÚÅäÖÃÁËµ±Ç°¹¤×÷ÈÕ£¬²¢ÇÒDATE_MODE²ÎÊýÅäÖÃÎª1£¬ÔòÈ¡²ÎÊýÈÕÆÚ
	 * 
	 * @return
	 */
	public static String getSysDate() {
		
		/* Ä¬ÈÏÈ¡·þÎñÆ÷ÈÕÆÚ */
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");// »ñÈ¡ÖÐ¹úµÄÊ±Çø
		simpledateformat.setTimeZone(timeZoneChina);// ÉèÖÃÏµÍ³Ê±Çø
		String s = simpledateformat.format(Calendar.getInstance().getTime());
		return s;
	}
	
	/**
	 * »ñÈ¡kkmmss¸ñÊ½µÄÏµÍ³Ê±¼ä
	 * 
	 * @return
	 */
	public static String getSysTime() {
		
		SimpleDateFormat simpledateformat = new SimpleDateFormat("kkmmss");
		TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");// »ñÈ¡ÖÐ¹úµÄÊ±Çø
		simpledateformat.setTimeZone(timeZoneChina);// ÉèÖÃÏµÍ³Ê±Çø
		String s = simpledateformat.format(Calendar.getInstance().getTime());
		return s;
	}
	
	/**
	 * È¡ÄêÔÂÈÕÖÐµÄÄê
	 * 
	 * @param fdate
	 *            ¸ñÊ½£ºyyyyMMdd»òyyyy-MM-dd
	 * @return
	 */
	public static String getYear(String fdate) {
		
		if (ObjectUtil.isEmpty(fdate))
			return "";
		String cur_date = fdate;
		cur_date = cur_date.substring(0, 4);
		return cur_date;
	}
	
	/**
	 * È¡ÄêÔÂÈÕÖÐµÄÔÂ
	 * 
	 * @param fdate
	 *            ¸ñÊ½£ºyyyyMMdd
	 * @return
	 */
	public static String getMonth(String fdate) {
		
		if (ObjectUtil.isEmpty(fdate))
			return "";
		String cur_date = fdate;
		cur_date = cur_date.substring(4, 6);
		return cur_date;
	}
	
	/**
	 * È¡ÄêÔÂÈÕÖÐµÄÈÕ
	 * 
	 * @param fdate
	 *            ¸ñÊ½£ºyyyyMMdd
	 * @return
	 */
	public static String getDay(String fdate) {
		
		if (ObjectUtil.isEmpty(fdate))
			return "";
		String cur_date = fdate;
		cur_date = cur_date.substring(6);
		return cur_date;
	}
	
	/**
	 * ¸ù¾Ýµ±Ç°Äê·Ý»ñÈ¡È¡ÏÂÀ­ÁÐ±íÄê·Ý¼¯ºÏ£¬´Ó2005Äê¿ªÊ¼ÖÁµ±Ç°Äê·Ýºó3Äê
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getYear() {
		
		List vRet = new ArrayList();
		int iCurrYear = Integer.parseInt((getSysDate()).substring(0, 4));
		for (int i = 0; i <= iCurrYear - 2005 + 3; i++) {
			vRet.add(Integer.toString(2005 + i));
		}
		return vRet;
	}
	
	/**
	 * ¸ù¾Ý¸ø¶¨µÄÄê·ÝºÍ¼¾¶ÈÌáÈ¡¶ÔÓ¦µÄ¼¾Ä©ÈÕÆÚ
	 * 
	 * @param ogYear
	 *            Äê·Ý
	 * @param iValue
	 *            ¼¾¶È
	 * @return
	 */
	public static String getQuarterOfLastDate(String ogYear, int iValue) {
		
		String strRetDate = "";
		switch (iValue) {
			case 1:
				strRetDate = ogYear + "0331";
				break;
			case 2:
				strRetDate = ogYear + "0630";
				break;
			case 3:
				strRetDate = ogYear + "0930";
				break;
			case 4:
				strRetDate = ogYear + "1231";
				break;
		}
		return strRetDate;
	}
	
	/**
	 * ¸ù¾Ý²ÎÊýogdate£¬µÃµ½ogdateÕâ¸öÔÂµÄ×îºóÒ»ÌìµÄÈÕÆÚ£¬ÀýÈç£º getLastDate("200308")=20030831
	 * ²ÎÊýogdate±ØÐëÊÇ6Î»£¨yyyyMM£©»ò8Î»£¨yyyyMMdd£©
	 * 
	 * @param ogdate
	 * @return
	 */
	public static String getMonthLastDate(String ogdate) {
		
		if (ogdate.length() == 6)
			ogdate = ogdate + "01";
		else ogdate = ogdate.substring(0, 6) + "01"; // °Ñogdate±ä³ÉÇ°6Î»¼Ó01µÄ´®£¬Èç20030805-->20030801
		ogdate = getNextDateByMonth(ogdate, 1);
		ogdate = getNextDateByNum(ogdate, -1);
		return ogdate;
	}
	
	/**
	 * ¸ù¾Ý²ÎÊýogdate£¬µÃµ½ogdateÕâ¸öÔÂµÄ×îºóÒ»¸ö¹¤×÷ÈÕ£¬ÀýÈç£º
	 * getMonthLastDateNoWeekend("200308")=20030829
	 * ²ÎÊýogdate±ØÐëÊÇ6Î»£¨yyyyMM£©»ò8Î»£¨yyyyMMdd£©
	 * 
	 * @param ogdate
	 * @return
	 */
	public static String getMonthLastWorkDate(String ogdate) {
		
		String sDate = getMonthLastDate(ogdate);
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = simpledateformat.parse(sDate, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int iWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (iWeek == 7)
			sDate = getNextDateByNum(sDate, -1);
		if (iWeek == 1)
			sDate = getNextDateByNum(sDate, -2);
		// calendar.add(2, i);
		date = calendar.getTime();
		return sDate;
	}
	
	/**
	 * µÃµ½ÊäÈëÈÕÆÚ+iÌìÒÔºóµÄÈÕÆÚ
	 * 
	 * @param s
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ
	 * @param i
	 *            ¿ÉÒÔÊÇ¸ºÊý
	 * @return
	 */
	public static String getNextDateByNum(String s, int i) {
		
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = simpledateformat.parse(s, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(5, i);
		date = calendar.getTime();
		s = simpledateformat.format(date);
		return s;
	}
	
	/**
	 * µÃµ½ÊäÈëÈÕÆÚ+iÔÂÒÔºóµÄÈÕÆÚ
	 * 
	 * @param s
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ
	 * @param i
	 *            ¿ÉÒÔÊÇ¸ºÊý
	 * @return
	 */
	public static String getNextDateByMonth(String s, int i) {
		
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = simpledateformat.parse(s, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(2, i);
		date = calendar.getTime();
		s = simpledateformat.format(date);
		return s;
	}
	
	/**
	 * µÃµ½ÊäÈëÈÕÆÚ+iÄêÒÔºóµÄÈÕÆÚ
	 * 
	 * @param s
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ
	 * @param i
	 *            ¿ÉÒÔÊÇ¸ºÊý
	 * @return
	 */
	public static String getNextDateByYear(String s, int i) {
		
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = simpledateformat.parse(s, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, i);
		date = calendar.getTime();
		s = simpledateformat.format(date);
		return s;
	}
	
	/**
	 * ÅÐ¶Ï²ÎÊý×Ö·û´®ÊÇ·ñÎªyyyyMMdd¸ñÊ½µÄÈÕÆÚ
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isDate(String date) {
		
		if (date.length() != 8)
			return false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			dateFormat.parse(date);
		}
		catch (Exception e) {
			return false;
		}
		String year = date.substring(0, 4);
		int y = Integer.parseInt(year);
		if (y < 1975 || y > 2100)
			return false;
		String month = date.substring(4, 6);
		int m = Integer.parseInt(month);
		if (m < 1 || m > 12)
			return false;
		String day = date.substring(6);
		int d = Integer.parseInt(day);
		if (d < 1)
			return false;
		String lastday = getMonthLastDate(date);
		int ld = Integer.parseInt(lastday.substring(6));
		if (d > ld)
			return false;// ´óÓÚ±¾ÔÂ×îºóÒ»Ìì£¬·µ»Ø¼Ù
		return true;
	}
	
	/**
	 * ÅÐ¶Ï²ÎÊý×Ö·û´®ÊÇ·ñÎªkkmmss¸ñÊ½µÄÊ±¼ä
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isTime(String time) {
		
		if (time.length() != 6)
			return false;
		String hour = time.substring(0, 2);
		int h = Integer.parseInt(hour);
		if (h < 0 || h > 24)
			return false;
		String miet = time.substring(2, 4);
		int m = Integer.parseInt(miet);
		if (m < 0 || m > 59)
			return false;
		if (h == 24 && m != 0)
			return false;
		String sd = time.substring(4);
		int s = Integer.parseInt(sd);
		if (s < 0 || s > 59)
			return false;
		if (h == 24 && s != 0)
			return false;
		return true;
	}
	
	/**
	 * ÅÐ¶ÏÈÕÆÚÊÇ·ñ·ûºÏ¸ñÊ½ÒªÇó
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isInPattern(String date, String pattern) {
		
		if (date == null)
			return false;
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			dateFormat.parse(date);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * °ÑYYYY-MM-DD¸ñÊ½ÈÕÆÚ×Ö·û´®¸ñÊ½»¯³ÉyyyyMMdd»òyyyyMM¸ñÊ½
	 * 
	 * @param date
	 *            YYYY-MM-DD¸ñÊ½ÈÕÆÚ×Ö·û´®
	 * @return
	 */
	public static String format2DB(String date) {
		
		if (ObjectUtil.isEmpty(date))
			return "";
		return date.replaceAll("-", "");
	}
	
	/**
	 * °Ñkk:mm:ss»òkk:mm¸ñÊ½Ê±¼ä×Ö·û´®¸ñÊ½»¯³Ékkmmss»òkkmm¸ñÊ½
	 * 
	 * @param time
	 *            kk:mm:ss»òkk:mm¸ñÊ½Ê±¼ä×Ö·û´®
	 * @return
	 */
	public static String formatTime2DB(String time) {
		
		if (ObjectUtil.isEmpty(time))
			return "";
		return time.replaceAll(":", "");
	}
	
	/**
	 * @Title: formatYMFromDB
	 * @Description: °ÑyyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®¸ñÊ½»¯³ÉYYYY-MM¸ñÊ½
	 * @author: LiuSiQing
	 * @date: 2015-4-2 ÏÂÎç5:31:16
	 */
	public static String formatYMFromDB(String date) {
		
		if (ObjectUtil.isEmpty(date))
			return "";
		StringBuffer buf = new StringBuffer(date.subSequence(0, 6));
		return buf.insert(4, "-").toString();
	}
	
	/**
	 * °ÑyyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®¸ñÊ½»¯³ÉYYYY-MM-DD¸ñÊ½
	 * 
	 * @param date
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®
	 * @return
	 */
	public static String formatFromDB(String date) {
		
		if (ObjectUtil.isEmpty(date))
			return "";
		StringBuffer buf = new StringBuffer(date);
		return buf.insert(6, '-').insert(4, '-').toString();
	}
	
	/**
	 * °ÑyyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®¸ñÊ½»¯³ÉYYYY-MM-DD¸ñÊ½
	 * 
	 * @param date
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®
	 * @return
	 */
	public static String formatFromDBPoint(String date) {
		
		if (ObjectUtil.isEmpty(date))
			return "";
		StringBuffer buf = new StringBuffer(date);
		return buf.insert(6, '.').insert(4, '.').toString();
	}
	
	/**
	 * @Title: formatFromDBTwo
	 * @Description: °ÑyyyyMM¸ñÊ½ÈÕÆÚ×Ö·û´®¸ñÊ½»¯³ÉYYYY-MM¸ñÊ½
	 * @param date
	 * @return Sep 15, 2014 8:07:34 PM
	 */
	public static String formatFromDBTwo(String date) {
		
		if (ObjectUtil.isEmpty(date))
			return "";
		StringBuffer buf = new StringBuffer(date);
		return buf.insert(4, '-').toString();
	}
	
	/**
	 * °Ñkkmmss»òkkmm¸ñÊ½Ê±¼ä×Ö·û´®¸ñÊ½»¯³Ékk:mm¸ñÊ½
	 * 
	 * @param time
	 *            kkmmss»òkkmm¸ñÊ½Ê±¼ä×Ö·û´®
	 * @return
	 */
	public static String formatTime4FromDB(String time) {
		
		if (ObjectUtil.isEmpty(time))
			return "";
		if (time.length() == 6)
			time = time.substring(0, 4);
		return new StringBuffer(time).insert(2, ':').toString();
	}
	
	/**
	 * °Ñkkmmss¸ñÊ½Ê±¼ä×Ö·û´®¸ñÊ½»¯³Ékk:mm:ss»òkk:mm¸ñÊ½
	 * 
	 * @param time
	 *            kkmmss¸ñÊ½Ê±¼ä×Ö·û´®
	 * @return
	 */
	public static String formatTimeFromDB(String time) {
		
		if (ObjectUtil.isEmpty(time))
			return "";
		StringBuffer buf = new StringBuffer(time);
		if (time.length() == 4)
			return buf.insert(2, ':').toString();
		return buf.insert(2, ':').insert(5, ':').toString();
	}
	
	/**
	 * °Ñkkmm¸ñÊ½Ê±¼ä×Ö·û´®¸ñÊ½»¯³Ékk:mm¸ñÊ½
	 * 
	 * @param time
	 *            kkmm¸ñÊ½Ê±¼ä×Ö·û´®
	 * @return
	 */
	public static String formatTimeFourFromDB(String time) {
		
		if (ObjectUtil.isEmpty(time))
			return "";
		StringBuffer buf = new StringBuffer(time);
		return buf.insert(2, ':').toString();
	}
	
	/**
	 * °ÑyyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®¸ñÊ½»¯³ÉYYYY-MM-DD¸ñÊ½,°Ñkkmmss¸ñÊ½Ê±¼ä×Ö·û´®¸ñÊ½»¯³Ékk:mm:ss¸ñÊ½,ÖÐ¼äÎÞ¿Õ¸ñ
	 * 
	 * @param date
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®
	 * @param time
	 *            kkmmss¸ñÊ½Ê±¼ä×Ö·û´®
	 * @return
	 */
	public static String formatDateTimeFromDB(String date, String time) {
		
		return formatFromDB(date) + formatTimeFromDB(time);
	}
	
	/**
	 * »ñµÃyyyy-MM-ddkk:mm:ss ÈÕÆÚ
	 * 
	 * @param date
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®
	 * @param time
	 *            kkmmss¸ñÊ½Ê±¼ä×Ö·û´®
	 * @return
	 */
	public static String getSysDateAndTime() {
		
		return formatDateTimeFromDBNotNull(getSysDate(), getSysTime());
	}
	
	/**
	 * °ÑyyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®¸ñÊ½»¯³ÉYYYY-MM-DD¸ñÊ½,°Ñkkmmss¸ñÊ½Ê±¼ä×Ö·û´®¸ñÊ½»¯³Ékk:mm:ss¸ñÊ½,ÖÐ¼ä¼Ó¿Õ¸ñºóÆ´½Ó
	 * 
	 * @param date
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®
	 * @param time
	 *            kkmmss¸ñÊ½Ê±¼ä×Ö·û´®
	 * @return
	 */
	public static String formatDateTimeFromDBNotNull(String date, String time) {
		
		if (ObjectUtil.isEmpty(date) || date.length() < 8)
			date = "        ";
		if (ObjectUtil.isEmpty(time) || date.length() < 6)
			time = "      ";
		StringBuffer buf = new StringBuffer(date);
		buf.insert(6, '-').insert(4, '-');
		StringBuffer buf1 = new StringBuffer(time);
		buf1.insert(2, ':').insert(5, ':');
		return buf.toString() + " " + buf1.toString();
	}
	
	/**
	 * °ÑyyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®¸ñÊ½»¯³ÉYYYY-MM-DD¸ñÊ½,°Ñkkmmss»òkkmm¸ñÊ½Ê±¼ä×Ö·û´®¸ñÊ½»¯³Ékk£ºmm¸ñÊ½, ÖÐ¼ä¼Ó¿Õ¸ñºóÆ´½Ó
	 * 
	 * @param date
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®
	 * @param time
	 *            kkmmss»òkkmm¸ñÊ½Ê±¼ä×Ö·û´®
	 * @return
	 */
	public static String formatDateTime4(String date, String time) {
		
		if (ObjectUtil.isEmpty(date) && ObjectUtil.isEmpty(time))
			return "";
		if (ObjectUtil.isEmpty(date))
			return formatTime4FromDB(time);
		if (ObjectUtil.isEmpty(time))
			return formatFromDB(date);
		return formatFromDB(date) + " " + formatTime4FromDB(time);
	}
	
	/**
	 * °ÑyyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®¸ñÊ½»¯³ÉYYYY-MM-DD¸ñÊ½,°Ñkkmmss¸ñÊ½Ê±¼ä×Ö·û´®¸ñÊ½»¯³Ékk:mm:ss»òkk£ºmm¸ñÊ½,
	 * ÖÐ¼ä¼Ó¿Õ¸ñºóÆ´½Ó
	 * 
	 * @param date
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ×Ö·û´®
	 * @param time
	 *            kkmmss¸ñÊ½Ê±¼ä×Ö·û´®
	 * @return
	 */
	public static String formatDateTime(String date, String time) {
		
		if (ObjectUtil.isEmpty(date) && ObjectUtil.isEmpty(time))
			return "";
		if (ObjectUtil.isEmpty(date))
			return formatTimeFromDB(time);
		if (ObjectUtil.isEmpty(time))
			return formatTimeFromDB(date);
		return formatFromDB(date) + " " + formatTimeFromDB(time);
	}
	
	/**
	 * µÃµ½"yyyyÄêMMÔÂddÈÕ"¸ñÊ½ÈÕÆÚ
	 * 
	 * @param fdate
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ
	 * @return
	 */
	public static String getCNDate(String fdate) {
		
		if (ObjectUtil.isEmpty(fdate))
			return "";
		String cur_date = fdate;
		cur_date = cur_date.substring(0, 4) + "Äê" + cur_date.substring(4, 6) + "ÔÂ" + cur_date.substring(6) + "ÈÕ";
		return cur_date;
	}
	
	/**
	 * µÃµ½"yyyyÄêMMÔÂ"¸ñÊ½ÈÕÆÚ
	 * 
	 * @param fdate
	 *            yyyyMMdd¸ñÊ½ÈÕÆÚ
	 * @return
	 */
	public static String getCNDateYM(String fdate) {
		
		if (ObjectUtil.isEmpty(fdate))
			return "";
		String cur_date = fdate;
		cur_date = cur_date.substring(0, 4) + "Äê" + cur_date.substring(4, 6) + "ÔÂ";
		return cur_date;
	}
	
	/**
	 * µÃµ½"kkÊ±mm·ÖssÃë"¸ñÊ½Ê±¼ä
	 * 
	 * @param ftime
	 *            kkmmss¸ñÊ½Ê±¼ä
	 * @return
	 */
	public static String getCNTime(String ftime) {
		
		if (ObjectUtil.isEmpty(ftime)) {
			return "";
		}
		String cur_time = ftime;
		cur_time = cur_time.substring(0, 2) + "Ê±" + cur_time.substring(2, 4) + "·Ö" + cur_time.substring(4) + "Ãë";
		return cur_time;
	}
	
	/**
	 * @Title: getCNFourTime
	 * @Description: µÃµ½"kkÊ±mm·Ö"¸ñÊ½Ê±¼ä
	 * @param ftime
	 * @return Jul 7, 2014 3:54:22 PM
	 */
	public static String getCNFourTime(String ftime) {
		
		if (ObjectUtil.isEmpty(ftime)) {
			return "";
		}
		String cur_time = ftime;
		cur_time = cur_time.substring(0, 2) + "Ê±" + cur_time.substring(2, 4) + "·Ö";
		return cur_time;
	}
	
	/**
	 * ¼ÆËãÁ½¸öÈÕÆÚÏà²îµÄÌìÊý
	 * 
	 * @param startDate
	 *            ¸ñÊ½£ºyyyy-MM-dd
	 * @param endDate
	 *            ¸ñÊ½£ºyyyy-MM-dd
	 * @return ·µ»ØÁ½ÈÕÆÚÏà²îµÄÌìÊý
	 */
	public static int getDatePeriod(String startDate, String endDate) {
		
		String[] date1 = startDate.split("-");
		String[] date2 = endDate.split("-");
		GregorianCalendar gc1 = new GregorianCalendar(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]),
				Integer.parseInt(date1[2]));
		GregorianCalendar gc2 = new GregorianCalendar(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]),
				Integer.parseInt(date2[2]));
		long longDate1 = gc1.getTimeInMillis();
		long longDate2 = gc2.getTimeInMillis();
		long period = longDate2 - longDate1;
		period /= 24 * 60 * 60 * 1000;
		return (int) period;
	}
	
	/**
	 * ¼ÆËãÁ½¸öÈÕÆÚÏà²îµÄÌìÊý
	 * 
	 * @param startDate
	 *            ¸ñÊ½£ºyyyyMMdd
	 * @param endDate
	 *            ¸ñÊ½£ºyyyyMMdd
	 * @return ·µ»ØÁ½ÈÕÆÚÏà²îµÄÌìÊý
	 */
	public static int dateMargin(String startDate, String endDate) {
		
		String d1 = format2DB(startDate);
		String d2 = format2DB(endDate);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(df.parse(d1, new ParsePosition(0)));
		Date end = df.parse(d2, new ParsePosition(0));
		int margin = 0;
		int step = startDate.compareTo(endDate) > 0 ? -1 : 1;
		while (calendar.getTime().compareTo(end) != 0) {
			calendar.add(Calendar.DATE, step);
			margin += step;
		}
		return margin;
	}
	
	/**
	 * ¼ÆËãÁ½¸öÊ±¼äµÄ²î£¨µ¥Î»£ºÃë£©
	 * 
	 * @param begin_dt
	 *            ¸ñÊ½£ºyyyyMMddkkmmss
	 * @param end_dt
	 *            ¸ñÊ½£ºyyyyMMddkkmmss
	 * @return ²î£¨Ãë£©
	 */
	public static int getSecsDiff(String begin_dt, String end_dt) {
		
		if (begin_dt == null || end_dt == null)
			return 0;
		if (begin_dt.length() == 8)
			begin_dt = begin_dt + "000000";
		if (begin_dt.length() == 6)
			begin_dt = getSysDate() + begin_dt;
		if (end_dt.length() == 8)
			end_dt = end_dt + "000000";
		if (end_dt.length() == 6)
			end_dt = getSysDate() + end_dt;
		int iBYYYY = Integer.parseInt(begin_dt.substring(0, 4));
		int iBMM = Integer.parseInt(begin_dt.substring(4, 6));
		int iBDD = Integer.parseInt(begin_dt.substring(6, 8));
		int iBhh = Integer.parseInt(begin_dt.substring(8, 10));
		int iBmm = Integer.parseInt(begin_dt.substring(10, 12));
		int iBss = Integer.parseInt(begin_dt.substring(12, 14));
		int iEYYYY = Integer.parseInt(end_dt.substring(0, 4));
		int iEMM = Integer.parseInt(end_dt.substring(4, 6));
		int iEDD = Integer.parseInt(end_dt.substring(6, 8));
		int iEhh = Integer.parseInt(end_dt.substring(8, 10));
		int iEmm = Integer.parseInt(end_dt.substring(10, 12));
		int iEss = Integer.parseInt(end_dt.substring(12, 14));
		Calendar BeginDate = new GregorianCalendar(iBYYYY, iBMM, iBDD, iBhh, iBmm, iBss);
		Calendar EndDate = new GregorianCalendar(iEYYYY, iEMM, iEDD, iEhh, iEmm, iEss);
		long lBegin = BeginDate.getTime().getTime();
		long lEnd = EndDate.getTime().getTime();
		// long lDiff = (lEnd > lBegin) ? (lEnd - lBegin) : (lBegin - lEnd);
		long lDiff = lBegin - lEnd;
		BeginDate = null;
		EndDate = null;
		return (int) (lDiff / 1000);
	}
	
	/**
	 * ¸ù¾ÝÈÕÆÚ×Ö·û´®»ñµÃjava.util.Date¶ÔÏó
	 * 
	 * @param StringÀàÐÍµÄÈÕÆÚ
	 * @param ÊäÈëÈÕÆÚµÄ¸ñÊ½
	 * @return Date¶ÔÏó
	 */
	public static Date getDateByString(String dateByString, String pattern) throws Exception {
		
		Date date = null;
		if (dateByString == null || dateByString.trim().equals(""))
			return null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			date = format.parse(dateByString);
		}
		catch (Exception e) {
			String error = "ÊäÈëµÄÈÕÆÚ¸ñÊ½²»ÕýÈ·£¬ÇëÊäÈë" + pattern + "¸ñÊ½µÄÈÕÆÚ";
			throw new Exception(error, e);
		}
		return date;
	}
	
	/**
	 * ¸ù¾Ýjava.util.Date¶ÔÏó»ñµÃÖ¸¶¨¸ñÊ½µÄÈÕÆÚ×Ö·û´®
	 * 
	 * @param Date¶ÔÏó
	 * @param Êä³öÈÕÆÚµÄ¸ñÊ½
	 * @return StringÀàÐÍµÄÈÕÆÚ
	 */
	public static String getStringByDate(Date date, String pattern) {
		
		if (date == null)
			return null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * ¸ù¾Ý¹¤×÷ÈÕÆÚ»ñµÃÉÏÒ»½áÏ¢ÈÕ,Èç¹û¹¤×÷ÈÕ±¾ÉíÎª½áÏ¢ÈÕ·µ»ØÉÏ¼¾¶È½áÏ¢ÈÕ
	 * 
	 * @param workDate
	 *            yyyyMMddÐÎÊ½ÈÕÆÚ×Ö·û´®
	 * @param cutday
	 *            ·ÖÌ¯ÈÕÆÚ£¬Í¨³£Îª21
	 * @return
	 */
	public static String getLastIntDate(String workDate, int cutday) {
		
		String retDate = "";
		/**
		 * ½áÏ¢ÔÂ£½µ±Ç°ÔÂÈÕ/((£¨µ±Ç°ÔÂ·Ý£­1£©/3*3+3)*100+·ÖÌ¯ÈÕ)*3+£¨µ±Ç°ÔÂ·Ý£­1£©/3*3+3-3
		 */
		int flag = (Integer.parseInt(getMonth(workDate)) - 1) / 3 * 3 + 3;
		int month = Integer.parseInt(getMonth(workDate) + getDay(workDate)) / ((flag) * 100 + cutday) * 3 + flag - 3;
		int year = (month == 0) ? Integer.parseInt(getYear(workDate)) - 1 : Integer.parseInt(getYear(workDate));
		month = (month == 0) ? 12 : month;
		retDate = year + StringUtil.fillString(new Integer(month).toString(), '0', 2) + "20";
		return retDate;
	}
	
	/**
	 * @Title: getSysDateAndTime
	 * @Description: µÃµ½ÖÐÎÄµÄÈÕÆÚºÍÊ±¼ä
	 * @return Sep 2, 2014 4:35:24 PM
	 */
	public static String getSysCNDateAndTime() {
		
		return getCNDate(getSysDate()) + "\u3000" + getCNTime(getSysTime());
	}
	
	/**
	 * @Title: isOutDate
	 * @Description: ÅÐ¶ÏÊÇ·ñÓâÆÚ
	 * @param enddate
	 * @param endtime
	 * @param date
	 * @return
	 */
	public static boolean isOutDate(String enddate, String endtime, String date) {
		
		// ÅÐ¶ÏÊÇ·ñ³¬¹ý×îºóÉÏ±¨Ê±¼ä
		if (ObjectUtil.isEmpty(enddate)) {
			enddate = (Integer.parseInt(DateUtil.getSysDate()) + 10) + "";
		}
		if (ObjectUtil.isEmpty(endtime)) {
			endtime = "00";
		}
		String endTime = enddate + endtime + "0000";
		String sysTime = date + "000000";
		int iidate = DateUtil.getSecsDiff(sysTime, endTime);
		// ÓâÆÚ
		if (iidate > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @Title: isOutDate
	 * @Description: ÅÐ¶ÏÊÇ·ñÓâÆÚ
	 * @param enddate
	 * @param date
	 * @return
	 */
	public static boolean isOutDate(String enddate, String date) {
		
		// ÅÐ¶ÏÊÇ·ñ³¬¹ý×îºóÉÏ±¨Ê±¼ä
		int dateTime = DateUtil.getDatePeriod(date, enddate);
		// ËùÑ¡Ê±¼ä±ÈÏÖÔÚÐ¡
		if (dateTime > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @Title: isOutSysTime
	 * @Description: ÊÇ·ñ±ÈÏµÍ³Ê±¼ä´ó
	 * @param mTime
	 * @return Jul 15, 2014 5:41:23 PM
	 */
	public static boolean isOutSysTime(String mDate, String mTime) {
		
		if (ObjectUtil.isEmpty(mDate))
			mDate = "00000000";
		else mDate = mDate.trim(); // Ïû³ý¿Õ¸ñ
		if (ObjectUtil.isEmpty(mTime))
			mTime = "000000";
		else mTime = mTime.trim(); // Ïû³ý¿Õ¸ñ
		if (mTime.length() == 2)
			mTime = mTime + "0000";
		if (mTime.length() == 4)
			mTime = mTime + "00";
		String mTemp = mDate + mTime;
		if ((Long.valueOf(mTemp) - Long.valueOf(getSysDate() + getSysTime())) > 0)
			return true;
		else return false;
	}
	
	/**
	 * @Title: isFastDoubleClick
	 * @Description:Config.LAST_CLICK_TIMEÊÇ·ñÊÇ¿ìËÙµã»÷(0.3Ãë)
	 * @return Sep 2, 2014 4:50:06 PM
	 */
	public synchronized static boolean isFastDoubleClick() {
		
		if (isFastDoubleClick(Config.LAST_CLICK_TIME)) {
			return true;
		}
		
		Config.LAST_CLICK_TIME = System.currentTimeMillis();
		return false;
	}
	
	/**
	 * @Title: isFastDoubleClick
	 * @Description: ÊÇ·ñÊÇ¿ìËÙµã»÷(0.3Ãë)
	 * @param lastClick
	 * @return Sep 2, 2014 4:50:06 PM
	 */
	public static boolean isFastDoubleClick(Long lastClick) {
		
		return isFastDoubleClick(lastClick, 200);
	}
	
	/**
	 * @Title: isFastDoubleClick
	 * @Description: ÊÇ·ñ¿ìËÙµã»÷
	 * @param lastClick
	 * @param time
	 * @return Sep 17, 2014 6:11:20 PM
	 */
	public static boolean isFastDoubleClick(Long lastClick, int time) {
		
		if (System.currentTimeMillis() - lastClick <= time) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: getSysDifftime
	 * @Description: ÏµÍ³Ê±¼ä²î
	 * @return Oct 11, 2014 3:54:22 PM
	 */
	public static long getSysDifftime(String sys_date, String sys_time, String now_date, String now_time) {
		
		return getSysDifftime(sys_date + sys_time, now_date + now_time);
	}
	
	/**
	 * @Title: getSysDifftime
	 * @Description: ÏµÍ³Ê±¼ä²î
	 * @return Oct 11, 2014 3:54:22 PM
	 */
	public static long getSysDifftime(String sys_time, String now_time) {
		
		if (ObjectUtil.isEmpty(sys_time) || ObjectUtil.isEmpty(now_time) || sys_time.length() < now_time.length()) // Á½¸öÊ±¼äÄÜÎª¿Õ£¬ÇÒÏµÍ³Ê±¼äµÄ³¤¶È²»ÄÜÐ¡ÓÚ±»±È½ÏµÄ
			return 0;
		if (sys_time.length() == 8) // Ö»ÓÐÏµÍ³ÈÕÆÚ
			sys_time = sys_time + "000000";
		if (sys_time.length() == 6) // Ö»ÓÐÏµÍ³Ê±¼ä
			sys_time = "00000000" + sys_time;
		if (now_time.length() == 8)
			now_time = now_time + "000000";
		if (now_time.length() == 6)
			now_time = "00000000" + now_time;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		try {
			long sys = sdf.parse(sys_time).getTime();
			long now = sdf.parse(now_time).getTime();
			return sys - now;
		}
		catch (ParseException e) {
			DebugUtil.e(TAG, e.toString());
			return 0;
		} // ºÁÃë
	}
	
	/**
	 * @Title: getTiemAgoCN
	 * @Description: ½«ºÁÃë×ª³ÉÊ±¼ä
	 * @param time
	 * @return Oct 11, 2014 4:05:14 PM
	 */
	public static String getTiemAgoCN(long time) {
		
		if (0 >= time)
			return "";
		time = time / 1000; // ×ª»»³ÉÃë
		if (60 > time) // Ò»·ÖÖÓÄÚ
			return "¸Õ¸Õ";
		time = time / 60; // ·ÖÖÓ
		if (60 > time) // Ò»Ð¡Ê±ÄÚ
			return time + "·ÖÖÓÇ°";
		time = time / 60; // Ð¡Ê±
		if (24 > time) // Ò»ÌìÄÚ
			return time + "Ð¡Ê±Ç°";
		time = time / 24; // Ìì
		return time + "ÌìÇ°";
	}
	
	/**
	 * ½«YYYYMMDD ×ª»¯Îª MM-DD
	 * 
	 * @param dateString
	 * @return
	 */
	public static String getDateForMouth(String dateString) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat simpledateformat = new SimpleDateFormat("MM-dd");
		String s = "";
		Date newDate = null;
		try {
			newDate = dateFormat.parse(dateString);
			s = simpledateformat.format(newDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
			s = "";
		}
		return s;
	}
	
	/**
	 * @Title: monthDBToCN
	 * @Description: ÔÂ·Ý×ªÖÐÎÄ
	 * @author: LiuSiQing
	 * @date: 2015-3-31 ÏÂÎç5:02:56
	 */
	public static String monthDBToCN(String month) {
		
		try {
			int m = Integer.parseInt(month);
			switch (m) {
				case 2:
					return "¶þÔÂ";
				case 3:
					return "ÈýÔÂ";
				case 4:
					return "ËÄÔÂ";
				case 5:
					return "ÎåÔÂ";
				case 6:
					return "ÁùÔÂ";
				case 7:
					return "ÆßÔÂ";
				case 8:
					return "°ËÔÂ";
				case 9:
					return "¾ÅÔÂ";
				case 10:
					return "Ê®ÔÂ";
				case 11:
					return "Ê®Ò»";
				case 12:
					return "Ê®¶þ";
				default:
					return "Ò»ÔÂ";
			}
		}
		catch (Exception e) {
			return "Ò»ÔÂ";
		}
	}
}
