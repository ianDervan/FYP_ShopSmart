package com.corservlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;



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
	public void checkNew(String item,String name)
	{

		check = 0;
		String tableN;

		i = 0;
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			DatabaseMetaData data = connection.getMetaData();
			ResultSet rsTables = data.getColumns(null, null, "shoppinglists", name);
			if (rsTables.next()) {

				check = 1;
			}
			else
			{
				Global g = new Global();
				g.checkTableName = 0;


				String query= "ALTER TABLE shoppinglists ADD " +name+" VARCHAR(50) AFTER id;" ;

				Statement stmt = connection.createStatement();

				stmt.executeUpdate(query);
				
				insertData(item,name);

			}

			if(check == 1)
			{


				Global g = new Global();

				g.checkTableName = 1;

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void checkOld(String item,String ShopListName)
	{

		int noColumn = 0;
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			
			DatabaseMetaData data = connection.getMetaData();
			ResultSet rsTables = data.getColumns(null, null, "shoppinglists", ShopListName);
			if (rsTables.next()) {

				Global g = new Global();
				g.checkTableName1 = 0;
				
				if(!item.trim().equals(""))
				{
					System.out.println("INSERT");
					insertData(item,ShopListName);
				}

			}
			else
			{
				noColumn = 1;
			}

			if(noColumn == 1)
			{

				Global g = new Global();
				g.checkTableName1 = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void insertData(String data,String ShopListName)
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			sqlInsertData = connection.prepareStatement(
					"INSERT INTO shoppingLists ("+ShopListName+") VALUES ( '"+ data+"'  )"
					);


			result =  sqlInsertData.executeUpdate();
			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}      

			connection.commit();


			//System.out.println("price is = \n" + newPrice);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void getData(String ShopListName)
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			String query= "SELECT * FROM  shoppinglists WHERE  "+ShopListName+"  IS NOT NULL";

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				if(rs!=null)
				{
					shoplist.add(rs.getString(ShopListName));
				}

			}

			for (String str : shoplist) {

				System.out.println("Item is: " + str);

			}

			connection.commit();



			//System.out.println("price is = \n" + newPrice);
		} catch (SQLException e) {

			e.printStackTrace();
		}

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
