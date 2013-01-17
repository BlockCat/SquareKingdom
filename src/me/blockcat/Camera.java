package me.blockcat;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.blockcat.Entity.Entity;
import me.blockcat.Obstacle.Obstacle;

public class Camera {

	private GUIGame game = null;
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	private int x = 200;
	private int y = 200;
	
	public Camera(GUIGame guiGame) {
		this.game = guiGame;
	}

	private synchronized List<Obstacle> getViewableObstacles() {
		List<Obstacle> list = new ArrayList<Obstacle>();
		for (Obstacle obs : game.getObstacles()) {
			if (obs.getX() >= (x * -1) - 20 && obs.getX() <= (x * -1) + WIDTH) {
				if (obs.getY() >= (y * -1) - 20 && obs.getY() <= (y * -1) + HEIGHT) {
					list.add(obs);
				}
			}
		}
		return list;
	}

	private synchronized List<Entity> getViewableEntities() {
		List<Entity> list = new ArrayList<Entity>();
		for (Entity ent : game.getEntities()) {
			if (ent.getX() >= (x * -1) - 20 && ent.getX() <= (x * -1) + WIDTH) {
				if (ent.getY() >= (y * -1) - 20 && ent.getY() <= (y * -1) + HEIGHT) {
					list.add(ent);					
				}
			}
		}
		return list;
	}

	public synchronized void update(Entity player) {
		if (player != null) {
			x = (player.getX() - (WIDTH / 2)) * -1;
			y = (player.getY() - (HEIGHT / 2)) * -1;
		}
		
		
	}

	public synchronized void render(Graphics2D g) {
		
		Iterator<Obstacle> it = this.getViewableObstacles().iterator();
		while(it.hasNext()) {
			Obstacle obs = it.next();
			obs.render(x + obs.getX() , y + obs.getY(), g);
		}
		Iterator<Entity> entIt = this.getViewableEntities().iterator();
		while(entIt.hasNext()) {
			Entity ent = entIt.next();
			ent.render(x + ent.getX(), y + ent.getY(), g);
		}
		/*for (Obstacle obs : this.viewableObs) {
			obs.render(x + obs.getX() , y + obs.getY(), g);
		}*/
		/*for (Entity ent : this.viewableEnts) {
			ent.render(x + ent.getX(), y + ent.getY(), g);
		}*/
	}

}
