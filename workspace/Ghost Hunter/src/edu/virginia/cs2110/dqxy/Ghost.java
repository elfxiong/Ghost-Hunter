package edu.virginia.cs2110.dqxy;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Ghost extends View implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1899088064795580479L;
	private Bitmap image;
	private Bitmap g_right;
	private Bitmap g_left;
	private Bitmap g_backFacing;
	private Bitmap g_frontFacing;

	private Bitmap g2_right;
	private Bitmap g2_left;
	private Bitmap g2_back;
	private Bitmap g2_front;

	private int orientation;
	private float x_change;
	private float y_change;
	private int type = 0;

	// private Human human;
	private float x;

	private float y;
	private float width = 50;
	private float height = 80;
	private float x_;
	private float y_;
	private float v = 2;
	private RectF hitbox = new RectF();
	private boolean intersectwwall;
	private boolean intersectwzombie;
	private ArrayList<Rect> oblist;

	public void updateHitbox() {
		this.hitbox.set(x + 30, y + 50, x + 105, y + 200);

	}

	public Ghost(int type, float x, float y, Context mapView) {
		super(mapView);
		init(type, x, y);
	}

	public Ghost(int type, float x, float y, float v, Context mapView) {
		super(mapView);
		init(type, x, y);
		this.v = v;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
	}

	public void init(int type, float x, float y) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.hitbox.set(x + 30, y + 50, x + 105, y + 200);

		Resources res = getResources();
		this.g_backFacing = BitmapFactory.decodeResource(res,
				R.drawable.ghost_back);
		this.g_frontFacing = BitmapFactory.decodeResource(res,
				R.drawable.ghost_front);
		this.g_left = BitmapFactory.decodeResource(res, R.drawable.ghost_left);
		this.g_right = BitmapFactory
				.decodeResource(res, R.drawable.ghost_right);

		this.g2_back = BitmapFactory.decodeResource(res, R.drawable.g2_back);
		this.g2_front = BitmapFactory.decodeResource(res, R.drawable.g2_front);
		this.g2_left = BitmapFactory.decodeResource(res, R.drawable.g2_left);
		this.g2_right = BitmapFactory.decodeResource(res, R.drawable.g2_right);

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

	public void moveToHuman(int p, MapView... params) {
		checkOri();
		
		this.x_ = params[0].getHuman().getX();
		this.y_ = params[0].getHuman().getY();
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

	}

	public void moveIndependent(MapView... params) {
		checkOri();

		x += this.x_change;
		y += this.y_change;

	}

	public void checkOri() {
		float dx = x_ - x;
		float dy = y_ - y;

		if (dy >= dx && dy >= -dx) {
			this.orientation = 3;
			// Log.d("Ghost class", "Front");
		} else if (dy <= dx && dy <= -dx) {
			this.orientation = 2;
			// Log.d("Ghost class", "back");
		} else if (dy >= dx && dy <= -dx) {
			this.orientation = 1;
			// Log.d("Ghost class", "left");
		} else if (dy <= dx && dy >= -dx) {
			this.orientation = 0;
			// Log.d("Ghost class", "right");
		}
	}

	public Bitmap getG_right() {
		return g_right;
	}

	public void setG_right(Bitmap g_right) {
		this.g_right = g_right;
	}

	public Bitmap getG_left() {
		return g_left;
	}

	public void setG_left(Bitmap g_left) {
		this.g_left = g_left;
	}

	public Bitmap getG_backFacing() {
		return g_backFacing;
	}

	public void setG_backFacing(Bitmap g_backFacing) {
		this.g_backFacing = g_backFacing;
	}

	public Bitmap getG_frontFacing() {
		return g_frontFacing;
	}

	public void setG_frontFacing(Bitmap g_frontFacing) {
		this.g_frontFacing = g_frontFacing;
	}

	public Bitmap getImage() {
		return image;
	}

	public Bitmap getG2_right() {
		return g2_right;
	}

	public Bitmap getG2_left() {
		return g2_left;
	}

	public Bitmap getG2_back() {
		return g2_back;
	}

	public Bitmap getG2_front() {
		return g2_front;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public float getX_change() {
		return x_change;
	}

	public void setX_change(float x_change) {
		this.x_change = x_change;
	}

	public float getY_change() {
		return y_change;
	}

	public void setY_change(float y_change) {
		this.y_change = y_change;
	}

	public RectF getHitbox() {
		return hitbox;
	}

	public void setHitbox(RectF hitbox) {
		this.hitbox = hitbox;
	}

	@Override
	public String toString() {
		return type + "," + x + "," + y + "," + v;
	}
}