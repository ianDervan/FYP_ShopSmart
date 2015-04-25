package fyp.shopsmart.employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import fyp.shopsmart.customer.Request.SendREJ;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRequests extends Activity {

	Button requestI;
	Button requestS;

	Map<String,String> request;

	List<String> storelistR; 
	List<String> storelistU; 

	ListView requestView;
	ArrayAdapter<String> arrayAdapter;
	 ArrayAdapter<String> dataAdapter;

	Spinner spinner;





	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_requests);

		requestI  = (Button) findViewById(R.id.btnviewr);
		requestS  = (Button) findViewById(R.id.btnviews);
		requestView = (ListView) findViewById(R.id.requestview);

		request  = new HashMap<String,String>();
		storelistR= new ArrayList<String>();
		storelistU= new ArrayList<String>();

		spinner = (Spinner) findViewById(R.id.spinnervr);

		request.put("getUsers","getUsers");
		request.put("getRequest","N");

		//SendRV r = new SendRV();
		//r.execute();

		requestI.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {


				request.put("getRequest","getRequest");
				request.put("getUsers","N");

				//SendRV r = new SendRV();
				//r.execute();


			}
		});
	}
//	public class SendRV extends
//	AsyncTask< ArrayList<HashMap<String,String>>,Void,StoreArray>
//	{
//
//		IPAddress ip = new IPAddress();
//
//		String url=  ip.getIPAddress();
//		JSONObject jsonSEND = new JSONObject(request);
//		StoreArray s = new StoreArray();
//
//		JSONArray jsArray = new JSONArray();
//		JSONArray jsArray1 = new JSONArray();
//		ArrayList<String> stringArray = new ArrayList<String>();
//		ArrayList<String> stringArray1 = new ArrayList<String>();

//		protected StoreArray doInBackground(ArrayList<HashMap<String, String>>...params) 
//		{
//
//			try{
//				String jsonString = HttpUtils.urlContentPost(url,"viewRequest", jsonSEND.toString());
//
//				JSONObject jsonResult = new JSONObject(jsonString);
//
//				if( jsonResult!= null)
//				{	
//
//					jsArray = jsonResult.getJSONArray("request");
//					jsArray1 = jsonResult.getJSONArray("users");
//					for (int i=0;i<jsArray.length();i++){ 
//						try {
//							stringArray.add(jsArray.get(i).toString());
//						} catch (JSONException e) {
//
//							Log.e( "JSONException", e.toString());
//						} 
//					}
//
//					for (int i=0;i<jsArray1.length();i++){ 
//						try {
//							stringArray1.add(jsArray1.get(i).toString());
//						} catch (JSONException e) {
//
//							Log.e( "JSONException", e.toString());
//						} 
//					}
//
//					s.storeRequest = stringArray;
//					s.storeUsers = stringArray1;
//				}
//
//			}catch (ClientProtocolException e) {
//				Log.d( "ClientProtocolException ",  e.toString());
//
//
//			} catch (IOException e) {
//
//				Log.e( "IOException", e.toString());
//
//
//			} catch (JSONException e) {
//				Log.e( "JSONException", e.toString());
//			}
//
//			return s;
//
//		}
//
//		protected void onPostExecute(StoreArray result) {
//			super.onPostExecute(result);
//
//
//			if (result != null) { 
//
//
//				if(result.storeRequest!= null)
//				{
//
//					storelistR= s.storeRequest;
//
//
//					displayList();
//				}
//				if(result.storeUsers!= null)
//				{
//					storelistU= s.storeUsers;
//					
//					toast(" " + storelistU);
//					
//					//displayUsers();
//					
//					
//
//					
//					
//				}
//
//
//			} 
//			
//		}
//
//	}
	public void displayList()
	{
		arrayAdapter = new ArrayAdapter<String>(
				this, 
				android.R.layout.simple_list_item_1,
				storelistR);

		requestView.setAdapter(arrayAdapter); 
	}
	public void displayUsers()
	{
		toast(" " + storelistU);
		
		
		ArrayAdapter<String> adapter5 =  new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, storelistU);
		adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter5);
		
		
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



}
