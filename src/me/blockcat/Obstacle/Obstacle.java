package me.blockcat.Obstacle;

import java.awt.Graphics2D;

public class Obstacle {
	
	protected int x = 0;
	protected int y = 0;
	protected final int WIDTH = 16;
	protected final int HEIGHT = 16;
	
	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;				
	}
	
	public boolean inObject(int x2, int y2) {
		if (x2 >= x && x2 < x + WIDTH)
			if (y2 >= y && y2 < y + HEIGHT) {
				return true;
			}
		return false;
	}

	public void render(int i, int j, Graphics2D g) {
		
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	

}
