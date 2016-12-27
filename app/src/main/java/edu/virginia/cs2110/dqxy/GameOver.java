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

import java.util.Locale;

public class GameOver extends Activity {
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
        MediaPlayer gameoverSound = MediaPlayer.create(this, R.raw.fail_trombone);
        gameoverSound.start();

        SharedPreferences sharedPref = getSharedPreferences(
                this.getString(R.string.save_slot_1), Context.MODE_PRIVATE);

        setContentView(R.layout.gameover);

        ((TextView) findViewById(R.id.stat1)).setText(String.format(Locale.US, "Score:  %d", sharedPref.getInt("Score", 0)));
        ((TextView) findViewById(R.id.stat2)).setText(String.format(Locale.US, "Money unspent:   %d", sharedPref.getInt("Money", 0)));
        ((TextView) findViewById(R.id.stat3)).setText(String.format(Locale.US, "Number of kills:   %d", sharedPref.getInt("NumKill", 0)));

        Log.d("GameOver", "Create");
    }

    public void finishGame(View btn) {
        buttonSound.start();
        this.getSharedPreferences(getString(R.string.save_slot_1),
                Context.MODE_PRIVATE).edit().clear().apply();
        finish();
    }

    @Override
    public void onBackPressed() {

    }

}
