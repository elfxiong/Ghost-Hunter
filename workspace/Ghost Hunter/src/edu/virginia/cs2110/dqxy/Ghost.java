package edu.virginia.cs2110.dqxy;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;


public class Ghost {

	private float x;
	

	private float y;
	private float width=50;
	private float height=80;
	private float x_;
	private float y_;
	private float v = 40;
	private Rect hitbox = new Rect();
	private boolean intersectwwall;
	private boolean intersectwzombie;
	private ArrayList<Rect> oblist;

	public Ghost(int x, int y) {
		super();
		
		this.x = x;
		//hitbox.left = (int) (x + 0.5);
		this.y = y;
		//hitbox.top = (int) (y + 0.5);
		
		// Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.human);
	
		
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

	
}
