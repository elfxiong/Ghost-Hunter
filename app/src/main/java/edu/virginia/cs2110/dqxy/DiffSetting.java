package edu.virginia.cs2110.dqxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class DiffSetting extends Activity {
	private SeekBar set;
	static int difficulty;
	protected static int position;
	MediaPlayer buttonSound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.difficulty);
		this.buttonSound = MediaPlayer.create(this, R.raw.button_15);
		set = (SeekBar) findViewById(R.id.seekBar1);

		set.setOnSeekBarChangeListener(new SlideToChange());

		set.setProgress(position);

		Log.d("GameStart", "Create");
	}

	private void setDifficulty() {
		// speed

		// percentage
		int position = set.getProgress();
		if (position == 0) {// Hardness 1
			// Log.d("H1", ""+position);
			this.difficulty = 0;

		}
		if (position == 30) {// Hardness 2
			// Log.d("H2", ""+position);
			this.difficulty = 5;
		}
		if (position == 60) {// Hardness 3
			// Log.d("H3", ""+position);
			this.difficulty = 10;
		}

		if (position == 90) {// Hardness 4
			// Log.d("H4", ""+position);
			this.difficulty = 15;
		}
		if (position == 120) {// Hardness 5
			// Log.d("H5", ""+position);
			this.difficulty = 20;
		}

	}

	class SlideToChange implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {

			int step = 30;
			progress = (((int) Math.round(progress / step)) * step);
			set.setProgress(position);
			position = progress;

			// Log.d("Hardness", ""+progress+"");
			setDifficulty();
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {

		}

	}

	public void setHardness(int hardness) {
		this.difficulty = hardness;
	}

	public void loadGame(View button) {
		Intent intent = new Intent(this, GameStart.class);
		buttonSound.start();
		this.startActivity(intent);
		this.finish();
	}

}