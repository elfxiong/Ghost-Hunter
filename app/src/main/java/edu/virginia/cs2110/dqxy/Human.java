package edu.virginia.cs2110.dqxy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.view.View;

public class Human extends View {

	static boolean walking = false;
	private int orientation; // 0 for right, 1 for left, 2 for back facing 3 for
								// front facing
	private int healthPoint;
	private float x;
	private float y;
	private float x_;
	private float y_;
	private float v = 4;
	private RectF hitbox = new RectF();
	private int bombRegular;
	private int bombSuper;
	private boolean immune = false;
	private int immuneCountDown;
	private Bitmap h_frontFacing1;
	private Bitmap h_frontFacing2;
	private Bitmap h_frontFacing3;
	private Bitmap h_backFacing1;
	private Bitmap h_backFacing2;
	private Bitmap h_backFacing3;
	private Bitmap h_right1;
	private Bitmap h_right2;
	private Bitmap h_right3;
	private Bitmap h_left1;
	private Bitmap h_left2;
	private Bitmap h_left3;

	public RectF getHitbox() {
		return hitbox;
	}

	public void setHitbox(RectF hitbox) {
		this.hitbox = hitbox;
	}


	public int getBombRegular() {
		return bombRegular;
	}

	public void setBombRegular(int bombRegular) {
		this.bombRegular = bombRegular;
	}

	public int getBombSuper() {
		return bombSuper;
	}

