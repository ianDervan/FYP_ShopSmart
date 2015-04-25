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
	String resultBarcode;

	SQLiteDatabase db;

	Button  btnAddItem;
	Button  btnShow;
	
	

	String getListNew;
	String getListOld;
	String getItem;
	String getPrice;

	int check;
	int n;
	int quantity;
	int back;


	ArrayList<String> storeItem;
	ArrayList<String> price;

	//	ListView shopListView;
	StoreArray s;

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
		btnAddItem = (Button) findViewById(R.id.additem);
		btnShow =  (Button) findViewById(R.id.show);
		
		txtMsg = (TextView) findViewById(R.id.txtMsg);

		quantity = 1;
		 storeItem = new ArrayList<String>();
	     price = new ArrayList<String>();
	     s = new StoreArray();

		barcode  = new HashMap<String,String>();
		
		openDatabase();
		 Intent intent = getIntent();
	    	
	  	   back = intent.getIntExtra("back",0);

		
		btnScan.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				startScanner();

			}
		});

		btnShow.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				useRawQueryShowAll();

			}
		});

		btnAddItem.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {

				String storeInput =txtItem.getText().toString();		
				String  storeInput1= txtPrice.getText().toString();
				
				s.shoplist.add(storeInput);

				storeItem.add(storeInput);
			
				price.add("€"+storeInput1);

				if(s.backpressed != 1)
				{
					dropTable();
					s.backpressed =0;
				}
				insertSomeDbData();
				useRawQueryShowAll();


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
					txtPrice.append("€"+ s.barcodePrice);
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
	private void openDatabase() {
		try {

			String SDcardPath = Environment.getExternalStorageDirectory().getPath();

				String myDbPath = SDcardPath + "/" + "shopingList.db";
				db = SQLiteDatabase.openDatabase(myDbPath, null,
						SQLiteDatabase.CREATE_IF_NECESSARY);
			
			

		} catch (SQLiteException e) {

			finish();
		}
	}
	private void insertSomeDbData() {
		
		db.beginTransaction();
		try {
			// create table
			db.execSQL("create table  if not exists shoppingList   ("
					+ " ID integer PRIMARY KEY autoincrement, "
					+ " Item  text, " + " Price text, "+" Quantity text);");
			// commit your changes
			db.setTransactionSuccessful();

		} catch (SQLException e1) {
			txtMsg.append("\nError insertSomeDbData: " + e1.getMessage());
			finish();
		} finally {
			db.endTransaction();
		}

		
		db.beginTransaction();
		try {
			
			for(int i = 0; i < storeItem.size(); i++){
				db.execSQL("insert into shoppingList (Item,Price,Quantity) "
						+ " values ('"+ storeItem.get(i)+"', '" + price.get(i)+"', '" + quantity+"');");
			}
			db.setTransactionSuccessful();
			
			
		} catch (SQLiteException e2) {
			txtMsg.append("\nError insertSomeDbData: " + e2.getMessage());
			
		} finally {
			db.endTransaction();
		}

	}

	private void dropTable() {
		// (clean start) action query to drop table

		try {
			db.execSQL("DROP TABLE IF EXISTS shoppingList;");
			//txtMsg.append("\n-dropTable - dropped!!");
		} catch (Exception e) {
			txtMsg.append("\nError dropTable: " + e.getMessage());
			finish();
		}
	}
	private void useRawQueryShowAll() {
		try {
			// hard-coded SQL select with no arguments
			String mySQL = "select * from shoppingList";
			Cursor c1 = db.rawQuery(mySQL, null);
			
			txtMsg.append("" + showCursor(c1) );
			
		} catch (Exception e) {
			txtMsg.append("\nShoping List is Empty");
			
		}
	}// useRawQuery1
	
	private String showCursor( Cursor cursor) {
		// show SCHEMA (column names & types)
		cursor.moveToPosition(-1); //reset cursor's top		
		String cursorData = "\n";
		
		try {
			// get column names
			String[] colName = cursor.getColumnNames();
			for(int i=0; i<colName.length; i++){
				String dataType = getColumnType(cursor, i);
				cursorData += colName[i] + dataType;
				
				if (i<colName.length-1){
					cursorData+= ", ";
				}
			}
		} catch (Exception e) {
			Log.e( "<<SCHEMA>>" , e.getMessage() );
		}
		cursorData += "";
		
		// now get the rows
		cursor.moveToPosition(-1); //reset cursor's top
		while (cursor.moveToNext()) {
			String cursorRow = "\n";
			for (int i = 0; i < cursor.getColumnCount(); i++) {
				cursorRow += cursor.getString(i);
				if (i<cursor.getColumnCount()-1) 
					cursorRow +=  ", ";
			}
			cursorData += cursorRow + "";
		}
		return cursorData + "\n";
	}
	private String getColumnType(Cursor cursor, int i) {
		try {
			//peek at a row holding valid data 
			cursor.moveToFirst(); 
			int result = cursor.getType(i);
			String[] types = { };
			//backtrack - reset cursor's top
			cursor.moveToPosition(-1);
			return types[result];
		} catch (Exception e) {
			return " ";
		}
	}
	
	
	private void showTable(String tableName) {
		try {
			String sql = "select * from " + tableName;
			Cursor c = db.rawQuery(sql, null);
			txtMsg.append(""  + showCursor(c));

		} catch (Exception e) {
			txtMsg.append("\nError showTable: " + e.getMessage());

		}
	}// useCursor1
	
	@Override
	public void onBackPressed()
	{
		s.backpressed=1;

		
		db.close();
	    finish();  
	}
}
