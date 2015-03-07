package fyp.shopsmart.employee;

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

import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;


import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import fyp.shopsmart.customer.BarcodeScan;
import fyp.shopsmart.customer.MainActivity;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
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
	

	String timeC;
	String name;
	String senduser;
	
	int recieve = 0;
	
	
	
	 Map<String,String> time;

	String time1;
	//public Flags flag;
	
	
	Button btnTI;
	Button btnTO;
	Button btnBI;
	Button btnBO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_staff_hours);
		
		 timeIn = (TextView) findViewById(R.id.timeIn);
		 timeOut = (TextView) findViewById(R.id.timeOut);
		 breakIn = (TextView) findViewById(R.id.breakIn);
		 breakOut = (TextView) findViewById(R.id.breakOut);
		 
		 btnTI  = (Button) findViewById(R.id.btnTI);
		 btnTO  = (Button) findViewById(R.id.btnTO);
		 btnBI  = (Button) findViewById(R.id.btnBI);
		 btnBO  = (Button) findViewById(R.id.btnBO);
		 
		 time  = new HashMap<String,String>(); 
		 
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

		 
		 Intent intent = getIntent();	
			check = intent.getIntExtra("SignedIn",0);
			
			if(check == 2)
			{
				time.put("userName","Ian");
				time.put("TimeIn" ,null);
				time.put("TimeOut" ,null);
				time.put("BreakIn" ,null);
				time.put("BreakOut" ,null);
				recieve=1;
				
				send();
				
				 
			}		
			if(check == 1)
			{
				time.put("userName","John (Manager)");
				time.put("TimeIn" ,null);
				time.put("TimeOut" ,null);
				time.put("BreakIn" ,null);
				time.put("BreakOut" ,null);
				recieve=1;
				
				send();

			}		
			if(check == 3)
			{
				time.put("userName","Sarah");
				time.put("TimeIn" ,null);
				time.put("TimeOut" ,null);
				time.put("BreakIn" ,null);
				time.put("BreakOut" ,null);
				recieve=1;
				
				send();

			}		
			if(check == 3)
			{
				time.put("userName","Aishling");
				time.put("TimeIn" ,null);
				time.put("TimeOut" ,null);
				time.put("BreakIn" ,null);
				time.put("BreakOut" ,null);
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
	    				alertDialog("John (Manager)");
	    			}	
	    			if(check == 2)
	    			{
	    				time.put("userName","Ian");
	    				insertTime(2);
	    				alertDialog("Ian");
	    			}
	    			if(check == 3)
	    			{
	    				time.put("userName","Sarah");
	    				insertTime(2);
	    				alertDialog("Sarah");
	    			}
	    			if(check == 4)
	    			{
	    				time.put("userName","Aishling");
	    				insertTime(2);
	    				alertDialog("Aishling");
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
	}
	
	public void insertTime(int field)
	{
		if(field == 1 && timeIn.getText().toString().equals(""))
		{
			getTime();
			timeIn.append(timeC);
			Toast.makeText(this, "time", Toast.LENGTH_LONG).show();
			
			time.put("TimeIn" ,timeC);
			time.put("TimeOut" ,null);
			time.put("BreakIn" ,null);
			time.put("BreakOut" ,null);
			send();
			
			
		}
		if(field == 2 &&breakIn.getText().toString().equals(""))
		{
			getTime();
			timeOut.append(timeC);
			
			time.put("TimeIn" ,null);
			time.put("TimeOut" ,timeC);
			time.put("BreakIn" ,null);
			time.put("BreakOut" ,null);
			send();
			
		//	breakIn.setText("");
		//	breakOut.setText("");
		//	timeOut.setText("");
		//	timeIn.setText("");
		
			
		}
		if(field == 3 &&breakIn.getText().toString().equals(""))
		{
			getTime();
			breakIn.append(timeC);
			
			
			time.put("TimeIn" ,null);
			time.put("TimeOut" ,null);
			time.put("BreakIn" ,timeC);
			time.put("BreakOut" ,null);
			send();
		}
		if(field == 4 &&breakOut.getText().toString().equals(""))
		{
			getTime();
			breakOut.append(timeC);
			
			time.put("TimeIn" ,null);
			time.put("TimeOut" ,null);
			time.put("BreakIn" ,null);
			time.put("BreakOut" ,timeC);
			
			send();
			

			
		}
		
	}
	public void alertDialog(String user)
	{
		
		senduser =user;
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
					
					time.put("userName","Ian");
					time.put("TimeIn" ,"1");
					time.put("TimeOut" ,);
					time.put("BreakIn" ,"1");
					time.put("BreakOut" ,"1");
					
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
		
		 String st,ft,bi,bo;
		
		String url=  "http://192.168.0.29:8080/NetworkingSupport/servlet";
		JSONObject jsonSEND = new JSONObject(time);

		 try{
			 //String min1,max1,average;
				
			 String jsonString = HttpUtils.urlContentPost(url,"user", jsonSEND.toString());
//			 JSONObject jsonResult = new JSONObject(jsonString);
			 
			 JSONObject jsonResult = new JSONObject(jsonString);
			 
			 if (recieve == 1)
			 {
				 st = jsonResult.getString("start");
				 
				 ft = jsonResult.getString("finish");
				 
				 bi = jsonResult.getString("breakI");	
				 bo = jsonResult.getString("breakO");	
				 Toast.makeText(this, "recievied" + st + "\nrecievied" + ft +"\nrecievied" + bi +"\nrecievied" + bo, Toast.LENGTH_LONG).show();
					breakIn.append(bi);
					breakOut.append(bo);
					timeOut.append(ft);
					timeIn.append(st);
				 
				 recieve = 0;
				 
			 
			 } 

			 
		 }catch (JSONException e) {
			 Toast.makeText(this, "JSONEEXCEPTION" + e, Toast.LENGTH_LONG).show();
		 } catch (ClientProtocolException e) {
			 
			 Toast.makeText(this, "illeagal base url", Toast.LENGTH_LONG).show();
			
		} catch (IOException e) {
			Toast.makeText(this, "Server Error", Toast.LENGTH_LONG).show();
		
			
			
		}
	}
	
