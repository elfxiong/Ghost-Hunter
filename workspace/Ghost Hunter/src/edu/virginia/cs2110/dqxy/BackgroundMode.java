package edu.virginia.cs2110.dqxy;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BackgroundMode extends Activity {

	SharedPreferences sharedPref;
	TextView sBomb_txt;
	LinearLayout layout;
	@Override
	protected void onCreate(Bundle savedd) {
		super.onCreate(savedd);
		layout = new LinearLayout(this);
		sharedPref = getSharedPreferences(getString(R.string.save_slot_1),
				Context.MODE_PRIVATE);
		
		resumeBtn();
		regularBomb();
//		superBomb();
		
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER);
		setContentView(layout);
	}


	private void resumeBtn() {
		Button resumeBtn = new Button(this);
		resumeBtn.setText("Resume");
		resumeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		layout.addView(resumeBtn);		
	}

	private void regularBomb() {
		Button rBomb_btn = new Button(this);
		rBomb_btn.setText("Buy Bomb");
		rBomb_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sharedPref.getInt("RegularBomb", 0);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putInt("RegularBomb",
						sharedPref.getInt("RegularBomb", 0) + 1);
				editor.commit();
				sBomb_txt.setText("Regular Bomb: "
						+ sharedPref.getInt("RegularBomb", 0));
			}
		});
		layout.addView(rBomb_btn);

		sBomb_txt = new TextView(this);
		sBomb_txt.setText("Regular Bomb: "
				+ sharedPref.getInt("RegularBomb", 0));
		layout.addView(sBomb_txt);
	}
	

	private void superBomb() {//TODO
		Button sBomb_btn = new Button(this);
		sBomb_btn.setText("Buy Bomb");
		sBomb_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sharedPref.getInt("RegularBomb", 0);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putInt("RegularBomb",
						sharedPref.getInt("RegularBomb", 0) + 1);
				editor.commit();
				sBomb_txt.setText("Regular Bomb: "
						+ sharedPref.getInt("RegularBomb", 0));
			}
		});
		layout.addView(sBomb_btn);

		sBomb_txt = new TextView(this);
		sBomb_txt.setText("Regular Bomb: "
				+ sharedPref.getInt("RegularBomb", 0));
		layout.addView(sBomb_txt);
	}
}
