package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



public class readData {

	private Connection connection ;
	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";
	int result;

	private PreparedStatement sqlInsertjtable;

	ResultSet rs;

	GUI gui;

	public  DefaultTableModel insertData(String table,int startColumn,int lastColumn)
			throws SQLException {

		try {

			connect();

		} catch (Exception e1) {


			e1.printStackTrace();
		}

		String query= "SELECT * FROM  "+table+"";

		Statement stmt = connection.createStatement();

		rs = stmt.executeQuery(query);

		ResultSetMetaData metaData = rs.getMetaData();

		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = startColumn; column < lastColumn; column++) {

			columnNames.add(metaData.getColumnName(column));

		}

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();

			for (int columnIndex =  startColumn; columnIndex < lastColumn; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);

		}

		return new DefaultTableModel(data, columnNames);

	}

	public void getTable (JTable table,int checkTable) {


		TableModel dtm = table.getModel();
		int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0 ; i < nRow ; i++)
		{
			for (int j = 0 ; j < nCol ; j++)
			{
				tableData[i][j] = dtm.getValueAt(i,j);
			}

		}

		convert(tableData,checkTable);
	}
	public <T> void convert(T[][] twoDArray,int checkTable) {
		List<T> list = new ArrayList<T>();
		for (T[] array : twoDArray) {
			list.addAll(Arrays.asList(array));
		}

		System.out.println(Arrays.toString(list.toArray()));

		if(checkTable==1)
		{

			insertDataSH(list);
		}
		else if(checkTable==2)
		{

			insertDataM(list);
		}
		else if(checkTable==3)
		{

			insertDataD(list);
		}
		else if(checkTable==4)
		{

			insertDataOL(list);
		}


	}
	public <T> void insertDataSH( List<T> list)
	{

		try {

			connect();

		} catch (Exception e1) {


			e1.printStackTrace();
		}

		try {
			for(int index= 0;index<35;index++)
			{
				for(int i= 1;i<8;i++)
				{		
					//System.out.print("id" + i);
					//System.out.print("\nINDEX" + index);

					sqlInsertjtable = connection.prepareStatement(
							"UPDATE rota SET Day = '"+list.get(index++)+"',John = '"+list.get(index++)+"',Ian = '"+ list.get(index++)+"',Sarah = '"+ list.get(index++)+"',Aishling = '"+ list.get(index++) +"'" + "WHERE id = '"+i+"'");
					result =  sqlInsertjtable.executeUpdate();

					System.out.print("\nAFTER" + index);
				}
			}
			if ( result == 0 ) {
				connection.rollback(); 

			}      
			connection.commit();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public <T> void insertDataM( List<T> list)
	{

		try {
			connect();
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		try {
			for(int index= 0;index<20;index++)
			{
				for(int i= 1;i<5;i++)
				{		


					sqlInsertjtable = connection.prepareStatement(
							"UPDATE manage SET Name = '"+list.get(index++)+"',Tills = '"+list.get(index++)+"',Deli = '"+ list.get(index++)+"',Stock = '"+ list.get(index++)+"',Store = '"+ list.get(index++) +"'" + "WHERE id = '"+i+"'");
					result =  sqlInsertjtable.executeUpdate();

					System.out.print("\nAFTER" + index);
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
	public <T> void insertDataD( List<T> list)
	{

		try {
			connect();
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		try {

			for(int index= 0;index<30;index++)
			{
				for(int i= 1;i<6;i++)
				{		
					System.out.print("id" + i);
					System.out.print("\nINDEX" + index);

					sqlInsertjtable = connection.prepareStatement(
							"UPDATE deliveries SET Delivery = '"+list.get(index++)+"',Monday = '"+list.get(index++)+"',Tuesday = '"+ list.get(index++)+"'"
									+ ",Wednesday = '"+ list.get(index++)+"',Thursday = '"+ list.get(index++) +"',Friday = '"+list.get(index++)+"'" + "WHERE id = '"+i+"'");
					result =  sqlInsertjtable.executeUpdate();

					System.out.print("\nAFTER" + index);
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
	public <T> void insertDataOL( List<T> list)
	{

		try {
			connect();
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		try {

			for(int index= 0;index<list.size();index++)
			{
				for(int i= 1;i<20;i++)
				{		
					System.out.print("id" + i);
					System.out.print("\nINDEX" + index);

					sqlInsertjtable = connection.prepareStatement(
							"UPDATE orderlist SET id='"+list.get(index++)+"', Qty = '"+list.get(index++)+"',Item = '"+list.get(index++)+"'"+ "WHERE id = '"+i+"'");
					result =  sqlInsertjtable.executeUpdate();

					System.out.print("\nAFTER" + index);
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
	public void addRow()
	{

		try {
			connect();
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		String lastid = null;
		
		int no;
		int no1 = 0;
		System.out.print("\nADD ITEM");

		try {

			sqlInsertjtable = connection.prepareStatement(
					"INSERT INTO orderlist (Item,Qty) VALUES ( '"+ ""+"' , '"+ ""+"')" );
			result =  sqlInsertjtable.executeUpdate();
			

			String query= "SELECT id FROM orderlist " +
					"WHERE Item = '"+""+"'";

			Statement stmt = connection.createStatement();
			
			String stock = null;

			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
			   stock = rs.getString("id");
			}
			
			gui.noOfRowsAdded=Integer.parseInt(stock);
	        
	        System.out.print("\nLastNumber" + stock);
			      
			connection.commit();

		} catch (SQLException e) {

			e.printStackTrace();
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
			sqlInsertjtable.close();
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
