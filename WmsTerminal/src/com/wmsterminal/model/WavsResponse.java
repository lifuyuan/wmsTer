package com.wmsterminal.model;

import java.io.Serializable;
import java.util.List;

public class WavsResponse {
	
	private List<OutboundWaves> outboundWaves ;

	public void setOutboundWaves(List<OutboundWaves> outboundWaves){
	this.outboundWaves = outboundWaves;
	}
	public List<OutboundWaves> getOutboundWaves(){
	return this.outboundWaves;
	}
	
	public static class OutboundWaves implements Serializable {
		private String outboundNum;

		private String waveNum;

		private List<Referred_skus> referred_skus ;

		public void setOutboundNum(String outboundNum){
		this.outboundNum = outboundNum;
		}
		public String getOutboundNum(){
		return this.outboundNum;
		}
		public void setWaveNum(String waveNum){
		this.waveNum = waveNum;
		}
		public String getWaveNum(){
		return this.waveNum;
		}
		public void setReferred_skus(List<Referred_skus> referred_skus){
		this.referred_skus = referred_skus;
		}
		public List<Referred_skus> getReferred_skus(){
		return this.referred_skus;
		}
		
		public static  class Referred_skus implements Serializable {
			private String goodsName;

			private String barcode;

			private List<ShelfInfo> shelfInfo ;

			private int quantity;

			public void setGoodsName(String goodsName){
			this.goodsName = goodsName;
			}
			public String getGoodsName(){
			return this.goodsName;
			}
			public void setBarcode(String barcode){
			this.barcode = barcode;
			}
			public String getBarcode(){
			return this.barcode;
			}
			public void setShelfInfo(List<ShelfInfo> shelfInfo){
			this.shelfInfo = shelfInfo;
			}
			public List<ShelfInfo> getShelfInfo(){
			return this.shelfInfo;
			}
			public void setQuantity(int quantity){
			this.quantity = quantity;
			}
			public int getQuantity(){
			return this.quantity;
			}
			
			public static class ShelfInfo implements Serializable {
				
				 private String shelfNum;
				 private int  shelfQuantity;
				public String getShelfNum() {
					return shelfNum;
				}
				public void setShelfNum(String shelfNum) {
					this.shelfNum = shelfNum;
				}
				public int getShelfQuantity() {
					return shelfQuantity;
				}
				public void setShelfQuantity(int shelfQuantity) {
					this.shelfQuantity = shelfQuantity;
				}
				 
			}
			}

		}


}
