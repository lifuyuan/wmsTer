package com.wmsterminal.base;

public class Config {
    /**
     * µ÷ÊÔ¿ª¹Ø
     */
    public static final boolean DEBUG = true;
    /**
     * ´íÎóÐÅÏ¢Ç°×º
     */
    public static final String ERROR = "BE:";

    /**
     * TODO ÊÇ·ñÐèÒª²¶»ñÒì³£
     */
    public static final boolean IS_NEED_CAUGHT_EXEPTION = false;

    public static final int DEFAULT_ERROR = -1;
    /**
     * Ã»ÓÐ¿Ø¼þID
     */
    public static final int NOT_CONTROL_ID = -1;


    /**
     * ²âÊÔ»ú
     */
    public static final String BASE_URL = "http://106.185.35.33:9001/";
    /**
     * ²âÊÔ»úÎÄ¼þµØÖ·Â·¾¶
     */
    public static final String BASE_FILE_URL = "http://114.80.86.42:8080/download/";
    /**
     * Éú²ú»ú
     */
//    public static final String BASE_URL = "http://operation.athub.com/AthubCloud/App/";
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * ÉÌÆ·ID´úºÅ
     */
    public static final String PRO_ID = "PRO_ID";
    /**
     * ×îºóÒ»´Îµã»÷Ê±¼ä
     */
    public volatile static long LAST_CLICK_TIME = 0;
    /**
     * HTTPÍ·²¿
     */
    public static final String HTTP = "http://";
    /**
     * HTTPSÍ·²¿
     */
    public static final String HTTPS = "https://";
    /**
     * SD¿¨Í·²¿
     */
    public static final String FILE = "file://";
    /**
     * CONTENTÍ·²¿
     */
    public static final String CONTENT = "content://";
    /**
     * assets(³ÌÐòÄÚ²¿ÎÄ¼þ)Í·²¿
     */
    public static final String ASSETS = "assets://";
    /**
     * drawable(³ÌÐòÄÚ²¿×ÊÔ´ÎÄ¼þ)Í·²¿
     */
    public static final String DRAWABLE = "drawable://";
    /**
     * ´ÓÍøÂç¼ÓÔØ±êÊ¾
     */
    public static final int FLAG_SERVER = 0;
    public static final int FLAG_HTTP = 1;
    public static final int FLAG_HTTPS = 2;
    /**
     * ´Ó±¾µØ¼ÓÔØ±êÊ¾
     */
    public static final int FLAG_FILE = 3;
    /**
     * ´Ócontent¼ÓÔØ
     */
    public static final int FLAG_CONTENT = 4;
    /**
     * ´Ó³ÌÐò¼ÓÔØ±êÊ¾
     */
    public static final int FLAG_ASSETS = 5;
    /**
     * ´ÓdrawableÖÐ¼ÓÔØ±êÊ¾
     */
    public static final int FLAG_DRAWABLE = 6;


    /*******************************
     * ÏìÓ¦Öµ
     **************************************/
    public static final int RELEVANT_PHOTO = 0X1001;
    public static final int RELEVANT_CHOOSE = 0X1002;
    public static final int RELEVANT_ONE = 0X0001;
    public static final int RELEVANT_TWO = 0X0002;
    public static final int RELEVANT_THREE = 0X0003;
    public static final int RELEVANT_FOUR = 0X0004;
    public static final int RELEVANT_FIVE = 0X0005;
    public static final int RELEVANT_DELETE = 0X1003;
    public static final int RELEVANT_SCAN = 0X9002;
    public static final int RELEVANT_START_SCAN = 0X9001;

    /*********************************** ÍøÂçÁ¬½Ó²ÎÊý****************************/

    /**
     * ³¬Ê±
     */
    public static final int DEFAULT_SOCKET_TIMEOUT = 20 * 1000;
    /**
     * Ä¬ÈÏÁ¬½ÓÊý
     */
    public static final int DEFAULT_HOST_CONNECTIONS = 10;
    /**
     * ×î´óÁ¬½ÓÊý
     */
    public static final int DEFAULT_MAX_CONNECTIONS = 30;
    /**
     * ÉèÖÃÌ×½Ó×ÖµÄ»º³åÇø
     */
    public static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;

    /*********************
     * ÏìÓ¦ÖµÉèÖÃ
     ************************************/
    public static final String USER_INFO = "USER_INFO";
    public static final String RESPONSE_FLAG_SUCCESS = "1";
    public static final String RESPONSE_FLAG_FAIL = "0";

