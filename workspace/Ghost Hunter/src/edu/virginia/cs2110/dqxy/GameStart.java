package edu.virginia.cs2110.dqxy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class GameStart extends Activity {

	// ScreenRefresher refresher;
	MoveRunnable postionUpdater;
	Thread positionUpdateThread;
	MapView currentMap;
	boolean newGame;
	ImageButton pauseButton;
	Button kill;
	RelativeLayout ll;
	static Object baton;
	static int money;
	static int score;

	// MapView pastMap;
	// MapView futureMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		money = 0;
		score = 0;
		// pastMap = new MapView(this);
		// futureMap = new MapView(this);

		// currentMap = new MapView(this);
		// refresher = new ScreenRefresher();
		if (savedInstanceState == null) {
			// postionUpdater = new MoveRunnable(currentMap);
			// positionUpdateThread = new Thread(postionUpdater);
			// positionUpdateThread.start(); // start in onResume() rather than
			// // onCreate()
			// // refresher.execute(currentMap);
			// setContentView(currentMap);
		} else {
			// loadGameDataFromSavedInstanceState(savedInstanceState);
		}

		// if (savedInstanceState == null) {
		// loadGameDataFromSharedPreferences(getPreferences(MODE_PRIVATE));
		// } else {
		//
		// loadGameDataFromSavedInstanceState(savedInstanceState);
		// }

		Log.d("GameStart", "Create");
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.baton = new Object();
		// refresher.runnable.onResume(); // the method written by ourselves
		loadGameDataFromSharedPreferences(this.getSharedPreferences(
				getString(R.string.save_slot_1), Context.MODE_PRIVATE));
		postionUpdater = new MoveRunnable(currentMap);
		positionUpdateThread = new Thread(postionUpdater);
		positionUpdateThread.start(); // start in onResume() rather than
										// onCreate()
										// refresher.execute(currentMap);
		currentMap.setOnTouchListener(new MyOnTouchListener());
		
		ll = new RelativeLayout(this);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));

		ll.addView(currentMap);
		addButtonsToLayout();

		setContentView(ll);
		// getSharedPreferences()
		Log.d("GameActivity", "Resume");
	}

	@Override
	protected void onPause() {
		super.onPause();// call it first
		// refresher.runnable.onPause(); // the method written by ourselves
		// onSaveInstanceState(new Bundle());
		// refresher.cancel(true);

		saveGameDataToSharedPreferences();
		// Intent intent = new Intent(this, BackgroundMode.class);
		// this.startActivity(intent);

		// Intent intent = new Intent(this, BackgroundMode.class);
		// this.startActivity(intent);

		// ((ViewManager) currentMap.getParent()).removeView(currentMap);

		Log.d("GameActivity", "Pause");
	}

	public void destroyGame() {
		onDestroy();
	}

	private void loadGameDataFromSavedInstanceState(Bundle savedInstanceState) {

	}

	private void loadGameDataFromSharedPreferences(SharedPreferences preferences) {
		currentMap = new MapView(this);

		// set human
		String[] humanInfo = preferences.getString("HumanInfo", "0,0").split(
				",");
		Float human_x = Float.parseFloat(humanInfo[0]);
		Float human_y = Float.parseFloat(humanInfo[1]);
		Human human = new Human(human_x, human_y, this);

		human.setBombRegular(preferences.getInt("RegularBomb", 10));
		currentMap.setHuman(human);

		// set ghosts
		String ghostInfo = preferences.getString("GhostInfo", "1,500,500,2;");// default

		// delete the ";" at the very end and then split
		String[] allGhostInfo = ghostInfo.substring(0, ghostInfo.length())
				.split(";");
		ArrayList<Ghost> ghost1List = new ArrayList<Ghost>();
		ArrayList<Ghost> ghost2List = new ArrayList<Ghost>();
		for (int i = 0; i < allGhostInfo.length; i++) {
			String[] oneGhostInfo = allGhostInfo[i].split(",");
			float x = Float.parseFloat(oneGhostInfo[1]);
			float y = Float.parseFloat(oneGhostInfo[2]);
			float v = Float.parseFloat(oneGhostInfo[3]);
			if (oneGhostInfo[0].equals("1")) {

				ghost1List.add(new Ghost(1, x, y, v, this));
			} else {
				ghost2List.add(new Ghost(2, x, y, v, this));
			}
		}
		currentMap.setGhost1List(ghost1List);
		currentMap.setGhost2List(ghost2List);

	}

	private void saveGameDataToSharedPreferences() {
		SharedPreferences sharedPref = this.getSharedPreferences(
				getString(R.string.save_slot_1), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();

		editor.putString("GhostInfo", currentMap.printGhostList());
		editor.putString("HumanInfo", currentMap.getHuman().toString());
		editor.putInt("RegularBomb", currentMap.getHuman().getBombRegular());

		editor.commit();

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("GameActivity", "Start");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("GameActivity", "Restart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		// refresher.cancel(true);

		ll.removeAllViewsInLayout();
		positionUpdateThread.interrupt();
		Log.d("GameActivity", "Stopped");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("GameActivity", "Destroyed");
	}

	private void addButtonsToLayout() {
		int height = this.getResources().getConfiguration().screenHeightDp;
		int width = this.getResources().getConfiguration().screenWidthDp;
		pauseButton = new ImageButton(this);

		pauseButton.setX(width * 0.8F);
		pauseButton.setY(height * 0.05F);

		// pauseButton.setMaxHeight((int) (width*0.01F));
		// pauseButton.setMaxWidth((int) (width*0.01F));
		pauseButton.setBackgroundColor(Color.TRANSPARENT);
		pauseButton.setImageBitmap(Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(getResources(), R.drawable.pause),
				(int) (width * 0.05), (int) (height * 0.05), true));
		pauseButton.setVisibility(View.VISIBLE);
		pauseButton.setOnClickListener(new ClickToPause());

		kill = new Button(this);
		kill.setText("Kill");
		kill.setX(100);
		kill.setY(100);
		// btn.setVisibility(View.VISIBLE);
		kill.setOnClickListener(new RegularKill());

		ll.addView(kill);
		ll.addView(pauseButton);

	}

	class ClickToPause implements OnClickListener {
		@Override
		public void onClick(View v) {
			Log.d("Btm", "click");
			// onPause();
			Intent intent = new Intent(GameStart.this, BackgroundMode.class);
			// Bundle stats = new Bundle();
			// stats.putInt("RegularBomb",
			// currentMap.getHuman().getBombRegular());
			GameStart.this.startActivity(intent);
		}

	}

	class MyOnTouchListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent me) {
			float x = me.getX();
			float y = me.getY();
			// PointF p = new PointF(x,y);

			currentMap.getHuman().setX_(x);
			currentMap.getHuman().setY_(y);

			return false;
		}
	}

	class RegularKill implements OnClickListener {
		@Override
		public void onClick(View arg0) {

			synchronized (baton) {
				try {
					currentMap.RegularKill();
					currentMap.getHuman().bombDecrease();
				} catch (Exception e) {
					;
				}

			}

		}
	}
}
