package com.navigation.drawer.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.navigation.drawer.view.DialChart02View;
import com.navigation.drawer.view.DialChart08View;
import com.navigation.drawer.view.DialChart03View;
import com.navigation.drawer.view.DialChart05View;
import com.navigation.drawer.view.DialChart06View;
import com.navigation.drawer.view.DialChart09View;


import java.util.Random;


/**
 * Dashboard Main Function
 *
 */
public class Item2Activity extends BaseActivity {

	DialChart08View chart1 = null;//MPH(汽車速度)
	DialChart05View chart2 = null;//Engine Temp(引擎溫度)
	DialChart06View chart3 = null;//RPM(轉速)
	DialChart03View chart4 = null;//MAF(空氣流量)
	DialChart09View chart5 = null;//Fuel rate(燃料費率)
	DialChart02View chart6 = null;//Battery(電瓶電壓)



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getLayoutInflater().inflate(R.layout.item_2_layout, frameLayout);
		chart1 = (DialChart08View)findViewById(R.id.circle_view);
		chart2 = (DialChart05View)findViewById(R.id.circle_view1);
		chart3 = (DialChart06View)findViewById(R.id.circle_view2);
		chart4 = (DialChart03View)findViewById(R.id.circle_view3);
		chart5 = (DialChart09View)findViewById(R.id.circle_view4);
		chart6 = (DialChart02View)findViewById(R.id.circle_view5);

		handler.post(updateThread); //Start update Thread

		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		

	}


	Handler handler = new Handler();
	Runnable updateThread = new Runnable(){

		public void run(){
			System.out.println("update Thread Start");
			Log.d("log","update Thread Start");
			MPH();
			Engine_Temp();
			RPM();
			MAF();
			Fuel_rate();
			Battery();
			handler.postDelayed(updateThread, 2000);
		}
	};



	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacks(updateThread);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, Menu.FIRST + 1, 0, R.string.help);
		menu.add(Menu.NONE, Menu.FIRST + 2, 0, R.string.about);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch(item.getItemId())
		{
			case Menu.FIRST+1:

				break;
			case Menu.FIRST+2:

				break;
		}
		return true;
	}


	private void MPH(){
		int max = 100;
		int min = 1;
		Random random = new Random();
		int p = random.nextInt(max)%(max-min+1) + min;
		float pf = p / 100f;
		chart1.setCurrentStatus(pf);
		chart1.invalidate();

	}

	private void Engine_Temp(){
		int max = 100;
		int min = 1;
		Random random = new Random();
		int p = random.nextInt(max)%(max-min+1) + min;
		float pf = p / 100f;
		chart2.setCurrentStatus(pf);
		chart2.invalidate();
	}

	private void RPM(){
		int max = 100;
		int min = 1;
		Random random = new Random();
		int p = random.nextInt(max)%(max-min+1) + min;
		float pf = p / 100f;
		chart3.setCurrentStatus(pf);
		chart3.invalidate();
	}

	private void MAF(){
		int max = 100;
		int min = 1;
		Random random = new Random();
		int p = random.nextInt(max)%(max-min+1) + min;
		float pf = p / 100f;
		chart4.setCurrentStatus(pf);
		chart4.invalidate();
	}

	private void Fuel_rate(){
		int max = 100;
		int min = 1;
		Random random = new Random();
		int p = random.nextInt(max)%(max-min+1) + min;
		float pf = p / 100f;
		chart5.setCurrentStatus(pf);
		chart5.invalidate();
	}

	private void Battery(){

		int max = 12;
		int min = 1;
		Random random = new Random();
		int p = random.nextInt(max)%(max-min+1) + min;
		float pf = p / 100f;
		chart6.setCurrentStatus(pf);
		chart6.invalidate();
	}
}
