package edu.virginia.cs2110.dqxy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Explosion extends Coin {

    private float x;
    private float y;
    private Bitmap explosion1;
    private Bitmap explosion2;
    private Bitmap explosion3;
    private Bitmap explosion4;
    private Bitmap explosion5;
    private int explosionCount;

    public Explosion(Context context) {
        super(context);
    }

    public Explosion(float x, float y, Context mapView) {
        super(x, y, mapView);
    }

    public void init(float x, float y) {
        this.x = x;
        this.y = y;
        explosionCount = 0;
        // Resources res = getResources();
        this.explosion1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.explosion1);
        this.explosion2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.explosion2);
        this.explosion3 = BitmapFactory.decodeResource(getResources(),
                R.drawable.explosion3);
        this.explosion4 = BitmapFactory.decodeResource(getResources(),
                R.drawable.explosion4);
        this.explosion5 = BitmapFactory.decodeResource(getResources(),
                R.drawable.explosion5);

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

    public void explode() {
        this.explosionCount++;
    }

    public Bitmap getExplosion1() {
        return explosion1;
    }

    public Bitmap getExplosion2() {
        return explosion2;
    }

    public Bitmap getExplosion3() {
        return explosion3;
    }

    public Bitmap getExplosion4() {
        return explosion4;
    }

    public Bitmap getExplosion5() {
        return explosion5;
    }


    public int getExplosionCount() {
        return explosionCount;
    }


}
