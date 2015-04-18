package com.corservlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;



public class Database {

	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";

	String timeIn;
	String timeOut;
	String breakIn;
	String breakOut;
	String dayS;


	String userName;
	String uname;
	int result;

	String get;
	String st = null;
	String bi = null;
	String bo = null;
	String to = null;

	String stU = null;
	String biU = null;
	String boU = null;
	String toU = null;


	// reference to database connection
	private Connection connection;
	private PreparedStatement sqlInsertTime;
	private PreparedStatement sqlInsertUserTime;
	private PreparedStatement sqlDeleteUser;
	private PreparedStatement sqlDeleteTable;

	//String t1,String t2,String t3,String t4

	public Database(String t1,String t2,String t3,String t4) 
	{


		timeIn = t1;
		timeOut=t4; 
		breakIn=t2;
		breakOut=t3;

	}
	public void readData( String name) throws SQLException
	{
		userName = name;

		Day day1 = new Day();
		dayS = day1.getDay();

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

			if(userName.equals("sarah"))
			{
				String queryUser = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM sarah " +
						"WHERE Day = '"+ dayS+"'";
				Statement stmtUser = connection.createStatement();
				ResultSet rsUser = stmtUser.executeQuery(queryUser);

				if(rsUser.next())
				{
					stU =  rsUser.getString("StartTime");
					biU =  rsUser.getString("BreakIn");
					boU =  rsUser.getString("BreakOut");
					toU =  rsUser.getString("FinishTime");

				}
			}

			else if(userName.equals("ian"))
			{

				String queryUser = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM ian " +
						"WHERE Day = '"+ dayS+"'";
				Statement stmtUser = connection.createStatement();
				ResultSet rsUser = stmtUser.executeQuery(queryUser);

				if(rsUser.next())
				{
					stU =  rsUser.getString("StartTime");
					biU =  rsUser.getString("BreakIn");
					boU =  rsUser.getString("BreakOut");
					toU =  rsUser.getString("FinishTime");

				}

			}
			else if(userName.equals("aishling"))
			{
				System.out.println("AISHLING");
				String queryUser = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM aishling " +
						"WHERE Day = '"+ dayS+"'";
				Statement stmtUser = connection.createStatement();
				ResultSet rsUser = stmtUser.executeQuery(queryUser);

				if(rsUser.next())
				{
					stU =  rsUser.getString("StartTime");
					biU =  rsUser.getString("BreakIn");
					boU =  rsUser.getString("BreakOut");
					toU =  rsUser.getString("FinishTime");

				}
			}
			else  if(userName.equals("john"))
			{
				System.out.println("INSERTINGJ");
				String queryUser = "SELECT StartTime,BreakIn,BreakOut,FinishTime FROM john " +
						"WHERE Day = '"+ dayS+"'";
				Statement stmtUser = connection.createStatement();
				ResultSet rsUser = stmtUser.executeQuery(queryUser);

				if(rsUser.next())
				{
					stU =  rsUser.getString("StartTime");
					biU =  rsUser.getString("BreakIn");
					boU =  rsUser.getString("BreakOut");
					toU =  rsUser.getString("FinishTime");

				}

			}



			if(timeIn != null && st.isEmpty())
			{
				System.out.println("TIME IN");
				//System.out.println(" START TIME");
				sqlInsertTime = connection.prepareStatement(
						"UPDATE staffhours SET StartTime = '"+timeIn+"' " +
								"WHERE Name = '"+ userName+"' ");
				result = sqlInsertTime.executeUpdate();


				if(stU.isEmpty())
				{


					sqlInsertUserTime = connection.prepareStatement(
							"UPDATE "+userName+" SET StartTime = '"+timeIn+"' " +
									"WHERE Day = '"+ dayS+"' ");
					result = sqlInsertUserTime.executeUpdate();

				}
			}
			if(timeOut != null && to.isEmpty() )
			{

				sqlInsertTime = connection.prepareStatement(
						"UPDATE staffhours SET FinishTime = '"+timeOut+"' " +
								"WHERE Name = '"+ userName+ "' ");
				result = sqlInsertTime.executeUpdate();

				if(toU.isEmpty())
				{

					sqlInsertUserTime = connection.prepareStatement(
							"UPDATE "+userName+"  SET FinishTime = '"+timeOut+"' " +
									"WHERE Day = '"+ dayS+"' ");
					result = sqlInsertUserTime.executeUpdate();

				}

			}
			if(breakIn != null && bi.isEmpty() )
			{

				sqlInsertTime = connection.prepareStatement(
						"UPDATE staffhours SET BreakIn = '"+breakIn+"' " +
								"WHERE Name = '"+ userName+"' ");
				result = sqlInsertTime.executeUpdate();

				if(biU.isEmpty())
				{
					sqlInsertUserTime = connection.prepareStatement(
							"UPDATE "+userName+"  SET BreakIn = '"+breakIn+"' " +
									"WHERE Day = '"+ dayS+"' ");
					result = sqlInsertUserTime.executeUpdate();

				}
			}
			if(breakOut != null && bo.isEmpty())
			{

				sqlInsertTime = connection.prepareStatement(
						"UPDATE staffhours SET breakOut = '"+breakOut+"' " +
								"WHERE Name = '"+ userName+"' ");
				result = sqlInsertTime.executeUpdate();

				if(boU.isEmpty())
				{

					sqlInsertUserTime = connection.prepareStatement(
							"UPDATE "+userName+"  SET BreakOut = '"+breakOut+"' " +
									"WHERE Day = '"+ dayS+"' ");
					result = sqlInsertUserTime.executeUpdate();
				}


			}


			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}      

			connection.commit();

		}
		catch ( SQLException sqlException ) {

			System.out.print("\nsqlException" + sqlException);

		}
	} 
	public void delete(String name)
	{

		try {
			connect();
		} catch (Exception e) {
		}
		try {

			System.out.print("\nDeleting staffhours\n");

			sqlDeleteUser = connection.prepareStatement(
					"UPDATE staffhours SET StartTime = '"+""+"',FinishTime = '"+""+"',BreakIn = '"+""+"',BreakOut = '"+""+"' " +
							"WHERE Name = '"+ name+"' ");

			result = sqlDeleteUser.executeUpdate();

			if ( result == 0 ) {

				connection.rollback();

			} 
			connection.commit();
			sqlDeleteUser.close();
			connection.close();

		} catch (SQLException e) {
			System.out.print("\nDeleting" + e);

		}
	}
	public void deleteTable(String user)
	{

		try {
			connect();
		} catch (Exception e) {
		}
		try {

			System.out.print("\nDeleting Table");

				sqlDeleteTable = connection.prepareStatement(
						"UPDATE "+user+" SET StartTime = '"+""+"',FinishTime = '"+""+"',BreakIn = '"+""+"',BreakOut = '"+""+"' " );
				result = sqlDeleteTable.executeUpdate();

			if ( result == 0 ) {

				connection.rollback();

			} 
			connection.commit();

			sqlDeleteTable.close();
			connection.close();

		} catch (SQLException e) {
			System.out.print("\nDeleting" + e);

		}
	}

	public void connect() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(dbURL, username, password);
		connection.setAutoCommit( false );
	}
	private void close() {

		try {
			sqlInsertTime.close();
			sqlInsertUserTime.close();
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
