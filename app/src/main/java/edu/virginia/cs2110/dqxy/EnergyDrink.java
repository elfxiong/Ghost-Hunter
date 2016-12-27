package edu.virginia.cs2110.dqxy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class EnergyDrink extends Coin {

    private float x;
    private float y;
    private Bitmap drink1;
    private Bitmap drink2;
    private Bitmap drink3;

    public EnergyDrink(Context context) {
        super(context);
    }

    public EnergyDrink(float x, float y, Context mapView) {
        super(x, y, mapView);
        init(x, y);
    }

    public void init(float x, float y) {
        this.x = x;
        this.y = y;


        // Resources res = getResources();
        this.drink1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.energy_drink1);
        this.drink2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.energy_drink2);
        this.drink3 = BitmapFactory.decodeResource(getResources(),
                R.drawable.energy_drink3);
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

    public Bitmap getDrink1() {
        return drink1;
    }

    public Bitmap getDrink2() {
        return drink2;
    }

    public Bitmap getDrink3() {
        return drink3;
    }

}
