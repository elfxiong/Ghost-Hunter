//package edu.virginia.cs2110.notUsed;
//
//import java.util.TreeSet;
//
//import edu.virginia.cs2110.dqxy.MapView;
//import edu.virginia.cs2110.dqxy.MoveRunnable;
//import edu.virginia.cs2110.dqxy.R;
//import edu.virginia.cs2110.dqxy.R.string;
//import android.app.Activity;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnTouchListener;
//
//public class GameStart_SurfaceViewTry extends Activity {
//
//	MoveRunnable changer;
//
//	MapView currentMap;
//	MapView pastMap;
//	MapView futureMap;
//	private MapSurfaceView surfaceView;
//
//	// Override the onCreate method
//	@Override
//	protected void onCreate(Bundle bundle) {
//		super.onCreate(bundle);		
//
//		surfaceView = new MapSurfaceView(this);
//		setContentView(surfaceView);
//		
//		surfaceView.setOnTouchListener(new OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent me) {
//				currentMap.getHuman().setX_(me.getX());
//				currentMap.getHuman().setY_(me.getY());
//				return true;
//			}
//		});
//		Log.d("GameStart", "Create");
//	}
//
//	@Override
//	protected void onStart() {
//		super.onStart();
//		Log.d("GameActivity", "Starte");
//	}
//
//	@Override
//	protected void onRestart() {
//		super.onRestart();
//		Log.d("GameActivity", "Restart");
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		Log.d("GameActivity", "Resume");
//	}
//
//	// @Override
//	// protected void onRestoreInstanceState(Bundle bundle) {
//	// // super.onRestoreInstanceState(bundle);
//	// Log.d("GameActivity", "restore");
//	// // currentMap = (MapView) bundle.getSerializable("currentMapState");
//	// // currentMap.setHuman((Human) bundle.getSerializable("human"));
//	// // Log.d("GameActivity", "x:" + currentMap.getHuman().getX());
//	// // Log.d("GameActivity", "y:" + currentMap.getHuman().getY());
//	// // ui = (UIThread) bundle.getSerializable("ui");
//	// }
//
//	@Override
//	protected void onSaveInstanceState(Bundle bundle) {
//
//		// bundle.putSerializable("currentMapState", currentMap);
//		// bundle.putSerializable("human", currentMap.getHuman());
//		// bundle.putSerializable("ui", ui);
//		// Log.d("GameActivity", "save");
//		// ui.cancel(true);
//		// super.onSaveInstanceState(bundle);
//	}
//
//	@Override
//	protected void onPause() {
//		super.onPause();// call it first
//		// request update and save the variable into the bundle
//		// onSaveInstanceState(new Bundle());
//
//		// wait()
//
//		Context context = this;
//		SharedPreferences sharedPref = context.getSharedPreferences(
//				getString(R.string.action_settings), Context.MODE_PRIVATE);
//		SharedPreferences.Editor editor = sharedPref.edit();
//
//		TreeSet<String> ghostInfo = new TreeSet<String>();
//		ghostInfo.add(new String("Ghostsfjlkasjdfklsjdlfksj"));
//		editor.putStringSet(getString(R.string.save_slot_1), ghostInfo);
//		editor.commit();
//
//		Log.d("GameActivity", "Paused");
//
//	}
//
//	@Override
//	protected void onStop() {
//		super.onStop();
//		Log.d("GameActivity", "Stopped");
//	}
//
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		Log.d("GameActivity", "Destroyed");
//	}
//
//}
