//package edu.virginia.cs2110.notUsed;
//
//import edu.virginia.cs2110.dqxy.Ghost;
//import edu.virginia.cs2110.dqxy.Human;
//import edu.virginia.cs2110.dqxy.MapView;
//
//
//public class Movemove extends Thread {
//
////	AsyncTask<MapView, MapView, Double> walker, floater;
//	MapView[] maps;
//
//	public Movemove(MapView... params) {
//		this.maps = params;
//	}
//
//	@Override
//	public void run() {
//		try {
//			while (true) {
//				sleep(500);
//				humanMove(maps);
//				ghostMove(maps);
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void humanMove(MapView... params) {
//		double v = 4;
//		double close = 2;
//		MapView map1 = params[0];
//		Human human = map1.getHuman();
//
//		float x = human.getX();
//		float y = human.getY();
//		float x_ = human.getX_();
//		float y_ = human.getY_();
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
//		if (x - x_ < close && x - x_ > -close) {
//			x = x_;
//		}
//		if (y - y_ < close && y - y_ > -close) {
//			y = y_;
//		}
//		human.setX(x);
//		human.setY(y);
//
//	}
//
//	private void ghostMove(MapView... params) {
//		double v = 2;
//		// double close = 2;
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
//}
