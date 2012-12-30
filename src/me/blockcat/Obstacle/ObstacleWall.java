package me.blockcat.Obstacle;

import java.awt.Color;
import java.awt.Graphics2D;

public class ObstacleWall extends Obstacle {
	
	
	public ObstacleWall(int x, int y) {
		super(x, y);
	}

	@Override
	public void render(int x, int y, Graphics2D g) {
		g.setColor(new Color(155,243,61));
		//g.fillRect(x, y, WIDTH, HEIGHT);
		g.fill3DRect(x, y, WIDTH, HEIGHT, true);
	}

}
