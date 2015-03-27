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
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

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
	Spinner spinner1;

	Map<String,String> rota;

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
		

		addListenerOnSpinnerItemSelection();

		rota  = new HashMap<String,String>();
		
		
		rota.put("rotaRequested", "1");

		Send s = new Send();
		s.execute();
	}
	 public void addListenerOnSpinnerItemSelection() {
			spinner1 = (Spinner) findViewById(R.id.spinner1);
			spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}

	public class Send extends
	AsyncTask< ArrayList<HashMap<String,String>>,Void,Map<String, String>>
	{

		Map<String,String> rRota;

		String url=  "http://10.12.2.47:8080/NetworkingSupport/servlet";
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

			}
		}

	}
}
