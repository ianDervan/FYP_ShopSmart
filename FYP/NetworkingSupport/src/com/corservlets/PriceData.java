package com.corservlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class PriceData {

	private Connection connection;
	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";

	String bar;
	String itemText;
	String itemTxt;

	String itemName;
	String price;
	String newPrice;
	
	String setUpName;
	String setUpBar;
	String setUpQ;
	String setUpP;
	
	String itemSetUp;
	
	String itemNameFD;

	private PreparedStatement sqlInsertNewPrice;
	private PreparedStatement sqlSubmitNewItem;
	private PreparedStatement sqlDeleteItem;

	int result;


	public void getItem(String barcode) 
	{
		bar= barcode;
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			String query= "SELECT item FROM barcodes " +
					"WHERE barcode = '"+bar +"'";

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				itemText=  rs.getString("Item");
			}



		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void getPrice(String item) 
	{
		itemName = item;
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			String query= "SELECT Price FROM barcodes " +
					"WHERE item = '"+itemName+"'";

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				price=  rs.getString("Price");
			}


			//System.out.println("price is = \n" + price);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void setNewPrice(String item,String newp) 
	{
		itemTxt = item;
		newPrice = newp;
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			sqlInsertNewPrice = connection.prepareStatement(
					"UPDATE barcodes  SET Price = '"+newPrice +"' " +
							"WHERE Item = '"+itemTxt+"' ");
			result =  sqlInsertNewPrice.executeUpdate();
			if ( result == 0 ) {
				connection.rollback(); // rollback update
			}      
			
			connection.commit();


			//System.out.println("price is = \n" + newPrice);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void setUpNewItem(String item,String bar,String p,String q) 
	{
	    setUpName = item;
		 setUpBar = bar;
		setUpQ = q;
		setUpP = p;
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {

			sqlSubmitNewItem = connection.prepareStatement(
			         "INSERT INTO barcodes ( barcode, item, price, stock) VALUES ( '"+ setUpBar+"' , '"+ setUpName +"', '"+setUpP +"', '"+setUpQ +"' )" );
			 result =sqlSubmitNewItem .executeUpdate();
	         
	         
	         if ( result == 0 ) {
	            connection.rollback(); 

	         }      
			connection.commit();


			
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void deleteItem(String item) 
	{
	    itemNameFD = item;
		
		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}


		try {


			sqlDeleteItem = connection.prepareStatement(
					"UPDATE barcodes SET barcode = '"+""+"',item = '"+""+"',price = '"+""+"',stock = '"+""+"' " +
							"WHERE item = '"+   itemNameFD +"' ");

			result = sqlDeleteItem.executeUpdate();
			
			
			

			if ( result == 0 ) {

				connection.rollback();

			} 
			connection.commit();


			
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public  String getItemText() {	
		return itemText;
	}
	public  String getItemPrice() {	
		return price;
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
			sqlSubmitNewItem.close();
			sqlInsertNewPrice.close();
			sqlDeleteItem.close();
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
