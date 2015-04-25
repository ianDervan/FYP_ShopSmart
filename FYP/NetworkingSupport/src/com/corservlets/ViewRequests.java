package com.corservlets;

import java.sql.Connection;
import java.sql.DriverManager;
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
	HashSet<String> users = new HashSet<String>();

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

			for (String str : users) {

				System.out.println("Item is: " + str);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}


	}
	public  ArrayList<String> getReqList() {	
		return requests;
	}
	public  HashSet<String> getUserList() {	
		return users;
	}



	public void connect() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(dbURL, username, password);
		connection.setAutoCommit( false );
	}
	private void close() {

		try {

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
