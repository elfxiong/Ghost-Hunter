package edu.virginia.cs2110.dqxy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class GameStart extends Activity implements SensorEventListener {
	private MediaPlayer disco;
	MoveRunnable postionUpdater;
	Thread positionUpdateThread;
	MapView currentMap;
	boolean newGame;
	ImageButton pauseButton;
	static ImageButton killButton;
	static ImageButton repelButton;
	static ImageButton discoButton;
	RelativeLayout gameLayout;
	static Object baton;
	static int money;
	static int score;
	static boolean gameover;
	static int numKill;
	float sensorX, sensorY;
	static boolean spray;
	static int repeller;
	private ArrayList<RectF> obList;

	public float getSensorX() {
		return sensorX;
	}

	private SensorManager mSensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Log.d("GameStart", "Create");
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER)
					.get(0);
			mSensorManager.registerListener(this, s,
					SensorManager.SENSOR_DELAY_GAME);
		}

		sensorX = sensorY = 0;

		GameStart.baton = new Object();
		loadGameDataFromSharedPreferences(this.getSharedPreferences(
				getString(R.string.save_slot_1), Context.MODE_PRIVATE));

		postionUpdater = new MoveRunnable(currentMap);
		positionUpdateThread = new Thread(postionUpdater);
		positionUpdateThread.start();

		currentMap.setOnTouchListener(new MyOnTouchListener());

		gameLayout = new RelativeLayout(this);
		gameLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));

		gameLayout.addView(currentMap);
		addButtonsToLayout();

		
		disco = MediaPlayer.create(this, R.raw.disco_time);
		setContentView(gameLayout);
		// getSharedPreferences()
		Log.d("GameActivity", "Resume");
	}

	private void setUpWalls() {
		DisplayMetrics metrics = this.getResources().getDisplayMetrics();
		float mapWidth = metrics.widthPixels;
		float mapHeight = metrics.heightPixels;
		obList = new ArrayList<RectF>();
		RectF rA0 = new RectF(mapWidth * 0.15F, mapHeight * 0.2F,
				mapWidth * 0.4F, mapHeight * 0.3F);
		RectF rA1 = new RectF(mapWidth * 0.6F, mapHeight * 0.2F,
				mapWidth * 0.85F, mapHeight * 0.3F);
		RectF rA2 = new RectF(mapWidth * 0.15F, mapHeight * 0.4F,
				mapWidth * 0.25F, mapHeight * 0.6F);
		RectF rA3 = new RectF(mapWidth * 0.4F, mapHeight * 0.4F,
				mapWidth * 0.6F, mapHeight * 0.6F);

		obList.add(rA0);
		obList.add(rA1);
		obList.add(rA2);
		obList.add(rA3);
	}

	@Override
	protected void onPause() {
		super.onPause();// call it first
		saveGameDataToSharedPreferences();
		mSensorManager.unregisterListener(this);
		Log.d("GameActivity", "Pause");
	}

	public void destroyGame() {
		onDestroy();
	}

	private void loadGameDataFromSharedPreferences(SharedPreferences preferences) {
		currentMap = new MapView(this);

		// stats
		int moneyStart = (int) (15 - DiffSetting.difficulty);
		money = preferences.getInt("Money", moneyStart);
		score = preferences.getInt("Score", 0);
		numKill = preferences.getInt("NumKill", 0);
		currentMap.setDiscoNum(preferences.getInt("Disco", 0));
		gameover = false;
		setUpWalls();
		currentMap.setObList(obList);
		currentMap.setBackgroundResource(R.drawable.grass_background);

		// set human
		String[] humanInfo = preferences.getString("HumanInfo", "100,100")
				.split(",");
		Float human_x = Float.parseFloat(humanInfo[0]);
		Float human_y = Float.parseFloat(humanInfo[1]);
		Human human = new Human(human_x, human_y, 10, this);
		human.setHealthPoint(preferences.getInt("HP", 10));
		human.setBombRegular(preferences.getInt("RegularBomb", 10));
		currentMap.setHuman(human);

		// set ghosts
		float vSet = (DiffSetting.difficulty + 10) / 10;
		String ghostInfo = preferences.getString("GhostInfo", "1,500,500,"
				+ vSet + ";");// default

		// delete the ";" at the very end and then split
		String[] allGhostInfo = ghostInfo.substring(0, ghostInfo.length())
				.split(";");
		ArrayList<Ghost> ghost1List = new ArrayList<Ghost>();
		ArrayList<Ghost> ghost2List = new ArrayList<Ghost>();

		try {
			for (int i = 0; i < allGhostInfo.length; i++) {
				String[] oneGhostInfo = allGhostInfo[i].split(",");
				if (oneGhostInfo.length == 1) {
					Log.d("ghostInfo", ghostInfo);
				}
				float x = Float.parseFloat(oneGhostInfo[1]);
				float y = Float.parseFloat(oneGhostInfo[2]);
				// float v = Float.parseFloat(oneGhostInfo[3]);
				if (oneGhostInfo[0].equals("1")) {
					ghost1List.add(new Ghost(1, x, y, this));
				} else {
					ghost2List.add(new Ghost(2, x, y, this));
				}
			}
		} catch (Exception e) {
			Log.d("Exception", ghostInfo);
			e.printStackTrace();
		} finally {
			currentMap.setGhost1List(ghost1List);
			currentMap.setGhost2List(ghost2List);
		}
		;
	}

	private void saveGameDataToSharedPreferences() {
		SharedPreferences sharedPref = this.getSharedPreferences(
				getString(R.string.save_slot_1), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();

		editor.putString("GhostInfo", currentMap.printGhostList());
		editor.putString("HumanInfo", currentMap.getHuman().toString());
		editor.putInt("RegularBomb", currentMap.getHuman().getBombRegular());
		editor.putInt("SuperBomb", currentMap.getHuman().getBombSuper());
		editor.putInt("HP", currentMap.getHuman().getHealthPoint());
		editor.putInt("Score", GameStart.score);
		editor.putInt("Money", GameStart.money);
		editor.putInt("NumKill", GameStart.numKill);
		editor.putInt("Disco", currentMap.getDiscoNum());

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

		gameLayout.removeAllViewsInLayout();
		postionUpdater.setFinish();
		try {
			positionUpdateThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

		pauseButton.setX(width * 0.9F);
		pauseButton.setY(height * 0.02F);

		pauseButton.setBackgroundColor(Color.TRANSPARENT);
		pauseButton.setImageBitmap(BitmapFactory.decodeResource(getResources(),
				R.drawable.shop_button));
		pauseButton.setVisibility(View.VISIBLE);
		pauseButton.setOnClickListener(new ClickToPause());

		killButton = new ImageButton(this);
		killButton.setBackgroundColor(Color.TRANSPARENT);
		killButton.setX(width * 1.20F);
		killButton.setY(height * 1.25F);
		killButton.setImageBitmap(BitmapFactory.decodeResource(getResources(),
				R.drawable.fireball_button));
		killButton.setOnClickListener(new RegularKill());

		repelButton = new ImageButton(this);
		repelButton.setX(width * 1.20F);
		repelButton.setY(height * 1.15F);
		repelButton.setBackgroundColor(Color.TRANSPARENT);
		// repelButton.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory
		// .decodeResource(getResources(), R.drawable.repellent_off),
		// (int) (width * 0.09), (int) (height * 0.09), true));
		repelButton.setImageDrawable(getResources().getDrawable(
				R.drawable.repellent_off));
		repelButton.setVisibility(View.VISIBLE);
		repelButton.setOnClickListener(new Repel());

		discoButton = new ImageButton(this);
		discoButton.setBackgroundColor(Color.TRANSPARENT);
		discoButton.setX(width * 1.20F);
		discoButton.setY(height * 1.05F);
		discoButton.setImageBitmap(BitmapFactory.decodeResource(getResources(),
				R.drawable.discoball));
		discoButton.setOnClickListener(new DiscoEffect());

		gameLayout.addView(killButton);
		gameLayout.addView(pauseButton);
		gameLayout.addView(repelButton);
		gameLayout.addView(discoButton);
	}

	class ClickToPause implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(GameStart.this, BackgroundMode.class);
			GameStart.this.startActivity(intent);
		}

	}

	class MyOnTouchListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent me) {
			float x = me.getX();
			float y = me.getY();

			currentMap.getHuman().setX_(x);
			currentMap.getHuman().setY_(y);

			return true;
		}
	}

	class RegularKill implements OnClickListener {
		@Override
		public void onClick(View btn) {
			synchronized (baton) {
				try {
					currentMap.regularKill();
					currentMap.getHuman().bombDecrease();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	class DiscoEffect implements OnClickListener {
		private static final long DISCO_TIME = 20000;

		@Override
		public void onClick(View btn) {
			currentMap.setDiscoOn(true);
			disco.start();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					currentMap.setDiscoOn(false);
					currentMap.setBackgroundResource(R.drawable.grass_background);
				}
			}, DISCO_TIME);
		}
	}

	class Repel implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			repeller = 0;
			if (spray == false && GameStart.money > 0) {
				for (Ghost ghost : MapView.ghost1List) {
					ghost.setV(-2);
				}
				spray = true;
				GameStart.repelButton.setImageDrawable(getResources()
						.getDrawable(R.drawable.repellent_on));
			} else if (spray == true || GameStart.money == 0) {
				spray = false;
				repeller = 0;
				for (Ghost ghost : MapView.ghost1List) {
					ghost.setV(2);
				}
				GameStart.repelButton.setImageDrawable(getResources()
						.getDrawable(R.drawable.repellent_off));
			}

		}

	}

	@Override
	public final void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public final void onSensorChanged(SensorEvent event) {
		sensorX = event.values[0];
		sensorY = event.values[1];
	}

	@Override
	public void onBackPressed() {

	}
}
