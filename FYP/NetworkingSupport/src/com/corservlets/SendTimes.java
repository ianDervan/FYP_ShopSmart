package com.corservlets;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;


public class SendTimes implements Serializable {
    private final String timeIn, timeOut,breakIn,breakOut;  
   
    JSONArray sendJsonArrayHours;
    
    private String s1;
    
    public SendTimes(String t1, String t2,String t3,String t4,JSONArray jsArray1) {
        timeIn = t1;
        timeOut = t2;
        breakIn = t3;
        breakOut= t4;
        sendJsonArrayHours = jsArray1;
    }

	public String getStart() {

		return timeIn;
	}
	public String getFinish() {

		return timeOut;
	}

	public String getBreakI() {

		return breakIn;
	}
	public String getBreakO() {

		return breakOut;
	}
	public JSONArray getStaffHours() {

		return sendJsonArrayHours;
	}
	
}
