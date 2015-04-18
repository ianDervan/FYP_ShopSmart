package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;



public class readData {
	
	private Connection connection ;
	String dbURL = "jdbc:mysql://localhost:3306/shopsmart";
	String username = "root";
	String password = "";
	
	
	ResultSet rs;
	
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
