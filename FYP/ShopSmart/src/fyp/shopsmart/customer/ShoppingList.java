package fyp.shopsmart.customer;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import fyp.shopsmart.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingList extends Activity {
	
	AutoCompleteTextView textView;
	TextView txtMsg;
	Button  btnClearText;
	Button  btnAddItem;
	ArrayList<String> storeItem;
	ArrayList<String> storeCost;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_list);
		
		txtMsg = (TextView) findViewById(R.id.txtMsg);
		btnClearText = (Button) findViewById(R.id.cleartext);
		btnAddItem = (Button) findViewById(R.id.addItem);
		
		storeItem = new ArrayList<String>();
		storeCost = new ArrayList<String>();

	    textView = (AutoCompleteTextView) findViewById(R.id.autocomplete);
		String[] suggestedItems = getResources().getStringArray(R.array.suggestions_array);
		ArrayAdapter<String> adapter = 
		        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestedItems);
		textView.setAdapter(adapter);
		
		btnClearText.setOnClickListener(new OnClickListener() {	
	      	public void onClick(View v) {
	      		
				textView.setText("");
				
	      		}		
	 		});
	
		
		btnAddItem.setOnClickListener(new OnClickListener() {	
	      	public void onClick(View v) {
	      		
			
				
				String storeInput =textView.getText().toString();
						
				String[] inputItem = storeInput.split("€");
				
			
					
				
				 txtMsg.append(inputItem[0]+"\n");
				 txtMsg.append(inputItem[1]+"\n");
				 
				 
				
				
				
				for (String item : storeItem)
				  {
				    txtMsg.setText(item+"\n");
				  }
				for (String cost : storeCost)
				  {
				    txtMsg.setText(cost+"\n");
				  }
				
				
	      		}		
	 		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shopping_list, menu);
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
