package edu.virginia.cs2110.dqxy;

import android.os.AsyncTask;

public class Changer extends AsyncTask<MapView, MapView, Double> {

	// Local Variable
	MapView theView;

	@Override
	// Enables the circles to "move" (get re-created) with 1 sec intervals
	protected Double doInBackground(MapView... params) {
		while (!this.isCancelled()) { // while not cancelled
			this.publishProgress(params);
			try {
				Thread.sleep(10); // 1sec // sleep (wait) one second
			} catch (InterruptedException e) {

			}

			move(params);
		}
		return 0.0;
	}

	private void move(MapView... params) {
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

	@Override
	protected void onProgressUpdate(MapView... params) {
		for (MapView cv : params) {
			cv.invalidate(); // re-draw (delete current and replace)
								// current image is no longer valid
		}
	}

	@Override
	protected void onPostExecute(Double result) { // param based on return type

	}

	@Override
	protected void onPreExecute() {

	}

	@Override
	protected void onCancelled() {

	}

}
