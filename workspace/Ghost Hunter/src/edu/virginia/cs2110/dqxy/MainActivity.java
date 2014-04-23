package edu.virginia.cs2110.dqxy;

import edu.virginia.cs2110.dqxy.R;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private final int SPLASH_TIME = 500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				setContentView(R.layout.activity_main);
			}
		}, SPLASH_TIME);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void loadGame(View button) {
		Intent intent = new Intent(this, GameStart.class);
		this.startActivity(intent);
		Log.d("MainActivity", "New Game Button was clicked");
	}

	public void newGame(View button) {
		this.getSharedPreferences(getString(R.string.save_slot_1),
				Context.MODE_PRIVATE).edit().clear().commit();
		this.loadGame(button);
		Log.d("MainActivity", "Load Game Button was clicked");
	}

	public void setDifficulty(View button) {

	}

	public void load(View button) {

	}

}
