package com.wmsterminal.model;

import java.io.Serializable;
import java.util.List;

public class ReturnParcel {
	
    private String status;
    private List<Reason> reasons;
    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setReasons(List<Reason> reasons) {
         this.reasons = reasons;
     }
     public List<Reason> getReasons() {
         return reasons;
     }
     
     public static class Reason implements Serializable{
    	 
    	 private String reason;
    	 private String referredParcelNum;
    	 
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getReferredParcelNum() {
			return referredParcelNum;
		}
		public void setReferredParcelNum(String referredParcelNum) {
			this.referredParcelNum = referredParcelNum;
		}
    	 
    	 
     }

}
