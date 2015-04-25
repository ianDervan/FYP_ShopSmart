package fyp.shopsmart.customer;

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
import fyp.shopsmart.employee.HttpUtils;
import fyp.shopsmart.employee.IPAddress;
import fyp.shopsmart.employee.StoreArray;
import fyp.shopsmart.employee.Manage.SendM;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Request extends Activity {

	Context context = this;

	Map<String,String> request;

	Button  btnSubmitItem;
	Button  btnRequestList;
	Button  btnShow;
	EditText itemName;

	String itemTxt;

	StoreArray s;

	EditText username;
	EditText day;
	EditText time;

	TextView status;

	int ok;
	List<String> storelist; 

	String inputUser,inputDay,inputTime;

	JSONArray jsArray;
	JSONObject jsonSEND ;

	int option;
	ListView shopListView;
	ArrayAdapter<String> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request);

		request  = new HashMap<String,String>();
		btnSubmitItem =  (Button) findViewById(R.id.submit);
		btnRequestList =  (Button) findViewById(R.id.request);
		btnShow = (Button) findViewById(R.id.showlist);
		itemName = (EditText) findViewById(R.id.itemname);
		username = (EditText) findViewById(R.id.username);
		day = (EditText) findViewById(R.id.day);
		time = (EditText) findViewById(R.id.time);
		shopListView = (ListView) findViewById(R.id.shoplistview);
		status = (TextView) findViewById(R.id.status);

		s = new StoreArray();
		ok =0;

		storelist= new ArrayList<String>();

		btnSubmitItem.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {


				itemTxt =itemName.getText().toString();

				if(!itemTxt.trim().equals(""))
				{	

					alertDialog("Request " + itemTxt +"",1);


					
					
					
				}
				else
				{
					toast("Please Enter The Item You Request");
				}


			}
		});
		btnShow.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {




				storelist= s.shoplist;
				toast(s.shoplist.toString());

				//	toast(storelist.toString());

				displayList();

			}
		});
		btnRequestList.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				inputUser = username.getText().toString();
				inputDay = day.getText().toString();
				inputTime = time.getText().toString();

				if(!inputUser.trim().equals("") && !inputDay.trim().equals("") &&  !inputTime.trim().equals(""))
				{	
					if(!s.shoplist.isEmpty())
					{
						alertDialog("Press yes to request shopping list for pickup ",2);



					}
					else
					{
						toast("Sorry your ShoppingList Is empty..");
					}



				}
				else
				{
					toast("Please fill out all fields");
				}



			}
		});
	}
	public void displayList()
	{
		arrayAdapter = new ArrayAdapter<String>(
				this, 
				android.R.layout.simple_list_item_1,
				storelist);

		shopListView.setAdapter(arrayAdapter); 
	}

	public class SendRE extends
	AsyncTask< ArrayList<HashMap<String,String>>,Void,StoreArray>
	{

		IPAddress ip = new IPAddress();

		String url=  ip.getIPAddress();
		JSONObject jsonSEND = new JSONObject(request);
		StoreArray s = new StoreArray();


		protected StoreArray doInBackground(ArrayList<HashMap<String, String>>...params) 
		{

			try{
				String jsonString = HttpUtils.urlContentPost(url,"request", jsonSEND.toString());

				JSONObject jsonResult = new JSONObject(jsonString);

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

		}

	}


	public void alertDialog(String message,int check)
	{
		option = check;

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		alertDialogBuilder

		.setMessage(message)
		.setCancelable(false)
		.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {

				if(option== 1)
				{



					request.put("newRequest",itemTxt);


					SendRE re = new SendRE();
					re.execute();

					toast(itemTxt +" was requested");

					itemName.setText("");

				}
				if(option== 2)
				{


					jsArray = new JSONArray(s.shoplist);

					try {
						jsonSEND = new JSONObject();
						jsonSEND.put("jsArray", jsArray );
						jsonSEND.put("userName",inputUser);
						jsonSEND.put("status","Ordered");
						jsonSEND.put("dayTime",inputDay + " " + inputTime);


						SendREJ rej = new SendREJ();
						rej.execute();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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

		if(option== 1)
		{
			alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Request Item</font>"));
			alertDialog.show();

		}
		if(option== 2)
		{
			alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Request Shopping List</font>"));
			alertDialog.show();

		}
		int titleDividerId = getResources().getIdentifier("android:id/titleDivider", "id", "android");
		View titleDivider = alertDialog.findViewById(titleDividerId);
		if (titleDivider != null)
			titleDivider.setBackgroundColor(Color.parseColor("#ED6F26"));
	}
	public class SendREJ extends
	AsyncTask<JSONObject,Void,StoreArray>
	{


		String userName,statusR;

		JSONArray jsArray = new JSONArray();

		List<String> stringArray = new ArrayList<String>();


		IPAddress ip = new IPAddress();

		String url=  ip.getIPAddress();



		StoreArray s = new StoreArray();


		protected StoreArray doInBackground( JSONObject...params) 
		{

			try{


				String jsonString = HttpUtils.urlContentPost(url,"request1", jsonSEND.toString());

				JSONObject jsonResult = new JSONObject(jsonString);

				if( jsonResult!= null)
				{	


					userName=jsonResult.getString("username");

					statusR=jsonResult.getString("status");

				}
				s.usernameS =userName;
				s.statusS =statusR;

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

				if(s.usernameS.equals("usernameTaken"))
				{
				
					toast("Sorry User Name  allready taken");
					username.setText("");
					day.setText("");
					time.setText("");


				}
				else{
					toast("Shopping list was requested");
				}
				if(!s.statusS.equals(""))
				{
					status.append(s.statusS);
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
}
