package fyp.shopsmart.customer;

import org.xmlpull.v1.XmlPullParser;

import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import fyp.shopsmart.customer.BarcodeScan.backgroundAsyncTask;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

	String x;
	String y;
	
	float XCord;
	float YCord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		 btnSearch = (Button) findViewById(R.id.search);
		 
		// XCord = Float.parseFloat(x);
		// YCord = Float.parseFloat(y);
		 
	//	 textView1 = (TextView) findViewById(R.id.textView1);
		 textView = (AutoCompleteTextView) findViewById(R.id.autocomplete);
			String[] suggestedItems = getResources().getStringArray(R.array.suggestions_array);
			ArrayAdapter<String> adapter = 
			        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestedItems);
			textView.setAdapter(adapter);
			
			BitmapFactory.Options myOptions = new BitmapFactory.Options();
		    myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important

		    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.grocery1,myOptions);
		   

		    Bitmap workingBitmap = Bitmap.createBitmap(bitmap);
		    Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

		    Canvas canvas = new Canvas(mutableBitmap);
		    
		  	 Paint paint = new Paint();
			    paint.setAntiAlias(true);
			    paint.setColor(Color.BLUE);
	    	
	    	 canvas.drawCircle(XCord, YCord, 25, paint);
		   

		    ImageView imageView = (ImageView)findViewById(R.id.imageView1);
		    imageView.setScaleType(ScaleType.FIT_XY);
		   imageView.setAdjustViewBounds(true);
		   imageView.setImageBitmap(mutableBitmap);
		    
		    btnSearch.setOnClickListener(new OnClickListener() {	
				public void onClick(View v) {
					
					
					searchItem =textView.getText().toString();
					//Toast.makeText(getApplicationContext(), searchItem, Toast.LENGTH_SHORT).show();
			    	Integer xmlResFile =  R.xml.barcodes;
			    	new backgroundAsyncTask().execute(xmlResFile);
			    	
			  
					
					
				}		
			});
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
    public class backgroundAsyncTask extends
	AsyncTask<Integer, Void, StringBuilder> {
    	
    	

    	ProgressDialog dialog = new ProgressDialog(Search.this);
    	

    	@Override
    	protected void onPostExecute(StringBuilder result) {
    		super.onPostExecute(result);
    		dialog.dismiss();
    	//	textView1.append (result.toString());
    		Toast.makeText(getApplicationContext(),x+y, Toast.LENGTH_SHORT).show();
    	//	Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
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
						//stringBuilder.append("\nSTART_DOCUMENT");

					} else if (eventType == XmlPullParser.END_DOCUMENT) {
					//	stringBuilder.append("\nEND_DOCUMENT");

					} else if (eventType == XmlPullParser.START_TAG) {
						nodeName = parser.getName();
						//stringBuilder.append("\nSTART_TAG: " + nodeName);
						
						//nodeText = parser.getText();
						
						if(n == 1)
						{
						 stringBuilder.append(getAttributes(parser));

						}
				

					} else if (eventType == XmlPullParser.END_TAG) {
						nodeName = parser.getName();
						//stringBuilder.append("\nEND_TAG:   " + nodeName );
						
						

					} else if (eventType == XmlPullParser.TEXT) {
						nodeText = parser.getText();
						nodeName = parser.getName();
					
						
						
						if(nodeText.equals(searchItem))
						{
							//0textView1.append("im here");
							//stringBuilder.append("\n    TEXT: ");
							n =1;

						}

					
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
    				
    				stringBuilder.append("X value");
    				
    				if(attrName.equals("X"))
    				{
    					
    					x = attrValue;
    					
    					//stringBuilder.append("X value");
    					stringBuilder.append("X value" + attrValue);
    				}
    				if(attrName.equals("Y"))
    				{
    					y = attrValue;
    					stringBuilder.append("Y value" + attrValue);
    				}

    		
    			}
    		}
    		return stringBuilder.toString();

    	}// innerElements

    }// backroundAsyncTask
}
