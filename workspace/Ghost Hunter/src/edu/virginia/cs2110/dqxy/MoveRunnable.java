package edu.virginia.cs2110.dqxy;

import android.graphics.RectF;

public class MoveRunnable implements Runnable {

	private MapView map;
	private Object mPauseLock;
	private boolean mPaused;
	private boolean mFinished;
	private int count;

	public MoveRunnable(MapView map) {

		mPauseLock = new Object();
		mPaused = false;
		mFinished = false;
		count = 0;
		this.map = map;
	}

	@Override
	public void run() {
		while (!mFinished) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
			// move human
			map.getHuman().updateHitBox();
			map.getHuman().HumanMove(this.hCollision());
			// move ghost
			ghost1Move();
			for (Ghost g : map.getGhost2List()) {
				g.moveIndependent(map);
			}

			if (count % 100 == 0) {
				for (Ghost g : map.getGhost2List()) {
					float vx = (float) Math.random() * 2 - 1;
					float vy = (float) Math.random() * 2 - 1;

					g.setX_change(vx);
					g.setY_change(vy);
				}
			}
			map.postInvalidate();

			synchronized (mPauseLock) {
				while (mPaused) {
					try {
						mPauseLock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void ghost1Move() {
		int k = 0;
		for (int i = 0; i < map.getGhost1List().size(); i++) {

			map.getGhost1List().get(i).updateHitbox();
			for (int j = 0; j < map.getGhost1List().size(); j++) {

				map.getGhost1List().get(j).updateHitbox();
				RectF b = map.getGhost1List().get(i).getHitbox();
				RectF r = map.getGhost1List().get(j).getHitbox();

				if (b.intersect(r)) {
					RectF h = b;
					h.setIntersect(map.getGhost1List().get(i).getHitbox(), r);

					boolean yDownOb = r.centerY() < b.centerY();
					boolean yUpOb = r.centerY() > b.centerY();

					boolean xLeftOb = r.centerX() > b.centerX();
					boolean xRightOb = r.centerX() < b.centerX();
					float iH = h.height();
					float iW = h.width();

					if (iH > iW) {

						if (xRightOb) {
							k = 4;

						}
						if (xLeftOb) {
							k = 3;
						}
					} else {
						if (yUpOb) {
							k = 2;
						}
						if (yDownOb) {
							k = 1;
						}
					}
				}
			}

			map.getGhost1List().get(i).moveToHuman(k, map);

		}
	}

	public int hCollision() {
		int k = 0;

		for (RectF r : map.getObList()) {
			if (map.getHuman().getHitbox().intersect(r)) {
				RectF h = map.getHuman().getHitbox();
				h.setIntersect(h, r);

				boolean yDownOb = r.centerY() < map.getHuman().getHitbox()
						.centerY();
				boolean yUpOb = r.centerY() > map.getHuman().getHitbox()
						.centerY();

				boolean xLeftOb = r.centerX() > map.getHuman().getHitbox()
						.centerX();
				boolean xRightOb = r.centerX() < map.getHuman().getHitbox()
						.centerX();

				// int iH =
				// (int)human.getHumanHitBox().intersection(obstacles.get(0)).getHeight();
				// int iW =
				// (int)human.getHumanHitBox().intersection(obstacles.get(0)).getWidth();

				float iH = h.height();
				float iW = h.width();

				if (iH > iW) {

					if (xRightOb) {
						k = 4;
					}
					if (xLeftOb) {
						k = 3;
					}
				} else {
					if (yUpOb) {
						k = 2;
					}
					if (yDownOb) {
						k = 1;
					}
				}

			}
		}

		return k;
	}

	// public int ggCollision() {
	// int k = 0;
	// for (int i = 0; i < map.getGhost1List().size(); i++) {
	// for (int j = 0; j < map.getGhost1List().size(); j++) {
	// RectF b = map.getGhost1List().get(i).getHitbox();
	// RectF r = map.getGhost1List().get(j).getHitbox();
	//
	// if (b.intersect(r)) {
	// RectF h = b;
	// h.setIntersect(map.getGhost1List().get(i).getHitbox(), r);
	//
	// boolean yDownOb = r.centerY() < b.centerY();
	// boolean yUpOb = r.centerY() > b.centerY();
	//
	// boolean xLeftOb = r.centerX() > b.centerX();
	// boolean xRightOb = r.centerX() < b.centerX();
	// float iH = h.height();
	// float iW = h.width();
	//
	// if (iH > iW) {
	//
	// if (xRightOb) {
	// k = 4;
	//
	// }
	// if (xLeftOb) {
	// k = 3;
	// }
	// } else {
	// if (yUpOb) {
	// k = 2;
	// }
	// if (yDownOb) {
	// k = 1;
	// }
	// }
	// }
	// }
	//
	// }
	// return k;
	//
	// }

	/**
	 * Call this on pause.
	 */
	public void onPause() {
		synchronized (mPauseLock) {
			mPaused = true;
		}
	}

	/**
	 * Call this on resume.
	 */
	public void onResume() {
		synchronized (mPauseLock) {
			mPaused = false;
			mPauseLock.notifyAll();
		}
	}

}
