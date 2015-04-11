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
import org.xmlpull.v1.XmlPullParser;

import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import fyp.shopsmart.customer.BarcodeScan.SendB;
import fyp.shopsmart.employee.HttpUtils;
import fyp.shopsmart.employee.IPAddress;
import fyp.shopsmart.employee.StoreArray;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.graphics.Canvas;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.res.Resources;




public class Search extends Activity {
	
	Button btnSearch;
	
	String searchItem;
	
	int n;
	
	AutoCompleteTextView textView;
	
	Map<String,String> search;

	String x;
	String y;
	
	float XCord;
	float YCord;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		 btnSearch = (Button) findViewById(R.id.search);
		 
		 search  = new HashMap<String,String>();
		 
		// XCord = Float.parseFloat(x);
		// YCord = Float.parseFloat(y);
		 
	//	 textView1 = (TextView) findViewById(R.id.textView1);
		 textView = (AutoCompleteTextView) findViewById(R.id.autocomplete);
			String[] suggestedItems = getResources().getStringArray(R.array.suggestions_array);
			ArrayAdapter<String> adapter = 
			        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestedItems);
			textView.setAdapter(adapter);
			
		
		    
		    btnSearch.setOnClickListener(new OnClickListener() {	
				public void onClick(View v) {
					
					
					searchItem =textView.getText().toString();
					//Toast.makeText(getApplicationContext(), searchItem, Toast.LENGTH_SHORT).show();
					
					search.put("GetSearch",searchItem);
					
					SendB sb = new SendB();
					sb.execute();
			    	
				}		
			});
	}
	

	public class SendB extends
	AsyncTask< ArrayList<HashMap<String,String>>,Void,StoreArray>
	{


		String searchX;
		String searchY;
		

		JSONArray jsArray = new JSONArray();

		List<String> stringArray = new ArrayList<String>();


		IPAddress ip = new IPAddress();

		String url=  ip.getIPAddress();
		JSONObject jsonSEND = new JSONObject(search);
		StoreArray s = new StoreArray();


		protected StoreArray doInBackground(ArrayList<HashMap<String, String>>...params) 
		{

			try{

				String jsonString = HttpUtils.urlContentPost(url,"search", jsonSEND.toString());

				JSONObject jsonResult = new JSONObject(jsonString);

				if( jsonResult!= null)
				{	
					
					if(!jsonResult.getString("searchX").equals(""))
					{
						searchX=jsonResult.getString("searchX");
					}
					if(!jsonResult.getString("searchY").equals(""))
					{
						searchY = jsonResult.getString("searchY");
					}

					s.xCoord = searchX;
					s.yCoord = searchY;
					

//					s.barcodeTxt = itemName;
//					s.barcodePrice = itemPrice;

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
		
				if(s.xCoord!=null)
				{
					toast(s.xCoord);
				}

				if(s.xCoord!=null)
				{
					toast(s.yCoord);
				}

				displaySearchCord(s.xCoord,s.yCoord);

			}
		}

	}

	public void displaySearchCord(String x, String y)
	{
		BitmapFactory.Options myOptions = new BitmapFactory.Options();
	    myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important

	    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.grocery,myOptions);
	   

	    Bitmap workingBitmap = Bitmap.createBitmap(bitmap);
	    Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

	    Canvas canvas = new Canvas(mutableBitmap);
	    
	  	 Paint paint = new Paint();
		    paint.setAntiAlias(true);
		    paint.setColor(Color.parseColor("#ED6F26"));
		    
		   XCord= Float.parseFloat(x);
		   YCord= Float.parseFloat(y);
	   
    	
    	 canvas.drawCircle(XCord, YCord, 25, paint);
	   
    	 
    	 

	    ImageView imageView = (ImageView)findViewById(R.id.imageView1);
	   imageView.setScaleType(ScaleType.FIT_CENTER);
	   imageView.setAdjustViewBounds(true);
	   imageView.setImageBitmap(mutableBitmap);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			
			return true;
		}
		return super.onOptionsItemSelected(item);
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
