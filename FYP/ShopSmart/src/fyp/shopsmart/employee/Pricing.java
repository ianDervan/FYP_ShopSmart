package fyp.shopsmart.employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;

import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import fyp.shopsmart.customer.BarcodeScan;
import fyp.shopsmart.employee.Rota.Send;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pricing extends Activity {
	
	Button btnScan;
	String resultBarcode;
	Map<String,String> price;
	AutoCompleteTextView itemTxt;
	EditText itemName;
	TextView priceOut;
	
	private static final int ZBAR_SCANNER_REQUEST = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pricing);
		
		btnScan  = (Button) findViewById(R.id.scanitem);
		itemName = (EditText) findViewById(R.id.enteritemname);
		priceOut = (TextView) findViewById(R.id.priceout);
		price  = new HashMap<String,String>();
		
		itemTxt = (AutoCompleteTextView) findViewById(R.id.autocompleteitem);
			String[] suggestedItems = getResources().getStringArray(R.array.suggestions_array);
			ArrayAdapter<String> adapter = 
			        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestedItems);
			itemTxt.setAdapter(adapter);
		
		
        btnScan.setOnClickListener(new OnClickListener() {	
    		public void onClick(View v) {
    			
    			

    			startScanner();
    			
//    			Send s = new Send();
//	    		s.execute();
	    		
    			
    			
    		}
    	});
	}
	  public void startScanner() {
	        if (isCameraAvailable()) {
	            Intent intent = new Intent(Pricing.this, ZBarScannerActivity.class);
	            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
	        } else {
	            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_LONG).show();
	        }
	        
	        
	    }



	    public boolean isCameraAvailable() {
	        PackageManager pm = getPackageManager();
	        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
	    }

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


	    	if (resultCode == RESULT_OK) {
	    		
	    		//Toast.makeText(this, "Scan Result = " + data.getStringExtra(ZBarConstants.SCAN_RESULT), Toast.LENGTH_LONG).show();
	    		resultBarcode = data.getStringExtra(ZBarConstants.SCAN_RESULT);
	    		
	    		Toast.makeText(this, "Barcode" + resultBarcode, Toast.LENGTH_LONG).show();

	    		price.put("barcode", resultBarcode);
	    		price.put("checkPrice", "1");
	    		
	    		send();
	    		//Toast.makeText(this, "aftersend", Toast.LENGTH_LONG).show();
	    		
	    	} else if(resultCode == RESULT_CANCELED && data != null) {
	    		String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
	    		if(!TextUtils.isEmpty(error)) {
	    			Toast.makeText(this, error, Toast.LENGTH_LONG).show();
	    		}
	    	}
	    }
	    public void send()
	    {
	    	SendP sp = new SendP();
    		sp.execute();
	    }
	    public class SendP extends
		AsyncTask< ArrayList<HashMap<String,String>>,Void,Map<String, String>>
		{
	    	

			Map<String,String> rPrice;
			
			String s1,s2;
			
			String url=  "http://10.12.2.47:8080/NetworkingSupport/servlet";
			JSONObject jsonSEND = new JSONObject(price);

			protected Map<String, String> doInBackground(ArrayList<HashMap<String, String>>...params) 
			{
				rPrice  = new HashMap<String,String>(); 
				
				try{

					
					
					String jsonString = HttpUtils.urlContentPost(url,"price", jsonSEND.toString());

					JSONObject jsonResult = new JSONObject(jsonString);

					if( jsonResult!= null)
					{	
						
						//s1 = ;
						//s2 = jsonResult.getString("finish");
						//rPrice.put("mon1",jsonResult.getString("ItemT"));
						rPrice.put("itemTxt",jsonResult.getString("itemTxt"));
						
						//s1 =jsonResult.getString("start");
						//s2 =jsonResult.getString("finish");
						//rPrice.put("item",s1);
						
						
						
						
						
					}
						
						
					
				}catch (ClientProtocolException e) {
					Log.d( "ClientProtocolException ", "ClientProtocolException ");


				} catch (IOException e) {
					Log.e( "IOException", e.toString());

				} catch (JSONException e) {
					Log.e( "JSONException", e.toString());
				}
				

				return rPrice;
				
				

			}
			protected void onPostExecute(Map<String, String> result) {
				super.onPostExecute(result);
				

			
				if(!result.isEmpty())
				{

					itemTxt.append(result.get("itemTxt"));
					
					//Toast.makeText(this,result.get("item") , Toast.LENGTH_LONG).show();
				}

			}
			
		}


		@Override
		public void onBackPressed()
		{
			finish();  
		}
	
}