//	public void inserTime()
//	{
//			
//			Toast.makeText(this, "check" + check, Toast.LENGTH_LONG).show();
//			if(check == 1)
//			{
//				setTitle("ShopSmart"+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"John");
//				if( checkIsEntered == 0)
//				{
//					if( checkIsEntered1 == 1 || checkIsEntered2 == 1 || checkIsEntered3 == 1)
//					{
//						timeIn.setText("");
//					}
//					
//						
//				}
//				
//				checkIsEntered =1;
//			}		
//			if(check == 2)
//			{
//				setTitle("ShopSmart"+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  "+"Ian");
//				
//				if( checkIsEntered1 == 0)
//				{
//					if(checkIsEntered == 1  || checkIsEntered2 == 1 || checkIsEntered3 == 1)
//					{
//						timeIn.setText("");
//					}
//					
//						
//				}
//				checkIsEntered1=1;	
//				
//			}	
//			if(check == 3)
//			{
//				setTitle("ShopSmart"+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Sarah");
//				if( checkIsEntered2 == 0)
//				{
//					if(checkIsEntered == 1  || checkIsEntered1 == 1 || checkIsEntered3 == 1)
//					{
//						timeIn.setText("");
//					}
//	
//					
//					
//				}
//				
//				checkIsEntered2=1;
//	
//			}		
//			if(check == 4)
//			{
//				setTitle("ShopSmart"+ "\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Aishling");
//				if( checkIsEntered3 == 0)
//				{
//					if(checkIsEntered == 1  || checkIsEntered1 == 1 || checkIsEntered2 == 1)
//					{
//						timeIn.setText("");
//					}
//
//	
//				}
//				checkIsEntered3 = 1;
//			}
//			
//			
//			
//		}
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
