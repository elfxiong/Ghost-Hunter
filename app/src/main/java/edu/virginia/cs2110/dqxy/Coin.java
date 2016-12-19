package edu.virginia.cs2110.dqxy;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class Coin extends View {

	private Bitmap coin1;
	private Bitmap coin2;
	private Bitmap coin3;
	private Bitmap coin4;
	private Bitmap coin5;
	private Bitmap coin6;
	private Bitmap coin7;
	private Bitmap coin8;
	private float x;
	private float y;
	private Rect hitbox = new Rect();
	private boolean intersectwwall;
	private boolean intersectwzombie;
	private ArrayList<Rect> oblist;

	public Coin(float x, float y, Context mapView) {
		super(mapView);
		init(x, y);
	}

	public void init(float x, float y) {
		this.x = x;
		this.y = y;

		// Resources res = getResources();
		this.coin1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.coin1);
		this.coin2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.coin2);
		this.coin3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.coin3);
		this.coin4 = BitmapFactory.decodeResource(getResources(),
				R.drawable.coin4);
		this.coin5 = BitmapFactory.decodeResource(getResources(),
				R.drawable.coin5);
		this.coin6 = BitmapFactory.decodeResource(getResources(),
				R.drawable.coin6);
		this.coin7 = BitmapFactory.decodeResource(getResources(),
				R.drawable.coin7);
		this.coin8 = BitmapFactory.decodeResource(getResources(),
				R.drawable.coin8);

	}

	public Bitmap getCoin1() {
		return coin1;
	}

	public void setCoin1(Bitmap coin1) {
		this.coin1 = coin1;
	}

	public Bitmap getCoin2() {
		return coin2;
	}

	public void setCoin2(Bitmap coin2) {
		this.coin2 = coin2;
	}

	public Bitmap getCoin3() {
		return coin3;
	}

	public void setCoin3(Bitmap coin3) {
		this.coin3 = coin3;
	}

	public Bitmap getCoin4() {
		return coin4;
	}

	public void setCoin4(Bitmap coin4) {
		this.coin4 = coin4;
	}

	public Bitmap getCoin5() {
		return coin5;
	}

	public void setCoin5(Bitmap coin5) {
		this.coin5 = coin5;
	}

	public Bitmap getCoin6() {
		return coin6;
	}

	public void setCoin6(Bitmap coin6) {
		this.coin6 = coin6;
	}

	public Bitmap getCoin7() {
		return coin7;
	}

	public void setCoin7(Bitmap coin7) {
		this.coin7 = coin7;
	}

	public Bitmap getCoin8() {
		return coin8;
	}

	public void setCoin8(Bitmap coin8) {
		this.coin8 = coin8;
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

	public Rect getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rect hitbox) {
		this.hitbox = hitbox;
	}

	public boolean isIntersectwwall() {
		return intersectwwall;
	}

	public void setIntersectwwall(boolean intersectwwall) {
		this.intersectwwall = intersectwwall;
	}

	public boolean isIntersectwzombie() {
		return intersectwzombie;
	}

	public void setIntersectwzombie(boolean intersectwzombie) {
		this.intersectwzombie = intersectwzombie;
	}

	public ArrayList<Rect> getOblist() {
		return oblist;
	}

	public void setOblist(ArrayList<Rect> oblist) {
		this.oblist = oblist;
	}

}
