//package edu.virginia.cs2110.notUsed;
//
//import java.io.Serializable;
//
//import edu.virginia.cs2110.dqxy.Ghost;
//import edu.virginia.cs2110.dqxy.MapView;
//import android.os.AsyncTask;
//import android.util.Log;
//
//public class Floater extends AsyncTask<MapView, MapView, Double> implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -3756524067111961624L;
//
//	@Override
//	protected Double doInBackground(MapView... params) {
//		while (!this.isCancelled()) {
//			this.publishProgress(params);
//			try {
//				Thread.sleep(30);
//			} catch (InterruptedException e) {
//
//			}
//			ghostMove(params);
////			 Log.d("Floater","moving");
//
//		}
//		return 0.0;
//	}
//
//	private void ghostMove(MapView... params) {
//		double v = 2;
//		MapView map1 = params[0];
//		Ghost g = map1.getGhost();
//
//		float x = g.getX();
//		float y = g.getY();
//		float x_ = map1.getHuman().getX() - 100;
//		float y_ = map1.getHuman().getY();
//
//		if (x != x_ && y != y_) {
//			double slope = (y - y_) / (x - x_);
//			double angle = Math.atan(slope);
//			double vx = v * Math.cos(angle);
//			double vy = v * Math.sin(angle);
//			if (x > x_) {
//				vx = -vx;
//				vy = -vy;
//			}
//			x += vx;
//			y += vy;
//		}
//
//		map1.getGhost().setX(x);
//		map1.getGhost().setY(y);
//
//	}
//
//	@Override
//	protected void onPostExecute(Double result) { // param based on return type
//		
//	}
//
//	@Override
//	protected void onPreExecute() {
//		Log.d("Floater", "PreExecuted");
//	}
//
//	@Override
//	protected void onCancelled() {
//		Log.d("Floater", "Cancelled");
//	}
//
//}
