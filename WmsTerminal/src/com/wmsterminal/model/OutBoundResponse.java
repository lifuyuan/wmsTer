package com.wmsterminal.model;

import java.io.Serializable;
import java.util.List;

public class OutBoundResponse {
	
	  	private List<Outboundorders> outboundorders;
	    public void setOutboundorders(List<Outboundorders> outboundorders) {
	         this.outboundorders = outboundorders;
	     }
	     public List<Outboundorders> getOutboundorders() {
	         return outboundorders;
	     }
	     
	     public static  class Outboundorders implements Serializable{

	    	    private String outboundnum;
	    	    private String orderNum;
	    	    private List<ReferredSkus> referred_skus;
	    	    public List<ReferredSkus> getReferred_skus() {
					return referred_skus;
				}
				public void setReferred_skus(List<ReferredSkus> referred_skus) {
					this.referred_skus = referred_skus;
				}
				public void setOutboundnum(String outboundnum) {
	    	         this.outboundnum = outboundnum;
	    	     }
	    	     public String getOutboundnum() {
	    	         return outboundnum;
	    	     }

	    	    public void setOrderNum(String orderNum) {
	    	         this.orderNum = orderNum;
	    	     }
	    	     public String getOrdernum() {
	    	         return orderNum;
	    	     }
	    	     
	    	     public static class ReferredSkus implements Serializable {

		    	        private String barcode;
		    	        private String goodsName;
		    	        private String shelfNum;
		    	        private int quantity;
		    	        public void setBarcode(String barcode) {
		    	             this.barcode = barcode;
		    	         }
		    	         public String getBarcode() {
		    	             return barcode;
		    	         }

		    	        public void setGoodsName(String goodsName) {
		    	             this.goodsName = goodsName;
		    	         }
		    	         public String getGoodsName() {
		    	             return goodsName;
		    	         }

		    	        public void setShelfNum(String shelfNum) {
		    	             this.shelfNum = shelfNum;
		    	         }
		    	         public String getShelfNum() {
		    	             return shelfNum;
		    	         }

		    	        public void setQuantity(int quantity) {
		    	             this.quantity = quantity;
		    	         }
		    	         public int getQuantity() {
		    	             return quantity;
		    	         }

		    	    }

	    	   
	     }
	    	    

}
