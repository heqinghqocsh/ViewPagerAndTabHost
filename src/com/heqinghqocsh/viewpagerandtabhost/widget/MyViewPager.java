package com.heqinghqocsh.viewpagerandtabhost.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager{
	
	private boolean scroll_able = true;//可滑动

	public MyViewPager(Context context) {
		super(context);
	}
	public MyViewPager(Context context,AttributeSet attrs) {
		super(context,attrs);
	}
	
	public void setScroll_able(boolean scroll_able){
		this.scroll_able = scroll_able;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (scroll_able) {
			return super.dispatchTouchEvent(ev);
		}else {
			return true;
		}
		
	}
	
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (scroll_able) {
			return super.onInterceptTouchEvent(arg0);
		}else {
			return true;
		}
	}

	
	
}
