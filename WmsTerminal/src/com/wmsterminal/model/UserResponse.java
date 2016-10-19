package com.wmsterminal.model;

import java.io.Serializable;

public class UserResponse implements Serializable {
	
	   private String status;
	   private String reason;
	   private String email;
	   private String nickname;
	   private String usertype;
	   private String datamanagementright;
	   private String lastsigninat;
	   private String token;
	   
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getDatamanagementright() {
		return datamanagementright;
	}
	public void setDatamanagementright(String datamanagementright) {
		this.datamanagementright = datamanagementright;
	}
	public String getLastsigninat() {
		return lastsigninat;
	}
	public void setLastsigninat(String lastsigninat) {
		this.lastsigninat = lastsigninat;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
