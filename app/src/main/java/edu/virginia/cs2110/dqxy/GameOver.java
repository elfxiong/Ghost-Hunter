package edu.virginia.cs2110.dqxy;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class GameOver extends Activity {
	private MediaPlayer gameoverSound;
	MediaPlayer buttonSound;

	public GameOver() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.buttonSound = MediaPlayer.create(this, R.raw.button_15);
		gameoverSound = MediaPlayer.create(this, R.raw.fail_trombone);
		gameoverSound.start();

		SharedPreferences sharedPref = getSharedPreferences(
				this.getString(R.string.save_slot_1), Context.MODE_PRIVATE);

		setContentView(R.layout.gameover);

		((TextView) findViewById(R.id.stat1)).setText("Score:  "
				+ sharedPref.getInt("Score", 0));
		((TextView) findViewById(R.id.stat2)).setText("Money unspent:   "
				+ sharedPref.getInt("Money", 0));
		((TextView) findViewById(R.id.stat3)).setText("Number of kills:   "
				+ sharedPref.getInt("NumKill", 0));

		Log.d("GameOver", "Create");
	}

	public void finishGame(View btn) {
		buttonSound.start();
		this.getSharedPreferences(getString(R.string.save_slot_1),
				Context.MODE_PRIVATE).edit().clear().commit();
		finish();
	}

	@Override
	public void onBackPressed() {

	}

}
