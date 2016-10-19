package com.wmsterminal.common.adapter;

import android.widget.BaseAdapter;



import com.wmsterminal.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>±ê Ìâ: Adapter</p>
 * <p>Ãè Êö: ÊÊÅäÆ÷»ù´¡Àà</p>
 * <p>°æ È¨: Copyright (c) Administrator</p>
 * <p>¹« Ë¾: ÉÏº£Ê«ÓíÐÅÏ¢¿Æ¼¼ÓÐÏÞ¹«Ë¾</p>
 * <p>´´½¨Ê±¼ä: Aug 23, 2014 4:45:51 PM</p>
 * @author Administrator
 * @version
 */
public abstract class Adapter<T> extends BaseAdapter {

	/**
	 * Êý¾Ý
	 */
	private List<T> mList = new ArrayList<>();

	/**
	 * µÃµ½adapterµÄÊýÁ¿
	 */
	@Override
	public int getCount() {
		return ObjectUtil.getSize(mList);
	}

	/**
	 * µÃµ½¶ÔÓ¦positionµÄ¶ÔÏó
	 */
	@Override
	public T getItem(int position) {
		return (T) ObjectUtil.getObject(mList, position);
	}

	/**
	 * µÃµ½adapterÊÓÍ¼µÄid
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * @Title: add
	 * @Description: Ìí¼Ó¶ÔÏó½øÈëadapter
	 * @param obj
	 * Aug 23, 2014 4:47:18 PM
	 */
	public void add(T obj) {
		mList.add(obj);
	}

	/**
	 * @Title: clear 
	 * @Description: adapterÇå¿Õ
	 * Aug 23, 2014 4:47:38 PM
	 */
	public void clear() {
		mList.clear();
	}

	/**
	 * @Title: add
	 * @Description: Ö¸¶¨Î»ÖÃÐÂÔö
	 * @author: LiuSiQing
	 * @date: 2015-3-18 ÏÂÎç5:36:56
	 */
	public void add(int index, T obj) {
		mList.add(index, obj);
	}

	/**
	 * @Title: remove
	 * @Description: remove
	 * @author: LiuSiQing
	 * @date: 2015-3-31 ÉÏÎç11:54:23
	 */
	public T remove(int location) {
		return mList.remove(location);
	}

	/**
	 * @Title: remove
	 * @Description: ÒÆ³ý
	 * @author: LiuSiQing
	 * @date: 2015-5-19 ÏÂÎç2:43:22
	 */
	public boolean remove(Object object) {
		return mList.remove(object);
	}

	/**
	 * @Title: refresh 
	 * @Description: adapterÖØÖÃ
	 * @param list
	 * Aug 23, 2014 4:47:48 PM
	 */
	@SuppressWarnings("unchecked")
	public void refresh(List<?> list) {
		if( null != list )
			mList = (List<T>)list;
	}

	/**
	 * @Title: refresh
	 * @Description: adapterÖØÖÃ
	 * @author: LiuSiQing
	 * @date: 2015-3-30 ÉÏÎç10:07:31
	 */
	public void refresh(T[] objs) {
		mList.clear();
		for(T obj : objs)
			mList.add(obj);
	}

	/**
	 * @Title: getSize
	 * @Description: »ñµÃListµÄ´óÐ¡
	 * @author: LiuSiQing
	 * @date: 2015-3-12 ÏÂÎç3:59:19
	 */
	public int getSize() {
		return ObjectUtil.getSize(mList);
	}

	/**
	 * @Title: getList
	 * @Description: »ñµÃListÊý¾Ý
	 * @author: LiuSiQing
	 * @date: 2015-4-2 ÉÏÎç10:04:26
	 */
	public List<T> getList() {
		return mList;
	}
}
