package edu.virginia.cs2110.dqxy;

import android.os.AsyncTask;
import android.util.Log;

public class UIThread extends AsyncTask<MapView, MapView, Double> {

	Walker walker;
	Floater floater;

	@Override
	// Enables the circles to "move" (get re-created) with 1 sec intervals
	protected Double doInBackground(MapView... params) {

		try {
			floater = new Floater();
			floater.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params[0]);
			walker = new Walker();
			walker.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params[0]);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (!this.isCancelled()) { // while not cancelled
			this.publishProgress(params); // refresh the map here
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
		}

		// Movemove movemove = new Movemove(params);
		// movemove.start();

		// while (!this.isCancelled()) { // while not cancelled
		// this.publishProgress(params);
		// try {
		// Thread.sleep(10); // 1sec // sleep (wait) one second
		// } catch (InterruptedException e) {
		//
		// }
		// }

		Log.d("UIThread", "The end of doInBackground");
		return 0.0;
	}

	@Override
	protected void onProgressUpdate(MapView... params) {
		for (MapView cv : params) {
			cv.invalidate(); // re-draw (delete current and replace)
			// current image is no longer valid
		}
		// walker.publishProgress(params);
	}

	@Override
	protected void onPostExecute(Double result) { // param based on return type

	}

	@Override
	protected void onPreExecute() {
		Log.d("Changer", "PreExecuted");
	}

	@Override
	protected void onCancelled() {
		Log.d("Changer", "Cancelled");
	}

}
