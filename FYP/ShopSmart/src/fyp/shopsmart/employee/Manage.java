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

import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import fyp.shopsmart.employee.Pricing.SendP;
import fyp.shopsmart.employee.Rota.Send;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Manage extends Activity {

	Spinner spinner1;
	Spinner spinner2;
	Spinner spinner3;
	Spinner spinner4;
	
	String timeC;

	Context context = this;
	int checkAlert;

	Map<String,String> manage;

	TextView[] staffop;
	TextView[] delivery;
	String staffopID;
	String deliveryID;
	Button btnSubmit;
	Button btnSubmitFDT;
	
	Button btnClear;
	Button btnClearFDT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);

		manage  = new HashMap<String,String>();
		checkAlert = 0;
		
		manage.put("manage", "2");


		SendM m = new SendM();
		m.execute();

		staffop  = new TextView[16];
		for(int i=0; i<staffop.length; i++) 
		{
			staffopID = "staffop" + (i+1);

			int resID = getResources().getIdentifier(staffopID, "id", getPackageName());
			staffop[i] = ((TextView) findViewById(resID));	
		}
		
		delivery  = new TextView[25];
		
		for(int i=0; i<delivery.length; i++) 
		{
			deliveryID = "delivery" + (i+1);

			int resID = getResources().getIdentifier(deliveryID, "id", getPackageName());
			delivery[i] = ((TextView) findViewById(resID));	
		}
		
		//delivery[24].append("10.50");
		btnSubmit  = (Button) findViewById(R.id.submitm);
		btnSubmitFDT =  (Button) findViewById(R.id.submitmfd);
		
		btnClear =  (Button) findViewById(R.id.clear);
		btnClearFDT =  (Button) findViewById(R.id.clearfd);
		//btnSubmit.setVisibility(View.GONE);
		
		

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.users_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter);

		spinner2 = (Spinner) findViewById(R.id.spinner2);
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
				this, R.array.operations_array, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter1);
		
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
				this, R.array.delivery_array, android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner3.setAdapter(adapter3);
		
		spinner4 = (Spinner) findViewById(R.id.spinner4);
		ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(
				this, R.array.days_array1, android.R.layout.simple_spinner_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner4.setAdapter(adapter4);

		btnSubmit.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				
				

				manage.put("manage", "1");

				//SendM m = new SendM();
				//m.execute();

				sendDetails();


			}
		});
		
		btnSubmitFDT.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				
				getTime();

				manage.put("manage", "3");
				manage.put("delivery",spinner3.getSelectedItem().toString());
				manage.put("day",spinner4.getSelectedItem().toString());
				manage.put("time",timeC);
			
				SendM m = new SendM();
				m.execute();

			}
		});
		
		btnClearFDT.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				
				for(int i=0; i<25; i++) 
				{

					delivery[i].setText("");

				}
				
				
				
				getTime();

				manage.put("manage", "4");
				manage.put("delivery",spinner3.getSelectedItem().toString());
				manage.put("day",spinner4.getSelectedItem().toString());

				SendM m = new SendM();
				m.execute();

			}
		});
	}

	public void sendDetails()
	{
		for(int i=0; i<16; i++) 
		{

			staffop[i].setText("");

		}
//		for(int i=0; i<staffop[i].length(); i++) 
//		{
//
//			staffop[i].setText("");
//
//		}
		
		
		
		manage.put("StaffName",spinner1.getSelectedItem().toString());
		manage.put("Operation",spinner2.getSelectedItem().toString());
		manage.put("manage", "1");


		SendM m = new SendM();
		m.execute();

	}


	public class SendM extends
	AsyncTask< ArrayList<HashMap<String,String>>,Void,StoreArray>
	{


		String itemName;
		String itemStock;

		JSONArray jsArray = new JSONArray();
		JSONArray jsArray1 = new JSONArray();

		ArrayList<String> stringArray = new ArrayList<String>();
		ArrayList<String> stringArray1 = new ArrayList<String>();



		String s1,s2;

		IPAddress ip = new IPAddress();

		String url=  ip.getIPAddress();
		JSONObject jsonSEND = new JSONObject(manage);
		StoreArray s = new StoreArray();
		
		protected void onPreExecute(){
			

           
        }

		protected StoreArray doInBackground(ArrayList<HashMap<String, String>>...params) 
		{
			


			try{

				String jsonString = HttpUtils.urlContentPost(url,"manage", jsonSEND.toString());

				JSONObject jsonResult = new JSONObject(jsonString);

				if( jsonResult!= null)
				{	
					jsArray = jsonResult.getJSONArray("itemOp");

					for (int i=0;i<jsArray.length();i++){ 
						try {
							stringArray.add(jsArray.get(i).toString());
						} catch (JSONException e) {

							Log.e( "JSONException", e.toString());
						} 
					}
					jsArray1 = jsonResult.getJSONArray("itemFDT");

					for (int i=0;i<jsArray1.length();i++){ 
						try {
							stringArray1.add(jsArray1.get(i).toString());
						} catch (JSONException e) {

							Log.e( "JSONException", e.toString());
						} 
						
						
					}
					
					s.storeManageArray = stringArray;
					s.storeDeliveryArray = stringArray1;


				}



			}catch (ClientProtocolException e) {
				Log.d( "ClientProtocolException ",  e.toString());


			} catch (IOException e) {

				Log.e( "IOException", e.toString());


			} catch (JSONException e) {
				Log.e( "JSONException", e.toString());
			}


			return s;

		}

		protected void onPostExecute(StoreArray result) {
			super.onPostExecute(result);


			if (result != null) { 
				
				if(result.storeManageArray!= null)
				{
					for(int i=0; i<result.storeManageArray.size(); i++) 
					{
						if(i < 16)
						{
							if(staffop[i].getText().toString().trim().equals(""))
							{
								staffop[i].append((result.storeManageArray.get(i)).toString());
							}
						}
					}
				}
				
				if(result.storeDeliveryArray!= null)
				{
					for(int i=0; i<result.storeDeliveryArray.size(); i++) 
					{
						if(i < 25)
						{
							if(delivery[i].getText().toString().trim().equals(""))
							{
								delivery[i].append((result.storeDeliveryArray.get(i)).toString());
							}
						}
					}
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


	public void onBackPressed()
	{
		finish();  
	}
	
	public String getTime()
	{
		 Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
		 timeC=sdf.format(cal.getTime());
		 return timeC;	  	  
	}





}
