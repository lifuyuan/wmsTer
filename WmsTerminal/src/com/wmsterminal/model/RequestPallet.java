package com.wmsterminal.model;

import java.io.Serializable;
import java.util.List;

public class RequestPallet {
	
	private String palletNum;
    private List<containedParcels> containedParcels;
    public void setPalletnum(String palletnum) {
         this.palletNum = palletnum;
     }
     public String getPalletnum() {
         return palletNum;
     }
     
  
     
     public List<containedParcels> getContainedParcels() {
		return containedParcels;
	}
	public void setContainedParcels(List<containedParcels> containedParcels) {
		this.containedParcels = containedParcels;
	}



	public static class containedParcels implements Serializable{
    	 
    	 private String method;
    	 private String parcelNum;
    	  
    	 public void setMethod(String method) {
 	         this.method = method;
 	     }
 	     public String getMethod() {
 	         return method;
 	     }

 	     public void setParcelnum(String parcelnum) {
 	         this.parcelNum = parcelnum;
 	     }
 	     public String getParcelnum() {
 	         return parcelNum;
 	     }
    	   

     }

}
