package edu.virginia.cs2110.dqxy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MapView extends View {

	// Local Variable

	public Human getHuman() {
		return human;
	}

	public void setHuman(Human human) {
		this.human = human;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	private Paint paint;
	private Paint redPaint;
	private Human human;
	private Bitmap bitmap;
	private double dangerLevel;

	// Three constructors - hover on CircleView, auto generate all of them
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
		human = new Human(100, 100);
		Resources res = getResources();
		bitmap = BitmapFactory.decodeResource(res, R.drawable.human);
		
		redPaint = new Paint();
		redPaint.setARGB(125, 100, 100, 100);
	}

	// Need to override the onDraw method to tell it what to draw
	@Override
	public void onDraw(Canvas c) {
		super.onDraw(c); // this will erase everything and make it white
		// (the default background color), or else we will see the
		// previous version of view (overwriting)

		// First tiny circle we drew:
		// Format: c.drawCircle(cx, cy, radius, paint);
		// c.drawCircle(40, 20, 15, paint); // small circle

		// find out height and width of this Canvas:
		float h = this.getMeasuredHeight();
		float w = this.getMeasuredWidth();

		
		
		// ** STATIC ** //
		// c.drawCircle(cx, cy, radius, paint);
		// centering on x (width), centering on y (height)
		// taking min of {width, height} and / by 2
		// (second circle, dividing by 4, for a different sized circle)
		// c.drawCircle(w/2, h/2, Math.min(w, h)/2, paint);
		// c.drawCircle(w/2, h/2, Math.min(w, h)/4, paint); //second circle

		// ** DYNAMIC DISPLAY ** //
		// int x = (int)(Math.random()*w); // so that x is different each time
		// c.drawCircle(x, h/2, Math.min(w, h)/2, paint);
		// c.drawCircle(x, h/2, Math.min(w, h)/4, paint); //second circle
		// human.setX_((float)Math.random()*w);
		// human.setY_((float)Math.random()*h);
		c.drawBitmap(bitmap, human.getX(), human.getY(), redPaint);
		c.drawRect(w*0.30F,h*0.03F, (float) (w*0.70F*((dangerLevel)/10)), h*0.07F, redPaint);
		c.drawCircle(50, 50, 30, redPaint);
	}

	// @Override
	// public boolean onTouch(View map, MotionEvent me) {
	// getHuman().setX_(me.getX());
	// getHuman().setY_(me.getY());
	// Log.d("called!", "dafajlsd;fjldasjfal;");
	// return true;
	// }

}