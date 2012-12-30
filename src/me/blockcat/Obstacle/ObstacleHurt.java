package me.blockcat.Obstacle;

import java.awt.Color;
import java.awt.Graphics2D;

public class ObstacleHurt extends Obstacle {

	private boolean visible = true;

	public ObstacleHurt(int x, int y, boolean visible) {
		super(x, y);
		this.visible = visible;
	}

	@Override
	public void render(int x, int y, Graphics2D g) {
		if (visible) {
			g.setColor(Color.RED);
			g.fill3DRect(x, y, WIDTH, HEIGHT, true);
		}
	}

}
