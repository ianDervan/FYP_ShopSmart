package com.corservlets;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;


public class SendTimes implements Serializable {
    private final String timeIn, timeOut,breakIn,breakOut,hoursD,hoursW;  
   
    JSONArray sendJsonArrayHours;
    
    private String s1;
    
    public SendTimes(String t1, String t2,String t3,String t4,JSONArray jsArray1,String t5,String t6) {
        timeIn = t1;
        timeOut = t2;
        breakIn = t3;
        breakOut= t4;
        hoursD= t5;
        hoursW= t6;
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
	public String getHoursD() {

		return hoursD;
	}
	public String getHoursW() {

		
		return hoursW;
	}
	public JSONArray getStaffHours() {

		return sendJsonArrayHours;
	}
	
}
