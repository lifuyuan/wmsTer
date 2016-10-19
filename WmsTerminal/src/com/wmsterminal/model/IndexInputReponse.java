package com.wmsterminal.model;

import java.io.Serializable;
import java.util.List;

public class IndexInputReponse {
	
	    private List<Inboundbatchnums> inboundbatchnums;
	    public void setInboundbatchnums(List<Inboundbatchnums> inboundbatchnums) {
	         this.inboundbatchnums = inboundbatchnums;
	     }
	     public List<Inboundbatchnums> getInboundbatchnums() {
	         return inboundbatchnums;
	     }
	     
	     public static class Inboundbatchnums implements Serializable{
	    	 
	    	  private String inboundbatchnum;
	    	    public void setInboundbatchnum(String inboundbatchnum) {
	    	         this.inboundbatchnum = inboundbatchnum;
	    	     }
	    	     public String getInboundbatchnum() {
	    	         return inboundbatchnum;
	    	     }
	     }

}
