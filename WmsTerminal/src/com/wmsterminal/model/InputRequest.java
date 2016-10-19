package com.wmsterminal.model;

import java.util.Date;

public class InputRequest {
	
    private String shelfNum;
    private String barcode;
    private int quantity;
    private String deadlineOfShelfLife;
    private String problem;
    private String operation;
    public void setShelfNum(String shelfNum) {
         this.shelfNum = shelfNum;
     }
     public String getShelfNum() {
         return shelfNum;
     }

    public void setBarcode(String barcode) {
         this.barcode = barcode;
     }
     public String getBarcode() {
         return barcode;
     }

    public void setQuantity(int quantity) {
         this.quantity = quantity;
     }
     public int getQuantity() {
         return quantity;
     }

     

    public String getDeadlineOfShelfLife() {
		return deadlineOfShelfLife;
	}
	public void setDeadlineOfShelfLife(String deadlineOfShelfLife) {
		this.deadlineOfShelfLife = deadlineOfShelfLife;
	}
	public void setProblem(String problem) {
         this.problem = problem;
     }
     public String getProblem() {
         return problem;
     }

    public void setOperation(String operation) {
         this.operation = operation;
     }
     public String getOperation() {
         return operation;
     }


}
