package edu.virginia.cs2110.notUsed;

import java.io.Serializable;

import edu.virginia.cs2110.dqxy.Human;
import edu.virginia.cs2110.dqxy.MapView;
import android.os.AsyncTask;
import android.util.Log;

public class Walker extends AsyncTask<MapView, MapView, Double> implements Serializable{

	// Local Variable
	// MapView theView;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7002584197860915610L;

	@Override
	// Enables the circles to "move" (get re-created) with 1 sec intervals
	protected Double doInBackground(MapView... params) {
		while (!this.isCancelled()) { // while not cancelled
			this.publishProgress(params);
			try {
				Thread.sleep(30); // 1sec // sleep (wait) one second
			} catch (InterruptedException e) {
			}
			humanMove(params);
//			Log.d("Walker", "human moving");
		}
		return 0.0;
	}

//	@Override
//	protected void onProgressUpdate(MapView... params) {
//		for (MapView cv : params) {
//			cv.invalidate(); // re-draw (delete current and replace)
//			// current image is no longer valid
//		}
//		// Log.d("Walker", "onProgressUpdate");
//	}

	// protected final void publishProgress (MapView... values){
	//
	// }

	@Override
	protected void onPostExecute(Double result) { // param based on return type

	}

	@Override
	protected void onPreExecute() {
		Log.d("Walker", "PreExecuted");
	}

	@Override
	protected void onCancelled() {
		Log.d("Walker", "Cancelled");
	}
	
	private void humanMove(MapView... params) {
		double v = 4;
		double close = 2;
		MapView map1 = params[0];
		Human human = map1.getHuman();

		float x = human.getX();
		float y = human.getY();
		float x_ = human.getX_();
		float y_ = human.getY_();

		if (x != x_ && y != y_) {
			double slope = (y - y_) / (x - x_);
			double angle = Math.atan(slope);
			double vx = v * Math.cos(angle);
			double vy = v * Math.sin(angle);
			if (x > x_) {
				vx = -vx;
				vy = -vy;
			}
			x += vx;
			y += vy;
		}
		if (x - x_ < close && x - x_ > -close) {
			x = x_;
		}
		if (y - y_ < close && y - y_ > -close) {
			y = y_;
		}
		human.setX(x);
		human.setY(y);

	}

}
