package com.navigation.drawer.activitys;

import android.os.Bundle;

/**
 * Diagnostics Main Function
 *
 *
 */
public class Item1Activity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		getLayoutInflater().inflate(R.layout.item_1_layout, frameLayout);
		

		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		
		//((ImageView)findViewById(R.id.image_view)).setBackgroundResource(R.drawable.image1);
	}



}
