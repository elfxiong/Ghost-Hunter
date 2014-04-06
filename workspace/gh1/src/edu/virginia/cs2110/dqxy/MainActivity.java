package edu.virginia.cs2110.dqxy;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


 

    /**
     * A placeholder fragment containing a simple view.
     */
   
    public void playButtonClicked(View button){
    	Intent intent = new Intent(this,GameStart.class);
    	this.startActivity(intent);
    	Log.d("2110","The play button was clicked");
    }

}
