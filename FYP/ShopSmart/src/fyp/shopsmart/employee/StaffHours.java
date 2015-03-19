package fyp.shopsmart.employee;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;


import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import fyp.shopsmart.customer.BarcodeScan;
import fyp.shopsmart.customer.MainActivity;
import fyp.shopsmart.customer.SignIn;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StaffHours extends Activity {
	
	int check;
	
	Context context = this;
	TextView timeIn;
	TextView timeOut;
	TextView breakIn;
	TextView breakOut;
	
	SQLiteDatabase db;

	String timeC;
	String name;
	String senduser;
	
	String start;
	String finish;
	String bIn;
	String bOut;
	
	int recieve = 0;
	
	
	
	 Map<String,String> time;

	String time1;
	//public Flags flag;
	
	
	Button btnTI;
	Button btnTO;
	Button btnBI;
	Button btnBO;
	Button btnView;
	TextView txtMsg;
	
	ArrayList<String> days;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_staff_hours);
		
		 timeIn = (TextView) findViewById(R.id.timeIn);
		 timeOut = (TextView) findViewById(R.id.timeOut);
		 breakIn = (TextView) findViewById(R.id.breakIn);
		 breakOut = (TextView) findViewById(R.id.breakOut);
		 txtMsg = (TextView) findViewById(R.id.txtMsg);
		 
		 btnTI  = (Button) findViewById(R.id.btnTI);
		 btnTO  = (Button) findViewById(R.id.btnTO);
		 btnBI  = (Button) findViewById(R.id.btnBI);
		 btnBO  = (Button) findViewById(R.id.btnBO);
		 btnView  = (Button) findViewById(R.id.viewHours);
		 
		 time  = new HashMap<String,String>(); 
		 days = new ArrayList<String>();

		 
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

		 
		 Intent intent = getIntent();	
			check = intent.getIntExtra("SignedIn",0);
				
			if(check == 1)
			{
				time.put("userName","John (Manager)");
				time.put("TimeIn" ,"2");
				time.put("TimeOut" ,"2");
				time.put("BreakIn" ,"2");
				time.put("BreakOut" ,"2");

				recieve=1;
				
				send();

			}		
			
			if(check == 2)
			{
				time.put("userName","Ian");
				time.put("TimeIn" ,"2");
				time.put("TimeOut" ,"2");
				time.put("BreakIn" ,"2");
				time.put("BreakOut" ,"2");

				recieve=1;
				
				send();
				
				 
			}		
		
			if(check == 3)
			{
				time.put("userName","Sarah");
				time.put("TimeIn" ,"2");
				time.put("TimeOut" ,"2");
				time.put("BreakIn" ,"2");
				time.put("BreakOut" ,"2");
		
				recieve=1;
				
				send();
			}		
			if(check == 4)
			{
				time.put("userName","Aishling");
				time.put("TimeIn" ,"2");
				time.put("TimeOut" ,"2");
				time.put("BreakIn" ,"2");
				time.put("BreakOut" ,"2");

				recieve=1;
				
				send();
			}		
		  btnTI.setOnClickListener(new OnClickListener() {	
	    		public void onClick(View v) {
	
	    			if(check == 1)
	    			{
	    				time.put("userName","John (Manager)");
	    				insertTime(1);
	    			}	
	    			if(check == 2)
	    			{
	    				time.put("userName","Ian");
	    				insertTime(1);
	    			
	    			}
	    			if(check == 3)
	    			{
	    				time.put("userName","Sarah");
	    				insertTime(1);
	    			}
	    			if(check == 4)
	    			{
	    				time.put("userName","Aishling");
	    				insertTime(1);
	    			}
	    		}
	    	});
		  btnTO.setOnClickListener(new OnClickListener() {	
	    		public void onClick(View v) {

	    				
	    			if(check == 1)
	    			{
	    				time.put("userName","John (Manager)");
	    				insertTime(2);
	    				//alertDialog("John (Manager)");
	    			}	
	    			if(check == 2)
	    			{
	    				time.put("userName","Ian");
	    				insertTime(2);

	    			}
	    			if(check == 3)
	    			{
	    				time.put("userName","Sarah");
	    				insertTime(2);
	    				//alertDialog("Sarah");
	    			}
	    			if(check == 4)
	    			{
	    				time.put("userName","Aishling");
	    				insertTime(2);
	    				//alertDialog("Aishling");
	    			}
	    				    		
	    		}
	    	});
		  btnBI.setOnClickListener(new OnClickListener() {	
	    		public void onClick(View v) {
	    			
	    			if(check == 1)
	    			{
	    				time.put("userName","John (Manager)");
	    				insertTime(3);
	    			}		
	    			if(check == 2)
	    			{
	    				time.put("userName","Ian");
	    				insertTime(3);
	    			}
	    			if(check == 3)
	    			{
	    				time.put("userName","Sarah");
	    				insertTime(3);
	    			}
	    			if(check == 4)
	    			{
	    				time.put("userName","Aishling");
	    				insertTime(3);
	    			}
	    			
	    		}
	    	});
		  btnBO.setOnClickListener(new OnClickListener() {	
	    		public void onClick(View v) {
	    			
	    			if(check == 1)
	    			{
	    				time.put("userName","John (Manager)");
	    				insertTime(4);
	    			}	
	    			if(check == 2)
	    			{
	    				time.put("userName","Ian");
	    				insertTime(4);
	    			}
	    			if(check == 3)
	    			{
	    				time.put("userName","Sarah");
	    				insertTime(4);
	    			}
	    			if(check == 4)
	    			{
	    				time.put("userName","Aishling");
	    				insertTime(4);
	    			}
		
	    		}
	    	});
		  btnView.setOnClickListener(new OnClickListener() {	
		      	public void onClick(View v) {
		      		
		      		txtMsg.setText("");

					
		      		}		
		 		});
	}
	
	public void insertTime(int field)
	{

		if(field == 1 && timeIn.getText().toString().equals(""))
		{
			getTime();
			timeIn.append(timeC);
			start= timeC;

			time.put("TimeIn" ,timeC);
			time.put("TimeOut" ,"1");
			time.put("BreakIn" ,"1");
			time.put("BreakOut" ,"1");
			recieve=2;
			send();
				
		}
		if(field == 2 &&timeOut.getText().toString().equals(""))
		{
			getTime();
			timeOut.append(timeC);
			finish= timeC;

			time.put("TimeIn" ,"1");
			time.put("TimeOut" ,timeC);
			time.put("BreakIn" ,"1");
			time.put("BreakOut" ,"1");
			recieve=2;
			send();

			alertDialog();

		}
		if(field == 3 &&breakIn.getText().toString().equals(""))
		{
			getTime();
			breakIn.append(timeC);
			bIn = timeC;

			time.put("TimeIn" ,"1");
			time.put("TimeOut" ,"1");
			time.put("BreakIn" ,timeC);
			time.put("BreakOut" ,"1");
			recieve=2;
			send();
		}
		if(field == 4 &&breakOut.getText().toString().equals(""))
		{
			getTime();
		
			breakOut.append(timeC);
			bOut = timeC;

			
			time.put("TimeIn" ,"1");
			time.put("TimeOut" ,"1");
			time.put("BreakIn" ,"1");
			time.put("BreakOut" ,timeC);
			recieve=2;
			
			send();
		}
		
	}

	public void alertDialog()
	{
		

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		alertDialogBuilder.setTitle("Sign Out");

		alertDialogBuilder

		.setMessage("Click yes to to sign out")
		.setCancelable(false)
		.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				
					breakIn.setText("");
					breakOut.setText("");
					timeOut.setText("");
					timeIn.setText("");

					time.put("TimeIn" ,"3");
					time.put("TimeOut" ,"3");
					time.put("BreakIn" ,"3");
					time.put("BreakOut" ,"3");
					
					send();
			}
		})
		.setNegativeButton("No",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {

				dialog.cancel();
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Set IP Address</font>"));
		alertDialog.show();


		int titleDividerId = getResources().getIdentifier("android:id/titleDivider", "id", "android");
		View titleDivider = alertDialog.findViewById(titleDividerId);
		if (titleDivider != null)
			titleDivider.setBackgroundColor(Color.parseColor("#ED6F26"));
		

	}
	public void send()
	{
		
		 String st = null,ft=null,bi = null,bo = null;
		
		String url=  "http://192.168.1.103:8080/NetworkingSupport/servlet";
		JSONObject jsonSEND = new JSONObject(time);

		 try{
			 //String min1,max1,average;
				
			 String jsonString = HttpUtils.urlContentPost(url,"user", jsonSEND.toString());
//			 JSONObject jsonResult = new JSONObject(jsonString);
			 
			 if (recieve == 1)
			 {
				 JSONObject jsonResult = new JSONObject(jsonString);
				 
				 if( jsonResult!= null)
				 {
						 st = jsonResult.getString("start");
						 
						 ft = jsonResult.getString("finish");
						 
						 bi = jsonResult.getString("breakI");	
						 bo = jsonResult.getString("breakO");	
						 Toast.makeText(this, "RECIEVING", Toast.LENGTH_LONG).show();
							breakIn.append(bi);
							bIn= bi;
							breakOut.append(bo);
							bOut= bo;
							timeOut.append(ft);
							finish = ft;
							timeIn.append(st);
							start = st;
						 
						 recieve = 0;
				 }
			 }
		 }catch (JSONException e) {
			 Toast.makeText(this, "JSONEEXCEPTION" + e, Toast.LENGTH_LONG).show();
		 } catch (ClientProtocolException e) {
			 
			 Toast.makeText(this, "illeagal base url", Toast.LENGTH_LONG).show();
			
		} catch (IOException e) {
			Toast.makeText(this, "Server Error", Toast.LENGTH_LONG).show();
		
			
			
		}
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
