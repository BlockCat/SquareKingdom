package me.blockcat.Entity;

import java.awt.Graphics2D;

public class Entity {
	
	protected int x;
	protected int y;
	protected double xSpeed = 0;
	protected double ySpeed = 0;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setXSpeed(int x) {
		xSpeed = x;
	}
	
	public void setYSpeed(int y) {
		ySpeed = y;
	}
	
	public void move() {
		x += xSpeed;
		y += ySpeed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void render(int x, int y, Graphics2D g) {
		
	}

}
