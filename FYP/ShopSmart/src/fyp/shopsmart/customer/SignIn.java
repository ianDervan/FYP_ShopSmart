package fyp.shopsmart.customer;



import fyp.shopsmart.R;
import fyp.shopsmart.employee.EmployeeMenu;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class SignIn extends Activity {
	
	
	 EditText username;
	 EditText password;
	 
	 Button   btnSignIn;
	 
	 String temp,temp1;
	
	//String SDcardPath = Environment.getExternalStorageDirectory().getPath();
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign);
		
		username = (EditText)findViewById(R.id.editText1);
	    password = (EditText)findViewById(R.id.editText2);
	
	    
	    btnSignIn = (Button) findViewById(R.id.signIn);
	  
		
	    
	    btnSignIn.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View v) {
        
         		 
        		signIn();
        		
        		//dropTable();
				//insertSomeDbData();
				
        		 openDatabase();
			}


		});
	}
	
	public void signIn()
	{
	

	     temp =(username.getText().toString());
		 
		 
		 temp1 = (password.getText().toString());
		 
	
		
		 if(temp.equals("John") && temp1.equals("john"))
 		 {
 			Toast.makeText(getApplicationContext(),"John signed In" , 
				 Toast.LENGTH_LONG).show();
 			
 			
 			Intent menu = new Intent (SignIn.this,
					  EmployeeMenu.class);

 				startActivity(menu);
 			 
 		 }
 		 else if(temp.equals("Ian") && temp1.equals("id19"))
 		 {
 			Toast.makeText(getApplicationContext(),"Ian signed In" , 
				 Toast.LENGTH_LONG).show();
 		
 			
 			Intent menu = new Intent (SignIn.this,
					  EmployeeMenu.class);

				startActivity(menu);
 			

 			 
 		 }
 		 else if(temp.equals("Aisling") && temp1.equals("aisling20"))
 		 {
 			Toast.makeText(getApplicationContext(),"Aisling signed In" , 
				 Toast.LENGTH_LONG).show();
 		
 			
 			Intent menu = new Intent (SignIn.this,
					  EmployeeMenu.class);

				startActivity(menu);
 			 
 		 }
 		 else if(temp.equals("Sarah") && temp1.equals("sarah21"))
 		 {
 			Toast.makeText(getApplicationContext(),"sarah signed In" , 
				 Toast.LENGTH_LONG).show();
 			
 			
 			Intent menu = new Intent (SignIn.this,
					  EmployeeMenu.class);

				startActivity(menu);
				
 			 
 		 }
 		 else
 		 {
 			 
 		 }
		 
		
		
	}
	private void openDatabase() {
		try {
			// path to private memory:
			//String SDcardPath = "//storage//extSdCard";
			// -----------------------------------------------------------
			// this provides the path name to the SD card
			// String SDcardPath =
			// Environment.getExternalStorageDirectory().getPath();
		String SDcardPath = Environment.getExternalStorageDirectory().getPath();
			String myDbPath = SDcardPath + "/" + "Base.db";
			//txtMsg.append("\n-openDatabase - DB Path: " + myDbPath);

			db = SQLiteDatabase.openDatabase(myDbPath, null,
					SQLiteDatabase.CREATE_IF_NECESSARY);

			//txtMsg.append("\n-openDatabase - DB was opened");
			//txtMsg.append("\n-openDatabase - DB was opened");
		} catch (SQLiteException e) {
			//txtMsg.append("\nError openDatabase: " + e.getMessage());
			finish();
		}
	}// createDatabase

	private void insertSomeDbData() {
		// create table: myAdroidTable
		db.beginTransaction();
		try {
			// create table
			db.execSQL("create table books ("
					+ " booksID integer PRIMARY KEY autoincrement, "
					+ " name  text, " + " cost text, " +" bookLength text, " + " bookType text, " + " bookAuthor text );  ");
			// commit your changes
			db.setTransactionSuccessful();
			
			//txtMsg.append("\n-insertSomeDbData - Table was created");

		} catch (SQLException e1) {
			//txtMsg.append("\nError insertSomeDbData: " + e1.getMessage());
			finish();
		} finally {
			db.endTransaction();
		}

		
		db.beginTransaction();
		try {
			
			for(int i = 0; i < 5; i++){
				db.execSQL("insert into books(name, cost, bookLength, bookType, bookAuthor) "
						);
			}

	
			db.setTransactionSuccessful();
			//txtMsg.append("\n-insertSomeDbData - 3 rec. were inserted");
			
		} catch (SQLiteException e2) {
			//txtMsg.append("\nError insertSomeDbData: " + e2.getMessage());
			
		} finally {
			db.endTransaction();
		}

	}// insertSomeData

	private void dropTable() {
		// (clean start) action query to drop table

		try {
			db.execSQL("DROP TABLE IF EXISTS books;");
			//txtMsg.append("\n-dropTable - dropped!!");
		} catch (Exception e) {
			//txtMsg.append("\nError dropTable: " + e.getMessage());
			finish();
		}
	}
	
	@Override
	public void onBackPressed()
	{
		db.close();
	    finish();  
	}

}
