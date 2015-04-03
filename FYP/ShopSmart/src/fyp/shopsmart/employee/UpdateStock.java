package fyp.shopsmart.employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;

import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import fyp.shopsmart.employee.Pricing.SendP;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateStock extends Activity {

	Map<String,String> stock;

	Button btnGetItem;
	Button btnCheckS;
	Button btnChangeS;
	Button btnAddItem;
	Button btnClearT;
	TextView itemTxtFS;
	TextView stockOut;
	EditText newStock;
	EditText newStockNo;

	String storeInput;
	String storeInput1;

	TextView[] orderList;
	TextView[] qtyList;

	int recieve;
	int check;
	int i;

	String orderListID;
	String qtyListID;

	Context context = this;
	String resultBarcode;
	private static final int ZBAR_SCANNER_REQUEST = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_stock);

		recieve =0;
		check =0;
		stock  = new HashMap<String,String>();
		stock.put("updateStock", "2");
		stock.put("addItem", "2");



		SendS ss = new SendS();
		ss.execute();


		orderList = new TextView[20];
		qtyList  = new TextView[20];
		for(int i=0; i<orderList.length; i++) 
		{
			orderListID = "orderlist" + (i+1);

			int resID = getResources().getIdentifier(orderListID, "id", getPackageName());
			orderList[i] = ((TextView) findViewById(resID));

		}
		for(int i=0; i<qtyList.length; i++) 
		{
			qtyListID = "qty" + (i+1);

			int resID = getResources().getIdentifier(qtyListID, "id", getPackageName());
			qtyList[i] = ((TextView) findViewById(resID));

		}


		btnGetItem  = (Button) findViewById(R.id.scanitems);
		btnCheckS  = (Button) findViewById(R.id.checkstock);
		btnChangeS  = (Button) findViewById(R.id.btnchange);
		btnAddItem = (Button) findViewById(R.id.additem);
		itemTxtFS  = (TextView) findViewById(R.id.itemnamefs);
		stockOut  = (TextView) findViewById(R.id.stockout);
		newStock  = (EditText) findViewById(R.id.changestock);
		newStockNo  = (EditText) findViewById(R.id.additemstock);
		btnClearT = (Button) findViewById(R.id.cleartable);



		btnGetItem.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {


				recieve = 1;
				startScanner();

			}
		});
		btnClearT.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {



				alertDialog("Delete order list all data will be lost.",3);



			}
		});
		btnChangeS.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				storeInput =itemTxtFS.getText().toString();
				storeInput1 =newStock.getText().toString();
				if(!storeInput.trim().equals("") && !storeInput1.trim().equals(""))
				{



					alertDialog("Change " + storeInput + " Stock to "+ storeInput1,1);

				}
				else
				{

					toast("Please enter an item and a stock No.");
				}


			}
		});
		btnCheckS.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				stockOut.setText("");

				storeInput =itemTxtFS.getText().toString();
				if(!storeInput.trim().equals(""))
				{



					stock.put("updateStock", "1");
					stock.put("addItem", "N");
					stock.put("barcodeS", "N");
					stock.put("itemTxt", storeInput);
					stock.put("newStock", "N");


					SendS ss = new SendS();
					ss.execute();



				}
				else
				{
					toast("No item scanned");


				}


			}
		});
		btnAddItem.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				storeInput =itemTxtFS.getText().toString();
				storeInput1 =newStockNo.getText().toString();



				if(!storeInput.trim().equals("") && !storeInput1.trim().equals(""))
				{



					alertDialog("Add " + storeInput +" to order list." ,2);

				}
				else
				{
					toast("Please scan Item and enter a quantity");
				}


			}
		});





	}

	public void startScanner() {
		if (isCameraAvailable()) {
			Intent intent = new Intent(UpdateStock.this, ZBarScannerActivity.class);
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

			stock.put("barcodeS", resultBarcode);
			stock.put("updateStock", "1");
			stock.put("addItem", "N");
			stock.put("itemTxt", "N");
			stock.put("newStock", "N");


			SendS ss = new SendS();
			ss.execute();

		} else if(resultCode == RESULT_CANCELED && data != null) {
			String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
			if(!TextUtils.isEmpty(error)) {
				Toast.makeText(this, error, Toast.LENGTH_LONG).show();


			}
		}
	}
	public class SendS extends
	AsyncTask< ArrayList<HashMap<String,String>>,Void,StoreArray>
	{


		String itemName;
		String itemStock;

		JSONArray jsArray = new JSONArray();
		JSONArray jsArray1 = new JSONArray();
		ArrayList<String> stringArray = new ArrayList<String>();
		ArrayList<String> stringArray1 = new ArrayList<String>();

		String s1,s2;

		String url=  "http://192.168.0.29:8080/NetworkingSupport/servlet";
		JSONObject jsonSEND = new JSONObject(stock);
		StoreArray s = new StoreArray();

		protected StoreArray doInBackground(ArrayList<HashMap<String, String>>...params) 
		{



			try{

				String jsonString = HttpUtils.urlContentPost(url,"stock", jsonSEND.toString());

				JSONObject jsonResult = new JSONObject(jsonString);

				if( jsonResult!= null)
				{	

					itemName =jsonResult.getString("itemTxtFS");
					itemStock =jsonResult.getString("itemStk");

					jsArray = jsonResult.getJSONArray("itemName");
					jsArray1 = jsonResult.getJSONArray("itemStock");

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

					s.itemN=itemName;
					s.itemS = itemStock;
					s.storeArray = stringArray;
					s.storeArray1=stringArray1;

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


				if(result.storeArray!= null)
				{
					for(int i=0; i<result.storeArray.size(); i++) 
					{
						if(i < 20)
						{
							if(orderList[i].getText().toString().trim().equals(""))
							{
								orderList[i].append((result.storeArray.get(i)).toString());
							}
						}
					}
				}
				if(result.storeArray1!= null)
				{
					for(int i=0; i<result.storeArray1.size(); i++) 
					{
						if(i < 20)
						{
							if(qtyList[i].getText().toString().trim().equals(""))
							{
								qtyList[i].append((result.storeArray1.get(i)).toString());
							}
						}
					}
				}
				if(result.itemN!= null)
				{
					itemTxtFS.append(result.itemN);
				}
				if(result.itemS!= null)
				{
					stockOut.append(result.itemS);
				}
				if(recieve== 1)
				{
					checkItem();
				}
				if(check == 3)
				{
					toast("check 3");
					for(int i=0; i<qtyList[i].length(); i++) 
					{

						qtyList[i].setText("");

					}
					for(int i=0; i<orderList[i].length(); i++) 
					{

						orderList[i].setText("");

					}
				}

			} 
		}

	}
	public void checkItem()
	{
		String store;


		if(recieve== 1)
		{
			store =itemTxtFS.getText().toString();

			if(store.trim().equals(""))
			{
				toast("Item Isnt in database");

			}
		}
	}
	public void alertDialog(String message,int c)
	{

		check= c;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);


		alertDialogBuilder

		.setMessage(message)
		.setCancelable(false)
		.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {

				if(check == 1)
				{
					stock.put("updateStock", "1");
					stock.put("barcodeS", "N");
					stock.put("itemTxt", storeInput);
					stock.put("newStock", storeInput1);
					stock.put("addItem", "N");


					SendS ss = new SendS();
					ss.execute();

					stockOut.setText("");
					newStock.setText("");


					toast( storeInput + " Stock changed to "  + storeInput1);
				}
				if(check == 2)
				{

					stock.put("updateStock", "N");
					stock.put("addItem", "1");
					stock.put("addItemTxt", storeInput);
					stock.put("addStockNo",  storeInput1);

					toast( storeInput + " Added to order list");

					SendS ss = new SendS();
					ss.execute();

				}
				if(check == 3)
				{

					
					stock.put("updateStock", "3");
					stock.put("addItem", "3");

					SendS ss = new SendS();
					ss.execute();



					toast("Order list deleted");
				}
			}
		})
		.setNegativeButton("No",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {

				dialog.cancel();
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		if(check== 1)
		{
			alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Change Stock</font>"));
			alertDialog.show();
		}
		if(check== 2)
		{
			alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Add item to order list</font>"));
			alertDialog.show();
		}
		if(check== 3)
		{
			alertDialog.setTitle( Html.fromHtml("<font color='#ED6F26'>Delete Order List</font>"));
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

}
