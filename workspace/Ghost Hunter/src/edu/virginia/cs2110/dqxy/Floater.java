package edu.virginia.cs2110.dqxy;

import android.os.AsyncTask;
import android.util.Log;

public class Floater extends AsyncTask<MapView, MapView, Double> {

	// Local Variable
	// MapView theView;

	@Override
	// Enables the circles to "move" (get re-created) with 1 sec intervals
	protected Double doInBackground(MapView... params) {
		while (!this.isCancelled()) { // while not cancelled
			this.publishProgress(params);
			try {
				Thread.sleep(30); // 1sec // sleep (wait) one second
			} catch (InterruptedException e) {

			}
			ghostMove(params);
			// Log.d("Floater","moved");

		}
		return 0.0;
	}

	private void ghostMove(MapView... params) {
		double v = 2;
		// double close = 2;
		MapView map1 = params[0];
		Ghost g = map1.getGhost();

		float x = g.getX();
		float y = g.getY();
		float x_ = map1.getHuman().getX() - 100;
		float y_ = map1.getHuman().getY();

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

		map1.getGhost().setX(x);
		map1.getGhost().setY(y);

	}

	// @Override
	// protected void onProgressUpdate(MapView... params) {
	// for (MapView cv : params) {
	// cv.invalidate(); // re-draw (delete current and replace)
	// // current image is no longer valid
	// }
	// // Log.d("Floater", "onProgressUpdate");
	// }

	// protected final void publishProgress (MapView... values){
	//
	// }

	@Override
	protected void onPostExecute(Double result) { // param based on return type

	}

	@Override
	protected void onPreExecute() {
		Log.d("Floater", "PreExecuted");
	}

	@Override
	protected void onCancelled() {
		Log.d("Floater", "Cancelled");
	}

}
