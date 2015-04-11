package fyp.shopsmart.customer;

import fyp.shopsmart.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
	
	 Button btnSign;
	 Button btnShopList;
	 Button btnScan;
	 Button btnSearch;
	 Button btnSpecialOffers;
	 
	 
	 
	 
	 int ok;
	 int backOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);
        
        btnSign = (Button) findViewById(R.id.signIn);
        btnShopList = (Button) findViewById(R.id.shoplist);
        btnScan = (Button) findViewById(R.id.scan);
        btnSearch = (Button) findViewById(R.id.search);
        btnSpecialOffers = (Button) findViewById(R.id.specialoffers);
        
        Intent mainIntent = getIntent();
	    ok = mainIntent.getIntExtra("main",0);

	    
	    backOk = 1;
	     
        btnSign.setOnClickListener(new OnClickListener() {	
      	public void onClick(View v) {
      		
			Intent signIn = new Intent (MainActivity.this,SignIn.class);
			startActivity(signIn);
			
      		}		
 		});
        btnSpecialOffers.setOnClickListener(new OnClickListener() {	
          	public void onClick(View v) {
          		
    			Intent signIn = new Intent (MainActivity.this,SpecialOffers.class);
    			startActivity(signIn);
    			
          		}		
     		});
        btnScan.setOnClickListener(new OnClickListener() {	
          	public void onClick(View v) {
          		
    			Intent scan = new Intent (MainActivity.this,BarcodeScan.class);
    			startActivity(scan);
    			
          		}		
     		});
        
        btnShopList.setOnClickListener(new OnClickListener() {	
          	public void onClick(View v) {
          		
    			Intent shopList = new Intent (MainActivity.this,ShoppingList.class);
    			
    			if(ok == 1)
    			{
    				shopList.putExtra("main", 1);
    			}
    			shopList.putExtra("backOk",1);
    			startActivity(shopList);
    			
    			
    			
          		}		
     		});
        btnSearch.setOnClickListener(new OnClickListener() {	
          	public void onClick(View v) {
          		
    			Intent search = new Intent (MainActivity.this,Search.class);
    			startActivity(search);
    			
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
	@Override
	public void onBackPressed()
	{

	    finish();  
	}
    
    
}
