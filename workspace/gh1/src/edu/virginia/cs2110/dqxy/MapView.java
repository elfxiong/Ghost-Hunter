package edu.virginia.cs2110.dqxy;

import java.io.File;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.media.Image;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class MapView extends View {

	private Paint paint;
	
	
	public MapView(Context context) {
		super(context);
		this.init();
	}

	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.init();
	}

	public MapView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.init();
	}

	
	private void init(){
	
	}
	
	
	@Override
	public void onDraw(Canvas c) {
		
		super.onDraw(c);//clear all the image
		int h = this.getMeasuredHeight();
		int w = this.getMeasuredWidth();
	c.drawCircle(40,20,15,paint);
//		Resources res = getResources();
//		Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.human);
//		c.drawBitmap(bitmap, 1, 1, paint);
		
	}
}