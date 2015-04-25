package com.corservlets;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;

public class SendViewR implements Serializable {
  
    
  
    JSONArray sendRequests,sendUsers,sendShopList;
    String sendStatus;
    String sendDayTime;
  
    
    public SendViewR(JSONArray jsArray,JSONArray jsArray1,JSONArray jsArray2,String status,String dt) {
        
    	sendRequests = jsArray; 
    	sendUsers = jsArray1;
    	sendShopList = jsArray2;
    	sendStatus = status;
    	sendDayTime =dt;
       
    }


	public JSONArray getRequest() {

		return sendRequests ;
	}
	public JSONArray getUsers() {

		return sendUsers;
	}

	public JSONArray getShopList() {

		return sendShopList;
	}
	public String getStatus() {

		return sendStatus;
	}
	public String getDayTime() {

		return sendDayTime;
	}

	 



}
