package com.corservlets;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;

public class SendViewR implements Serializable {
  
    
  
    JSONArray sendRequests,sendUsers;
  
    
    public SendViewR(JSONArray jsArray,JSONArray jsArray1) {
        
    	sendRequests = jsArray; 
    	sendUsers = jsArray1;
       
    }


	public JSONArray getRequest() {

		return sendRequests ;
	}
	public JSONArray getUsers() {

		return sendUsers;
	}

	 



}
