package com.corservlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;


public class ShoppingLists {
	private Connection connection;
	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";
	int result;
	int check = 0;
	int i;

	ArrayList<String> shoplist= new ArrayList<String>();

	private PreparedStatement sqlInsertData;
	
	String status;


	public void insertData(String userName,String dayTime,JSONArray js,String Status)
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}

		Global g = new Global();
		
		g.checkUser=0;
		
		try {

			String query1= "SELECT UserName FROM shoppinglists " +
					"WHERE UserName= '"+userName+"'";

			Statement stmt1 = connection.createStatement();

			ResultSet rs1 = stmt1.executeQuery(query1);

			if(rs1.next())
			{

				g.checkUser=1;

			}
			else
			{

				for(int i= 0;i<js.length();i++)
				{		

					try {
						sqlInsertData = connection.prepareStatement(
								"INSERT INTO shoppinglists(  UserName, DayTime,ShoppingList,Status) VALUES ( '"+userName+"' , '"+ dayTime +"', '"+js.get(i) +"', '"+Status +"'  )" );
						result = sqlInsertData .executeUpdate();
					} catch (JSONException e) {

						e.printStackTrace();
					}



				}

			}

			if ( result == 0 ) {
				connection.rollback(); 

			}      
			connection.commit();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void getStatus(String userName)
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}

		
		try {
			System.out.print("status ");

			String query1= "SELECT Status FROM shoppinglists " +
					"WHERE UserName= '"+userName+"'";

			Statement stmt1 = connection.createStatement();

			ResultSet rs1 = stmt1.executeQuery(query1);

			if(rs1.next())
			{

				status = rs1.getString("Status");

			}
			
			System.out.print("status " + status);


			if ( result == 0 ) {
				connection.rollback(); 

			}      
			
			
			connection.commit();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		

	}
	public String getStatusR()
	{
		return status;
	}

	public  ArrayList<String> getList() {	
		return shoplist;
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
			sqlInsertData.close();
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
