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
	private PreparedStatement sqlInsertNewFs;
	private PreparedStatement sqlDeleteItem;
	private PreparedStatement sqlSubmitNewItem;

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
		else if(checkTable==5)
		{

			insertDataB(list);
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

					sqlInsertjtable = connection.prepareStatement(
							"UPDATE rota SET Day = '"+list.get(index++)+"',John = '"+list.get(index++)+"',Ian = '"+ list.get(index++)+"',Sarah = '"+ list.get(index++)+"',Aishling = '"+ list.get(index++) +"'" + "WHERE id = '"+i+"'");
					result =  sqlInsertjtable.executeUpdate();


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
	public <T> void insertDataB( List<T> list)
	{

		try {

			connect();

		} catch (Exception e1) {


			e1.printStackTrace();
		}
		
		try {
			int rowCount = 0;
			Statement stmt1 = connection.createStatement();
			rs = stmt1.executeQuery("SELECT COUNT(*) FROM barcodes");;

			if(rs.next());
			{
				rowCount = rs.getInt(1);
				System.out.println(rowCount);
			}
			for(int index= 0;index<list.size();index++)
			{
				for(int i= 1;i<=rowCount;i++)
				{		

					sqlInsertjtable = connection.prepareStatement(
							"UPDATE barcodes SET Barcode = '"+list.get(index++)+"',Item= '"+list.get(index++)+"',Price = '"+list.get(index++)+"',Stock = '"+list.get(index++)+"',X = '"+list.get(index++)+"',Y= '"+list.get(index++)+"'" + "WHERE id = '"+i+"'");
					result =  sqlInsertjtable.executeUpdate();


				}
			}
			if ( result == 0 ) {
				connection.rollback(); 

			}      
			connection.commit();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.print("exception" + e);
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


					sqlInsertjtable = connection.prepareStatement(
							"UPDATE deliveries SET Delivery = '"+list.get(index++)+"',Monday = '"+list.get(index++)+"',Tuesday = '"+ list.get(index++)+"'"
									+ ",Wednesday = '"+ list.get(index++)+"',Thursday = '"+ list.get(index++) +"',Friday = '"+list.get(index++)+"'" + "WHERE id = '"+i+"'");
					result =  sqlInsertjtable.executeUpdate();


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

			int rowCount = 0;
			Statement stmt1 = connection.createStatement();
			rs = stmt1.executeQuery("SELECT COUNT(*) FROM orderlist");;

			if(rs.next());
			{
				rowCount = rs.getInt(1);
				System.out.println(rowCount);
			}

			for(int index= 0;index<list.size();index++)
			{
				for(int i= 1;i<=rowCount;i++)
				{		

					sqlInsertjtable = connection.prepareStatement(
							"UPDATE orderlist SET  ID='"+list.get(index++)+"',Item = '"+list.get(index++)+"', Qty = '"+list.get(index++)+"'"+ "WHERE ID = '"+i+"'");
					result =  sqlInsertjtable.executeUpdate();

				}
			}
			if ( result == 0 ) {
				connection.rollback(); 

			}      
			connection.commit();

		} catch (SQLException e) {
			System.out.print("\nSQLException aaa" + e);
		}
	}
	public void addNew(String itemTxt,String qtyTxt)
	{

		try {
			connect();
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		gui.newRow = 0;
		String id = null;


		try {

			String check =null;
			String query1= "SELECT Qty FROM orderlist " +
					"WHERE item = '"+ itemTxt+"'";

			Statement stmt1 = connection.createStatement();

			ResultSet rs1 = stmt1.executeQuery(query1);

			if(rs1.next())
			{
				check =  rs1.getString("Qty");
				gui.newRow= 1;
			}
			else
			{


				sqlInsertNewFs = connection.prepareStatement(
						"INSERT INTO orderlist (Item,Qty) VALUES ( '"+ itemTxt+"' , '"+   qtyTxt +"')" );
				result =sqlInsertNewFs.executeUpdate();

			}

			String query= "SELECT ID FROM orderlist " +
					"WHERE item = '"+ itemTxt+"'";

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				id =  rs.getString("ID");
			}


			if ( result == 0 ) {
				connection.rollback(); 

			}      

			gui.noOfRowsAdded= Integer.parseInt(id);

			connection.commit();  


		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	public void delteItemFS()
	{

		try {
			connect();
		} catch (Exception e1) {

			e1.printStackTrace();
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
	public void setUpNewItem(String item,String bar,String price,String stock,String x,String y) 
	{

		try {
			connect();
		} catch (Exception e) {

			e.printStackTrace();
		}
		gui.barcodeInDatabse=0;

		try {

			String query1= "SELECT Barcode FROM barcodes " +
					"WHERE barcode= '"+ bar+"'";

			Statement stmt1 = connection.createStatement();

			ResultSet rs1 = stmt1.executeQuery(query1);

			if(rs1.next())
			{
				gui.barcodeInDatabse=1;
				System.out.print("IN DATABSE" + gui.barcodeInDatabse);
				
			}
			else
			{
				System.out.print("NOT IN DATABSE" + gui.barcodeInDatabse);
				sqlSubmitNewItem = connection.prepareStatement(
						"INSERT INTO barcodes (  barcode, item, price, stock,X,Y) VALUES ( '"+bar+"' , '"+ item +"', '"+price +"', '"+stock +"', '"+x+"', '"+y +"' )" );
				result =sqlSubmitNewItem .executeUpdate();

			}
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

		connection.setAutoCommit( false );
	}
	private void close() {

		try {
			sqlInsertNewFs.close();
			sqlInsertjtable.close();
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
