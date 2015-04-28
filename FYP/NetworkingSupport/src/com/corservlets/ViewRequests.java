package com.corservlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

public class ViewRequests {

	private Connection connection;
	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";
	int result;

	ArrayList<String> requests = new ArrayList<String>();
	ArrayList<String> shoppingList = new ArrayList<String>();
	HashSet<String> users = new HashSet<String>();
	
	private PreparedStatement sqlInsertStatus;
	String daytime;

	public void getRequests()
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {


			String query= "SELECT * FROM  requesteditems";

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				requests.add(rs.getString("RequestedItems"));


			}

		} catch (SQLException e) {

			e.printStackTrace();
		}


	}
	public void getShopList(String userName)
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {


			String query= "SELECT * FROM  shoppinglists" +" Where UserName = '"+userName +"'";

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				shoppingList.add(rs.getString("ShoppingList"));


			}


		} catch (SQLException e) {

			e.printStackTrace();
		}


	}
	public void getDelivery(String userName)
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

		
			String query= "SELECT DayTime FROM  shoppinglists" +" Where UserName = '"+userName +"'";

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				daytime=(rs.getString("DayTime"));


			}


		} catch (SQLException e) {

			e.printStackTrace();
		}


	}
	public void getUsers()
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {


			String query= "SELECT * FROM  shoppinglists";

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				users.add(rs.getString("UserName"));

			}

//			for (String str : users) {
//
//				System.out.println("Item is: " + str);
//
//			}
		} catch (SQLException e) {

			e.printStackTrace();
		}


	}
	public void insertStatus(String status,String user)
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			

			sqlInsertStatus = connection.prepareStatement(
					"UPDATE shoppinglists SET Status = '"+status+"' " +
							"WHERE UserName = '"+user+"' ");
			result =  sqlInsertStatus.executeUpdate();
			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}      

			connection.commit();

		} catch (SQLException e) {

			System.out.println("Item is: " + e);
		}


	}
	public void delete(String user)
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			//System.out.println("Item is: " + user);

			sqlInsertStatus = connection.prepareStatement(
					"DELETE FROM shoppinglists WHERE UserName = '"+user+"' ");
			result =  sqlInsertStatus.executeUpdate();
			
	

			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}      


			connection.commit();

		} catch (SQLException e) {

			//System.out.println("Item is: " + e);
		}


	}
	public  ArrayList<String> getReqList() {	
		return requests;
	}
	public  ArrayList<String> getShopList() {	
		return shoppingList;
	}
	public  HashSet<String> getUserList() {	
		return users;
	}
	public  String getdayTime() {	
		return daytime;
	}



	public void connect() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(dbURL, username, password);
		connection.setAutoCommit( false );
	}
	private void close() {

		try {
			sqlInsertStatus.close();
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
