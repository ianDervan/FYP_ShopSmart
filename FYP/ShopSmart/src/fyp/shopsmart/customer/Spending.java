package fyp.shopsmart.customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Spending extends Activity {
	
	TextView txtMsg;
	SQLiteDatabase db;
	Button  btnShow;
	
	ArrayList<String> totalSpent;
	ArrayList<String> time;
	int day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending);
		
		txtMsg = (TextView) findViewById(R.id.txtMsg);
		btnShow = (Button) findViewById(R.id.show);
		totalSpent = new ArrayList<String>();
		time= new ArrayList<String>();
		
		 totalSpent.clear();
		
		
		 
		
		  
		 Calendar calendar = Calendar.getInstance();
		 int day = calendar.get(Calendar.DAY_OF_WEEK); 
		 
		 txtMsg.append(day + ""  + "\n");
		 

		

		
		 Intent myLocalIntent = getIntent();
		 time=myLocalIntent.getStringArrayListExtra("time");
		
		 totalSpent=myLocalIntent.getStringArrayListExtra("cost");
		 
		 for(String temp: totalSpent)
		 {
			// txtMsg.append(temp+"\n");
		 }
		 for(String temp1: time)
		 {
			 txtMsg.append(temp1+"\n");
		 }
		
		 

		btnShow.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				openDatabase();
				dropTable();
				insertSomeDbData();
				useRawQueryShowAll();
			}		
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spending, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private void openDatabase() {
		try {

			String SDcardPath = Environment.getExternalStorageDirectory().getPath();

				String myDbPath = SDcardPath + "/" + "spending.db";
				db = SQLiteDatabase.openDatabase(myDbPath, null,
						SQLiteDatabase.CREATE_IF_NECESSARY);
			
			

		} catch (SQLiteException e) {

			finish();
		}
	}
	private void insertSomeDbData() {
		
		db.beginTransaction();
		try {
			// create table
			db.execSQL("create table spending ("
					+ " Time, "
					+ " Sun text,"+ " Mon text, "+ " Tues text,"+ " Wed text,"+ " Thurs text,"+ " Fri text,"+ " Sat text);");
					
			// commit your changes
			db.setTransactionSuccessful();

		} catch (SQLException e1) {
			txtMsg.append("\nError insertSomeDbData: " + e1.getMessage());
			finish();
		} finally {
			db.endTransaction();
		}

		
		db.beginTransaction();
		try {
			
			   String na = "N/A";
				//db.execSQL("insert into spending (Time,Sun,Mon,Tues,Wed,Thurs,Fri,Sat) "
						//+ " values ('"+ na+"','"+ na+"', '" + na+"', '"+ na+"','"+ na+"', '" +na+"', '" +na+"','" +na+"');");	
			   
			  
			   
			   
			   for(int i=0;i<totalSpent.size();i++)
			   {
				   db.execSQL("insert into spending (Time,Fri) "
						   + " values ('"+time.get(i)+"','"+totalSpent.get(i)+"' );");
			   } 
			   
			
			db.setTransactionSuccessful();
			
			
		} catch (SQLiteException e2) {
			txtMsg.append("\nError insertSomeDbData: " + e2.getMessage());
			
		} finally {
			db.endTransaction();
		}

	}

	private void dropTable() {
		// (clean start) action query to drop table

		try {
			db.execSQL("DROP TABLE IF EXISTS spending;");
			//txtMsg.append("\n-dropTable - dropped!!");
		} catch (Exception e) {
			txtMsg.append("\nError dropTable: " + e.getMessage());
			finish();
		}
	}
	private void useRawQueryShowAll() {
		try {
			// hard-coded SQL select with no arguments
			String mySQL = "select * from spending";
			Cursor c1 = db.rawQuery(mySQL, null);
			
			txtMsg.append("" + showCursor(c1) );
			
		} catch (Exception e) {
			txtMsg.append("\nNo spending Done Yet");
			
		}
	}// useRawQuery1
	
	private String showCursor( Cursor cursor) {
		// show SCHEMA (column names & types)
		cursor.moveToPosition(-1); //reset cursor's top		
		String cursorData = "\n";
		
		try {
			// get column names
			String[] colName = cursor.getColumnNames();
			for(int i=0; i<colName.length; i++){
				String dataType = getColumnType(cursor, i);
				cursorData += colName[i] + dataType;
				
				if (i<colName.length-1){
					cursorData+= ", ";
				}
			}
		} catch (Exception e) {
			Log.e( "<<SCHEMA>>" , e.getMessage() );
		}
		cursorData += "";
		
		// now get the rows
		cursor.moveToPosition(-1); //reset cursor's top
		while (cursor.moveToNext()) {
			String cursorRow = "\n";
			for (int i = 0; i < cursor.getColumnCount(); i++) {
				cursorRow += cursor.getString(i);
				if (i<cursor.getColumnCount()-1) 
					cursorRow +=  ", ";
			}
			cursorData += cursorRow + "";
		}
		return cursorData + "\n";
	}
	private String getColumnType(Cursor cursor, int i) {
		try {
			//peek at a row holding valid data 
			cursor.moveToFirst(); 
			int result = cursor.getType(i);
			String[] types = { };
			//backtrack - reset cursor's top
			cursor.moveToPosition(-1);
			return types[result];
		} catch (Exception e) {
			return " ";
		}
	}
	
	@Override
	public void onBackPressed()
	{
		db.close();
	    finish();  
	}
}
