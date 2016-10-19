package com.wmsterminal.model;

import com.wmsterminal.base.Config;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * <Auto Created by fd.CreateTable> ²»µÃÉÃ×ÔÐÞ¸Ä£¡
 */
/**
 * @Package: com.mngwyhouhzmb.data.entity
 * @ClassName: HandleImage
 * @Description: ´¦ÀíÍ¼Æ¬
 * @author: LiuSiQing
 * @date: 2015-4-22 ÉÏÎç9:30:21
 */
public class ImageZoom implements Parcelable {
	
	private String	file_path;					// µØÖ·£¬ÎªÈ«Â·¾¶
	private String	file_name;					// ÎÄ¼þÃû
	private int		status;						// ´¦Àí×´Ì¬
	private String	file_size;					// ÎÄ¼þ´óÐ¡
	private int		flag	= Config.FLAG_FILE;	// ±êÊ¾£¬´ÓÄÄ»ñÈ¡×ÊÔ´,Ä¬ÈÏÎª3£¬´Ó±¾µØ¼ÓÔØ
	private int		count;						// ¼ÆÊý
	
	public String getFile_path() {
		
		return file_path;
	}
	
	public void setFile_path(String file_path) {
		
		this.file_path = file_path;
	}
	
	public String getFile_name() {
		
		return file_name;
	}
	
	public void setFile_name(String file_name) {
		
		this.file_name = file_name;
	}
	
	public int getStatus() {
		
		return status;
	}
	
	public void setStatus(int status) {
		
		this.status = status;
	}
	
	public String getFile_size() {
		
		return file_size;
	}
	
	public void setFile_size(String file_size) {
		
		this.file_size = file_size;
	}
	
	public int getFlag() {
		
		return flag;
	}
	
	public void setFlag(int flag) {
		
		this.flag = flag;
	}
	
	public int getCount() {
		
		return count;
	}
	
	public void setCount(int count) {
		
		this.count = count;
	}
	
	@Override
	public int describeContents() {
		
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeString(file_path);
		dest.writeString(file_name);
		dest.writeInt(status);
		dest.writeString(file_size);
		dest.writeInt(flag);
		dest.writeInt(count);
	}
	
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Creator() {
		
		@Override
		public ImageZoom createFromParcel(Parcel source) {
			
			ImageZoom dto = new ImageZoom();
			dto.setFile_path(source.readString());
			dto.setFile_name(source.readString());
			dto.setStatus(source.readInt());
			dto.setFile_size(source.readString());
			dto.setFlag(source.readInt());
			dto.setCount(source.readInt());
			return dto;
		}
		
		@Override
		public ImageZoom[] newArray(int size) {
			
			return new ImageZoom[size];
		}
	};
}
