package com.corservlets;



import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;

public class SendManage implements Serializable {

    
  
    JSONArray sendJsonArrayOp;
    JSONArray sendJsonArrayFDT;

    
    public SendManage(JSONArray jsArray,JSONArray jsArray1) {
     
        sendJsonArrayOp = jsArray;
        sendJsonArrayFDT = jsArray1;
 
    }

	public JSONArray getItemOp() {

		return sendJsonArrayOp;
	}
	
	public JSONArray getItemFDT() {

		return sendJsonArrayFDT;
	}

	 



}
