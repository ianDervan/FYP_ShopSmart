package com.corservlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RotaData {

	private Connection connection;
	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";

	String john = null;
	String ian = null;
	String sarah = null;
	int result;
	String aishling = null;


	ArrayList<String> rota = new ArrayList<String>();
	ArrayList<String> rotaNw = new ArrayList<String>();

	String userNameFR;

	String swapDay = null;
	String withUser = null;
	String forDay = null;
	String getDay;

	private PreparedStatement sqlInsert1;
	private PreparedStatement sqlInsert2;
	private PreparedStatement sqlInsertDay;

	private PreparedStatement sqlInsertShift1;
	private PreparedStatement sqlInsertShift2;
	private PreparedStatement sqlInsertShift3;

	public void insertShift(Map<String,String>  rota) 
	{

		String user1 =rota.get("user1");
		String shift1 =rota.get("shift1");
		String day1 =rota.get("day1");

		String user2 =rota.get("user2");
		String shift2 =rota.get("shift2");
		String day2 =rota.get("day2");

		String user3 =rota.get("user3");
		String shift3 =rota.get("shift3");
		String day3 =rota.get("day3");

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {

			if(!user1.equals("N"))
			{
				
				if(day1.equals("All Days"))
				{
					
					sqlInsertShift1 = connection.prepareStatement(
							"UPDATE rota  SET "+user1+" = '"+shift1+"' " 
									);
					result = sqlInsertShift1.executeUpdate();
				}
				else
				{
					sqlInsertShift1 = connection.prepareStatement(
							"UPDATE rota  SET "+user1+" = '"+shift1+"' " +
									"WHERE Day = '"+ day1+"' ");
					result = sqlInsertShift1.executeUpdate();
					
				}

			}
			if(!user2.equals("N"))
			{
				if(day2.equals("All Days"))
				{
					sqlInsertShift2 = connection.prepareStatement(
							"UPDATE rota  SET "+user2+" = '"+shift2+"' " 
									);
					result = sqlInsertShift2.executeUpdate();
				}
				else
				{
					sqlInsertShift2 = connection.prepareStatement(
							"UPDATE rota  SET "+user2+" = '"+shift2+"' " +
									"WHERE Day = '"+ day2+"' ");
					result = sqlInsertShift2.executeUpdate();
					
				}

			}

			if(!user3.equals("N"))
			{
				if(day3.equals("All Days"))
				{
					sqlInsertShift3 = connection.prepareStatement(
							"UPDATE rota  SET "+user3+" = '"+shift3+"' ");
					result = sqlInsertShift3.executeUpdate();
				}
				else
				{
					sqlInsertShift3 = connection.prepareStatement(
							"UPDATE rota  SET "+user3+" = '"+shift3+"' " +
									"WHERE Day = '"+ day3+"' ");
					result = sqlInsertShift3.executeUpdate();
					
				}

			}

			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}      

			connection.commit();


		} catch (SQLException e) {

		}


	}
	public void clearShift(Map<String,String>  rota) 
	{

		String user1 =rota.get("user1");
		String shift1 =rota.get("shift1");
		String day1 =rota.get("day1");

		String user2 =rota.get("user2");
		String shift2 =rota.get("shift2");
		String day2 =rota.get("day2");

		String user3 =rota.get("user3");
		String shift3 =rota.get("shift3");
		String day3 =rota.get("day3");

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {

			if(!user1.equals("N"))
			{
				
				if(day1.equals("All Days"))
				{
					
					sqlInsertShift1 = connection.prepareStatement(
							"UPDATE rota  SET "+user1+" = '"+""+"' " 
									);
					result = sqlInsertShift1.executeUpdate();
				}
				else
				{
					sqlInsertShift1 = connection.prepareStatement(
							"UPDATE rota  SET "+user1+" = '"+""+"' " +
									"WHERE Day = '"+ day1+"' ");
					result = sqlInsertShift1.executeUpdate();
					
				}

			}
			if(!user2.equals("N"))
			{
				if(day2.equals("All Days"))
				{
					sqlInsertShift2 = connection.prepareStatement(
							"UPDATE rota  SET "+user2+" = '"+""+"' " 
									);
					result = sqlInsertShift2.executeUpdate();
				}
				else
				{
					sqlInsertShift2 = connection.prepareStatement(
							"UPDATE rota  SET "+user2+" = '"+""+"' " +
									"WHERE Day = '"+ day2+"' ");
					result = sqlInsertShift2.executeUpdate();
					
				}

			}

			if(!user3.equals("N"))
			{
				if(day3.equals("All Days"))
				{
					sqlInsertShift3 = connection.prepareStatement(
							"UPDATE rota  SET "+user3+" = '"+""+"' ");
					result = sqlInsertShift3.executeUpdate();
				}
				else
				{
					sqlInsertShift3 = connection.prepareStatement(
							"UPDATE rota  SET "+user3+" = '"+""+"' " +
									"WHERE Day = '"+ day3+"' ");
					result = sqlInsertShift3.executeUpdate();
					
				}

			}

			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}      

			connection.commit();


		} catch (SQLException e) {

			System.out.println("\nexception 1" + e );
		}


	}

	public void getRota() 
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}

		try{

			String query1 = "SELECT John,Ian,Sarah,Aishling FROM rota " +
					"WHERE Day = 'Monday'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query1);


			if(rs.next())
			{


				rota.add(rs.getString("John"));
				rota.add(rs.getString("Ian"));
				rota.add(rs.getString("Sarah"));
				rota.add(rs.getString("Aishling"));



			}


			String query2 = "SELECT John,Ian,Sarah,Aishling FROM rota " +
					"WHERE Day = 'Tuesday'";

			rs = stmt.executeQuery(query2);

			if(rs.next())
			{
				rota.add(rs.getString("John"));
				rota.add(rs.getString("Ian"));
				rota.add(rs.getString("Sarah"));
				rota.add(rs.getString("Aishling"));
			}

			String query3 = "SELECT John,Ian,Sarah,Aishling FROM rota " +
					"WHERE Day = 'Wednesday'";
			rs = stmt.executeQuery(query3);


			if(rs.next())
			{

				rota.add(rs.getString("John"));
				rota.add(rs.getString("Ian"));
				rota.add(rs.getString("Sarah"));
				rota.add(rs.getString("Aishling"));
			}


			String query4 = "SELECT John,Ian,Sarah,Aishling FROM rota " +
					"WHERE Day = 'Thursday'";

			rs = stmt.executeQuery(query4);


			if(rs.next())
			{
				rota.add(rs.getString("John"));
				rota.add(rs.getString("Ian"));
				rota.add(rs.getString("Sarah"));
				rota.add(rs.getString("Aishling"));
			}

			String query5 = "SELECT John,Ian,Sarah,Aishling FROM rota " +
					"WHERE Day = 'Friday'";
			rs = stmt.executeQuery(query5);


			if(rs.next())
			{			
				rota.add(rs.getString("John"));
				rota.add(rs.getString("Ian"));
				rota.add(rs.getString("Sarah"));
				rota.add(rs.getString("Aishling"));
			}

			String query6 = "SELECT John,Ian,Sarah,Aishling FROM rota " +
					"WHERE Day = 'Saturday'";

			rs = stmt.executeQuery(query6);

			if(rs.next())
			{

				rota.add(rs.getString("John"));
				rota.add(rs.getString("Ian"));
				rota.add(rs.getString("Sarah"));
				rota.add(rs.getString("Aishling"));
			}

			String query7 = "SELECT John,Ian,Sarah,Aishling FROM rota " +
					"WHERE Day = 'Sunday'";	
			rs = stmt.executeQuery(query7);

			if(rs.next())
			{

				rota.add(rs.getString("John"));
				rota.add(rs.getString("Ian"));
				rota.add(rs.getString("Sarah"));
				rota.add(rs.getString("Aishling"));
			}



			//			for (Map.Entry<String,String> entry : rota.entrySet()) {
			//				String key = entry.getKey();
			//				String value = entry.getValue();
			//
			//
			//				System.out.println("key, " + key + " value " + value );
			//			}
			connection.commit();

		}catch ( SQLException sqlException ) {
			System.out.println("sqlexceptionA " + sqlException );

		}



	}
	public void getRotaNW() 
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}

		try{

			String query1 = "SELECT John,Ian,Sarah,Aishling FROM rotanw " +
					"WHERE Day = 'Monday'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query1);


			if(rs.next())
			{			
				rotaNw.add(rs.getString("John"));
				rotaNw.add(rs.getString("Ian"));
				rotaNw.add(rs.getString("Sarah"));
				rotaNw.add(rs.getString("Aishling"));

			}

			String query2 = "SELECT John,Ian,Sarah,Aishling FROM rotanw " +
					"WHERE Day = 'Tuesday'";

			rs = stmt.executeQuery(query2);


			if(rs.next())
			{
				rotaNw.add(rs.getString("John"));
				rotaNw.add(rs.getString("Ian"));
				rotaNw.add(rs.getString("Sarah"));
				rotaNw.add(rs.getString("Aishling"));
			}

			String query3 = "SELECT John,Ian,Sarah,Aishling FROM rotanw " +
					"WHERE Day = 'Wednesday'";
			rs = stmt.executeQuery(query3);


			if(rs.next())
			{
				rotaNw.add(rs.getString("John"));
				rotaNw.add(rs.getString("Ian"));
				rotaNw.add(rs.getString("Sarah"));
				rotaNw.add(rs.getString("Aishling"));
			}

			String query4 = "SELECT John,Ian,Sarah,Aishling FROM rotanw " +
					"WHERE Day = 'Thursday'";
			rs = stmt.executeQuery(query4);


			if(rs.next())
			{
				rotaNw.add(rs.getString("John"));
				rotaNw.add(rs.getString("Ian"));
				rotaNw.add(rs.getString("Sarah"));
				rotaNw.add(rs.getString("Aishling"));
			}

			String query5 = "SELECT John,Ian,Sarah,Aishling FROM rotanw " +
					"WHERE Day = 'Friday'";
			rs = stmt.executeQuery(query5);

			if(rs.next())
			{
				rotaNw.add(rs.getString("John"));
				rotaNw.add(rs.getString("Ian"));
				rotaNw.add(rs.getString("Sarah"));
				rotaNw.add(rs.getString("Aishling"));
			}

			String query6 = "SELECT John,Ian,Sarah,Aishling FROM rotanw " +
					"WHERE Day = 'Saturday'";
			rs = stmt.executeQuery(query6);

			if(rs.next())
			{				
				rotaNw.add(rs.getString("John"));
				rotaNw.add(rs.getString("Ian"));
				rotaNw.add(rs.getString("Sarah"));
				rotaNw.add(rs.getString("Aishling"));
			}

			String query7 = "SELECT John,Ian,Sarah,Aishling FROM rotanw " +
					"WHERE Day = 'Sunday'";
			rs = stmt.executeQuery(query7);


			if(rs.next())
			{				
				rotaNw.add(rs.getString("John"));
				rotaNw.add(rs.getString("Ian"));
				rotaNw.add(rs.getString("Sarah"));
				rotaNw.add(rs.getString("Aishling"));
			}

			//						for (Map.Entry<String,String> entry : rotanw.entrySet()) {
			//							String key = entry.getKey();
			//							String value = entry.getValue();
			//			
			//			
			//							System.out.println("key, " + key + " value " + value );
			//						}
			connection.commit();

		}catch ( SQLException sqlException ) {
			System.out.println("sqlexceptionA " + sqlException );

		}
	}

	public   ArrayList<String>  getRotaDataResult() {	
		return rota;
	}
	public   ArrayList<String>  getRotaNWResult() {	
		return rotaNw;
	}
	public void swapDay(String user,String sday,String wuser,String fuday)
	{
		userNameFR = user;
		swapDay =sday;
		withUser =wuser;
		forDay = fuday;

		try {
			connect();
		} catch (Exception e) {
		}

		System.out.print("\n user " + userNameFR);
		System.out.print("\n swapday" + swapDay);
		System.out.print("\n withUser" + withUser);
		System.out.print("\n forDay " + forDay);

		getDay = null;
		String getUserDay = null;

		try {

			String query = "SELECT John,Ian,Sarah,Aishling FROM rota " +
					"WHERE Day = '"+ swapDay+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				getDay =  rs.getString( userNameFR);


			}
			System.out.println("TIME IS = " + getDay);
			//			
			String query1 = "SELECT John,Ian,Sarah,Aishling FROM rota " +
					"WHERE Day = '"+forDay +"'";
			Statement stmt1 = connection.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query1);
			if(rs1.next())
			{
				getUserDay =  rs1.getString(withUser);


			}
			String rot1 = "rota";

			sqlInsert1 = connection.prepareStatement(
					"UPDATE rota  SET "+userNameFR+" = '"+getUserDay+"' " +
							"WHERE Day = '"+ swapDay+"' ");
			result = sqlInsert1.executeUpdate();

			sqlInsert2 = connection.prepareStatement(
					"UPDATE rota  SET "+withUser+" = '"+getDay+"' " +
							"WHERE Day = '"+ forDay+"' ");
			result = sqlInsert2.executeUpdate();
			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}      
			//System.out.println("\nOTHER USER TIME IS = " + getUserDay);
			connection.commit();
		} catch (SQLException e) {
			System.out.println("SQL EXCEPTION\n" + e);
		}
	}
	public void requestDayOff(String day,String user) 
	{

		String insertDay;
		String userName;

		insertDay = day;
		userName = user;
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			sqlInsertDay = connection.prepareStatement(
					"UPDATE rotanw  SET "+userName+" = 'NA' " +
							"WHERE Day = '"+ insertDay+"' ");
			result = sqlInsertDay.executeUpdate();
			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}      
			//System.out.println("\nOTHER USER TIME IS = " + getUserDay);
			connection.commit();
		} catch (SQLException e) {

			System.out.println("\nSQLException = " + e);
		}


	}
	public void connect() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(dbURL, username, password);
		//System.out.println("Connected to database....\n");
		connection.setAutoCommit( false );
	}
	private void close() {

		try {
			sqlInsert2.close();
			sqlInsert1.close();
			sqlInsertDay.close();
			sqlInsertShift1.close();
			//	sqlInsertShift2.close();
			//	sqlInsertShift3.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void finalize()
	{
		close(); 
	}




}
