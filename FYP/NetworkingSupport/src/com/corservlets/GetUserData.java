package com.corservlets;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class GetUserData {

	private Connection connection;

	private PreparedStatement sqlInsertDH;
	private PreparedStatement sqlInsertWH;

	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";

	String st = null;
	String bi = null;
	String bo = null;
	String to = null;

	String stU = null;
	String biU = null;
	String boU = null;
	String toU = null;

	int result;

	Map<String,String> data = new HashMap<String,String>(); 
	ArrayList<String> hours = new ArrayList<String>();
	String userName;
	String user;

	String daily;
	String weekly;
	public void getData( String name) throws SQLException
	{
		userName = name;

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();


		}


		try{	 

			String query = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM staffhours " +
					"WHERE Name = '"+ userName+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if(rs.next())
			{
				st =  rs.getString("StartTime");
				bi =  rs.getString("BreakIn");
				bo =  rs.getString("BreakOut");
				to =  rs.getString("FinishTime");

			}
			data.put("StartTime",st);
			data.put("BreakIn",bi);
			data.put("BreakOut",bo);
			data.put("FinishTime",to);



			//connection.commit();
		}
		catch ( SQLException sqlException ) {

		}
	} 
	public void getStaffData(String name) 
	{
		user = name;


		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();

		}

		try{	
			
			

			String query1 = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM "+user+" " +
					"WHERE Day = '"+"Monday"+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query1);

			if(rs.next())
			{
				hours.add(rs.getString("StartTime"));
				hours.add(rs.getString("BreakIn"));
				hours.add(rs.getString("BreakOut"));
				hours.add(rs.getString("FinishTime"));
			}

			String query2 = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM "+user+" " +
					"WHERE Day = '"+"Tuesday"+"'";
			rs = stmt.executeQuery(query2);

			if(rs.next())
			{				
				hours.add(rs.getString("StartTime"));
				hours.add(rs.getString("BreakIn"));
				hours.add(rs.getString("BreakOut"));
				hours.add(rs.getString("FinishTime"));
			}

			String query3 = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM "+user+" " +
					"WHERE Day = '"+"Wednesday"+"'";
			rs = stmt.executeQuery(query3);

			if(rs.next())
			{
				hours.add(rs.getString("StartTime"));
				hours.add(rs.getString("BreakIn"));
				hours.add(rs.getString("BreakOut"));
				hours.add(rs.getString("FinishTime"));

			}

			String query4 = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM "+user+" " +
					"WHERE Day = '"+"Thursday"+"'";
			rs = stmt.executeQuery(query4);

			if(rs.next())
			{
				hours.add(rs.getString("StartTime"));
				hours.add(rs.getString("BreakIn"));
				hours.add(rs.getString("BreakOut"));
				hours.add(rs.getString("FinishTime"));
			}

			String query5 = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM "+user+ " " +
					"WHERE Day = '"+"Friday"+"'";
			rs = stmt.executeQuery(query5);

			if(rs.next())
			{
				hours.add(rs.getString("StartTime"));
				hours.add(rs.getString("BreakIn"));
				hours.add(rs.getString("BreakOut"));
				hours.add(rs.getString("FinishTime"));
			}

			String query6 = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM "+user+" " +
					"WHERE Day = '"+"Saturday"+"'";
			rs = stmt.executeQuery(query6);

			if(rs.next())
			{
				hours.add(rs.getString("StartTime"));
				hours.add(rs.getString("BreakIn"));
				hours.add(rs.getString("BreakOut"));
				hours.add(rs.getString("FinishTime"));
			}

			String query7 = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM "+user+" " +
					"WHERE Day = '"+"Sunday"+"'";
			rs = stmt.executeQuery(query7);

			if(rs.next())
			{
				hours.add(rs.getString("StartTime"));
				hours.add(rs.getString("BreakIn"));
				hours.add(rs.getString("BreakOut"));
				hours.add(rs.getString("FinishTime"));
			}
		}
		catch ( SQLException sqlException ) {
			System.out.println("sqlexceptionA " + sqlException );

		}
	}
	public void getHoursDay(String name) 
	{

		String start = null;
		String finish = null;
		String breakI = null;
		String breakO = null;
		String weeklyHours = null;

		float wh = 0;

		float s = 0;
		float f = 0;
		float i = 0;
		float o = 0;

		float sum;
		float sum1;
		String dayS = null;

		Day day = new Day();
		dayS = day.getDay();

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();

		}


		try {
			String query7 = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM "+name+" " +
					"WHERE Day = '"+dayS+"'";
			Statement stmt;
			stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query7);

			if(rs.next())
			{
				start = rs.getString("StartTime");
				breakI = rs.getString("BreakIn");
				breakO =rs.getString("BreakOut");
				finish = rs.getString("FinishTime");
			}

			start  =start.replace(":", ".");
			breakI = breakI.replace(":", ".");
			breakO = breakO.replaceAll(":", ".");
			finish = finish.replaceAll(":", ".");

			//			s = Float.valueOf(start);
			//			f = Float.valueOf(finish);
			//			i = Float.valueOf(breakI);
			//			o = Float.valueOf(breakO);

			//NumberFormat formatter = new DecimalFormat("#00.00");

			if(!start.trim().equals(""))
			{
				s= Float.parseFloat(start);
			}
			if(!finish.trim().equals(""))
			{
				f= Float.parseFloat(finish);
			}
			if(!breakI.trim().equals(""))
			{
				i= Float.parseFloat(breakI);
			}
			if(!breakO.trim().equals(""))
			{
				o= Float.parseFloat(breakO);
			}

		

			sum = ((o -i) - (f- s));

			float x = Math.abs(sum);

			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);

			x = Float.parseFloat(df.format(x));

			String insertHD = Float.toString(x);

			


			sqlInsertDH= connection.prepareStatement(
					"UPDATE staffhours SET HoursWorkedD = '"+insertHD+"'"+
							"WHERE Name = '"+name+"' ");
			result = sqlInsertDH.executeUpdate();


			String query1 = "SELECT HoursWorkedW FROM staffhours " +
					"WHERE Name = '"+name+"'";

			rs = stmt.executeQuery(query1);

			if(rs.next())
			{
				weeklyHours = rs.getString("HoursWorkedW");

			}

			

			if(!weeklyHours.trim().equals(""))
			{
				wh= Float.parseFloat(weeklyHours);
				

				sum1 = wh+ x;
				
				sum1 =Float.parseFloat(df.format(sum1));

				String insertHW = Float.toString(sum1);

				sqlInsertDH= connection.prepareStatement(
						"UPDATE staffhours SET HoursWorkedW = '"+insertHW+"'"+
								"WHERE Name = '"+name+"' ");
				result = sqlInsertDH.executeUpdate();

			}
			else
			{

				sqlInsertWH= connection.prepareStatement(
						"UPDATE staffhours SET HoursWorkedW = '"+insertHD+"'"+
								"WHERE Name = '"+name+"' ");
				result = sqlInsertWH.executeUpdate();

			}

			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}  

			connection.commit();

		} catch (SQLException e) {
			System.out.println("Exception\n" +e);
		}



	}
	
	public void getHoursTotal(String username)
	{
		
		
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			String query= "SELECT HoursWorkedD FROM staffhours " +
					"WHERE Name = '"+username+"'";

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				daily=  rs.getString("HoursWorkedD");
			}
			
		
			
			String query2= "SELECT HoursWorkedW FROM staffhours " +
					"WHERE Name = '"+username+"'";

			stmt = connection.createStatement();

            rs = stmt.executeQuery(query2);
			if(rs.next())
			{
				weekly =  rs.getString("HoursWorkedW");
			}
	
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

	public  String getDataResult(String key) {	
		return data.get(key);
	}
	public  ArrayList<String> getUserDataResult() {	
		return hours;
	}
	public String getDaily()
	{
		return daily;
	}
	public String getWeekly()
	{
		return weekly;
	}

	public void connect() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(dbURL, username, password);
		//System.out.println("Connected to database....\n");
		connection.setAutoCommit( false );
	}
	protected void finalize()
	{
		close(); 
	}

	private void close() {

		try {
			sqlInsertDH.close();
			sqlInsertWH.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
