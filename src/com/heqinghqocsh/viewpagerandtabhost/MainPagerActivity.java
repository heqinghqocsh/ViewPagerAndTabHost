package com.heqinghqocsh.viewpagerandtabhost;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.heqinghqocsh.viewpagerandtabhost.widget.MyViewPager;

public class MainPagerActivity extends FragmentActivity {

	private boolean isOnKeyBacking = false;
	private final Handler mainLoopHandler = new Handler(Looper.getMainLooper());

	private MyViewPager mViewPager;
	private final static int TOTAL_PATE = 4;
	private ImageView[] bottomBar;
	private int[] bk_ids = {R.drawable.home_pressed,R.drawable.note_message_normal
			,R.drawable.contact_normal,R.drawable.me_normal
			,R.drawable.home_pressed,R.drawable.note_message_pressed
			,R.drawable.contact_pressed,R.drawable.me_pressed};

	private GestureDetector gestureDetector;
	private ChangeTab changeTab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);
		gestureDetector = new GestureDetector(new TabHostGestureListener());
		bottomBar = new ImageView[TOTAL_PATE];
		bottomBar[0] = (ImageView) findViewById(R.id.home_tutor);
		bottomBar[1] = (ImageView) findViewById(R.id.note_tutor);
		bottomBar[2] = (ImageView) findViewById(R.id.contact_tutor);
		bottomBar[3] = (ImageView) findViewById(R.id.me_tutor);

		mViewPager = (MyViewPager) findViewById(R.id.mainViewPager);
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setCurrentItem(0);
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			@Override
			public int getCount() {
				return TOTAL_PATE;
			}
			@Override
			public Fragment getItem(int index) {
				switch (index) {
				case 0:
					return new HomeFragmet();
				case 1:
					return new NoteListFragment();
				case 2:
					ContactFragment contactFragment = new ContactFragment();
					changeTab = contactFragment;
					return contactFragment;
				case 3:
					return new MePageFragment();
				default:
					return new HomeFragmet();
				}
			}
		});
		viewPagerChange();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (gestureDetector.onTouchEvent(ev)) {
			ev.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(ev);
	}

	private class TabHostGestureListener extends SimpleOnGestureListener{
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if (mViewPager.getCurrentItem() == 2) {
				if (changeTab.getCurTabIndex() == 0) {
					if (e2.getX() - e1.getX() < -50) {
						changeTab.changeTabItem(1);
					}else if (e2.getX() - e1.getX() > 50) {
						mViewPager.setCurrentItem(1);
					}
					return true;
				}
				if (changeTab.getCurTabIndex() == 1) {
					if (e2.getX() - e1.getX() < -50) {
						changeTab.changeTabItem(2);
					}else if (e2.getX() - e1.getX() > 50) {
						changeTab.changeTabItem(0);
					}
					return true;
				}
				if (changeTab.getCurTabIndex() == 2) {
					if (e2.getX() - e1.getX() < -50) {
						mViewPager.setCurrentItem(3);
					}else if (e2.getX() - e1.getX() > 50) {
						changeTab.changeTabItem(1);
					}
					return true;
				}
			}
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}

	private void viewPagerChange() {
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {
				for (int i = 0; i < TOTAL_PATE; i++) {
					if (index == i) {
						bottomBar[i].setBackgroundResource(bk_ids[i+TOTAL_PATE]);
					}else {
						bottomBar[i].setBackgroundResource(bk_ids[i]);
					}
				}
				if (index == 2) {
					mViewPager.setScroll_able(false);
				}else {
					mViewPager.setScroll_able(true);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	/**
	 * 点击的时候切换页面
	 * @param view
	 */
	public void changePage(View view){
		int i = 0;
		switch (view.getId()) {
		case R.id.home_layout:
			i = 0;
			break;
		case R.id.note_layout:
			i = 1;
			break;
		case R.id.contact_layout:
			i = 2;
			break;
		case R.id.me_layout:
			i = 3;
			break;
		}
		mViewPager.setCurrentItem(i);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isOnKeyBacking) {
				mainLoopHandler.removeCallbacks(onBackTimeThread);
				isOnKeyBacking = false;
				finish();
			}else {
				isOnKeyBacking = true;
				Toast.makeText(this, "再按一下退出", Toast.LENGTH_SHORT).show();
				mainLoopHandler.postDelayed(onBackTimeThread, 2000);
			}
			return true;
		}else {
			return super.onKeyDown(keyCode, event);
		}
	}

	private final Runnable onBackTimeThread = new Runnable(){
		public void run() {
			isOnKeyBacking = false;
		}
	};

	public interface ChangeTab{
		public void changeTabItem(int index);
		public int getCurTabIndex();
	}

}
