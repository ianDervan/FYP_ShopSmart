package fyp.shopsmart.customer;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import fyp.shopsmart.R;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingList extends Activity {
	
	AutoCompleteTextView textView;
	TextView txtMsg;
	EditText inputQuantity;
	Button  btnClearText;
	Button  btnAddItem;
	Button  btnHide;
	ArrayList<String> storeItem;
	ArrayList<String> price;
	ArrayList<String> quantity;
	
	SQLiteDatabase db;
	
	int sh;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_list);
		
		sh=0;
		
		txtMsg = (TextView) findViewById(R.id.txtMsg);
		inputQuantity = (EditText) findViewById(R.id.quantity);
		btnClearText = (Button) findViewById(R.id.cleartext);
		btnAddItem = (Button) findViewById(R.id.addItem);
		btnHide = (Button) findViewById(R.id.hide);
		
		storeItem = new ArrayList<String>();
		price = new ArrayList<String>();
		quantity = new ArrayList<String>();

	    textView = (AutoCompleteTextView) findViewById(R.id.autocomplete);
		String[] suggestedItems = getResources().getStringArray(R.array.suggestions_array);
		ArrayAdapter<String> adapter = 
		        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestedItems);
		textView.setAdapter(adapter);
		
		btnClearText.setOnClickListener(new OnClickListener() {	
	      	public void onClick(View v) {
	      		
				textView.setText("");
				txtMsg.setText("");
	      		}		
	 		});
		
		btnHide.setOnClickListener(new OnClickListener() {	
	      	public void onClick(View v) {
	      		
	      		
//				if(sh == 1)
//	      		{
//					InputMethodManager imm = (InputMethodManager)getSystemService(
//			      		      Context.INPUT_METHOD_SERVICE);
//			      		imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
//	      			
//	      			sh=0;
//	      			
//	      		}
	
	      			InputMethodManager imm = (InputMethodManager)getSystemService(
		  	      		      Context.INPUT_METHOD_SERVICE);
		      			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
		      			
		      			openDatabase();
		      			useRawQueryShowAll();
		      			
		      		
		      		
				
	      		}		
	 		});
		
	
		
		btnAddItem.setOnClickListener(new OnClickListener() {	
	      	public void onClick(View v) {
	      		
	      		String text =textView.getText().toString();
	      		
	      		
	      		if (text != null && text.trim().length() > 0) {
	      		    // your code
	      		

				String storeInput =textView.getText().toString();		
				String[] inputItem = storeInput.split("�");
			
				quantity.add(inputQuantity.getText().toString());

				 storeItem.add(inputItem[0]);
				 price.add("�"+inputItem[1]);
				 
				
				 openDatabase();
				 dropTable();
				 insertSomeDbData();
				 useRawQueryShowAll();
				
	
				 textView.setText("");
	      		}

	      		}		
	 		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shopping_list, menu);
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
			String myDbPath = SDcardPath + "/" + "shopingList.db";
		
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
			db.execSQL("create table shoppingList ("
					+ " ID integer PRIMARY KEY autoincrement, "
					+ " Item  text, " + " Price text, "+" Quantity text);");
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
			
			for(int i = 0; i < storeItem.size(); i++){
				db.execSQL("insert into shoppingList (Item,Price,Quantity) "
						+ " values ('"+ storeItem.get(i)+"', '" + price.get(i)+"', '" + quantity.get(i)+"');");
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
			db.execSQL("DROP TABLE IF EXISTS shoppingList;");
			//txtMsg.append("\n-dropTable - dropped!!");
		} catch (Exception e) {
			txtMsg.append("\nError dropTable: " + e.getMessage());
			finish();
		}
	}
	private void useRawQueryShowAll() {
		try {
			// hard-coded SQL select with no arguments
			String mySQL = "select * from shoppingList";
			Cursor c1 = db.rawQuery(mySQL, null);
			
			txtMsg.append("\nShow All" + showCursor(c1) );
			
		} catch (Exception e) {
			txtMsg.append("\nError useRawQuery1: " + e.getMessage());
			
		}
	}// useRawQuery1
	
	private String showCursor( Cursor cursor) {
		// show SCHEMA (column names & types)
		cursor.moveToPosition(-1); //reset cursor's top		
		String cursorData = "\nCursor: [";
		
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
		cursorData += "]";
		
		// now get the rows
		cursor.moveToPosition(-1); //reset cursor's top
		while (cursor.moveToNext()) {
			String cursorRow = "\n[";
			for (int i = 0; i < cursor.getColumnCount(); i++) {
				cursorRow += cursor.getString(i);
				if (i<cursor.getColumnCount()-1) 
					cursorRow +=  ", ";
			}
			cursorData += cursorRow + "]";
		}
		return cursorData + "\n";
	}
	private String getColumnType(Cursor cursor, int i) {
		try {
			//peek at a row holding valid data 
			cursor.moveToFirst(); 
			int result = cursor.getType(i);
			String[] types = {":NULL", ":INT", ":FLOAT", ":STR", ":BLOB", ":UNK" };
			//backtrack - reset cursor's top
			cursor.moveToPosition(-1);
			return types[result];
		} catch (Exception e) {
			return " ";
		}
	}

	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		finish();
	}
	
}
