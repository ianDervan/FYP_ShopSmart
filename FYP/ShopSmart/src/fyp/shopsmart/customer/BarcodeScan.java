package fyp.shopsmart.customer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;

import fyp.shopsmart.R;
import fyp.shopsmart.employee.HttpUtils;
import fyp.shopsmart.employee.IPAddress;
import fyp.shopsmart.employee.Pricing;
import fyp.shopsmart.employee.StoreArray;
import fyp.shopsmart.employee.Pricing.SendP;
import net.sourceforge.zbar.Symbol;

public class BarcodeScan extends Activity {


	Button  btnScan;
	TextView txtItem;
	TextView txtPrice;
	TextView txtMsg;
	TextView shopList;
	EditText newShopList;
	EditText oldShopList;
	String resultBarcode;

	Button  btnAddItem;
	Button  btnShow;

	String getListNew;
	String getListOld;
	String getItem;
	String getPrice;

	int check;
	int n;
	
	 ListView shopListView;
	 ArrayAdapter<String> arrayAdapter;
	 
	 List<String> storelist; 
	 

	
//	ListView shopListView;

	Map<String,String> barcode;


	private static final int ZBAR_SCANNER_REQUEST = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barcode_scan);

		btnScan = (Button) findViewById(R.id.btnscan);
		txtItem = (TextView) findViewById(R.id.item);
		txtPrice = (TextView) findViewById(R.id.price);
		shopList = (TextView) findViewById(R.id.shoplist);
		newShopList  = (EditText) findViewById(R.id.shoplistname);
		oldShopList = (EditText) findViewById(R.id.oldshoplist);
		btnAddItem = (Button) findViewById(R.id.additem);
		btnShow =  (Button) findViewById(R.id.show);
		
		 shopListView = (ListView) findViewById(R.id.shoplistview);

		 
		storelist= new ArrayList<String>();
		
		barcode  = new HashMap<String,String>();

		btnScan.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				startScanner();

			}
		});
		
		btnShow.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				getListOld =oldShopList.getText().toString();
				getListNew =newShopList.getText().toString();
				

				if(!getListOld.trim().equals(""))
				{
					check = 1;
					barcode.put("shopListName",getListOld);
					barcode.put("data","N");
					barcode.put("checkTable", "N");
					barcode.put("getItem","SHOW");

					SendB sb = new SendB();
					sb.execute();
					
				}
				else
				{
					if(!getListNew.trim().equals(""))
					{
						toast("Please enter shopping list name in other field");
					}
					else
					{
						toast("Please enter a shopping list name");
					}
				}
				
				
	
			}
		});

		//
		btnAddItem.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				addItem();
			}
		});


	}

	public void startScanner() {
		if (isCameraAvailable()) {
			Intent intent = new Intent(BarcodeScan.this, ZBarScannerActivity.class);
			startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
		} else {
			Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_LONG).show();
		}



	}
	
	public void addItem()
	{
		getListNew =newShopList.getText().toString();
		getListOld =oldShopList.getText().toString();
		getItem =txtItem.getText().toString();
		getPrice =txtPrice.getText().toString();

		if(!getListNew.trim().equals("") && !getItem.trim().equals(""))
		{
			check = 1;
			barcode.put("shopListName",getListNew);
			barcode.put("data",getItem+" " +getPrice);
			barcode.put("checkTable", "1");
			barcode.put("getItem","ADD");

			SendB sb = new SendB();
			sb.execute();		

		}
		else 
		{
			n =1;
		}
		if(!getListOld.trim().equals("") && !getItem.trim().equals(""))
		{
			check = 1;
			barcode.put("shopListName",getListOld);
			barcode.put("data",getItem+" " +getPrice);
			barcode.put("checkTable", "N");
			barcode.put("getItem","ADD");

			SendB sb = new SendB();
			sb.execute();
			
		
				
		}
		else 
		{
			n=2;
		}
		
		if(n==1)
		{
			toast("Please scan Item and enter shopping list name");
		}
		if(n==2)
		{
			toast("Please scan Item and enter shopping list name");
		}
		
	}



	public boolean isCameraAvailable() {
		PackageManager pm = getPackageManager();
		return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {


		if (resultCode == RESULT_OK) {


			resultBarcode = data.getStringExtra(ZBarConstants.SCAN_RESULT);

			barcode.put("getItem",resultBarcode);
			barcode.put("shopListName","GETITEM");

			SendB sb = new SendB();
			sb.execute();

		} else if(resultCode == RESULT_CANCELED && data != null) {
			String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
			if(!TextUtils.isEmpty(error)) {
				Toast.makeText(this, error, Toast.LENGTH_LONG).show();


			}
		}
	}
	public class SendB extends
	AsyncTask< ArrayList<HashMap<String,String>>,Void,StoreArray>
	{


		String itemName;
		String itemPrice;
		String nameCheck;

		JSONArray jsArray = new JSONArray();

		List<String> stringArray = new ArrayList<String>();


		IPAddress ip = new IPAddress();

		String url=  ip.getIPAddress();
		JSONObject jsonSEND = new JSONObject(barcode);
		StoreArray s = new StoreArray();


		protected StoreArray doInBackground(ArrayList<HashMap<String, String>>...params) 
		{

			try{

				String jsonString = HttpUtils.urlContentPost(url,"barcode", jsonSEND.toString());

				JSONObject jsonResult = new JSONObject(jsonString);

				if( jsonResult!= null)
				{	
					if(!jsonResult.getString("itemTxt").equals(""))
					{
						itemName=jsonResult.getString("itemTxt");
					}
					if(!jsonResult.getString("itemPrice").equals(""))
					{
						itemPrice = jsonResult.getString("itemPrice");
					}
					if(!jsonResult.getString("tableNameCheck").equals(""))
					{
						nameCheck = jsonResult.getString("tableNameCheck");
					}
					
					
				if(jsonResult.getJSONArray("list") != null)
				{
					jsArray = jsonResult.getJSONArray("list");
					for (int i=0;i<jsArray.length();i++){ 
						try {
							stringArray.add(jsArray.get(i).toString());
						} catch (JSONException e) {

							Log.e( "JSONException", e.toString());
						} 
					}
				}

				
					s.shoplist = stringArray;
					s.tNameCheck = nameCheck;

					s.barcodeTxt = itemName;
					s.barcodePrice = itemPrice;

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
				if(s.barcodeTxt!=null)
				{
					txtItem.append(s.barcodeTxt);
				}

				if(s.barcodePrice!=null)
				{
					txtPrice.append(s.barcodePrice);
				}
				if(!s.shoplist.isEmpty() && s.shoplist != null)
				{
					//toast("HERE" + s.shoplist);
					storelist= s.shoplist;
					//toast("HERE" + storelist);
					
					displayList();
					
					//shopListView.setAdapter(arrayAdapter); 	
				}
				if(s.tNameCheck!= null)
				{
					if(s.tNameCheck.equals("nameTaken"))
					{
						toast("Shopping list table name taken");
						newShopList.setText("");
					}
					if(s.tNameCheck.equals("noColumn"))
					{
						toast("No shoping List with that name found");
						toast("Please enter new shopping list");
						oldShopList.setText("");
					}
					if(s.tNameCheck.equals("noShow"))
					{
						toast("No shoping List with that name found");
						oldShopList.setText("");
					}
				}


			}
		}

	}
	
	public void displayList()
	{
		 arrayAdapter = new ArrayAdapter<String>(
                 this, 
                 android.R.layout.simple_list_item_1,
                 storelist);
		 
		 shopListView.setAdapter(arrayAdapter); 
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
