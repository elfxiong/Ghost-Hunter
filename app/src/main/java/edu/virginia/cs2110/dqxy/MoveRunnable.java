package edu.virginia.cs2110.dqxy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.util.Log;

public class MoveRunnable implements Runnable {
	private Activity gameActivity;
	private MapView map;
	private Object mPauseLock;
	private boolean mPaused;
	private boolean mFinished;
	private int count;
	private boolean energy;
	private int drinkCount;
	private MediaPlayer coinSound;
	private MediaPlayer drinkSound;

	public MoveRunnable(MapView map) {
		mPauseLock = new Object();
		mPaused = false;
		mFinished = false;
		count = 0;
		this.map = map;
		this.gameActivity = (Activity) map.getContext();
		this.coinSound = MediaPlayer.create(gameActivity, R.raw.coins_drop);
		this.drinkSound = MediaPlayer.create(gameActivity, R.raw.energy_drink);
	}

	@Override
	public void run() {
		if (GameStart.gameover) {
			return;
		}
		while (!mFinished && !GameStart.gameover) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			count++;
			humanMovement();

			if (!map.isDiscoOn()) {
				ghostMovement();
			} else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
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

	private void ghostMovement() {
		// move ghost
		ghost1Move();

		// kill ghost From the back
		synchronized (GameStart.baton) {
			ArrayList<Ghost> ghost2List_dead = new ArrayList<Ghost>();
			for (Ghost g : map.getGhost2List()) {
				g.moveIndependent(map);
				if (this.gCollision(g)) {
					ghost2List_dead.add(g);
					GameStart.numKill++;
					GameStart.money++;
				}
			}
			map.getGhost2List().removeAll(ghost2List_dead);
		}

		if (count % 100 == 0) {
			for (Ghost g : map.getGhost2List()) {
				float vx = (float) Math.random() * 2 - 1;
				float vy = (float) Math.random() * 2 - 1;

				g.setX_change(vx);
				g.setY_change(vy);
			}
		}
	}

	private void humanMovement() {
		map.getHuman().immuneCountDown();
		// move human
		map.getHuman().updateHitBox();
		map.getHuman().HumanMove(this.hCollision());
		collectCoin();
		collectDrink();
		sprayer();

		// checks if energy drink effects have worn off
		if (energy == true) {
			if (count % 30 == 0) {
				drinkCount++;
			}
			if (drinkCount > 10) {
				map.getHuman().setV(4);
				Log.d("v: ", "v: " + map.getHuman().getV());
				energy = false;
				drinkCount = 0;
			}
		}

		// human collide with ghost
		for (Ghost g : map.getGhost1List()) {
			if (RectF.intersects(map.getHuman().getHitbox(), g.getHitbox())) {
				if (!map.getHuman().hpDecrease()) {
					setGameOver();
					return;
				}
			}
		}
		for (Ghost g : map.getGhost2List()) {
			if (RectF.intersects(map.getHuman().getHitbox(), g.getHitbox())) {
				if (!map.getHuman().hpDecrease()) {
					setGameOver();
					return;
				}
			}
		}

		// gravity
		float sensorX = ((GameStart) map.getContext()).sensorX;
		float sensorY = ((GameStart) map.getContext()).sensorY;
		if (sensorX > 1 || sensorX < -1) {
			map.getHuman()
					.setX((float) (map.getHuman().getX() - sensorX / 2.5));
		}
		if (sensorY > 1 || sensorY < -1) {
			map.getHuman()
					.setY((float) (map.getHuman().getY() + sensorY / 2.5));
		}

	}

	private void collectCoin() {

		ArrayList<Coin> coinList = map.getWallet();
		ArrayList<Coin> removeList = new ArrayList<Coin>();
		Human human = map.getHuman();
		for (Coin c : coinList) {
			double dis_x = human.getX() - c.getX();
			double dis_y = human.getY() - c.getY();
			double dis = dis_x * dis_x + dis_y * dis_y;
			if (dis < 7000) {
				removeList.add(c);
				GameStart.money += 1;
				coinSound.start();
			}
		}
		coinList.removeAll(removeList);
	}

	private void collectDrink() {
		ArrayList<EnergyDrink> drinkList = map.getFridge();
		ArrayList<EnergyDrink> removeList = new ArrayList<EnergyDrink>();
		Human human = map.getHuman();
		for (EnergyDrink ed : drinkList) {
			double dis_x = human.getX() - ed.getX();
			double dis_y = human.getY() - ed.getY();
			double dis = dis_x * dis_x + dis_y * dis_y;
			if (dis < 7000) {
				drinkSound.start();
				removeList.add(ed);
				human.setV(8);
				energy = true;
				drinkCount = 0;
			}
		}
		drinkList.removeAll(removeList);
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

	public boolean gCollision(Ghost g) {
		boolean k = false;
		Float gcx = g.getHitbox().centerX();
		Float gcy = g.getHitbox().centerX();
		Float hcx = map.getHuman().getHitbox().centerX();
		Float hcy = map.getHuman().getHitbox().centerY();
		if (g.getHitbox().intersect(map.getHuman().getHitbox())) {
			if ((g.getOrientation() == 2 && map.getHuman().getOrientation() == 2)
					&& gcy < hcy) {
				k = true;
			}
			if ((g.getOrientation() == 1 && map.getHuman().getOrientation() == 1)
					&& gcx < hcx) {
				k = true;
			}
			if ((g.getOrientation() == 3 && map.getHuman().getOrientation() == 3)
					&& gcy > hcy) {
				k = true;
			}
			if ((g.getOrientation() == 4 && map.getHuman().getOrientation() == 4)
					&& gcy < hcy) {
				k = true;
			}
		}
		return k;
	}

	public static void sprayer() {
		if (GameStart.spray == true) {
			GameStart.repeller++;
			if (GameStart.repeller % 25 == 0 && GameStart.money > 0) {
				GameStart.money -= 1;
			}
		}
	}

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

	public void setFinish() {
		mFinished = true;
	}

	private void setGameOver() {
		GameStart.gameover = true;
		this.setFinish();
		Intent gameoverIntent = new Intent(gameActivity, GameOver.class);
		gameActivity.startActivity(gameoverIntent);
		gameActivity.finish();// finish after start
	}

}
