package edu.virginia.cs2110.dqxy;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class MapView extends View implements OnTouchListener {
	private Paint translucentPaint;
	private Paint normalPaint;
	private Paint scorePaint;
	private Paint wallPaint;
	private Paint redPaint;
	private Paint yellowPaint;
	private Paint greenPaint;
	private Human human;
	private int count;
	static ArrayList<Ghost> ghost1List;
	private ArrayList<Ghost> ghost2List;
	private Bitmap smoke;
	private Bitmap moneybag;
	private Bitmap fireball;
	private boolean shake;
	private int bombTimer;
	private boolean alert;
	float mapHeight;
	float mapWidth;
	private ArrayList<RectF> obList;
	private ArrayList<Coin> wallet;
	private ArrayList<EnergyDrink> fridge;
	private ArrayList<Explosion> popcorn;
	boolean detonate = false;
	private int wizCount;
	private int ghostCount;
	private int coinCount;
	private int drinkCount;
	private int addCount;
	private boolean discoOn;
	Random rnd;
	private MediaPlayer killSound;
	private int discoNum;

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
		rnd = new Random();
		shake = false;
		discoOn = false;
		ghost1List = new ArrayList<Ghost>();
		ghost2List = new ArrayList<Ghost>();
		normalPaint = new Paint();
		normalPaint.setARGB(200, 100, 100, 100);
		normalPaint.setTextSize(10);
		translucentPaint = new Paint();
		translucentPaint.setAlpha(125);

		redPaint = new Paint();
		redPaint.setARGB(200, 255, 0, 0);
		yellowPaint = new Paint();
		yellowPaint.setARGB(200, 255, 255, 0);
		greenPaint = new Paint();
		greenPaint.setARGB(200, 0, 255, 0);

		alert = false;
		smoke = BitmapFactory.decodeResource(getResources(), R.drawable.smoke);
		moneybag = BitmapFactory.decodeResource(getResources(),
				R.drawable.moneybag);
		fireball = BitmapFactory.decodeResource(getResources(),
				R.drawable.fire_ball_small);
		wallet = new ArrayList<Coin>();
		fridge = new ArrayList<EnergyDrink>();
		popcorn = new ArrayList<Explosion>();
		scorePaint = new Paint();
		scorePaint.setTextSize(30);

		wallPaint = new Paint();
		wallPaint.setAlpha(125);
		wallPaint.setARGB(200, 100, 100, 100);

		addCount = (int) (800 - ((DiffSetting.difficulty + 10) * 20));

		this.killSound = MediaPlayer.create(this.getContext(), R.raw.explosion);

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
		if (count % addCount == 0) {
			Ghost ghost = new Ghost(1, randNearHuman(human.getX()),
					randNearHuman(human.getY()), this.getContext());
			ghost1List.add(ghost);
		}

		if (count % addCount == 0) {
			Ghost g = new Ghost(2, (float) Math.random()
					* this.getMeasuredWidth(), (float) Math.random()
					* this.getMeasuredHeight(), this.getContext());
			ghost2List.add(g);
		}
	}

	@Override
	public void onDraw(Canvas c) {
		super.onDraw(c);
		// ((Activity)(this.getContext())).setContentView(new GameOverView());
		count++;
		if (count == 1) {
			mapHeight = this.getHeight();
			mapWidth = this.getWidth();

		}
		if (count % 2 == 0) {
			coinCount++;
		}
		if (count % 5 == 0 && Human.walking == true) {
			wizCount++;
		} else {
			if (Human.walking == false) {
				wizCount = 0;
			}
		}

		if (count % 5 == 0) {
			ghostCount++;
			if (ghostCount == 5) {
				ghostCount = 0;
			}
		}
		if (count % 3 == 0) {
			drinkCount++;
		}

		if (GameStart.money <= 0) {
			GameStart.repelButton.setEnabled(false);
			GameStart.repelButton.setImageDrawable(getResources().getDrawable(
					R.drawable.repellent_grey));
			GameStart.spray = false;

		} else if (GameStart.money == 1) {
			GameStart.repelButton.setEnabled(true);
			if (GameStart.spray == false) {
				GameStart.repelButton.setImageDrawable(getResources()
						.getDrawable(R.drawable.repellent_off));
			}
		}
		if (human.getBombRegular() <= 0) {
			GameStart.killButton.setEnabled(false);
		} else {
			GameStart.killButton.setEnabled(true);
		}
		if (discoNum <= 0) {
			GameStart.discoButton.setEnabled(false);
		} else {
			GameStart.discoButton.setEnabled(true);
		}

		if (discoOn) {
			if (count % 4 == 0) {
				int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),
						rnd.nextInt(256));
				this.setBackgroundColor(color);
			}
		}

		drawHuman(c);
		drawGhost(c);
		drawObs(c);
		addGhost();
		drawCoin(c);
		drawDrink(c);
		drawExplosion(c);
		coinGenerator(wallet);
		if (count % 100 == 0) {
			GameStart.score++;
		}

		alert(c);

		c.drawCircle(40, 50, 30, normalPaint);
		drawHPBar(c);

		c.drawBitmap(moneybag, mapWidth * 0.56F, mapHeight * 0.03F, normalPaint);
		c.drawText(String.valueOf(GameStart.money), mapWidth * 0.56F,
				mapHeight * 0.06F, scorePaint);

		c.drawBitmap(fireball, mapWidth * 0.62F, mapHeight * 0.03F, normalPaint);
		c.drawText(String.valueOf(human.getBombRegular()), mapWidth * 0.62F,
				mapHeight * 0.06F, scorePaint);

		c.drawText("Score: " + String.valueOf(GameStart.score),
				mapWidth * 0.8F, mapHeight * 0.06F, scorePaint);
		// kill all zombie if shake
		if (shake && human.getBombSuper() > 0) {
			int temp = human.getBombSuper();
			Log.d("", "bombs before kill" + temp);
			c.drawBitmap(smoke, human.getX() - 20, human.getY() - 20,
					normalPaint);
			killAll();
		}

		if (count - bombTimer >= 100) {
			shake = false;
			// human.setKilTap(false);
		}

	}

	private void drawExplosion(Canvas c) {
		ArrayList<Explosion> removeList = new ArrayList<Explosion>();
		for (Explosion e : popcorn) {
			e.explode();
			int eCount = e.getExplosionCount();
			if (eCount <= 10) {
				c.drawBitmap(e.getExplosion1(), e.getX(), e.getY(), normalPaint);
			}
			if (eCount <= 20) {
				c.drawBitmap(e.getExplosion2(), e.getX(), e.getY(), normalPaint);
			}
			if (eCount <= 30) {
				c.drawBitmap(e.getExplosion3(), e.getX(), e.getY(), normalPaint);
			}
			if (eCount <= 40) {
				c.drawBitmap(e.getExplosion4(), e.getX(), e.getY(), normalPaint);
			}
			if (eCount <= 50) {
				c.drawBitmap(e.getExplosion5(), e.getX(), e.getY(), normalPaint);
			}
			if (eCount > 50) {
				removeList.add(e);
			}
		}
		popcorn.removeAll(removeList);
	}

	private void drawHPBar(Canvas c) {
		Paint paint = normalPaint;
		int hp = human.getHealthPoint();
		if (hp <= 2) {
			paint = redPaint;
		} else if (hp <= 5) {
			paint = yellowPaint;
		} else {
			paint = greenPaint;
		}
		c.drawRect(mapWidth * 0.10F, mapHeight * 0.02F,
				(float) (mapWidth * (0.10F + 0.50F * hp / 10)),
				mapHeight * 0.07F, paint);
	}

	private void drawObs(Canvas c) {
		for (RectF r : obList) {
			c.drawRect(r, wallPaint);
		}
	}

	public void drawGhost(Canvas c) {
		for (Ghost ghost : ghost1List) {
			// Log.d("mapview", "Orientation:" + ghost.getOrientation()
			// + "ghostcount: " + ghostCount);
			if (ghost.getOrientation() == 0) {
				// Log.d("mapview", "ghostcount: " + ghostCount);

				if (ghostCount == 0) {
					c.drawBitmap(ghost.getG_right2(), ghost.getX(),
							ghost.getY(), normalPaint);
					// Log.d("mapview", "ghostdrawright2");
				}
				if (ghostCount == 1) {
					c.drawBitmap(ghost.getG_right1(), ghost.getX(),
							ghost.getY(), normalPaint);
					// Log.d("mapview", "ghostdrawright1");
				}
				if (ghostCount == 2) {
					c.drawBitmap(ghost.getG_right2(), ghost.getX(),
							ghost.getY(), normalPaint);
					// Log.d("mapview", "ghostdrawright2");
				}
				if (ghostCount == 3) {
					c.drawBitmap(ghost.getG_right3(), ghost.getX(),
							ghost.getY(), normalPaint);
					// Log.d("mapview", "ghostdrawright3");
				}
				if (ghostCount == 4) {
					c.drawBitmap(ghost.getG_right2(), ghost.getX(),
							ghost.getY(), normalPaint);
					// Log.d("mapview", "ghostdrawright2");
				}
			}
			if (ghost.getOrientation() == 1) {
				c.drawBitmap(ghost.getG_left2(), ghost.getX(), ghost.getY(),
						normalPaint);
				// Log.d("mapview", "Orientation: 1 ghostcount: " + ghostCount);
				if (ghostCount == 0) {
					c.drawBitmap(ghost.getG_left2(), ghost.getX(),
							ghost.getY(), normalPaint);
					// Log.d("mapview", "ghostdrawleft2");
				}
				if (ghostCount == 1) {
					c.drawBitmap(ghost.getG_left1(), ghost.getX(),
							ghost.getY(), normalPaint);
					// Log.d("mapview", "ghostdrawleft1");
				}
				if (ghostCount == 2) {
					c.drawBitmap(ghost.getG_left2(), ghost.getX(),
							ghost.getY(), normalPaint);
					// Log.d("mapview", "ghostdrawleft2");
				}
				if (ghostCount == 3) {
					c.drawBitmap(ghost.getG_left3(), ghost.getX(),
							ghost.getY(), normalPaint);
					// Log.d("mapview", "ghostdrawleft3");
				}
				if (ghostCount == 4) {
					c.drawBitmap(ghost.getG_left2(), ghost.getX(),
							ghost.getY(), normalPaint);
					ghostCount = 0;
					// Log.d("mapview", "ghostdrawleft2");
				}

			}
			if (ghost.getOrientation() == 2) {

				if (ghostCount == 0) {
					c.drawBitmap(ghost.getG_back2(), ghost.getX(),
							ghost.getY(), normalPaint);
				}
				if (ghostCount == 1) {
					c.drawBitmap(ghost.getG_back1(), ghost.getX(),
							ghost.getY(), normalPaint);
				}
				if (ghostCount == 2) {
					c.drawBitmap(ghost.getG_back2(), ghost.getX(),
							ghost.getY(), normalPaint);
				}
				if (ghostCount == 3) {
					c.drawBitmap(ghost.getG_back3(), ghost.getX(),
							ghost.getY(), normalPaint);
				}
				if (ghostCount == 4) {
					c.drawBitmap(ghost.getG_back2(), ghost.getX(),
							ghost.getY(), normalPaint);
					ghostCount = 0;
				}

			}
			if (ghost.getOrientation() == 3) {

				if (ghostCount == 0) {
					c.drawBitmap(ghost.getG_front2(), ghost.getX(),
							ghost.getY(), normalPaint);
				}
				if (ghostCount == 1) {
					c.drawBitmap(ghost.getG_front1(), ghost.getX(),
							ghost.getY(), normalPaint);
				}
				if (ghostCount == 2) {
					c.drawBitmap(ghost.getG_front2(), ghost.getX(),
							ghost.getY(), normalPaint);
				}
				if (ghostCount == 3) {
					c.drawBitmap(ghost.getG_front3(), ghost.getX(),
							ghost.getY(), normalPaint);
				}
				if (ghostCount == 4) {
					c.drawBitmap(ghost.getG_front2(), ghost.getX(),
							ghost.getY(), normalPaint);
					ghostCount = 0;
				}

			}
		}
		for (Ghost ghost : ghost2List) {
			if (ghost.getOrientation() == 0) {
				c.drawBitmap(ghost.getG2_right(), ghost.getX(), ghost.getY(),
						normalPaint);
			}
			if (ghost.getOrientation() == 1) {
				c.drawBitmap(ghost.getG2_left(), ghost.getX(), ghost.getY(),
						normalPaint);
			}
			if (ghost.getOrientation() == 2) {
				c.drawBitmap(ghost.getG2_back(), ghost.getX(), ghost.getY(),
						normalPaint);
			}
			if (ghost.getOrientation() == 3) {

				c.drawBitmap(ghost.getG2_front(), ghost.getX(), ghost.getY(),
						normalPaint);
			}
		}
	}

	public void alert(Canvas c) {
		int num = 0;
		for (Ghost g : ghost2List) {
			double dis_x = Math.abs(human.getX() - g.getX());
			double dis_y = Math.abs(human.getY() - g.getY());
			double dis = Math.sqrt(Math.pow(dis_x, 2) + Math.pow(dis_y, 2));
			if (dis <= 100) {
				this.alert = true;
				num++;
				Paint p = new Paint();

				p.setARGB(num * 7 + 200, 255, 0, 0);
				c.drawCircle(40, 50, 25, p);
			}

		}

	}

	public void drawHuman(Canvas c) {
		if (human.getOrientation() == 0) {

			if (wizCount == 0) {
				c.drawBitmap(human.getH_right2(), human.getX(), human.getY(),
						normalPaint);
			}
			if (wizCount == 1) {
				c.drawBitmap(human.getH_right1(), human.getX(), human.getY(),
						normalPaint);
			}
			if (wizCount == 2) {
				c.drawBitmap(human.getH_right2(), human.getX(), human.getY(),
						normalPaint);
			}
			if (wizCount == 3) {
				c.drawBitmap(human.getH_right3(), human.getX(), human.getY(),
						normalPaint);
			}
			if (wizCount == 4) {
				c.drawBitmap(human.getH_right2(), human.getX(), human.getY(),
						normalPaint);
				wizCount = 0;
			}

		}
		if (human.getOrientation() == 1) {

			if (wizCount == 0) {
				c.drawBitmap(human.getH_left2(), human.getX(), human.getY(),
						normalPaint);
			}
			if (wizCount == 1) {
				c.drawBitmap(human.getH_left1(), human.getX(), human.getY(),
						normalPaint);
			}
			if (wizCount == 2) {
				c.drawBitmap(human.getH_left2(), human.getX(), human.getY(),
						normalPaint);
			}
			if (wizCount == 3) {
				c.drawBitmap(human.getH_left3(), human.getX(), human.getY(),
						normalPaint);
			}
			if (wizCount == 4) {
				c.drawBitmap(human.getH_left2(), human.getX(), human.getY(),
						normalPaint);
				wizCount = 0;
			}
		}

		if (human.getOrientation() == 2) {
			if (wizCount == 0) {
				c.drawBitmap(human.getH_backFacing2(), human.getX(),
						human.getY(), normalPaint);
			}
			if (wizCount == 1) {
				c.drawBitmap(human.getH_backFacing1(), human.getX(),
						human.getY(), normalPaint);
			}
			if (wizCount == 2) {
				c.drawBitmap(human.getH_backFacing2(), human.getX(),
						human.getY(), normalPaint);
			}
			if (wizCount == 3) {
				c.drawBitmap(human.getH_backFacing3(), human.getX(),
						human.getY(), normalPaint);
			}
			if (wizCount == 4) {
				c.drawBitmap(human.getH_backFacing2(), human.getX(),
						human.getY(), normalPaint);
				wizCount = 0;
			}
		}
		if (human.getOrientation() == 3) {
			if (wizCount == 0) {
				c.drawBitmap(human.getH_frontFacing2(), human.getX(),
						human.getY(), normalPaint);
			}
			if (wizCount == 1) {
				c.drawBitmap(human.getH_frontFacing1(), human.getX(),
						human.getY(), normalPaint);
			}
			if (wizCount == 2) {
				c.drawBitmap(human.getH_frontFacing2(), human.getX(),
						human.getY(), normalPaint);
			}
			if (wizCount == 3) {
				c.drawBitmap(human.getH_frontFacing3(), human.getX(),
						human.getY(), normalPaint);
			}
			if (wizCount == 4) {
				c.drawBitmap(human.getH_frontFacing2(), human.getX(),
						human.getY(), normalPaint);
				wizCount = 0;
			}
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

	public void regularKill() {
		if (human.getBombRegular() > 0) {
			killSound.start();

			ArrayList<Ghost> ghostList_dead = new ArrayList<Ghost>();
			for (Ghost g : getGhost2List()) {
				double dis_x = human.getX() - g.getX();
				double dis_y = human.getY() - g.getY();
				double dis = dis_x * dis_x + dis_y * dis_y;
				if (dis < 25000) {
					GameStart.score += 10;
					ghostList_dead.add(g);
					GameStart.numKill++;
					drinkCount = 0;
					if (count % 4 != 0) {
						wallet.add(new Coin(g.getX(), g.getY(), this
								.getContext()));
					} else {
						fridge.add(new EnergyDrink(g.getX(), g.getY(), this
								.getContext()));
					}
				}
			}
			for (Ghost g : ghost1List) {
				double dis_x = human.getX() - g.getX();
				double dis_y = human.getY() - g.getY();
				double dis = dis_x * dis_x + dis_y * dis_y;
				if (dis < 25000) {
					GameStart.score += 10;
					ghostList_dead.add(g);
					GameStart.numKill++;
					drinkCount = 0;
					if (count % 4 != 0) {
						wallet.add(new Coin(g.getX(), g.getY(), this
								.getContext()));
					} else {
						fridge.add(new EnergyDrink(g.getX(), g.getY(), this
								.getContext()));
					}
				}
			}
			for (Ghost g : ghostList_dead) {
				popcorn.add(new Explosion(g.getX(), g.getY() - 10, this
						.getContext()));
			}
			ghost1List.removeAll(ghostList_dead);
			ghost2List.removeAll(ghostList_dead);
			// synchronized (GameStart.baton) {
			// Iterator<Ghost> i1 = ghost1List.iterator();
			// while (i1.hasNext()) {
			// Ghost g = i1.next();
			// double dis_x = human.getX() - g.getX();
			// double dis_y = human.getY() - g.getY();
			// double dis = dis_x * dis_x + dis_y * dis_y;
			// if (dis < 25000) {
			// i1.remove();
			// }
			// }
			// Iterator<Ghost> i2 = ghost2List.iterator();
			// while (i2.hasNext()) {
			// Ghost g = i2.next();
			// double dis_x = human.getX() - g.getX();
			// double dis_y = human.getY() - g.getY();
			// double dis = dis_x * dis_x + dis_y * dis_y;
			// if (dis < 25000) {
			// i2.remove();
			// }
			// }
			// }

		} else {
			Toast.makeText(this.getContext(), "You are out of bombs!",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void drawCoin(Canvas c) {

		for (Coin coin : wallet) {
			if (coinCount == 1) {
				c.drawBitmap(coin.getCoin1(), coin.getX(), coin.getY(),
						normalPaint);
			}
			if (coinCount == 2) {
				c.drawBitmap(coin.getCoin2(), coin.getX(), coin.getY(),
						normalPaint);
			}
			if (coinCount == 3) {
				c.drawBitmap(coin.getCoin3(), coin.getX(), coin.getY(),
						normalPaint);
			}
			if (coinCount == 4) {
				c.drawBitmap(coin.getCoin4(), coin.getX(), coin.getY(),
						normalPaint);
			}
			if (coinCount == 5) {
				c.drawBitmap(coin.getCoin5(), coin.getX(), coin.getY(),
						normalPaint);
			}
			if (coinCount == 6) {
				c.drawBitmap(coin.getCoin6(), coin.getX(), coin.getY(),
						normalPaint);
			}
			if (coinCount == 7) {
				c.drawBitmap(coin.getCoin7(), coin.getX(), coin.getY(),
						normalPaint);
			}
			if (coinCount == 8) {
				c.drawBitmap(coin.getCoin8(), coin.getX(), coin.getY(),
						normalPaint);
				coinCount = 1;
			}
		}
	}

	private void drawDrink(Canvas c) {
		for (EnergyDrink drink : fridge) {
			if (drinkCount == 1) {
				c.drawBitmap(drink.getDrink1(), drink.getX(), drink.getY(),
						normalPaint);
			}
			if (drinkCount == 2) {
				c.drawBitmap(drink.getDrink2(), drink.getX(), drink.getY(),
						normalPaint);
			}
			if (drinkCount == 3) {
				c.drawBitmap(drink.getDrink3(), drink.getX(), drink.getY(),
						normalPaint);
			}
			if (drinkCount == 4) {
				c.drawBitmap(drink.getDrink2(), drink.getX(), drink.getY(),
						normalPaint);
			}
			if (drinkCount == 5) {
				c.drawBitmap(drink.getDrink1(), drink.getX(), drink.getY(),
						normalPaint);
				drinkCount = 1;
			}
		}

	}

	private void coinGenerator(ArrayList<Coin> wallet) {
		Random dice = new Random();
		int h = this.getMeasuredHeight();
		int w = this.getMeasuredWidth();
		float x = dice.nextInt(w);
		float y = dice.nextInt(h);
		if (wallet.isEmpty()) {
			if (x > w * 0.99 || x < w * 0.01) {
				coinGenerator(wallet);
			}
			if (y > h * 0.99 || y < w * 0.01) {
				coinGenerator(wallet);
			}
			Coin coin = new Coin(x, y, this.getContext());
			wallet.add(coin);
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

	synchronized public ArrayList<Ghost> getGhost2List() {
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

	public ArrayList<Coin> getWallet() {
		return wallet;
	}

	public void setWallet(ArrayList<Coin> wallet) {
		this.wallet = wallet;
	}

	public ArrayList<RectF> getObList() {
		return obList;
	}

	public void setObList(ArrayList<RectF> obList) {
		this.obList = obList;
	}

	public ArrayList<EnergyDrink> getFridge() {
		return fridge;
	}

	public void setFridge(ArrayList<EnergyDrink> fridge) {
		this.fridge = fridge;
	}

	public ArrayList<Explosion> getPopcorn() {
		return popcorn;
	}

	public int getDiscoNum() {
		return discoNum;
	}

	public void setDiscoNum(int discoNum) {
		this.discoNum = discoNum;
	}

	public void setPopcorn(ArrayList<Explosion> popcorn) {
		this.popcorn = popcorn;
	}

	public boolean isDiscoOn() {
		return discoOn;
	}

	public void setDiscoOn(boolean discoOn) {
		this.discoOn = discoOn;
	}

}