	public void setBombSuper(int bombSuper) {
		this.bombSuper = bombSuper;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public Human(float x, float y, int hp, Context mapView) {
		super(mapView);
		init(x, y, hp);

	}

	public void init(float x, float y, int hp) {
		this.x = x;
		this.y = y;
		this.x_ = x;
		this.y_ = y;
		healthPoint = hp;

		// Resources res = getResources();

		this.h_frontFacing1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_front1);
		this.h_frontFacing2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_front2);
		this.h_frontFacing3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_front3);
		this.h_backFacing1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_back1);
		this.h_backFacing2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_back2);
		this.h_backFacing3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_back3);
		this.h_right1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_right1);
		this.h_right2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_right2);
		this.h_right3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_right3);
		this.h_left1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_left1);
		this.h_left2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_left2);
		this.h_left3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.wizard_left3);

	}

	public Bitmap getH_frontFacing1() {
		return h_frontFacing1;
	}

	public void setH_frontFacing1(Bitmap h_frontFacing1) {
		this.h_frontFacing1 = h_frontFacing1;
	}

	public Bitmap getH_frontFacing2() {
		return h_frontFacing2;
	}

	public void setH_frontFacing2(Bitmap h_frontFacing2) {
		this.h_frontFacing2 = h_frontFacing2;
	}

	public Bitmap getH_frontFacing3() {
		return h_frontFacing3;
	}

	public void setH_frontFacing3(Bitmap h_frontFacing3) {
		this.h_frontFacing3 = h_frontFacing3;
	}

	public Bitmap getH_backFacing1() {
		return h_backFacing1;
	}

	public void setH_backFacing1(Bitmap h_backFacing1) {
		this.h_backFacing1 = h_backFacing1;
	}

	public Bitmap getH_backFacing2() {
		return h_backFacing2;
	}

	public void setH_backFacing2(Bitmap h_backFacing2) {
		this.h_backFacing2 = h_backFacing2;
	}

	public Bitmap getH_backFacing3() {
		return h_backFacing3;
	}

	public void setH_backFacing3(Bitmap h_backFacing3) {
		this.h_backFacing3 = h_backFacing3;
	}

	public Bitmap getH_right1() {
		return h_right1;
	}

	public void setH_right1(Bitmap h_right1) {
		this.h_right1 = h_right1;
	}

	public Bitmap getH_right2() {
		return h_right2;
	}

	public void setH_right2(Bitmap h_right2) {
		this.h_right2 = h_right2;
	}

	public Bitmap getH_right3() {
		return h_right3;
	}

	public void setH_right3(Bitmap h_right3) {
		this.h_right3 = h_right3;
	}

	public Bitmap getH_left1() {
		return h_left1;
	}

	public void setH_left1(Bitmap h_left1) {
		this.h_left1 = h_left1;
	}

	public Bitmap getH_left2() {
		return h_left2;
	}

	public void setH_left2(Bitmap h_left2) {
		this.h_left2 = h_left2;
	}

	public Bitmap getH_left3() {
		return h_left3;
	}

	public void setH_left3(Bitmap h_left3) {
		this.h_left3 = h_left3;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX_() {
		return x_;
	}

	public void setX_(float x_) {
		this.x_ = x_;
	}

	public float getY_() {
		return y_;
	}

	public void setY_(float y_) {
		this.y_ = y_;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public boolean isImmune() {
		return immune;
	}

	public void setImmune(boolean immune) {
		this.immune = immune;
	}

	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
	}

	public void immuneCountDown() {
		immuneCountDown--;
		if (immuneCountDown == 0) {
			immune = false;
		}
	}

	public void setImmuneCountDown(int immuneCountDown) {
		this.immuneCountDown = immuneCountDown;
	}

	public void updateHitBox() {
		hitbox.set(x, y, x + h_left2.getWidth(), y + h_left2.getHeight());

	}

	public void checkOri() {
		float dx = x_ - x;
		float dy = y_ - y;

		if (dy >= dx && dy >= -dx) {
			this.orientation = 3;
		}
		if (dy <= dx && dy <= -dx) {
			this.orientation = 2;
		}
		if (dy >= dx && dy <= -dx) {
			this.orientation = 1;
		}
		if (dy <= dx && dy >= -dx) {
			this.orientation = 0;
		}
	}

	public void HumanMove(int p) {
		checkOri();
		walking = false;
		double close = 2;
		double vx = 0;
		double vy = 0;
		if (p == 0) {
			if (x != x_ && y != y_) {
				walking = true;
				double slope = (y - y_) / (x - x_);
				double angle = Math.atan(slope);
				vx = v * Math.cos(angle);
				vy = v * Math.sin(angle);
				if (x > x_) {
					vx = -vx;
					vy = -vy;
				}
			}
		} else if (p == 1) {
			if (x != x_ && y != y_) {
				walking = true;
				if (y > y_) {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = v * Math.cos(angle);
					vy = 0;
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}
				} else {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = v * Math.cos(angle);
					vy = v * Math.sin(angle);
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}

				}

			}
		} else if (p == 2) {
			if (x != x_ && y != y_) {
				walking = true;
				if (y < y_) {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = v * Math.cos(angle);
					vy = 0;
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}
				} else {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = v * Math.cos(angle);
					vy = v * Math.sin(angle);
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}

				}

			}
		} else if (p == 3) {
			if (x != x_ && y != y_) {
				walking = true;
				if (x < x_) {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = 0;
					vy = v * Math.sin(angle);
					;
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}
				} else {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = v * Math.cos(angle);
					vy = v * Math.sin(angle);
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}

				}

			}
		} else if (p == 4) {
			if (x != x_ && y != y_) {
				walking = true;
				if (x > x_) {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = 0;
					vy = v * Math.sin(angle);
					;
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}
				} else {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = v * Math.cos(angle);
					vy = v * Math.sin(angle);
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}

				}

			}
		}
		x += vx;
		y += vy;
		if (x - x_ < close && x - x_ > -close) {
			x = x_;
		}
		if (y - y_ < close && y - y_ > -close) {
			y = y_;
		}
		if (x == x_ && y == y_) {
			walking = false;
		}

	}

	public void bombDecrease() {
		if (this.bombRegular > 0) {
			this.bombRegular--;
		}
	}

	public boolean hpDecrease() {
		if (immune) {
			return true;
		} else if (this.healthPoint > 1) {
			this.healthPoint--;
			this.immune = true;
			this.immuneCountDown = 30;
			return true;
		} else {
			return false;
		}
	}

	public void bombIncrease() {
		this.bombRegular++;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

}
