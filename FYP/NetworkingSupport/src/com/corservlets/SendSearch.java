package com.corservlets;

import java.io.Serializable;

public class SendSearch implements Serializable {
    private final String searchX,searchY;  
    
   

    
    public SendSearch(String x,String y) {
        searchX = x;
        searchY = y;


    }
  

	public String getSearchX() {

		return searchX;
	}
	public String getSearchY() {

		return searchY;
	}
	

}