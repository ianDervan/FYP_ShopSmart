package com.corservlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Requests {


	private Connection connection;
	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";
	
	int result;
	
	private PreparedStatement  sqlrequest;
	public void insertRequest(String Item)
	{
		

			try {
				connect();
			} catch (Exception e) {

				e.printStackTrace();
			}


			try {

			    sqlrequest = connection.prepareStatement(
						"INSERT INTO requesteditems (RequestedItems) VALUES ( '"+Item+"')" );
				result = sqlrequest.executeUpdate();


				if ( result == 0 ) {
					connection.rollback(); 

				}      
				connection.commit();



			} catch (SQLException e) {

				e.printStackTrace();
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
			 sqlrequest.close();
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
