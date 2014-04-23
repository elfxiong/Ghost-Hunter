//package edu.virginia.cs2110.notUsed;
//
//import java.io.Serializable;
//
//import edu.virginia.cs2110.dqxy.MapView;
//import edu.virginia.cs2110.dqxy.MoveRunnable;
//import android.os.AsyncTask;
//import android.os.Handler;
//import android.util.Log;
//
//public class ScreenRefresher extends AsyncTask<MapView, MapView, Double> implements
//		Serializable {
//
//	private static final long serialVersionUID = 4083016158647645752L;
//	Walker walker;
//	Floater floater;
//	MapView map;
//	private Runnable mtimerTask;
//	Handler mhandler;
//	protected MoveRunnable runnable;
//
//	@Override
//	// Enables the circles to "move" (get re-created) with 1 sec intervals
//	protected Double doInBackground(MapView... params) {
//		this.map = params[0];
//		while (!this.isCancelled()) { // while not cancelled
//			this.publishProgress(map); // refresh the map here
//			try {
//				Thread.sleep(30);
//			} catch (InterruptedException e) {
//			}
//		}
//		Log.d("UIThread", "The end of doInBackground");
//		return 0.0;
//	}
//
//	@Override
//	protected void onProgressUpdate(MapView... params) {
//		for (MapView cv : params) {
//			cv.invalidate(); // re-draw (delete current and replace)
//			// current image is no longer valid
//		}
//		// Log.i("UI", "Refreshing screen");
//	}
//
//	@Override
//	protected void onPostExecute(Double result) { // param based on return type
//
//	}
//
//	@Override
//	protected void onPreExecute() {
//		Log.d("Changer", "PreExecuted");
//	}
//
//	@Override
//	protected void onCancelled() {
//		Log.d("Changer", "Cancelled");
//	}
//
//	public void setMaps(MapView map) {
//		this.map = map;
//	}
//
//}
