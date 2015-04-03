package fyp.shopsmart.employee;

import fyp.shopsmart.R;
import fyp.shopsmart.customer.MainActivity;
import fyp.shopsmart.customer.SignIn;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class EmployeeMenu extends Activity {
	
	Button btnStaffH;
	Button btnRota;
	Button btnStock;
	Button btnPricing;
	Button btnManage;
	int check;
	int john;
	int ian;
	int sarah;
	int aishling;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_menu);
		
		check = 0;
		
		 btnStaffH = (Button) findViewById(R.id.staffhours);
		 btnPricing  = (Button) findViewById(R.id.btnpricing);
		 btnRota = (Button) findViewById(R.id.rota);
		 btnStock = (Button) findViewById(R.id.updatestock);
		 btnManage = (Button) findViewById(R.id.btnmanage);
		 Intent intent = getIntent();	
	  	 ian = intent.getIntExtra("ian",0);
	  	 john = intent.getIntExtra("john",0);
	  	 sarah= intent.getIntExtra("sarah",0);
	  	 aishling = intent.getIntExtra("aishling",0);

	  	  	
		 if(john == 1)
	  	 {
	  		 
			 //time = intent.getStringExtra("jtime");
			 //Toast.makeText(this, "time" +time, Toast.LENGTH_LONG).show();
			 
	  		 setTitle("ShopSmart"+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"John");
	  		 check=1;
	  		 
	  	 }		
		 if(ian == 1)
	  	 {
			 //time = intent.getStringExtra("itime");
			// Toast.makeText(this, "time" +time, Toast.LENGTH_LONG).show();
			 
	  		 setTitle("ShopSmart"+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  "+"Ian");
	  		 check = 2;
	  		 
	  	 }	
		 if(sarah == 1)
	  	 {
			 //time = intent.getStringExtra("stime");
			// Toast.makeText(this, "time" +time, Toast.LENGTH_LONG).show();
	  		 setTitle("ShopSmart"+ "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Sarah");
	  		 check=3;
	  		 
	  	 }		
	  	 if(aishling == 1)
	  	 {
	  		 //time = intent.getStringExtra("atime");
	  		// Toast.makeText(this, "time" +time, Toast.LENGTH_LONG).show();
	  		 setTitle("ShopSmart"+ "\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Aishling");
	  		 check=4;
	  		 
	  	 }		
		 btnStaffH.setOnClickListener(new OnClickListener() {	
		      	public void onClick(View v) {
		      		
					Intent staffHours = new Intent (EmployeeMenu.this,StaffHours.class);
					if(check == 1)
					{
						staffHours.putExtra("SignedIn" , 1);
						startActivity(staffHours);		
					}
					if(check == 2)
					{
						staffHours.putExtra("SignedIn" , 2);
						startActivity(staffHours);		
					}
					if(check == 3)
					{
						staffHours.putExtra("SignedIn" , 3);
						startActivity(staffHours);		
					}
					if(check == 4)
					{
						staffHours.putExtra("SignedIn" , 4);
						startActivity(staffHours);		
					}
						
		    }		
		 });
		 
		 btnRota.setOnClickListener(new OnClickListener() {	
		      	public void onClick(View v) {
		      		
					Intent staffHours = new Intent (EmployeeMenu.this,Rota.class);
					if(check == 1)
					{
						staffHours.putExtra("SignedIn" , 1);
						startActivity(staffHours);		
					}
					if(check == 2)
					{
						staffHours.putExtra("SignedIn" , 2);
						startActivity(staffHours);		
					}
					if(check == 3)
					{
						staffHours.putExtra("SignedIn" , 3);
						startActivity(staffHours);		
					}
					if(check == 4)
					{
						staffHours.putExtra("SignedIn" , 4);
						startActivity(staffHours);		
					}
						
		    }		
		 });
		 btnManage.setOnClickListener(new OnClickListener() {	
		      	public void onClick(View v) {
		      		
					Intent manage = new Intent (EmployeeMenu.this,Manage.class);
					if(check == 1)
					{
						manage.putExtra("SignedIn" , 1);
						startActivity(manage);		
					}
					if(check == 2)
					{
						manage.putExtra("SignedIn" , 2);
						startActivity(manage);		
					}
					if(check == 3)
					{
						manage.putExtra("SignedIn" , 3);
						startActivity(manage);		
					}
					if(check == 4)
					{
						manage.putExtra("SignedIn" , 4);
						startActivity(manage);		
					}
						
		    }		
		 });
		 
		 btnPricing.setOnClickListener(new OnClickListener() {	
		      	public void onClick(View v) {
		      		
					Intent pricing= new Intent (EmployeeMenu.this,Pricing.class);

						
						startActivity(pricing);		

						
		    }		
		 });
		 btnStock.setOnClickListener(new OnClickListener() {	
		      	public void onClick(View v) {
		      		
					Intent stock= new Intent (EmployeeMenu.this,UpdateStock.class);

						
						startActivity(stock);		

						
		    }		
		 });
		 
		 
	}

}
