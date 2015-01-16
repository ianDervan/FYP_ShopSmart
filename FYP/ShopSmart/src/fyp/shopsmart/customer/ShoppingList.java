package fyp.shopsmart.customer;

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

public class ShoppingList extends Activity {
	
	AutoCompleteTextView textView;
	TextView txtMsg;
	Button  btnClearText;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_list);
		
		txtMsg = (TextView) findViewById(R.id.txtMsg);
		btnClearText = (Button) findViewById(R.id.cleartext);

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
