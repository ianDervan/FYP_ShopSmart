package fyp.shopsmart.employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import fyp.shopsmart.employee.Manage.SendM;
import fyp.shopsmart.employee.StaffHours.Send;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Rota extends Activity {

	AutoCompleteTextView textView;

	TextView shift;
	TextView swap;
	TextView with;

	TextView[] rotaList;
	TextView[] rotanwList;

	String rotaListID;
	String rotanwListID;
	Spinner spinner1;
	Spinner spinner2;
	Spinner spinner3;
	Spinner spinner4;


	Spinner spinner5;
	Spinner spinner6;
	Spinner spinner7;
	Spinner spinner8;
	Spinner spinner9;
	Spinner spinner10;
	Spinner spinner11;
	Spinner spinner12;
	Spinner spinner13;

	Button btnSS;
	Button btnClear;
	Button btnSubmit;
	Button btnBO;
	Map<String,String> rota;
	int checkS;
	
	Context context = this;
	
	int checkAlert;

	int notEmpty;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_rota);

		notEmpty=0;

		rotaList  = new TextView[28];
		for(int i=0; i<rotaList.length; i++) 
		{
			rotaListID = "rota" + (i+1);

			int resID = getResources().getIdentifier(rotaListID, "id", getPackageName());
			rotaList[i] = ((TextView) findViewById(resID));

		}

		rotanwList  = new TextView[28];
		for(int i=0; i<rotanwList.length; i++) 
		{
			rotanwListID = "rotanw" + (i+1);

			int resID = getResources().getIdentifier(rotanwListID, "id", getPackageName());
			rotanwList[i] = ((TextView) findViewById(resID));

		}

		shift = (TextView) findViewById(R.id.shift); 
		with = (TextView) findViewById(R.id.with); 
		swap = (TextView) findViewById(R.id.swap); 

		btnSS  = (Button) findViewById(R.id.btnsendswap);
		btnBO  = (Button) findViewById(R.id.btnbookoff);
		btnSubmit  = (Button) findViewById(R.id.submit);
		btnClear  = (Button) findViewById(R.id.clear);

		checkS = 0;

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.days_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter);

		spinner2 = (Spinner) findViewById(R.id.spinner2);
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
				this, R.array.users_array, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter1);

		spinner3 = (Spinner) findViewById(R.id.spinner3);
		ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
				this, R.array.days_array, android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner3.setAdapter(adapter3);

		spinner4 = (Spinner) findViewById(R.id.dayoff);
		ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(
				this, R.array.days_array, android.R.layout.simple_spinner_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner4.setAdapter(adapter4);

		spinner5 = (Spinner) findViewById(R.id.spinner5);
		ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(
				this, R.array.users_arrayR, android.R.layout.simple_spinner_item);
		adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner5.setAdapter(adapter5);

		spinner6 = (Spinner) findViewById(R.id.spinner6);
		ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(
				this, R.array.shifts_arrayR, android.R.layout.simple_spinner_item);
		adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner6.setAdapter(adapter6);

		spinner7 = (Spinner) findViewById(R.id.spinner7);
		ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(
				this, R.array.days_arrayR, android.R.layout.simple_spinner_item);
		adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner7.setAdapter(adapter7);

		spinner8 = (Spinner) findViewById(R.id.spinner8);
		ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(
				this, R.array.users_arrayR, android.R.layout.simple_spinner_item);
		adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner8.setAdapter(adapter8);


		spinner9 = (Spinner) findViewById(R.id.spinner9);
		ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(
				this, R.array.shifts_arrayR, android.R.layout.simple_spinner_item);
		adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner9.setAdapter(adapter9);

		spinner10 = (Spinner) findViewById(R.id.spinner10);
		ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(
				this, R.array.days_arrayR, android.R.layout.simple_spinner_item);
		adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner10.setAdapter(adapter10);

		spinner11 = (Spinner) findViewById(R.id.spinner11);
		ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(
				this, R.array.users_arrayR, android.R.layout.simple_spinner_item);
		adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner11.setAdapter(adapter11);

		spinner12 = (Spinner) findViewById(R.id.spinner12);
		ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(
				this, R.array.shifts_arrayR, android.R.layout.simple_spinner_item);
		adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner12.setAdapter(adapter12);

		spinner13 = (Spinner) findViewById(R.id.spinner13);
		ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(
				this, R.array.days_arrayR, android.R.layout.simple_spinner_item);
		adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner13.setAdapter(adapter13);


		Intent intent = getIntent();	
		checkS = intent.getIntExtra("SignedIn",0);

		if(checkS == 1)
		{
			shift.setVisibility(View.GONE);
			swap.setVisibility(View.GONE);
			with.setVisibility(View.GONE);
			spinner1.setVisibility(View.GONE);
			spinner2.setVisibility(View.GONE);
			spinner3.setVisibility(View.GONE);
			btnSS.setVisibility(View.GONE);
		}	
		else
		{
			LinearLayout users = (LinearLayout) findViewById(R.id.linearLayout3);
			users.setVisibility(View.GONE);

			ScrollView scroll= (ScrollView) findViewById(R.id.ScrollViewJ);
			scroll.setVisibility(View.GONE);

		}


		rota  = new HashMap<String,String>();
		rota.put("rotaRequested", "1");

		Send s = new Send();
		s.execute();


		btnSS.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {


				alertDialog("Swap "+spinner1.getSelectedItem().toString()+"'s shift" + " with " + 
				spinner2.getSelectedItem().toString()+"'s shift" + " on " + spinner3.getSelectedItem().toString(),1);
			}		
		});
		btnSubmit.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				for(int i=0; i<28; i++) 
				{

					rotaList[i].setText("");

				}

				rota.put("rotaRequested", "4");

				getItemSelected();
			}
		});

		btnClear.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				for(int i=0; i<28; i++) 
				{

					rotaList[i].setText("");

				}

				rota.put("rotaRequested", "5");

				getItemSelected();
			}
		});



		btnBO.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				
				for(int i=0; i<28; i++) 
				{

					rotanwList[i].setText("");

				}
				
				alertDialog("Book "+spinner4.getSelectedItem().toString()+" off",2);


				
			}		
		});

	}

	public void getItemSelected()
	{
		String user1 = spinner5.getSelectedItem().toString();
		String shift1 = spinner6.getSelectedItem().toString();
		String day1 = spinner7.getSelectedItem().toString();

		String user2 = spinner8.getSelectedItem().toString();
		String shift2 = spinner9.getSelectedItem().toString();
		String day2 = spinner10.getSelectedItem().toString();

		String user3 = spinner11.getSelectedItem().toString();
		String shift3 = spinner12.getSelectedItem().toString();
		String day3 =spinner13.getSelectedItem().toString();

		if(!user1.trim().equals("") &&!shift1.trim().equals("") && !day1.trim().equals(""))
		{
			rota.put("user1",user1);
			rota.put("shift1",shift1);
			rota.put("day1",day1);

			notEmpty = 1;	

		}
		else
		{
			rota.put("user1","N");
			rota.put("shift1","N");
			rota.put("day1","N");
		}
		if(!user2.trim().equals("") &&!shift2.trim().equals("") && !day2.trim().equals(""))
		{
			rota.put("user2",user2);
			rota.put("shift2",shift2);
			rota.put("day2",day2);

			notEmpty = 1;	

		}
		else
		{
			rota.put("user2","N");
			rota.put("shift2","N");
			rota.put("day2","N");
		}
		if(!user3.trim().equals("") &&!shift3.trim().equals("") && !day3.trim().equals(""))
		{
			rota.put("user3",user3);
			rota.put("shift3",shift3);
			rota.put("day3",day3);

			notEmpty = 1;	

		}
		else
		{
			rota.put("user3","N");
			rota.put("shift3","N");
			rota.put("day3","N");

		}

		if(notEmpty == 1)
		{


			Send s = new Send();
			s.execute();

		}


	}


	public void sendDetails()
	{
		
		for(int i=0; i<28; i++) 
		{

			rotaList[i].setText("");

		}

		rota.put("swapDay",spinner1.getSelectedItem().toString());
		rota.put("withUser",spinner2.getSelectedItem().toString());
		rota.put("forUsersDay",spinner3.getSelectedItem().toString());
		rota.put("rotaRequested", "2");
		Send s = new Send();
		s.execute();


	}



	public class Send extends
	AsyncTask< ArrayList<HashMap<String,String>>,Void,StoreArray>
	{


		IPAddress ip = new IPAddress();

		String url=  ip.getIPAddress();

		JSONArray jsArray = new JSONArray();
		JSONArray jsArray1 = new JSONArray();
		ArrayList<String> stringArray = new ArrayList<String>();
		ArrayList<String> stringArray1 = new ArrayList<String>();

		JSONObject jsonSEND = new JSONObject(rota);

		StoreArray s = new StoreArray();

		protected StoreArray doInBackground(ArrayList<HashMap<String, String>>...params) 
		{


			try{

				String jsonString = HttpUtils.urlContentPost(url,"rota", jsonSEND.toString());

				JSONObject jsonResult = new JSONObject(jsonString);

				if( jsonResult!= null)
				{

					jsArray = jsonResult.getJSONArray("rota");
					jsArray1 = jsonResult.getJSONArray("rotaNW");

					for (int i=0;i<jsArray.length();i++){ 

						try {
							stringArray.add(jsArray.get(i).toString());
						} catch (JSONException e) {

							Log.e( "JSONException", e.toString());
						} 
					}
					for (int i=0;i<jsArray1.length();i++){ 

						try {
							stringArray1.add(jsArray1.get(i).toString());
						} catch (JSONException e) {

							Log.e( "JSONException", e.toString());
						} 

					}

					s.storeRotaArray = stringArray;
					s.storeRotaNWArray=stringArray1;
				}



			}catch (JSONException e) {

				Log.e( "JSONEXCEPTION", e.toString());
			} catch (ClientProtocolException e) {
				Log.e( "ClientProtocolException ", e.toString());


			} catch (IOException e) {
				Log.e( "IOException", e.toString());

			}



			return s;
		}
		protected void onPostExecute(StoreArray result) {
			super.onPostExecute(result);
			if(result != null)
			{

				if(result.storeRotaArray!= null)
				{
					for(int i=0; i<result.storeRotaArray.size(); i++) 
					{
						if(i < 28)
						{
							if(rotaList[i].getText().toString().trim().equals(""))
							{
								rotaList[i].append((result.storeRotaArray.get(i)).toString());
							}
						}
					}
				}
				if(result.storeRotaNWArray!= null)
				{
					for(int i=0; i<result.storeRotaNWArray.size(); i++) 
					{
						if(i < 28)
						{
							if(rotanwList[i].getText().toString().trim().equals(""))
							{
								rotanwList[i].append((result.storeRotaNWArray.get(i)).toString());
							}
						}
					}
				}


			}
		}

	}
	public void alertDialog(String message,int check)
	{
		checkAlert = check;

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		alertDialogBuilder

		.setMessage(message)
		.setCancelable(false)
		.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {

				if(checkAlert== 1)
				{
					if(checkS == 1)
					{
						rota.put("userNameFR","John");
						sendDetails();
					}		

					if(checkS == 2)
					{
						rota.put("userNameFR","Ian");

						sendDetails();
					}		

					if(checkS == 3)
					{
						rota.put("userNameFR","Sarah");

						sendDetails();

					}		
					if(checkS == 4)
					{
						rota.put("userNameFR","aishling");

						sendDetails();
					}		

				
				}
				else if(checkAlert== 2)
				{
					if(checkS == 1)
					{
						rota.put("userNameFR","John");
						rota.put("rotaRequested", "3");
						rota.put("Day",spinner4.getSelectedItem().toString());

						Send s = new Send();
						s.execute();

					}		

					if(checkS == 2)
					{
						rota.put("userNameFR","Ian");
						rota.put("rotaRequested", "3");
						rota.put("Day",spinner4.getSelectedItem().toString());

						Send s = new Send();
						s.execute();

					}		

					if(checkS == 3)
					{
						rota.put("userNameFR","Sarah");
						rota.put("rotaRequested", "3");
						rota.put("Day",spinner4.getSelectedItem().toString());

						Send s = new Send();
						s.execute();

					}		
					if(checkS == 4)
					{
						rota.put("userNameFR","aishling");
						rota.put("rotaRequested", "3");
						rota.put("Day",spinner3.getSelectedItem().toString());
						Send s = new Send();
						s.execute();

					}		


				}	    
			}
		})
		.setNegativeButton("No",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {

				dialog.cancel();
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		
		if(checkAlert== 1)
		{
			alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Swap</font>"));
			alertDialog.show();
			
		}
		else if(checkAlert== 2)
		{
			alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Book Off</font>"));
			alertDialog.show();
		}	    

		

		int titleDividerId = getResources().getIdentifier("android:id/titleDivider", "id", "android");
		View titleDivider = alertDialog.findViewById(titleDividerId);
		if (titleDivider != null)
			titleDivider.setBackgroundColor(Color.parseColor("#ED6F26"));
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

	@Override
	public void onBackPressed()
	{
		finish();  
	}
}
