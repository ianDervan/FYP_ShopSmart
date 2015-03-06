package fyp.shopsmart.customer;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import java.util.TimeZone;


import android.app.Activity;

import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import fyp.shopsmart.R;
import fyp.shopsmart.employee.EmployeeMenu;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingList extends Activity {
	

	public static int shopListOk = 0;
	
	
	AutoCompleteTextView textView;
	TextView txtMsg;
	EditText inputQuantity;
	Button  btnClearText;
	Button  btnAddItem;
	Button  btnHide;
	Button  btnRemoveOne;
	Button  btnRemoveA;
	Button  btnSpending;
	ArrayList<String> storeItem;
	ArrayList<String> price;
	ArrayList<String> quantity;
	ArrayList<Double> totalSpent;
	ArrayList<String> sendtotalSpent;

	ArrayList<String> sendTime;
	SQLiteDatabase db;
		
	int sh;
	int n ;
	double sum;
	
	int v1;
	int ok;
	int mainOk;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_list);
		
		sh=0;
		sum=0;
		n=0;

		txtMsg = (TextView) findViewById(R.id.txtMsg);
		inputQuantity = (EditText) findViewById(R.id.quantity);
		btnClearText = (Button) findViewById(R.id.cleartext);
		btnAddItem = (Button) findViewById(R.id.addItem);
		btnHide = (Button) findViewById(R.id.hide);
		btnSpending = (Button) findViewById(R.id.spending);
		
		btnRemoveOne = (Button) findViewById(R.id.removeOne);
		btnRemoveA = (Button) findViewById(R.id.removeAll);
		
		storeItem = new ArrayList<String>();
		price = new ArrayList<String>();
		quantity = new ArrayList<String>();
		
		totalSpent = new ArrayList<Double>();
		sendtotalSpent = new ArrayList<String>();
		sendTime = new ArrayList<String>();

	    textView = (AutoCompleteTextView) findViewById(R.id.autocomplete);
		String[] suggestedItems = getResources().getStringArray(R.array.suggestions_array);
		ArrayAdapter<String> adapter = 
		        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestedItems);
		textView.setAdapter(adapter);
		
		txtMsg.setText("value" + shopListOk);
		
		sendtotalSpent.clear();
		totalSpent.clear();

	    Intent intent = getIntent();
	    ok = intent.getIntExtra("start",0);
	    

	    Intent mainIntent = getIntent();
	    mainOk = mainIntent.getIntExtra("main",0);
	   
			
	  	openDatabase();
		
		btnClearText.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				textView.setText("");
				txtMsg.setText("");
				
				
			}		
		});
		
		btnSpending.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
	
				  Intent spending = new Intent (ShoppingList.this,
						  Spending.class);
				  
				  Calendar cal = Calendar.getInstance(TimeZone.getDefault());
				  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
				  String timeC=sdf.format(cal.getTime());
				  
				  sendTime.add(timeC);
				  
				 
				  String temp = String.format("%.2f", sum);
				  
				  sendtotalSpent.add(String.valueOf(temp));
				  
				  spending.putStringArrayListExtra("cost", sendtotalSpent);
				  spending.putStringArrayListExtra("time",sendTime);
				  

				  startActivity(spending);
			}		
		});
		
		btnHide.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				InputMethodManager imm = (InputMethodManager)getSystemService(
						Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
				
			//	openDatabase();

				useRawQueryShowAll();
				//txtMsg.setText("");
	

			}		
		});
		
		btnRemoveOne.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
//				db.close();

				
				//dropTable();
				//openDatabase();
			useDeleteMethod();
			
			//db.close();
			textView.setText("");
				
			
	
			}
		});
		btnRemoveA.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//openDatabase();
			
			//	String myDbPath = SDcardPath + "/" + "shopingList.db"
		//	Context.deleteDatabase);
				
				//openDatabase();
				
			//	insertSomeDbData();
				
				dropTable();
				
				
				//db.close();

			txtMsg.setText("");
	
			}
		});
	
		btnAddItem.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				String text =textView.getText().toString();


				if (text != null && text.trim().length() > 0 ) 
	            {
					
					String storeInput =textView.getText().toString();		
					String[] inputItem = storeInput.split("€");
					
					quantity.add(inputQuantity.getText().toString());
					storeItem.add(inputItem[0]);
					price.add("€"+inputItem[1]);
					
					
					
					sum =Double.parseDouble(inputItem[1]);
					totalSpent.add(sum);

					for(int i = 0; i < totalSpent.size(); i++){
							
							  sum += totalSpent.get(i);
							
					}
				
				    txtMsg.append("Total spent\n" + sum);
				    
				    if(ok == 1 || mainOk==1 || shopListOk == 1)
				    {
				    	//openDatabase();
				    	insertData();
					    useRawQueryShowAll();
				    	
				    }
				    else
				    {
				    	//openDatabase();
						dropTable();
						insertSomeDbData();
					    useRawQueryShowAll();
				    	
				    }


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
			
			txtMsg.append("" + showCursor(c1) );
			
		} catch (Exception e) {
			txtMsg.append("\nShoping List is Empty");
			
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
	
	
	private void useDeleteMethod() {
		// using the 'delete' method to remove a group of friends
		// whose id# is between 2 and 7

		try {
				
			String[] whereArgs = { textView.getText().toString() };

			int recAffected = db.delete("shoppingList", "ID = ?",
					whereArgs);

			//txtMsg.append("\n" + recAffected);
			//db.close();
			
			showTable("shoppingList");
			
		} catch (Exception e) {
			txtMsg.append("\n-useDeleteMethod - Error: " + e.getMessage());
		}
	}
	
	private void showTable(String tableName) {
		try {
			String sql = "select * from " + tableName;
			Cursor c = db.rawQuery(sql, null);
			txtMsg.append(""  + showCursor(c));

		} catch (Exception e) {
			txtMsg.append("\nError showTable: " + e.getMessage());

		}
	}// useCursor1
	private void insertData() {
		
		db.beginTransaction();
		try {
			// create table
			
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

	@Override
	public void onBackPressed()
	{
		
		//ok=1;
		shopListOk = 1;
		
		if(ok == 1)
		{
			ok = 0;
			Intent barCode = new Intent (ShoppingList.this,BarcodeScan.class);
		
			barCode.putExtra("back", 1);
		
			startActivity(barCode);
		}
//		if(mainOk == 1 )
//		{
//			mainOk = 0;
//			
//			Intent main = new Intent (ShoppingList.this,MainActivity.class);	
//			
//			startActivity(main);
//		}
		

		db.close();
	    finish();  
	}
	
}
