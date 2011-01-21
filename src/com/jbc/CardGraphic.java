package com.jbc;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class CardGraphic extends GraphicObject {

	public final static int WIDTH  = 150;
	public final static int HEIGHT = 200;
	
	public final static int DISPLAYED_WIDTH = WIDTH / 4;
	public final static int DISPLAYED_HEIGHT = HEIGHT / 4;
	
	public final static float SCALE_WIDTH = 0.25f;
	public final static float SCALE_HEIGHT = 0.25f;
	
	
	/**
	 * Create card from whole deck bitmap. The card will move from origin to destination
	 * @param bitmap the whole deck bitmap
	 * @param x originX
	 * @param y originY
	 * @param toX destinationX
	 * @param toY destinationY
	 * @param card attached card
	 */
	public CardGraphic(Bitmap bitmap, int x, int y, int toX, int toY) {
		super(x, y, toX, toY);
		int left = WIDTH * (8 - 1);
		int top = HEIGHT * (4 - 1);
		speedX = 5;
		speedY = 3;
		
		Matrix matrix = new Matrix();
		matrix.postScale(SCALE_WIDTH, SCALE_HEIGHT);
		this._bitmap = Bitmap.createBitmap(bitmap, left, top, WIDTH, HEIGHT, matrix, false);
	}
	
	public void animate() {
		
		x += speedX;
		y += speedY;
		
		if (x < 0){
			x = -x;
			speedX *= -1;
		}
		if (x > toX){
			x -= speedX;
			speedX *= -1;
		}
		if (y < 0){
			y = -y;
			speedY *= -1;
		}
		if (y > toY){
			y -= speedY;
			speedY *= -1;
		}
		
	}
	
	

	
}
