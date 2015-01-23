package fyp.shopsmart.customer;

import fyp.shopsmart.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
	
	 Button btnSign;
	 Button btnShopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);
        
        btnSign = (Button) findViewById(R.id.signIn);
        btnShopList = (Button) findViewById(R.id.shoplist);
        
        
        btnSign.setOnClickListener(new OnClickListener() {	
      	public void onClick(View v) {
      		
			Intent signIn = new Intent (MainActivity.this,SignIn.class);
			startActivity(signIn);
			
      		}		
 		});
        
        btnShopList.setOnClickListener(new OnClickListener() {	
          	public void onClick(View v) {
          		
    			Intent shopList = new Intent (MainActivity.this,ShoppingList.class);
    			startActivity(shopList);
    			
          		}		
     		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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