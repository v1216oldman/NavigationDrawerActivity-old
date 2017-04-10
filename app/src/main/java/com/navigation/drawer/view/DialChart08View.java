package com.navigation.drawer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import org.xclcharts.chart.DialChart;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.plot.PlotAttrInfo;
import org.xclcharts.view.GraphicalView;

import java.util.ArrayList;
import java.util.List;

public class DialChart08View extends GraphicalView {

	private String TAG = "DialChart05View";
	private DialChart chart = new DialChart();
	private float mPercentage = 0.1f;

	float mP1 = 0.0f;
	float mP2 =  0.0f;

	public DialChart08View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}

	public DialChart08View(Context context, AttributeSet attrs){
        super(context, attrs);
        initView();
	 }

	 public DialChart08View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView()
	 {
		chartRender();
	 }
	 
	 @Override  
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
	        super.onSizeChanged(w, h, oldw, oldh);  
	        chart.setChartRange(w ,h ); 
	    }  		
			
	 public void chartRender()
		{
			try {								
							
				//设置标题背景			
				chart.setApplyBackgroundColor(true);
				chart.setBackgroundColor( Color.rgb(28, 129, 243) );
				//绘制边框
				chart.showRoundBorder();
						
				//设置当前百分比
				chart.getPointer().setPercentage(mPercentage);
				
				//设置指针长度
				chart.getPointer().setLength(0.6f);
				
				//增加轴
				addAxis();						
				/////////////////////////////////////////////////////////////
				addPointer();
				//设置附加信息
				addAttrInfo();
				/////////////////////////////////////////////////////////////
												
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.toString());
			}
			
		}
		
		public void addAxis()
		{
		
			List<String> rlabels  = new ArrayList<String>();
			int j=0;
			for(int i=0;i<=100;)
			{
				if(0 == i || j == 5)
				{
					rlabels.add(Integer.toString(i));
					j = 0;
				}else{
					rlabels.add("");
					j++;
				}
										
				i+=2;
			}
			chart.addOuterTicksAxis(0.8f, rlabels);
			
			//环形颜色轴
			List<Float> ringPercentage = new ArrayList<Float>();				
			ringPercentage.add( 0.33f);
			ringPercentage.add( 0.33f);
			ringPercentage.add( 1 - 2 * 0.33f);
			
			List<Integer> rcolor  = new ArrayList<Integer>();				
			rcolor.add(Color.rgb(133, 206, 130));
			rcolor.add(Color.rgb(252, 210, 9));		
			rcolor.add(Color.rgb(229, 63, 56));	
			chart.addStrokeRingAxis(0.8f,0.7f, ringPercentage, rcolor);
									
			List<String> rlabels2  = new ArrayList<String>();
			for(int i=0;i<=10;i++)
			{
				rlabels2.add(Integer.toString(i*10));
			}
			chart.addInnerTicksAxis(0.7f, rlabels2);
								
			chart.getPlotAxis().get(1).getFillAxisPaint().setColor(Color.rgb(28, 129, 243) );
			
			chart.getPlotAxis().get(0).hideAxisLine();
			chart.getPlotAxis().get(2).hideAxisLine();
			chart.getPlotAxis().get(0).getTickMarksPaint().setColor(Color.YELLOW);
			chart.getPlotAxis().get(2).getTickMarksPaint().setColor(Color.WHITE);
			chart.getPlotAxis().get(2).getTickLabelPaint().setColor(Color.WHITE);
		
			
		}
		
		
		private void addAttrInfo()
		{
			PlotAttrInfo plotAttrInfo = chart.getPlotAttrInfo();
			//设置附加信息
			Paint paintTB = new Paint();
			paintTB.setColor(Color.WHITE);
			paintTB.setTextAlign(Paint.Align.CENTER);
			paintTB.setTextSize(30);
			paintTB.setAntiAlias(true);
			plotAttrInfo.addAttributeInfo(XEnum.Location.TOP, "MPH", 0.2f, paintTB);


			Paint paintBT = new Paint();
			paintBT.setColor(Color.WHITE);
			paintBT.setTextAlign(Paint.Align.CENTER);
			paintBT.setTextSize(60);
			paintBT.setFakeBoldText(true);
			paintBT.setAntiAlias(true);
			plotAttrInfo.addAttributeInfo(XEnum.Location.BOTTOM,
			Float.toString(MathHelper.getInstance().round(mPercentage * 100,2)), 0.3f, paintBT);


			Paint paintBT2 = new Paint();
			paintBT2.setColor(Color.WHITE);
			paintBT2.setTextAlign(Paint.Align.CENTER);
			paintBT2.setTextSize(30);
			paintBT2.setFakeBoldText(true);
			paintBT2.setAntiAlias(true);
			plotAttrInfo.addAttributeInfo(XEnum.Location.BOTTOM, "Km/h", 0.4f, paintBT2);
			
		}
		
		public void addPointer()
		{				

		}
		public void setCurrentStatus(float percentage)
		{
			//清理
			chart.clearAll();
					
			mPercentage =  percentage;
			//设置当前百分比
			chart.getPointer().setPercentage(mPercentage);
			addAxis();						
			addPointer();
			addAttrInfo();
		}


		@Override
		public void render(Canvas canvas) {
			// TODO Auto-generated method stub
			 try{
		            chart.render(canvas);
		        } catch (Exception e){
		        	Log.e(TAG, e.toString());
		        }
		}

}
