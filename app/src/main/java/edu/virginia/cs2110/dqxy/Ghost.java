package edu.virginia.cs2110.dqxy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.view.View;

public class Ghost extends View {

    private Bitmap g_right1;
    private Bitmap g_right2;
    private Bitmap g_right3;
    private Bitmap g_left1;
    private Bitmap g_left2;
    private Bitmap g_left3;
    private Bitmap g_back1;
    private Bitmap g_back2;
    private Bitmap g_back3;
    private Bitmap g_front1;
    private Bitmap g_front2;
    private Bitmap g_front3;

    private Bitmap g2_right;
    private Bitmap g2_left;
    private Bitmap g2_back;
    private Bitmap g2_front;

    private int orientation;
    private float x_change;
    private float y_change;
    private int type = 0;

    private float x;
    private float y;
    private float x_;
    private float y_;
    private float v;
    private RectF hitbox = new RectF();

    public Ghost(Context context) {
        super(context);
    }

    public Ghost(int type, float x, float y, Context mapView) {
        super(mapView);
        init(type, x, y);
    }

    public void updateHitbox() {
        this.hitbox.set(x, y, x + g_left2.getWidth(), y + g_left2.getHeight());
    }

    public void setV(float v) {
        this.v = v;
    }

    public void init(int type, float x, float y) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.hitbox.set(x + 30, y + 50, x + 105, y + 200);

        Resources res = getResources();
        this.g_front1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_front1);
        this.g_front2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_front2);
        this.g_front3 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_front3);
        this.g_back1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_back1);
        this.g_back2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_back2);
        this.g_back3 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_back3);
        this.g_right1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_right1);
        this.g_right2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_right2);
        this.g_right3 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_right3);
        this.g_left1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_left1);
        this.g_left2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_left2);
        this.g_left3 = BitmapFactory.decodeResource(getResources(),
                R.drawable.ghost1_left3);

        this.g2_back = BitmapFactory.decodeResource(res, R.drawable.g2_back);
        this.g2_front = BitmapFactory.decodeResource(res, R.drawable.g2_front);
        this.g2_left = BitmapFactory.decodeResource(res, R.drawable.g2_left);
        this.g2_right = BitmapFactory.decodeResource(res, R.drawable.g2_right);

        this.v = (float) (DiffSetting.difficulty + 10) / 10;

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

    public void moveToHuman(int p, MapView... params) {
        checkOri();

        this.x_ = params[0].getHuman().getX();
        this.y_ = params[0].getHuman().getY();
        double vx = 0;
        double vy = 0;

        if (p == 0) {
            if (x != x_ && y != y_) {
                double slope = (y - y_) / (x - x_);
                double angle = Math.atan(slope);
                vx = v * Math.cos(angle);
                vy = v * Math.sin(angle);
                if (x > x_) {
                    vx = -vx;
                    vy = -vy;
                }
            }
        } else if (p == 1) {
            if (x != x_ && y != y_) {
                if (y > y_) {
                    double slope = (y - y_) / (x - x_);
                    double angle = Math.atan(slope);
                    vx = v * Math.cos(angle);
                    vy = 0;
                    if (x > x_) {
                        vx = -vx;
                        vy = -vy;
                    }
                } else {
                    double slope = (y - y_) / (x - x_);
                    double angle = Math.atan(slope);
                    vx = v * Math.cos(angle);
                    vy = v * Math.sin(angle);
                    if (x > x_) {
                        vx = -vx;
                        vy = -vy;
                    }

                }

            }
        } else if (p == 2) {
            if (x != x_ && y != y_) {

                if (y < y_) {
                    double slope = (y - y_) / (x - x_);
                    double angle = Math.atan(slope);
                    vx = v * Math.cos(angle);
                    vy = 0;
                    if (x > x_) {
                        vx = -vx;
                        vy = -vy;
                    }
                } else {
                    double slope = (y - y_) / (x - x_);
                    double angle = Math.atan(slope);
                    vx = v * Math.cos(angle);
                    vy = v * Math.sin(angle);
                    if (x > x_) {
                        vx = -vx;
                        vy = -vy;
                    }

                }

            }
        } else if (p == 3) {
            if (x != x_ && y != y_) {

                if (x < x_) {
                    double slope = (y - y_) / (x - x_);
                    double angle = Math.atan(slope);
                    vx = 0;
                    vy = v * Math.sin(angle);

                    if (x > x_) {
                        vx = -vx;
                        vy = -vy;
                    }
                } else {
                    double slope = (y - y_) / (x - x_);
                    double angle = Math.atan(slope);
                    vx = v * Math.cos(angle);
                    vy = v * Math.sin(angle);
                    if (x > x_) {
                        vx = -vx;
                        vy = -vy;
                    }

                }

            }
        } else if (p == 4) {
            if (x != x_ && y != y_) {

                if (x > x_) {
                    double slope = (y - y_) / (x - x_);
                    double angle = Math.atan(slope);
                    vx = 0;
                    vy = v * Math.sin(angle);

                    if (x > x_) {
                        vx = -vx;
                        vy = -vy;
                    }
                } else {
                    double slope = (y - y_) / (x - x_);
                    double angle = Math.atan(slope);
                    vx = v * Math.cos(angle);
                    vy = v * Math.sin(angle);
                    if (x > x_) {
                        vx = -vx;
                        vy = -vy;
                    }

                }

            }
        }
        x += vx;
        y += vy;

    }

    public void moveIndependent() {
        checkOri();
        this.updateHitbox();
        x += this.x_change;
        y += this.y_change;
    }

    public void checkOri() {
        float dx = x_ - x;
        float dy = y_ - y;

        if (dy >= dx && dy >= -dx) {
            this.orientation = 3;
            // Log.d("Ghost class", "Front");
        } else if (dy <= dx && dy <= -dx) {
            this.orientation = 2;
            // Log.d("Ghost class", "back");
        } else if (dy >= dx && dy <= -dx) {
            this.orientation = 1;
            // Log.d("Ghost class", "left");
        } else if (dy <= dx && dy >= -dx) {
            this.orientation = 0;
            // Log.d("Ghost class", "right");
        }
    }

    public Bitmap getG2_right() {
        return g2_right;
    }

    public Bitmap getG2_left() {
        return g2_left;
    }

    public Bitmap getG2_back() {
        return g2_back;
    }

    public Bitmap getG2_front() {
        return g2_front;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setX_change(float x_change) {
        this.x_change = x_change;
    }

    public void setY_change(float y_change) {
        this.y_change = y_change;
    }

    public Bitmap getG_right1() {
        return g_right1;
    }

    public Bitmap getG_right2() {
        return g_right2;
    }

    public Bitmap getG_right3() {
        return g_right3;
    }

    public Bitmap getG_left1() {
        return g_left1;
    }

    public Bitmap getG_left2() {
        return g_left2;
    }

    public Bitmap getG_left3() {
        return g_left3;
    }

    public Bitmap getG_back1() {
        return g_back1;
    }

    public Bitmap getG_back2() {
        return g_back2;
    }

    public Bitmap getG_back3() {
        return g_back3;
    }

    public Bitmap getG_front1() {
        return g_front1;
    }

    public Bitmap getG_front2() {
        return g_front2;
    }

    public Bitmap getG_front3() {
        return g_front3;
    }

    public RectF getHitbox() {
        return hitbox;
    }

    @Override
    public String toString() {
        return type + "," + x + "," + y + "," + v;
    }
}