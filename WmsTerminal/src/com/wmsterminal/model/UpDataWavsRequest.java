package com.wmsterminal.model;

import java.util.List;

public class UpDataWavsRequest {
	
	private String status;

	private List<OosInfo> oosInfo ;

	public void setStatus(String status){
	this.status = status;
	}
	public String getStatus(){
	return this.status;
	}
	public void setOosInfo(List<OosInfo> oosInfo){
	this.oosInfo = oosInfo;
	}
	public List<OosInfo> getOosInfo(){
	return this.oosInfo;
	}
	
	public static class OosInfo {
		private String barcode;

		private int oosQuantity;

		public void setBarcode(String barcode){
		this.barcode = barcode;
		}
		public String getBarcode(){
		return this.barcode;
		}
		public void setOosQuantity(int oosQuantity){
		this.oosQuantity = oosQuantity;
		}
		public int getOosQuantity(){
		return this.oosQuantity;
		}

		}

}
