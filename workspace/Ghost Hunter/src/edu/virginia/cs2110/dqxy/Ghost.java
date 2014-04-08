package edu.virginia.cs2110.dqxy;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


public class Ghost extends View{
	private Bitmap image;
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

	
	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public Ghost(int x, int y,Context mapView) {
		super(mapView);
		init(x,y);
	}
	
	public Ghost(int x, int y, Context context,AttributeSet attrs){
		super(context, attrs);
		init(x,y);
	}
	
	public Ghost(int x, int y,Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(x,y);
	}

	
	public void init(int x, int y){
		this.x = x;
		//hitbox.left = (int) (x + 0.5);
		this.y = y;
		//hitbox.top = (int) (y + 0.5);
		Resources res = getResources();
		image = BitmapFactory.decodeResource(res, R.drawable.ghost);
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
 
	public void moveToHuman(){
		
	}
	
}
