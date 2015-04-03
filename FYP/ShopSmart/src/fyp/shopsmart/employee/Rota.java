package fyp.shopsmart.employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import fyp.shopsmart.employee.StaffHours.Send;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Rota extends Activity {

	AutoCompleteTextView textView;

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
	
	TextView mon1R;
	TextView mon2R;
	TextView mon3R;
	TextView mon4R;
	TextView tues1R;
	TextView tues2R;
	TextView tues3R;
	TextView tues4R;
	TextView wed1R;
	TextView wed2R;
	TextView wed3R;
	TextView wed4R;
	TextView thurs1R;
	TextView thurs2R;
	TextView thurs3R;
	TextView thurs4R;
	TextView fri1R;
	TextView fri2R;
	TextView fri3R;
	TextView fri4R;
	TextView sat1R;
	TextView sat2R;
	TextView sat3R;
	TextView sat4R;
	TextView sun1R;
	TextView sun2R;
	TextView sun3R;
	TextView sun4R;
	Spinner spinner1;
	Spinner spinner2;
	Spinner spinner3;
	Spinner spinner4;

	Button btnSS;
	Button btnBO;
	Map<String,String> rota;
	int check;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_rota);

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
		
		mon1R = (TextView) findViewById(R.id.mon1R);
		mon2R = (TextView) findViewById(R.id.mon2R);
		mon3R = (TextView) findViewById(R.id.mon3R);
		mon4R = (TextView) findViewById(R.id.mon4R);
		tues1R = (TextView) findViewById(R.id.tues1R);
		tues2R = (TextView) findViewById(R.id.tues2R);
		tues3R = (TextView) findViewById(R.id.tues3R);
		tues4R = (TextView) findViewById(R.id.tues4R);
		wed1R = (TextView) findViewById(R.id.wed1R);
		wed2R = (TextView) findViewById(R.id.wed2R);
		wed3R = (TextView) findViewById(R.id.wed3R);
		wed4R = (TextView) findViewById(R.id.wed4R);
		thurs1R = (TextView) findViewById(R.id.thurs1R);
		thurs2R = (TextView) findViewById(R.id.thurs2R);
		thurs3R = (TextView) findViewById(R.id.thurs3R);
		thurs4R = (TextView) findViewById(R.id.thurs4R);
		fri1R = (TextView) findViewById(R.id.fri1R);
		fri2R = (TextView) findViewById(R.id.fri2R);
		fri3R = (TextView) findViewById(R.id.fri3R);
		fri4R = (TextView) findViewById(R.id.fri4R);
		sat1R = (TextView) findViewById(R.id.sat1R);
		sat2R = (TextView) findViewById(R.id.sat2R);
		sat3R = (TextView) findViewById(R.id.sat3R);
		sat4R = (TextView) findViewById(R.id.sat4R);
		sun1R = (TextView) findViewById(R.id.sun1R);
		sun2R = (TextView) findViewById(R.id.sun2R);
		sun3R = (TextView) findViewById(R.id.sun3R);
		sun4R = (TextView) findViewById(R.id.sun4R); 
		
		btnSS  = (Button) findViewById(R.id.btnsendswap);
		btnBO  = (Button) findViewById(R.id.btnbookoff);

		check = 0;
		
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


		 Intent intent = getIntent();	
			check = intent.getIntExtra("SignedIn",0);
			
