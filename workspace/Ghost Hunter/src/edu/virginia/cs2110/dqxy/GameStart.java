package edu.virginia.cs2110.dqxy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class GameStart extends Activity {
	MapView map;
	UIThread ui;
	Bitmap humanImage;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {// 此方法在ui线程运行
			switch (msg.what) {
			// case 1:
			// // mImageView.setImageBitmap((Bitmap) msg.obj);//
			// imageview显示从网络获取到的logo
			// // Toast.makeText(getApplication(),
			// // getApplication().getString(R.string.get_pic_success),
			// Toast.LENGTH_LONG).show();
			// break;

			// case 0:
			// Toast.makeText(getApplication(),
			// // getApplication().getString(R.string.get_pic_failure),
			// Toast.LENGTH_LONG).show();
			// break;
			}
		}
	};

	// Override the onCreate method
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// if (savedInstanceState == null) {

		// For dynamic display (added)
		// Walker changer = new Walker(); // new class
		map = new MapView(this);
		setContentView(map); // set the ContentView to be the circle view

		// For dynamic display (added)
		// changer.execute(map); // goes after setContentView

		map.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				map.getHuman().setX_(me.getX());
				map.getHuman().setY_(me.getY());
				return true;
			}
		});
		ui = new UIThread();
		ui.execute(map);

		// ui = new UI(this, map);
		// Movemove movemove = new Movemove(map);
		// movemove.start();

		// ui.start();

		Log.d("a", "the end of this create");
		// } else {
		// long value = savedInstanceState.getLong("param");
		// }
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("GameActivity", "Started");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("GameActivity", "Restarted");
	}

	@Override
	protected void onResume() {
		// or
		super.onResume();
		Log.d("GameActivity", "Resumed");
	}

	// @Override
	// protected void onSaveInstanceState(Bundle savedInstanceState) {
	// super.onSaveInstanceState(savedInstanceState);
	// savedInstanceState.putFloat("location", );
	// }

	@Override
	protected void onPause() {

		// request update and save the variable into the bundle
		onSaveInstanceState(new Bundle());
		// savesdfadfsadfsfdsdafsdf

		// wait()

		Log.d("GameActivity", "Paused");
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("GameActivity", "Stopped");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("GameActivity", "Destroyed");
	}

}
