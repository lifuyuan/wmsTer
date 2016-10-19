package com.wmsterminal.model;

import java.util.List;

public class WmsInputResponse {
	
	private String status;

	private List<Reasons> reasons ;

	public void setStatus(String status){
	this.status = status;
	}
	public String getStatus(){
	return this.status;
	}
	public void setReasons(List<Reasons> reasons){
	this.reasons = reasons;
	}
	public List<Reasons> getReasons(){
	return this.reasons;
	}
	
	public class Reasons {
		private String reason;

		public void setReason(String reason){
		this.reason = reason;
		}
		public String getReason(){
		return this.reason;
		}
	}	

}