    /*********************ÐÅÏ¢ÌáÊ¾**************************************/
    /**
     * ³É¹¦
     */
    public static final String SUCCESS = "1";
    public static final int DEFAULT = 0;
    /**
     * ³É¹¦-Î¬ÐÞ×Ê½ð
     */
    public static final String SUCCESS_WXZJ = "SUCCESS";
    /**
     * ³É¹¦
     */
    public static final int SUCCESS_INT = 1;
    /**
     * Ê§°Ü
     */
    public static final String FAIL = "0";
    /**
     * Ê§°Ü
     */
    public static final int FAIL_INT = 0;
    /**
     * ¼ÓÔØÖÐ
     */
    public static final int LOADING_INT = 1;
    /**
     * ³É¹¦±êÊ¶
     */
    public static final String FLAG = "flag";
    /**
     * ÐÅÏ¢±êÊ¶
     */
    public static final String MESSAGE = "message";
    /**
     * ÏµÍ³Òì³£
     */
    public static final String ERROR_DEFAULT = "ÏµÍ³Òì³£";
    /**
     * ÏÂÔØ´íÎó
     */
    public static final String ERROR_DOWN = "ÏÂÔØÊ§°Ü£¬ÇëÉÔºóÖØÊÔ£¡";
    /**
     * ÍøÂç²»¿ÉÓÃ£¬ÇëÊ¹ÓÃWiFi/3G/4GÍøÂç
     */
    public static final String ERROR_NETWORK_OFF = "ÍøÂç²»¿ÉÓÃ£¬ÇëÊ¹ÓÃWiFi/3G/4GÍøÂç";
    /**
     * ÏµÍ³ÕýÔÚ½¨ÉèÖÐ
     */
    public static final String SYSTEM_IN_CONSTRUCTION = "ÏµÍ³ÕýÔÚ½¨ÉèÖÐ......";
    /**
     * ÖÐÎÄÌáÊ¾ÐÅÏ¢
     */
    public static final String NETWORK_ERROR = "ÄúµÄÍøÂç²»ºÃ,ÇëÉÔºóÔÙÊÔ";

    /**
     * ·ÖÒ³
     */
    public static final int PAGE_SIZE = 10;
    public static final String PAGE_SIZE_STRING = "10";
    public static final int PAGE_MAX_REFRESH = 100;

    ///////////////////////////////////////////////////////////////////////////////////////////
    /**
     * WHAT
     */
    public static final int WHAT_ONE = 1;
    public static final int WHAT_TWO = 2;
    public static final int WAHT_THREE = 3;
    public static final int WAHT_FOUR = 4;
    /**
     * ÏîÄ¿×´Ì¬
     */
    public static final String PRO_STATUS_YUREZHONG = "03";
    public static final String PRO_STATUS_CHOUJIZHONG = "04";
    public static final String PRO_STATUS_SUCCESS = "06";
    public static final String PRO_STATUS_FAILED = "07";
    /**
     * ½ð¶îµÄ×Ö·û
     */
    public static final String TXT_MONEY_FLAG = "£¤";
    /**
     * °Ù·ÖºÅ×Ö·û
     */
    public static final String TXT_PERCENT_FLAG = "%";
    /**
     * ÇëÇóÂë key
     */
    public static final String REQUEST_CODE_KEY = "request_code";
    /**
     * ÇëÇó±êÊ¶
     */
    public static final String REQUEST_FLAG = "requ_message";
    /**
     * SESSION±êÊ¶
     */
    public static final String SESSION = "SESSION";
    /**
     * ·¢ÏÖÒ³ÃæÇëÇóÂë
     */
    public static final String REQUEST_CODE_ACTIVITY_FIND = "A001";
    /**
     * ÁÐ±íÒ³ÇëÇóÂð
     */
    public static final String REQUEST_CODE_ACTIVITY_FIND_LIST_MOST = "A002";//×îÊÜ»¶Ó­
    public static final String REQUEST_CODE_ACTIVITY_FIND_LIST_NEW = "A003";//×îÐÂ·¢Æð
    /**
     * ÏîÄ¿ÏêÇéÒ³
     */
    public static final String REQUEST_CODE_ACTIVITY_PROJECT_DYNAMIC = "B003";//¶¯Ì¬
    public static final String REQUEST_CODE_ACTIVITY_PROJECT_REPORT = "B004";//»Ø±¨
    /**
     * ÆÀÂÛÒ³
     */
    public static final String REQUEST_CODE_ACTIVITY_COMMENT_QUERY = "C001";//²éÑ¯ÏîÄ¿ËùÓÐÆÀÂÛ
    public static final String REQUEST_CODE_ACTIVITY_COMMENT_COMMNET = "C002";//ÆÀ¼Û
    public static final String REQUEST_CODE_ACTIVITY_COMMENT_REPLY = "C003";//Ò»¼¶»Ø¸´
    public static final String REQUEST_CODE_ACTIVITY_COMMENT_REPLY_REPLY = "C004";//¶þ¼¶»Ø¸´

    public static final String REQUEST_CODE_ACTIVITY_PROJECT_ALL = "D001";//²éÑ¯ÏîÄ¿ËùÓÐÆÀÂÛ
    public static final String REQUEST_CODE_ACTIVITY_PROJECT_FAQI = "D002";//ÆÀ¼Û
    public static final String REQUEST_CODE_ACTIVITY_PROJECT_GENTOU = "D003";//Ò»¼¶»Ø¸´
    public static final String REQUEST_CODE_ACTIVITY_PROJECT_GUANZHU = "D004";//¶þ¼¶»Ø¸´

    /** ÉÌÆ··ÖÀà µÈ¼¶ */

}
