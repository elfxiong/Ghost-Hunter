package edu.virginia.cs2110.dqxy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Popup extends Activity{
	MapView map;
	Bitmap humanImage;

	// Override the onCreate method
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		map = new MapView(this); // entire pop up takes CircleView
		// this- the savedInstanceState passed into onCreate method

		map.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				map.getHuman().setX_(me.getX());
				map.getHuman().setY_(me.getY());
				Log.d("OnTouch", "Get Called");
				return true;
			}
		});

		// For dynamic display (added)
		Changer changer = new Changer(); // new class

		setContentView(map); // set the ContentView to be the circle view

		// For dynamic display (added)
		changer.execute(map); // goes after setContentView

	}

}
