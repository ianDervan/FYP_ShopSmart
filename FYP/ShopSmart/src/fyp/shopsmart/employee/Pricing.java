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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
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
	Button btnCheckP;
	Button btnChangeP;
	EditText itemName;
	TextView priceOut;
	EditText newPrice;
	
	String storeInput;
	String storeInput1;
	
	Button btnGetBar;
	Button btnSubmit;
	EditText enterBar;
	EditText enterName;
	EditText enterQuantity;
	EditText enterPrice;
	
	int recieve;
	String resultBarcode;
	Map<String,String> price;
	AutoCompleteTextView itemTxt;
	Context context = this;

	private static final int ZBAR_SCANNER_REQUEST = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pricing);
		
		recieve = 0;

		btnScan  = (Button) findViewById(R.id.scanitem);
		btnCheckP  = (Button) findViewById(R.id.checkprice);
		btnChangeP  = (Button) findViewById(R.id.btnchange);
		itemName = (EditText) findViewById(R.id.enteritemname);
		priceOut = (TextView) findViewById(R.id.priceout);
		newPrice = (EditText) findViewById(R.id.changeprice);
		
		btnGetBar  = (Button) findViewById(R.id.btnscanbar);
		btnSubmit  = (Button) findViewById(R.id.btnsubmitsetup);
		enterBar = (EditText) findViewById(R.id.enteritembarcode);
		enterName = (EditText) findViewById(R.id.enteritemname);
		enterPrice = (EditText) findViewById(R.id.enterprice);
		enterQuantity = (EditText) findViewById(R.id.enterquantity);
		
		
		
		price  = new HashMap<String,String>();

		itemTxt = (AutoCompleteTextView) findViewById(R.id.autocompleteitem);
		String[] suggestedItems = getResources().getStringArray(R.array.suggestions_array);
		ArrayAdapter<String> adapter = 
				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestedItems);
		itemTxt.setAdapter(adapter);


		btnGetBar.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				startScanner();
				
				recieve = 1;

			}
		});
		btnScan.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				startScanner();
				
				

			}
		});
		btnChangeP.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				storeInput =newPrice.getText().toString();
				storeInput1 =itemTxt.getText().toString();
				
				if(!storeInput.trim().equals("") && !storeInput.trim().equals(""))
				{	
					alertDialog();
					
				}
			}
		});
		btnCheckP.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				storeInput =itemTxt.getText().toString();
				if(!storeInput.trim().equals(""))
				{
					Log.d( "storeinput not null aa", "storeinput not null aa");
					price.put("checkPrice", "1");
					price.put("barcode", "N");
					price.put("checkPriceTxt",  storeInput);
					price.put("newPrice",  "N");
				
					
					SendP sp = new SendP();
					sp.execute();
					
					
				}


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
			price.put("checkPriceTxt", "N");
			price.put("newPrice",  "N");

			SendP sp = new SendP();
			sp.execute();
			
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
		if(recieve== 1)
		{
			enterBar.append(resultBarcode);
		}
		
	}
	public class SendP extends
	AsyncTask< ArrayList<HashMap<String,String>>,Void,Map<String, String>>
	{


		Map<String,String> rPrice;

		String s1,s2;

		String url=  "http://192.168.1.103:8080/NetworkingSupport/servlet";
		JSONObject jsonSEND = new JSONObject(price);

		protected Map<String, String> doInBackground(ArrayList<HashMap<String, String>>...params) 
		{
			rPrice  = new HashMap<String,String>(); 

			try{



				String jsonString = HttpUtils.urlContentPost(url,"price", jsonSEND.toString());

				JSONObject jsonResult = new JSONObject(jsonString);

				if( jsonResult!= null)
				{	

			
					rPrice.put("itemTxt",jsonResult.getString("itemTxt"));
					rPrice.put("itemPrice",jsonResult.getString("itemPrice"));

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
				
				if(!result.get("itemTxt").equals("N"))
				{
					itemTxt.append(result.get("itemTxt"));
				}
			
				if(!result.get("itemPrice").equals("N"))
				{
					  priceOut.append("€"+result.get("itemPrice"));
						
				}
			}

		}

	}
	public void alertDialog()
	{
		

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		alertDialogBuilder.setTitle("Sign Out");

		alertDialogBuilder

		.setMessage("Click yes to Change Price")
		.setCancelable(false)
		.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				
	
					
				price.put("checkPrice", "1");
				price.put("barcode", "N");
				price.put("checkPriceTxt",  storeInput1);
				price.put("newPrice", storeInput);
				Log.d( "storeinput not null", "storeinput not null");
				
				SendP sp = new SendP();
				sp.execute();
				
				newPrice.setText("");
				priceOut.setText("");
				
				
					    	
				
			}
		})
		.setNegativeButton("No",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {

				dialog.cancel();
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Change Price</font>"));
		alertDialog.show();


		int titleDividerId = getResources().getIdentifier("android:id/titleDivider", "id", "android");
		View titleDivider = alertDialog.findViewById(titleDividerId);
		if (titleDivider != null)
			titleDivider.setBackgroundColor(Color.parseColor("#ED6F26"));
		

	}


	@Override
	public void onBackPressed()
	{
		finish();  
	}

}
