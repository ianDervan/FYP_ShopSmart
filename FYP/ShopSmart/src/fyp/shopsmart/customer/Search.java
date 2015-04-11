package fyp.shopsmart.customer;

import org.xmlpull.v1.XmlPullParser;

import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
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
 
}
