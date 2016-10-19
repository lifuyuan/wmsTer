package com.wmsterminal.common.thread;

import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package com.mngwyhouhzmb.common.thread
 * @Title TaskExecutor
 * @Description Ïß³Ì³Ø
 * @author LiuSiQing
 * @Time 2014Äê11ÔÂ26ÈÕÉÏÎç10:14:13
 */
public class TaskExecutor {

	private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

	/**
	 * @Title 2014Äê11ÔÂ26ÈÕ
	 * @Description Ë½ÓÐ¹¹Ôìº¯Êý
	 * @author LiuSiQing
	 * @Time 2014Äê11ÔÂ26ÈÕÉÏÎç10:14:26
	 */
	private TaskExecutor() {
	}
	
	/**
	 * @Title: getExecutorService
	 * @Description: »ñµÃÏß³Ì³Ø
	 * @author: LiuSiQing
	 * @date: 2015-6-25 ÏÂÎç4:27:21
	 */
	public static ExecutorService getExecutorService(){
		return executorService;
	}

	/**
	 * @Title Execute 
	 * @Description Ö´ÐÐÏÖ³¡
	 * @param task
	 */
	public static void Execute(Runnable task) {
		// if( null == executorService || executorService.isShutdown() || executorService.isTerminated() )
		// executorService = Executors.newFixedThreadPool(5);
		executorService.submit(task);
	}

	/**
	 * @Title IsTerminated 
	 * @Description Ïß³ÌÊÇ·ñÖ´ÐÐÍê³É
	 * @return
	 */
	public static boolean IsTerminated() {
		executorService.shutdown();
		while( true )
			if( executorService.isTerminated() )
				break;
		return true;
	}

	/**
	 * @Title shutdownNow 
	 * @Description Á¢¼´¹Ø±ÕÏß³Ì³Ø
	 * @return
	 */
	@Deprecated
	public static List<Runnable> shutdownNow() {
		List<Runnable> list = executorService.shutdownNow();
		// executorService = null;
		return list;
	}
}
