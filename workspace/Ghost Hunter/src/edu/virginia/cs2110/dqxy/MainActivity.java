package edu.virginia.cs2110.dqxy;

import edu.virginia.cs2110.dqxy.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    public void topButtonClicked(View button) {
    	// Create an intent to associate button clicked with Popup class
    	Intent intent = new Intent (this, GameStart.class);
    	this.startActivity(intent);
    	Log.d("2110", "The Button was clicked"); //d=debug message.   format: tag + message
    }	

}
