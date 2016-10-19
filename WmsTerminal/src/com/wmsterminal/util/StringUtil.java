package com.wmsterminal.util;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.annotation.SuppressLint;

/**
 * @Package: com.mngwyhouhzmb.util
 * @ClassName: StringUtil
 * @Description: ×Ö·û´®´¦ÀíÊµÓÃÀà
 * @author: LiuSiQing
 * @date: 2015-6-26 ÉÏÎç11:09:27
 */
@SuppressLint("DefaultLocale") public final class StringUtil {

	public static long genEventCode() {
		return System.currentTimeMillis();
	}

	/**
	 * @Title: trim
	 * @Description: TODO
	 * @author: LiuSiQing
	 * @date: 2015-4-17 ÉÏÎç11:44:19
	 */
	public static String trim(String word) {
		if( null != word )
			word = word.trim();
		return word;
	}

	/**
	 * °Ñ²ÎÊýwordµÄµÚÒ»¸ö×ÖÄ¸´óÐ´
	 * @param word
	 * @return
	 */

	public static final String upperFirstWord(String word) {
		String firstWord = word.substring(0, 1).toUpperCase();
		return firstWord + word.substring(1);
	}

	public static final String upperWord(String word) {
		return word.toUpperCase();
	}

	/**
	 * @Title: lowerFirstWord
	 * @Description: °Ñ²ÎÊýwordµÄµÚÒ»¸ö×ÖÄ¸Ð¡Ð´
	 * @param word
	 * @return Jun 23, 2014 1:33:41 PM
	 */
	public static final String lowerFirstWord(String word) {
		String firstWord = word.substring(0, 1).toLowerCase();
		return firstWord + word.substring(1);
	}

	/**
	 * @Title: encodeByte
	 * @Description: ½«×Ö½ÚÊý×é×ª»»ÎªÖ¸¶¨±àÂëµÄ×Ö·û´®
	 * @param strbyte
	 * @param destEncoding Ä¿±ê±àÂë
	 * @return Jun 23, 2014 1:33:53 PM
	 */
	public static String encodeByte(byte[] strbyte, String destEncoding) {
		String ret = null;
		try {
			ret = new String(strbyte, destEncoding);
		} catch(Exception e) {
			ret = "byte to " + destEncoding + " error. str=[" + strbyte + "]";
		}
		return (ret);
	}

	/**
	 * ½«×Ö½ÚÊý×é×ª»»ÎªÖ¸¶¨±àÂëµÄ×Ö·û´®
	 * @param str
	 * @param srcEncoding Ô­×Ö·û´®±àÂë
	 * @param destEncoding Ä¿±ê±àÂë
	 * @return
	 */
	public static String encodeStr(String str, String srcEncoding, String destEncoding) {
		String ret = null;
		try {
			ret = new String(str.getBytes(srcEncoding), destEncoding);
		} catch(Exception e) {
			ret = srcEncoding + " to " + destEncoding + " error. str=" + str;
		}
		return (ret);
	}

