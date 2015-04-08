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
import fyp.shopsmart.customer.Search.backgroundAsyncTask;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.renderscript.Type;
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
	TextView txtMsg;
	TextView timeIn;
	TextView timeOut;
	TextView breakIn;
	TextView breakOut;
	
	TextView mon1;
	TextView mon2;
	TextView mon3;
	TextView mon4;
	TextView tues1;
	TextView tues2;
	TextView tues3;
	TextView tues4;
	TextView wed1;
	TextView wed2;
	TextView wed3;
	TextView wed4;
	TextView thurs1;
	TextView thurs2;
	TextView thurs3;
	TextView thurs4;
	TextView fri1;
	TextView fri2;
	TextView fri3;
	TextView fri4;
	TextView sat1;
	TextView sat2;
	TextView sat3;
	TextView sat4;
	TextView sun1;
	TextView sun2;
	TextView sun3;
	TextView sun4;

	SQLiteDatabase db;
	String timeC;

	int recieve;
	Button btnTI;
	Button btnTO;
	Button btnBI;
	Button btnBO;
	Button btnView;
	
	Button btnClearTable;
	Button btnClearHours;
	
	
	ArrayList<String> days;
	Map<String,String> time;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_staff_hours);
		
		recieve = 0;
		
		 timeIn = (TextView) findViewById(R.id.timeIn);
		 timeOut = (TextView) findViewById(R.id.timeOut);
		 breakIn = (TextView) findViewById(R.id.breakIn);
		 breakOut = (TextView) findViewById(R.id.breakOut);
		 txtMsg = (TextView) findViewById(R.id.txtMsg);
		 mon1 = (TextView) findViewById(R.id.mon1);
		 mon2 = (TextView) findViewById(R.id.mon2);
		 mon3 = (TextView) findViewById(R.id.mon3);
		 mon4 = (TextView) findViewById(R.id.mon4);
		 tues1 = (TextView) findViewById(R.id.tues1);
		 tues2 = (TextView) findViewById(R.id.tues2);
		 tues3 = (TextView) findViewById(R.id.tues3);
		 tues4 = (TextView) findViewById(R.id.tues4);
		 wed1 = (TextView) findViewById(R.id.wed1);
		 wed2 = (TextView) findViewById(R.id.wed2);
		 wed3 = (TextView) findViewById(R.id.wed3);
		 wed4 = (TextView) findViewById(R.id.wed4);
		 thurs1 = (TextView) findViewById(R.id.thurs1);
		 thurs2 = (TextView) findViewById(R.id.thurs2);
		 thurs3 = (TextView) findViewById(R.id.thurs3);
		 thurs4 = (TextView) findViewById(R.id.thurs4);
		 fri1 = (TextView) findViewById(R.id.fri1);
		 fri2 = (TextView) findViewById(R.id.fri2);
		 fri3 = (TextView) findViewById(R.id.fri3);
		 fri4 = (TextView) findViewById(R.id.fri4);
		 sat1 = (TextView) findViewById(R.id.sat1);
		 sat2 = (TextView) findViewById(R.id.sat2);
		 sat3 = (TextView) findViewById(R.id.sat3);
		 sat4 = (TextView) findViewById(R.id.sat4);
		 sun1 = (TextView) findViewById(R.id.sun1);
		 sun2 = (TextView) findViewById(R.id.sun2);
		 sun3 = (TextView) findViewById(R.id.sun3);
		 sun4 = (TextView) findViewById(R.id.sun4); 
		 
		 btnTI  = (Button) findViewById(R.id.btnTI);
		 btnTO  = (Button) findViewById(R.id.btnTO);
		 btnBI  = (Button) findViewById(R.id.btnBI);
		 btnBO  = (Button) findViewById(R.id.btnBO);
		 btnView  = (Button) findViewById(R.id.refresh);
		 btnClearHours = (Button) findViewById(R.id.btnclearday);
		 btnClearTable = (Button) findViewById(R.id.btncleartable);
		 time  = new HashMap<String,String>(); 
		 days = new ArrayList<String>();

		 
		 //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			//StrictMode.setThreadPolicy(policy);

		 Intent intent = getIntent();	
			check = intent.getIntExtra("SignedIn",0);
				
			if(check == 1)
			{
				time.put("userName","john(manager)");
				time.put("TimeIn" ,"2");
				time.put("TimeOut" ,"2");
				time.put("BreakIn" ,"2");
				time.put("BreakOut" ,"2");

				recieve=1;
				
				Send s = new Send();
				 s.execute();

			}		
			
			if(check == 2)
			{
				time.put("userName","ian");
				time.put("TimeIn" ,"2");
				time.put("TimeOut" ,"2");
				time.put("BreakIn" ,"2");
				time.put("BreakOut" ,"2");

				recieve=1;
				
				Send s = new Send();
				 s.execute();	 
			}		
		
			if(check == 3)
			{
				time.put("userName","sarah");
				time.put("TimeIn" ,"2");
				time.put("TimeOut" ,"2");
				time.put("BreakIn" ,"2");
				time.put("BreakOut" ,"2");
		
				recieve=1;
				
				Send s = new Send();
				 s.execute();
			}		
			if(check == 4)
			{
				time.put("userName","aishling");
				time.put("TimeIn" ,"2");
				time.put("TimeOut" ,"2");
				time.put("BreakIn" ,"2");
				time.put("BreakOut" ,"2");

				recieve=1;
				
				Send s = new Send();
				 s.execute();
			}		
		  btnTI.setOnClickListener(new OnClickListener() {	
	    		public void onClick(View v) {
	
	    			if(check == 1)
	    			{
	    				time.put("userName","john(manager)");
	    				insertTime(1);
	    			}	
	    			if(check == 2)
	    			{
	    				time.put("userName","ian");
	    				insertTime(1);
	    			
	    			}
	    			if(check == 3)
	    			{
	    				time.put("userName","sarah");
	    				insertTime(1);
	    			}
	    			if(check == 4)
	    			{
	    				time.put("userName","aishling");
	    				insertTime(1);
	    			}
	    		}
	    	});
		  btnTO.setOnClickListener(new OnClickListener() {	
	    		public void onClick(View v) {

	    				
	    			if(check == 1)
	    			{
	    				time.put("userName","john(manager)");
	    				insertTime(2);
	    				//alertDialog("John (Manager)");
	    			}	
	    			if(check == 2)
	    			{
	    				time.put("userName","ian");
	    				insertTime(2);

	    			}
	    			if(check == 3)
	    			{
	    				time.put("userName","sarah");
	    				insertTime(2);
	    				//alertDialog("Sarah");
	    			}
	    			if(check == 4)
	    			{
	    				time.put("userName","aishling");
	    				insertTime(2);
	    				//alertDialog("Aishling");
	    			}
	    				    		
	    		}
	    	});
		  btnBI.setOnClickListener(new OnClickListener() {	
	    		public void onClick(View v) {
	    			
	    			if(check == 1)
	    			{
	    				time.put("userName","john(manager)");
	    				insertTime(3);
	    			}		
	    			if(check == 2)
	    			{
	    				time.put("userName","ian");
	    				insertTime(3);
	    			}
	    			if(check == 3)
	    			{
	    				time.put("userName","sarah");
	    				insertTime(3);
	    			}
	    			if(check == 4)
	    			{
	    				time.put("userName","aishling");
	    				insertTime(3);
	    			}
	    			
	    		}
	    	});
		  btnBO.setOnClickListener(new OnClickListener() {	
	    		public void onClick(View v) {
	    			
	    			if(check == 1)
	    			{
	    				time.put("userName","john(manager)");
	    				insertTime(4);
	    			}	
	    			if(check == 2)
	    			{
	    				time.put("userName","ian");
	    				insertTime(4);
	    			}
	    			if(check == 3)
	    			{
	    				time.put("userName","sarah");
	    				insertTime(4);
	    			}
	    			if(check == 4)
	    			{
	    				time.put("userName","aishling");
	    				insertTime(4);
	    			}
		
	    		}
	    	});
		  btnView.setOnClickListener(new OnClickListener() {	
		      	public void onClick(View v) {
		      		
		      		finish();
		      		startActivity(getIntent());
		      		}		
		 		});
		  
		  btnClearTable.setOnClickListener(new OnClickListener() {	

				public void onClick(View v) {
		      		
		      		time.put("TimeIn" ,"4");
					time.put("TimeOut" ,"4");
					time.put("BreakIn" ,"4");
					time.put("BreakOut" ,"4");
					
					recieve=2;
					//timeIn.append("IN");
					Send s = new Send();
					 s.execute();
					 
					 finish();
			      		startActivity(getIntent());
							 
		      		}		
		 		});
	}
	
	
	public void insertTime(int field)
	{

		if(field == 1 && timeIn.getText().toString().equals(""))
		{
			getTime();
			timeIn.append(timeC);


			time.put("TimeIn" ,timeC);
			time.put("TimeOut" ,"1");
			time.put("BreakIn" ,"1");
			time.put("BreakOut" ,"1");
			recieve=2;
			
			Send s = new Send();
			 s.execute();
		}
		if(field == 2 &&timeOut.getText().toString().equals(""))
		{
			getTime();
			


			time.put("TimeIn" ,"1");
			time.put("TimeOut" ,timeC);
			time.put("BreakIn" ,"1");
			time.put("BreakOut" ,"1");
			recieve=2;
			Send s = new Send();
			 s.execute();

			alertDialog();

		}
		if(field == 3 &&breakIn.getText().toString().equals(""))
		{
			
			getTime();
			breakIn.append(timeC);


			time.put("TimeIn" ,"1");
			time.put("TimeOut" ,"1");
			time.put("BreakIn" ,timeC);
			time.put("BreakOut" ,"1");
			recieve=2;
			Send s = new Send();
			 s.execute();
		}
		if(field == 4 &&breakOut.getText().toString().equals(""))
		{
			getTime();
		
			breakOut.append(timeC);


			
			time.put("TimeIn" ,"1");
			time.put("TimeOut" ,"1");
			time.put("BreakIn" ,"1");
			time.put("BreakOut" ,timeC);
			recieve=2;
			
			Send s = new Send();
			 s.execute();
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
				
				timeOut.append(timeC);
				
					breakIn.setText("");
					breakOut.setText("");
					timeOut.setText("");
					timeIn.setText("");

					time.put("TimeIn" ,"3");
					time.put("TimeOut" ,"3");
					time.put("BreakIn" ,"3");
					time.put("BreakOut" ,"3");
					
					recieve=2;
					Send s = new Send();
					 s.execute();
					    	
			}
		})
		.setNegativeButton("No",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {

				dialog.cancel();
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Sign Out</font>"));
		alertDialog.show();


		int titleDividerId = getResources().getIdentifier("android:id/titleDivider", "id", "android");
		View titleDivider = alertDialog.findViewById(titleDividerId);
		if (titleDivider != null)
			titleDivider.setBackgroundColor(Color.parseColor("#ED6F26"));
		

	}
	public class Send extends
	AsyncTask< ArrayList<HashMap<String,String>>,Void,Map<String, String>>
	{

		String st = null,ft=null,bi = null,bo = null;
		String smon1;

		IPAddress ip = new IPAddress();
		
		String url=  ip.getIPAddress();
		JSONObject jsonSEND = new JSONObject(time);

		protected Map<String, String> doInBackground(ArrayList<HashMap<String, String>>...params) 
		{
			 Map<String,String> rTime  = new HashMap<String,String>(); 
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
						
						//bo =jsonResult.g
						//recieve = 0;
						//jsonResult.getJSONArray(name)

						rTime.put("st", st);
						rTime.put("ft", ft);
						rTime.put("bi", bi);
						rTime.put("bo", bo);
						
						rTime.put("mon1",jsonResult.getString("mon1"));
						rTime.put("mon2",jsonResult.getString("mon2"));
						rTime.put("mon3",jsonResult.getString("mon3"));
						rTime.put("mon4",jsonResult.getString("mon4"));
						
						rTime.put("tues1",jsonResult.getString("tues1"));
						rTime.put("tues2",jsonResult.getString("tues2"));
						rTime.put("tues3",jsonResult.getString("tues3"));
						rTime.put("tues4",jsonResult.getString("tues4"));
						
						rTime.put("wed1",jsonResult.getString("wed1"));
						rTime.put("wed2",jsonResult.getString("wed2"));
						rTime.put("wed3",jsonResult.getString("wed3"));
						rTime.put("wed4",jsonResult.getString("wed4"));

						rTime.put("thurs1",jsonResult.getString("thurs1"));
						rTime.put("thurs2",jsonResult.getString("thurs2"));
						rTime.put("thurs3",jsonResult.getString("thurs3"));
						rTime.put("thurs4",jsonResult.getString("thurs4"));

						rTime.put("fri1",jsonResult.getString("fri1"));
						rTime.put("fri2",jsonResult.getString("fri2"));
						rTime.put("fri3",jsonResult.getString("fri3"));
						rTime.put("fri4",jsonResult.getString("fri4"));

						rTime.put("fri1",jsonResult.getString("fri1"));
						rTime.put("fri2",jsonResult.getString("fri2"));
						rTime.put("fri3",jsonResult.getString("fri3"));
						rTime.put("fri4",jsonResult.getString("fri4"));

						rTime.put("sat1",jsonResult.getString("sat1"));
						rTime.put("sat2",jsonResult.getString("sat2"));
						rTime.put("sat3",jsonResult.getString("sat3"));
						rTime.put("sat4",jsonResult.getString("sat4"));
						
						rTime.put("sun1",jsonResult.getString("sun1"));
						rTime.put("sun2",jsonResult.getString("sun2"));
						rTime.put("sun3",jsonResult.getString("sun3"));
						rTime.put("sun4",jsonResult.getString("sun4"));

						
					}
				}
			}catch (JSONException e) {
				// Toast.makeText(this, "JSONEEXCEPTION" + e, Toast.LENGTH_LONG).show();
			} catch (ClientProtocolException e) {

				// Toast.makeText(this, "illeagal base url", Toast.LENGTH_LONG).show();

			} catch (IOException e) {
				//Toast.makeText(this, "Server Error", Toast.LENGTH_LONG).show();

			}
			
			
		
				return rTime;
		
		}

		protected void onPostExecute(Map<String, String> result) {
    		//super.onPostExecute(result);
    		if(!result.isEmpty())
    		{
    			//if(!result.get("st").equals("5") && !result.get("ft").equals("5"));
    			//{
		    		timeIn.append(result.get("st"));
		    		timeOut.append(result.get("ft"));
		    		breakIn.append(result.get("bi"));
		    		breakOut.append(result.get("bo"));
    			//}
    			
	 		     mon1.append(result.get("mon1"));
	    		 mon2.append(result.get("mon2"));
	    		 mon3.append(result.get("mon3"));
	    		 mon4.append(result.get("mon4"));
	    		 tues1.append(result.get("tues1")) ;
	    		 tues2.append(result.get("tues2"));
	    		 tues3.append(result.get("tues3"));
	    		 tues4.append(result.get("tues4"));
	    		 wed1.append(result.get("wed1"));
	    		 wed2.append(result.get("wed2"));
	    		 wed3.append(result.get("wed3"));
	    		 wed4.append(result.get("wed4"));
	    		 thurs1.append(result.get("thurs1"));
	    		 thurs2.append(result.get("thurs2"));
	    		 thurs3.append(result.get("thurs3"));
	    		 thurs4.append(result.get("thurs4"));
	    		 fri1.append(result.get("fri1"));
	    		 fri2.append(result.get("fri2"));
	    		 fri3.append(result.get("fri3"));
	    		 fri4.append(result.get("fri4"));
	    		 sat1.append(result.get("sat1"));
	    		 sat2.append(result.get("sat2"));
	    		 sat3.append(result.get("sat3"));
	    		 sat4.append(result.get("sat4"));
	    		 sun1.append(result.get("sun1"));
	    		 sun2.append(result.get("sun2"));
	    		 sun3.append(result.get("sun3"));
	    		 sun4.append(result.get("sun4")); 
	    			
    		}
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
