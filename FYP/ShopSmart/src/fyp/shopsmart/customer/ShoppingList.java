package fyp.shopsmart.customer;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import fyp.shopsmart.R;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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
	ArrayList<String> storeItem;
	ArrayList<String> price;
	ArrayList<String> quantity;
	
	SQLiteDatabase db;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_list);
		
		txtMsg = (TextView) findViewById(R.id.txtMsg);
		inputQuantity = (EditText) findViewById(R.id.quantity);
		btnClearText = (Button) findViewById(R.id.cleartext);
		btnAddItem = (Button) findViewById(R.id.addItem);
		
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
				
	      		}		
	 		});
	
		
		btnAddItem.setOnClickListener(new OnClickListener() {	
	      	public void onClick(View v) {

				String storeInput =textView.getText().toString();		
				String[] inputItem = storeInput.split("€");

					
				quantity.add(inputQuantity.getText().toString());


				 storeItem.add(inputItem[0]);
				 price.add("€"+inputItem[1]);
				 
				 for(String testp : price)
				 {
					 txtMsg.append(testp);
				 }
				 
				 openDatabase();
				 dropTable();
				 insertSomeDbData();

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
					+ " itemID integer PRIMARY KEY autoincrement, "
					+ " Item  text, " + " Price text, " +" Quantity text);");
			// commit your changes
			db.setTransactionSuccessful();

			txtMsg.append("\n-insertSomeDbData - Table was created");

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
	
}
