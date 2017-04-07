package com.navigation.drawer.activitys;

import android.os.Bundle;

/**
 * Maps Main Function
 *
 */
public class Item3Activity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getLayoutInflater().inflate(R.layout.item_3_layout, frameLayout);
		

		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		

	}
}
