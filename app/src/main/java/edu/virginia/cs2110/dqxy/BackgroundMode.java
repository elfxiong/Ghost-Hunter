package edu.virginia.cs2110.dqxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BackgroundMode extends Activity {

	SharedPreferences sharedPref;
	LinearLayout layout;
	int regularBombPrize = 3;
	int pillPrize = 10;
	int discoPrize = 20;
	private MediaPlayer moneySound;
	MediaPlayer buttonSound;

	@Override
	protected void onCreate(Bundle savedd) {
		super.onCreate(savedd);
		this.moneySound = MediaPlayer.create(this, R.raw.coins_drop);
		this.buttonSound = MediaPlayer.create(this, R.raw.button_15);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		layout = new LinearLayout(this);
		sharedPref = getSharedPreferences(getString(R.string.save_slot_1),
				Context.MODE_PRIVATE);
		setContentView(R.layout.pausescreen);
		((TextView) findViewById(R.id.gamestats)).setText("Score:"
				+ GameStart.score + "\nMoney: " + GameStart.money
				+ "\nNumber of Kills: " + GameStart.numKill);
		regularBomb();
		chillPill();
		discoBall();
	}

	private void regularBomb() {
		if (sharedPref.getInt("Money", 0) < regularBombPrize) {
			findViewById(R.id.shopBtn1).setEnabled(false);
		} else {
			findViewById(R.id.shopBtn1).setEnabled(true);
		}
		findViewById(R.id.shopBtn1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int money = sharedPref.getInt("Money", 0);
				if (money < regularBombPrize) {
					Toast.makeText(BackgroundMode.this,
							R.string.no_enough_money, Toast.LENGTH_SHORT)
							.show();
					findViewById(R.id.shopBtn1).setEnabled(false);
					return;
				}
				if (money == regularBombPrize){
					findViewById(R.id.shopBtn1).setEnabled(false);
				}
				moneySound.start();
				sharedPref.getInt("RegularBomb", 0);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putInt("RegularBomb",
						sharedPref.getInt("RegularBomb", 0) + 1);
				editor.putInt("Money", sharedPref.getInt("Money", 0)
						- regularBombPrize);
				editor.commit();

				((TextView) findViewById(R.id.shopTxt1)).setText("   "
						+ sharedPref.getInt("RegularBomb", 0));
			}
		});

		// ((TextView) findViewById(R.id.shopPrz1)).setText("   $" +
		// regularBombPrize);

		((TextView) findViewById(R.id.shopTxt1)).setText("   "
				+ sharedPref.getInt("RegularBomb", 0));
	}

	private void chillPill() {
		if (sharedPref.getInt("Money", 0) < pillPrize) {
			findViewById(R.id.shopBtn2).setEnabled(false);
		} else {
			findViewById(R.id.shopBtn2).setEnabled(true);
		}
		findViewById(R.id.shopBtn2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				int money = sharedPref.getInt("Money", 0);
				int HP = sharedPref.getInt("HP", 0);
				if (HP == 10) {
					Toast.makeText(BackgroundMode.this, R.string.hp_full,
							Toast.LENGTH_SHORT).show();
					findViewById(R.id.shopBtn2).setEnabled(false);
					return;
				}
				if (money < pillPrize) {
					Toast.makeText(BackgroundMode.this,
							R.string.no_enough_money, Toast.LENGTH_SHORT)
							.show();
					findViewById(R.id.shopBtn2).setEnabled(false);
					return;
				}
				if (money == pillPrize){
					findViewById(R.id.shopBtn2).setEnabled(false);
				}
				moneySound.start();
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putInt("HP", sharedPref.getInt("HP", 0) + 1);
				editor.putInt("Money", sharedPref.getInt("Money", 0)
						- pillPrize);
				editor.commit();

				((TextView) findViewById(R.id.shopTxt2)).setText("   "
						+ sharedPref.getInt("HP", 0) + "/10");
			}
		});

		// ((TextView) findViewById(R.id.shopPrz2)).setText("   $"
		// + pillPrize);
		((TextView) findViewById(R.id.shopTxt2)).setText("   "
				+ sharedPref.getInt("HP", 0) + "/10");
	}

	private void discoBall() {
		if (sharedPref.getInt("Money", 0) < discoPrize) {
			findViewById(R.id.shopBtn3).setEnabled(false);
		} else {
			findViewById(R.id.shopBtn3).setEnabled(true);
		}
		findViewById(R.id.shopBtn3).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				int money = sharedPref.getInt("Money", 0);
				if (money < discoPrize) {
					Toast.makeText(BackgroundMode.this,
							R.string.no_enough_money, Toast.LENGTH_SHORT)
							.show();
					findViewById(R.id.shopBtn3).setEnabled(false);
					return;
				}
				if (money == discoPrize){
					findViewById(R.id.shopBtn3).setEnabled(false);
				}

				moneySound.start();
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putInt("Disco", sharedPref.getInt("Disco", 0) + 1);
				editor.putInt("Money", sharedPref.getInt("Money", 0)
						- discoPrize);
				editor.commit();

				((TextView) findViewById(R.id.shopTxt3)).setText("   "
						+ sharedPref.getInt("Disco", 0));
			}
		});

		// ((TextView) findViewById(R.id.shopPrz3)).setText("   $"
		// + pillPrize);
		((TextView) findViewById(R.id.shopTxt3)).setText("   "
				+ sharedPref.getInt("Disco", 0));
	}

	public void resumeGame(View button) {
		buttonSound.start();
		this.finish();
	}

	public void backToMain(View button) {
		buttonSound.start();
		Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {

	}
}
