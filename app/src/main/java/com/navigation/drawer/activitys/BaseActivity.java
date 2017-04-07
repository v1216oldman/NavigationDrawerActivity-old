package com.navigation.drawer.activitys;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.navigation.drawer.adapters.NavigationDrawerListAdapter;
import com.navigation.drawer.models.Items;


public class BaseActivity extends Activity {

	protected FrameLayout frameLayout;
	protected ListView mDrawerList;
	protected String[] listArray = { "Home", "Diagnostics", "Dashboard", "Maps", "Monitors", "Logs" ,"Setting", "About"};
	protected ArrayList<Items> _items;
	protected static int position;
	private static boolean isLaunch = true;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation_drawer_base_layout);


		ActivityCollector.addActivity(this);
		
		frameLayout = (FrameLayout)findViewById(R.id.content_frame);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		// set a custom shadow that overlays the main content when the drawer opens
		//mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
		_items = new ArrayList<Items>();
		_items.add(new Items(R.string.Item_One, R.string.Item_One_Description, R.drawable.item_1));
		_items.add(new Items(R.string.Item_Two, R.string.Item_Two_Description, R.drawable.item_2));
		_items.add(new Items(R.string.Item_Three,R.string.Item_Three_Description, R.drawable.item_3));
		_items.add(new Items(R.string.Item_Four, R.string.Item_Four_Description, R.drawable.item_4));
		_items.add(new Items(R.string.Item_Five, R.string.Item_Five_Description, R.drawable.item_5));
		_items.add(new Items(R.string.Item_Six, R.string.Item_Six_Description, R.drawable.item_6));
		_items.add(new Items(R.string.Item_Seven, R.string.Item_Seven_Description, R.drawable.item_7));
		
		//Adding header on list view 
		View header = (View)getLayoutInflater().inflate(R.layout.list_view_header_layout, null);
		mDrawerList.addHeaderView(header);
		
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new NavigationDrawerListAdapter(this, _items));
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				openActivity(position);
			}
		});
		
		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			getActionBar().setHomeButtonEnabled(true);
		}

		// ActionBarDrawerToggle ties together the the proper interactions between the sliding drawer and the action bar app icon
		actionBarDrawerToggle = new ActionBarDrawerToggle(
				this,						/* host Activity */
				mDrawerLayout, 				/* DrawerLayout object */
				R.drawable.obd2,     /* nav drawer image to replace 'Up' caret */
				R.string.open_drawer,       /* "open drawer" description for accessibility */
				R.string.close_drawer)      /* "close drawer" description for accessibility */ 
		{ 
			@Override
			public void onDrawerClosed(View drawerView) {
				getActionBar().setTitle(listArray[position]);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(getString(R.string.app_name));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, slideOffset);
			}

			@Override
			public void onDrawerStateChanged(int newState) {
				super.onDrawerStateChanged(newState);
			}
		};
		mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

		if(isLaunch){

			isLaunch = false;
			openActivity(0);
		}
	}
	

	protected void openActivity(int position) {


//		mDrawerList.setItemChecked(position, true);
//		setTitle(listArray[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
		BaseActivity.position = position; //Setting currently selected position in this field so that it will be available in our child activities. 
		
		switch (position) {
		case 0:
			startActivity(new Intent(this, HomeActivity.class));
			break;
		case 1:
			startActivity(new Intent(this, Item1Activity.class));
			break;
		case 2:
			startActivity(new Intent(this, Item2Activity.class));
			break;
		case 3:
			startActivity(new Intent(this, Item3Activity.class));
			break;
		case 4:
			startActivity(new Intent(this, Item4Activity.class));
			break;
		case 5:
			startActivity(new Intent(this, Item5Activity.class));
			break;
		case 6:
			//ActivityCollector.removeActivity(this);
			startActivity(new Intent(this, Item6Activity.class));
			break;
		case 7:
			startActivity(new Intent(this, Item7Activity.class));
			break;

		default:
			startActivity(new Intent(this, HomeActivity.class));
			break;
		}
		
		Toast.makeText(this, "Selected Item Position::"+position, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// The action bar home/up action should open or close the drawer. 
		// ActionBarDrawerToggle will take care of this.
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
		
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
    /* We can override onBackPressed method to toggle navigation drawer*/
	@Override
	public void onBackPressed() {
		if(mDrawerLayout.isDrawerOpen(mDrawerList)){
			mDrawerLayout.closeDrawer(mDrawerList);
		}else {
			mDrawerLayout.openDrawer(mDrawerList);
		}
	}


	@Override
	public void onResume() {
		super.onResume();

	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}




	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			new AlertDialog.Builder(BaseActivity.this)
					.setTitle(R.string.Title_Back)
					.setMessage(R.string.Message_Back)
					.setIcon(R.drawable.exit)
					.setPositiveButton(R.string.confirm,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
													int which) {
									ActivityCollector.finishAll();
									//finish();
								}
							})
					.setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
													int which) {
									// TODO Auto-generated method stub

								}
							}).show();
		}
		return true;
	}
}
