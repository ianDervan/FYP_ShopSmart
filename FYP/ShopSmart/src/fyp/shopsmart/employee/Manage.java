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

	Context context = this;
	int checkAlert;

	Map<String,String> manage;

	Button btnSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);

		manage  = new HashMap<String,String>();
		checkAlert = 0;

		btnSubmit  = (Button) findViewById(R.id.submitm);

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

		btnSubmit.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				
				manage.put("manage", "1");

				//SendM m = new SendM();
				//m.execute();

				sendDetails();
				
				
			}
		});
	}

	public void sendDetails()
	{
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

		protected StoreArray doInBackground(ArrayList<HashMap<String, String>>...params) 
		{



			try{

				String jsonString = HttpUtils.urlContentPost(url,"manage", jsonSEND.toString());

	//			JSONObject jsonResult = new JSONObject(jsonString);

//				if( jsonResult!= null)
//				{	
//
//
//				}



			}catch (ClientProtocolException e) {
				Log.d( "ClientProtocolException ",  e.toString());


			} catch (IOException e) {

				Log.e( "IOException", e.toString());
			}

//			} catch (JSONException e) {
//				Log.e( "JSONException", e.toString());
//			}


			return s;

		}

		protected void onPostExecute(StoreArray result) {
			super.onPostExecute(result);


			if (result != null) { 


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





}
