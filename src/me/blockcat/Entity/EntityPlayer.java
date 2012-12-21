package me.blockcat.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import me.blockcat.Direction;
import me.blockcat.GUIGame;
import me.blockcat.Main;
import me.blockcat.Obstacle.Obstacle;
import me.blockcat.Obstacle.ObstacleWall;

public class EntityPlayer extends Entity{

	private boolean inJump = false;
	private boolean ableSpace = true;
	private GUIGame game;

	public EntityPlayer(int x, int y, GUIGame game) {
		super(x, y);
		this.game = game;
	}

	@Override
	public void move() {
		super.move();

		if (getSolid(Direction.DOWN) instanceof ObstacleWall) {// if standing on wall
			ySpeed = 0;
			y = getSolid(Direction.DOWN).getY() - 16;
			inJump = false;
		} else {
			ySpeed++;
			inJump = true;
		}

		if (Main.main.keyListener.isPressed(KeyEvent.VK_SPACE)) {
			if (!inJump && !(getSolid(Direction.UP) instanceof ObstacleWall) && ableSpace) {
				this.jump();
				ableSpace = false;
			}
		} else if (!inJump){
			ableSpace = true;
		}

		if (inJump && getSolid(Direction.UP) instanceof ObstacleWall) {
			if (ySpeed < 0) {
				ySpeed = 0;
			}
		}

		if (ySpeed >= 16) {
			ySpeed = 16;
		}
		if (Main.main.keyListener.isPressed(KeyEvent.VK_A)) {
			if (xSpeed >= -5) {
				xSpeed--;
			} else {
				xSpeed = -5;
			}
		} else if (Main.main.keyListener.isPressed(KeyEvent.VK_D)) {
			if (xSpeed <= 5) {
				xSpeed++;
			} else {
				xSpeed = 5;
			}

		} else {
			if (xSpeed > 0) {
				xSpeed -= 0.5;
				
			} else if (xSpeed < 0) {
				xSpeed += 0.5;
				
			}
		}
		if (xSpeed > 0) {
			checkBump(Direction.RIGHT);
		} else {
			checkBump(Direction.LEFT);
		}
	}

	private void checkBump(Direction direction) {
		if (getSolid(direction) instanceof ObstacleWall) {
			xSpeed = 0;

			if (direction == Direction.LEFT) {
				this.x = getSolid(direction).getX() + 16;
			} else if (direction == Direction.RIGHT) {
				this.x = getSolid(direction).getX() - 16;
			}
		}
	}

	private void jump() {
		ySpeed = -14;
		inJump = true;
	}

	private Obstacle getSolid(Direction direction) {
		return game.getSolid(x,y, direction);

	}

	@Override
	public void render(int x, int y, Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 16, 16);
		g.fill3DRect(x, y, 16, 16, true);
	}

}
