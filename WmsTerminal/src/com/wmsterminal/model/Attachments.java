package com.wmsterminal.model;

import com.wmsterminal.base.Config;



import android.os.Parcel;
import android.os.Parcelable;

/*
 * <Auto Created by fd.CreateTable> ²»µÃÉÃ×ÔÐÞ¸Ä£¡
 */
/**
 * ÉÏ´«¸½¼þÐÅÏ¢±í
 */
public class Attachments implements Parcelable {
	
	private String	userid;							// ÓÃ»§id
	private String	attch_id;						// ¸½¼þÐòºÅ
	private String	table_name;						// ¸½¼þ¶ÔÓÚµÄ±íÃû
	private String	refno_id;						// ¸½¼þ¹ØÁªµÄÖ÷¼üID
	private String	comm_date;						// Ìá½»ÈÕÆÚ
	private String	orgn_filename;					// Ô­Ê¼ÎÄ¼þÃû
	private String	file_path;						// ÎÄ¼þ´æ·ÅÂ·¾¶
	private String	filename;						// ×Ô¶¨ÒåÎÄ¼þÃû
	private String	file_size;						// ÎÄ¼þ´óÐ¡
	private String	isCheck;						// ÊÇ·ñÑ¡ÖÐ
	private int		flag	= Config.FLAG_SERVER;	// ±êÊ¾£¬´ÓÄÄ»ñÈ¡×ÊÔ´,Ä¬ÈÏÎª0
	
	public String getUserid() {
		
		return userid;
	}
	
	public void setUserid(String userid) {
		
		this.userid = userid;
	}
	
	/** È¡µÃ£º¸½¼þÐòºÅ */
	public String getAttch_id() {
		
		return attch_id;
	}
	
	/** ÉèÖÃ£º¸½¼þÐòºÅ */
	public void setAttch_id(String attch_id) {
		
		this.attch_id = attch_id;
	}
	
	/** È¡µÃ£º¸½¼þ¶ÔÓÚµÄ±íÃû */
	public String getTable_name() {
		
		return table_name;
	}
	
	/** ÉèÖÃ£º¸½¼þ¶ÔÓÚµÄ±íÃû */
	public void setTable_name(String table_name) {
		
		this.table_name = table_name;
	}
	
	/** È¡µÃ£º¸½¼þ¹ØÁªµÄÖ÷¼üID */
	public String getRefno_id() {
		
		return refno_id;
	}
	
	/** ÉèÖÃ£º¸½¼þ¹ØÁªµÄÖ÷¼üID */
	public void setRefno_id(String refno_id) {
		
		this.refno_id = refno_id;
	}
	
	/** È¡µÃ£ºÌá½»ÈÕÆÚ */
	public String getComm_date() {
		
		return comm_date;
	}
	
	/** ÉèÖÃ£ºÌá½»ÈÕÆÚ */
	public void setComm_date(String comm_date) {
		
		this.comm_date = comm_date;
	}
	
	/** È¡µÃ£ºÔ­Ê¼ÎÄ¼þÃû */
	public String getOrgn_filename() {
		
		return orgn_filename;
	}
	
	/** ÉèÖÃ£ºÔ­Ê¼ÎÄ¼þÃû */
	public void setOrgn_filename(String orgn_filename) {
		
		this.orgn_filename = orgn_filename;
	}
	
	/** È¡µÃ£ºÎÄ¼þ´æ·ÅÂ·¾¶ */
	public String getFile_path() {
		
		return file_path;
	}
	
	/** ÉèÖÃ£ºÎÄ¼þ´æ·ÅÂ·¾¶ */
	public void setFile_path(String file_path) {
		
		this.file_path = file_path;
	}
	
	/** È¡µÃ£º×Ô¶¨ÒåÎÄ¼þÃû */
	public String getFilename() {
		
		return filename;
	}
	
	/** ÉèÖÃ£º×Ô¶¨ÒåÎÄ¼þÃû */
	public void setFilename(String filename) {
		
		this.filename = filename;
	}
	
	/** È¡µÃ£ºÎÄ¼þ´óÐ¡ */
	public String getFile_size() {
		
		return file_size;
	}
	
	/** ÉèÖÃ£ºÎÄ¼þ´óÐ¡ */
	public void setFile_size(String file_size) {
		
		this.file_size = file_size;
	}
	
	public String getIsCheck() {
		
		return isCheck;
	}
	
	public void setIsCheck(String isCheck) {
		
		this.isCheck = isCheck;
	}
	
	public int getFlag() {
		
		return flag;
	}
	
	public void setFlag(int flag) {
		
		this.flag = flag;
	}
	
	@Override
	public int describeContents() {
		
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeString(attch_id);
		dest.writeString(table_name);
		dest.writeString(refno_id);
		dest.writeString(comm_date);
		dest.writeString(orgn_filename);
		dest.writeString(file_path);
		dest.writeString(filename);
		dest.writeString(file_size);
		dest.writeString(isCheck);
		dest.writeInt(flag);
	}
	
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Creator() {
		
		@Override
		public Attachments createFromParcel(Parcel source) {
			
			Attachments att = new Attachments();
			att.setAttch_id(source.readString());
			att.setTable_name(source.readString());
			att.setRefno_id(source.readString());
			att.setComm_date(source.readString());
			att.setOrgn_filename(source.readString());
			att.setFile_path(source.readString());
			att.setFilename(source.readString());
			att.setFile_size(source.readString());
			att.setIsCheck(source.readString());
			att.setFlag(source.readInt());
			return att;
		}
		
		@Override
		public Attachments[] newArray(int size) {
			
			return new Attachments[size];
		}
	};
}
