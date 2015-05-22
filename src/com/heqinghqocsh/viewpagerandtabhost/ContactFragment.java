package com.heqinghqocsh.viewpagerandtabhost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TabHost.OnTabChangeListener;

import com.heqinghqocsh.viewpagerandtabhost.MainPagerActivity.ChangeTab;

public class ContactFragment extends Fragment implements ChangeTab{

	private FragmentTabHost tabHost;
	private int currentItem;//当前的tab 索引
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.contact_page, container,false);
		tabHost = (FragmentTabHost) view.findViewById(R.id.tabHost);
		tabHost.setup(getActivity(), getChildFragmentManager(),android.R.id.tabcontent);
		tabHost.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				
				return false;
			}
		});
		initTabHost();
		
		return view;
	}
	
	private void initTabHost(){
		tabHost.addTab(tabHost.newTabSpec("好友")
				.setIndicator("好友"),Fragment_inner_friends.class,null);
		tabHost.addTab(tabHost.newTabSpec("关注")
				.setIndicator("关注"),Fragment_inner_focus.class,null);
		tabHost.addTab(tabHost.newTabSpec("粉丝")
				.setIndicator("粉丝"),Fragment_inner_fans.class,null);
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String arg0) {
				if (arg0.equals("好友")) {
					currentItem = 0;
				}else if(arg0.equals("关注")){
					currentItem = 1;
				}else {
					currentItem = 2;
				}
			}
		});
	}
	
	
	
	
	
	
	public static class Fragment_inner_friends extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.tab_inner_friends, container,false);
			return view;
		}
	}
	public static class Fragment_inner_focus extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.tab_inner_focus, container,false);
			return view;
		}
	}
	public static class Fragment_inner_fans extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.tab_inner_fans, container,false);
			return view;
		}
	}
	@Override
	public void changeTabItem(int index) {
		tabHost.setCurrentTab(index);
	}
	
	public int getCurTabIndex(){
		return currentItem;
	}

}
