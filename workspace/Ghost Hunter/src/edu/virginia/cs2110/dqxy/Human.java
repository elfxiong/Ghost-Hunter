package edu.virginia.cs2110.dqxy;

import java.util.ArrayList;
import java.util.ResourceBundle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class Human extends View {
	private Bitmap image;
	private float x;

	private float y;
	private float width = 50;
	private float height = 80;
	private float x_;
	private float y_;
	private float v = 40;
	private Rect hitbox = new Rect();
	private boolean intersectwwall;
	private boolean intersectwzombie;
	private ArrayList<Rect> oblist;

	public Human(Context mapView) {
		super(mapView);
		init();

	}

	public Human(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Human(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void init() {
		this.x = x;
		// hitbox.left = (int) (x + 0.5);
		this.y = y;
		// hitbox.top = (int) (y + 0.5);

		Resources res = getResources();
		image = BitmapFactory.decodeResource(getResources(), R.drawable.human);
	}

	
	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
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
	
	public void HumanMove() {
		double v = 4;
		double close = 2;
		//MapView map1 = params[0];
//		Human human = map1.getHuman();
//
//		float x = human.getX();
//		float y = human.getY();
//		float x_ = human.getX_();
//		float y_ = human.getY_();

		if (x != x_ && y != y_) {
			double slope = (y - y_) / (x - x_);
			double angle = Math.atan(slope);
			double vx = v * Math.cos(angle);
			double vy = v * Math.sin(angle);
			if (x > x_) {
				vx = -vx;
				vy = -vy;
			}
			x += vx;
			y += vy;
		}
		if (x - x_ < close && x - x_ > -close) {
			x = x_;
		}
		if (y - y_ < close && y - y_ > -close) {
			y = y_;
		}
		this.setX(x);
		this.setY(y);

	}
	
}
