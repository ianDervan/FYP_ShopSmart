package fyp.shopsmart.customer;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import fyp.shopsmart.R;
import fyp.shopsmart.employee.EmployeeMenu;
import fyp.shopsmart.employee.HttpUtils;
import fyp.shopsmart.employee.Users;
import android.R.layout;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class SignIn extends Activity {
	
	
	public static int checkIsSignedIn = 0;
	public static int checkIsSignedIn1 = 0;
	public static int checkIsSignedIn2 = 0;
	public static int checkIsSignedIn3 = 0;
	
	//public Flags flag;
	
	 EditText username;
	 EditText password;
	 Button   btnSignIn;
	 String temp,temp1;
	 
	 String timeC;
		 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign);
			
		//setTitle("ARE YA WELL");
		username = (EditText)findViewById(R.id.editText1);
	    password = (EditText)findViewById(R.id.editText2);
	    
	   
	    
	 //   flag = new Flags();

	    
	    btnSignIn = (Button) findViewById(R.id.signIn);
	    btnSignIn.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View v) {
                 		 
        		signIn();
        		         
			}
		});
	    
	    
	}
	
	
	public void signIn()
	{
	
		String message1;

		temp1 =(username.getText().toString());

		temp = (password.getText().toString());
	
		if(temp.equalsIgnoreCase("John") && temp1.equalsIgnoreCase("john20"))
		{
			message1 = "John Signed In";
			toast(message1);
			
			getTime();
			
			Intent menu = new Intent (SignIn.this,
					EmployeeMenu.class);
			
				menu.putExtra("john", 1);
				menu.putExtra("jtime", timeC);
				 //Toast.makeText(this, "timeNow" +timeC, Toast.LENGTH_LONG).show();
				
				startActivity(menu);

			
		}
		else if(temp.equalsIgnoreCase("Ian") && temp1.equalsIgnoreCase("id19"))
		{

			message1 = "Ian Signed In";
			toast(message1);
			
			getTime();
			

			Intent menu = new Intent (SignIn.this,
					
					EmployeeMenu.class);
			menu.putExtra("itime", timeC);
			menu.putExtra("ian", 1);
			
			
			startActivity(menu);
			

		}
		else if(temp.equalsIgnoreCase("Sarah") && temp1.equalsIgnoreCase("sarah21"))
		{
			message1 = "Sarah Signed In";
			toast(message1);
			
			getTime();
			
			Intent menu = new Intent (SignIn.this,
					EmployeeMenu.class);
			
			menu.putExtra("sarah", 1);
			menu.putExtra("stime", timeC);

			startActivity(menu);


		}
		else if(temp.equalsIgnoreCase("Aishling") && temp1.equalsIgnoreCase("Aishling22"))
		{
			message1 = "Aishling Signed In";
			toast(message1);
			
			getTime();
			
			Intent menu = new Intent (SignIn.this,
					EmployeeMenu.class);
			
			menu.putExtra("aishling", 1);
			menu.putExtra("atime", timeC);

			startActivity(menu);	
		}
		else
		{
			message1 = "Sorry Wrong Username or Password";
			toast(message1);
		}
		 	
	}
	public void toast(String message)
	{
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.customtoast, (ViewGroup)
		findViewById(R.id.toast_layout_root));
		TextView text = (TextView) layout.findViewById(R.id.toasttext);
		text.setText(message);
		Toast t = new Toast(getApplicationContext());
		t.setDuration(Toast.LENGTH_LONG);
		t.setView(layout);
		t.show();     
	}
	public String getTime()
	{
		 Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
		 timeC=sdf.format(cal.getTime());
		 
		 return timeC;
		  	  
	}
	
	@Override
	public void onBackPressed()
	{
	
	    finish();  
	}

}
