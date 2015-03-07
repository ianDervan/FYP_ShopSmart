package fyp.shopsmart.customer;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
import android.provider.DocumentsContract.Document;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;

import fyp.shopsmart.R;
import net.sourceforge.zbar.Symbol;

public class BarcodeScan extends Activity {
	
	
	Button  btnScan;
	TextView txtItem;
	TextView txtMsg;
	TextView shopList;
	String resultBarcode;
	
	Button  btnAddItem;
	Button  btnShow;
	Button  btnOptions;
	
	ArrayList<String> storeItem;
	ArrayList<String> price;
	
	SQLiteDatabase db;
	
	int quantity;

	int  n;
	
	int check =0;
	int back =0;
	
	
    private static final int ZBAR_SCANNER_REQUEST = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scan);
        
        btnScan = (Button) findViewById(R.id.scanBarcode);
        txtItem = (TextView) findViewById(R.id.item);
        shopList = (TextView) findViewById(R.id.shoplist);
         
        btnAddItem = (Button) findViewById(R.id.addItem);
        btnShow = (Button) findViewById(R.id.show);
        btnOptions = (Button) findViewById(R.id.options);
        txtMsg = (TextView) findViewById(R.id.txtMsg);
        
        storeItem = new ArrayList<String>();
        price = new ArrayList<String>();
        
        quantity = 1;
        n =0;
        
        openDatabase();
        Intent intent = getIntent();
    	
  	    back = intent.getIntExtra("back",0);
  	    
        btnScan.setOnClickListener(new OnClickListener() {	
    		public void onClick(View v) {

    			startScanner();
    		}
    	});
        
        shopList.setOnClickListener(new OnClickListener() {	
    		public void onClick(View v) {
    			
    			Intent shopList = new Intent (BarcodeScan.this,ShoppingList.class);
    			startActivity(shopList);
    			
    		}
    	});
        btnAddItem.setOnClickListener(new OnClickListener() {	
    		public void onClick(View v) {
    			
				String storeInput =txtItem.getText().toString();		
				String[] inputItem = storeInput.split("€");
				
				storeItem.add(inputItem[0]);
				price.add("€"+inputItem[1]);
				
				txtItem.setText("");
				
				if( back == 1)
				{
					insertData();
					useRawQueryShowAll();
					
				}
				else
				{
					dropTable();
					insertSomeDbData();
					useRawQueryShowAll();
				}

    		}
    	});
        btnShow.setOnClickListener(new OnClickListener() {	
    		public void onClick(View v) {
    			
				useRawQueryShowAll();


    		}
    	});
        
        btnOptions.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				
				//db.close();

				Intent shopList = new Intent (BarcodeScan.this,ShoppingList.class);
				
				shopList.putExtra("start", 1);
				

    			startActivity(shopList);
			}		
		});
        
 
    }

    public void startXml()
    {

    	//txtItem.append("Result " + resultBarcode);
    	Integer xmlResFile =  R.xml.barcodes;
    	new backgroundAsyncTask().execute(xmlResFile);

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
    		//Toast.makeText(this, "Scan Result = " + data.getStringExtra(ZBarConstants.SCAN_RESULT), Toast.LENGTH_LONG).show();
    		
    		resultBarcode = data.getStringExtra(ZBarConstants.SCAN_RESULT);

    		startXml();

    	} else if(resultCode == RESULT_CANCELED && data != null) {
    		String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
    		if(!TextUtils.isEmpty(error)) {
    			Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    		}
    	}
    }

    public class backgroundAsyncTask extends
	AsyncTask<Integer, Void, StringBuilder> {

    	ProgressDialog dialog = new ProgressDialog(BarcodeScan.this);

    	@Override
    	protected void onPostExecute(StringBuilder result) {
    		super.onPostExecute(result);
    		dialog.dismiss();
    		txtItem.append (result.toString());
    		//txtItem.append( "\n" + resultBarcode);
    	}

    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		dialog.setMessage("Please wait...");
    		dialog.setCancelable(false);
    		dialog.show();

    	}

    	@Override
    	protected void onProgressUpdate(Void... values) {
    		super.onProgressUpdate(values);
    	}

    	@Override
    	protected StringBuilder doInBackground(Integer... params) {
    		int xmlResFile = params[0];
    		XmlPullParser parser = getResources().getXml(xmlResFile);

    		StringBuilder stringBuilder = new StringBuilder();
    		String nodeText = "";
    		String nodeName = "";
    		try {
    			int eventType = -1;
    			while (eventType != XmlPullParser.END_DOCUMENT) {

    				eventType = parser.next();

    				if (eventType == XmlPullParser.START_DOCUMENT) {
    				

    				} else if (eventType == XmlPullParser.END_DOCUMENT) {
    				

    				} else if (eventType == XmlPullParser.START_TAG) {
    					//nodeName = parser.getName();
    					//stringBuilder.append("\nSTART_TAG: " + nodeName);

    					stringBuilder.append(getAttributes(parser));


    				} else if (eventType == XmlPullParser.END_TAG) {
    					//nodeName = parser.getName();
    					//stringBuilder.append("\nEND_TAG:   " + nodeName );

    				} else if (eventType == XmlPullParser.TEXT && n == 1) {
    					nodeText = parser.getText();
    					stringBuilder.append( nodeText);

    					n =0;

    				}
    			}
    		} catch (Exception e) {
    			Log.e("<<PARSING ERROR>>", e.getMessage());

    		}

    		return stringBuilder;
    	}// doInBackground

    	private String getAttributes(XmlPullParser parser) {
    		StringBuilder stringBuilder = new StringBuilder();
    		// trying to detect inner attributes nested inside a node tag
    		String name = parser.getName();
    		if (name != null) {
    			int size = parser.getAttributeCount();

    			for (int i = 0; i < size; i++) {
    				String attrName = parser.getAttributeName(i);

    				String attrValue = parser.getAttributeValue(i);

    				if(attrValue.equals(resultBarcode))
    				{
    					n = 1;	

    				}
    			}
    		}
    		return stringBuilder.toString();

    	}// innerElements

    }// backroundAsyncTask
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
			db.execSQL("create table shoppingList ("
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
				db.execSQL("insert into shoppingList (Item,Price, Quantity) "
						+ " values ('"+ storeItem.get(i)+"', '" + price.get(i)+"', '" + quantity+"');");
			}
			db.setTransactionSuccessful();
			
			
		} catch (SQLiteException e2) {
			 txtMsg.append("\nError insertSomeDbData: " + e2.getMessage());
			
		} finally {
			db.endTransaction();
		}

	}
	private void insertData() {
		
		
		db.beginTransaction();
		try {
			
			for(int i = 0; i < storeItem.size(); i++){
				db.execSQL("insert into shoppingList (Item,Price, Quantity) "
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
	

	@Override
	public void onBackPressed()
	{
		Intent intent = new Intent (BarcodeScan.this,MainActivity.class);
	
		intent.putExtra("main", 1);
		startActivity(intent);
	
		db.close();
	    finish();  
	}




}