	/**
	 * ISO-8859-1×ªUTF-8
	 * @param str
	 * @return
	 */
	public static String ISO2UTF8(String str) {
		String ret = null;
		try {
			ret = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch(Exception e) {
			ret = "ISO2UTF8 error. str=" + str;
		}
		return (ret);
	}

	/**
	 * UTF-8×ªISO-8859-1
	 * @param str
	 * @return
	 */
	public static String UTF82ISO(String str) {
		String ret = null;
		try {
			ret = new String(str.getBytes("UTF-8"), "ISO-8859-1");
		} catch(Exception e) {
			ret = "UTF82ISO error. str=" + str;
		}
		return (ret);
	}

	/**
	 * ISO-8859-1×ªGBK
	 * @param str
	 * @return
	 */
	public static String ISO2GBK(String str) {
		String ret = null;
		try {
			ret = new String(str.getBytes("ISO-8859-1"), "GBK");
		} catch(Exception e) {
			ret = "ISO2GBK error. str=" + str;
		}
		return (ret);
	}

	/**
	 * GBK×ªISO-8859-1
	 * @param str
	 * @return
	 */
	public static String GBK2ISO(String str) {
		String ret = null;
		try {
			ret = new String(str.getBytes("GBK"), "ISO-8859-1");
		} catch(Exception e) {
			ret = "GBK2ISO error. str=" + str;
		}
		return (ret);
	}

	/**
	 * °ÑÔ´´®sOldÖÐÎªsParttenµÄ×Ö·û´®ÓÃsReplaceÌæ»»£¬
	 * @param sOld Ô´×Ö·û´®
	 * @param sPartten ÒªÌæ»»µÄ×Ö·û´®
	 * @param sReplace Ìæ»»³ÉµÄ×Ö·û´®
	 * @return
	 */
	public static String replace(String sOld, String sPartten, String sReplace) {
		if( sOld == null )
			return null;
		if( sPartten == null )
			return sOld;
		if( sReplace == null )
			sReplace = "";
		StringBuffer sBuffer = new StringBuffer();
		int isOldLen = sOld.length();
		int isParttenLen = sPartten.length();
		int iIndex = -1;
		int iHead = 0;
		while( (iIndex = sOld.indexOf(sPartten, iHead)) > -1 ) {
			sBuffer.append(sOld.substring(iHead, iIndex)).append(sReplace);
			iHead = iIndex + isParttenLen;
		}
		sBuffer.append(sOld.substring(iHead, isOldLen));
		return sBuffer.toString();
	}

	/**
	 * °ÑÊäÈëÖµ¸ñÊ½»¯±£ÁôÁ½Î»Ð¡Êý£¬Èçprice=4535.234095 Ôò´Ëº¯Êý·µ»ØÎª4535.23
	 * @param price
	 * @return
	 */
	public static String format2Decimal(double price) {
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance(java.util.Locale.CHINESE);
		java.text.DecimalFormat df = (java.text.DecimalFormat)nf;
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		String pattern = "#0.00";
		df.applyPattern(pattern);
		df.setDecimalSeparatorAlwaysShown(true);
		return df.format(price);
	}

	/**
	 * °ÑÊäÈëÖµ¸ñÊ½»¯±£ÁôÁ½Î»Ð¡Êý£¬Èçprice=4535.234095 Ôò´Ëº¯Êý·µ»ØÎª4535.23
	 * @param price
	 * @return
	 */
	public static String format2Decimal(String price) {
		double d = 0.0;
		if( ObjectUtil.isEmpty(price) )
			d = 0.0;
		else
			try {
				d = Double.parseDouble(price);
			} catch(java.lang.NumberFormatException ex) {
				d = 0.0;
			} catch(java.lang.Exception ex) {
				d = 0.0;
			}
		return format2Decimal(d);
	}

//	/**
//	 * ½«½ð¶î×ª»»³ÉÇ§·ÖÎ»¸ñÊ½
//	 * @param price
//	 * @return
//	 */
//	public static String format2Amt(String price) {
//		double d = 0.0;
//		if( ObjectUtil.isEmpty(price) )
//			d = 0.0;
//		else
//			try {
//				d = Double.parseDouble(price);
//			} catch(java.lang.NumberFormatException ex) {
//				d = 0.0;
//			} catch(java.lang.Exception ex) {
//				d = 0.0;
//			}
//		return MoneyUtil.toAmtString(format2Decimal(d));
//	}
//
//	/**
//	 * ½«½ð¶î×ª»»³ÉÇ§·ÖÎ»ºóÌí¼ÓÈËÃñ±Ò·ûºÅÓë"Ôª"
//	 * @param price
//	 * @return
//	 */
//	public static String format2AmtWithYuan(String price) {
//		return "£¤" + format2Amt(price) + "Ôª";
//	}

	/**
	 * °´·Ö¸ô·ûºÅ¶Á³ö×Ö·û´®µÄÄÚÈÝ
	 * @param strlist º¬ÓÐ·Ö¸ô·ûºÅµÄ×Ö·û´®
	 * @param ken ·Ö¸ô·ûºÅ
	 * @return ÁÐ±í
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final List parseStringToArrayList(String strlist, String ken) {
		StringTokenizer st = new StringTokenizer(strlist, ken);
		if( strlist == null || strlist.equals("") || st.countTokens() <= 0 )
			return new ArrayList();
		int size = st.countTokens();
		List strv = new ArrayList();
		for(int i = 0; i < size; i++) {
			String nextstr = st.nextToken();
			if( !nextstr.equals("") )
				strv.add(nextstr);
		}
		return strv;
	}

	/**
	 * °ÑÔ´´®strÒÔdelimÎª·Ö¸î·ûºÅ£¬·Ö³ÉÒ»¸öStringÊý×é·µ»Ø£¬Èç str="aaa,bbb,ccc" , delim="," Ôò´Ëº¯Êý·µ»ØÎª
	 * String[0]="aaa" String[1]="bbb" String[2]="ccc"
	 * @param str ´ý´¦Àí×Ö·û´®
	 * @param delim ·Ö¸ô·û
	 * @return
	 */
	public static String[] split(String str, String delim) {
		if( ObjectUtil.isEmpty(delim) ) {
			String[] strReturn = new String[1];
			strReturn[0] = str;
		}
		StringTokenizer st = new StringTokenizer(str, delim);
		int size = st.countTokens();
		if( size < 0 )
			return null;
		String[] strReturn = new String[size];
		int i = 0;
		while( st.hasMoreTokens() ) {
			strReturn[i++] = st.nextToken();
		}
		return strReturn;
	}

	/**
	 * °ÑÔ´´®strÒÔdelimÎª·Ö¸î·ûºÅ£¬·Ö³ÉÒ»¸öStringÊý×é·µ»Ø£¬<br>
	 * Èç str="aaa,,bbb,ccc" , delim=","<br>
	 * Ôò´Ëº¯Êý·µ»ØÎª String[0]="aaa"<br>
	 * String[1]="" <br>
	 * String[2]="bbb"<br>
	 * String[3]="ccc"
	 * @param str ´ý´¦Àí×Ö·û´®
	 * @param delim ·Ö¸î·û
	 * @return Èç¹û´«ÈëµÄ×Ö·ûÎª¿Õ£¬·µ»ØÊý×éstring[0]=""
	 */
	public static String[] splitInNull(String str, String delim) {
		if( ObjectUtil.isEmpty(delim) ) {
			String[] strReturn = new String[1];
			strReturn[0] = str;
			return strReturn;
		}
		if( ObjectUtil.isEmpty(str) ) {
			String[] strReturn = new String[1];
			strReturn[0] = str;
			return strReturn;
		}
		StringTokenizer st = new StringTokenizer(str, delim, true);
		int i = st.countTokens();
		StringBuffer tempString = new StringBuffer();
		for(int j = 0; j < i; j++)
			tempString.append(" " + st.nextToken() + " ");
		st = new StringTokenizer(tempString.toString(), delim);
		i = st.countTokens();
		String[] tempArray = new String[i];
		for(int j = 0; j < i; j++)
			tempArray[j] = st.nextToken().trim();
		return tempArray;
	}

	/**
	 * ½«´«ÈëµÄ×Ö·û´®Êý×é×ª»»³É×Ö·û´®£¬×ª»»Ö®ºóµÄ¸ñÊ½£º'xxx','xxx',...,'xxx'
	 * @param stringArray
	 * @return
	 */
	public static String Array2String(String stringArray[]) {
		try {
			String StringResult = "";
			if( ObjectUtil.isEmpty(stringArray) ) {
				return StringResult;
			}
			int size = stringArray.length;
			for(int i = 0; i < size; i++) {
				if( ObjectUtil.isEmpty(stringArray[i]) )
					continue;
				StringResult += "'" + stringArray[i] + "',";
			}
			StringResult = StringResult.substring(0, StringResult.length() - 1);
			return StringResult;
		} catch(Exception ex) {
			return "";
		}
	}

	/**
	 * ½«´«ÈëµÄ×Ö·û´®Êý×é×ª»»³ÉÖ¸¶¨·Ö¸ô·ûµÄ×Ö·û´® ÒÔdelimÎª·Ö¸ô£¬Èç Array2String(array[],"-")£¬Ôò·µ»Ø´®ÊÇ'-'·Ö¸ôµÄ
	 * @param stringArray
	 * @param delim ·Ö¸î·û
	 * @return
	 */
	public static String Array2String(String stringArray[], String delim) {
		try {
			String StringResult = "";
			if( ObjectUtil.isEmpty(stringArray) ) {
				return StringResult;
			}
			int size = stringArray.length;
			if( size == 1 ) {
				return stringArray[0];
			}
			for(int i = 0; i < size - 1; i++) {
				if( ObjectUtil.isEmpty(stringArray[i]) )
					continue;
				StringResult += stringArray[i] + delim;
			}
			StringResult += stringArray[size - 1];
			return StringResult;
		} catch(Exception ex) {
			return "";
		}
	}

	/**
	 * ½«×Ö·û´®¸ñÊ½»¯³É HTML ´úÂëÊä³ö
	 * @param string Òª¸ñÊ½»¯µÄ×Ö·û´®
	 * @return ¸ñÊ½»¯ºóµÄ×Ö·û´®
	 */
	public static String toHtml(String string) {
		if( string == null )
			return "";
		string = string.replaceAll("&", "&amp;");
		string = string.replaceAll("\"", "&quot;");
		string = string.replaceAll("<", "&lt;");
		string = string.replaceAll(">", "&gt;");
		// string = string.replaceAll("\r\n", "\n");
		string = string.replaceAll("\n", "<br>\n");
		string = string.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		string = string.replaceAll(" ", "&nbsp;");
		return string;
	}

	/**
	 * ½«sqlÄÚµÄ'×ª»»³É''
	 * @param sql
	 * @return
	 */
	public static String toSql(String sql) {
		if( sql == null )
			return "";
		sql = sql.replaceAll("'", "''");
		return sql;
	}

	/**
	 * ½«sqlÄÚµÄ''×ª»»³É'
	 * @param sql
	 * @return
	 */
	public static String fromSql(String sql) {
		if( sql == null )
			return "";
		sql = sql.replaceAll("''", "'");
		return sql;
	}

	/**
	 * µÃµ½Ò»¶ÔÏótoStringµÄÖµ£¬Èç¹ûÊÇnullÔò·µ»Ø""
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj) {
		if( null == obj || ObjectUtil.isEmpty(obj.toString()) )
			return "";
		else
			return obj.toString();
	}

	/**
	 * µÃµ½Ò»¶ÔÏótoStringµÄÖµ£¬Èç¹ûÊÇnullÔò·µ»ØÄ¬ÈÏÖµ£¬Èç¹ûÄ¬ÈÏÖµÎªnullÔò·µ»Ø""
	 * @param obj
	 * @param defaultObj Ä¬ÈÏÖµ
	 * @return
	 */
	public static String getString(Object obj, Object defaultObj) {
		if( null == obj || ObjectUtil.isEmpty(obj.toString()) )
			if( null == defaultObj )
				return "";
			else
				return defaultObj.toString();
		else
			return obj.toString();
	}

	/**
	 * ´Ó×Ö·û´®Ê×Î»¿ªÊ¼»ñÈ¡Ö¸¶¨×Ö·û´®µÄÖ¸¶¨³¤¶ÈµÄ×Ó´®£¬²»¿¼ÂÇÖÐÎÄÇé¿öÔì³ÉµÄ×Ö·û´®³¤¶È±ä»¯ÎÊÌâ
	 * @param sourceString Ô´×Ö·û´®
	 * @param maxLength ½ØÈ¡×î´ó³¤¶È
	 * @return ¼ôÇÐºóµÄ½á¹û£¬Èç¹ûÔ´×Ö·û´®Îªnull£¬·µ»Ø¿Õ´®
	 */
	public static String subString(String sourceString, int maxLength) {
		String innerSourceString = sourceString;
		if( null == sourceString )
			innerSourceString = ""; // Èç¹ûÎªnull£¬·µ»Ø¿Õ´®
		String endString = "";
		int trueLength = innerSourceString.length();
		if( trueLength > maxLength )
			endString = innerSourceString.substring(0, maxLength); // Êµ¼Ê³¤¶È´óÓÚÐèÒªµÄ³¤¶È
		else
			endString = innerSourceString;
		return endString;
	}

	/**
	 * °´×Ö·û³¤¶È½ØÈ¡×Ö¶Î´®£¬³¬¹ý×î´ó×Ö·û´®µÄ×·¼Ó"..."·ûºÅ£¬×¢ÊäÈëµÄ½ØÈ¡²ÎÊý³¤¶ÈÒÑ°üº¬Ê¡ÂÔºÅ£¬Êµ¼Ê½ØÈ¡ÄÚÈÝ³¤¶È±È²ÎÊýÖµÉÙ1¸ö×Ö·û String
	 * str = "ÎÒÊÇÒ»Ö»Ð¡Ð¡Ð¡Ð¡Äñ";
	 * System.out.println(StringUtil.subStringAppendDots(str,5));
	 * Êä³ö½á¹ûÊÇ"ÎÒÊÇÒ»Ö»...";
	 * @param sourceString
	 * @param maxLength
	 * @return
	 */
	public static String subStringAppendDots(String sourceString, int maxLength) {
		String str = null;
		if( sourceString.length() > maxLength )
			str = subString(sourceString, maxLength - 1) + "...";
		else
			str = sourceString;
		return str;
	}

	/**
	 * °´×Ö½Ú³¤¶È½ØÈ¡×Ö¶Î´®£¬³¬¹ý×î´ó×Ö·û´®µÄ×·¼Ó"..."·ûºÅ£¬×¢ÊäÈëµÄ½ØÈ¡²ÎÊý³¤¶ÈÒÑ°üº¬Ê¡ÂÔºÅ£¬Êµ¼Ê½ØÈ¡ÄÚÈÝ³¤¶È±È²ÎÊýÖµÉÙ2-4¸ö×Ö½Ú
	 * @param sourceString
	 * @param maxLength
	 * @return
	 */
	public static String subStringByBytesAppendDots(String sourceString, int maxLength) {
		String str = null;
		if( getStrLenByBytes(sourceString) > maxLength )
			str = subStringByBytes(sourceString, maxLength - 3) + "...";
		else
			str = sourceString;
		return str;
	}

	/**
	 * °´×Ö·û³¤¶È´ÓÎ²²¿½ØÈ¡×Ö¶Î´®£¬³¬¹ý×î´ó×Ö·û´®µÄÌæ»»Îª"..."·ûºÅ£¬×¢ÊäÈëµÄ½ØÈ¡²ÎÊý³¤¶ÈÒÑ°üº¬Ê¡ÂÔºÅ£¬Êµ¼Ê½ØÈ¡ÄÚÈÝ³¤¶È±È²ÎÊýÖµÉÙ1¸ö×Ö·û
	 * String str = "ÎÒÊÇÒ»Ö»Ð¡Ð¡Ð¡Ð¡Äñ";
	 * System.out.println(StringUtil.subStringByBytesBeginWithDots(str,5));
	 * Êä³ö½á¹ûÊÇ"...Ð¡Ð¡Ð¡Äñ";
	 * @param sourceString
	 * @param maxLength
	 * @return
	 */
	public static String subStringByBytesBeginWithDots(String sourceString, int maxLength) {
		String str = null;
		if( getStrLenByBytes(sourceString) > maxLength )
			str = "..." + subStringByBytes(sourceString, (getStrLenByBytes(sourceString) - maxLength + 3), maxLength);
		else
			str = sourceString;
		return str;
	}

	/**
	 * ´Ó×Ö·û´®Ê×Î»¿ªÊ¼»ñÈ¡Ö¸¶¨×Ö·û´®µÄÖ¸¶¨³¤¶ÈµÄ×Ó´®£¬Ê¹ÓÃbyteÐÎÊ½»ñÈ¡×Ö·û´®³¤¶È±ÜÃâÖÐÎÄÇé¿öÔì³ÉµÄ×Ö·û´®³¤¶È±ä»¯ÎÊÌâ
	 * ²¢È¥³ý½ØÈ¡ºóÎ²²¿ÓÉÓÚ°ë¸öÖÐÎÄ×Ö·ûÔì³ÉµÄÂÒÂë
	 * @param sourceString
	 * @param maxLength ½ØÈ¡×Ü³¤¶È
	 * @return ¼ôÇÐºóµÄ½á¹û£¬Èç¹ûÔ´×Ö·û´®Îªnull£¬·µ»Ø¿Õ´®
	 */
	public static String subStringByBytes(String sourceString, int maxLength) {
		return subStringByBytes(sourceString, 0, maxLength);
	}

	/**
	 * ´ÓÖ¸¶¨Æ«ÒÆÎ»ÖÃ¿ªÊ¼»ñÈ¡Ö¸¶¨×Ö·û´®µÄÖ¸¶¨³¤¶ÈµÄ×Ó´®£¬Ê¹ÓÃbyteÐÎÊ½»ñÈ¡×Ö·û´®³¤¶È±ÜÃâÖÐÎÄÇé¿öÔì³ÉµÄ×Ö·û´®³¤¶È±ä»¯ÎÊÌâ
	 * ²¢È¥³ý½ØÈ¡ºóÊ×Î²ÓÉÓÚ°ë¸öÖÐÎÄ×Ö·ûÔì³ÉµÄÂÒÂë
	 * @param sourceString
	 * @param offset Æ«ÒÆÎ»ÖÃ
	 * @param maxLength ½ØÈ¡×Ü³¤¶È
	 * @return ¼ôÇÐºóµÄ½á¹û£¬Èç¹ûÔ´×Ö·û´®Îªnull£¬·µ»Ø¿Õ´®
	 */
	public static String subStringByBytes(String sourceString, int offset, int maxLength) {
		if( maxLength <= 0 )
			return "";
		if( offset < 0 )
			offset = 0;
		String retString = "";
		byte[] sourceByte = sourceString.getBytes();
		int trueLength = sourceByte.length;
		int beginIndex = 0;
		int trueoffset = 0;
		int trueMaxLength = maxLength;
		boolean bChineseFirstHalf = false;
		/* »ñÈ¡¼ÆËãºóµÄÆ«ÒÆÁ¿,Óë¿ªÊ¼Ë÷Òý */
		if( trueLength > offset ) {
			for(int i = 0; i < offset; i++) {
				trueoffset++;// Êµ¼ÊÆ«ÒÆÁ¿
				if( sourceByte[i] > 0 ) {// µ±Ç°Î»ÖÃ²»ÊÇÖÐÎÄ
					bChineseFirstHalf = false;
					beginIndex++;// ¿ªÊ¼Ö¸ÕëÏòºóÒÆ¶¯
				} else if( sourceByte[i] < 0 && !bChineseFirstHalf ) {// µ±Ç°Î»ÖÃÎªÖÐÎÄ×ÖÊ×
					bChineseFirstHalf = true;
					beginIndex++;// ¿ªÊ¼Ö¸ÕëÏòºóÒÆ¶¯
				} else {// µ±Ç°Î»ÖÃÎªÖÐÎÄ×ÖÎ²
					bChineseFirstHalf = false;
					if( i == offset - 1 )
						trueoffset++;// µ±Ç°Î»ÖÃÎªÖÐÎÄ×ÖÎ²Êµ¼ÊÆ«ÒÆÁ¿ÔÙÏòºóÒÆ¶¯£¬Æ«ÒÆÎ»ÖÃÓÀÔ¶ÔÚÖÐÎÄ×ÖÊ×ºó°ë½Ç×Ö·û´¦
					if( trueoffset >= trueLength )
						return "";
				}
			}
		} else
			return "";
		/* »ñÈ¡½áÊøË÷Òý */
		int endIndex = 0;
		if( trueoffset > offset )
			trueMaxLength--; // Æ«ÒÆÎ»ÖÃÏòºó£¬ÔòÊµ¼Ê½ØÈ¡³¤¶È¼õÉÙ
		bChineseFirstHalf = false;
		if( trueLength <= (trueMaxLength + trueoffset) )
			endIndex = sourceString.length();
		else {
			for(int i = 0; i < (trueMaxLength + trueoffset); i++) {
				if( sourceByte[i] > 0 ) {// µ±Ç°Î»ÖÃ²»ÊÇÖÐÎÄ
					bChineseFirstHalf = false;
					endIndex++;// ½áÊøÖ¸ÕëÏòºóÒÆ¶¯
				} else if( sourceByte[i] < 0 && !bChineseFirstHalf )
					bChineseFirstHalf = true; // µ±Ç°Î»ÖÃÎªÖÐÎÄ×ÖÊ×
				else {// µ±Ç°Î»ÖÃÎªÖÐÎÄ×ÖÎ²
					bChineseFirstHalf = false;
					endIndex++;// ½áÊøÖ¸ÕëÏòºóÒÆ¶¯
				}
			}
			if( endIndex > sourceString.length() )
				endIndex = sourceString.length();
		}
		retString = sourceString.substring(beginIndex, endIndex);
		return retString;
	}

	/**
	 * °´ÕÕËùÉè×Ö·û¼¯£¬·µ»Ø×Ö·û´®µÄ³¤¶È£¬Ê¹ÓÃbyteÐÎÊ½»ñÈ¡×Ö·û´®³¤¶È±ÜÃâÖÐÎÄÇé¿öÔì³ÉµÄ×Ö·û´®³¤¶È±ä»¯ÎÊÌâ
	 * @param str ÅÐ¶Ï×Ö·û´®
	 * @param encoding ±àÂëÉè¶¨
	 * @return ´Ë±àÂëµÄ³¤¶È
	 */
	public static int getStrLenByBytes(String str, String encoding) {
		int retInt = -1;
		try {
			if( ObjectUtil.isEmpty(str) )
				retInt = 0;
			else {
				byte[] byArr = str.getBytes(encoding);
				retInt = byArr.length;
			}
		} catch(UnsupportedEncodingException e) {
		}
		return retInt;
	}

	/**
	 * °´ÕÕÏµÍ³×Ö·û¼¯£¬·µ»Ø×Ö·û´®µÄ³¤¶È£¬Ê¹ÓÃbyteÐÎÊ½»ñÈ¡×Ö·û´®³¤¶È±ÜÃâÖÐÎÄÇé¿öÔì³ÉµÄ×Ö·û´®³¤¶È±ä»¯ÎÊÌâ
	 * @param str ÅÐ¶Ï×Ö·û´®
	 * @return ´Ë±àÂëµÄ³¤¶È
	 */
	public static int getStrLenByBytes(String str) {
		return getStrLenByBytes(str, System.getProperty("file.encoding"));
	}

	/**
	 * ¸ù¾Ý¸ø¶¨µÄÎ»Êý£¬ÓÃ¸ø¶¨µÄ×Ö·ûÇ°²¹×ã×Ö·û´®µÄÎ»Êý£¬²»¿¼ÂÇÖÐÎÄÇé¿öÔì³ÉµÄ×Ö·û´®³¤¶È±ä»¯ÎÊÌâ
	 * @param strValue Ô´×ÖÌõ´®
	 * @param ch Òª²¹µÄ×Ö·û
	 * @param iSign ²¹×ãºóµÄ³¤¶È
	 * @return
	 */
	public static String fillString(String strValue, char ch, int iSign) {
		try {
			StringBuffer strTemp = new StringBuffer();
			int iDifference = iSign - strValue.length();
			if( iDifference <= 0 )
				return strValue;
			for(int i = 0; i < iDifference; i++)
				strTemp.append(ch);
			strTemp.append(strValue);
			return strTemp.toString();
		} catch(Exception ex) {
			return "";
		}
	}

	/**
	 * ¸ù¾Ý¸ø¶¨µÄÎ»Êý£¬ÓÃ¸ø¶¨µÄ×Ö·ûºó²¹×ã×Ö·û´®µÄÎ»Êý£¬²»¿¼ÂÇÖÐÎÄÇé¿öÔì³ÉµÄ×Ö·û´®³¤¶È±ä»¯ÎÊÌâ
	 * @param strValue Ô´×ÖÌõ´®
	 * @param ch Òª²¹µÄ×Ö·û
	 * @param iSign ²¹×ãºóµÄ³¤¶È
	 * @return
	 */
	public static String fillStringOnTail(String strValue, char ch, int iSign) {
		try {
			StringBuffer strTemp = new StringBuffer();
			strTemp.append(strValue);
			int iDifference = iSign - strValue.length();
			if( iDifference <= 0 )
				return strValue;
			for(int i = 0; i < iDifference; i++)
				strTemp.append(ch);
			return strTemp.toString();
		} catch(Exception ex) {
			return "";
		}
	}

	/**
	 * ¸ù¾Ý¸ø¶¨µÄÎ»Êý£¬ÓÃ¸ø¶¨µÄ×Ö·ûÇ°²¹×ã×Ö·û´®µÄÎ»Êý£¬Ê¹ÓÃbyteÐÎÊ½»ñÈ¡×Ö·û´®³¤¶È±ÜÃâÖÐÎÄÇé¿öÔì³ÉµÄ×Ö·û´®³¤¶È±ä»¯ÎÊÌâ
	 * @param strValue Ô´×ÖÌõ´®
	 * @param ch Òª²¹µÄ×Ö·û
	 * @param iSign ²¹×ãºóµÄ³¤¶È
	 * @return
	 */
	public static String fillStringByBytes(String strValue, char ch, int iSign) {
		try {
			StringBuffer strTemp = new StringBuffer();
			int iDifference = iSign - strValue.getBytes().length;
			if( iDifference <= 0 )
				return strValue;
			for(int i = 0; i < iDifference; i++)
				strTemp.append(ch);
			strTemp.append(strValue);
			return strTemp.toString();
		} catch(Exception ex) {
			return "";
		}
	}

	/**
	 * ¸ù¾Ý¸ø¶¨µÄÎ»Êý£¬ÓÃ¸ø¶¨µÄ×Ö·ûºó²¹×ã×Ö·û´®µÄÎ»Êý£¬Ê¹ÓÃbyteÐÎÊ½»ñÈ¡×Ö·û´®³¤¶È±ÜÃâÖÐÎÄÇé¿öÔì³ÉµÄ×Ö·û´®³¤¶È±ä»¯ÎÊÌâ
	 * @param strValue Ô´×ÖÌõ´®
	 * @param ch Òª²¹µÄ×Ö·û
	 * @param iSign ²¹×ãºóµÄ³¤¶È
	 * @return
	 */
	public static String fillStringByBytesOnTail(String strValue, char ch, int iSign) {
		try {
			StringBuffer strTemp = new StringBuffer();
			strTemp.append(strValue);
			int iDifference = iSign - strValue.getBytes().length;
			if( iDifference <= 0 )
				return strValue;
			for(int i = 0; i < iDifference; i++)
				strTemp.append(ch);
			return strTemp.toString();
		} catch(Exception ex) {
			return "";
		}
	}

	/**
	 * ±È½ÏÁ½¸ö×Ö·û´®ÊÇ·ñÏàµÈ£¨ÈÎÒâÒ»¸ö¿ÉÒÔÎªNull£©
	 * @param str1 ÅÐ¶Ï¶ÔÏóString1
	 * @param str2 ÅÐ¶Ï¶ÔÏóString2
	 * @return ÏàµÈ·µ»Øtrue£¬·´Ö®Îªfalse
	 */
	public static boolean equals(String str1, String str2) {
		return str1 != null ? str1.equals(str2) : str2 == null;
	}

	/**
	 * @Title: equalsIgnoreCase
	 * @Description: ±È½ÏÁ½¸ö×Ö·û´®ÊÇ·ñÏàµÈ£¨ºöÂÔ´óÐ¡Ð´,ÈÎÒâÒ»¸ö¿ÉÒÔÎªNull£©
	 * @param str1 str1 ÅÐ¶Ï¶ÔÏóString1
	 * @param str2 str2 ÅÐ¶Ï¶ÔÏóString2
	 * @return ÏàµÈ·µ»Øtrue£¬·´Ö®Îªfalse Jun 11, 2014 10:06:33 AM
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1 != null ? str1.equalsIgnoreCase(str2) : str2 == null;
	}

	/**
	 * ÅÐ¶Ï×Ö·û´®ÖÐÊÇ·ñ°üº¬ËùÐèÅÐ¶ÏµÄ×Ö·û
	 * @param str »ù´¡×Ö·û´®
	 * @param searchChar ÅÐ¶ÏÓÃ×Ö·û
	 * @return °üº¬Ê±·µ»Øtrue£¬·´Ö®Îªfalse
	 */
	public static boolean contains(String str, char searchChar) {
		if( ObjectUtil.isEmpty(str) )
			return false;
		else
			return str.indexOf(searchChar) >= 0;
	}

	/**
	 * ÅÐ¶Ï×Ö·û´®ÖÐÊÇ·ñ°üº¬ËùÐèÅÐ¶ÏµÄ×Ö·û´®
	 * @param str »ù´¡×Ö·û´®
	 * @param searchChar ÅÐ¶ÏÓÃ×Ö·û´®
	 * @return °üº¬Ê±·µ»Øtrue£¬·´Ö®Îªfalse
	 */
	public static boolean contains(String str, String searchStr) {
		if( ObjectUtil.isEmpty(str) || ObjectUtil.isEmpty(searchStr) )
			return false;
		else
			return str.indexOf(searchStr) >= 0;
	}

	/**
	 * ±È½ÏÁ½¸ö×Ö·û´®ÖÐµÄÖµÊÇ·ñÏàµÈ£¬Èç¹ûÏàµÈÔò·´»ØµÚÈý¸ö²ÎÊýµÄÖµ
	 * @param data
	 * @param str
	 * @return
	 */
	public static String equals(Object data, String data1, String str) {
		if( getString(data).equals(data1) )
			return str;
		return "";
	}

	/**
	 * ±È½ÏÁ½¸ö×Ö·û´®ÖÐµÄÖµÊÇ·ñÏàµÈ£¬Èç¹ûÏàµÈÔò·´»ØµÚÈý¸ö²ÎÊýµÄÖµ
	 * @param data
	 * @param str
	 * @return
	 */
	public static String equals(String data, String data1, String str) {
		if( getString(data).equals(data1) )
			return str;
		return "";
	}

	/**
	 * ASCIIÂë×ªString
	 * @param str
	 * @return
	 */
	public static String ascii2Native(String str) {
		StringBuilder sb = new StringBuilder();
		int begin = 0;
		int index = str.indexOf("%u");
		while( index != -1 ) {
			sb.append(str.substring(begin, index));
			sb.append(ascii2Char(str.substring(index, index + 6)));
			begin = index + 6;
			index = str.indexOf("%u", begin);
		}
		sb.append(str.substring(begin));
		return sb.toString();
	}

	private static char ascii2Char(String str) {
		if( str.length() != 6 )
			throw new IllegalArgumentException("Ascii string of a native character must be 6 character.");
		if( !"%u".equals(str.substring(0, 2)) )
			throw new IllegalArgumentException("Ascii string of a native character must start with \"\\u\".");
		String tmp = str.substring(2, 4);
		int code = Integer.parseInt(tmp, 16) << 8;
		tmp = str.substring(4, 6);
		code += Integer.parseInt(tmp, 16);
		return (char)code;
	}

	public static String toChinese(String sDigit) {
		String sChinese = sDigit;
		sChinese = "";
		if( sDigit.charAt(0) >= '0' && sDigit.charAt(0) <= '9' ) {
			switch(sDigit.charAt(0)) {
				case '1': {
					if( sDigit.charAt(1) == '0' ) {
						sChinese = sChinese + "Ê®";
						break;
					}
					sChinese = sChinese + "Ò»";
					break;
				}
				case '2': {
					sChinese = sChinese + "¶þ";
					break;
				}
				case '3': {
					sChinese = sChinese + "Èý";
					break;
				}
				case '4': {
					sChinese = sChinese + "ËÄ";
					break;
				}
				case '5': {
					sChinese = sChinese + "Îå";
					break;
				}
				case '6': {
					sChinese = sChinese + "Áù";
					break;
				}
				case '7': {
					sChinese = sChinese + "Æß";
					break;
				}
				case '8': {
					sChinese = sChinese + "°Ë";
					break;
				}
				case '9': {
					sChinese = sChinese + "¾Å";
					break;
				}
				case '0': {
					sChinese = sChinese + "0";
					break;
				}
			}
		}
		return sChinese;
	}

	/**
	 * @Title: isEmpty
	 * @Description: Èç¹ûstrÎª¿ÕÔò·µ»Ødef
	 * @param str
	 * @param def
	 * @return Aug 30, 2014 2:35:55 PM
	 */
	public static String isEmpty(String str, String def) {
		return ObjectUtil.isEmpty(str) ? def : str;
	}

	/**
	 * ÅÐ¶ÏStringÊÇ·ñÎªÊý×Ö
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() )
			return false;
		return true;
	}

	/**
	 * ÅÐ¶ÏÒ»¸ö×Ö·û´®ÊÇ²»ÊÇÊý×Ö(°üÀ¨¸ºÊý»ò1-20)
	 * @param str
	 * @return
	 */
	protected static boolean isNumeric2(String str) {
		char[] charArr = str.toCharArray();
		int index = str.indexOf("-"); // ¸ººÅµÄË÷Òý
		for(int i = 0; i < charArr.length; i++)
			if( i == index )
				continue;
			else {
				if( charArr[i] >= '0' && charArr[i] <= '9' )
					continue;
				else
					return false;
			}
		return true;
	}

	/**
	 * @Title: isMobileNO
	 * @Description: ÑéÖ¤ÊÖ»ú¸ñÊ½
	 * @param mobiles
	 * @return Sep 25, 2014 2:50:33 PM
	 */
	public static boolean isMobileNO(String mobiles) {
		/*
		 * ÒÆ¶¯£º134¡¢135¡¢136¡¢137¡¢138¡¢139¡¢150¡¢151¡¢157(TD)¡¢158¡¢159¡¢187¡¢188 ÁªÍ¨£º130¡¢131¡¢132¡¢152¡¢155¡¢156¡¢185¡¢186 µçÐÅ£º133¡¢153¡¢180¡¢189¡¢£¨1349ÎÀÍ¨£©
		 * ×Ü½áÆðÀ´¾ÍÊÇµÚÒ»Î»±Ø¶¨Îª1£¬µÚ¶þÎ»±Ø¶¨Îª3»ò5»ò8£¬ÆäËûÎ»ÖÃµÄ¿ÉÒÔÎª0-9
		 */
		if( ObjectUtil.isEmpty(mobiles) || mobiles.length() < 11 )
			return false;
		String regex = "[1]\\d{10}";// "[1]"´ú±íµÚ1Î»ÎªÊý×Ö1£¬"[358]"´ú±íµÚ¶þÎ»¿ÉÒÔÎª3¡¢5¡¢8ÖÐµÄÒ»¸ö£¬"\\d{9}"´ú±íºóÃæÊÇ¿ÉÒÔÊÇ0¡«9µÄÊý×Ö£¬ÓÐ9Î»¡£
		return mobiles.matches(regex);
	}

	/**
	 * @Title: isEmail
	 * @Description: ÑéÖ¤ÓÊÏä¸ñÊ½
	 * @param email
	 * @return Sep 25, 2014 2:50:39 PM
	 */
	public static boolean isEmail(String email) {
		if( ObjectUtil.isEmpty(email) || email.length() < 7 )
			return false;
		String regex = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
		// String regex =
		// "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return email.matches(regex);
	}

	/**
	 * @Title: strSpecial
	 * @Description: ÌØÊâ×Ö·û
	 * @param str
	 * @return Sep 4, 2014 6:13:22 PM
	 */
	public static String strSpecial(String str) {
		String regEx = "[/\\:*?<>|\"\n\t]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("");
	}

	/**
	 * @Title: strSpecial
	 * @Description: È¥³ýÌØÊâ×Ö·û²»°üÀ¨»Ø³µºÍTAB
	 * @param str
	 * @return Sep 4, 2014 6:13:22 PM
	 */
	public static String reSpecial(String str) {
		String regEx = "[/\\:*?<>|\"]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("");
	}

	public static String telPhone(String phone) {
		if( ObjectUtil.isEmpty(phone) )
			return phone;
		if( phone.trim().startsWith("+86") )
			return phone;
		return "+86" + phone;
	}

	/**
	 * @Title replacePhone
	 * @Description Ìæ»»µç»°
	 * @param phone
	 * @return
	 */
	public static String replacePhone(String phone) {
		if( !isMobileNO(phone) )
			return "";
		return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
	}

	public static void main(String[] args) {
		String phone = "12345678901";
		System.out.println(phone);
		System.out.println(replacePhone(phone));
	}

//	/**
//	 * »ñµÃµØÖ·
//	 */
//	public static String getAddress(Context context, Task_list task) {
//		return context.getString(R.string.shanghaishi) + CodesItem.getValue(CodesItem.HPB_CODE, task.getHpb_code())
//				+ task.getTask_road() + task.getTask_unit() + task.getTask_unit_no();
//	}
}
