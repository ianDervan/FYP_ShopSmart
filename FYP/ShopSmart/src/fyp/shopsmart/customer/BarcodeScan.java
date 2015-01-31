package fyp.shopsmart.customer;


import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
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
	String resultBarcode;
	
	int n =0;
	
	int check =0;
    private static final int ZBAR_SCANNER_REQUEST = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scan);
        
        btnScan = (Button) findViewById(R.id.scanBarcode);
        txtItem = (TextView) findViewById(R.id.item);
        
        btnScan.setOnClickListener(new OnClickListener() {	
    		public void onClick(View v) {
    			
    			    			
    			startScanner();
    			 
    	        //txtItem.append("\nHOW" + i);
    			
    			// InputStream is=getResources().openRawResource(res.values.my_xml);
    			
    		
    			//parse using builder to get DOM representation of the XML file
    	    	if(check==1)
    			{

    				startXml();
    			}
    		
    			
    		}
    	});
        

        
        //startXml();
        
    }
    
    public void startXml()
    {
    	
    
    		 Toast.makeText(this, "Im in Here", Toast.LENGTH_LONG).show();
    			//txtItem.setText("");
				//txtItem.append (" " +resultBarcode + "\n");
				//Integer xmlResFile =  R.xml.barcodes;
				//new backgroundAsyncTask().execute(xmlResFile);
	
    	
    	
    	
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
    		check=1;
    		resultBarcode = data.getStringExtra(ZBarConstants.SCAN_RESULT);
    		txtItem.append(resultBarcode +"\n");
    		
    		
    		//txtItem.append("\n");
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
	txtItem.append( "\n" + resultBarcode);
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
				//stringBuilder.append("\nSTART_DOCUMENT");

			} else if (eventType == XmlPullParser.END_DOCUMENT) {
				//stringBuilder.append("\nEND_DOCUMENT");

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
			//stringBuilder.append("\n    Attrib <key,value>= "
				//	+ attrName + ", " + attrValue);
			
			//stringBuilder.append("\n" +result);
			
			if( attrValue.equals("\""+resultBarcode+"\""))
			{
				//stringBuilder.append("story");
				
			  n = 1;	
			  //resultBarcode= null;
			}
			
		}

	}
	return stringBuilder.toString();

}// innerElements

}// backroundAsyncTask





	
    
}