//			if(check == 1)
//			{
//				setContentView(R.layout.activity_rota_john);
//				Toast.makeText(Rota.this,
//						"OnClickListener : " + 
//								"\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) 
//								
//								,
//								Toast.LENGTH_SHORT).show();
//			}	


		rota  = new HashMap<String,String>();
		rota.put("rotaRequested", "1");

		Send s = new Send();
		s.execute();


		btnSS.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				
				if(check == 1)
				{
					rota.put("userNameFR","John");
					sendDetails();
				}		
				
				if(check == 2)
				{
					rota.put("userNameFR","Ian");
					
					sendDetails();
				}		
			
				if(check == 3)
				{
					rota.put("userNameFR","Sarah");
			
					sendDetails();
					
				}		
				if(check == 4)
				{
					rota.put("userNameFR","aishling");

					sendDetails();
				}		
			}		
		});
		btnBO.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

			
				if(check == 1)
				{
					rota.put("userNameFR","John");
					rota.put("rotaRequested", "3");
					rota.put("Day",spinner4.getSelectedItem().toString());
					
					Send s = new Send();
					 s.execute();
					 
					 finish();
				   		startActivity(getIntent());
				   		
				}		
				
				if(check == 2)
				{
					rota.put("userNameFR","Ian");
					rota.put("rotaRequested", "3");
					rota.put("Day",spinner4.getSelectedItem().toString());
					
					Send s = new Send();
					 s.execute();
					 
					 finish();
				   		startActivity(getIntent());
					
					
				}		
			
				if(check == 3)
				{
					rota.put("userNameFR","Sarah");
					rota.put("rotaRequested", "3");
					rota.put("Day",spinner4.getSelectedItem().toString());
					
					Send s = new Send();
					 s.execute();
					 
					 finish();
				   		startActivity(getIntent());
			
					
					
				}		
				if(check == 4)
				{
					rota.put("userNameFR","aishling");
					rota.put("rotaRequested", "3");
					rota.put("Day",spinner3.getSelectedItem().toString());
					
					Send s = new Send();
					 s.execute();

					 finish();
				   		startActivity(getIntent());
					
				}		
				
			}		
		});

	}

	
	
	public void sendDetails()
	{
		rota.put("swapDay",spinner1.getSelectedItem().toString());
		rota.put("withUser",spinner2.getSelectedItem().toString());
		rota.put("forUsersDay",spinner3.getSelectedItem().toString());
		rota.put("rotaRequested", "2");
		Send s = new Send();
		 s.execute();
		 
		 finish();
   		startActivity(getIntent());
		 	
	}



	public class Send extends
	AsyncTask< ArrayList<HashMap<String,String>>,Void,Map<String, String>>
	{

		Map<String,String> rRota;
		
		IPAddress ip = new IPAddress();
		
		String url=  ip.getIPAddress();
		JSONObject jsonSEND = new JSONObject(rota);

		protected Map<String, String> doInBackground(ArrayList<HashMap<String, String>>...params) 
		{
			rRota  = new HashMap<String,String>(); 
			
			try{

				String jsonString = HttpUtils.urlContentPost(url,"rota", jsonSEND.toString());

				JSONObject jsonResult = new JSONObject(jsonString);

				if( jsonResult!= null)
				{
					rRota.put("mon1",jsonResult.getString("monJ"));
					rRota.put("mon2",jsonResult.getString("monI"));
					rRota.put("mon3",jsonResult.getString("monS"));
					rRota.put("mon4",jsonResult.getString("monA"));

					rRota.put("tues1",jsonResult.getString("tuesJ"));
					rRota.put("tues2",jsonResult.getString("tuesI"));
					rRota.put("tues3",jsonResult.getString("tuesS"));
					rRota.put("tues4",jsonResult.getString("tuesA"));

					rRota.put("wed1",jsonResult.getString("wedJ"));
					rRota.put("wed2",jsonResult.getString("wedI"));
					rRota.put("wed3",jsonResult.getString("wedS"));
					rRota.put("wed4",jsonResult.getString("wedA"));

					rRota.put("thurs1",jsonResult.getString("thursJ"));
					rRota.put("thurs2",jsonResult.getString("thursI"));
					rRota.put("thurs3",jsonResult.getString("thursS"));
					rRota.put("thurs4",jsonResult.getString("thursA"));

					rRota.put("fri1",jsonResult.getString("friJ"));
					rRota.put("fri2",jsonResult.getString("friI"));
					rRota.put("fri3",jsonResult.getString("friS"));
					rRota.put("fri4",jsonResult.getString("friA"));

					rRota.put("fri1",jsonResult.getString("friJ"));
					rRota.put("fri2",jsonResult.getString("friI"));
					rRota.put("fri3",jsonResult.getString("friS"));
					rRota.put("fri4",jsonResult.getString("friA"));

					rRota.put("sat1",jsonResult.getString("satJ"));
					rRota.put("sat2",jsonResult.getString("satI"));
					rRota.put("sat3",jsonResult.getString("satS"));
					rRota.put("sat4",jsonResult.getString("satA"));

					rRota.put("sun1",jsonResult.getString("sunJ"));
					rRota.put("sun2",jsonResult.getString("sunI"));
					rRota.put("sun3",jsonResult.getString("sunS"));
					rRota.put("sun4",jsonResult.getString("sunA"));
					
					rRota.put("mon1NW",jsonResult.getString("monJNW"));
					rRota.put("mon2NW",jsonResult.getString("monINW"));
					rRota.put("mon3NW",jsonResult.getString("monSNW"));
					rRota.put("mon4NW",jsonResult.getString("monANW"));

					rRota.put("tues1NW",jsonResult.getString("tuesJNW"));
					rRota.put("tues2NW",jsonResult.getString("tuesINW"));
					rRota.put("tues3NW",jsonResult.getString("tuesSNW"));
					rRota.put("tues4NW",jsonResult.getString("tuesANW"));

					rRota.put("wed1NW",jsonResult.getString("wedJNW"));
					rRota.put("wed2NW",jsonResult.getString("wedINW"));
					rRota.put("wed3NW",jsonResult.getString("wedSNW"));
					rRota.put("wed4NW",jsonResult.getString("wedANW"));

					rRota.put("thurs1NW",jsonResult.getString("thursJNW"));
					rRota.put("thurs2NW",jsonResult.getString("thursINW"));
					rRota.put("thurs3NW",jsonResult.getString("thursSNW"));
					rRota.put("thurs4NW",jsonResult.getString("thursANW"));

					rRota.put("fri1NW",jsonResult.getString("friJNW"));
					rRota.put("fri2NW",jsonResult.getString("friINW"));
					rRota.put("fri3NW",jsonResult.getString("friSNW"));
					rRota.put("fri4NW",jsonResult.getString("friANW"));

					rRota.put("fri1NW",jsonResult.getString("friJNW"));
					rRota.put("fri2NW",jsonResult.getString("friINW"));
					rRota.put("fri3NW",jsonResult.getString("friSNW"));
					rRota.put("fri4NW",jsonResult.getString("friANW"));

					rRota.put("sat1NW",jsonResult.getString("satJNW"));
					rRota.put("sat2NW",jsonResult.getString("satINW"));
					rRota.put("sat3NW",jsonResult.getString("satSNW"));
					rRota.put("sat4NW",jsonResult.getString("satANW"));

					rRota.put("sun1NW",jsonResult.getString("sunJNW"));
					rRota.put("sun2NW",jsonResult.getString("sunINW"));
					rRota.put("sun3NW",jsonResult.getString("sunSNW"));
					rRota.put("sun4NW",jsonResult.getString("sunANW"));
					

				}

			}catch (JSONException e) {

				Log.d( "JSONEXCEPTION", "JSONEXCEPTION");
			} catch (ClientProtocolException e) {
				Log.d( "ClientProtocolException ", "ClientProtocolException ");


			} catch (IOException e) {
				Log.d( "IOException", "IOException");

			}

			return rRota;

		}
		protected void onPostExecute(Map<String, String> result) {
			//super.onPostExecute(result);
			if(!result.isEmpty())
			{
				mon1.append(result.get("mon1"));
				mon2.append(result.get("mon2"));
				mon3.append(result.get("mon3"));
				mon4.append(result.get("mon4"));
				tues1.append(result.get("tues1"));
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
				
				
				
				mon1R.append(result.get("mon1NW"));
				mon2R.append(result.get("mon2NW"));
				mon3R.append(result.get("mon3NW"));
				mon4R.append(result.get("mon4NW"));
				tues1R.append(result.get("tues1NW"));
				tues2R.append(result.get("tues2NW"));
				tues3R.append(result.get("tues3NW"));
				tues4R.append(result.get("tues4NW"));
				wed1R.append(result.get("wed1NW"));
				wed2R.append(result.get("wed2NW"));
				wed3R.append(result.get("wed3NW"));
				wed4R.append(result.get("wed4NW"));
				thurs1R.append(result.get("thurs1NW"));
				thurs2R.append(result.get("thurs2NW"));
				thurs3R.append(result.get("thurs3NW"));
				thurs4R.append(result.get("thurs4NW"));
				fri1R.append(result.get("fri1NW"));
				fri2R.append(result.get("fri2NW"));
				fri3R.append(result.get("fri3NW"));
				fri4R.append(result.get("fri4NW"));
				sat1R.append(result.get("sat1NW"));
				sat2R.append(result.get("sat2NW"));
				sat3R.append(result.get("sat3NW"));
				sat4R.append(result.get("sat4NW"));
				sun1R.append(result.get("sun1NW"));
				sun2R.append(result.get("sun2NW"));
				sun3R.append(result.get("sun3NW"));
				sun4R.append(result.get("sun4NW"));

				
			}
		}

	}


	@Override
	public void onBackPressed()
	{
		finish();  
	}
}
