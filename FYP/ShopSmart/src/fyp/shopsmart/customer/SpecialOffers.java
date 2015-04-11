package fyp.shopsmart.customer;

import fyp.shopsmart.R;
import fyp.shopsmart.R.id;
import fyp.shopsmart.R.layout;
import fyp.shopsmart.R.menu;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class SpecialOffers extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_adapter);

		GridView gridView = (GridView) findViewById(R.id.grid_view);

		// Instance of ImageAdapter Class
		gridView.setAdapter(new ImageAdapter(this));
	}
}