package com.wmsterminal.model;

import java.io.Serializable;
import java.util.List;

public class ReleasePallet {
	
	 private String status;
	    private List<Reasons> reasons;
	    public void setStatus(String status) {
	         this.status = status;
	     }
	     public String getStatus() {
	         return status;
	     }

	    public void setReasons(List<Reasons> reasons) {
	         this.reasons = reasons;
	     }
	     public List<Reasons> getReasons() {
	         return reasons;
	     }
	     
	 public static class Reasons implements Serializable{
	    
		 private String reason;
	     private String palletnum;
	     public void setReason(String reason) {
	          this.reason = reason;
	      }
	      public String getReason() {
	          return reason;
	      }

	     public void setPalletnum(String palletnum) {
	          this.palletnum = palletnum;
	      }
	      public String getPalletnum() {
	          return palletnum;
	      }

	     }
}
