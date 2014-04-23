package edu.virginia.cs2110.dqxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class MapView extends View implements Serializable, OnTouchListener {

	private static final long serialVersionUID = -6303933339537109651L;
	private Paint translucentPaint;
	private Paint redPaint;
	private Paint scorePaint;
	private Paint wallPaint;
	private Human human;
	private double dangerLevel;
	private int count;
	private ArrayList<Ghost> ghost1List;
	private ArrayList<Ghost> ghost2List;
	private Bitmap smoke;
	private boolean shake;
	private int bombTimer;
	private boolean alert;
	float mapHeight;
	float mapWidth;
	private ArrayList<RectF> ObList = new ArrayList<RectF>();
	private ArrayList<Coin> wallet;
	private Coin coin;
	private int coinNumber;
	private int coiner;

	public MapView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MapView(Context context) {
		super(context);
		init();
	}

	// init method sets up paint
	public void init() {
		shake = false;
		ghost1List = new ArrayList<Ghost>();
		ghost2List = new ArrayList<Ghost>();
		redPaint = new Paint();
		redPaint.setARGB(200, 100, 100, 100);
		redPaint.setTextSize(10);
		translucentPaint = new Paint();
		translucentPaint.setAlpha(125);
		alert = false;
		smoke = BitmapFactory.decodeResource(getResources(), R.drawable.smoke);

		RectF rA = new RectF();
		rA.set(300, 200, 700, 1000);
		ObList.add(rA);
		coin = new Coin(60, 500, this.getContext());
		wallet = new ArrayList<Coin>();
		wallet.add(coin);
		scorePaint = new Paint();
		scorePaint.setTextSize(40);
		wallPaint = new Paint();
		wallPaint.setAlpha(125);

	}

	public void killAll() {
		this.ghost1List.clear();
		this.ghost2List.clear();
	}

	public float randNearHuman(float f) {
		Random r = new Random();
		float low = f + 100;
		float high = f + 200;
		float ret = (float) r.nextInt((int) (high - low)) + low;
		return ret;
	}

	public void addGhost() {
		if (count % 200 == 0) {
			Ghost ghost = new Ghost(1, randNearHuman(human.getX()),
					randNearHuman(human.getY()), this.getContext());
			ghost1List.add(ghost);

		}

		if (count % 300 == 0) {
			Ghost g = new Ghost(2, (float) Math.random()
					* this.getMeasuredWidth(), (float) Math.random()
					* this.getMeasuredHeight(), this.getContext());
			ghost2List.add(g);
		}

	}

	@Override
	public void onDraw(Canvas c) {
		super.onDraw(c);

		count++;
		if (count % 5 == 0) {
			coiner++;
		}
		if (count == 1) {
			mapHeight = this.getHeight();
			mapWidth = this.getWidth();

		}
		DrawHuman(c);
		drawGhost(c);
		DrawObs(c);
		addGhost();
		drawCoin(c);
		Coin.collectCoin(human, wallet);
		if (count % 20 == 0) {
			GameStart.score++;
		}
		c.drawText(String.valueOf(GameStart.score), mapWidth * 0.9F,
				mapHeight * 0.06F, scorePaint);

		c.drawText(String.valueOf(GameStart.money), mapWidth * 0.2F,
				mapHeight * 0.06F, scorePaint);

		c.drawRect(mapWidth * 0.30F, mapHeight * 0.03F, (float) (mapWidth
				* 0.70F * (10 - dangerLevel) / 10), mapHeight * 0.07F, redPaint);
		c.drawCircle(50, 50, 30, redPaint);
		c.drawText("The number of Ghosts: " + ghost1List.size(),
				mapWidth * 0.75F, mapHeight * 0.07F, redPaint);

		c.drawRect(mapWidth * 0.15F, mapHeight * 0.2F, mapWidth * 0.4F,
				mapHeight * 0.3F, wallPaint);
		c.drawRect(mapWidth * 0.6F, mapHeight * 0.2F, mapWidth * 0.85F,
				mapHeight * 0.3F, wallPaint);
		c.drawRect(mapWidth * 0.15F, mapHeight * 0.4F, mapWidth * 0.25F,
				mapHeight * 0.6F, wallPaint);
		c.drawRect(mapWidth * 0.4F, mapHeight * 0.4F, mapWidth * 0.6F,
				mapHeight * 0.6F, wallPaint);
		c.drawRect(mapWidth * 0.75F, mapHeight * 0.4F, mapWidth * 0.85F,
				mapHeight * 0.6F, wallPaint);
		c.drawRect(mapWidth * 0.15F, mapHeight * 0.7F, mapWidth * 0.4F,
				mapHeight * 0.8F, wallPaint);
		c.drawRect(mapWidth * 0.6F, mapHeight * 0.7F, mapWidth * 0.85F,
				mapHeight * 0.8F, wallPaint);

		alert(c);

		// kill all zombie if shake
		if (shake && human.getBombSuper() > 0) {
			int temp = human.getBombSuper();
			Log.d("", "bombs before kill" + temp);
			c.drawBitmap(smoke, human.getX() - 20, human.getY() - 20, redPaint);
			killAll();
		}

		if (count - bombTimer >= 100) {
			shake = false;
			// human.setKilTap(false);
		}

	}

	private void DrawObs(Canvas c) {
		for (RectF r : ObList) {
			c.drawRect(r, redPaint);
		}
	}

	public void drawGhost(Canvas c) {
		for (Ghost ghost : ghost1List) {
			if (ghost.getOrientation() == 0) {
				c.drawBitmap(ghost.getG_right(), ghost.getX(), ghost.getY(),
						redPaint);
			}
			if (ghost.getOrientation() == 1) {
				c.drawBitmap(ghost.getG_left(), ghost.getX(), ghost.getY(),
						redPaint);
			}
			if (ghost.getOrientation() == 2) {
				c.drawBitmap(ghost.getG_backFacing(), ghost.getX(),
						ghost.getY(), redPaint);
			}
			if (ghost.getOrientation() == 3) {

				c.drawBitmap(ghost.getG_frontFacing(), ghost.getX(),
						ghost.getY(), redPaint);
			}
			c.drawRect(ghost.getHitbox(), redPaint);
		}
		for (Ghost ghost : ghost2List) {
			if (ghost.getOrientation() == 0) {
				c.drawBitmap(ghost.getG2_right(), ghost.getX(), ghost.getY(),
						redPaint);
				// Log.d("MapView","right");
			}
			if (ghost.getOrientation() == 1) {
				c.drawBitmap(ghost.getG2_left(), ghost.getX(), ghost.getY(),
						redPaint);
				// Log.d("MapView","left");
			}
			if (ghost.getOrientation() == 2) {
				c.drawBitmap(ghost.getG2_back(), ghost.getX(), ghost.getY(),
						redPaint);
				// Log.d("MapView","back");
			}
			if (ghost.getOrientation() == 3) {

				c.drawBitmap(ghost.getG2_front(), ghost.getX(), ghost.getY(),
						redPaint);
				// Log.d("MapView","front");
			}
		}
	}

	public void alert(Canvas c) {
		int num = 0;
		for (Ghost g : ghost2List) {
			double dis_x = Math.abs(human.getX() - g.getX());
			double dis_y = Math.abs(human.getY() - g.getY());
			double dis = Math.sqrt(Math.pow(dis_x, 2) + Math.pow(dis_y, 2));
			// Log.d("", "" + dis);
			if (dis <= 100) {
				this.alert = true;
				num++;
				Paint p = new Paint();

				p.setARGB(num * 7 + 200, 255, 0, 0);
				// p.setARGB(255, 255, 0, 0);
				c.drawCircle(50, 50, 25, p);

			}

		}

	}

	public void DrawHuman(Canvas c) {
		if (human.getOrientation() == 0) {
			c.drawBitmap(human.getH_right(), human.getX(), human.getY(),
					redPaint);
		}
		if (human.getOrientation() == 1) {
			c.drawBitmap(human.getH_left(), human.getX(), human.getY(),
					redPaint);
		}
		if (human.getOrientation() == 2) {
			c.drawBitmap(human.getH_backFacing(), human.getX(), human.getY(),
					redPaint);
		}
		if (human.getOrientation() == 3) {
			c.drawBitmap(human.getH_frontFacing(), human.getX(), human.getY(),
					redPaint);
		}

	}

	public Human getHuman() {
		return human;
	}

	public void setHuman(Human human) {
		this.human = human;
	}

	public ArrayList<Ghost> getGhost1List() {
		return ghost1List;
	}

	public void setGhost1List(ArrayList<Ghost> ghost1List) {
		this.ghost1List = ghost1List;
	}

	public void RegularKill() {

		if (human.getBombRegular() > 0) {
			Toast.makeText(this.getContext(),
					"Bomb:" + (human.getBombRegular() - 1), Toast.LENGTH_SHORT)
					.show();
			// for (Ghost g : ghost2List) {
			// double dis_x = human.getX() - g.getX();
			// double dis_y = human.getY() - g.getY();
			// double dis = dis_x * dis_x + dis_y * dis_y;
			// if (dis < 150 * 150) {
			// ghost2List.remove(g);
			// }
			// }
			// for (Ghost g : ghost1List) {
			// double dis_x = human.getX() - g.getX();
			// double dis_y = human.getY() - g.getY();
			// double dis = dis_x * dis_x + dis_y * dis_y;
			// if (dis < 150 * 150) {
			// ghost1List.remove(g);
			// }
			// }

			Iterator<Ghost> i1 = ghost1List.iterator();
			while (i1.hasNext()) {
				Ghost g = i1.next();
				double dis_x = human.getX() - g.getX();
				double dis_y = human.getY() - g.getY();
				double dis = dis_x * dis_x + dis_y * dis_y;
				if (dis < 150 * 150) {
					i1.remove();
				}
			}
			Iterator<Ghost> i2 = ghost2List.iterator();
			while (i2.hasNext()) {
				Ghost g = i2.next();
				double dis_x = human.getX() - g.getX();
				double dis_y = human.getY() - g.getY();
				double dis = dis_x * dis_x + dis_y * dis_y;
				if (dis < 150 * 150) {
					i2.remove();
				}
			}

		} else {
			Toast.makeText(this.getContext(), "You are out of bombs!",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void drawCoin(Canvas c) {

		for (Coin coin : wallet) {
			if (coiner == 1) {
				c.drawBitmap(coin.getCoin1(), coin.getX(), coin.getY(),
						redPaint);
			}
			if (coiner == 2) {
				c.drawBitmap(coin.getCoin2(), coin.getX(), coin.getY(),
						redPaint);
			}
			if (coiner == 3) {
				c.drawBitmap(coin.getCoin3(), coin.getX(), coin.getY(),
						redPaint);
			}
			if (coiner == 4) {
				c.drawBitmap(coin.getCoin4(), coin.getX(), coin.getY(),
						redPaint);
			}
			if (coiner == 5) {
				c.drawBitmap(coin.getCoin5(), coin.getX(), coin.getY(),
						redPaint);
			}
			if (coiner == 6) {
				c.drawBitmap(coin.getCoin6(), coin.getX(), coin.getY(),
						redPaint);
			}
			if (coiner == 7) {
				c.drawBitmap(coin.getCoin7(), coin.getX(), coin.getY(),
						redPaint);
			}
			if (coiner == 8) {
				c.drawBitmap(coin.getCoin8(), coin.getX(), coin.getY(),
						redPaint);
				coiner = 1;
			}
		}
	}

	public String printGhostList() {
		String retVal = "";
		for (Ghost g : ghost1List) {
			retVal += g.toString() + ";";
		}
		for (Ghost g : ghost2List) {
			retVal += g.toString() + ";";
		}
		return retVal;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		human.setX_(event.getX());
		human.setY_(event.getY());
		Log.d("MapView", "touch");
		return true;
	}

	public ArrayList<Ghost> getGhost2List() {
		return ghost2List;
	}

	public void setGhost2List(ArrayList<Ghost> ghost2List) {
		this.ghost2List = ghost2List;
	}

	public boolean isShake() {
		return shake;
	}

	public void setShake(boolean shake) {
		this.shake = shake;
	}

	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}

	public ArrayList<RectF> getObList() {
		return ObList;
	}

	public void setObList(ArrayList<RectF> obList) {
		ObList = obList;
	}
}
