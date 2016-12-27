package edu.virginia.cs2110.dqxy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    public Coin(Context context) {
        super(context);
    }

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

    public Bitmap getCoin2() {
        return coin2;
    }

    public Bitmap getCoin3() {
        return coin3;
    }

    public Bitmap getCoin4() {
        return coin4;
    }

    public Bitmap getCoin5() {
        return coin5;
    }

    public Bitmap getCoin6() {
        return coin6;
    }

    public Bitmap getCoin7() {
        return coin7;
    }

    public Bitmap getCoin8() {
        return coin8;
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

}
