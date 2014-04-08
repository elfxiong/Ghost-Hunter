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

	private Paint paint;
	private Paint redPaint;
	private Human human;
	private double dangerLevel;
	private Ghost ghost;

	public Ghost getGhost() {
		return ghost;
	}

	public void setGhost(Ghost ghost) {
		this.ghost = ghost;
	}

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
		human = new Human(this.getContext());
		ghost = new Ghost(10, 10, this.getContext());
		// Resources res = getResources();
		// bitmapH = BitmapFactory.decodeResource(res, R.drawable.human);
		// bitmapG = BitmapFactory.decodeResource(res, R.drawable.ghost);
		redPaint = new Paint();
		redPaint.setARGB(125, 100, 100, 100);
	}

	// Need to override the onDraw method to tell it what to draw
	@Override
	public void onDraw(Canvas c) {
		super.onDraw(c);
		float h = this.getMeasuredHeight();
		float w = this.getMeasuredWidth();

		// draw the human
		c.drawBitmap(human.getImage(), human.getX(), human.getY(), redPaint);
		c.drawRect(w * 0.30F, h * 0.03F,
				(float) (w * 0.70F * (dangerLevel) / 10), h * 0.07F, redPaint);
		c.drawCircle(50, 50, 30, redPaint);

		// draw the ghost
		c.drawBitmap(ghost.getImage(), ghost.getX(), ghost.getY(), paint);

		// add the ghost

	}

}
