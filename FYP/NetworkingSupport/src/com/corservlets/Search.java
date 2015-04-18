package com.corservlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Search {
	
	private Connection connection;
	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";
	int result;
	
	Map<String,String> search = new HashMap<String,String>(); 
	public void getCoOrdinates(String itemName)
	{
		
		String x = null;
		String y = null;
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			String query= "SELECT searchX FROM barcodes " +
					"WHERE item = '"+itemName+"'";

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				x =  rs.getString("searchX");
			}
			
			search.put("searchX",x);
			
			String query2= "SELECT searchY FROM barcodes " +
					"WHERE item = '"+itemName+"'";

			stmt = connection.createStatement();

            rs = stmt.executeQuery(query2);
			if(rs.next())
			{
				y =  rs.getString("searchY");
			}
			search.put("searchY",y);

			System.out.println("stock is = \n" + x);
			System.out.println("stock is = \n" + y);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	
	public  String getSearch(String key) {	
		return search.get(key);
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
