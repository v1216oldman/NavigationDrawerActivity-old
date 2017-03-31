package com.navigation.drawer.activity;

import com.navigation.drawer.adapters.HomeScreenGridViewAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Home mail Function
 *
 */
public class HomeActivity extends BaseActivity implements AdapterView.OnItemClickListener {
	
	
	private GridView gridview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * We will not use setContentView in this activty Rather than we will
		 * use layout inflater to add view in FrameLayout of our base activity
		 * layout
		 */

		/**
		 * Adding our layout to parent class frame layout.
		 */
		getLayoutInflater().inflate(R.layout.home_layout, frameLayout);

		/**
		 * Setting title and itemChecked
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		
		initializeActivity();
	}

	private void initializeActivity() {

		gridview = (GridView)findViewById(R.id.gridview);
		gridview.setAdapter(new HomeScreenGridViewAdapter(this, _items));
		gridview.setOnItemClickListener(this);


	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		switch (position) {

			case 0:
				startActivity(new Intent(this, Item1Activity.class));
				break;
			case 1:
				startActivity(new Intent(this, Item2Activity.class));
				break;
			case 2:
				startActivity(new Intent(this, Item3Activity.class));
				break;
			case 3:
				startActivity(new Intent(this, Item4Activity.class));
				break;
			case 4:
				startActivity(new Intent(this, Item5Activity.class));
				break;
			case 5:
				startActivity(new Intent(this, Item6Activity.class));
				break;
			case 6:
				startActivity(new Intent(this, Item7Activity.class));
				break;
			default:
				startActivity(new Intent(this, HomeActivity.class));
				break;
		}
		Toast.makeText(this, "Selected Item Position::"+position, Toast.LENGTH_LONG).show();
	}
}
