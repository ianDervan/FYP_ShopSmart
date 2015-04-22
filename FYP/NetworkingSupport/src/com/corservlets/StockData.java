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

public class StockData {

	private Connection connection;
	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";
	int result;

	String itemName;
	String itemTxt;
	String stock;
	String newStock;

	String addItemTxt;
	String addItemStock;
	ArrayList<String> stockdata = new ArrayList<String>();
	ArrayList<String> stockdataQty = new ArrayList<String>();

	private PreparedStatement sqlInsertNewStock;
	private PreparedStatement sqlAddItem;
	public void getStock(String item) 
	{
		itemName = item;
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			String query= "SELECT stock FROM barcodes " +
					"WHERE item = '"+itemName+"'";

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				stock=  rs.getString("stock");
			}


			//System.out.println("stock is = \n" + stock);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void setNewStock(String item,String newp) 
	{
		itemTxt = item;
		newStock = newp;
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			sqlInsertNewStock = connection.prepareStatement(
					"UPDATE barcodes  SET stock = '"+newStock +"' " +
							"WHERE Item = '"+itemTxt+"' ");
			result =  sqlInsertNewStock.executeUpdate();
			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}      

			connection.commit();


			//System.out.println("stock is = \n" + newPrice);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void addItem(String item,String bar) 
	{
		addItemTxt = item;
		addItemStock = bar;

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			sqlAddItem = connection.prepareStatement(
					"INSERT INTO orderlist (Item,Qty) VALUES ( '"+addItemTxt+"' , '"+  addItemStock +"')" );
			result =sqlAddItem .executeUpdate();


			if ( result == 0 ) {
				connection.rollback(); 

			}      
			connection.commit();



		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void getItem() 
	{
		int i = 0;
		String getItemtxt = null;

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			
				String query= "SELECT * FROM  orderlist";

				Statement stmt = connection.createStatement();

				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{
					stockdata.add(rs.getString("item"));
					stockdataQty.add(rs.getString("quantity"));
					
				}
				
			

//			for (String str : stockdata) {
//
//				System.out.println("Item is: " + str);
//
//			}
//			for (String str : stockdataQty) {
//
//				System.out.println("Item is: " + str);
//
//			}

			connection.commit();



		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void clearTable() 
	{
		
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {
	
			
	     

			Statement stmt = connection.createStatement();

			
			
			String sql = "TRUNCATE orderlist";
		    // Execute deletion
		    stmt.executeUpdate(sql);
	      
	      connection.commit();



	} catch (SQLException e) {

		e.printStackTrace();
	}
		
	}
	public  ArrayList<String> getItemName() {	
		return stockdata;
	}
	public ArrayList<String> getItemStk() {	
		return stockdataQty;
	}
	public  String getStockNo() {	
		return stock;
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
			sqlInsertNewStock.close();
			sqlAddItem.close();
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
