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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
	//	EditText itemName;
	TextView priceOut;
	EditText newPrice;

	Button btnScanFD;
	Button btnDelete;
	EditText itemTxt;
	EditText itemTxtFD;
	String storeInput;
	String storeInput1;

	Button btnGetBar;
	Button btnSubmit;
	EditText enterBar;
	EditText enterName;
	EditText enterQuantity;
	EditText enterPrice;

	String inputBar;
	String inputName;
	String inputQuantity;
	String inputPrice;



	int recieve;
	int checkScan;
	String deleteTxt;
	String resultBarcode;
	Map<String,String> price;

	Context context = this;
	int checkAlert;

	private static final int ZBAR_SCANNER_REQUEST = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pricing);

		recieve = 0;
		checkScan = 0;

		btnScan  = (Button) findViewById(R.id.scanitem);
		btnScanFD  = (Button) findViewById(R.id.scanitem1);
		btnDelete  = (Button) findViewById(R.id.deleteitem);
		btnCheckP  = (Button) findViewById(R.id.checkprice);
		btnChangeP  = (Button) findViewById(R.id.btnchange);
		itemTxt = (EditText) findViewById(R.id.itemname);
		itemTxtFD = (EditText) findViewById(R.id.itemnamed);
		priceOut = (TextView) findViewById(R.id.priceout);
		newPrice = (EditText) findViewById(R.id.changeprice);

		btnGetBar  = (Button) findViewById(R.id.btnscanbar);
		btnSubmit  = (Button) findViewById(R.id.btnsubmitsetup);
		enterBar = (EditText) findViewById(R.id.enteritembarcode);
		enterName = (EditText) findViewById(R.id.enteritemname);
		enterPrice = (EditText) findViewById(R.id.enterprice);
		enterQuantity = (EditText) findViewById(R.id.enterquantity);



		price  = new HashMap<String,String>();




		btnSubmit.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				inputName =enterName.getText().toString();
				inputBar =enterBar.getText().toString();
				inputQuantity =enterQuantity.getText().toString();
				inputPrice =enterPrice.getText().toString();

				if(!inputName.trim().equals("") && !inputBar.trim().equals("") && !inputQuantity.trim().equals("") && !inputPrice.trim().equals(""))
				{	
					alertDialog("Click yes to setup " +inputName,2);

				}
				else
				{
					toast("All fields must be filled");
				}


			}
		});
		btnGetBar.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				recieve = 1;

				storeInput1 =itemTxt.getText().toString();


				startScanner();



			}
		});
		btnScan.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {


				checkScan = 1;
				recieve = 6;
				startScanner();

			}
		});
		btnScanFD.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				recieve = 5;
				checkScan = 2;

				startScanner();

			}
		});
		btnDelete.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {


				deleteTxt =itemTxtFD.getText().toString();

				if(!deleteTxt.trim().equals(""))
				{	
					alertDialog("Delete " +deleteTxt +" From Database?",3);



				}
				else
				{
					toast("Please Enter An Item To Be Deleted");
				}


			}
		});
		btnChangeP.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				storeInput =newPrice.getText().toString();
				storeInput1 =itemTxt.getText().toString();

				if(!storeInput.trim().equals("") && !storeInput.trim().equals(""))
				{	
					alertDialog("Change " +storeInput1 +" price to " + storeInput + "?",1);

				}
				else
				{
					toast("Please scan an item and enter a price");
				}
			}
		});
		btnCheckP.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				storeInput =itemTxt.getText().toString();
				if(!storeInput.trim().equals(""))
				{

					price.put("checkPrice", "1");
					price.put("barcode", "N");
					price.put("setUpPrice", "N");
					price.put("checkPriceTxt",  storeInput);
					price.put("newPrice",  "N");



					recieve = 2;

					SendP sp = new SendP();
					sp.execute();


				}
				else
				{
					toast("No item scanned");
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
			price.put("setUpPrice", "N");
			price.put("deleteItem", "N");
			price.put("checkPriceTxt", "N");
			price.put("newPrice",  "N");

			SendP sp = new SendP();
			sp.execute();



		} else if(resultCode == RESULT_CANCELED && data != null) {
			String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
			if(!TextUtils.isEmpty(error)) {
				Toast.makeText(this, error, Toast.LENGTH_LONG).show();


			}
		}
	}
	public void send()
	{


		String store;
		String store1;
		String store2;

		if(recieve== 1)
		{
			store =itemTxt.getText().toString();
			enterBar.append(resultBarcode);

			if(!store.trim().equals(""))
			{
				toast(store);
				toast("Is Already In Database");
				toast("Please Scan Different Item");
				enterBar.setText("");

				//itemTxt.clearComposingText();
				//itemTxt.setText("");

			}
			else if(store.trim().equals(""))
			{
				toast("Item Isnt Already in database");

			}
		}
		if(recieve== 5)
		{
			//enterBar.append(resultBarcode);
			store1 =itemTxtFD.getText().toString();

			if(store1.trim().equals(""))
			{
				toast("Item Isnt In The Database");

			}
		}
		if(recieve== 6)
		{
			//enterBar.append(resultBarcode);
			store2 =itemTxt.getText().toString();

			if(store2.trim().equals(""))
			{
				toast("Item Isnt In The Database");

			}
		}


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

					if(checkScan == 1)
					{
						itemTxt.append(result.get("itemTxt"));
					}
					if(checkScan == 2)
					{
						itemTxtFD.append(result.get("itemTxt"));
					}

				}

				if(!result.get("itemPrice").equals("N"))
				{
					priceOut.append("€"+result.get("itemPrice"));

				}
			}
			if(recieve == 1)
			{

				send();

			}
			if(recieve == 5)
			{

				send();

			}
			if(recieve == 6)
			{

				send();



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

					price.put("checkPrice", "1");
					price.put("setUpPrice", "N");
					price.put("deleteItem", "N");
					price.put("barcode", "N");
					price.put("checkPriceTxt",  storeInput1);
					price.put("newPrice", storeInput);

					SendP sp = new SendP();
					sp.execute();

					newPrice.setText("");
					priceOut.setText("");
					itemTxt.setText("");

					toast(storeInput1 + " price changed to " + storeInput);
				}
				else if(checkAlert== 2)
				{
					price.put("checkPrice", "N");
					price.put("deleteItem", "N");
					price.put("setUpPrice", "1");
					price.put("inputName",inputName);
					price.put("inputBar",inputBar);
					price.put("inputQuantity",inputQuantity);
					price.put("inputPrice",inputPrice);
					recieve = 2;

					SendP sp = new SendP();
					sp.execute();

					toast(enterName + "is now setup");

					enterBar.setText("");
					enterName.setText("");
					enterQuantity.setText("");
					enterPrice.setText("");

				}	    
				else if(checkAlert== 3)
				{
					price.put("checkPrice", "N");
					price.put("setUpPrice", "N");
					price.put("deleteItem", "1");
					price.put("deleteTxt", deleteTxt);

					recieve = 2;

					SendP sp = new SendP();
					sp.execute();

					toast(deleteTxt + "is now deleted");

					itemTxtFD.setText("");


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
			alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Change Price</font>"));
			alertDialog.show();
		}
		if(checkAlert== 2)
		{
			alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Setup Item</font>"));
			alertDialog.show();
		}
		if(checkAlert== 3)
		{
			alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Delete Item</font>"));
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
