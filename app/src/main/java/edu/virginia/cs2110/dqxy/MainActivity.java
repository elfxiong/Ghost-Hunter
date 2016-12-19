package edu.virginia.cs2110.dqxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	private final int SPLASH_TIME = 2000;
	MediaPlayer buttonSound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.buttonSound = MediaPlayer.create(this, R.raw.button_15);
		setContentView(R.layout.splashscreen);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				setContentView(R.layout.activity_main);
				if (MainActivity.this.getSharedPreferences(
						getString(R.string.save_slot_1), Context.MODE_PRIVATE)
						.getInt("HP", 0) == 0) {
					findViewById(R.id.loadGameBtn).setEnabled(false);
				}
			}
		}, SPLASH_TIME);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void loadGame(View button) {
		buttonSound.start();
		Intent intent = new Intent(this, GameStart.class);
		this.startActivity(intent);
	}

	public void newGame(View button) {
		buttonSound.start();
		this.getSharedPreferences(getString(R.string.save_slot_1),
				Context.MODE_PRIVATE).edit().clear().commit();
		Intent intent = new Intent(this, DiffSetting.class);
		this.startActivity(intent);
	}

	public void endGame(View button) {
		buttonSound.start();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(intent);
		System.exit(0);
	}

	@Override
	public void onBackPressed() {

	}

}
