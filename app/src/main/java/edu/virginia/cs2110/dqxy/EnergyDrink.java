package edu.virginia.cs2110.dqxy;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class EnergyDrink extends Coin {
	
	private float x;
	private float y;
	private Bitmap drink1;
	private Bitmap drink2;
	private Bitmap drink3;
	
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

	public void setDrink1(Bitmap drink1) {
		this.drink1 = drink1;
	}

	public Bitmap getDrink2() {
		return drink2;
	}

	public void setDrink2(Bitmap drink2) {
		this.drink2 = drink2;
	}

	public Bitmap getDrink3() {
		return drink3;
	}

	public void setDrink3(Bitmap drink3) {
		this.drink3 = drink3;
	}

	public static void collectEnergyDrink(Human human, ArrayList<EnergyDrink> drinkList) {
		for (int i = 0; i < drinkList.size(); i++){
		if (human.getX() < drinkList.get(i).getX() + 32 && human.getX() > drinkList.get(i).getX() - 32
				&& human.getY() < drinkList.get(i).getY() + 32 && human.getY() > drinkList.get(i).getY() - 80) {
			drinkList.remove(i);
//			MapView.human.setV(MapView.human.getV() * 2);
		}
	}

}
}
