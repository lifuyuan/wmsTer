package com.wmsterminal.model;

public class RequestModel {

	private RequHeader head;
	private Object message;
	
	public RequHeader getHead() {
		return head;
	}
	public void setHead(RequHeader head) {
		this.head = head;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
}
