package fyp.shopsmart.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Users {
	
	String iTimeI;
	String iTimeO;
	String iBreakI;
	String iBreakO;
	
	 Map<String,String> ian = new HashMap<String,String>(); 
	
	
	public Users()
	{

	}

	public void setIanTime(String timeIn,String timeOut,String breakIn,String breakOut) {
		
		iTimeI = timeIn;
		iTimeO = timeOut;
		iBreakI = breakIn;
		iBreakO = breakOut;
		
		ian.put("iTimeI", iTimeI);
		ian.put("iTimeO", iTimeO);
		ian.put("iBreakI", iBreakI);
		ian.put("iBreakO", iBreakO);
	}
	public  Map<String, String> getTime() {	
		return ian;
	}





}


