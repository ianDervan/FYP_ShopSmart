package com.corservlets;

import java.io.Serializable;

public class SendSLP implements Serializable {
    private final String price;  
    
   

    
    public SendSLP(String p1) {
    	price = p1;
    
    	

    }
  

	public String getPriceSL() {

		return price;
	}
}
	
