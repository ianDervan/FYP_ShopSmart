package com.corservlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManageData {

	private Connection connection;
	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";
	int result;

	String staffN;
	String operation;

	String deliveryN;
	String deliveryD;
	String deliveryT;

	ArrayList<String> storeArray = new ArrayList<String>();
	ArrayList<String> storeArray1 = new ArrayList<String>();


	private PreparedStatement sqlInsertOp;
	private PreparedStatement sqlInsertDT;
	private PreparedStatement sqlDeleteOp;
	private PreparedStatement sqlClearFDT;
	private PreparedStatement sqlClearAFDT;
	private PreparedStatement sqlClear;


	public void insertOp(String user,String op) 
	{
		staffN = user;
		operation = op;

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			sqlDeleteOp = connection.prepareStatement(
					"UPDATE manage SET Tills = '"+""+"',Deli = '"+""+"',Stock = '"+""+"',store = '"+""+"' " +
							"WHERE Name = '"+staffN+"' ");
			result = sqlDeleteOp.executeUpdate();

			sqlInsertOp = connection.prepareStatement(
					"UPDATE manage  SET "+operation+" = 'X' " +
							"WHERE Name = '"+ staffN +"' ");
			result = sqlInsertOp.executeUpdate();

			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}  

			connection.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("\n exception ..." + e);
		}


	}

	public void insertDelivery(String name,String day,String time) 
	{
		deliveryN = name;
		deliveryD = day;
		deliveryT = time;

		try {
			connect();
		} catch (Exception e) {

			System.out.print("\n exception ..." + e);
		}
		try {
			sqlInsertDT = connection.prepareStatement(
					"UPDATE deliveries  SET "+deliveryD+" = '"+deliveryT+"' " +
							"WHERE Delivery= '"+ deliveryN +"' ");
			result = sqlInsertDT.executeUpdate();

			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}  

			connection.commit();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("\n exception ..." + e);
		}





	}

	public void getData() 
	{


		try {
			connect();
		} catch (Exception e) {

			System.out.print("\n exception ..." + e);
		}


		try {
			String query = "SELECT Tills,Deli,Stock,Store FROM manage " +
					"WHERE Name = 'Ian'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if(rs.next())
			{
				storeArray.add(  rs.getString("Tills"));
				storeArray.add(  rs.getString("Deli"));
				storeArray.add(  rs.getString("Stock"));
				storeArray.add(  rs.getString("Store"));

			}

			String query1 = "SELECT Tills,Deli,Stock,Store FROM manage " +
					"WHERE Name = 'John'";
			Statement stmt1 = connection.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query1);

			if(rs1.next())
			{
				storeArray.add(  rs1.getString("Tills"));
				storeArray.add(  rs1.getString("Deli"));
				storeArray.add(  rs1.getString("Stock"));
				storeArray.add(  rs1.getString("Store"));

			}

			String query2 = "SELECT Tills,Deli,Stock,Store FROM manage " +
					"WHERE Name = 'Sarah'";
			Statement stmt2 = connection.createStatement();
			ResultSet rs2 = stmt2.executeQuery(query2);

			if(rs2.next())
			{
				storeArray.add(  rs2.getString("Tills"));
				storeArray.add(  rs2.getString("Deli"));
				storeArray.add(  rs2.getString("Stock"));
				storeArray.add(  rs2.getString("Store"));

			}

			String query3 = "SELECT Tills,Deli,Stock,Store FROM manage " +
					"WHERE Name = 'Aishling'";
			Statement stmt3 = connection.createStatement();
			ResultSet rs3 = stmt3.executeQuery(query3);

			if(rs3.next())
			{
				storeArray.add(  rs3.getString("Tills"));
				storeArray.add(  rs3.getString("Deli"));
				storeArray.add(  rs3.getString("Stock"));
				storeArray.add(  rs3.getString("Store"));

			}

//			for (String str : storeArray) {
//
//				System.out.println("Item is: " + str);
//
//			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("\n exception ..." + e);
		}



	}
	
	public void getDataFDT() 
	{


		try {
			connect();
		} catch (Exception e) {

			System.out.print("\n exception ..." + e);
		}


		try {
			String query = "SELECT Monday,Tuesday,Wednesday,Thursday,Friday FROM deliveries " +
					"WHERE Delivery = 'Fruit+Veg'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if(rs.next())
			{
				storeArray1.add(  rs.getString("Monday"));
				storeArray1.add(  rs.getString("Tuesday"));
				storeArray1.add(  rs.getString("Wednesday"));
				storeArray1.add(  rs.getString("Thursday"));
				storeArray1.add(  rs.getString("Friday"));

			}
			
			String query1 = "SELECT Monday,Tuesday,Wednesday,Thursday,Friday FROM deliveries " +
					"WHERE Delivery = 'Dairy'";
			 rs = stmt.executeQuery(query1);

			if(rs.next())
			{
				storeArray1.add(  rs.getString("Monday"));
				storeArray1.add(  rs.getString("Tuesday"));
				storeArray1.add(  rs.getString("Wednesday"));
				storeArray1.add(  rs.getString("Thursday"));
				storeArray1.add(  rs.getString("Friday"));
			}
			
			String query2 = "SELECT Monday,Tuesday,Wednesday,Thursday,Friday FROM deliveries " +
					"WHERE Delivery = 'Frozen'";
			 rs = stmt.executeQuery(query2);

			if(rs.next())
			{
				storeArray1.add(  rs.getString("Monday"));
				storeArray1.add(  rs.getString("Tuesday"));
				storeArray1.add(  rs.getString("Wednesday"));
				storeArray1.add(  rs.getString("Thursday"));
				storeArray1.add(  rs.getString("Friday"));
			}
			
			
			String query4 = "SELECT Monday,Tuesday,Wednesday,Thursday,Friday FROM deliveries " +
					"WHERE Delivery = 'Minerals'";
			 rs = stmt.executeQuery(query4);

			if(rs.next())
			{
				storeArray1.add(  rs.getString("Monday"));
				storeArray1.add(  rs.getString("Tuesday"));
				storeArray1.add(  rs.getString("Wednesday"));
				storeArray1.add(  rs.getString("Thursday"));
				storeArray1.add(  rs.getString("Friday"));
			}
			
			String query5 = "SELECT Monday,Tuesday,Wednesday,Thursday,Friday FROM deliveries " +
					"WHERE Delivery = 'Alcohol'";
			 rs = stmt.executeQuery(query5);

			if(rs.next())
			{
				storeArray1.add(  rs.getString("Monday"));
				storeArray1.add(  rs.getString("Tuesday"));
				storeArray1.add(  rs.getString("Wednesday"));
				storeArray1.add(  rs.getString("Thursday"));
				storeArray1.add(  rs.getString("Friday"));
			}

//			
//			for (String str : storeArray1) {
//
//				System.out.println("Item is: " + str);
//
//			}
			
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("\n exception ..." + e);
		}



	}
	
	public void clearDataFDT(String Name,String Day) 
	{
		

		try {
			connect();
		} catch (Exception e) {

			System.out.print("\n exception ..." + e);
		}
		
		try {
			sqlClearFDT = connection.prepareStatement(
					"UPDATE deliveries SET "+ Day + " = '"+""+"'"+
							"WHERE Delivery = '"+Name+"' ");
			result = sqlClearFDT.executeUpdate();

			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}  

			connection.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("\n exception ..." + e);
		}
		
	}
	public void clearData(String Name,String Operation) 
	{
		

		try {
			connect();
		} catch (Exception e) {

			System.out.print("\n exception ..." + e);
		}
		
		try {
			sqlClear= connection.prepareStatement(
					"UPDATE manage SET "+ Operation + " = '"+""+"'"+
							"WHERE Name = '"+Name+"' ");
			result = sqlClear.executeUpdate();

			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}  

			connection.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("\n exception ..." + e);
		}
		
	}
	public void clearADataFDT(String Day) 
	{
		

		try {
			connect();
		} catch (Exception e) {

			System.out.print("\n exception ..." + e);
		}
		System.out.print("\n clear." );

		try {
			sqlClearAFDT = connection.prepareStatement(
					"UPDATE deliveries SET "+ Day + " = '"+""+"'");
			result = sqlClearAFDT.executeUpdate();

			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}  

			connection.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("\n exception ..." + e);
		}
		
	}


	public ArrayList<String> getItemOp() {	
		return storeArray;
	}
	public ArrayList<String> getItemFDT() {	
		return storeArray1;
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
			sqlInsertOp.close();
			sqlInsertDT.close();
			sqlDeleteOp.close();
			sqlClearFDT.close();
			sqlClear.close();
			sqlClearAFDT.close();

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
