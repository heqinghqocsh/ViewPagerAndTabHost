package com.heqinghqocsh.viewpagerandtabhost;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class HomeFragmet extends Fragment{

	private ViewPager viewPager;
	private MyPagerAdapter myPagerAdapter;
	private List<View> pageViews;
	private ImageView[] imageViews;
	private ImageView imageView;
	//private int currentItem = 0;
	private GridView mGridView;
	
	private Integer[] ids = {R.drawable.distribution_market
			,R.drawable.parking_place,R.drawable.cater,R.drawable.hotel
			,R.drawable.tire_shop,R.drawable.butter,R.drawable.repair_shop
			,R.drawable.over_load};
	private String[] tips = {"配货市场","停车场","餐饮","住宿"
			,"轮胎店","打黄油","修理铺","超限站"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_page, container,false);
		viewPager = (ViewPager) view.findViewById(R.id.viewPager);
		mGridView = (GridView) view.findViewById(R.id.gridView);
		//initViewPager();
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		initViewPager();
		initGridView(getActivity());
		super.onViewCreated(view, savedInstanceState);
	}
	
	private void initViewPager() {
		//viewPager.setTouchDelegate(delegate)
		initPagerAdapter();
		initCirclePoint();
		viewPager.setAdapter(myPagerAdapter);
		viewPager.setOnPageChangeListener(new PagerChangeListener());
	}

	private void initPagerAdapter() {
		pageViews = new ArrayList<View>();
		ImageView img1 = new ImageView(getActivity());
		img1.setBackgroundResource(R.drawable.first);
		img1.setAdjustViewBounds(true);
		pageViews.add(img1);
		
		ImageView img2 = new ImageView(getActivity());
		img2.setBackgroundResource(R.drawable.second);
		img2.setAdjustViewBounds(true);
		img2.setScaleType(ScaleType.CENTER_CROP);
		pageViews.add(img2);
		
		ImageView img3 = new ImageView(getActivity());
		img3.setBackgroundResource(R.drawable.third);
		img3.setAdjustViewBounds(true);
		pageViews.add(img3);
		
		ImageView img4 = new ImageView(getActivity());
		img4.setBackgroundResource(R.drawable.fourth);
		img4.setAdjustViewBounds(true);
		pageViews.add(img4);
		
		myPagerAdapter = new MyPagerAdapter(pageViews);
	}

	private void initCirclePoint() {
		ViewGroup group = (ViewGroup) getView().findViewById(R.id.point_layout);
		imageViews = new ImageView[pageViews.size()];
		LayoutParams params = new LayoutParams(20, 20);
		params.setMargins(30, 0, 0, 0);
		for (int i = 0; i < pageViews.size(); i++) {
			imageView = new ImageView(getActivity());
			imageView.setLayoutParams(params);
			//imageView.set
			imageViews[i] = imageView;
			//初始值，默认第0个选中
			if(i == 0){
				imageViews[i].setBackgroundResource(R.drawable.point_focused);
			}else {
				imageViews[i].setBackgroundResource(R.drawable.point_normal);
			}
			group.addView(imageViews[i]);
		}
	}

	private void initGridView(Activity activity){
		mGridView.setAdapter(new MyGridViewAdapter(activity,ids));
	}
	
	private final class PagerChangeListener implements OnPageChangeListener{
		@Override
		public void onPageScrollStateChanged(int state) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int arg0) {
			//currentItem = arg0;
			
			//重新设置圆点布局集合
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0].setBackgroundResource(R.drawable.point_focused);
				if (arg0 != i) {
					imageViews[i].setBackgroundResource(R.drawable.point_normal);
				}
			}
		}
	}
	
	private final class MyPagerAdapter extends PagerAdapter{
		private List<View> views = null;
		//初始化数据源
		public MyPagerAdapter(List<View> views){
			this.views = views;
		}
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager)container).removeView(views.get(position));
		}
		@Override
		public int getCount() {
			return views.size();
		}
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager)container).addView(views.get(position),0);
			return views.get(position);
		}
	}
	
	
	private final class MyGridViewAdapter extends ArrayAdapter<Integer>{

		public MyGridViewAdapter(Activity activity,Integer[] ids) {
			super(activity, 0,ids);
		}
		
		@Override
		public int getCount() {
			return ids.length;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.grid_view_item, parent, false);
			}
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.fun_icon);
			TextView tip = (TextView) convertView.findViewById(R.id.tip);
			imageView.setBackgroundResource(ids[position]);
			tip.setText(tips[position]);
			return convertView;
		}
	}
	
	
}
