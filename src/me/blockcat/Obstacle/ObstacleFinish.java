package me.blockcat.Obstacle;

import java.awt.Color;
import java.awt.Graphics2D;

public class ObstacleFinish extends Obstacle {

	public ObstacleFinish(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void render(int x, int y, Graphics2D g) {
		g.setColor(new Color(255,233,0));
		g.fill3DRect(x, y, WIDTH, HEIGHT, false);
	}

}
