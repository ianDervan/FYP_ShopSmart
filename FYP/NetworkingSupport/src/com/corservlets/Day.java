package com.corservlets;

import java.util.Calendar;

public class Day {
	
	String dayS;

	public String getDay()
	{
		Calendar calendar = Calendar.getInstance();
		//int day = calendar.get(Calendar.DAY_OF_WEEK);
		int day = 3;

		if(day ==1)
		{
			dayS = "Sunday";
		}
		else if(day ==2)
		{
			dayS = "Monday";
		}
		else if(day ==3)
		{
			dayS = "Tuesday";
		}
		else if(day ==4)
		{
			dayS = "Wednesday";
		}
		else if(day ==5)
		{
			dayS = "Thursday";
		}
		else if(day ==6)
		{
			dayS = "Friday";
		}
		else if(day ==7)
		{
			dayS = "Saturday";
		}

		return dayS;
	}
}
