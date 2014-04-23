package edu.virginia.cs2110.dqxy;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Human extends View {

	private Bitmap h_right;
	private Bitmap h_left;
	private Bitmap h_backFacing;
	private Bitmap h_frontFacing;
	private int orientation; // 0 for right, 1 for left, 2 for back facing 3 for
								// front facing
	private float x;
	private float y;
	private float width = 50;
	private float height = 80;
	private float x_;
	private float y_;
	private float v = 40;
	private RectF hitbox = new RectF();
	public RectF getHitbox() {
		return hitbox;
	}

	public void setHitbox(RectF hitbox) {
		this.hitbox = hitbox;
	}

	private boolean intersectwwall;
	private boolean intersectwzombie;
	private boolean kilTap;
	private ArrayList<Rect> oblist;
	private int bombRegular;

	public boolean isKilTap() {
		return kilTap;
	}

	public void setKilTap(boolean kilTap) {
		this.kilTap = kilTap;
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

	private int bombSuper;

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public Human(float x, float y, Context mapView) {
		super(mapView);
		init(x, y);

	}

	public Human(float x, float y, Context context, AttributeSet attrs) {
		super(context, attrs);
		init(x, y);
	}

	public Human(float x, float y, Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(x, y);
	}

	public void init(float x, float y) {
		this.x = x;
		// hitbox.left = (int) (x + 0.5);
		this.y = y;
		// hitbox.top = (int) (y + 0.5);

		// Resources res = getResources();
		this.h_frontFacing = BitmapFactory.decodeResource(getResources(),
				R.drawable.human_front);
		this.h_backFacing = BitmapFactory.decodeResource(getResources(),
				R.drawable.human_back);
		this.h_right = BitmapFactory.decodeResource(getResources(),
				R.drawable.human_right);
		this.h_left = BitmapFactory.decodeResource(getResources(),
				R.drawable.human_left);

		this.bombRegular = 10;
		this.bombSuper = 10000;
		this.kilTap = false;
	}

	public Bitmap getH_right() {
		return h_right;
	}

	public void setH_right(Bitmap h_right) {
		this.h_right = h_right;
	}

	public Bitmap getH_left() {
		return h_left;
	}

	public void setH_left(Bitmap h_left) {
		this.h_left = h_left;
	}

	public Bitmap getH_backFacing() {
		return h_backFacing;
	}

	public void setH_backFacing(Bitmap h_backFacing) {
		this.h_backFacing = h_backFacing;
	}

	public Bitmap getH_frontFacing() {
		return h_frontFacing;
	}

	public void setH_frontFacing(Bitmap h_frontFacing) {
		this.h_frontFacing = h_frontFacing;
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
	public void updateHitBox() {
		hitbox.set(x + 25, y + 50, x + 100, y + 180);

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
		double v = 4;
		double close = 2;
		double vx = 0;
		double vy = 0;
		if (p == 0) {
			if (x != x_ && y != y_) {
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

				if (y > y_) {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = v * Math.cos(angle);
					vy = 0;
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}
				}
				else{
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

				if (y < y_) {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = v * Math.cos(angle);
					vy = 0;
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}
				}
				else{
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
		}else if (p == 3) {
			if (x != x_ && y != y_) {

				if (x < x_) {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = 0;
					vy = v * Math.sin(angle);;
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}
				}
				else{
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
		}else if (p == 4) {
			if (x != x_ && y != y_) {

				if (x > x_) {
					double slope = (y - y_) / (x - x_);
					double angle = Math.atan(slope);
					vx = 0;
					vy = v * Math.sin(angle);;
					if (x > x_) {
						vx = -vx;
						vy = -vy;
					}
				}
				else{
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

	}

	public void bombDecrease() {
		if (this.bombRegular > 0) {
			this.bombRegular--;
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
