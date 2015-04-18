package com.corservlets;


import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;

public class SendStock implements Serializable {
    private final String itemTxt,itemStk;  
    
  
    JSONArray sendJsonArrayName;
    JSONArray sendJsonArrayStk;
    
    public SendStock(String s1,String s2,JSONArray jsArray,JSONArray jsArray1) {
        itemTxt = s1;
        itemStk = s2;
        sendJsonArrayName = jsArray; 
        sendJsonArrayStk = jsArray1;
     

    }

	public String getItemTxtFS() {

		return itemTxt;
	}
	public String getItemStk() {

		return itemStk;
	}
	public JSONArray getItemName() {

		return sendJsonArrayName;
	}
	public JSONArray getItemStock() {

		return sendJsonArrayStk;
	}

	 



}