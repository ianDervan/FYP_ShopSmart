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
import org.json.JSONArray;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.renderscript.Type;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

	TextView[] staffhours;

	String staffhoursID;


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

		staffhours  = new TextView[28];
		for(int i=0; i<staffhours.length; i++) 
		{
			staffhoursID = "staffhours" + (i+1);

			int resID = getResources().getIdentifier(staffhoursID, "id", getPackageName());
			staffhours[i] = ((TextView) findViewById(resID));

		}


		timeIn = (TextView) findViewById(R.id.timeIn);
		timeOut = (TextView) findViewById(R.id.timeOut);
		breakIn = (TextView) findViewById(R.id.breakIn);
		breakOut = (TextView) findViewById(R.id.breakOut);
		txtMsg = (TextView) findViewById(R.id.txtMsg);


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
			time.put("userName","john");
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
					time.put("userName","john");
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
					time.put("userName","john");
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
					time.put("userName","john");
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
					time.put("userName","john");
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
			time.put("TimeOut" ,"N");
			time.put("BreakIn" ,"N");
			time.put("BreakOut" ,"N");
			recieve=2;

			Send s = new Send();
			s.execute();
		}
		if(field == 2 &&timeOut.getText().toString().equals(""))
		{

			if(!timeIn.getText().toString().equals(""))
			{
				getTime();

				time.put("TimeIn" ,"N");
				time.put("TimeOut" ,timeC);
				time.put("BreakIn" ,"N");
				time.put("BreakOut" ,"N");
				recieve=2;
				Send s = new Send();
				s.execute();

				alertDialog();
			}
			else
			{
				toast("Please enter a start time first");
			}

		}
		
		if(field == 3 &&breakIn.getText().toString().equals(""))
		{

			getTime();
			breakIn.append(timeC);


			time.put("TimeIn" ,"N");
			time.put("TimeOut" ,"N");
			time.put("BreakIn" ,timeC);
			time.put("BreakOut" ,"N");
			recieve=2;
			Send s = new Send();
			s.execute();
		}
		if(field == 4 &&breakOut.getText().toString().equals(""))
		{

			if(!breakIn.getText().toString().equals(""))
			{

				getTime();

				breakOut.append(timeC);

				time.put("TimeIn" ,"N");
				time.put("TimeOut" ,"N");
				time.put("BreakIn" ,"N");
				time.put("BreakOut" ,timeC);
				recieve=2;

				Send s = new Send();
				s.execute();
			}
			else{
				toast("Please enter a break in time first");
			}

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
	AsyncTask< ArrayList<HashMap<String,String>>,Void,StoreArray>
	{

		String st,ft,bi,bo;
		JSONArray jsArray = new JSONArray();
		JSONArray jsArray1 = new JSONArray();
		ArrayList<String> stringArray = new ArrayList<String>();
		ArrayList<String> stringArray1 = new ArrayList<String>();

		IPAddress ip = new IPAddress();

		String url=  ip.getIPAddress();
		JSONObject jsonSEND = new JSONObject(time);

		StoreArray s = new StoreArray();

		protected StoreArray doInBackground(ArrayList<HashMap<String, String>>...params) 
		{

			try{

				String jsonString = HttpUtils.urlContentPost(url,"user", jsonSEND.toString());
				//			 JSONObject jsonResult = new JSONObject(jsonString);
				if (recieve == 1)
				{
					JSONObject jsonResult = new JSONObject(jsonString);

					if( jsonResult!= null)
					{

						st =jsonResult.getString("start");
						ft =jsonResult.getString("finish");
						bi =jsonResult.getString("breakI");
						bo =jsonResult.getString("breakO");

						jsArray = jsonResult.getJSONArray("staffHours");

						for (int i=0;i<jsArray.length();i++){ 
							try {
								stringArray.add(jsArray.get(i).toString());
							} catch (JSONException e) {

								Log.e( "JSONException", e.toString());
							} 
						}

						s.st=st;
						s.ft = ft;
						s.bi = bi;
						s.bo = bo;
						s.storeStaffHoursArray = stringArray;

					}
				}
			}catch (JSONException e) {
				// Toast.makeText(this, "JSONEEXCEPTION" + e, Toast.LENGTH_LONG).show();
			} catch (ClientProtocolException e) {

				// Toast.makeText(this, "illeagal base url", Toast.LENGTH_LONG).show();

			} catch (IOException e) {
				//Toast.makeText(this, "Server Error", Toast.LENGTH_LONG).show();

			}



			return s;

		}

		protected void onPostExecute(StoreArray result) {
			//super.onPostExecute(result);
			if(result != null)
			{
				//if(!result.get("st").equals("5") && !result.get("ft").equals("5"));
				//{


				if(result.storeStaffHoursArray!= null)
				{
					for(int i=0; i<result.storeStaffHoursArray.size(); i++) 
					{
						if(i < 28)
						{
							if(staffhours[i].getText().toString().trim().equals(""))
							{
								staffhours[i].append((result.storeStaffHoursArray.get(i)).toString());
							}
						}
					}
				}

				if(result.st!= null)
				{
					timeIn.append(result.st);
				}
				if(result.ft!= null)
				{
					timeOut.append(result.ft);
				}
				if(result.bi!= null)
				{
					breakIn.append(result.bi);
				}
				if(result.bo!= null)
				{
					breakOut.append(result.bo);
				}




			}
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
