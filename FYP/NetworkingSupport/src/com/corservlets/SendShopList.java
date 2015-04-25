package com.corservlets;

import java.io.Serializable;

public class SendShopList implements Serializable {
    private final String listOk,status;  
    
   

    
    public SendShopList(String p1,String p2) {
    	listOk = p1;
    	status = p2;


    }
  

	public String getUsername() {

		return listOk;
	}
	public String getStatus() {

		return status;
	}
	

}