package com.heqinghqocsh.viewpagerandtabhost.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.heqinghqocsh.viewpagerandtabhost.R;

public class SlideBar extends View{

	private Paint paint;
	private TextView header;
	private float height;
	private ListView mListView;
	private Context context;
	
	private String[] sections;

	public SlideBar(Context context,AttributeSet attrs) {
		super(context,attrs);
		this.context = context;
		init();
	}
	
	private void init() {
		sections = new String[]{"#","A","B","C","D","E", "F", "G", "H"
				, "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S"
				, "T", "U", "V", "W", "X", "Y", "Z"};
		paint = new Paint();
		paint.setColor(Color.DKGRAY);
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(40);
	}
	
	public void setListView(ListView listView){
		this.mListView = listView;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		float center = getWidth() / 2;
		height = getHeight() / sections.length;
		for (int i = 0; i < sections.length; i++) {
			canvas.drawText(sections[i], center, height * (i + 1), paint);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (header == null) {
				header = (TextView) ((View)getParent())
						.findViewById(R.id.floating_header);
			}
			setHeaderAndScroll(event);
			header.setVisibility(View.VISIBLE);
			setBackgroundResource(R.drawable.slidebar_bk_pressed);
			return true;
		case MotionEvent.ACTION_MOVE:
			setHeaderAndScroll(event);
			return true;
		case MotionEvent.ACTION_UP:

		case MotionEvent.ACTION_CANCEL:
			header.setVisibility(View.INVISIBLE);
			setBackgroundColor(Color.TRANSPARENT);
			return true;
		}
		return super.onTouchEvent(event);
	}
	
	private void setHeaderAndScroll(MotionEvent event) {
		if (mListView == null) {
			return;
		}
		String headerStr = sections[selectForPoint(event.getY())];
		header.setText(headerStr);
		Adapter adapter = mListView.getAdapter();
		
	}

	private int selectForPoint(float y){
		int index = (int)(y / height);
		if (index < 0) {
			index = 0;
		}
		if (index > sections.length - 1) {
			index = sections.length - 1;
		}
		return index;
	}
	

}
