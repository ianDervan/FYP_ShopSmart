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
 			Toast.makeText(getApplicationContext(),"ARA SHTOP" , 
 					 Toast.LENGTH_LONG).show();
 		 }
		 	
	}
	
	@Override
	public void onBackPressed()
	{
		db.close();
	    finish();  
	}

}
