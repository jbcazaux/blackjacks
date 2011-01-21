package com.jbc;

import android.graphics.Bitmap;

public abstract class GraphicObject {
	protected Bitmap _bitmap;
	protected float x, y, toX, toY;
	protected float speedX, speedY;

	private boolean moving;

	public GraphicObject(Bitmap bitmap, int x, int y, int toX, int toY) {
		_bitmap = bitmap;
		this.x = x;
		this.y = y;
		
		move(toX, toY);
	}
	
	public GraphicObject(int x, int y, int toX, int toY) {
		this.x = x;
		this.y = y;
		
		move(toX, toY);
	}
	
	public GraphicObject(Bitmap bitmap, int x, int y) {
		_bitmap = bitmap;
		this.x = x;
		this.y = y;
		
		moving = false;
		
	}

	public GraphicObject(int x, int y) {
		this.x = x;
		this.y = y;
		
		moving = false;
		
	}
	
	public Bitmap getGraphic() {
		return _bitmap;
	}

	public void animate() {
		if (isMoving()) {
			x += speedX;
			y += speedY;

			if ((x < toX && speedX <0) ||
					(x > toX && speedX > 0)) {
				x = toX;
			}
			if ((y < toY && speedY <0) ||
					(y > toY && speedY > 0)) {
				y = toY;
			}
			if (x == toX && y == toY) {
				moving = false;
			}
		}
	}
	
	public void move(int toX, int toY){
		this.toX = toX;
		this.toY = toY;
		
		final float div = 50f;
		
		this.speedX = ((float)(toX - x) / div);
		this.speedY = ((float)toY - y) / div;
		
		moving = true;
	}

	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}

	public boolean isMoving(){
		return moving;
	}
}
